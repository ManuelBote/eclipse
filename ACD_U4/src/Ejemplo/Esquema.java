
/**
 * ═══════════════════════════════════════════════════════════════════════════════
 *                    ESQUEMA GLOBAL BIBLIOTECA - GUÍA COMPLETA JPA
 * ═══════════════════════════════════════════════════════════════════════════════
 * Documentación didáctica que EXPLICA cada anotación JPA y su función específica.
 * 
 * @author Formación IES Augustbriga - Perplexity AI
 * @created 26 Febrero 2026
 * 
 * ▶️ PROPÓSITO: Gestionar préstamos biblioteca con reglas negocio
 *   - Socio ≤ 2 préstamos simultáneos
 *   - Sanción 7 días si devolución tarde (>14 días)
 *   - Borrado condicional (sin préstamos pendientes)
 */

package Ejemplo;

// ============================================================================
// 1. IMPORTS JPA - ¿PARA QUÉ SIRVEN CADA UNO?
// ============================================================================
/**
 * @Entity: Marca clase como "tabla en BD" (obligatorio)
 * @Table: Especifica NOMBRE tabla física en MySQL
 * @Id: Define clave primaria
 * @GeneratedValue: Cómo generar IDs automáticos
 * @Column: Configura columna BD (nullable, unique, length...)
 * @Temporal: Mapea fechas (SOLO java.util.Date/Calendar)
 * @ManyToOne/@OneToMany: Relaciones entre tablas
 * @JoinColumn: Crea FK física en BD
 * @NamedQuery: Consultas precompiladas (más rápidas)
 * @Transactional: Gestiona transacciones ACID
 */

import javax.persistence.*;  // Todas las anotaciones JPA
import java.io.Serializable; // Obligatorio para entidades
import java.sql.Date;        // Tipo fecha SQL (SIN @Temporal)
import java.util.ArrayList;
import java.util.List;


// ============================================================================
// 2. CLASE SOCIO - TABLA "socio"
// ============================================================================
@Entity  // ← TRANSFORMA esta clase en TABLA BD
@Table(name = "socio")       // ← NOMBRE EXACTO tabla MySQL
@NamedQuery(name = "Socio.findByNif", 
            query = "SELECT s FROM Socio s WHERE s.nif = :nif")
public class Socio implements Serializable {
    
    // ═══════════════════════════════════════════════════════════════════════════
    // ANÁLISIS DETALLADO CADA ATRIBUTO:
    // ═══════════════════════════════════════════════════════════════════════════
    
    /**
     * @Id          → Esta columna será CLAVE PRIMARIA
     * @GeneratedValue(strategy=IDENTITY) 
     *              → MySQL AUTO_INCREMENT (1,2,3...)
     * Ejemplo BD: id_socio INT PRIMARY KEY AUTO_INCREMENT
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * @Column(unique=true, nullable=false, length=9)
     * unique=true     → NO permite duplicados (CREATE UNIQUE INDEX)
     * nullable=false  → NOT NULL en BD
     * length=9        → VARCHAR(9) en MySQL
     * Ejemplo BD: nif VARCHAR(9) UNIQUE NOT NULL
     */
    @Column(unique = true, nullable = false, length = 9)
    private String nif;

    /**
     * Sin propiedades especiales → VARCHAR(255) por defecto
     * nullable=false → NOT NULL
     */
    @Column(nullable = false, length = 100)
    private String nombre;

    /**
     * java.sql.Date → SIN @Temporal (automapeo Hibernate 5.x)
     * @Column(name="fecha_sancion") → nombre columna personalizada
     * null = NO sancionado
     * Ejemplo BD: fecha_sancion DATE NULL
     */
    @Column(name = "fecha_sancion")
    private Date fechaSancion;

    /**
     * RELACIÓN 1:N con Préstamo
     * @OneToMany     → 1 Socio tiene MUCHOS préstamos
     * mappedBy="socio" → Indica campo OPPOSITO en Prestamo que crea FK
     * cascade=ALL    → Propaga operaciones (persist,remove...) a préstamos
     * orphanRemoval  → Borra préstamos si los quitas de la lista
     * fetch=LAZY     → Carga SOLO cuando pides socio.getPrestamos()
     * Ejemplo BD: NO crea columna (la FK está en prestamo.id_socio)
     */
    @OneToMany(mappedBy = "socio", cascade = CascadeType.ALL, 
               orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Prestamo> prestamos = new ArrayList<>();

    // Constructores obligatorios
    public Socio() {}  // ← JPA necesita constructor vacío
    
    public Socio(String nif, String nombre) {
        this.nif = nif;
        this.nombre = nombre;
        this.fechaSancion = null;
    }

    // Getters/Setters (OBLIGATORIOS - JPA los usa para mapeo)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    // ... resto getters/setters
}




// ============================================================================
// 3. CLASE LIBRO - TABLA "libro"  
// ============================================================================
@Entity
@Table(name = "libro")
@NamedQuery(name = "Libro.findByIsbn", query = "SELECT l FROM Libro l WHERE l.isbn = :isbn")
public class Libro implements Serializable {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * isbn = clave natural internacional (13 dígitos)
     * Ejemplo: "978-84-123456-78-9"
     */
    @Column(unique = true, nullable = false, length = 13)
    private String isbn;

