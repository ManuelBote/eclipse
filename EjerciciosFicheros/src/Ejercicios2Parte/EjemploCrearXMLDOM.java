package Ejercicios2Parte;

import java.io.File;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

public class EjemploCrearXMLDOM {

	public static void main(String[] args) throws TransformerException {

		try {
			
			DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = factoria.newDocumentBuilder();
			
			//Creamos DOM vacio
			Document documento = db.newDocument();
			documento.setXmlVersion("1.0");
			
			//Creamos elementos y añadimos al DOM
			Element elemento = documento.createElement("raiz");
			Element elemento1 = documento.createElement("hoja");
			
			//Añadimos elemento raiz al documento
			documento.appendChild(elemento);
			elemento.appendChild(elemento1);
			
			//Añadimos atributos a elemento
			elemento1.setAttribute("nombre", "valor");
			
			
			//1. Crear una fuente/oritgen  con el arbol DOM
			DOMSource fuente = new DOMSource(documento);
			
			//2. Crear el destino de la transformacion
			StreamResult ficheroXML = new StreamResult(new File("xml/salidaVacio.xml"));
			//2.1. Si queremos mostrar por consola StreamResult consola = new StreamResult(System.out);
			StreamResult consola = new StreamResult(System.out);
			
			//3. Crear un transformador:
			Transformer t = TransformerFactory.newInstance().newTransformer();
			
			//4. Mejoramos el forma
			t.setOutputProperty(OutputKeys.INDENT, "yes");
			t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			
			//5. Transformar la fuente en el resultado
			t.transform(fuente, ficheroXML);
			//5.1. Si queremos mostrar por consola t.transform(fuente, consola);
			t.transform(fuente, consola);
			
			
		} catch (ParserConfigurationException e) {
			// TODO: handle exception
		}
		
	}

}
