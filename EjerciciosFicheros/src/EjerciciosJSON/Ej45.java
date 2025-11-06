package EjerciciosJSON;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Ej45 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String jsonString = "{ \"DNI\": \"12345678A\", \"nombre\": \"Juan\", \"edad\": 30 }";
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		try {
			
			Persona persona = mapper.readValue(jsonString, Persona.class);
			System.out.println(persona);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
