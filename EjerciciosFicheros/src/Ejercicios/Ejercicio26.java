package Ejercicios;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Ejercicio26 {
	
	static String ruta = "/home/diurno/eclipse-workspace/EjerciciosFicheros/Ejemplos/";
	static Empleado empleadoInicial = new Empleado("12345678K", "Jose", 1250);

	public static void main(String[] args) throws ClassNotFoundException {
		
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta + "empleado.bin"))) {

			oos.writeObject(empleadoInicial);
			System.out.println("Empleado añadido");

		} catch (IOException e) {
			System.out.println("Error al escribir el empleado: " + e.getMessage());

		}

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta + "empleado.bin"))) {

			Empleado emp = (Empleado) ois.readObject();
			System.out.println(emp);

		} catch (IOException e) {
			System.out.println("Error al leer el empleado: " + e.getMessage());

		}


	}

}
