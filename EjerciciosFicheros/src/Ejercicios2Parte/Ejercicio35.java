package Ejercicios2Parte;

import java.io.File;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

public class Ejercicio35 {

	public static void main(String[] args) throws TransformerException {

		/**
		 * Crea un documento DOM vacío, añade un nodo raíz llamado profesores, un nodo
		 * elemento llamado profesor con un atributo grupo 2DAM y un nodo texto. Muestra
		 * el resultado por consola.
		 */
		
		try {
			
			DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = factoria.newDocumentBuilder();
			
			Document documento = db.newDocument();
			documento.setXmlVersion("1.0");
			
			Element elemento = documento.createElement("profesores");
			Element elemento1 = documento.createElement("profesor");
			
			documento.appendChild(elemento);
			elemento.appendChild(elemento1);
			
			
			elemento1.setAttribute("grupo", "2DAM");
			
			Text texto = documento.createTextNode("Juan Perez");
			elemento1.appendChild(texto);
			
			
			DOMSource fuente = new DOMSource(documento);

			StreamResult consola = new StreamResult(System.out);

			Transformer t = TransformerFactory.newInstance().newTransformer();
			
			t.setOutputProperty(OutputKeys.INDENT, "yes");
			t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			
			t.transform(fuente, consola);
			
			
		} catch (ParserConfigurationException e) {
			// TODO: handle exception
		}

	}

}
