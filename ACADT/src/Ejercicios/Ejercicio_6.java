package Ejercicios;
import java.util.Scanner;

public class Ejercicio_6 {
	
	public static void main(String[] args) {
		Scanner teclado = new Scanner (System.in);

		System.out.printf("Indique el precio del producto: ");
		String precio = teclado.nextLine();
		double precioInt = Integer.parseInt(precio);
		
		double precioMes = precioInt/3;
		
		double primerMes = precioMes + (precioInt*2.5/100);
		double segundoMes = precioMes + ((precioInt-precioMes)*5/100);
		double tercerMes = precioMes + ((precioInt-precioMes*2)*10/100);
		
		
		System.out.println("El primer mes debe pagar: " + primerMes + "€");
		System.out.println("El segundo mes debe pagar: " + segundoMes + "€");
		System.out.println("El tercer mes debe pagar: " + tercerMes + "€");
		
		System.out.println("El precio total es: " + (primerMes + segundoMes + tercerMes) + "€");
		
		}
	
}
