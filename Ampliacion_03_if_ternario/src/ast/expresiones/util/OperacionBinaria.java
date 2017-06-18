package ast.expresiones.util;

import ast.expresiones.Expresion;

/**
 * Implementación abstracta de la interfaz Expresion para las clases que
 * contienen otra dos expresiones dentro de ellas.
 * 
 * @author Iván González Mahagamage
 */
public abstract class OperacionBinaria extends ExpresionAbstracta {
	protected String operador;
	private Expresion izq;
	private Expresion der;

	/**
	 * Constructor con parámetros.
	 * 
	 * @param linea
	 *            Línea en la que se encuentra el lexema.
	 * @param columna
	 *            Columna en la que se encuentra el lexema.
	 * @param izq
	 *            Expresión que se encuentra a la izquierda del operando.
	 * @param operador
	 *            Operando de la operación.
	 * @param der
	 *            Expresión que se encuentra a la derecha del operando.
	 * 
	 */
	public OperacionBinaria(int linea, int columna, Expresion izq,
			String operador, Expresion der) {
		super(linea, columna);
		this.izq = izq;
		this.der = der;
		this.operador = operador;
	}

	/**
	 * Método que devuelve el parámetro izq.
	 * 
	 * @return Expresión que se encuentra a la izquierda del operando.
	 */
	public Expresion getIzq() {
		return izq;
	}

	/**
	 * Método que devuelve el parámetro der.
	 * 
	 * @return Expresión que se encuentra a la derecha del operando.
	 */
	public Expresion getDer() {
		return der;
	}

	@Override
	public String toString() {
		return "OperacionBinaria [operador=" + operador + ", izq=" + izq
				+ ", der=" + der + ", linea=" + linea + ", columna=" + columna
				+ "]";
	}

	/**
	 * Método que devuelve el operador de la expresión.
	 * 
	 * @return Operador de la expresión.
	 */
	public String getOperador() {
		return operador;
	}

}
