package ast.expresiones;

import ast.util.NodoPosicion;

/**
 * Clase que simula en el análizador léxico un token que es un literal entero.
 * (Un número)
 * 
 * @author Iván González Mahagamage
 *
 */
public class LiteralEntero extends NodoPosicion implements Expresion {
	private int valor;

	/**
	 * Constructor con parámetros.
	 * 
	 * @param linea
	 *            Línea en la que se encuentra el lexema.
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
	 * Método que devuelve el parámetro valor.
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
