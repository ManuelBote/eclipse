package ProductoCRUD;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

//Manuel Bote Zabala

public class Principal {

	public static Scanner tc = new Scanner(System.in);

	public static EntityManager entity() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("bdproductos");
		return emf.createEntityManager();
	}

	public static void main(String[] args) {
		int opcion;

		do {
			mostrarMenu();
			opcion = tc.nextInt();
			tc.nextLine();

			switch (opcion) {
			case 0 -> System.out.println("Saliendo...");
			case 1 -> insertarProducto();
			case 2 -> listarProductos();
			case 3 -> butcarProducto();
			case 4 -> actualizarProducto();
			case 5 -> eliminarProducto();
			default -> System.out.println("Opción no válida.");
			}
		} while (opcion != 6);

	}

	// Menu
	private static void mostrarMenu() {
		System.out.println("\n=== CRUD PRODUCTOS ===");
		System.out.println("[1] Insertar producto");
		System.out.println("[2] Listar productos");
		System.out.println("[3] Butcar producto");
		System.out.println("[4] Actualizar producto");
		System.out.println("[5] Eliminar producto");
		System.out.println("[0] Salir");
		System.out.print("Opción: ");
	}

	//Insertar
	private static void insertarProducto() {
		EntityManager em = entity();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();

			System.out.print("Nombre: ");
			String nombre = tc.nextLine();
			System.out.print("Precio: ");
			Double precio = tc.nextDouble();

			Producto p = new Producto(nombre, precio);
			em.persist(p);

			tx.commit();
			System.out.println("Producto guardado con ID: " + p.getId());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	//Listar todo
	private static void listarProductos() {
		EntityManager em = entity();

		try {
			List<Producto> lista = em.createQuery("SELECT p FROM Producto p", Producto.class).getResultList();

			System.out.println("\n--- LISTA DE PRODUCTOS ---");
			if (lista.isEmpty()) {
				System.out.println("No hay productos.");
			} else {
				for (Producto p : lista) {
					System.out
							.println("ID: " + p.getId() + ", Nombre: " + p.getNombre() + ", Precio: " + p.getPrecio());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	//Buscar producto
	private static void butcarProducto() {
		EntityManager em = entity();

		try {
			System.out.print("ID del producto: ");
			Integer id = tc.nextInt();

			Producto p = em.find(Producto.class, id);
			if (p != null) {
				System.out.println("Nombre: " + p.getNombre());
				System.out.println("Precio: " + p.getPrecio());
			} else {
				System.out.println("Producto no encontrado.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	//Actualizar producto
	private static void actualizarProducto() {
		EntityManager em = entity();
		EntityTransaction tx = em.getTransaction();

		try {
			System.out.print("ID del producto a actualizar: ");
			Integer id = tc.nextInt();

			Producto p = em.find(Producto.class, id);
			if (p == null) {
				System.out.println("Producto no encontrado.");
				return;
			}

			tx.begin();

			System.out.print("Nuevo nombre (" + p.getNombre() + "): ");
			String nombre = tc.next();
			p.setNombre(nombre);

			System.out.print("Nuevo precio (" + p.getPrecio() + "): ");
			Double precio = tc.nextDouble();
			p.setPrecio(precio);

			em.merge(p);
			tx.commit();
			System.out.println("Producto actualizado.");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	//Eliminar Producto
	private static void eliminarProducto() {
		EntityManager em = entity();
		EntityTransaction tx = em.getTransaction();

		try {
			System.out.print("ID del producto a eliminar: ");
			Integer id = tc.nextInt();

			Producto p = em.find(Producto.class, id);
			if (p == null) {
				System.out.println("Producto no encontrado.");
				return;
			}

			tx.begin();
			em.remove(p);
			tx.commit();
			System.out.println("Producto eliminado.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

}
