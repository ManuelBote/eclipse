package ExamenFichero_ManuelBoteZabala;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

//@Author Manuel Bote Zabala
@XmlType(propOrder = {"id", "nombre", "comentario"})
public class AugusFitXML implements Serializable{

	int id;
	String nombre, comentario;
	
	public AugusFitXML() {
	}
	
	public AugusFitXML(int id, String nombre, String comentario) {
		this.id = id;
		this.nombre = nombre;
		this.comentario = comentario;
	}

	@XmlElement
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlElement
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@XmlElement
	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	
	
	
}
