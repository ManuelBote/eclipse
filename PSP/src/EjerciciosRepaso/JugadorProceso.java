package EjerciciosRepaso;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashSet;

public class JugadorProceso {

	public static void main(String[] args) {
		
		int id = Integer.parseInt(args[0]);
		HashSet<Integer> carton = new HashSet<Integer>();
		
		while(carton.size() != 15) {
			carton.add(1+ (int)(Math.random()*99));
		}
		
		try (BufferedReader padre = new BufferedReader(new InputStreamReader(System.in));
			Socket socketPadre = new Socket("localhost", 12345)){

	        String linea;
	        while ((linea = padre.readLine()) != null) {
	            if (linea.equals("FIN")) {
	            	break;
	            }
	            
	            int num = Integer.parseInt(linea);
	            if (carton.remove(num)) {
	                System.out.println("Jugador " + id + " tiene " + num);
	            }
	            
	            if (carton.isEmpty()) {
	                // Avisar padre
	                PrintWriter outPadre = new PrintWriter(socketPadre.getOutputStream(), true);
	                outPadre.println(id);
	                break;
	            }
	        }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

}
