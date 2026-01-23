package Ejercicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

//@Author Manuel Bote Zabala
public class Ej13 {
	
	static Scanner ts = new Scanner(System.in);

	//Metodo para hacer la conexion a la BD
	public static Connection conectar() {
		Connection conexion = null;
		
		try {
			Class.forName("org.postgresql.Driver");
			conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mascota", "postgres", "toor");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conexion;
	}
	
	
	//Menu opcion Alta
	public static void menuAlta() {
		int menu = 0;
		
		System.out.println("\n======= Menu Alta =======");
		System.out.println("[1] Alta Veterinario");
		System.out.println("[2] Alta Mascota");
		System.out.println("[3] Alta Propietario");
		System.out.println("[0] Volver");
		System.out.print("Seleccione una opcion: ");
		menu = ts.nextInt(); ts.nextLine();
		
		switch(menu) {
			case 1 -> altaMascota();
			case 2 -> altaMascota();
			case 3 -> altaPropietario();
			case 0 -> System.out.println("Volviendo../n");
			default -> System.out.println("Opcion no valida\n");
		}
	}
	
	//Menu opcion Modificacion
	public static void menuMod() {
		int menu = 0;
		
		System.out.println("\n======= Menu Modificacion =======");
		System.out.println("[1] Modificar Propietario");
		System.out.println("[2] Modificar Mascota");
		System.out.println("[0] Volver");
		System.out.print("Seleccione una opcion: ");
		menu = ts.nextInt(); ts.nextLine();
		
		switch(menu) {
			case 1 -> modificarPropietario();
			case 2 -> modificarMascota();
			case 0 -> System.out.println("Volviendo../n");
			default -> System.out.println("Opcion no valida\n");
		}
	}
	
	//Menu opcion Baja
	public static void menuBaja() {
		int menu = 0;
		
		System.out.println("\n======= Menu Baja =======");
		System.out.println("[1] Baja Mascota");
		System.out.println("[2] Baja Propietario");
		System.out.println("[0] Volver");
		System.out.print("Seleccione una opcion: ");
		menu = ts.nextInt(); ts.nextLine();
		
		switch(menu) {
			case 1 -> bajaMascota();
			case 2 -> bajaPropietario();
			case 0 -> System.out.println("Volviendo../n");
			default -> System.out.println("Opcion no valida\n");
		}
	}

	public static void main(String[] args) {
		
		int cont = 0;
		boolean condW = true;
		
		do {
			
			System.out.println("\n======= Menu Veterinaria =======");
			System.out.println("[1] Alta");
			System.out.println("[2] Modificar");
			System.out.println("[3] Baja");
			System.out.println("[4] Aplicar Vacuna");
			System.out.println("[0] Salir");
			System.out.print("Seleccione una opcion: ");
			cont = ts.nextInt(); ts.nextLine();
			
			switch(cont) {
		
			case 1 -> menuAlta();	
			case 2 -> menuMod();	
			case 3 -> menuBaja();	
			case 4 -> aplicarVacuna();
			case 0 -> condW = false;
			
			}
			
		}while(condW);
		System.out.println("Saliendo..");
		
	}
	
	//========================	
	//===== METODOS ALTA =====
	//========================
	
	//Alta Veterinario
	public static void altaVeterinario() {
		Connection conexion = conectar();
		
		try {
			System.out.print("\nDNI del veterinario: ");
			String dni = ts.nextLine();
			System.out.print("Nombre del veterinario: ");
			String nombre = ts.nextLine();
			System.out.print("Nº de colegioalo del veterinario: ");
			int numCol = ts.nextInt(); ts.nextLine();
			
			String sql = "INSERT INTO public.veterinario(dni, nombre, num_colegiado) VALUES (?, ?, ?)";
			
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setString(1, dni);	
			stmt.setString(2, nombre);
			stmt.setInt(3, numCol);
			
			// Ejecutar la consulta
			int resultado = stmt.executeUpdate();
			if (resultado > 0) {
			System.out.println("¡Datos insertados con éxito!");
			} else {
			System.out.println("No se insertaron datos.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Alta Masctota
	public static void altaMascota() {
		Connection conexion = conectar();
		
		try {		
			System.out.print("\nNombre de la mascota: ");
			String nombre = ts.nextLine();
			System.out.print("Especie de la mascota: ");
			String especie = ts.nextLine();
			System.out.print("Raza de la mascota: ");
			String raza = ts.nextLine();

			System.out.print("Cantidad de vacunas de la mascota: ");
			int cant = ts.nextInt(); ts.nextLine();
			
			//Formacion del array
			StringBuilder arrayVacunas = new StringBuilder("ARRAY[");
			
			for (int i = 0; i < cant; i++) {
				System.out.println("Vacuna " + (i+1));
				
				System.out.print("Nombre de la vacuna: ");
				String vacuna = ts.nextLine();
				System.out.print("Numero de colegiado del veterinario: ");
				int veterinario = ts.nextInt(); ts.nextLine();
				System.out.print("Fecha de vacunacion(YYYY-MM-DD): ");
				LocalDate fecha = LocalDate.parse(ts.nextLine());
				
				if(i > 0) {
					arrayVacunas.append(", ");
				}
				arrayVacunas.append("('" + vacuna + "', '" + veterinario + "', '" + fecha + "')");	
			}
			arrayVacunas.append("]::cartilla_vacunacion[]");
			
			
			String sql = "INSERT INTO public.mascota (nombre, especie, raza, vacuna)"
					+ "VALUES (?, ?, ?, "+ arrayVacunas.toString() +")";
			
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setString(1, nombre);	
			stmt.setString(2, especie);
			stmt.setString(3, raza);

			
			// Ejecutar la consulta
			int resultado = stmt.executeUpdate();
			if (resultado > 0) {
			System.out.println("¡Datos insertados con éxito!");
			} else {
			System.out.println("No se insertaron datos.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//AltaPropietario
	public static void altaPropietario() {
		Connection conexion = conectar();
		
		try {
			System.out.print("\nDNI del propietario: ");
			String dni = ts.nextLine();
			System.out.print("Nombre del propietario: ");
			String nombre = ts.nextLine();
			System.out.print("Calle del propietario: ");
			String calle = ts.nextLine();
			System.out.print("Nuemero del domicilio del propietario: ");
			int num = ts.nextInt(); ts.nextLine();
			System.out.print("Ciudad del propietario: ");
			String ciudad = ts.nextLine();
			System.out.print("Codigo postal: ");
			int cp = ts.nextInt(); ts.nextLine();
			
			String sql = "INSERT INTO public.propietario(dni, nombre, direccion) VALUES (?, ?, (?, ?, ?, ?))";
			
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setString(1, dni);	
			stmt.setString(2, nombre);
			stmt.setString(3, calle);
			stmt.setInt(4, num);
			stmt.setString(5, ciudad);
			stmt.setInt(6, cp);
			
			// Ejecutar la consulta
			int resultado = stmt.executeUpdate();
			if (resultado > 0) {
			System.out.println("¡Datos insertados con éxito!");
			} else {
			System.out.println("No se insertaron datos.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//=============================	
	//===== METODOS MODIFICAR =====
	//=============================
	
	//Modificar Propietario
	public static void modificarPropietario() {
		Connection conexion = conectar();
		
		try {
			
			System.out.print("\nDNI del propietario a modificar: ");
			String dni = ts.nextLine();
			
			String sql = "SELECT nombre, (direccion).calle, (direccion).numero, (direccion).poblacion, (direccion).cp FROM public.propietario WHERE dni=?";
			PreparedStatement select = conexion.prepareStatement(sql);
			select.setString(1, dni);
			
			ResultSet rs = select.executeQuery();
			if(rs.next()) {
				String sNombre = rs.getString(1);
				String sCalle = rs.getString(2);
				int sNum = rs.getInt(3);
				String sCiudad = rs.getString(4);
				int sCp = rs.getInt(5);
				
				
				System.out.println("Introduce los campos que desee cambiar: ");
				
				 System.out.print("Nombre (" + sNombre + "): ");
		            String nombre = ts.nextLine();
		            nombre = (nombre.isEmpty()) ? sNombre : nombre;

		            System.out.print("Calle (" + sCalle + "): ");
		            String calle = ts.nextLine();
		            calle = (calle.isEmpty()) ? sCalle : calle;

		            System.out.print("Número (" + sNum + "): ");
		            String snum = ts.nextLine();
		            Integer num = (snum.isEmpty()) ? sNum : Integer.parseInt(snum);

		            System.out.print("Ciudad (" + sCiudad + "): ");
		            String ciudad = ts.nextLine();
		            ciudad = (ciudad.isEmpty()) ? sCiudad : ciudad;

		            System.out.print("CP (" + sCp + "): ");
		            String scp = ts.nextLine();
		            Integer cp = (scp.isEmpty()) ? sCp : Integer.parseInt(scp);
				
				sql = "UPDATE public.propietario SET nombre=?, direccion=(?,?,?,?) WHERE dni=?";
				
				PreparedStatement stmt = conexion.prepareStatement(sql);
				stmt.setString(1, nombre);
				stmt.setString(2, calle);
				stmt.setInt(3, num);
				stmt.setString(4, ciudad);
				stmt.setInt(5, cp);
				stmt.setString(6, dni);	
				
				// Ejecutar la consulta
				int resultado = stmt.executeUpdate();
				if (resultado > 0) {
					System.out.println("Propietario Modificado");
				} else {
					System.out.println("No se ha modificado los datos.");
				}	
				
			} else {
				System.out.println("No existe propietario con ese DNI");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//Modificar Mascota
		public static void modificarMascota() {
			Connection conexion = conectar();
			
			try {
				
				System.out.print("\nID de la mascota a modificar: ");
				int id = ts.nextInt(); ts.nextLine();
				
				String sql = "SELECT nombre, especie, raza, dni_propietario FROM public.mascota WHERE id=?";
				PreparedStatement select = conexion.prepareStatement(sql);
				select.setInt(1, id);
				
				ResultSet rs = select.executeQuery();
				if(rs.next()) {
					String sNombre = rs.getString(1);
					String sEspecie = rs.getString(2);
					String sRaza = rs.getString(3);
					String sDniP = rs.getString(4);
					
					
					System.out.println("Introduce los campos que desee cambiar: ");
					
					 System.out.print("Nombre (" + sNombre + "): ");
			            String nombre = ts.nextLine();
			            nombre = (nombre.isEmpty()) ? sNombre : nombre;

			            System.out.print("Especie (" + sEspecie + "): ");
			            String especie = ts.nextLine();
			            especie = (especie.isEmpty()) ? sEspecie : especie;

			            System.out.print("Raza (" + sRaza + "): ");
			            String raza = ts.nextLine();
			            raza = (raza.isEmpty()) ? especie : raza;

			            System.out.print("DNI Propietario (" + sDniP + "): ");
			            String dniP = ts.nextLine();
			            dniP = (dniP.isEmpty()) ? sDniP : dniP;
					
					sql = "UPDATE public.mascota SET nombre=?, especie=?, raza=?, dni_propietario=? WHERE id=?";
					
					PreparedStatement stmt = conexion.prepareStatement(sql);
					stmt.setString(1, nombre);
					stmt.setString(2, especie);
					stmt.setString(3, raza);
					stmt.setString(4, dniP);	
					stmt.setInt(5, id);
					
					// Ejecutar la consulta
					int resultado = stmt.executeUpdate();
					if (resultado > 0) {
						System.out.println("Mascota modificada");
					} else {
						System.out.println("No se ha modificado los datos.");
					}	
					
				} else {
					System.out.println("No existe mascota con ese id");
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		//========================	
		//===== METODOS ALTA =====
		//========================
		
		//Baja Mascota
		public static void bajaMascota() {
			Connection conexion = conectar();
			
			try {
				
				System.out.print("\nID de la mascota a eliminar: ");
				int id = ts.nextInt(); ts.nextLine();
				
				String sql = "SELECT * FROM public.mascota WHERE id=?";
				PreparedStatement select = conexion.prepareStatement(sql);
				select.setInt(1, id);
				
				ResultSet rs = select.executeQuery();
				if(rs.next()) {
					
					sql = "DELETE FROM public.mascota WHERE id=?";
					
					PreparedStatement stmt = conexion.prepareStatement(sql);
					stmt.setInt(1, id);
					
					// Ejecutar la consulta
					int resultado = stmt.executeUpdate();
					if (resultado > 0) {
						System.out.println("¡Mascota eliminada");
					} else {
						System.out.println("No se eliminado la mascota.");
					}	
					
				} else {
					System.out.println("No existe mascota con ese id");
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		//Baja Propietario
		public static void bajaPropietario() {
			Connection conexion = conectar();
			
			try {
				
				System.out.print("\nDNI del propietario a eliminar: ");
				String dni = ts.nextLine();
				
				String sql = "SELECT * FROM public.propietario WHERE dni=?";
				PreparedStatement select = conexion.prepareStatement(sql);
				select.setString(1, dni);
				
				ResultSet rs = select.executeQuery();
				if(rs.next()) {
					
					sql = "DELETE FROM public.propietario WHERE dni=?";
					
					PreparedStatement stmt = conexion.prepareStatement(sql);
					stmt.setString(1, dni);
					
					// Ejecutar la consulta
					int resultado = stmt.executeUpdate();
					if (resultado > 0) {
						System.out.println("¡Propietario eliminado");
					} else {
						System.out.println("No se eliminado al propietario.");
					}	
					
				} else {
					System.out.println("No existe propietario con ese dni");
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		
		//==========================	
		//===== APLICAR VACUNA =====
		//==========================
		
		//Metodo de aplicar vacunas
		public static void aplicarVacuna() {
			Connection conexion = conectar();
			
			System.out.print("ID de la mascota: ");
	       	int id = ts.nextInt(); ts.nextLine();
	        System.out.print("Nombre de la vacuna: ");
	        String nombreVacuna = ts.nextLine();
	        System.out.print("Numero colegiado: ");
	        int numc = ts.nextInt(); ts.nextLine();
	        System.out.print("Fecha de vacunacion(YYYY-MM-DD): ");
			LocalDate fecha = LocalDate.parse(ts.nextLine());

	        String sql = "UPDATE mascota SET vacuna = array_append(vacuna, (?, ?, ?::date)::cartilla_vacunacion) WHERE id = ?";

	        try (PreparedStatement pst = conexion.prepareStatement(sql)) {

	            pst.setString(1, nombreVacuna);
	            pst.setInt(2, numc);
	            pst.setDate(3, java.sql.Date.valueOf(fecha));
	            pst.setInt(4, id);

	            int filas = pst.executeUpdate();

	            if (filas > 0) {
	                System.out.println("Vacuna aplicada correctamente");
	            } else {
	                System.out.println("No existe ninguna mascota con ese ID");
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		}
		
}
