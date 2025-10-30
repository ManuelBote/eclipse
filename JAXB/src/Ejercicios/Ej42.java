package Ejercicios;

import java.io.File;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class Ej42 {

	public static void main(String[] args) {

		/*
		 * El programa pedirá al usuario, los datos de la librería y de los libros de la
		 * librería. Utilizando JAXB, crea un fichero XML con la estructura siguiente.
		 */
		Scanner sc = new Scanner(System.in);
		
		Libreria lib = new Libreria();
		
		System.out.println("Introduce el nombre de la libreria: ");
		String nombreLibreria = sc.nextLine();
		System.out.println("Introduce el luegar de la libreria: ");
		String lugar = sc.nextLine();
		System.out.println("Introduce el codigo postal de la libreria: ");
		String cp = sc.nextLine();

		System.out.println("\n.....................................\n");
		
		int cond = 0;
		do {
			
			System.out.println("Introduce el titulo del libro: ");
			String titulo = sc.nextLine();
			System.out.println("Introduce el nombre del autor del libro: ");
			String autor = sc.nextLine();
			System.out.println("Introduce la editorial del libro: ");
			String editor = sc.nextLine();
			System.out.println("Introduce el isbn del libro: ");
			int isbn = Integer.parseInt(sc.nextLine());
			
			lib.aniadirLibro(new Libro(titulo, autor, editor, isbn));
			
			System.out.println("Deseea añadir otro libro (0/1)");
			cond = Integer.parseInt(sc.nextLine());
			 	
		}while(cond != 0);
		
		
		lib.setNombre(nombreLibreria);
		lib.setLugar(lugar);
		lib.setCp(cp);
		
		lib.mostrarLibros();
		System.out.println("\n.....................................\n");
		
		try {
			
			JAXBContext jaxbContext =  JAXBContext.newInstance(Libreria.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			
			marshaller.marshal(lib, System.out);
			marshaller.marshal(lib, new File("xml/libreriaEj42.xml"));
			
		} catch (JAXBException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
