package Ejercicios;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class EjemploLeerFichero {

	public static void main(String[] args)  {
		

		FileReader fichero = null;
		
		try {
			fichero = new FileReader("nombres.txt");
			char letra;
			int caracter;
			
			while((caracter = fichero.read()) != -1) {
				letra = (char) caracter;
				System.out.print(letra);
			}
		
		
		}catch(FileNotFoundException e) {
			System.out.println("Fichero no encontrado");
			e.printStackTrace();
		}
		catch (IOException e){
			System.out.println("Error al leer");
			e.printStackTrace();
		}
		finally {
			try {
				fichero.close();

			}catch(IOException e) {
				e.printStackTrace();
			}
		}

	}

}
