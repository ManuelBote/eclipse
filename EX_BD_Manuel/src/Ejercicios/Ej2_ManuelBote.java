package Ejercicios;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Ej2_ManuelBote {
	
	private static final String URL = "jdbc:mysql://localhost:3306/bdempleados";
	private static final String USER = "root"; // Usuario
	private static final String PASSWORD = ""; // Contraseña
	
	static final Scanner tc = new Scanner(System.in);
	
	public static Connection conectar() {
		Connection conexion = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Establecer la conexión
			conexion = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Conexión exitosa a la base de datos\n");
		} catch (ClassNotFoundException e) {
			System.out.println("Error: No se encontró el driver de MySQL.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error: No se pudo conectar a la base de datos.");
			e.printStackTrace();
		}
		return conexion;
	}

	public static void main(String[] args) {
		
		int cond = 0;
		
		do {
			
			System.out.println("\n ==== Menu ==== ");
			System.out.println("[1] Empleados que cobran mas de 3000");
			System.out.println("[2] Cambiar empleado de RRHH a ventas");
			System.out.println("[3] Aniadir campo prima");
			System.out.println("[4] Rellenar campo prima");
			System.out.println("[5] Mostrar empleados especifico por salario y departamento");
			System.out.println("[6] Total empleados por departamento");
			System.out.println("[7] Borrar empleado por dni");
			System.out.println("[0] Salir");
			System.out.print("Selecciona una opcion: ");
			cond = tc.nextInt(); tc.nextLine();
			
			switch(cond) {
			case 0 -> System.out.println("Saliendo...");
			case 1 -> empleadosMas3000();
			case 2 -> cambiarDepartamento();
			case 3 -> aniadirPrima();
			case 4 -> rellenarPrima();
			case 5 -> buscarEmpleados();
			case 6 -> empleadosPorDepartamento();
			case 7 -> borrarEmpleado();
			default -> System.out.println("Opcion no valida");
			}
			
		}while(cond != 0);

	}
	
	
	// Apartado 1
	public static void empleadosMas3000() {
		Connection conexion = conectar();
		String sql = "SELECT * FROM empleados WHERE sueldo > 3000";
		try {
			Statement stmt = conexion.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			System.out.println("Empleados que cobran mas de 3000: ");
			while(rs.next()) {
				String dni = rs.getString(1);
				String nombre = rs.getString(2);
				int edad = rs.getInt(3);
				String departamento = rs.getString(4);
				int sueldo = rs.getInt(5);
				System.out.println("DNI: " + dni + ", Nombre: " + nombre + ", Edad: " + edad + ", Departamento: " + departamento + ", Sueldo: " + sueldo);
			}
			
			rs.close();
			stmt.close();
			conexion.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	// Apartado 2
	public static void cambiarDepartamento() {
		Connection conexion = conectar();
		String sql = "UPDATE empleados SET departamento = 'Ventas' WHERE dni = '58969696Z' ";
		try {
			Statement stmt = conexion.createStatement();
			int resultado = stmt.executeUpdate(sql);
			
			if(resultado != -1) {
				System.out.println("Se ha modificado el empleado");
			}
			
			stmt.close();
			conexion.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	//Apartado 3
	public static void aniadirPrima() {
		Connection conexion = conectar();
		String sql = "ALTER TABLE empleados ADD COLUMN IF NOT EXISTS prima DECIMAL";
		
		try {
			
			Statement sentencia = conexion.createStatement();
			int resultado = sentencia.executeUpdate(sql);
			
			if(resultado != -1) {
				System.out.println("Se ha añadido el campo 'prima'");
			}
			
			sentencia.close();
			conexion.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	// Apartado 4
	public static void rellenarPrima() {
		Connection conexion = conectar();
		String sql = "UPDATE empleados SET prima = (sueldo*0.10)";
		try {
			Statement stmt = conexion.createStatement();
			int resultado = stmt.executeUpdate(sql);
			
			if(resultado != -1) {
				System.out.println("Se ha modificado el campo prima");
			}
			
			stmt.close();
			conexion.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
		
		
	//Apartado 5
	public static void buscarEmpleados() {
		Connection conexion = conectar();
		
		System.out.print("Introduce el departamento del empleado: ");
		String dep = tc.nextLine();
		System.out.print("Introduce el minimo de sueldo del empleado: ");
		int sueldo = tc.nextInt(); tc.nextLine();
		
		String sql = "SELECT * FROM empleados WHERE departamento = ? AND sueldo >= ?";
		
		try {
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			sentencia.setString(1, dep);
			sentencia.setInt(2, sueldo);
			
			ResultSet rs = sentencia.executeQuery();
			
			while(rs.next()) {
				String dni = rs.getString(1);
				String nombre = rs.getString(2);
				int edad = rs.getInt(3);
				String departamento = rs.getString(4);
				int sueldo_f = rs.getInt(5);
				System.out.println("DNI: " + dni + ", Nombre: " + nombre + ", Edad: " + edad + ", Departamento: " + departamento + ", Sueldo: " + sueldo_f);
			}
			System.out.println("No se encuentran mas empleados");
			
			rs.close();
			sentencia.close();
			conexion.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	//Apartdado 6
	public static void empleadosPorDepartamento() {
		Connection conexion = conectar();
		
		/*
		 * BEGIN
		 *	SELECT COUNT(*) AS ventas FROM empleados WHERE departamento = 'Ventas';
		 *	SELECT COUNT(*) AS marketing FROM empleados WHERE departamento = 'Marketing';
		 *	SELECT COUNT(*) AS rrhh FROM empleados WHERE departamento = 'RRHH';
		 * END
		 */
		String sql = "{ CALL empleadosDepartamento() }";
		
		try {
			CallableStatement stmt = conexion.prepareCall(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int ventas = rs.getInt("ventas");
				/*int marketing = rs.getInt("marketing");
				int rrhh = rs.getInt("rrhh");*/
				System.out.println("Ventas: " + ventas/* + ", Marketing: " + marketing + ", rrhh: " + rrhh*/);
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	//Apartado 7
	public static void borrarEmpleado() {
		Connection conexion = conectar();
		
		System.out.print("DNI del empleado a eliminar: ");
		String dni = tc.nextLine();
		
		/*
		 * 	DELIMITER $$
			CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminarEmpleado`(IN `p_dni` VARCHAR(9))
			BEGIN
			
			DELETE FROM empleados WHERE dni = p_dni;
			
			END$$
			DELIMITER ;
		 */
		String sql = "{ CALL eliminarEmpleado(?) }";
		
		try {
			CallableStatement stmt = conexion.prepareCall(sql);
			stmt.setString(1, dni);
			
			boolean rs = stmt.execute();
			
			if(!rs) {
				System.out.println("Se ha eliminado el empleado con dni: " + dni);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
