package Unidad4;
import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ClienteWorker {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost", 5001);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
        );
        PrintWriter out = new PrintWriter(
                socket.getOutputStream(), true
        );

        Map<String, Integer> parcial = new HashMap<>();
        String linea;
        int lineasLeidas = 0;

        // Recibir bloque
        while ((linea = in.readLine()) != null) {
            if (linea.equals("FIN")) break;
            if (linea.equals("SIN_TRABAJO")) {
                socket.close();
                return;
            }

            lineasLeidas++;
            
            String[] partes = linea.split(",");
            if (partes.length >= 3) {
                String codigoCiudadBanco = partes[2].trim().toUpperCase();
                
                // Validar código (2+ letras)
                if (codigoCiudadBanco.length() >= 2 && codigoCiudadBanco.matches(".*[A-Z].*")) {
                    parcial.put(codigoCiudadBanco, parcial.getOrDefault(codigoCiudadBanco, 0) + 1);
                }
            }
            
            if (lineasLeidas == 1) {
                System.out.println("Ejemplo: '" + linea + "' -> código '" + (partes.length >= 3 ? partes[2] : "N/A") + "'");
            }
        }

        // Resultado
        System.out.println("Worker: " + lineasLeidas + " líneas -> " + parcial.size() + " códigos únicos");
        for (var e : parcial.entrySet()) {
            out.println(e.getKey() + "," + e.getValue());
        }
        out.println("FIN");
        out.flush();

        socket.close();
    }
}
