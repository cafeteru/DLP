package ast.expresiones.util;

import ast.expresiones.Expresion;

/**
 * Implementación abstracta de la interfaz Expresion para las clases que
 * contienen otra expresión dentro de ellas.
 * 
 * @author Iván González Mahagamage
 */
public abstract class OperacionUnaria extends ExpresionAbstracta {
	protected String operador;
	private Expresion expresion;

	/**
	 * Constructor con parámetros.
	 * 
	 * @param linea
	 *            Linea en la que se encuentra la expresión.
	 * @param columna
	 *            Columna en la que se encuentra la expresión.
	 * @param operador
	 *            Operador de la expresión.
	 * @param expresion
	 *            Expresión contenida dentro de esta expresión.
	 */
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
