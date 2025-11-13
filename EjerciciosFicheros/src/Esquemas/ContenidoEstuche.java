package Esquemas;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ContenidoEstuche implements Serializable {
    String regla;
    int numRegla;
    String boli;
    int numBoli;

    public ContenidoEstuche() {}

    public ContenidoEstuche(String regla, int numRegla, String boli, int numBoli) {
        this.regla = regla;
        this.numRegla = numRegla;
        this.boli = boli;
        this.numBoli = numBoli;
    }

    @XmlElement
    public String getRegla() {
        return regla;
    }
    public void setRegla(String regla) {
        this.regla = regla;
    }

    @XmlElement
    public int getNumRegla() {
        return numRegla;
    }
    public void setNumRegla(int numRegla) {
        this.numRegla = numRegla;
    }

    @XmlElement
    public String getBoli() {
        return boli;
    }
    public void setBoli(String boli) {
        this.boli = boli;
    }

    @XmlElement
    public int getNumBoli() {
        return numBoli;
    }
    public void setNumBoli(int numBoli) {
        this.numBoli = numBoli;
    }

    @Override
    public String toString() {
        return "ContenidoEstuche [regla=" + regla + ", numRegla=" + numRegla +
                ", boli=" + boli + ", numBoli=" + numBoli + "]";
    }
}
