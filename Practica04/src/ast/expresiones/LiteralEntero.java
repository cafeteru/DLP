package ast.expresiones;

import ast.util.NodoPosicion;

/**
 * Clase que simula en el an�lizador l�xico un token que es un literal entero.
 * (Un n�mero)
 * 
 * @author Iv�n Gonz�lez Mahagamage
 *
 */
public class LiteralEntero extends NodoPosicion implements Expresion {
	private int valor;

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
	public LiteralEntero(int linea, int columna, int valor) {
		super(linea, columna);
		this.valor = valor;
	}

	/**
	 * M�todo que devuelve el par�metro valor.
	 * 
	 * @return Valor entero del literal entero.
	 */
	public int getValor() {
		return valor;
	}

	@Override
	public String toString() {
		return "LiteralEntero [valor=" + valor + ", linea=" + linea + ", columna=" + columna + "]";
	}



}
