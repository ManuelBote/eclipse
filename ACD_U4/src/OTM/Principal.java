package OTM;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


//Manuel Bote Zabala
public class Principal {

	public static Scanner tc = new Scanner(System.in);

	public static EntityManager entity() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("bdEscuela");
		return emf.createEntityManager();
	}

	public static void main(String[] args) {

		int opcion;

		do {
			mostrarMenu();
			opcion = tc.nextInt();
			tc.nextLine();

			switch (opcion) {
			case 1 -> crearDatosEjemplo();
			case 2 -> listarTodo();
			case 3 -> System.out.println("Saliendo...");
			default -> System.out.println("Opción no valida.");
			}
		} while (opcion != 3);

	}

	private static void mostrarMenu() {
		System.out.println("\n===== Ejemplo OneToMany =====");
		System.out.println("[1] Crear datos de ejemplo");
		System.out.println("[2] Listar Profesores + Alumnos");
		System.out.println("[0] Salir");
		System.out.print("Selecciona una opcion: ");
	}

	// 1. Crear datos de ejemplo
	private static void crearDatosEjemplo() {
		EntityManager em = entity();
		try {
			em.getTransaction().begin();
			
			// Crear profesores
			Profesor p1 = new Profesor("Pepe");
			em.persist(p1);
			Profesor p2 = new Profesor("Ana");
			em.persist(p2);
			
			// Crear alumnos y asignar
			Alumno a1 = new Alumno("Juan");
			p1.addAlumno(a1);
			Alumno a2 = new Alumno("Maria");
			p1.addAlumno(a2);
			Alumno a3 = new Alumno("Pedro");
			p2.addAlumno(a3);
			
			em.getTransaction().commit();
			System.out.println("Datos creados");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	// 2. Listar TODO
	private static void listarTodo() {
        EntityManager em = entity();

		try {
			List<Alumno> as = em.createQuery("SELECT a FROM Alumno a", Alumno.class).getResultList();
			for(Alumno a : as) {
				System.out.println(a.toString());
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
        		
        em.close();
    }

}
