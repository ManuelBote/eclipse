package Ejercicios2Parte;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;

public class EjemploLeerPersonasXML {
	
	
	public static void mostrarNodos(Node elemento, int nivel) {
		
		//Mostrar nombre del elemento
		for (int i = 0; i < nivel; i++) {
			System.out.print("\t");
		}
		System.out.print("Nodo: " + elemento.getNodeName());
		
		//Mostrar atributos
		if(elemento.hasAttributes()) {
			NamedNodeMap atri = elemento.getAttributes();
			
			for(int i = 0; i < atri.getLength(); i++) {
				System.out.print("\tAtributo: " + atri.item(i).getNodeName() + ": " + atri.item(i).getNodeValue());
				
			}
		}
		
		System.out.println();  //Salto de linea
		
		//Obtener hijos del elemento
		NodeList hijos = elemento.getChildNodes();
		
		//Recorrer nodos hijos
		for (int i = 0; i < hijos.getLength(); i++) {
			Node nodo = hijos.item(i);
			
			//Mostrar contenido si es un nodo de texto que no esta vacio
			if(nodo.getNodeType() == Node.TEXT_NODE && !nodo.getNodeValue().trim().isEmpty()) {
				
				//Mostrar nombre del elemento
				for (int j = 0; j < nivel; j++) {
					System.out.print("\t");
				}
				System.out.println("Contenido: " + nodo.getNodeValue().trim());
				
			}
			
			//SI es un nodo de tipo ELEMENT_NODE
			if(nodo.getNodeType() == Node.ELEMENT_NODE) {
				mostrarNodos(nodo, nivel + 1);
			}
		}
		
	}
	
	

	public static void main(String[] args) {
		
		try {
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			//CArgar el archivo XML
			File archivoXML = new File("xml/personas.xml");
			Document documento = builder.parse(archivoXML);
			
			//Normalizar documento para elimiar espacios vacios
			documento.getDocumentElement().normalize();
			
			//Obtener raiz
			Element raiz = documento.getDocumentElement();
			
			//Mostrar los nodos recursivamente
			mostrarNodos(raiz, 0);
			
		} catch(Exception e ) {
			e.printStackTrace();
		}
		
	}

}
