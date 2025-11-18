package EjercicioNavaVinted;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileReader;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import com.fasterxml.jackson.databind.ObjectMapper;

//@Author Manuel Bote Zabala
public class Principal {
	
	static final File FILE_CSV = new File("ropa.csv");
	static final File FILE_DAT = new File("ropa.dat");
	static final File FILE_PRECIOS = new File("precio.dat");
	static final File FILE_XML = new File("xml/ropa.xml");
	static final File FILE_JSON = new File("json/ropa.json");

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
		try (BufferedReader bw = new BufferedReader(new FileReader(FILE_CSV))){
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
		
		try (RandomAccessFile raf = new RandomAccessFile(FILE_DAT, "rw")) {
			for (Ropa r: listaRopa) {
				raf.writeInt(r.getId());
                raf.writeUTF(r.getNombre());
                //raf.writeUTF(r.getCategoria());
                raf.writeUTF(r.getTalla());
                raf.writeUTF(r.getColor());
                //raf.writeUTF(r.getMaterial());
                raf.writeInt(r.getStock());
                raf.writeInt(r.getPrecio());
                raf.writeInt(r.getCoste());
                raf.writeUTF(r.getEstado());
                raf.writeInt(r.getDescuento());
				
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
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
		try (RandomAccessFile raf = new RandomAccessFile(FILE_DAT, "r");
				RandomAccessFile raf2 = new RandomAccessFile(FILE_PRECIOS, "rw")){ 
			while (true) {
		        try {
		        	int id = raf.readInt();

		            String nombre = raf.readUTF().trim();
		            String talla = raf.readUTF().trim();
		            String color = raf.readUTF().trim();

		            int stock = raf.readInt();
		            int precio = raf.readInt();
		            int coste = raf.readInt();

		            String estado = raf.readUTF().trim();
		            int descuento = raf.readInt();

		            System.out.println(id + ", " + precio + ", " + coste + ", " + descuento);
		            
		            raf2.writeInt(id);
		            raf2.writeInt(precio);
		            raf2.writeInt(coste);
		            raf2.writeInt(descuento);
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
		System.out.print("Introduzca el id del producto: ");
		int idBuscado = tc.nextInt(); tc.nextLine();
		
		try (RandomAccessFile raf = new RandomAccessFile(FILE_PRECIOS, "r")){
			
			while (raf.getFilePointer() < raf.length()) {
				int id = raf.readInt();
				
				if (id == idBuscado) {
					int precio = raf.readInt();
					int coste = raf.readInt();
					int descuento = raf.readInt();
					
					double beneficio = precio - (precio*descuento/100)-coste;
					System.out.println("Beneficio: " + beneficio);
					
					return;
				}
				else {raf.skipBytes(12);}
			}
			System.out.println("DNI no encontrado.");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}
	
	public static void ejercicio4() {
		ContenedorRopaXML contRopa = new ContenedorRopaXML();
		
		try (RandomAccessFile raf = new RandomAccessFile(FILE_DAT, "r")){
			
			while (true) {
		        try {
		        	int id = raf.readInt();

		            String nombre = raf.readUTF().trim();
		            String talla = raf.readUTF().trim();
		            String color = raf.readUTF().trim();

		            int stock = raf.readInt();
		            int precio = raf.readInt();
		            int coste = raf.readInt();

		            String estado = raf.readUTF().trim();
		            int descuento = raf.readInt();

		            contRopa.getPrendas().add(new RopaXML(nombre, talla, color, precio, estado));
		        } catch (EOFException eof) {
		            break; // Fin del archivo, salimos del bucle
		        }
		    } 
			
			JAXBContext jaxbContext = JAXBContext.newInstance(ContenedorRopaXML.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(contRopa, FILE_XML);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}
	
	public static void ejercicio5() {
		ObjectMapper mapper = new ObjectMapper();
		ArrayList<RopaJSON> contRopa = new ArrayList<RopaJSON>();
		
		try (RandomAccessFile raf = new RandomAccessFile(FILE_DAT, "r")){
			
			while (true) {
		        try {
		        	int id = raf.readInt();

		            String nombre = raf.readUTF().trim();
		            String talla = raf.readUTF().trim();
		            String color = raf.readUTF().trim();

		            int stock = raf.readInt();
		            int precio = raf.readInt();
		            int coste = raf.readInt();

		            String estado = raf.readUTF().trim();
		            int descuento = raf.readInt();

		            contRopa.add(new RopaJSON(id, precio));
		        } catch (EOFException eof) {
		            break; // Fin del archivo, salimos del bucle
		        }
		    } 
			
			mapper.writeValue(FILE_JSON, contRopa);			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
}
