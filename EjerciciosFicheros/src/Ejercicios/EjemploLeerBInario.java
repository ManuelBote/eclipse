package Ejercicios;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class EjemploLeerBInario {

	public static void main(String[] args) {
		
		
		String ruta = "Ejemplos/datos.bin";
		
		try(FileInputStream fis = new FileInputStream(ruta);
				DataInputStream dis = new DataInputStream(fis)){
			
			int numero = dis.readInt();
			double decimal = dis.readDouble();
			boolean booleano = dis.readBoolean();
			String texto = dis.readUTF();
			
			System.out.println("Numero: " + numero);
			System.out.println("Decimal: " + decimal);
			System.out.println("Booleano: " + booleano);
			System.out.println("Texto: " + texto);
			
		} catch(IOException e) {
			e.getStackTrace();
		}
		
		

	}

}
