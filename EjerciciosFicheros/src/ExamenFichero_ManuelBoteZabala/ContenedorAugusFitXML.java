package ExamenFichero_ManuelBoteZabala;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//@Author Manuel Bote Zabala
@XmlRootElement(name = "Augustfit")
public class ContenedorAugusFitXML implements Serializable{
	
	ArrayList<AugusFitXML> personas = new ArrayList<AugusFitXML>();
	
	public ContenedorAugusFitXML() {
	}

	@XmlElement(name = "Usuario")
	public ArrayList<AugusFitXML> getPersonas() {
		return personas;
	}

	public void setPersonas(ArrayList<AugusFitXML> personas) {
		this.personas = personas;
	}
	
	

}
