package Ejercicios;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

public class EjemploAleatorioNumero {

	public static void main(String[] args) {
		
		Scanner teclado = new Scanner (System.in);
		
		try(
			//Crear o abrir fichero para escribir	
			RandomAccessFile archivo = new RandomAccessFile("numeros.txt", "rw")){
			
			//Escribir datos en el archivo
			archivo.writeInt(1);
			archivo.writeDouble(5.5);
			
			archivo.writeInt(2);
			archivo.writeDouble(7.7);
			
			archivo.writeInt(3);
			archivo.writeDouble(9.9);
			
			//Variables
			int numPosicion;
			double valor;
			
			//Solicitar al usuario el nombre del fichero
			System.out.println("Introduce el orden de la nota a buscar: ");
			int numLeer = Integer.parseInt(teclado.nextLine());
			
			//Ir al inicio del archivo
			archivo.seek(0);
			
			//Bucle para leer todos los datos
			try {
				while(true) {
					//Leemos el archivo
					numPosicion = archivo.readInt();
					valor = archivo.readDouble();
					
					if(numPosicion == numLeer) {
						System.out.println("Numero enconrado: " + numPosicion + ", valor: " + valor);
						break;
					}
				}
			}catch(EOFException e) {
				System.out.println("Fin del archivo");
			}
			
			//Cerramos el archivo
			archivo.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}

	}

}
