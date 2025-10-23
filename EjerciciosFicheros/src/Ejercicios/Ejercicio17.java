package Ejercicios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ejercicio17 {

	public static void main(String[] args) {

		/*
		 * Realiza un programa que permita modificar los datos de un fichero. El
		 * programa mostrará el fichero y pedirá al usuario qué línea quiere modificar.
		 * El usuario introducirá un número de línea y el programa le pedirá los datos
		 * de la nueva línea. Por último, el programa modificará el fichero y mostrará
		 * el fichero modificado.
		 */
		
		Scanner teclado = new Scanner(System.in);

		System.out.print("Introduce la ruta del archivo: ");
		String ruta = teclado.nextLine();
		System.out.println("");
		
		File fichero = new File("src/Ejercicios/"+ruta);
		File fichTemp = new File("src/Ejercicios/temp.txt");
		
		List<String> lineas = new ArrayList<>();

		
		if(!fichero.exists()) {
			System.out.println("El archivo no exite");
		}
		else {
			
			//Leer fichero original
			try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
	            String linea;
	            int numLinea = 1;
	            while ((linea = br.readLine()) != null) {
	                System.out.println(numLinea + ": " + linea);
	                lineas.add(linea);
	                numLinea++;
	            }
	        } catch (IOException e) {
	            System.out.println("Error al leer el fichero: " + e.getMessage());
	            return;
	        }
			
			
			//Pedir linea a modificar
			System.out.print("\n¿Que línea quieres modificar? ");
            int numBorrar = Integer.parseInt(teclado.nextLine());
            
            if (numBorrar < 1 || numBorrar > lineas.size()) {
                System.out.println("Numero de línea no valido.");
                return;
            }
			
            
            //Reescribir fichero
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(fichTemp))) {
                for (int i = 0; i < lineas.size(); i++) {
                    if (i != numBorrar - 1) { 
                        bw.write(lineas.get(i));
                        bw.newLine();
                    } else {
                    	System.out.println("Introduce el contenido que quieras agregar: ");
                    	String texto = teclado.nextLine();
                    	bw.write(texto);
                    	bw.newLine();
                    }
                }
            } catch (IOException e) {
                System.out.println("Error al escribir el fichero temporal: " + e.getMessage());
                return;
            }
            
            
            //Comprobar cambio de nombre y borrar
            if (fichero.delete()) {
                if (fichTemp.renameTo(fichero)) {
                    System.out.println("\nLínea borrada correctamente.");
                } else {
                    System.out.println("Error al renombrar el fichero temporal.");
                }
            } else {
                System.out.println("Error al eliminar el fichero original.");
            }
			
            // Mostrar contenido nuevo
            System.out.println("\nContenido final del fichero:");
            try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    System.out.println(linea);
                }
            } catch (IOException e) {
                System.out.println("Error al leer el fichero actualizado: " + e.getMessage());
            }
		}

	}

}
