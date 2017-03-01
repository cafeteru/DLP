package ast.util;

import ast.NodoAST;

/**
 * Clase intermedia para asignar a cada token una linea y una columna.
 * 
 * @author Iv�n Gonz�lez Mahagamage
 *
 */
public class NodoPosicion implements NodoAST {
	protected int linea;
	protected int columna;

	/**
	 * Constructor con par�metros.
	 * 
	 * @param linea
	 *            L�nea en la que se encuentra el token.
	 * @param columna
	 *            Columna en la que se encuentra el token.
	 */
	public NodoPosicion(int linea, int columna) {
		this.linea = linea;
		this.columna = columna;
	}

	@Override
	public int getLinea() {
		return linea;
	}

	@Override
	public int getColumna() {
		return columna;
	}
}
