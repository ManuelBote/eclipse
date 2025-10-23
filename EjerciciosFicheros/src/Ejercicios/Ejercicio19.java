package Ejercicios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Ejercicio19 {

	public static void main(String[] args) {

		/*
		 * A partir de un fichero cotizaciones.txt que contiene 5 líneas con información
		 * de cada empresa. Queremos generar un fichero cotizaciones2.txt con el nombre
		 * de la empresa y el precio de la acción que se encuentran en la línea 2 y 3
		 * respectivamente, para la siguiente empresa en la línea 7 y 8 y así
		 * sucesivamente. Para dar con las líneas de información debes calcular el
		 * modulo o resto de dividir esa línea por 5.
		 */


		File fichero = new File("Ejemplos/ej19_cotizacion.txt");
		File nuevoFichero = new File("Ejemplos/ej19_cotizacion2.txt");
		
		try(BufferedReader br = new BufferedReader(new FileReader(fichero));
				BufferedWriter bw = new BufferedWriter(new FileWriter(nuevoFichero))){
			
			String linea;
			int contLinea = 1;
			
			while((linea = br.readLine()) != null) {
				if(contLinea % 5 == 2) {
					bw.write(linea + ";");
					
				} else if(contLinea % 5 == 3) {
					bw.write(linea);
					bw.newLine();
				}
				contLinea ++;
			}
			
			System.out.println("Archivo creado");
			
		}catch(IOException e) {
			e.getStackTrace();
		}
	}

}
