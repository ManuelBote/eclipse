package Esquemas;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement
public class Estuche implements Serializable {
    String marca;
    int numSerie;
    List<ContenidoEstuche> contenido;

    public Estuche(String marca, int numSerie, List<ContenidoEstuche> contenido) {
        this.marca = marca;
        this.numSerie = numSerie;
        this.contenido = contenido;
    }

    public Estuche() {}

    @XmlElement
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }

    @XmlElement
    public int getNumSerie() {
        return numSerie;
    }
    public void setNumSerie(int numSerie) {
        this.numSerie = numSerie;
    }

    @XmlElement
    public List<ContenidoEstuche> getContenido() {
        return contenido;
    }
    public void setContenido(List<ContenidoEstuche> contenido) {
        this.contenido = contenido;
    }

    @Override
    public String toString() {
        return "Estuche [marca=" + marca + ", numSerie=" + numSerie + ", contenido=" + contenido + "]";
    }
}
