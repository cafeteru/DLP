package ast.expresiones;

import ast.expresiones.util.OperacionBinaria;

public class Logica extends OperacionBinaria {

	public Logica(int linea, int columna, Expresion izq, String operador,
			Expresion der) {
		super(linea, columna, izq, operador, der);
	}

	@Override
	public String toString() {
		return "Logica [operador=" + operador + ", linea=" + linea
				+ ", columna=" + columna + ", getIzq()=" + getIzq()
				+ ", getDer()=" + getDer() + "]";
	}

}
