package EjercicioFinal;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class PersonasJson implements Serializable{
	
	public List<PersonaJson> personas;
	
	public PersonasJson() {
	}

	public List<PersonaJson> getPersonasJson() {
		return personas;
	}

	public void setPersonasJson(List<PersonaJson> personas) {
		this.personas = personas;
	}
	

}
