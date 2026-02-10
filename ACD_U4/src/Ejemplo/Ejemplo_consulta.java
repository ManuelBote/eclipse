package Ejemplo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Ejemplo_consulta {

	public static void main(String[] args) {
		// Crear EntityManagerFactory con la unidad de persistencia 'UnidadPersonas'
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("bdPersonas");
		EntityManager em = emf.createEntityManager();
		try {
			// Consultar todas las personas
			List<Persona> personas = em.createQuery("SELECT p FROM persona p", Persona.class).getResultList();
			// Mostrar las personas consultadas
			for (Persona persona : personas) {
				System.out.println(persona.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}
}
