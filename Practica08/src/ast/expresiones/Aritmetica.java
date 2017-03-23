package ast.expresiones;

import ast.expresiones.util.OperacionBinaria;
import visitor.Visitor;

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
	}

	@Override
	public String toString() {
		return "Aritmetica [operador=" + operador + ", linea=" + linea
				+ ", columna=" + columna + ", getIzq()=" + getIzq()
				+ ", getDer()=" + getDer() + "]";
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}
}
