package Unidad4;
import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;

public class WorkerHandler implements Runnable {

    private final Socket socket;
    private final Queue<List<String>> colaBloques;
    private final List<String> resultadosMap;
    private final CountDownLatch latch;

    public WorkerHandler(Socket socket,
                         Queue<List<String>> colaBloques,
                         List<String> resultadosMap,
                         CountDownLatch latch) {

        this.socket = socket;
        this.colaBloques = colaBloques;
        this.resultadosMap = resultadosMap;
        this.latch = latch;
    }

    @Override
    public void run() {
        try (
            Socket s = socket;
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(s.getInputStream()))
        ) {

            // Tomar un bloque de trabajo
            List<String> bloque = colaBloques.poll();

            if (bloque == null) {
                out.println("SIN_TRABAJO");
                out.println("FIN");
                return;
            }

            // Enviar bloque al cliente
            for (String linea : bloque) {
                out.println(linea);
            }
            out.println("FIN");

            // Recibir resultados MAP
            String linea;
            while ((linea = in.readLine()) != null) {
                if ("FIN".equals(linea)) break;
                resultadosMap.add(linea);
            }

            // Bloque procesado correctamente
            latch.countDown();

            System.out.println(
                Thread.currentThread().getName() + " completo un bloque"
            );

        } catch (IOException e) {
            e.printStackTrace();
            // AquÃ­ podrÃ­as reinsertar el bloque si quieres tolerancia a fallos
        }
    }
}