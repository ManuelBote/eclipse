package JAXB_Ejercicios;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class Ej43 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		File archivo = new File("xml/libreriaEj42.xml");
		
		if(archivo.exists()) {
			
			try {
				
				JAXBContext jaxbcontext = JAXBContext.newInstance(Libreria.class);
				Unmarshaller um = jaxbcontext.createUnmarshaller();
				
				Libreria lib = (Libreria) um.unmarshal(archivo);
				
				System.out.println("Libreria " + lib.getNombre() + ", " + lib.getLugar() + " " + lib.getCp());
				System.out.println("Lista de libros: ");
				
				for(int i = 0; i < lib.getLibro().size(); i++) {
					System.out.println("Libro " + (i+1) + "\t\tTitulo: " + lib.getLibro().get(i).getTitulo()
							+ "\tAutor: " + lib.getLibro().get(i).getAutor()
							+ "\tEditorial: " + lib.getLibro().get(i).getEditorial()
							+ "\tISBN: " + lib.getLibro().get(i).getIsbn());
				}
				
			} catch (JAXBException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}

	}

}
