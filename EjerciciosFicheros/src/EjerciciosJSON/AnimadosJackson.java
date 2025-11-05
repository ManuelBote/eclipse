package EjerciciosJSON;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AnimadosJackson {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ObjectMapper mapper = new ObjectMapper();
		
		//LEER una cadena json
		String jsonInput = "{\"id\":1,\"nombre\":\"Robin\",\"apellido\":\"Wilson\"}";
		Animados a = new Animados();
		
		try {
			a = mapper.readValue(jsonInput, Animados.class);
		} catch (JsonProcessingException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		System.out.println("Leer y parsed a animados desde JSON: " + a);
		
		//ESCRIBE una cadena json
		Animados b = new Animados(2, "Roger", "Rabbit");
		System.out.println("Animado object " + b + "as JSON = ");
		
		try {
			mapper.writeValue(System.out, b);
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
		

	}

}
