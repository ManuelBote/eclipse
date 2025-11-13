package EjercicioFinal;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Principal {

	static Scanner sc = new Scanner(System.in);
	static PersonasJson personasJson = new PersonasJson();
	static PersonasXML personasXml = new PersonasXML();
	static TreeMap<String, PersonaJson>  ArrayPersonasJson = new TreeMap<String, PersonaJson>();
	static TreeMap<String, PersonaXML>  ArrayPersonasXml = new TreeMap<String, PersonaXML>();
	static TreeMap<String, Persona> ArrayPersonas = new TreeMap<String, Persona>();

	public static void main(String[] args)  {
		// TODO Auto-generated method stub

		//Lectura del JSON
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			PersonasJson personas = mapper.readValue(new File("json/personas.json"), PersonasJson.class);
			personasJson.setPersonasJson(personas.getPersonasJson());
			
			/* Comprobar que cargue objetos
			for(PersonaJson p : personasJson.getPersonasJson()) {
				System.out.println(p.toString());
			}
			*/
			for (PersonaJson p : personasJson.getPersonasJson()) {
				ArrayPersonasJson.put(p.getDni(), p);
			}
			
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		//Lectura del XML
		try {
			JAXBContext contexto = JAXBContext.newInstance(PersonasXML.class);
			Unmarshaller um = contexto.createUnmarshaller();
			personasXml = (PersonasXML) um.unmarshal(new File("xml/personas.xml"));	
			
			/*  Comprobar que cargen los objetos
				for(PersonaXML p : personasXml.getPersonasXML()) {
					System.out.println(p.toString());
				}
			*/
			for (PersonaXML p : personasXml.getPersonasXML()) {
				ArrayPersonasXml.put(p.getDni(), p);
			}
			
		} catch (JAXBException e) {
			// TODO: handle exception
			e.printStackTrace();
		}	

		int cond = 0;

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
				
				break;
				
			case 5:
				break;
				
			case 6:
				break;
			}

		} while (cond != 0);

	}

	private static void unificarFicheros() {
		// TODO Auto-generated method stub
		generarArrayTotal();
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File("personasFusion.obj")))){
			for(Persona p : ArrayPersonas.values()) {
				bw.write(p.toString());
				bw.newLine();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	private static void generarArrayTotal() {
		for(PersonaJson p: ArrayPersonasJson.values()) {
			if(ArrayPersonasXml.containsKey(p.getDni())) {
				ArrayPersonas.put(p.getDni(), new Persona(p.getDni(), p.getNombre(), p.getEdad(), ArrayPersonasXml.get(p.getDni()).getTelefono()
						, ArrayPersonasXml.get(p.getDni()).getEmail()));
			} else {
				ArrayPersonas.put(p.getDni(), new Persona(p.getDni(), p.getNombre(), p.getEdad()));
			}
		}
		
		for(PersonaXML p: ArrayPersonasXml.values()) {
				if(!ArrayPersonas.containsKey(p.getDni())){
				ArrayPersonas.put(p.getDni(), new Persona(p.getDni(), p.getTelefono(), p.getEmail()));
			}
		}
	}

	private static void leerXML() {
		// TODO Auto-generated method stub
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(PersonasXML.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
		
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(personasXml, new File("xml/personas2.xml"));
			
		} catch (JAXBException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
	}

	private static void leerJson() {
		// TODO Auto-generated method stub
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("persona1.obj")))){
			for(PersonaJson p: personasJson.getPersonasJson()) {
				oos.writeObject(p);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		/*
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("persona1.obj")))){
			while(true) {
				PersonaJson p = (PersonaJson) ois.readObject();
				System.out.println(p.toString());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		*/
		
		
		
	}

}
