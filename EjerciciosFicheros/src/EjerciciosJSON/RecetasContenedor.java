package EjerciciosJSON;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecetasContenedor {

	private List<Recetas> recetas;

	public List<Recetas> getRecetas() {
		return recetas;
	}

	public void setRecetas(List<Recetas> recetas) {
		this.recetas = recetas;
	}

}
