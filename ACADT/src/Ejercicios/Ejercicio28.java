package Ejercicios;

import Ejercicios.Vehiculo;
// @author Manuel Bote Zabala
public class Ejercicio28 {

	public static void main(String[] args) {

		/*
		 * 28. Crea una clase principal llamada Concesionario donde se instancian o
		 * crean 2 objetos de la clase vehiculo (coche1 y moto1)  Para el objeto coche1
		 * los datos se inicializaran llamando al constructor con parámetros y le
		 * pasamos los datos directamente al constructor.  Para el objeto moto1 los
		 * datos se inicializaran llamando al constructor por defecto (al vacio) y
		 * pasaremos los datos utilizando los métodos setters de variables locales
		 * declaradas en el método main. Para este ejercicio nos inventaremos los datos
		 * y no será necesario solicitárselos al usuario Continuamos con la clase
		 * principal anterior. Una vez que tenemos los 2 vehículos vamos a mostrar los
		 * datos de cada uno de ellos por pantalla, para ello debes emplear los métodos:
		 *  Para el coche1 los métodos get.  Para el moto1 el método toString. Una vez
		 * mostrados los datos llamaremos al método pvp de cada objeto. Pasándole el
		 * beneficio que queramos pero comprendido entre 0 y 100.
		 */
		
		//Inicializar coche1 y moto1
		Vehiculo coche1 = new Vehiculo("2492BLP", "Renault", 4200);
		Vehiculo moto1 = new Vehiculo();
		
		//Añadir valores de moto1
		moto1.setMatricula("3549HTS");
		moto1.setMarca("Yamaha");
		moto1.setPrecio_de_compra(11000);
		
		//Mostrar valores de coche1
		System.out.println(coche1.getMatricula());
		System.out.println(coche1.getMarca());
		System.out.println(coche1.getPrecio_de_compra());
		
		//Mostrar valores de moto1
		System.out.println(moto1.toString());
		
		System.out.println("Beneficio de coche1 con 50%");
		System.out.println(coche1.pvp(50));
		
		System.out.println("Beneficio de moto1 con 20%");
		System.out.println(coche1.pvp(20));
		
		
		

	}

}
