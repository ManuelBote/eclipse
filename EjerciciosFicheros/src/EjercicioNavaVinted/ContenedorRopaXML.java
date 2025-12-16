package EjercicioNavaVinted;

import java.io.Serializable;
//@Author Manuel Bote Zabala
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Prendas")
public class ContenedorRopaXML implements Serializable{
	
	ArrayList<RopaXML> prendas = new ArrayList<RopaXML>();
	
	public ContenedorRopaXML() {
	}

	@XmlElement(name = "Prenda")
	public ArrayList<RopaXML> getPrendas() {
		return prendas;
	}

	public void setPrendas(ArrayList<RopaXML> prendas) {
		this.prendas = prendas;
	}
	
	
	
	

}
