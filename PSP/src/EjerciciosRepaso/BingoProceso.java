package EjerciciosRepaso;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class BingoProceso {
    private static Scanner tc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("¿Cuántos jugadores? (1-10): ");
        int num = tc.nextInt(); tc.nextLine();
        
        List<Process> hijos = new ArrayList<>();
        List<PrintStream> salidasHijos = new ArrayList<>();
        List<Thread> hilosLectura = new ArrayList<>();
        List<BufferedReader> entradasStdout = new ArrayList<>();
        AtomicBoolean juegoTerminado = new AtomicBoolean(false);
        AtomicInteger ganadorId = new AtomicInteger(-1);

        // Lanzar hijos
        for (int i = 0; i < num; i++) {
            try {
                ProcessBuilder pb = new ProcessBuilder("java", "EjerciciosRepaso.JugadorProceso", String.valueOf(i + 1));
                pb.directory(new File("."));  // Asegura classpath
                Process hijo = pb.start();
                hijos.add(hijo);
                PrintStream salida = new PrintStream(hijo.getOutputStream(), true);
                salidasHijos.add(salida);
                
                // Hilo para leer stdout (detecta GANADOR y prints)
                final int idHilo = i + 1;
                BufferedReader stdoutHijo = new BufferedReader(new InputStreamReader(hijo.getInputStream()));
                entradasStdout.add(stdoutHijo);
                Thread hiloStdout = new Thread(() -> {
                    try {
                        String linea;
                        while ((linea = stdoutHijo.readLine()) != null && !juegoTerminado.get()) {
                            System.out.println("[Jugador " + idHilo + "] " + linea);
                            if (linea.startsWith("GANADOR:") && ganadorId.get() == -1) {
                                ganadorId.set(Integer.parseInt(linea.split(":")[1]));
                            }
                        }
                    } catch (IOException e) {}
                });
                hiloStdout.start();
                hilosLectura.add(hiloStdout);
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        // Generar números hasta ganador
        HashSet<Integer> numbers = new HashSet<>();
        while (ganadorId.get() == -1 && numbers.size() < 99) {
            int n;
            do {
                n = 1 + (int) (Math.random() * 99);
            } while (numbers.contains(n));
            numbers.add(n);
            System.out.println("*** NÚMERO SALIDO: " + n + " ***");

            // Enviar a todos hijos
            for (PrintStream out : salidasHijos) {
                out.println(n);
            }
            
            try {
                Thread.sleep(1000);  // Pausa ronda para simulación realista
            } catch (InterruptedException e) {}
        }

        int idGanador = ganadorId.get();
        if (idGanador != -1) {
            System.out.println("¡Jugador " + idGanador + " ha GANADO!");
        }

        // Terminar
        juegoTerminado.set(true);
        for (PrintStream out : salidasHijos) {
            out.println("FIN");
        }
        
        // Esperar terminación
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {}
        for (Process h : hijos) {
            h.destroyForcibly();
        }
        tc.close();
    }
}
