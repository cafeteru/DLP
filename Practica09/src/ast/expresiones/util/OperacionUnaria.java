package ast.expresiones.util;

import ast.expresiones.Expresion;

public abstract class OperacionUnaria extends ExpresionAbstracta {
	protected String operador;
	private Expresion expresion;

	public OperacionUnaria(int linea, int columna, String operador,
			Expresion expresion) {
		super(linea, columna);
		this.expresion = expresion;
		this.operador = operador;
	}

	public String getOperador() {
		return operador;
	}

	public Expresion getExpresion() {
		return expresion;
	}

}
