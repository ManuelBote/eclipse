package EjercicioFinal;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class PersonasJson {
	
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
