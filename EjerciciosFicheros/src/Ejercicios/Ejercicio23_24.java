package Ejercicios;

import java.io.*;
import java.util.*;

public class Ejercicio23_24 {
	
	static String ruta = "/home/diurno/eclipse-workspace/EjerciciosFicheros/Ejemplos/";
	static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {


		System.out.println("Elige un ejercicio (1-5):");
		int opcion = teclado.nextInt();
		teclado.nextLine();

		switch (opcion) {
		case 1:
			ejercicio23();
			break;

		case 2:
			ejercicio24();
			break;
			
		}
		
	}
	
	// Ejercicio 23
		/*
		 * Realiza un programa que permita generar un fichero con las notas de los
		 * alumnos de AD. El programa solicitará al usuario que introduzca el número de
		 * expediente del alumno, el nombre y la nota del alumno. El fichero tendrá la
		 * siguiente estructura: 
		 * • int expediente 
		 * • double nota 
		 * • String Nombre_Alumno:
		 * Los String tienen que acabar con \n para que se pueda saber el tamaño del
		 * String y leer después con el método readChar(). La longitud del String es
		 * variable y el fin lo marca el \n. Podría usarse cualquier otro separador.
		 */
		public static void ejercicio23() {

			File fichero = new File(ruta, "alumnosAD.bin");
			int num = 0;
			
			do {
				
				System.out.println("Introduce el numero de expediente: ");
				int numEx = Integer.parseInt(teclado.nextLine());
				
				System.out.println("Introduce la nota del alumno: ");
				double nota = Double.parseDouble(teclado.nextLine());
				
				System.out.println("Introduce el nombre del alumno: ");
				String nombre = teclado.nextLine() + "\n";
				
				try(DataOutputStream dos = new DataOutputStream(new FileOutputStream(fichero, true))){
					
					dos.writeInt(numEx);
					dos.writeDouble(nota);
					dos.writeUTF(nombre);
					
				}catch(IOException e) {
					e.getStackTrace();
				}
				
				System.out.println("¿Desea agregar otro alumno? (0/1)");
				num = Integer.parseInt(teclado.nextLine());
				
			}while(num != 0);
			
			
		}

		// Ejercicio 24
		/*
		 * Realiza un programa que muestre los registros del fichero del ejercicio anterior. 
		 */
		public static void ejercicio24() {
			
			File fichero = new File(ruta, "alumnosAD.bin");
			
			if (fichero.exists()) {
				try (DataInputStream dis = new DataInputStream(new FileInputStream(fichero))) {
					
					while(true) {
					
						int numEx = dis.readInt();
						double nota = dis.readDouble();	
						String nomb = dis.readUTF();
						
						 System.out.println("Expediente: " + numEx);
			             System.out.println("Nota: " + nota);
			             System.out.println("Nombre: " + nomb);
						
					}

				}catch (EOFException e) {
					System.out.println("Fin del fichero");

				} catch (IOException e) {
					e.getStackTrace();
				}
			}
		}

}
