package Unidad4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LauncherWorkers {
    
    public static void main(String[] args) {
        int numWorkers = 500;  //Cantidad de trabajadores
        
        System.out.println("AUTO-LAUNCHER: Iniciando " + numWorkers + " workers...");
        
        List<Thread> workers = new ArrayList<>();
        
        for (int i = 1; i <= numWorkers; i++) {
            final int workerId = i;
            Thread worker = new Thread(() -> {
                try {
                    System.out.println("Worker #" + workerId + " -> Procesando...");
                    ClienteWorker.main(new String[]{});
                    System.out.println("Worker #" + workerId + " -> Completado");
                } catch (Exception e) {
                    System.err.println("Worker #" + workerId + " -> Error: " + e.getMessage());
                }
            }, "Worker ->" + i);
            
            workers.add(worker);
            worker.start();
            
            // Pausa mínima
            try { Thread.sleep(5); } catch (InterruptedException ignored) {}
        }
        
        // Esperar TODOS
        System.out.println("\nEsperando " + numWorkers + " workers...");
        for (Thread w : workers) {
            try {
                w.join(10000);  // Timeout 10s por worker
            } catch (InterruptedException e) {
                System.out.println("Interrumpido: " + e.getMessage());
            }
        }
        
        System.out.println("\nFinalizacion de los trabajadores.\n");
        System.out.println("Presiona Enter para salir...");
        new Scanner(System.in).nextLine();
    }
}

