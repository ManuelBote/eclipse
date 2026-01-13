package EjerciciosRepaso;

import java.io.*;
import java.util.HashSet;

public class JugadorProceso {
    public static void main(String[] args) {
    	
        int id = Integer.parseInt(args[0]);
        HashSet<Integer> carton = new HashSet<Integer>();
        
        while (carton.size() != 15) {
            carton.add(1 + (int) (Math.random() * 99));
        }
        
        System.out.println("Jugador " + id + " - Cartón: " + carton);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String linea;
            while (true) {
            	linea = br.readLine();
                if (linea.equals("FIN")) break;
                int num = Integer.parseInt(linea);
                if (carton.remove(num)) {
                    System.out.println("Jugador " + id + " tiene " + num);
                }
                if (carton.isEmpty()) {
                    System.out.println("GANADOR:" + id);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
