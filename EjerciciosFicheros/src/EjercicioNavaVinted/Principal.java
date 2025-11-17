package EjercicioNavaVinted;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

import EjercicioFinal.Persona;

public class Principal {
	
	static Scanner tc = new Scanner(System.in);
	static ArrayList<Ropa> listaRopa = new ArrayList<Ropa>();

	public static void main(String[] args) {
		
		int cond = 0;
		
		do {
			mostrarMenu();
			cond = tc.nextInt(); tc.nextLine();
			
			switch(cond) {
			case 1 -> ejercicio1();
			case 2 -> ejercicio2();
			case 3 -> ejercicio3();
			case 4 -> ejercicio4();
			case 5 -> ejercicio5();
			case 0 -> System.out.println("Saliendo...");
			}
			
		}while(cond != 0);
		
	}
	
	//Menu
	public static void mostrarMenu() {
		System.out.println(" ===== Menu de Ejercicios ===== ");
		System.out.println("[1] Ejercicio 1 - De csv a binario");
		System.out.println("[2] Ejercicio 2 - Acceso aleatorio para precio final");
		System.out.println("[3] Ejercicio 3 - Saber beneficio aleatorio");
		System.out.println("[4] Ejercicio 4 - Generar XML");
		System.out.println("[5] Ejercicio 5 - Generar JSON");
		System.out.println("[0] Salir");
		System.out.print("Selecciona una opcion: ");
	}
	
	//Ejercicio 1
	public static void ejercicio1() {
		
		try (BufferedReader bw = new BufferedReader(new FileReader(new File("ropa.csv")))){
			String linea = "";
			while((linea = bw.readLine()) != null) {
				String[] datos = linea.split(";");
				listaRopa.add(new Ropa(Integer.parseInt(datos[0]), datos[1], datos[2], datos[3], datos[4], datos[5],
						Integer.parseInt(datos[6]), Integer.parseInt(datos[7]), Integer.parseInt(datos[8]), datos[9], 
						Integer.parseInt(datos[10])));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("ropa.dat")))) {
			for (Ropa r: listaRopa) {
				oos.writeInt(r.getId()); //Id
				
				//Nombre
				String dat = r.getNombre();
				if(dat.length() < 30) {
					while(dat.length() == 30) {
						dat += " ";
					}
				}
				oos.writeBytes(dat);
				
				//Talla
				dat = r.getTalla();
				if(dat.length() < 3) {
					while(dat.length() == 3) {
						dat += " ";
					}
				}
				oos.writeBytes(dat);
				
				//Color
				dat = r.getColor();
				if(dat.length() < 10) {
					while(dat.length() == 10) {
						dat += " ";
					}
				}
				oos.writeBytes(dat);
				
				oos.writeInt(r.getStock());		//stock
				oos.writeInt(r.getPrecio());	//precio
				oos.writeInt(r.getCoste());		//coste
				
				//Estado
				dat = r.getEstado();
				if(dat.length() < 50) {
					while(dat.length() == 50) {
						dat += " ";
					}
				}
				oos.writeBytes(dat);
				
				oos.writeInt(r.getDescuento());		//descuento
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		/* Metodo mostrar, para comprobar el funcionamiento
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("ropa.dat")))){ 
			while (true) {
		        try {
		        	int id = ois.readInt();

		            String nombre = ois.readUTF().trim();
		            String talla = ois.readUTF().trim();
		            String color = ois.readUTF().trim();

		            int stock = ois.readInt();
		            int precio = ois.readInt();
		            int coste = ois.readInt();

		            String estado = ois.readUTF().trim();
		            int descuento = ois.readInt();

		            System.out.println(id + ", " + nombre + ", " + talla + ", " + color + ", " + stock + ", " + precio + ", " + coste + ", " + estado + ", " + descuento);
		        } catch (EOFException eof) {
		            break; // Fin del archivo, salimos del bucle
		        }
		    } 
		} catch (Exception e) { 
			// TODO: handle exception 
			e.printStackTrace();
		}
		*/
		
	}
	
	public static void ejercicio2() {
		
		try (RandomAccessFile raf = new RandomAccessFile("ropa.dat", "r");
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("precio.dat")))){ 
			while (true) {
		        try {
		        	int id = raf.readInt();

		            raf.skipBytes(53);
		            
		            int precio = raf.readInt();
		            int coste = raf.readInt();

		            raf.skipBytes(52);
		         
		            int descuento = raf.readInt();

		            System.out.println(id + ", " + precio + ", " + coste + ", " + descuento);
		        } catch (EOFException eof) {
		            break; // Fin del archivo, salimos del bucle
		        }
		    } 
		} catch (Exception e) { 
			// TODO: handle exception 
			e.printStackTrace();
		}
			
	}
	
	public static void ejercicio3() {
		
	}
	
	public static void ejercicio4() {
		
	}
	
	public static void ejercicio5() {
		
	}
}
