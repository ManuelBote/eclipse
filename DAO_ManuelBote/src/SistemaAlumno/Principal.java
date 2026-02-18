package SistemaAlumno;

import java.util.List;

public class Principal {

	public static void main(String[] args) {

        SistemaAlumnosDAO dao = new SistemaaAlumnosImplementacionDAO();

        // Insertar alumno
        Alumno a = new Alumno();
        a.setNombre("Luis");
        a.setApellido("Paco");
        a.setEmail("luispaco@gmail.com");
        dao.insertarA(a);

        // Insertar curso
        Curso c = new Curso();
        c.setNombre("Bases de Datos");
        c.setHoras(100);
        c.setDescripcion("SQL");
        dao.insertarC(c);

        // Listar alumnos
        List<Alumno> alumnos = dao.listarA();
        for (Alumno al : alumnos) {
            System.out.println(al.getId_alumno() + " - " + al.getNombre());
        }

        // Obtener y modificar un curso
        Curso cursoBD = dao.obtenerPorIdC(1);
        if (cursoBD != null) {
            cursoBD.setHoras(120);
            dao.modificarC(cursoBD);
        }

        // Eliminar alumno
        dao.eliminarA(2);
    }
	
	
}
