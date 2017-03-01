package ast.expresiones;

import ast.util.NodoPosicion;

public class LiteralReal extends NodoPosicion implements Expresion {
	private double valor;

	/**
	 * Constructor con par�metros.
	 * 
	 * @param linea
	 *            L�nea en la que se encuentra el lexema.
	 * @param columna
	 *            Columna en la que se encuentra el lexema.
	 * @param valor
	 *            Valor entero del literal entero.
	 */
	public LiteralReal(int linea, int columna, double valor) {
		super(linea, columna);
		this.valor = valor;
	}
}
