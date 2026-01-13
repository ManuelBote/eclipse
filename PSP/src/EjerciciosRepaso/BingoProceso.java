package EjerciciosRepaso;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class BingoProceso {
    // Variable compartida thread-safe para parar al ganar
    private static final AtomicInteger ganadorId = new AtomicInteger(-1);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Número de jugadores: ");
        int nJugadores = sc.nextInt();
        sc.nextLine();

        List<Process> hijos = new ArrayList<>();
        List<PrintStream> salidasHijos = new ArrayList<>();

        try {
            for (int i = 1; i <= nJugadores; i++) {
                final int idHijo = i;
                
                ProcessBuilder pb = new ProcessBuilder(
                    "java",
                    "src/EjerciciosRepaso/JugadorProceso.java",  
                    String.valueOf(i)
                );
                pb.redirectError(ProcessBuilder.Redirect.INHERIT);
                Process p = pb.start();
                hijos.add(p);

                PrintStream outHijo = new PrintStream(p.getOutputStream(), true);
                salidasHijos.add(outHijo);

                new Thread(() -> leerSalidaHijo(p, idHijo)).start();
            }

            // 🔑 BUCLE PARA al GANAR
            HashSet<Integer> numbers = new HashSet<>();
            while (ganadorId.get() == -1 && numbers.size() < 99) {
                int n;
                do {
                    n = 1 + (int) (Math.random() * 99);
                } while (numbers.contains(n));
                numbers.add(n);

                System.out.println("\n*** NÚMERO SALIDO: " + n + " ***");

                for (PrintStream out : salidasHijos) {
                    out.println(n);
                }

                Thread.sleep(500);
            }

            // Enviar FIN a todos
            System.out.println("=== Partida terminada! Ganador: " + ganadorId.get() + " ===");
            for (PrintStream out : salidasHijos) {
                out.println("FIN");
                out.flush();
            }

            for (Process p : hijos) {
                p.waitFor();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void leerSalidaHijo(Process p, int idHijo) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println("[Jugador " + idHijo + "] " + linea);

                if (linea.startsWith("GANADOR:")) {
                    //  Parar si uno gana
                    int idGanador = Integer.parseInt(linea.substring(8).trim());
                    if (ganadorId.compareAndSet(-1, idGanador)) {
                        System.out.println("=== ¡GANADOR DETECTADO: Jugador " + idGanador + "! ===");
                    }
                }
            }
        } catch (IOException e) {
            // Normal al cerrar
        }
    }
}
