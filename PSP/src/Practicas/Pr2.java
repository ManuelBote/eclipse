package Practicas;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Pr2 {

	public static void main(String[] args) {
		
		try {
			
			Process proceso = Runtime.getRuntime().exec("java --version");
			java.io.BufferedReader lector = new java.io.BufferedReader(new java.io.InputStreamReader(proceso.getInputStream()));
			
			BufferedWriter bw = new BufferedWriter(new FileWriter("salida.txt"));
			String linea;
			
			while((linea = lector.readLine()) != null) {
				bw.write(linea);
				bw.newLine();
			}
			
			bw.close();
			lector.close();
			System.out.println("Fichero creado");
		} catch (Exception e) {
			e.printStackTrace();
				
		}finally {
			
		}

	}

}
