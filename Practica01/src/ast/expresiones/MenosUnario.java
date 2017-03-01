package ast.expresiones;

import ast.util.NodoPosicion;

/**
 * Clase que simula en el análizador léxico un token que es un menos unario.
 * (Vuelve a negativo el valor de la expresión a su derecha.)
 * 
 * @author Iván González Mahagamage
 *
 */
public class MenosUnario extends NodoPosicion implements Expresion {
	private Expresion expresion;

	/**
	 * Constructor con parámetros.
	 * 
	 * @param linea
	 *            Línea en la que se encuentra el lexema.
	 * @param columna
	 *            Columna en la que se encuentra el lexema.
	 * @param expresion
	 *            Expresión a la cual invierte su valor.
	 */
	public MenosUnario(int linea, int columna, Expresion expresion) {
		super(linea, columna);
		this.expresion = expresion;
	}

	/**
	 * Método que devuelve el parámetro expresion.
	 * 
	 * @return Expresión a la cual invierte su valor.
	 */
	public Expresion getExpresion() {
		return expresion;
	}

	/**
	 * Redefinición del método toString().
	 */
	@Override
	public String toString() {
		return "-";
	}
}
