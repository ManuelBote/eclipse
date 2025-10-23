package Practicas;

import java.io.*;
import java.util.*;

public class Pr4 {

	public static void main(String[] args) {

		List<List<String>> comandos = Arrays.asList(
				Arrays.asList("ping", "-c", "3", "www.google.com"),
				Arrays.asList("java", "--version"),
				Arrays.asList("ls"));
		
		List<Process> procesos = new ArrayList<>();
		
		try {
			for (List<String> comando : comandos) {
				ProcessBuilder pb = new ProcessBuilder(comando);
				pb.redirectErrorStream(true);
				procesos.add(pb.start());
			}
			for (Process proceso : procesos) {
				new Thread(() -> {
					try (BufferedReader lector = new BufferedReader(new InputStreamReader(proceso.getInputStream()))) {
						String linea;
						while ((linea = lector.readLine()) != null) {
							System.out.println(linea);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}).start();

			}
			for (Process proceso : procesos) {
				proceso.waitFor();
			}
			System.out.println("Todos los procesos han terminado.");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
