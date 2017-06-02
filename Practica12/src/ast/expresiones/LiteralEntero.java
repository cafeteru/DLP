package ast.expresiones;

import ast.expresiones.util.ExpresionAbstracta;
import visitor.Visitor;

/**
 * Clase que simula un literal entero.
 * 
 * @author Iván González Mahagamage
 *
 */
public class LiteralEntero extends ExpresionAbstracta {
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
	 * M�todo que devuelve el parámetro valor.
	 * 
	 * @return Valor entero del literal entero.
	 */
	public int getValor() {
		return valor;
	}

	@Override
	public String toString() {
		return "LiteralEntero [valor=" + valor + ", linea=" + linea
				+ ", columna=" + columna + "]";
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}
}
