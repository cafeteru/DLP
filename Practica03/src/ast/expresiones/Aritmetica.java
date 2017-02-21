package ast.expresiones;

import ast.util.NodoPosicion;

/**
 * Clase que simula en el análizador léxico un token que es una operación
 * aritmética.
 * 
 * @author Iván González Mahagamage
 *
 */
public class Aritmetica extends NodoPosicion implements Expresion {
	private String operador;
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
	 * 
	 * @param der
	 *            Expresión que se encuentra a la derecha del operando.
	 * 
	 */
	public Aritmetica(int linea, int columna, Expresion izq, String operador,
			Expresion der) {
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

	/**
	 * Redefinición del método toString().
	 */
	@Override
	public String toString() {
		return operador;
	}

}
