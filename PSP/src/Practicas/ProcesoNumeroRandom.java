package Practicas;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ProcesoNumeroRandom {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner (System.in);
		
		System.out.println("Introduce un numero: ");
		int numTarget = Integer.parseInt(sc.nextLine());
		
		try {
			
			while(true) {
				Process proceso = Runtime.getRuntime().exec("java src/Practicas/NumeroRandom.java");
				BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
				
				String linea = br.readLine();
				
				if(linea != null) {
					System.out.println(linea);
					int num = Integer.parseInt(linea);
					
					if(num == numTarget) {
						proceso.destroy();
						break;
					}
				}
				
				proceso.waitFor();
				
			}
			
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
