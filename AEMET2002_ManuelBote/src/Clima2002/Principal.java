package Clima2002;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.persistence.*;
import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("bdClima");
		EntityManager em = emf.createEntityManager();
		try {
			// 1) Leer JSON
			List<ClimaMensual> datos = leerJson("json\\clima2002.json");
			// 2) Guardar en BD
			guardarDatos(em, datos);
			// 3) Menú con consultas
			ejecutarConsultas(em);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}

	private static List<ClimaMensual> leerJson(String ruta) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(new File(ruta), new TypeReference<List<ClimaMensual>>() {
		});
	}

	private static void guardarDatos(EntityManager em, List<ClimaMensual> datos) {
		em.getTransaction().begin();
		for (ClimaMensual c : datos) {
			em.persist(c);
		}
		em.getTransaction().commit();
	}

	private static void ejecutarConsultas(EntityManager em) {
		Scanner sc = new Scanner(System.in);
		int opcion;
		do {
			System.out.println("\n=== MENÚ CLIMÁTICO 2020 ===");
			System.out.println("1. Mes más lluvioso");
			System.out.println("2. Mes menos lluvioso");
			System.out.println("3. Mes más caluroso");
			System.out.println("4. Mes más frío");
			System.out.println("5. Mes con mayor temperatura media");
			System.out.println("6. Mes con menor temperatura media");
			System.out.println("0. Salir");
			System.out.print("Opción: ");
			opcion = sc.nextInt();

			switch (opcion) {
			case 1:
				mostrarMes(em, "p_mes", true, "Mes más lluvioso");
				break;
			case 2:
				mostrarMes(em, "p_mes", false, "Mes menos lluvioso");
				break;
			case 3:
				mostrarMes(em, "tm_max", true, "Mes más caluroso");
				break;
			case 4:
				mostrarMes(em, "tm_min", false, "Mes más frío");
				break;
			case 5:
				mostrarMes(em, "tm_mes", true, "Mes con mayor temp. media");
				break;
			case 6:
				mostrarMes(em, "tm_mes", false, "Mes con menor temp. media");
				break;
			case 0:
				System.out.println("Saliendo...");
				break;
			default:
				System.out.println("Opción no válida");
				break;
			}
		} while (opcion != 0);
		sc.close();
	}

	private static void mostrarMes(EntityManager em, String campo, boolean desc, String mensaje) {
		String orden;

		if (desc) {
			orden = "DESC";
		} else {
			orden = "ASC";
		}
		ClimaMensual r = em.createQuery(
				"SELECT c FROM ClimaMensual c WHERE c.fecha <> '2020-13' ORDER BY c." + campo + " " + orden,
				ClimaMensual.class).setMaxResults(1).getSingleResult();
		System.out.println(mensaje + ": " + nombreMes(r.getFecha()));
	}

	private static String nombreMes(String fecha) {

		int mes = Integer.parseInt(fecha.split("-")[1]);
		switch (mes) {
		case 1:
			return "enero";
		case 2:
			return "febrero";
		case 3:
			return "marzo";
		case 4:
			return "abril";
		case 5:
			return "mayo";
		case 6:
			return "junio";
		case 7:
			return "julio";
		case 8:
			return "agosto";
		case 9:
			return "septiembre";
		case 10:
			return "octubre";
		case 11:
			return "noviembre";
		case 12:
			return "diciembre";
		default:
			return "mes desconocido";
		}
	}

}
