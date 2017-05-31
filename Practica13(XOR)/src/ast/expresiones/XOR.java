package ast.expresiones;

import ast.expresiones.util.OperacionBinaria;
import visitor.Visitor;

public class XOR extends OperacionBinaria {

	public XOR(int linea, int columna, Expresion izq, String operador,
			Expresion der) {
		super(linea, columna, izq, operador, der);
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

	@Override
	public String toString() {
		return "XOR [operador=" + operador + ", lValue=" + lValue + ", tipo="
				+ tipo + ", linea=" + linea + ", columna=" + columna + "]";
	}

}
