package EjercicioFinal;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.fasterxml.jackson.databind.ObjectMapper;

//@author Manuel Bote Zabala
public class Principal {

	static Scanner sc = new Scanner(System.in);
	static Personas personasJson = new Personas();
	static Personas personasXml = new Personas();
	static TreeMap<String, Persona> ArrayPersonasJson = new TreeMap<String, Persona>();
	static TreeMap<String, Persona> ArrayPersonasXml = new TreeMap<String, Persona>();
	static TreeMap<String, Persona> ArrayPersonas = new TreeMap<String, Persona>();

	public static void cargarJson() {
		ObjectMapper mapper = new ObjectMapper();

		try {
			Personas personas = mapper.readValue(new File("EjercicioFinalFicheros/personas.json"), Personas.class);
			personasJson.setPersonas(personas.getPersonas());
			/*
			 * Comprobar que cargue objetos for(PersonaJson p :
			 * personasJson.getPersonasJson()) { System.out.println(p.toString()); }
			 * 
			 * for (Persona p : personasJson.getPersonas()) {
			 * ArrayPersonasJson.put(p.getDni(), p); }
			 */
			
			System.out.println("Json cargado");
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static void cargarXML() {
		// Lectura del XML
		try {
			JAXBContext contexto = JAXBContext.newInstance(Personas.class);
			Unmarshaller um = contexto.createUnmarshaller();
			personasXml = (Personas) um.unmarshal(new File("EjercicioFinalFicheros/personas.xml"));
			/*
			 * Comprobar que cargen los objetos for(PersonaXML p :
			 * personasXml.getPersonasXML()) { System.out.println(p.toString()); }
			 * 
			 * for (Persona p : personasXml.getPersonas()) {
			 * ArrayPersonasXml.put(p.getDni(), p); }
			 */

			System.out.println("XML cargado");
		} catch (JAXBException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		int cond = 0;
		cargarJson();
		cargarXML();

		do {

			System.out.println("\n[1] Leer Json");
			System.out.println("[2] Leer XML");
			System.out.println("[3] Unificar Ficheros");
			System.out.println("[4] Generar csv");
			System.out.println("[5] Generar fichero binario aleatorio");
			System.out.println("[6] Buscar en el fichero binario aleatorio");
			System.out.println("[0] Salir");
			System.out.print("Selecciona una opcion: ");
			cond = Integer.parseInt(sc.nextLine());

			switch (cond) {

			case 0:
				break;

			case 1:
				leerJson();
				break;

			case 2:
				leerXML();
				break;

			case 3:
				unificarFicheros();
				break;

			case 4:
				generarCsv();
				break;

			case 5:
				generarFicheroAleatorio();
				break;

			case 6:
				leerFicheroAleatorio();
				break;
			}

		} while (cond != 0);

	}

	//Leer Fichero Aleatorio
	private static void leerFicheroAleatorio() {
		
		System.out.println("Introduzca el dni que quiere buscar");
		String dniBuscado = sc.nextLine();
		
		try (RandomAccessFile raf = new RandomAccessFile("telefonos.bin", "r");){
			while (raf.getFilePointer() < raf.length()) {
				String dni = raf.readUTF();
				
				if (dni.trim().equalsIgnoreCase(dniBuscado.trim())) {
					int tel = raf.readInt();
					System.out.println("Teléfono de " + dni + ": " + tel);
					return;
				}
				else {raf.skipBytes(4);}
			}
			System.out.println("DNI no encontrado.");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}

	//Generar fichero aleatorio
	private static void generarFicheroAleatorio() {
		// TODO Auto-generated method stub
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("personas.obj")))){ 
			RandomAccessFile raf = new RandomAccessFile("telefonos.bin", "rw");
			while (true) {
		        try {
		            Persona p = (Persona) ois.readObject();
		            
		            String dni = p.getDni();
		            if(dni.length() < 9) {
		            	while(dni.length()<9) {
		    				dni += " ";
		    			}
		            } else if(dni.length() > 9) {
		            	 dni = dni.substring(0, 9);
		            }
		            
		            raf.writeUTF(dni);
					raf.writeInt(p.getTelefono());
					
		        } catch (EOFException eof) {
		            break; // Fin del archivo, salimos del bucle
		        }
		    }
				 
		} catch (Exception e) { 
			// TODO: handle exception 
			e.printStackTrace();
		}
	}

	//Generar CSV
	private static void generarCsv() {
		// TODO Auto-generated method stub
		ArrayList<Persona> personaCsv = new ArrayList<Persona>();
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("personas.obj")))){ 
			while (true) {
		        try {
		            Persona p = (Persona) ois.readObject();
		            personaCsv.add(p);
		        } catch (EOFException eof) {
		            break; // Fin del archivo, salimos del bucle
		        }
		    }
				 
		} catch (Exception e) { 
			// TODO: handle exception 
			e.printStackTrace();
		}
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File("contactos.csv")))){
			for(Persona p : personaCsv) {
				bw.write(p.getDni() + ";" + p.getNombre() + ";" + p.getEdad() + ";" + p.getTelefono() + ";" + p.getEmail());
				bw.newLine();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}

	//Generar fichero unificado
	private static void unificarFicheros() {
		// TODO Auto-generated method stub
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("persona1.obj")))){ 
			while (true) {
		        try {
		            Persona p = (Persona) ois.readObject();
		            ArrayPersonasJson.put(p.getDni(), p);
		        } catch (EOFException eof) {
		            break; // Fin del archivo, salimos del bucle
		        }
		    }
				 
		} catch (Exception e) { 
			// TODO: handle exception 
			e.printStackTrace();
		}
		
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("persona2.obj")))){ 
			while (true) {
		        try {
		            Persona p = (Persona) ois.readObject();
		            ArrayPersonasXml.put(p.getDni(), p);
		        } catch (EOFException eof) {
		            break; // Fin del archivo, salimos del bucle
		        }
		    }
				 
		} catch (Exception e) { 
			// TODO: handle exception 
			e.printStackTrace();
		}
		
		generarArrayTotal();

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("personas.obj")))) {
			for (Persona p : ArrayPersonas.values()) {
				oos.writeObject(p);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("personas.obj")))){ 
			while (true) {
		        try {
		            Persona p = (Persona) ois.readObject();
		            System.out.println(p.toString());
		        } catch (EOFException eof) {
		            break; // Fin del archivo, salimos del bucle
		        }
		    } 
		} catch (Exception e) { 
			// TODO: handle exception 
			e.printStackTrace();
		}
	}

	
	//Metodo para unificar los arrays
	private static void generarArrayTotal() {
		for (Persona p : ArrayPersonasJson.values()) {
			if (ArrayPersonasXml.containsKey(p.getDni())) {
				ArrayPersonas.put(p.getDni(), new Persona(p.getDni(), p.getNombre(), p.getEdad(),
						ArrayPersonasXml.get(p.getDni()).getTelefono(), ArrayPersonasXml.get(p.getDni()).getEmail()));
			} else {
				ArrayPersonas.put(p.getDni(), new Persona(p.getDni(), p.getNombre(), p.getEdad()));
			}
		}

		for (Persona p : ArrayPersonasXml.values()) {
			if (!ArrayPersonas.containsKey(p.getDni())) {
				ArrayPersonas.put(p.getDni(), new Persona(p.getDni(), p.getTelefono(), p.getEmail()));
			}
		}
	}

	//Leer XML
	private static void leerXML() {
		// TODO Auto-generated method stub
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("persona2.obj")))) {
			for (Persona p : personasXml.getPersonas()) {
				oos.writeObject(p);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		 /*
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("persona2.obj")))){ 
			while(true) { 
				Persona p = (Persona) ois.readObject(); 
				System.out.println(p.toString()); 	
			}
				 
		} catch (Exception e) { 
			// TODO: handle exception 
			e.printStackTrace();
		}
		*/
		
	}

	//Leer JSON
	private static void leerJson() {
		// TODO Auto-generated method stub
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("persona1.obj")))) {
			for (Persona p : personasJson.getPersonas()) {
				oos.writeObject(p);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		/*
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("persona1.obj")))){ 
			while(true) { 
				Persona p = (Persona) ois.readObject(); 
				System.out.println(p.toString()); 	
			}
				 
		} catch (Exception e) { 
			// TODO: handle exception 
			e.printStackTrace();
		}
		*/
	}

}
