package Ejercicios;

import java.io.*;
import java.util.*;

public class Ejercicios21_25 {

	static String ruta = "/home/diurno/eclipse-workspace/EjerciciosFicheros/Ejemplos/";

	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);

		System.out.println("Elige un ejercicio (1-5):");
		int opcion = teclado.nextInt();
		teclado.nextLine();

		switch (opcion) {
		case 1:
			ejercicio21(teclado);
			break;

		case 2:
			ejercicio22(teclado);
			break;

		case 3:
			ejercicio23(teclado);
			break;

		case 4:
			ejercicio24(teclado);
			break;

		case 5:
			ejercicio25();
			break;

		default:
			System.out.println("Opción no válida. Elige un número del 1 al 5.");
		}

		teclado.close();
	}

	// Ejercicio 21
	/*
	 * Crea un programa que genere un fichero binario que almacene un número entero
	 * entre el 32 y el 126. Se pedirá al usuario la ruta del fichero y el número
	 * que desea almacenar. Si el fichero existe se sobrescribirá. Visualiza el
	 * fichero creado. Compara el resultado con la tabla ASCII.
	 */
	public static void ejercicio21(Scanner teclado) {

		System.out.println("Introduzca la ruta del fichero: ");
		String nombreF = teclado.nextLine();

		File fichero = new File(ruta, nombreF);

		try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(fichero))) {

			System.out.println("Escribe un numero para añadir: ");
			int num = Integer.parseInt(teclado.nextLine());

			if (num >= 32 && num <= 126) {
				dos.writeByte(num);
			}

		} catch (IOException e) {
			e.getStackTrace();
		}

	}

	// Ejercicio 22
	/*
	 * Realiza un programa que muestre el número del fichero generado en el
	 * ejercicio anterior.
	 */
	public static void ejercicio22(Scanner teclado) {

		System.out.println("Introduzca la ruta del fichero: ");
		String nombreF = teclado.nextLine();

		File fichero = new File(ruta, nombreF);

		if (fichero.exists()) {
			try (DataInputStream dis = new DataInputStream(new FileInputStream(fichero))) {

				char caracter = (char) dis.read();
				System.out.println("Contenido del archivo: ");
				System.out.println(caracter);

			} catch (IOException e) {
				e.getStackTrace();
			}
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
	public static void ejercicio23(Scanner teclado) {

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
	public static void ejercicio24(Scanner teclado) {
		
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

	// Ejercicio 25
	public static void ejercicio25() {

	}

}
