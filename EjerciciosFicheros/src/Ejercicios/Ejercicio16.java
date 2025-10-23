package Ejercicios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio16 {

	public static void main(String[] args) throws IOException {
		
		/*
		 * Realiza un programa que permita borrar los datos de un fichero. El programa
		 * mostrará el fichero y pedirá al usuario qué línea quiere borrar. El usuario
		 * introducirá un número de línea, modificará el fichero y mostrará el fichero
		 * modificado.
		 */
		
		Scanner teclado = new Scanner(System.in);
		
		BufferedReader fichero = null;
		
		ArrayList<String> lineas = new ArrayList<String>();

		System.out.println("Introduce la ruta del archivo");
		String ruta = teclado.nextLine();
		
		try {
			fichero = new BufferedReader(new FileReader("src/Ejercicios/"+ruta));
			
			String linea;		
			while((linea = fichero.readLine()) != null) {
				System.out.println(linea);
				lineas.add(linea);
			}
			
			
			System.out.print("¿Que lineas deseas borrar? ");
			int num = Integer.parseInt(teclado.nextLine());
			
			BufferedWriter bw = new BufferedWriter(new FileWriter("src/Ejercicios/"+ruta));
			
			for (int i = 0; i < lineas.size(); i++) {
				if (num != i+1) {
					bw.write(lineas.get(i));
					bw.newLine();
				}
			}
			bw.close();
			
			
			System.out.println("\nFichero actualizado:");
            BufferedReader nuevo = new BufferedReader(new FileReader("src/Ejercicios/"+ruta));
            
            while ((linea = nuevo.readLine()) != null) {
                System.out.println(linea);
            }
            nuevo.close();
			
			
		}catch(Exception e) {
			System.out.println("Error al abrir el fichero");
			e.getMessage();
			e.printStackTrace();
		}
		finally {
			fichero.close();
		}
		
	}

}
