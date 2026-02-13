package SistemaAlumno;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
	private static final String MYSQL_URL  = "jdbc:mysql://localhost:3306/bdsisalumnos";
    private static final String MYSQL_USER = "root";
    private static final String MYSQL_PASS = "";

    private static final String POSTGRES_URL  = "jdbc:postgresql://localhost:5432/BDalumnos";
    private static final String POSTGRES_USER = "postgres";
    private static final String POSTGRES_PASS = "toor";

    public static Connection getConexion(String dbType) throws SQLException {
        Connection conexion = null;
        if (dbType.equalsIgnoreCase("mysql")) {
            conexion = DriverManager.getConnection(MYSQL_URL, MYSQL_USER, MYSQL_PASS);
        } else if (dbType.equalsIgnoreCase("postgres")) {
            conexion = DriverManager.getConnection(POSTGRES_URL, POSTGRES_USER, POSTGRES_PASS);
        } else {
            throw new SQLException("Tipo de base de datos no soportado: " + dbType);
        }
        return conexion;
    }
}
