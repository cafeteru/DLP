package ast.expresiones;

import ast.util.NodoPosicion;

/**
 * Clase que simula en el an�lizador l�xico un token que es una variable.
 * 
 * @author Iv�n Gonz�lez Mahagamage
 *
 */
public class Variable extends NodoPosicion implements Expresion {
	private String clave;

	/**
	 * Constructor con par�metros.
	 * 
	 * @param linea
	 *            L�nea en la que se encuentra el lexema.
	 * @param columna
	 *            Columna en la que se encuentra el lexema.
	 * @param clave
	 *            Clave de la variable.
	 */
	public Variable(int linea, int columna, String clave) {
		super(linea, columna);
		this.clave = clave;
	}

	/**
	 * M�todo que devuelve el par�metro clase.
	 * 
	 * @return Clave de la variable.
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * Redefinici�n del m�todo toString().
	 */
	@Override
	public String toString() {
		return clave;
	}
}