    @Column(nullable = false, length = 200)
    private String titulo;

    /**
     * Controla cuántos préstamos simultáneos permite
     * Ej: numEjemplares=3 → máximo 3 préstamos pendientes
     */
    @Column(nullable = false)
    private int numEjemplares;

    /**
     * Cascade DIFERENTE al Socio (cumple enunciado)
     * SOLO PERSIST/MERGE/REMOVE → NO propaga todo
     */
    @OneToMany(mappedBy = "libro", cascade = {CascadeType.PERSIST, 
                                              CascadeType.MERGE, 
                                              CascadeType.REMOVE})
    private List<Prestamo> prestamos;

    public Libro() {}
    // Getters/Setters...
}




// ============================================================================
// 4. CLASE PRESTAMO - TABLA INTERMEDIA N:N
// ============================================================================
@Entity
@Table(name = "prestamo")
@NamedQuery(name = "Prestamo.pendientesTotal", 
            query = "SELECT COUNT(p) FROM Prestamo p WHERE p.fechaDevolucion IS NULL")
public class Prestamo implements Serializable {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * @ManyToOne → MUCHOS préstamos : 1 socio
     * @JoinColumn → CREA COLUMNA FK física en BD
     * Ejemplo BD: id_socio BIGINT NOT NULL (FK a socio.id_socio)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_socio", nullable = false)
    private Socio socio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_libro", nullable = false)
    private Libro libro;

    /**
     * FECHAS con java.sql.Date → SIN @Temporal
     * fechaDevolucion=null → préstamo PENDIENTE
     */
    @Column(name = "fecha_prestamo", nullable = false)
    private Date fechaPrestamo;

    @Column(name = "fecha_devolucion")
    private Date fechaDevolucion;

    // Constructor ESPECIAL con fecha actual automática
    public Prestamo(Socio socio, Libro libro) {
        this.socio = socio;
        this.libro = libro;
        this.fechaPrestamo = new Date(System.currentTimeMillis()); // Hoy
        this.fechaDevolucion = null; // Pendiente
    }
}




// ============================================================================
// 5. persistence.xml - CONFIGURACIÓN GLOBAL
// ============================================================================
/*
<?xml version="1.0" encoding="UTF-8"?>
<persistence xmlns="http://java.sun.com/xml/ns/persistence" version="2.0">
    <persistence-unit name="bdLibreria" transaction-type="RESOURCE_LOCAL">
        
        <!-- CLASES que Hibernate debe mapear -->
        <class>Libreria.Socio</class>
        <class>Libreria.Libro</class>
        <class>Libreria.Prestamo</class>

        <!-- PROVIDER Hibernate -->
        <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
        
        <!-- CONEXIÓN MYSQL -->
        <properties>
            <property name="javax.persistence.jdbc.driver" 
                      value="com.mysql.cj.jdbc.Driver"/>
            <property name="javax.persistence.jdbc.url" 
                      value="jdbc:mysql://localhost:3306/bdbiblioteca?useSSL=false&serverTimezone=UTC"/>
            <property name="javax.persistence.jdbc.user" value="root"/>
            <property name="javax.persistence.jdbc.password" value=""/>

            <!-- HIBERNATE ESPECÍFICO -->
            <property name="hibernate.dialect" value="org.hibernate.dialect.MySQL8Dialect"/>
            <property name="hibernate.hbm2ddl.auto" value="update"/> <!-- Crea tablas AUTO -->
            <property name="hibernate.show_sql" value="true"/>       <!-- Muestra SQL -->
        </properties>
    </persistence-unit>
</persistence>
*/





// ============================================================================
// 6. USO EN Principal.java - PATRÓN ESTÁNDAR
// ============================================================================
/*
EntityManagerFactory emf = Persistence.createEntityManagerFactory("bdLibreria");
EntityManager em = emf.createEntityManager();
EntityTransaction tx = em.getTransaction();

try {
    tx.begin();  // ← INICIO TRANSACCIÓN ACID
    
    Socio s = new Socio("12345678A", "Juan");
    em.persist(s);  // ← CREATE
    
    Libro l = em.find(Libro.class, 1L);  // ← READ por ID
    
    l.setNumEjemplares(5);
    em.merge(l);  // ← UPDATE
    
    em.remove(l);  // ← DELETE
    
    tx.commit();  // ← CONFIRMA en BD
} catch(Exception e) {
    tx.rollback();  // ← DESHACE todo si error
} finally {
    em.close();
}
emf.close();
*/
