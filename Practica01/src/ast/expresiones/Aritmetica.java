package ast.expresiones;

import ast.util.NodoPosicion;

/**
 * Clase que simula en el an�lizador l�xico un token que es una operaci�n
 * aritm�tica.
 * 
 * @author Iv�n Gonz�lez Mahagamage
 *
 */
public class Aritmetica extends NodoPosicion implements Expresion {
	private String operador;
	private Expresion izq;
	private Expresion der;

	/**
	 * Constructor con par�metros.
	 * 
	 * @param linea
	 *            L�nea en la que se encuentra el lexema.
	 * @param columna
	 *            Columna en la que se encuentra el lexema.
	 * @param izq
	 *            Expresi�n que se encuentra a la izquierda del operando.
	 * @param operador
	 *            Operando de la operaci�n.
	 * 
	 * @param der
	 *            Expresi�n que se encuentra a la derecha del operando.
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
	 * M�todo que devuelve el par�metro izq.
	 * 
	 * @return Expresi�n que se encuentra a la izquierda del operando.
	 */
	public Expresion getIzq() {
		return izq;
	}

	/**
	 * M�todo que devuelve el par�metro der.
	 * 
	 * @return Expresi�n que se encuentra a la derecha del operando.
	 */
	public Expresion getDer() {
		return der;
	}

	/**
	 * Redefinici�n del m�todo toString().
	 */
	@Override
	public String toString() {
		return operador;
	}

}
