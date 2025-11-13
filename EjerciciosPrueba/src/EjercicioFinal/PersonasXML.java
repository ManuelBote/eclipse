package EjercicioFinal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "personas")
public class PersonasXML implements Serializable{
	
	public List<PersonaXML> personas;
	
	public PersonasXML() {
	}

	@XmlElement(name = "persona")
	public List<PersonaXML> getPersonasXML() {
		return personas;
	}

	public void setPersonasXML(List<PersonaXML> personas) {
		this.personas = personas;
	}
	
	
	
}
