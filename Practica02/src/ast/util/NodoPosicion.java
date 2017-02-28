package ast.util;

import ast.NodoAST;

/**
 * Clase intermedia para asignar a cada token una linea y una columna.
 * 
 * @author Iván González Mahagamage
 *
 */
public class NodoPosicion implements NodoAST {
	int linea, columna;

	/**
	 * Constructor con parámetros.
	 * 
	 * @param linea
	 *            Línea en la que se encuentra el token.
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
