package Ejemplos;

public class ejemplo {

}
/*
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


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.ProcessBuilder;

public class Padre {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Uso: java Padre <fichero1> <fichero2> <ficheroSalida>");
            System.exit(1);
        }

        String ruta1 = args[0];
        String ruta2 = args[1];
        String rutaSalida = args[2];

        Process p1 = null;
        Process p2 = null;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaSalida))) {

            // Construir procesos hijos independientes
            ProcessBuilder pb1 = new ProcessBuilder("java", "FiltroProceso", ruta1);
            ProcessBuilder pb2 = new ProcessBuilder("java", "FiltroProceso", ruta2);

            // Opcional: definir directorio si hace falta que encuentre las clases
            // pb1.directory(new File("ruta/a/clases"));
            // pb2.directory(new File("ruta/a/clases"));

            p1 = pb1.start();
            p2 = pb2.start();

            // Lectores de la salida estándar de cada hijo
            try (BufferedReader r1 = new BufferedReader(
                     new InputStreamReader(p1.getInputStream()));
                 BufferedReader r2 = new BufferedReader(
                     new InputStreamReader(p2.getInputStream()))) {

                // Leer hijo 1 y escribir en el fichero final
                String linea;
                while ((linea = r1.readLine()) != null) {
                    bw.write(linea);
                    bw.newLine();
                }

                // Leer hijo 2 y escribir en el fichero final
                while ((linea = r2.readLine()) != null) {
                    bw.write(linea);
                    bw.newLine();
                }
            }

            // Esperar a que terminen los hijos (opcional pero recomendable)
            int exit1 = p1.waitFor();
            int exit2 = p2.waitFor();
            System.out.println("Hijo 1 terminó con código " + exit1);
            System.out.println("Hijo 2 terminó con código " + exit2);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.exit(2);
        }
    }
}
*/
