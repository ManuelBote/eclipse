package Ejercicios;

public class Vehiculo {
	
	String matricula;
	String marca;
	double precio_de_compra;
	

	public Vehiculo() {
		
	}
	
	public Vehiculo(String matricula, String marca, double precio_de_compra) {
		this.matricula = matricula;
		this.marca = marca;
		this.precio_de_compra = precio_de_compra;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public double getPrecio_de_compra() {
		return precio_de_compra;
	}

	public void setPrecio_de_compra(double precio_de_compra) {
		this.precio_de_compra = precio_de_compra;
	}
	
	
	public double pvp(int num) {
		return this.precio_de_compra + (this.precio_de_compra * num / 100);
	}

	@Override
	public String toString() {
		return "Vehiculo [matricula=" + matricula + ", marca=" + marca + ", precio_de_compra=" + precio_de_compra + "]";
	}
	
	public static void main(String[] args) {
		
		Vehiculo v = new Vehiculo("4353GKL", "Opel", 5436.98);
		
		System.out.println(v.toString());
		System.out.println(v.pvp(50));
		
		
	}
	
	
	
	
	


}
