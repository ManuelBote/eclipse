package CRUDMongoDB;

import java.util.Iterator;
import java.util.Scanner;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;


public class Principal {

	public static  MongoCollection<Document> cAlumnos;
	public static MongoCollection<Document> cCursos;
	public static Scanner tc = new Scanner(System.in);
	public static String uri = "mongodb+srv://mbotez01:mgWx3bbpWR17yYti@cluster1.gfqs9.mongodb.net/?appName=Cluster1";
    
	public static void main(String[] args) {
		MongoClient clienteMongo = MongoClients.create(uri);	
        MongoDatabase database = clienteMongo.getDatabase("SistemaAlumnoDB");
        cAlumnos = database.getCollection("alumnos");
        cCursos = database.getCollection("cursos");
        
        while (true) {
            System.out.println("\n=== SISTEMA DE ALUMNOS Y CURSOS ===");
            System.out.println("[1] Crear Alumno");
            System.out.println("[2] Crear Curso");
            System.out.println("[3] Obtener todos los Alumnos");
            System.out.println("[4] Obtener todos los Cursos");
            System.out.println("[5] Obtener Alumno por ID");
            System.out.println("[6] Obtener Curso por ID");
            System.out.println("[7] Modificar Alumno por ID");
            System.out.println("[8] Modificar Curso por ID");
            System.out.println("[9] Eliminar Alumno por ID");
            System.out.println("[10] Eliminar Curso por ID");
            System.out.println("[0] Salir");
            System.out.print("Elige una opción: ");

            int opcion = tc.nextInt(); tc.nextLine();

            switch (opcion) {
                case 1 -> crearAlumno();
                case 2 -> crearCurso();
                case 3 -> listarAlumnos();
                case 4 -> listarCursos();
                case 5 -> obtenerAlumnoPorId();
                case 6 -> obtenerCursoPorId();
                case 7 -> modificarAlumno();
                case 8 -> modificarCurso();
                case 9 -> eliminarAlumno();
                case 10 -> eliminarCurso();
                case 0 -> {
                    System.out.println("Saliendo...");
                    clienteMongo.close();
                    return;
                }
                default -> System.out.println("Opción inválida.");
            }
        }

	}
	
	public static void crearAlumno() {
	    System.out.print("Nombre: ");
	    String nombre = tc.nextLine();
	    System.out.print("Apellido: ");
	    String apellido = tc.nextLine();
	    System.out.print("Email: ");
	    String email = tc.nextLine();
	
	    Document doc = new Document("nombre", nombre)
	            .append("apellido", apellido)
	            .append("email", email);
	    InsertOneResult result = cAlumnos.insertOne(doc);
	    System.out.println("Alumno creado con ID: " + result.getInsertedId().asObjectId().getValue());
	}
	
	 public static void crearCurso() {
	        System.out.print("Nombre: ");
	    String nombre = tc.nextLine();
	    System.out.print("Horas: ");
	    int horas = tc.nextInt();
	    tc.nextLine();
	    System.out.print("Descripción: ");
	    String descripcion = tc.nextLine();
	
	    Document doc = new Document("nombre", nombre)
	            .append("horas", horas)
	            .append("descripcion", descripcion);
	    InsertOneResult result = cCursos.insertOne(doc);
	    System.out.println("Curso creado con ID: " + result.getInsertedId().asObjectId().getValue());
	}
	
	public static void listarAlumnos() {
	    System.out.println("\n--- ALUMNOS ---");
	    Iterator<Document> iterator = cAlumnos.find().iterator();
	    if (!iterator.hasNext()) {
	        System.out.println("No hay alumnos.");
	        return;
	    }
	    while (iterator.hasNext()) {
	        Document doc = iterator.next();
	        System.out.println("ID: " + doc.getObjectId("_id") +
	                ", Nombre: " + doc.getString("nombre") +
	                " " + doc.getString("apellido") +
	                ", Email: " + doc.getString("email"));
	    }
	}
	
