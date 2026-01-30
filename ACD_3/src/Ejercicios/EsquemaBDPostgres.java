package Ejercicios;

// Esquema general de trabajo con PostgreSQL y arrays 1D desde Java
// (usa JDBC + tipos ARRAY + funciones almacenadas)

import java.sql.*;
import java.util.Arrays;

public class EsquemaBDPostgres {

    /*
     * 1. MÉTODOS ESTÁTICOS DE UTILIDAD (conexión, cierre, etc.)
     */

    // Conectar a la BD
    // PostgreSQL: Driver org.postgresql.Driver y URL jdbc:postgresql://localhost:5432/persona2 [file:1]
    public static Connection obtenerConexion() throws SQLException {
        // Datos ejemplo de los apuntes
        String url = "jdbc:postgresql://localhost:5432/persona2"; // BD persona/persona2 [file:1]
        String user = "postgres";
        String pass = "toor";
        return DriverManager.getConnection(url, user, pass);
    }

    // Cargar driver (opcional en versiones modernas)
    // PostgreSQL: Class.forName("org.postgresql.Driver"); [file:1]
    public static void cargarDriver() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
     * 2. MÉTODOS CRUD CON STATEMENT / PREPAREDSTATEMENT
     */

    // INSERT sencillo (persona sin arrays)
    // PostgreSQL:
    //  CREATE TABLE persona (
    //      dni    VARCHAR(9) PRIMARY KEY,
    //      nombre TEXT,
    //      edad   INT
    //  ); [file:1]
    //  INSERT INTO persona (dni, nombre, edad)
    //  VALUES ('12345678A','Juan Pérez',30); [file:1]
    public static void insertarPersonaSimple() {
        String sql = "INSERT INTO persona (dni, nombre, edad) VALUES (?, ?, ?)";

        try (Connection conn = obtenerConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, "12345678A");
            pst.setString(2, "Juan Pérez");
            pst.setInt(3, 30);

            pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // INSERT con ARRAY de teléfonos
    // PostgreSQL:
    //  CREATE TABLE persona2 (
    //      dni       varchar(9) PRIMARY KEY,
    //      nombre    text,
    //      edad      int,
    //      telefonos text[]
    //  ); [file:1]
    //  INSERT INTO persona2 (dni, nombre, edad, telefonos)
    //  VALUES ('12345679B','Ana Gómez',30, ARRAY['912345678','678901234']); [file:1]
    public static void insertarPersonaConArray() {
        String sql = "INSERT INTO persona2 (dni, nombre, edad, telefonos) VALUES (?, ?, ?, ?)";

        try (Connection conn = obtenerConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            String dni = "12345679B";
            String nombre = "Ana Gómez";
            int edad = 30;
            String[] telefonos = { "912345678", "678901234" };

            // JDBC: conn.createArrayOf("text", telefonos) [file:1][file:2]
            Array telefonosArray = conn.createArrayOf("text", telefonos);

            pst.setString(1, dni);
            pst.setString(2, nombre);
            pst.setInt(3, edad);
            pst.setArray(4, telefonosArray);

            pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // SELECT con arrays (recuperar y convertir a String[])
    // PostgreSQL:
    //  SELECT dni, nombre, edad, telefonos FROM persona2; [file:1]
    public static void mostrarPersonasConArray() {
        String sql = "SELECT dni, nombre, edad, telefonos FROM persona2";

        try (Connection conn = obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String dni = rs.getString("dni");
                String nombre = rs.getString("nombre");
                int edad = rs.getInt("edad");

                // JDBC: ResultSet.getArray("telefonos") [file:1][file:2]
                Array telefonosArray = rs.getArray("telefonos");
                String[] telefonos = (String[]) telefonosArray.getArray(); // [file:1][file:2]

                System.out.println("DNI: " + dni);
                System.out.println("Nombre: " + nombre);
                System.out.println("Edad: " + edad);
                System.out.println("Teléfonos: " + Arrays.toString(telefonos));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // UPDATE: añadir teléfono con array_append
    // PostgreSQL:
    //  UPDATE persona2
    //  SET telefonos = array_append(telefonos, '123123123')
    //  WHERE dni = '12345679B'; [file:1][file:2]
    public static void anyadirTelefono(String dni, String nuevoTelefono) {
        String sql = "UPDATE persona2 " +
                     "SET telefonos = array_append(telefonos, ?) " +
                     "WHERE dni = ?";

        try (Connection conn = obtenerConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, nuevoTelefono);
            pst.setString(2, dni);
            pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // UPDATE: eliminar teléfono con array_remove
    // PostgreSQL:
    //  UPDATE persona2
    //  SET telefonos = array_remove(telefonos, '678901234')
    //  WHERE dni = '12345679B'; [file:1][file:2]
    public static void eliminarTelefono(String dni, String telefonoAEliminar) {
        String sql = "UPDATE persona2 " +
                     "SET telefonos = array_remove(telefonos, ?) " +
                     "WHERE dni = ?";

        try (Connection conn = obtenerConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, telefonoAEliminar);
            pst.setString(2, dni);
            pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // UPDATE: reemplazar valores en array con array_replace
    // PostgreSQL:
    //  UPDATE persona2
    //  SET telefonos = array_replace(telefonos,'viejo','nuevo')
    //  WHERE dni = '...'; [file:1][file:2]
    public static void reemplazarTelefono(String dni, String viejo, String nuevo) {
        String sql = "UPDATE persona2 " +
                     "SET telefonos = array_replace(telefonos, ?, ?) " +
                     "WHERE dni = ?";

        try (Connection conn = obtenerConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, viejo);
            pst.setString(2, nuevo);
            pst.setString(3, dni);
            pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // SELECT con ANY(...) para buscar un valor dentro del array
    // PostgreSQL:
    //  SELECT * FROM persona2
    //  WHERE '912345678' = ANY(telefonos); [file:2]
    public static void buscarPorTelefono(String telefono) {
        String sql = "SELECT dni, nombre FROM persona2 WHERE ? = ANY(telefonos)";

        try (Connection conn = obtenerConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, telefono);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    System.out.println("DNI: " + rs.getString("dni") +
                                       " Nombre: " + rs.getString("nombre"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * 3. MÉTODOS QUE LLAMAN A FUNCIONES / PROCEDIMIENTOS ALMACENADOS
     */

    // Función almacenada PostgreSQL (apuntes):
    //  CREATE OR REPLACE FUNCTION contarpersonas(nom VARCHAR)
    //  RETURNS INTEGER
    //  LANGUAGE plpgsql AS
    //  $$
    //  DECLARE total INTEGER;
    //  BEGIN
    //      SELECT COUNT(*) INTO total
    //      FROM persona
    //      WHERE persona.nombre = nom;
    //      RETURN total;
    //  END;
    //  $$; [file:1]
    public static int contarPersonasPorNombre(String nombre) {
        int resultado = 0;

        // Llamada JDBC: { ? = call contarpersonas(?) } [file:1]
        String sql = "{ ? = call contarpersonas(?) }";

        try (Connection conn = obtenerConexion();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.registerOutParameter(1, Types.INTEGER);
            stmt.setString(2, nombre);

            stmt.execute();
            resultado = stmt.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado;
    }

    // Procedimiento almacenado PostgreSQL:
    //  CREATE OR REPLACE PROCEDURE saludo()
    //  LANGUAGE plpgsql AS
    //  $$
    //  BEGIN
    //      RAISE NOTICE 'Hola';
    //  END;
    //  $$; [file:1]
    public static void llamarProcedimientoSaludo() {
        // Llamada JDBC a procedimiento: CALL saludo(); [file:1]
        String sql = "{ call saludo() }";

        try (Connection conn = obtenerConexion();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * 4. ARRAYS EN JAVA (lado cliente, sin BD)
     */

    // Repaso Arrays en Java:
    //  String[] roles = new String[3];
    //  roles[0] = "ADMIN";
    //  roles[1] = "USER";
    //  roles[2] = "EDITOR";
    //
    //  String[] roles2 = { "ADMIN", "USER", "EDITOR" }; [file:2]
    public static void ejemploArraysJava() {
        String[] roles = { "MODERATOR", "USER", "ADMIN" };

        // Arrays.sort(roles) ordena el array [file:2]
        Arrays.sort(roles);
        // Arrays.toString(roles) lo convierte en una cadena [file:2]
        System.out.println(Arrays.toString(roles)); // [ADMIN, MODERATOR, USER] [file:2]
    }

    public static void main(String[] args) {
        // cargarDriver();
        // insertarPersonaSimple();
        // insertarPersonaConArray();
        // mostrarPersonasConArray();
        // anyadirTelefono("12345679B", "123123123");
        // eliminarTelefono("12345679B", "678901234");
        // reemplazarTelefono("12345679B", "912345678", "600000000");
        // buscarPorTelefono("600000000");
        // System.out.println(contarPersonasPorNombre("Juan"));
        // llamarProcedimientoSaludo();
        // ejemploArraysJava();
    }
}
