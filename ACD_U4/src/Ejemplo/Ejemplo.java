package Ejemplo;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Ejemplo {

	public static void main(String[] args) {

		// Creamos la factoria
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("bdPersonas");
		System.out.println("Factoria creada");
		EntityManager em = emf.createEntityManager();
		System.out.print("Entidad creada");
		Scanner scanner = new Scanner(System.in);
		try {
			// Iniciar la transacción
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			Persona p = new Persona(2, "Pepe", 34);
			// Persistir la persona
			em.persist(p);
			tx.commit();
			System.out.println("Persona guardada con éxito.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Ocurrió un error. Revirtiendo cambios.");
			em.getTransaction().rollback();
		} finally {
			em.close();
			emf.close();
		}
		scanner.close();

	}

}
