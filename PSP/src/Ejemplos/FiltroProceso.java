package Ejemplos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FiltroProceso {
	
	public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Uso: java FiltroProceso <ficheroEntrada>");
            System.exit(1);
        }

        String rutaEntrada = args[0];

        try (BufferedReader br = new BufferedReader(new FileReader(rutaEntrada))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains("java")) {      // filtro por palabra
                    System.out.println(linea);      // sale por stdout
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(2);
        }
    }

}
