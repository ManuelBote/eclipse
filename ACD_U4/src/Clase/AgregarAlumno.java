package Clase;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class AgregarAlumno {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntityManager em = Principal.entity();

		try {
			EntityTransaction tx = em.getTransaction();
			tx.begin();

			Alumno a1 = new Alumno("Pepito", "2DAW", 123456789);
			Alumno a2 = new Alumno("Julian", "1DAM", 234567890);
			Alumno a3 = new Alumno("Miguel", "1DAW", 345678901);
			em.persist(a1);
			em.persist(a2);
			em.persist(a3);
			tx.commit();

			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

}
