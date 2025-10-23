package Ejercicios2Parte;

import java.io.File;

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

public class Ejercicio36 {

	public static void main(String[] args) throws TransformerFactoryConfigurationError, TransformerException {
		// TODO Auto-generated method stub
		
		/**
		 * Completa el ejercicio anterior guardando el DOM como fichero XML
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

			StreamResult fichero = new StreamResult(new File("xml/ejercicio36.xml"));

			Transformer t = TransformerFactory.newInstance().newTransformer();

			t.setOutputProperty(OutputKeys.INDENT, "yes");
			t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

			t.transform(fuente, fichero);

		} catch (ParserConfigurationException e) {
			// TODO: handle exception
		}

	}

}
