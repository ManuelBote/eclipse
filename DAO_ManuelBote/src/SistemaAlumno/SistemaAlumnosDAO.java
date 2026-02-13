package SistemaAlumno;

import java.util.List;

public interface SistemaAlumnosDAO {

	// ALUMNO
	void insertarA(Alumno alumno);

	Alumno obtenerPorIdA(int id_alumno);

	List<Alumno> listarA();

	void modificarA(Alumno alumno);

	void eliminarA(int id_alumno);

	
	// CURSO
	void insertarC(Curso curso);

	Curso obtenerPorIdC(int id_curso);

	List<Curso> listarC();

	void modificarC(Curso curso);

	void eliminarC(int id_curso);

}
