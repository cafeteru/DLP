package ast.expresiones;

import ast.util.NodoPosicion;

/**
 * Clase que simula en el an�lizador l�xico un token que es un menos unario.
 * (Vuelve a negativo el valor de la expresi�n a su derecha.)
 * 
 * @author Iv�n Gonz�lez Mahagamage
 *
 */
public class MenosUnario extends NodoPosicion implements Expresion {
	private Expresion expresion;

	/**
	 * Constructor con par�metros.
	 * 
	 * @param linea
	 *            L�nea en la que se encuentra el lexema.
	 * @param columna
	 *            Columna en la que se encuentra el lexema.
	 * @param expresion
	 *            Expresi�n a la cual invierte su valor.
	 */
	public MenosUnario(int linea, int columna, Expresion expresion) {
		super(linea, columna);
		this.expresion = expresion;
	}

	/**
	 * M�todo que devuelve el par�metro expresion.
	 * 
	 * @return Expresi�n a la cual invierte su valor.
	 */
	public Expresion getExpresion() {
		return expresion;
	}

	/**
	 * Redefinici�n del m�todo toString().
	 */
	@Override
	public String toString() {
		return "-";
	}
}
