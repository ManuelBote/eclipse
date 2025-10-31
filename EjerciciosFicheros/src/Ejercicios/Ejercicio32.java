package Ejercicios;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

//@author Manuel Bote Zabala
public class Ejercicio32 {

	public static void main(String[] args) {
		
		/*
		 * Crea un programa en Java que simule el acceso y modificación de registros en un archivo
		 * binario que contiene información de productos. Cada registro almacenado en el archivo
		 * tiene un tamaño fijo y contiene los siguientes campos:
		 *  Código del producto, un número entero.
		 *  Nombre del producto, máximo 20 caracteres.
		 *  Precio del producto, un número con decimales.
		 *  Cantidad en stock, un número entero.
		 */
		
		try(RandomAccessFile archivo = new RandomAccessFile("Ejemplos/productos.dat", "rw")){
			
			escribirProducto(archivo, 1, "Leche", 1.50, 100);
			escribirProducto(archivo, 2, "Pan", 8.75, 200);
			escribirProducto(archivo, 3, "Zumo", 15.30, 50);
			
			leerProducto(archivo, 1);
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
		    e.printStackTrace();
		}


	}
	
	private static void escribirProducto(RandomAccessFile archivo, int c, String n, double p, int s) {
		
		if(n.length() <= 20) {
			while(n.length()<20) {
				n += " ";
			}
		}
		
		try {
			archivo.writeInt(c); 		//Codigo
			archivo.writeBytes(n);		//Nombre
			//archivo.writeChar(n); 	//Array de caracteres
			//archivo.writeUTF(n);		//cada caracter ocupa 2 bytes, pero utiliza los 2 bytes primero para indicar
			archivo.writeDouble(p);		//Precio
			archivo.writeInt(s);		//Stock
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private static void leerProducto(RandomAccessFile archivo, int indice) {
		
		try {
			archivo.seek(indice*36);
			
			int codigo = archivo.readInt();
			
			byte[] nombreBytes = new byte[20];
			archivo.read(nombreBytes);
			String nombre = new String(nombreBytes).trim();
			
            double precio = archivo.readDouble();
            int stock = archivo.readInt();

            System.out.println("Producto: " + codigo);
            System.out.println("Nombre: " + nombre);
            System.out.println("Precio: " + precio);
            System.out.println("Cantidad en stock: " + stock);
		
		}catch(IOException e) {
			e.getStackTrace();
		}
		
	}
	

}
