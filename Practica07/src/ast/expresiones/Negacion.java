package ast.expresiones;

import ast.expresiones.util.OperacionUnaria;
import visitor.Visitor;

public class Negacion extends OperacionUnaria {

	public Negacion(int linea, int columna, String operador,
			Expresion expresion) {
		super(linea, columna, operador, expresion);
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}
}
