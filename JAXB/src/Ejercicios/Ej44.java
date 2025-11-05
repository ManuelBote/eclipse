package Ejercicios;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Ej44 {
	
	static Scanner tc = new Scanner(System.in);
	static Empresa emp = null;
	static HashMap<Integer, Trabajador> trab = new HashMap<Integer, Trabajador>();

	public static void main(String[] args) {
		
		File archivo = new File("xml/empresa.xml");
		int cond = 0;
		
		//Comprobar si existen datos para cargarlos
		if(archivo.exists()) {
			
			try {
				JAXBContext contexto = JAXBContext.newInstance(Empresa.class);
				Unmarshaller um = contexto.createUnmarshaller();
				emp = (Empresa) um.unmarshal(archivo);	
				
				for (int i = 0; i < emp.getTrabajadores().size(); i++) {
					trab.put(emp.getTrabajadores().get(i).getNif(), emp.getTrabajadores().get(i));
				}
				
				System.out.println("Datos cargados");
				
			} catch (JAXBException e) {
				// TODO: handle exception
				e.printStackTrace();
			}	
			
		}else {
			
			System.out.print("Introduce el NIE de la empresa: ");
			int nie = Integer.parseInt(tc.nextLine());
			System.out.print("Introduce el nombre de la empresa: ");
			String nombreEmp = tc.nextLine();
			
			//Datos de direccion
			System.out.print("Introduce la calle de la empresa: ");
			String calle = tc.nextLine();
			System.out.print("Introduce el numero de direccion de la empresa: ");
			int num = Integer.parseInt(tc.nextLine());
			System.out.print("Introduce la localidad de la empresa: ");
			String localidad = tc.nextLine();
			System.out.print("Introduce el codigo postal de la empresa: ");
			int cp = Integer.parseInt(tc.nextLine());
			
			emp = new Empresa(nie, nombreEmp, new Direccion(calle, localidad, num, cp), new ArrayList<Trabajador>());
			
			
		}
		
		do {
			
			System.out.println("\n===========================");
			System.out.println("[1] Ver datos de la empresa");
			System.out.println("[2] Ver trabajadores");
			System.out.println("[3] Añadir trabajadores");
			System.out.println("[4] Modificar trabajador");
			System.out.println("[5] Borrar trabajador");
			System.out.println("[0] Salir");
			System.out.println("===========================");
			System.out.print("Selecciona una opcion: ");
			cond = Integer.parseInt(tc.nextLine());
			
			switch(cond) {
			
				case 0:
					break;
			
				case 1:
					verDatosEmpresa();
					break;
					
				case 2:
					break;
					
				case 3:
					break;
					
				case 4:
					break;
					
				case 5:
					break;
					
				default:
					System.out.println("Seleccione una opcion correcta");
					break;
			
			}
			
		}while (cond != 0);
		

	}
	
	
	public static void verDatosEmpresa() {
		
		try {
			
			JAXBContext jaxbContext = JAXBContext.newInstance(Empresa.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
		
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(emp, System.out);
		
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
	}

}
