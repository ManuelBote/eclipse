package Practicas;

public class Pr3 {

	public static void main(String[] args) {
		
		try {
			Process proceso = Runtime.getRuntime().exec("java src/Practicas/Pr1.java");
			java.io.BufferedReader lector = new java.io.BufferedReader(
					new java.io.InputStreamReader(proceso.getInputStream()));
			
			String linea;
			while ((linea = lector.readLine()) != null) {
				System.out.println(linea);
			}
			
			lector.close();
			proceso.waitFor();
			System.out.println("Código de salida: " + proceso.exitValue());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
