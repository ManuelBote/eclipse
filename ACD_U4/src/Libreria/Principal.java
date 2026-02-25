package Libreria;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Principal {

    private static final String PU = "bdLibreria"; // nombre en persistence.xml
    private static EntityManagerFactory emf;
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        emf = Persistence.createEntityManagerFactory("bdLibreria");

        int opcion;
        do {
            mostrarMenu();
            opcion = leerInt("Elige opción: ");

            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();

            try {
                tx.begin();
                switch (opcion) {
                    case 1 -> crearSocio(em);
                    case 2 -> crearLibro(em);
                    case 3 -> modificarEjemplares(em);
                    case 4 -> crearPrestamo(em);
                    case 5 -> devolverPrestamo(em);
                    case 6 -> mostrarSocioYPrestamos(em);
                    case 7 -> prestamosPendientesPorSocio(em);
                    case 8 -> totalPrestamosPendientes(em);
                    case 9 -> totalLibrosYEjemplares(em);
                    case 10 -> borrarLibro(em);
                    case 11 -> borrarSocio(em);
                    case 0 -> System.out.println("Saliendo...");
                    default -> System.out.println("Opción no válida.");
                }
                if (tx.isActive()) tx.commit();
            } catch (Exception e) {
                if (tx.isActive()) tx.rollback();
                e.printStackTrace();
            } finally {
                em.close();
            }
        } while (opcion != 0);

        emf.close();
        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n===== BIBLIOTECA =====");
        System.out.println("1. Crear socio");
        System.out.println("2. Crear libro");
        System.out.println("3. Modificar nº de ejemplares de un libro");
        System.out.println("4. Crear préstamo");
        System.out.println("5. Devolver préstamo");
        System.out.println("6. Mostrar datos de un socio (con préstamos)");
        System.out.println("7. Nº de préstamos pendientes por socio");
        System.out.println("8. Nº de préstamos pendientes de devolución");
        System.out.println("9. Nº de libros y ejemplares totales");
        System.out.println("10. Borrar libro");
        System.out.println("11. Borrar socio");
        System.out.println("0. Salir");
    }

    // --------- UTILIDADES BÁSICAS ---------

    private static int leerInt(String msg) {
        System.out.print(msg);
        while (!sc.hasNextInt()) {
            sc.nextLine();
            System.out.print("Introduce un número: ");
        }
        int n = sc.nextInt();
        sc.nextLine(); // limpiar salto
        return n;
    }

    private static String leerLinea(String msg) {
        System.out.print(msg);
        return sc.nextLine();
    }

    // --------- OPCIÓN 1: CREAR SOCIO ---------

    private static void crearSocio(EntityManager em) {
        String nif = leerLinea("NIF: ");

        List<Socio> existentes = em
                .createNamedQuery("Socio.findByNif", Socio.class)
                .setParameter("nif", nif)
                .getResultList();

        if (!existentes.isEmpty()) {
            System.out.println("Ya existe un socio con ese NIF.");
            return;
        }

        String nombre = leerLinea("Nombre: ");

        Socio s = new Socio();
        s.setNif(nif);
        s.setNombre(nombre);
        s.setFechaSancion(null);

        em.persist(s);
        System.out.println("Socio creado con id: " + s.getId());
    }

    // --------- OPCIÓN 2: CREAR LIBRO ---------

    private static void crearLibro(EntityManager em) {
        String isbn = leerLinea("ISBN: ");

        List<Libro> existentes = em
                .createNamedQuery("Libro.findByIsbn", Libro.class)
                .setParameter("isbn", isbn)
                .getResultList();

        if (!existentes.isEmpty()) {
            System.out.println("Ya existe un libro con ese ISBN.");
            return;
        }

        String titulo = leerLinea("Título: ");
        int ejemplares = leerInt("Número de ejemplares: ");

        Libro l = new Libro();
        l.setIsbn(isbn);
        l.setTitulo(titulo);
        l.setNumEjemplares(ejemplares);

        em.persist(l);
        System.out.println("Libro creado con id: " + l.getId());
    }

    // --------- OPCIÓN 3: MODIFICAR EJEMPLARES ---------

    private static void modificarEjemplares(EntityManager em) {
        String isbn = leerLinea("ISBN del libro: ");

        List<Libro> lista = em
                .createNamedQuery("Libro.findByIsbn", Libro.class)
                .setParameter("isbn", isbn)
                .getResultList();

        if (lista.isEmpty()) {
            System.out.println("Libro no encontrado.");
            return;
        }

        Libro l = lista.get(0);
        int nuevos = leerInt("Nuevo número de ejemplares: ");
        l.setNumEjemplares(nuevos);
        em.merge(l);

        System.out.println("Ejemplares actualizados.");
    }

    // --------- AUXILIARES DE REGLAS ---------

    private static boolean estaSancionado(Socio s) {
        Date f = s.getFechaSancion();
        if (f == null) return false;
        LocalDate fin = f.toLocalDate();
        return LocalDate.now().isBefore(fin);
    }

    private static long prestamosPendientesSocio(EntityManager em, Socio s) {
        return em.createNamedQuery("Prestamo.pendientesSocio", Long.class)
                .setParameter("socioId", s.getId())
                .getSingleResult();
    }

    private static long prestamosPendientesLibro(EntityManager em, Libro l) {
        return em.createNamedQuery("Prestamo.pendientesLibro", Long.class)
                .setParameter("libroId", l.getId())
                .getSingleResult();
    }

    // --------- OPCIÓN 4: CREAR PRÉSTAMO ---------

    private static void crearPrestamo(EntityManager em) {
        String nif = leerLinea("NIF socio: ");

        List<Socio> socios = em
                .createNamedQuery("Socio.findByNif", Socio.class)
                .setParameter("nif", nif)
                .getResultList();

        if (socios.isEmpty()) {
            System.out.println("Socio no encontrado.");
            return;
        }
        Socio s = socios.get(0);

        if (estaSancionado(s)) {
            System.out.println("Socio sancionado hasta " + s.getFechaSancion());
            return;
        }

        long pendSocio = prestamosPendientesSocio(em, s);
        if (pendSocio >= 2) {
            System.out.println("El socio ya tiene 2 préstamos pendientes.");
            return;
        }

        String isbn = leerLinea("ISBN libro: ");

        List<Libro> libros = em
                .createNamedQuery("Libro.findByIsbn", Libro.class)
                .setParameter("isbn", isbn)
                .getResultList();

        if (libros.isEmpty()) {
            System.out.println("Libro no encontrado.");
            return;
        }
        Libro l = libros.get(0);

        long pendLibro = prestamosPendientesLibro(em, l);
        if (pendLibro >= l.getNumEjemplares()) {
            System.out.println("No hay ejemplares disponibles de ese libro.");
            return;
        }

        Prestamo p = new Prestamo(s, l); // tu constructor
        em.persist(p);
        System.out.println("Préstamo creado con id: " + p.getId());
    }

    // --------- OPCIÓN 5: DEVOLVER PRÉSTAMO ---------

    private static void devolverPrestamo(EntityManager em) {
        long id = leerInt("ID del préstamo a devolver: ");

        Prestamo p = em.find(Prestamo.class, id);
        if (p == null) {
            System.out.println("Préstamo no encontrado.");
            return;
        }
        if (p.getFechaDevolucion() != null) {
            System.out.println("Ese préstamo ya está devuelto.");
            return;
        }

        Date hoy = Date.valueOf(LocalDate.now());
        p.setFechaDevolucion(hoy);

        // Calcular retraso (plazo 14 días)
        LocalDate fPrestamo = p.getFechaPrestamo().toLocalDate();
        LocalDate plazo = fPrestamo.plusDays(14);

        if (LocalDate.now().isAfter(plazo)) {
            long diasRetraso = ChronoUnit.DAYS.between(plazo, LocalDate.now());
            System.out.println("Devolución con retraso de " + diasRetraso + " días.");

            // sanción 1 semana
            LocalDate finSancion = LocalDate.now().plusDays(7);
            Socio s = p.getSocio();
            s.setFechaSancion(Date.valueOf(finSancion));
            em.merge(s);
            System.out.println("Socio sancionado hasta " + finSancion);
        }

        em.merge(p);
        System.out.println("Préstamo devuelto.");
    }

    // --------- OPCIÓN 6: MOSTRAR SOCIO Y SUS PRÉSTAMOS ---------

    private static void mostrarSocioYPrestamos(EntityManager em) {
        String nif = leerLinea("NIF socio: ");

        List<Socio> socios = em
                .createNamedQuery("Socio.findByNif", Socio.class)
                .setParameter("nif", nif)
                .getResultList();

        if (socios.isEmpty()) {
            System.out.println("Socio no encontrado.");
            return;
        }
        Socio s = socios.get(0);

        System.out.println("Socio: " + s.getNombre() + " (id " + s.getId() + ")");
        System.out.println("NIF: " + s.getNif());
        System.out.println("Fecha sanción: " + s.getFechaSancion());

        // Cargar préstamos del socio
        List<Prestamo> prestamos = em.createQuery(
                        "SELECT p FROM Prestamo p WHERE p.socio.id = :idSocio", Prestamo.class)
                .setParameter("idSocio", s.getId())
                .getResultList();

        if (prestamos.isEmpty()) {
            System.out.println("No tiene préstamos.");
        } else {
            System.out.println("Préstamos:");
            for (Prestamo p : prestamos) {
                System.out.println("  ID " + p.getId() +
                        " | Libro: " + p.getLibro().getTitulo() +
                        " | F. préstamo: " + p.getFechaPrestamo() +
                        " | F. devolución: " + p.getFechaDevolucion());
            }
        }
    }

    // --------- OPCIÓN 7: Nº PRÉSTAMOS PENDIENTES POR SOCIO ---------

    private static void prestamosPendientesPorSocio(EntityManager em) {
        List<Object[]> res = em
                .createNamedQuery("Socio.préstamosPendientesPorSocio", Object[].class)
                .getResultList();

        if (res.isEmpty()) {
            System.out.println("No hay préstamos pendientes.");
            return;
        }

        System.out.println("NIF  | Préstamos pendientes");
        for (Object[] fila : res) {
            String nif = (String) fila[0];
            Long num = (Long) fila[1];
            System.out.println(nif + " -> " + num);
        }
    }

    // --------- OPCIÓN 8: TOTAL PRÉSTAMOS PENDIENTES ---------

    private static void totalPrestamosPendientes(EntityManager em) {
        Long total = em
                .createNamedQuery("Prestamo.pendientesTotal", Long.class)
                .getSingleResult();
        System.out.println("Préstamos pendientes de devolución: " + total);
    }

    // --------- OPCIÓN 9: TOTAL LIBROS Y EJEMPLARES ---------

    private static void totalLibrosYEjemplares(EntityManager em) {
        Object[] res = (Object[]) em
                .createNamedQuery("Libro.totalLibrosEjemplares", Object[].class)
                .getSingleResult();

        Long numLibros = (Long) res[0];
        Long sumaEjemplares = (Long) res[1];

        System.out.println("Número de libros distintos: " + numLibros);
        System.out.println("Número total de ejemplares: " + (sumaEjemplares != null ? sumaEjemplares : 0));
    }

    // --------- OPCIÓN 10: BORRAR LIBRO ---------

    private static void borrarLibro(EntityManager em) {
        String isbn = leerLinea("ISBN del libro a borrar: ");

        List<Libro> libros = em
                .createNamedQuery("Libro.findByIsbn", Libro.class)
                .setParameter("isbn", isbn)
                .getResultList();

        if (libros.isEmpty()) {
            System.out.println("Libro no encontrado.");
            return;
        }
        Libro l = libros.get(0);

        long pendientes = prestamosPendientesLibro(em, l);
        if (pendientes > 0) {
            System.out.println("No se puede borrar: tiene préstamos pendientes.");
            return;
        }

        em.remove(l);
        System.out.println("Libro borrado.");
    }

    // --------- OPCIÓN 11: BORRAR SOCIO ---------

    private static void borrarSocio(EntityManager em) {
        String nif = leerLinea("NIF del socio a borrar: ");

        List<Socio> socios = em
                .createNamedQuery("Socio.findByNif", Socio.class)
                .setParameter("nif", nif)
                .getResultList();

        if (socios.isEmpty()) {
            System.out.println("Socio no encontrado.");
            return;
        }
        Socio s = socios.get(0);

        long pendientes = prestamosPendientesSocio(em, s);
        if (pendientes > 0) {
            System.out.println("No se puede borrar: tiene préstamos pendientes.");
            return;
        }

        em.remove(s);
        System.out.println("Socio borrado.");
    }
}
