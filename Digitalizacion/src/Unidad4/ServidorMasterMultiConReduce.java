package Unidad4;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;

public class ServidorMasterMultiConReduce {

    private static final int PUERTO = 5001;
    private static final String ARCHIVO = "/home/diurno/eclipse-workspace/Digitalizacion/src/Unidad4/MOCK_DATA.csv";  // Ruta al archivo adjunto

    public static void main(String[] args)
            throws IOException, InterruptedException {

        // Cargar datos del CSV (simula HDFS)
        List<String> datos = cargarDatosCSV(ARCHIVO);
        System.out.println("Datos cargados: " + datos.size() + " lineas");

        int tamBloque = 2;

        Queue<List<String>> colaBloques =
                new ConcurrentLinkedQueue<>(particionar(datos, tamBloque));

        List<String> resultadosMap =
                Collections.synchronizedList(new ArrayList<>());

        CountDownLatch latch =
                new CountDownLatch(colaBloques.size());

        ServerSocket server = new ServerSocket(PUERTO);
        System.out.println("MASTER activo en puerto " + PUERTO);
        System.out.println("Bloques pendientes: " + colaBloques.size());

        while (latch.getCount() > 0) {
            Socket cliente = server.accept();

            Thread hilo = new Thread(
                new WorkerHandler(
                    cliente,
                    colaBloques,
                    resultadosMap,
                    latch
                )
            );
            hilo.start();
        }

        server.close();

        // Esperar a que TODOS los bloques terminen
        latch.await();

        // REDUCE final
        Map<String, Integer> resultadoFinal =
                reduceFinal(resultadosMap);

        System.out.println("\nREDUCE final (conteo CodigoCiudadBanco):");
        resultadoFinal.forEach(
            (k, v) -> System.out.println(k + " -> " + v)
        );
    }

    // Nueva funcion: Cargar datos del CSV
    private static List<String> cargarDatosCSV(String archivo) throws IOException {
        List<String> datos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            boolean primerLinea = true;  // Saltar header
            while ((linea = br.readLine()) != null) {
                if (primerLinea) {
                    primerLinea = false;
                    continue;
                }
                if (!linea.trim().isEmpty()) {
                    datos.add(linea.trim());
                }
            }
        }
        return datos;
    }

    // Particionar datos 
    private static List<List<String>> particionar(
            List<String> datos, int tam) {

        List<List<String>> bloques = new ArrayList<>();
        for (int i = 0; i < datos.size(); i += tam) {
            bloques.add(datos.subList(
                i, Math.min(i + tam, datos.size()))
            );
        }
        return bloques;
    }

    // Reduce final 
    private static Map<String, Integer> reduceFinal(
            List<String> resultadosMap) {

        Map<String, Integer> acumulado = new HashMap<>();

        for (String linea : resultadosMap) {
            String[] partes = linea.split(",");
            if (partes.length != 2) continue;

            String clave = partes[0].trim();
            int valor = Integer.parseInt(partes[1].trim());

            acumulado.put(
                clave,
                acumulado.getOrDefault(clave, 0) + valor
            );
        }
        return acumulado;
    }
}