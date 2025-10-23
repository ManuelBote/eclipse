package Ejercicios;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class EjemploEscribirBinario {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String ruta = "Ejemplos/datos.bin";
		
		try(FileOutputStream fos = new FileOutputStream(ruta);
			DataOutputStream dos = new DataOutputStream(fos)){
			
			//Escribir algunos datos binarios
			dos.writeInt(123);				//Escribir un entero
			dos.writeDouble(45.67);			//Escribir un double
			dos.writeBoolean(true);			//Escribir un booleano
			dos.writeUTF("Hola Mundo\nAAA");		//Escribir un String en UTF-8+
			
			System.out.println("Datos escritos correctamente en " + ruta);
			
		} catch(IOException e) {
			e.printStackTrace();
		}

	}

}
