package EjerciciosJSON;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DeJsonAObject {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		try {
			PersonaEjemplo[] persona = mapper.readValue(new File("json/personaEjemplo.json"), PersonaEjemplo[].class);
			
			for(PersonaEjemplo p: persona) {
				System.out.println(p);
			}
			
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
