package Ej2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;

public class Padre {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int contRonda = 0;
		int cont1 = 0;
		int cont2 = 0;
		int cont3 = 0;

		try {
			while (true) {
				contRonda++;
				int[] numeros = { 0, 0, 0 };

				//Proceso 1
				Process proceso1 = Runtime.getRuntime().exec("java src/Ej2/Hijo.java");
				BufferedReader br1 = new BufferedReader(new InputStreamReader(proceso1.getInputStream()));
				String linea1 = br1.readLine();
				if (linea1 != null) {
					int num = Integer.parseInt(linea1);
					numeros[0] = num;
				}

				//Proceso 2
				Process proceso2 = Runtime.getRuntime().exec("java src/Ej2/Hijo.java");
				BufferedReader br2 = new BufferedReader(new InputStreamReader(proceso2.getInputStream()));
				String linea2 = br2.readLine();
				if (linea2 != null) {
					int num = Integer.parseInt(linea2);
					numeros[1] = num;
				}

				//Proceso 3
				Process proceso3 = Runtime.getRuntime().exec("java src/Ej2/Hijo.java");
				BufferedReader br3 = new BufferedReader(new InputStreamReader(proceso3.getInputStream()));
				String linea3 = br3.readLine();
				if (linea3 != null) {
					int num = Integer.parseInt(linea3);
					numeros[2] = num;
				}

				//Texto de cada ronda
				System.out.println("Ronda " + contRonda);
				System.out.println("Proceso 1 -> " + numeros[0]);
				System.out.println("Proceso 2 -> " + numeros[1]);
				System.out.println("Proceso 3 -> " + numeros[2]);

				//Comprobacion del ganador
				if (numeros[0] > numeros[1] && numeros[0] > numeros[2]) {
					System.out.println("Ganador de la ronda : Proceso 1\n\n");
					cont1++;
				} else if (numeros[1] > numeros[0] && numeros[1] > numeros[2]) {
					System.out.println("Ganador de la ronda : Proceso 2\n\n");
					cont2++;
				} else if (numeros[2] > numeros[0] && numeros[2] > numeros[1]) {
					System.out.println("Ganador de la ronda : Proceso 3\n\n");
					cont3++;
				} else {
					System.out.println("Empate\n\n");
				}
				
				//Comprobar si algun proceso ya llego a 5 puntos
				if(cont1 == 5) {
					System.out.println("Ganador final: Proceso 1");
					proceso1.destroy();
					proceso2.destroy();
					proceso3.destroy();
					break;
				} 
				else if(cont2 == 5) {
					System.out.println("Ganador final: Proceso 2");
					proceso1.destroy();
					proceso2.destroy();
					proceso3.destroy();
					break;
				}
				else if(cont3 == 5) {
					System.out.println("Ganador final: Proceso 3");
					proceso1.destroy();
					proceso2.destroy();
					proceso3.destroy();
					break;
				}
				
				proceso1.waitFor();
				proceso2.waitFor();
				proceso3.waitFor();
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
