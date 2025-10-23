package Ejercicios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EjemploLeerLineasFichero {

	public static void main(String[] args) throws IOException {
		
		BufferedReader fichero = null;
		
		try {
			fichero = new BufferedReader(new FileReader("nombres.txt"));
			
			String linea;
			System.out.println("Contenido del fichero: ");
			
			while((linea = fichero.readLine()) != null) {
				System.out.println(linea);
			}
			
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
