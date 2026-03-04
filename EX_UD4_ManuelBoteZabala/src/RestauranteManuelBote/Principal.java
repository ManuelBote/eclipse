package RestauranteManuelBote;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Principal {
	
	public static Scanner tc = new Scanner(System.in);
	
	public static EntityManager entity() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UPRestaurante");
		return emf.createEntityManager();
	}
	
	public static void main(String[] args) {
		//Cargar tablas (tablas no creadas)
		EntityManager em = entity();
		em.close();
		
		int cond = 0;
		
		do {
			System.out.println("\n====== MENU RESTAURANTE ======");
			System.out.println("[1] Crear 2 camareros, 2 mesas y 2 reservas");
			System.out.println("[2] Listar mesas de un camarero");
			System.out.println("[3] Actualizar telefono de camarero");
			System.out.println("[4] Borrar camarero");
			System.out.println("[5] Mostrar nombre del cliente, fecha y hora de reserva de una mesa");
			System.out.println("[6] Mostrar total de mesas que atiende un camarero");
			System.out.println("[7] Salir");
			System.out.print("Selecciona una opcion: ");
			cond = tc.nextInt(); tc.nextLine();
			
			switch(cond) {
			case 1 -> crearDatos();
			case 2 -> listarMesas();
			case 3 -> actualizarTelefono();
			case 4 -> borrarCamarero();
			case 5 -> mostrarDatosReserva();
			case 6 -> totalMesasCamarero();
			case 7 -> System.out.println("Saliendo...");
			default -> System.out.println("Opcion no valida");
			
			}
			
		}while(cond != 0);

	}
	
	public static void crearDatos(){
		EntityManager em = entity();
		
		try {
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			
			Camarero c1 = new Camarero(1, "12345678A", "paco", 123456789);
			Camarero c2 = new Camarero(2, "12345678B", "Pedro", 234567890);
			em.persist(c1);
			em.persist(c2);
			
			Mesa m1 = new Mesa(1, 10, 4, c1);
			Mesa m2 = new Mesa(2, 12, 4, c1);
			em.persist(m1);
			em.persist(m2);
			
			Reserva r1 = new Reserva("Juan", "123", "23", m1);
			Reserva r2 = new Reserva("Miguel", "234", "13", m2);
			em.persist(r1);
			em.persist(r2);
					
			tx.commit();
			
			System.out.println("Datos añadidos");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
	
	
	
	public static void listarMesas(){
		System.out.print("Introduce el id del camarero: ");
		int id = tc.nextInt(); tc.nextLine();
		
		EntityManager em = entity();
		
		try {
			List<Camarero> cs = em.createQuery("SELECT c FROM camarero c WHERE idcamarero = '" + id + "'", Camarero.class).getResultList();
			for(Camarero c : cs) {
				for(Mesa m : c.getMesas()) {
					System.out.println(m.toString());
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
	
	
	
	public static void actualizarTelefono(){
		System.out.print("Introduce el id del camarero: ");
		int id = tc.nextInt(); tc.nextLine();
		
		EntityManager em = entity();
		
		try {
			Camarero c = em.find(Camarero.class, id);
			if(c != null) {
				System.out.print("Introduce el nuevo telefono del camarero: ");
				int tlf = tc.nextInt(); tc.nextLine();
				
				em.getTransaction().begin();
				c.setTelefonoCamarero(tlf);
				em.merge(c);
				em.getTransaction().commit();
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
	
	
	
	public static void borrarCamarero(){
		System.out.print("Introduce el id del camarero: ");
		int id = tc.nextInt(); tc.nextLine();
		
		EntityManager em = entity();
		
		try {
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			
			Camarero c = em.find(Camarero.class, id);
			em.remove(c);
			tx.commit();

			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		
	}
	
	
	
	public static void mostrarDatosReserva(){
		EntityManager em = entity();
		System.out.print("Introduce el id de la mesa: ");
		int id = tc.nextInt(); tc.nextLine();
		
		try {
			
			String consulta = "SELECT m FROM mesa m WHERE idmesa = " + id;
			Query sql = em.createQuery(consulta);
			
			List<Mesa> mesa = sql.getResultList();
			for(Mesa m : mesa) {
				Reserva r = m .getReserva();
				System.out.println("Nombre cliente: " + r.getNombreCliente());
				System.out.println("Fecha reserva: " + r.getFechaReserva());
				System.out.println("Hora reserva: " + r.getHoraReserva());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		
	}
	
	
	
	public static void totalMesasCamarero(){
		System.out.print("Introduce el id del camarero: ");
		int id = tc.nextInt(); tc.nextLine();
		
		EntityManager em = entity();
		
		try {
			Query sql = em.createNamedQuery("totalMesas");
			sql.setParameter("idcamarero", id);
			int total = (int) sql.getSingleResult();
			
			System.out.println("Total del mesas del camarero: " + total);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

}
