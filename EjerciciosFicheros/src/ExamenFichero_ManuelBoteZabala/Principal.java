package ExamenFichero_ManuelBoteZabala;

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

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.fasterxml.jackson.databind.ObjectMapper;

//@Author Manuel Bote Zabala
public class Principal {
	//Nombres de rutas de ficheros
	static final File FILE_CSV = new File("FicheroExamen/AugustFit2.csv");
	static final File FILE_DAT = new File("FicheroExamen/AugustFit.dat");
	static final File FILE_XML = new File("FicheroExamen/AugustFitXML.xml");
	static final File FILE_JSON = new File("FicheroExamen/AugustFitJSON.json");
	static final File FILE_RANDOM = new File("FicheroExamen/actividad.dat");
	
	static Scanner tc = new Scanner(System.in);
	static ArrayList<Augusfit> listaFit = new ArrayList<Augusfit>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
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
					default -> System.out.println("Opcion no valida");
					}
					
				}while(cond != 0);	
		}
		
	//Menu
	public static void mostrarMenu() {
		System.out.println(" ===== Menu de Ejercicios ===== ");
		System.out.println("[1] Ejercicio 1 - Cargar lista con CSV");
		System.out.println("[2] Ejercicio 2 - Generar XML");			
		System.out.println("[3] Ejercicio 3 - Generar JSON");
		System.out.println("[4] Ejercicio 4 - Generar Fichero acceso aletorio");
		System.out.println("[5] Ejercicio 5 - Calcular indice de actividad");
		System.out.println("[0] Salir");
		System.out.print("Selecciona una opcion: ");
	}
	
	//Cargar los datos del csv a la lista "listaFit"
	public static void cargarDatosLista() {
		try (BufferedReader br = new BufferedReader(new FileReader(FILE_CSV))){			
			String linea = "";
			while((linea = br.readLine()) != null) {
				String[] datos = linea.split(";");
				listaFit.add(new Augusfit(Integer.parseInt(datos[0]), datos[1], Integer.parseInt(datos[2]), Integer.parseInt(datos[3]),
						Integer.parseInt(datos[4]), Integer.parseInt(datos[5]), datos[6]));
						
			}
			System.out.println("Datos cargados");
			
			/*
			 * Comprobacion de funcionamiento
			 * for(Augusfit a : listaFit) {
				System.out.println(a.toString());
			}
			 */	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//Ejercicio 1
	public static void ejercicio1() {
		//Llama al metodo por si no se habia cargado el fichero anteriormente
		if(listaFit.isEmpty()) {
			cargarDatosLista();
		} 
		
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_DAT))){
			for(Augusfit a : listaFit) {
				oos.writeObject(a);
			}
			System.out.println("Fichero .dat creado");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//Ejerccio 2
	public static void ejercicio2() {
		//Llama al metodo por si no se habia cargado el fichero anteriormente
		if(listaFit.isEmpty()) {
			cargarDatosLista();
		} 
			
		ContenedorAugusFitXML contxml = new ContenedorAugusFitXML();
		for(Augusfit a : listaFit) {
			contxml.getPersonas().add(new AugusFitXML(a.getId(), a.getNombre(), a.getComentaio()));
		}
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(ContenedorAugusFitXML.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(contxml, FILE_XML);
			
			System.out.println("XML creado");
		} catch (JAXBException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
			
	}
	
	//Ejercicio 3
	public static void ejercicio3() {
		//Llama al metodo por si no se habia cargado el fichero anteriormente
		if(listaFit.isEmpty()) {
			cargarDatosLista();
		} 
		
		ObjectMapper mapper = new ObjectMapper();
		ArrayList<AugusFitJson> contJson = new ArrayList<AugusFitJson>();
		for(Augusfit a : listaFit) {
			contJson.add(new AugusFitJson(a.getId(), a.getPasosDiarios(), a.getCaloriasQuemadas(), a.getHorasSueno(), a.getRitmoCardiaco()));
		}
		
		try {
			mapper.writeValue(FILE_JSON, contJson);
			System.out.println("JSON creado");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	//Ejercicio 4
	public static void ejercicio4() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_DAT));
				RandomAccessFile raf = new RandomAccessFile(FILE_RANDOM, "rw")){
			while (true) {
		        try {
		        	Augusfit user = (Augusfit) ois.readObject();
		        	
		        	raf.writeInt(user.getId());
		        	raf.writeInt(user.getPasosDiarios());
		        	raf.writeInt(user.getRitmoCardiaco());
		        	
		        } catch (EOFException eof) {
		            break; // Fin del archivo, salimos del bucle
		        }
		    }
			System.out.println("Fichero aleatorio creado");
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//Ejercicio 5
	public static void ejercicio5() {
		System.out.print("Introduzca el id del usuario: ");
		int idBuscado = tc.nextInt(); tc.nextLine();
		
		try (RandomAccessFile raf = new RandomAccessFile(FILE_RANDOM, "rw")){
			while (raf.getFilePointer() < raf.length()) {
				int id = raf.readInt();
				
				if (id == idBuscado) {
					int pasos = raf.readInt();
					int ritmo = raf.readInt();
					
					double indice = pasos*ritmo/100;
					System.out.println("Indice de actividad: " + indice);
					
					return;
				}
				else {raf.skipBytes(8);}
			}
			System.out.println("Usuario no encontrado.");
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}


}