	public static void listarCursos() {
	    System.out.println("\n--- CURSOS ---");
	    Iterator<Document> iterator = cCursos.find().iterator();
	    if (!iterator.hasNext()) {
	        System.out.println("No hay cursos.");
	        return;
	    }
	    while (iterator.hasNext()) {
	        Document doc = iterator.next();
	        System.out.println("ID: " + doc.getObjectId("_id") +
	                ", Nombre: " + doc.getString("nombre") +
	                ", Horas: " + doc.getInteger("horas") +
	                ", Descripción: " + doc.getString("descripcion"));
	    }
	}
	
	public static void obtenerAlumnoPorId() {
	    System.out.print("ID del alumno (ObjectId): ");
	    String idStr = tc.nextLine();
	    try {
	        Document doc = cAlumnos.find(Filters.eq("_id", new ObjectId(idStr))).first();
	        if (doc != null) {
	            System.out.println("Alumno: " + doc.toJson());
	        } else {
	            System.out.println("Alumno no encontrado.");
	        }
	    } catch (IllegalArgumentException e) {
	        System.out.println("ID inválido.");
	    }
	}
	
	public static void obtenerCursoPorId() {
	    System.out.print("ID del curso (ObjectId): ");
	    String idStr = tc.nextLine();
	    try {
	        Document doc = cCursos.find(Filters.eq("_id", new ObjectId(idStr))).first();
	        if (doc != null) {
	            System.out.println("Curso: " + doc.toJson());
	        } else {
	            System.out.println("Curso no encontrado.");
	        }
	    } catch (IllegalArgumentException e) {
	        System.out.println("ID inválido.");
	    }
	}
	
	public static void modificarAlumno() {
	    System.out.print("ID del alumno: ");
	    String idStr = tc.nextLine();
	    try {
	        ObjectId id = new ObjectId(idStr);
	        System.out.print("Nuevo nombre: ");
	        String nombre = tc.nextLine();
	        System.out.print("Nuevo apellido: ");
	        String apellido = tc.nextLine();
	        System.out.print("Nuevo email: ");
	        String email = tc.nextLine();
	
	        Document updateDoc = new Document("nombre", nombre)
	                .append("apellido", apellido)
	                .append("email", email);
	        UpdateResult result = cAlumnos.replaceOne(Filters.eq("_id", id), updateDoc);
	        System.out.println("Modificados: " + result.getModifiedCount());
	    } catch (IllegalArgumentException e) {
	        System.out.println("ID inválido.");
	    }
	}
	
	public static void modificarCurso() {
	    System.out.print("ID del curso: ");
	    String idStr = tc.nextLine();
	    try {
	        ObjectId id = new ObjectId(idStr);
	        System.out.print("Nuevo nombre: ");
	        String nombre = tc.nextLine();
	        System.out.print("Nuevas horas: ");
	        int horas = tc.nextInt();
	        tc.nextLine();
	        System.out.print("Nueva descripción: ");
	        String descripcion = tc.nextLine();
	
	        Document updateDoc = new Document("nombre", nombre)
	                .append("horas", horas)
	                .append("descripcion", descripcion);
	        UpdateResult result = cCursos.replaceOne(Filters.eq("_id", id), updateDoc);
	        System.out.println("Modificados: " + result.getModifiedCount());
	    } catch (IllegalArgumentException e) {
	        System.out.println("ID inválido.");
	    }
	}
	
	public static void eliminarAlumno() {
	    System.out.print("ID del alumno: ");
	    String idStr = tc.nextLine();
	    try {
	        DeleteResult result = cAlumnos.deleteOne(Filters.eq("_id", new ObjectId(idStr)));
	        System.out.println("Eliminados: " + result.getDeletedCount());
	    } catch (IllegalArgumentException e) {
	        System.out.println("ID inválido.");
	    }
	}
	
	public static void eliminarCurso() {
	    System.out.print("ID del curso: ");
	    String idStr = tc.nextLine();
	    try {
	        DeleteResult result = cCursos.deleteOne(Filters.eq("_id", new ObjectId(idStr)));
	        System.out.println("Eliminados: " + result.getDeletedCount());
	    } catch (IllegalArgumentException e) {
	        System.out.println("ID inválido.");
	    }
	}
	

}
