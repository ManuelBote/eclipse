package EjercicioFinal;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Principal {

	static Scanner sc = new Scanner(System.in);
	static PersonasJson personasJson = new PersonasJson();
	static PersonasXML personasXml = new PersonasXML();

	public static void main(String[] args) throws TransformerFactoryConfigurationError, TransformerException {
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
			System.out.println(personasXml.getPersonasXML().toString());
			*/
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

	private static void leerXML() {
		// TODO Auto-generated method stub
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(PersonasXML.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
		
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(personasXml, System.out);
			
		} catch (JAXBException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
	}

	private static void leerJson() throws TransformerFactoryConfigurationError, TransformerException {
		// TODO Auto-generated method stub
		

		try {
			DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = factoria.newDocumentBuilder();
			
			Document documento = db.newDocument();
			documento.setXmlVersion("1.0");
			
			Element personasCont = documento.createElement("personas");
			
			for (PersonaJson a : personasJson.getPersonasJson()) {
				
				Element persona = documento.createElement("persona");
				
				Element dni = documento.createElement("dni");
				Element nombre = documento.createElement("nombre");
				Element edad = documento.createElement("edad");
				
				Text textoDni = documento.createTextNode(a.getDni());
				Text textoNombre = documento.createTextNode(a.getNombre());
				Text textoEdad = documento.createTextNode(Integer.toString(a.getEdad()));
				
				dni.appendChild(textoDni);
				nombre.appendChild(textoNombre);
				edad.appendChild(textoEdad);
				
				persona.appendChild(dni);
				persona.appendChild(nombre);
				persona.appendChild(edad);
				
				personasCont.appendChild(persona);
			}
			
			
			documento.appendChild(personasCont);
			
			DOMSource fuente = new DOMSource(documento);
			StreamResult fichero = new StreamResult(new File("xml/leerJson.xml"));

			Transformer t = TransformerFactory.newInstance().newTransformer();

			t.setOutputProperty(OutputKeys.INDENT, "yes");
			t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			
			t.transform(fuente, fichero);
			
			System.out.println("Fichero creado");
		} catch (ParserConfigurationException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
	}

}
