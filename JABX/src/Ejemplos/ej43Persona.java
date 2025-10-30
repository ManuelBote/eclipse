package Ejemplos;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class ej43Persona {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Personas persona1 = new Personas();// ArrayList de persona
		persona1.aniadirPersona(new Persona("123123", "Paco", 45));
		persona1.aniadirPersona(new Persona("666666", "Luis", 33));
		persona1.mostrarPersonas();
		System.out.println("...................");
		try {
			// Crear contexto JAXB
			JAXBContext jaxbContext = JAXBContext.newInstance(Personas.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			// Configuración opcional para formato legible
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			// Convertir objeto a XML y mostrar en consola
			marshaller.marshal(persona1, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

}
