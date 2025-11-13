package Esquemas;

import java.io.*;
import java.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;

// JAXB imports
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

public class Ejercicio {
	private static Scanner scanner = new Scanner(System.in);
	private static final ObjectMapper mapper = new ObjectMapper();

	public static void main(String[] args) {
		int opcion;

		do {
			imprimirMenu();
			opcion = scanner.nextInt();
			scanner.nextLine();

			switch (opcion) {
			case 1 -> ejercicio1();
			case 2 -> ejercicio2();
			case 3 -> ejercicio3();
			case 4 -> ejercicio4();
			case 5 -> ejercicio5();
			case 6 -> System.out.println("Saliendo...");
			default -> System.out.println("Opción no válida.");
			}
			System.out.println();
		} while (opcion != 6);

		scanner.close();
	}

	private static void imprimirMenu() {
		System.out.println("===== MENÚ PRINCIPAL =====");
		System.out.println("1) Ejercicio 1 - Descripción breve");
		System.out.println("2) Ejercicio 2 - Descripción breve");
		System.out.println("3) Ejercicio 3 - Descripción breve");
		System.out.println("4) Ejercicio 4 - Descripción breve");
		System.out.println("5) Ejercicio 5 - Descripción breve");
		System.out.println("6) Salir");
		System.out.print("Elige una opción (1-6): ");
	}

	private static void ejercicio1() {
	    System.out.println("Introduce la marca del estuche:");
	    String marca = scanner.nextLine();
	    System.out.println("Introduce el número de serie:");
	    int numSerie = scanner.nextInt();
	    scanner.nextLine();

	    List<ContenidoEstuche> contenido = new ArrayList<>();
	    int seguir;
	    do {
	        System.out.println("Marca de la regla:");
	        String mr = scanner.nextLine();
	        System.out.println("Cantidad de reglas:");
	        int cr = scanner.nextInt();
	        scanner.nextLine();
	        System.out.println("Marca del bolígrafo:");
	        String mb = scanner.nextLine();
	        System.out.println("Cantidad de bolis:");
	        int cb = scanner.nextInt();
	        scanner.nextLine();

	        contenido.add(new ContenidoEstuche(mr, cr, mb, cb));
	        System.out.println("Grefg:Sigo? 1=Sí, 0=No:");
	        seguir = scanner.nextInt();
	        scanner.nextLine();
	    } while (seguir != 0);

	    Estuche estuche1 = new Estuche(marca, numSerie, contenido);

	    try {
	        // Guardar en JSON (Jackson)
	        mapper.writerWithDefaultPrettyPrinter().writeValue(new File("estuches.json"), estuche1);

	        // Guardar en XML (JAXB Marshall)
	        JAXBContext context = JAXBContext.newInstance(Estuche.class);
	        Marshaller marshaller = context.createMarshaller();
	        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	        marshaller.marshal(estuche1, new File("estuches.xml"));

	        // Guardar en Binario
	        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("estuches.bin"))) {
	            oos.writeObject(estuche1);
	        }

	        // Guardar en TXT
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter("estuches.txt"))) {
	            writer.write(estuche1.toString());
	        }

	        // Guardar en OBJ (serialización, igual que binario)
	        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("estuches.obj"))) {
	            oos.writeObject(estuche1);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    System.out.println("¡NUEVO ESTUCHE GUARDADO!\n" + estuche1);
	}


	private static void ejercicio2() {
	    // 1. JSON - Jackson
	    try {
	        Estuche estucheJSON = mapper.readValue(new File("estuches.json"), Estuche.class); 
	        System.out.println("[JSON] " + estucheJSON);
	    } catch (Exception e) {
	        System.out.println("No se encontró el fichero JSON o es inválido.");
	    }

	    // 2. Binario (Serializable)
	    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("estuches.bin"))) {
	        Estuche estucheBin = (Estuche) ois.readObject();
	        System.out.println("[BIN] " + estucheBin);
	    } catch (Exception e) {
	        System.out.println("No se encontró el fichero binario o es inválido.");
	    }

	    // 3. TXT (texto plano)
	    try (BufferedReader reader = new BufferedReader(new FileReader("estuches.txt"))) {
	        String linea;
	        while ((linea = reader.readLine()) != null) {
	            System.out.println("[TXT] " + linea); 
	        }
	    } catch (Exception e) {
	        System.out.println("No se encontró el fichero TXT.");
	    }

	    // 4. XML - JAXB Unmarshall
	    try {
	        JAXBContext context = JAXBContext.newInstance(Estuche.class); 
	        Unmarshaller unmarshaller = context.createUnmarshaller();
	        Estuche estucheXML = (Estuche) unmarshaller.unmarshal(new File("estuches.xml"));
	        System.out.println("[XML] " + estucheXML);
	    } catch (Exception e) {
	        System.out.println("No se encontró el fichero XML o es inválido.");
	    }

	    // 5. OBJ
	    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("estuches.obj"))) {
	        Estuche estucheObj = (Estuche) ois.readObject();
	        System.out.println("[OBJ] " + estucheObj);
	    } catch (Exception e) {
	        System.out.println("No se encontró el fichero OBJ o es inválido.");
	    }
	}


	private static void ejercicio3() {
		System.out.println("Has elegido Ejercicio 3.");
	}

	private static void ejercicio4() {
		System.out.println("Has elegido Ejercicio 4.");
	}

	private static void ejercicio5() {
		System.out.println("Has elegido Ejercicio 5.");
	}
}
