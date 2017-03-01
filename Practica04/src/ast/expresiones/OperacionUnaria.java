package ast.expresiones;

import ast.util.CompositeSentencia;

public class OperacionUnaria extends CompositeSentencia implements Expresion {
	protected String operador;
	private Expresion expresion;

	public OperacionUnaria(int linea, int columna, String operador,
			Expresion expresion) {
		super(linea, columna);
		this.expresion = expresion;
		this.operador = operador;
	}

	@Override
	public String toString() {
		return "OperacionUnaria [operador=" + operador + ", expresion=" + expresion + ", sentencias=" + sentencias
				+ ", linea=" + linea + ", columna=" + columna + "]";
	}
	
	
	

}
