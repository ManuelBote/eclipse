package OTO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import OTM.Alumno;

//Manuel Bote Zabala
public class Principal {

	 private static final String PU_NAME = "bdDireccion";

	    public static void main(String[] args) {
	        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU_NAME);
	        EntityManager em = emf.createEntityManager();
	        
	        em.getTransaction().begin();

	        // Crear Persona y Dirección
	        Persona p = new Persona("23452789B", "Juan", 23, 123456789);
	        Direccion d = new Direccion(p, "Calle calle", "Navalmoral", 10300);
	        p.setDireccion(d);
	        
	        em.persist(p);  
	        
	        em.getTransaction().commit();

	        // Listar
	        List<Persona> ps = em.createQuery("SELECT p FROM Persona p JOIN FETCH p.direccion", Persona.class).getResultList();
			for(Persona pf : ps) {
				System.out.println(pf.toString());
			}
			
	        em.close();
	        emf.close();
	    }
	
}
