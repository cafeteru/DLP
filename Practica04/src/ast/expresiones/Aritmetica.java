package ast.expresiones;

import ast.expresiones.util.OperacionBinaria;

/**
 * Clase que simula en el análizador léxico un token que es una operación
 * aritmética.
 * 
 * @author Iván González Mahagamage
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
