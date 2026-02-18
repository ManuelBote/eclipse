package SistemaAlumno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SistemaaAlumnosImplementacionDAO implements SistemaAlumnosDAO{

    private static final String DB_TYPE = "mysql";

    // ================== ALUMNO ==================

    @Override
    public void insertarA(Alumno alumno) {
        String sql = "INSERT INTO alumno (nombre, apellido, email) VALUES (?,?,?)";
        try (Connection conn = ConexionBD.getConexion(DB_TYPE);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, alumno.getNombre());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getEmail());
            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    
    @Override
    public Alumno obtenerPorIdA(int id_alumno) {
        String sql = "SELECT * FROM alumno WHERE id_alumno = ?";
        Alumno a = null;

        try (Connection conn = ConexionBD.getConexion(DB_TYPE);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id_alumno);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                a = new Alumno(
                        rs.getInt("id_alumno"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("email")
                );
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return a;
    }

    
    @Override
    public List<Alumno> listarA() {
        List<Alumno> lista = new ArrayList<>();
        String sql = "SELECT * FROM alumno";

        try (Connection conn = ConexionBD.getConexion(DB_TYPE);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Alumno a = new Alumno(
                        rs.getInt("id_alumno"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("email")
                );
                lista.add(a);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    
    @Override
    public void modificarA(Alumno alumno) {
        String sql = "UPDATE alumno SET nombre = ?, apellido = ?, email = ? WHERE id_alumno = ?";
        try (Connection conn = ConexionBD.getConexion(DB_TYPE);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, alumno.getNombre());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getEmail());
            ps.setInt(4, alumno.getId_alumno());
            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    
    @Override
    public void eliminarA(int id_alumno) {
        String sql = "DELETE FROM alumno WHERE id_alumno = ?";
        try (Connection conn = ConexionBD.getConexion(DB_TYPE);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id_alumno);
            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    
    
    // ================== CURSO ==================

    @Override
    public void insertarC(Curso curso) {
        String sql = "INSERT INTO curso (nombre, horas, descripcion) VALUES (?,?,?)";
        try (Connection conn = ConexionBD.getConexion(DB_TYPE);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, curso.getNombre());
            ps.setInt(2, curso.getHoras());
            ps.setString(3, curso.getDescripcion());
            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    
    @Override
    public Curso obtenerPorIdC(int id_curso) {
        String sql = "SELECT * FROM curso WHERE id_curso = ?";
        Curso c = null;

        try (Connection conn = ConexionBD.getConexion(DB_TYPE);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id_curso);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                c = new Curso(
                        rs.getInt("id_curso"),
                        rs.getString("nombre"),
                        rs.getInt("horas"),
                        rs.getString("descripcion")
                );
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return c;
    }
    

    @Override
    public List<Curso> listarC() {
        List<Curso> lista = new ArrayList<>();
        String sql = "SELECT * FROM curso";

        try (Connection conn = ConexionBD.getConexion(DB_TYPE);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Curso c = new Curso(
                        rs.getInt("id_curso"),
                        rs.getString("nombre"),
                        rs.getInt("horas"),
                        rs.getString("descripcion")
                );
                lista.add(c);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
    

    @Override
    public void modificarC(Curso curso) {
        String sql = "UPDATE curso SET nombre = ?, horas = ?, descripcion = ? WHERE id_curso = ?";
        try (Connection conn = ConexionBD.getConexion(DB_TYPE);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, curso.getNombre());
            ps.setInt(2, curso.getHoras());
            ps.setString(3, curso.getDescripcion());
            ps.setInt(4, curso.getId_curso());
            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    

    @Override
    public void eliminarC(int id_curso) {
        String sql = "DELETE FROM curso WHERE id_curso = ?";
        try (Connection conn = ConexionBD.getConexion(DB_TYPE);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id_curso);
            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
	
}
