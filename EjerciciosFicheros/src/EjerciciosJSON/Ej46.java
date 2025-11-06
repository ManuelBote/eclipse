package EjerciciosJSON;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Ej46 {

	public static void main(String[] args) {

		ObjectMapper mapper = new ObjectMapper();

		try {

			RecetasContenedor recetas = new ObjectMapper().readValue(new File("json/ej46recetas.json"),
					RecetasContenedor.class);

			for (Recetas r : recetas.getRecetas()) {
				System.out.println("Nombre: " + r.getNombre());
				System.out.println("Tipo: " + r.getTipo());
				System.out.println("Origen: " + r.getOrigen().getPais() + ", " + r.getOrigen().getRegion());

				System.out.println("Ingredientes: ");
				for (Ingredientes i : r.getIngredientes()) {
					System.out.println("- " + i.getNombre() + ", " + i.getCantidad());
				}
				System.out.println("\n");
			}

		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
