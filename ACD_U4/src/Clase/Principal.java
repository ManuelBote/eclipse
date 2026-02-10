package Clase;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;



public class Principal {

	public static Scanner tc = new Scanner(System.in);
	
	public static EntityManager entity() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("bdAlumnos");
		return emf.createEntityManager();
	}

	public static void main(String[] args) {
		//Cargar tablas (tablas no creadas)
		EntityManager em = entity();
		em.close();
		
		int cond = 0;
		
		do {
			System.out.println("\n[1] Mostrar profesores");
			System.out.println("[2] Agregar profesor y mostrar profesores");
			System.out.println("[3] Mostrar nombres de alumnos de '2DAM'");
			System.out.println("[4] Mostrar alumnos ordenador por curso");
			System.out.println("[5] Borrar profesor");		
			System.out.print("Selecciona una opcion: ");
			cond = tc.nextInt(); tc.nextLine();
			
			switch(cond) {
			case 0 -> System.out.println("Saliendo...");
			case 1 -> mostrarProfesores();
			case 2 -> agregarProfesor();
			case 3 -> mostrarAlumnos2dam();
			case 4 -> mostarAlumnosOrden();
			case 5 -> eliminarProfesor();
			default -> System.out.println("Opcion no valida");
			
			}
			
		}while(cond != 0);

	}
	
	
	public static void mostrarProfesores() {
		EntityManager em = entity();
		
		try {
			List<Profesor> ps = em.createQuery("SELECT p FROM profesor p", Profesor.class).getResultList();
			for(Profesor p : ps) {
				System.out.println(p.toString());
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
	
	public static void agregarProfesor() {
		EntityManager em = entity();
		
		try {
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			
			System.out.println("Introduce el nombre del profesor: ");
			String nombre = tc.nextLine();
			
			Profesor p = new Profesor(nombre);
			em.persist(p);
			tx.commit();
			
			mostrarProfesores();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
	
	public static void mostrarAlumnos2dam() {
		EntityManager em = entity();
		
		try {
			List<Alumno> as = em.createQuery("SELECT a FROM alumno a WHERE curso = '2DAM'", Alumno.class).getResultList();
			for(Alumno a : as) {
				System.out.println(a.getNombre());
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	public static void mostarAlumnosOrden() {
		EntityManager em = entity();
		
		try {
			List<Alumno> as = em.createQuery("SELECT a FROM alumno a ORDER BY curso", Alumno.class).getResultList();
			for(Alumno a : as) {
				System.out.println(a.getNombre() + " - " + a.getCurso());
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	public static void eliminarProfesor() {
		EntityManager em = entity();
		
		try {
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			
			System.out.println("Introduce el id del profesor: ");
			int id = tc.nextInt(); tc.nextLine();
			
			Profesor p = em.find(Profesor.class, id);
			em.remove(p);
			tx.commit();
			
			mostrarProfesores();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
	
}
