package Ejercicios2Parte;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

//@author Manuel Bote Zabala
public class Ejercicio37 {

	public static void main(String[] args) throws TransformerFactoryConfigurationError, TransformerException {
		// TODO Auto-generated method stub

		/*
		 * Crea un programa que nos solicite por consola el expediente, nombre del
		 * alumno y su nota hasta que le indiquemos salir. Los datos no tendrán
		 * duplicados y estarán ordenados por expediente. Para mantener la persistencia
		 * de los datos se guardarán en un fichero notasAlumno.xml con el siguiente
		 * resultado:
		 */
		
		Scanner teclado = new Scanner(System.in);
		int salir = 0;
		TreeMap<Integer, Alumno> exAlumno = new TreeMap<Integer, Alumno>();
		
		do {
			
			System.out.print("Introduce el numero de expediente del alumno: ");
			int numEx = Integer.parseInt(teclado.nextLine());
			
			if(!exAlumno.containsKey(numEx)) {
				
				System.out.print("Introduzca el nombre del alumno: ");
				String nombre = teclado.nextLine();
				
				System.out.print("Introduce la nota del alumno: ");
				double nota = Double.parseDouble(teclado.nextLine());
				
				exAlumno.put(numEx, new Alumno(numEx, nombre, nota));
				
				
			}else {
				System.out.println("El alumno ya existe");
			}
			
			System.out.println("Desea añadir otro alumno (0/1)");
			salir = Integer.parseInt(teclado.nextLine());
			
			
		}while (salir != 0);
		
		
		try {
			
			DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = factoria.newDocumentBuilder();
			
			Document documento = db.newDocument();
			documento.setXmlVersion("1.0");
			
			Element alumnos = documento.createElement("alumnos");
			
			for (Alumno a : exAlumno.values()) {
				
				Element alumno = documento.createElement("alumno");
				Element numExpediente = documento.createElement("numExpediente");
				Element nombreAlumno = documento.createElement("nombreAlumno");
				Element notaAlumno = documento.createElement("nota");
				
				Text textoNumEx = documento.createTextNode(Integer.toString(a.getNumEx()));
				Text textoNombre = documento.createTextNode(a.getNombre());
				Text textoNota = documento.createTextNode(Double.toString(a.getNota()));
				
				numExpediente.appendChild(textoNumEx);
				nombreAlumno.appendChild(textoNombre);
				notaAlumno.appendChild(textoNota);
				
				alumno.appendChild(numExpediente);
				alumno.appendChild(nombreAlumno);
				alumno.appendChild(notaAlumno);
				
				alumnos.appendChild(alumno);
				
			}
			
			
			documento.appendChild(alumnos);
			
			DOMSource fuente = new DOMSource(documento);
			StreamResult fichero = new StreamResult(new File("xml/notasAlumno.xml"));

			Transformer t = TransformerFactory.newInstance().newTransformer();

			t.setOutputProperty(OutputKeys.INDENT, "yes");
			t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			
			t.transform(fuente, fichero);
			
			System.out.println("Fichero creado");
			
		} catch (ParserConfigurationException e) {
			// TODO: handle exception
			e.getStackTrace();
		}
		
		

	}

}
