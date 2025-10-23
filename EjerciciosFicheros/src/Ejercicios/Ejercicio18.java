package Ejercicios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio18 {

	public static void main(String[] args) {

		/*
		 * Realiza un programa al que pida el nombre de un fichero de texto y genere
		 * otro fichero exactamente igual, pero con las letras del alfabeto en
		 * mayúsculas transformadas en minúsculas y viceversa. Si en el fichero original
		 * aparece el texto Hola, en el fichero final debe aparecer hOLA.
		 */
		
		Scanner teclado = new Scanner(System.in);

		System.out.print("Introduce la ruta del archivo: ");
		String ruta = teclado.nextLine();
		System.out.println("");
		
		File fichero = new File("Ejemplos/"+ruta);
		File nuevoFichero = new File("Ejemplos/CambioMm_"+ruta);
		StringBuilder build = new StringBuilder();
		
		try(FileReader fr = new FileReader(fichero)){
			
			char letra;
			int caracter;
			
			while((caracter = fr.read()) != -1) {
				letra = (char) caracter;
				if(letra == '\n' || letra == ' ') {
					build.append(letra);
					
				}else if (Character.isLowerCase(letra)) {
					build.append(Character.toUpperCase(letra));
					
				}else if (Character.isUpperCase(letra)) {
					build.append(Character.toLowerCase(letra));
				}
				
			}
			
		}catch(IOException e) {
			e.getStackTrace();
		}
		
		
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(nuevoFichero))){
			
			bw.write(build.toString());
			
		}catch(IOException e) {
			e.getStackTrace();
		}
		
		try(BufferedReader br = new BufferedReader(new FileReader(nuevoFichero))){
			
			String linea;
			
			while((linea = br.readLine()) != null) {
				System.out.println(linea);
			}
			
		}catch(IOException e) {
			e.getStackTrace();
		}

	}

}
