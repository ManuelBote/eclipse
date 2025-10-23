package Ejercicios2Parte;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;

public class EjemploLeerXML {
	
	
	public static void recorrerRamaDom(Node nodo, int nivel) {
		
		if(nodo !=null && nodo.getNodeType() == Node.ELEMENT_NODE) {
			
			String indentacion = " ".repeat(nivel * 4); //Cada nivel añade 4 espacios
			
			//Mostrar el nombre dle nodo
			System.out.println(indentacion + "Nombre: " + nodo.getNodeName());
			
			//Obtener los hijos del nodo
			NodeList hijos = nodo.getChildNodes();
			
			//Recorrer los nodos hijos
			for(int i = 0; i < hijos.getLength(); i++) {
				Node nodoNieto = hijos.item(i);
				recorrerRamaDom(nodoNieto, nivel + 1);
			}
						
		}
		
	}
	
	

	public static void main(String[] args) {
		

		try {
			//Crear un nuevo objeto DocumentBuilderFactory
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			
			//Crear un DocumenteBuilder
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			//Cargar el archivo XML
			File archivoXML = new File("xml/personas.xml");
			Document documento = builder.parse(archivoXML);
			
			//Obtener el elemento raiz
			Element raiz = documento.getDocumentElement();
			
			
			//Mostrar los nodos recursivamente
			recorrerRamaDom(raiz,0);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		}

	}

}
