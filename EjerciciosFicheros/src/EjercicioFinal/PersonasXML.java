package EjercicioFinal;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "personas")
public class PersonasXML {
	
	public List<PersonasXML> personas;
	
	public PersonasXML() {
	}

	@XmlElement(name = "persona")
	public List<PersonasXML> getPersonasXML() {
		return personas;
	}

	public void setPersonasXML(List<PersonasXML> personas) {
		this.personas = personas;
	}
	
	
	
}
