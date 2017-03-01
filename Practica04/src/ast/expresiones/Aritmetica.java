package ast.expresiones;

import ast.expresiones.util.OperacionBinaria;

/**
 * Clase que simula en el an�lizador l�xico un token que es una operaci�n
 * aritm�tica.
 * 
 * @author Iv�n Gonz�lez Mahagamage
 *
 */
public class Aritmetica extends OperacionBinaria {

	public Aritmetica(int linea, int columna, Expresion izq, String operador,
			Expresion der) {
		super(linea, columna, izq, operador, der);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Aritmetica [operador=" + operador + ", linea=" + linea
				+ ", columna=" + columna + ", getIzq()=" + getIzq()
				+ ", getDer()=" + getDer() + "]";
	}

}
