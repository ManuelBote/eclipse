package Ejercicios;

import java.util.*;
import java.io.*;

public class Ejercicio27 {
	
	static String ruta = "/home/diurno/eclipse-workspace/EjerciciosFicheros/Ejemplos/";
	static Scanner teclado = new Scanner(System.in);
	static Empleado empleadoInicial = new Empleado("12345678K", "Jose", 1250);
	static File f = new File(ruta, "datosEmpleados.bin");


	public static void main(String[] args) throws ClassNotFoundException {

		int num = 0;
		do {

			System.out.println("\n=====Selecciona una opcion=====");
			System.out.println("[1] Dar de alta empleados");
			System.out.println("[2] Buscar empleados por DNI");
			System.out.println("[3] Listar empleados");
			System.out.println("[4] Borrar empleado por DNI");
			System.out.println("[5] Salir");
			num = Integer.parseInt(teclado.nextLine());

			switch (num) {

			//Agregar Empleado
			case 1:
				agregarEmpleado();
				break;

			//Buscar empleado por DNI
			case 2:
				buscarEmpleado();
				break;

			//Listar empleados
			case 3:
				listarEmpleados();
				break;

			//Borrar empleado por DNI
			case 4:
				borrarEmpleado();
				break;

			case 5:
				System.out.println("Saliendo..");
				break;
			}

		} while (num != 5);

	}
	
	public static void agregarEmpleado() {
		System.out.println("DNI del empleado: ");
		String dni = teclado.nextLine();
		System.out.println("Nombre del empleado: ");
		String nombre = teclado.nextLine();
		System.out.println("Sueldo del empleado: ");
		double sueldo = Double.parseDouble(teclado.nextLine());

		Empleado emp = new Empleado(dni, nombre, sueldo);

		if (f.exists()) {
			try (ObjectOutputStream oos = new MiObjectOutputStream(new FileOutputStream(ruta + "datosEmpleados.bin", true))) {
				oos.writeObject(emp);
				System.out.println("Empleado agragado");

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
		}else {
			try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta + "datosEmpleados.bin", true))){
				oos.writeObject(emp);
				System.out.println("Empleado agragado");

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	
	public static void buscarEmpleado() throws ClassNotFoundException {
		if(f.exists()) {
			
			System.out.println("Indique el DNI del empleado a buscar: ");
			String dniBuscar = teclado.nextLine();
			
			try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta + "datosEmpleados.bin"))){
				
				while(true) {
					Empleado e = (Empleado)ois.readObject();
					if(e.getDni().equals(dniBuscar)) {
						System.out.println(e);
					}
				}
				
			} catch (EOFException eof) {
				 // Hemos llegado al final del archivo: es normal, salimos del bucle
			 } catch (FileNotFoundException fnfe) {
				 System.out.println("El archivo no existe.");
			 } catch (IOException e) {
				 System.out.println("Error leyendo el archivo: " + e.getMessage());
			 }
		}else {
			System.out.println("El fichero no existe");
		}
	}
	
	public static void listarEmpleados() throws ClassNotFoundException {
		if(f.exists()) {
			
			try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta + "datosEmpleados.bin"))){
				
				while(true) {
					Empleado e = (Empleado)ois.readObject();
					System.out.println(e);
				}
				
			} catch (EOFException eof) {
				 // Hemos llegado al final del archivo: es normal, salimos del bucle
			} catch (FileNotFoundException fnfe) {
				 System.out.println("El archivo no existe.");
			} catch (IOException e) {
				 System.out.println("Error leyendo el archivo: " + e.getMessage());
			}
		}else {
			System.out.println("El fichero no existe");
		}
	}

	
	public static void borrarEmpleado() throws ClassNotFoundException {
		if (f.exists()) {
			System.out.println("Indique el DNI del empleado a borrar: ");
			String dniBorrar = teclado.nextLine();
			
			ArrayList<Empleado> empleados = new ArrayList<Empleado>();
			
			try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta + "datosEmpleados.bin"))){
				
				while(true) {
					Empleado e = (Empleado)ois.readObject();
					if(!e.getDni().equals(dniBorrar)) {
						empleados.add(e);								
					}
				}
				
			} catch (EOFException eof) {
				 // Hemos llegado al final del archivo: es normal, salimos del bucle
			} catch (FileNotFoundException fnfe) {
				 System.out.println("El archivo no existe.");
			} catch (IOException e) {
				 System.out.println("Error leyendo el archivo: " + e.getMessage());
			}
			
			
			try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta + "datosEmpleados.bin"))){
				for (int i = 0; i < empleados.size(); i++) {
					oos.writeObject(empleados.get(i));							
				}
				System.out.println("Empleado borrado");
				listarEmpleados();

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			
		}else {
			System.out.println("El fichero no existe");
		}
	}
	
}
