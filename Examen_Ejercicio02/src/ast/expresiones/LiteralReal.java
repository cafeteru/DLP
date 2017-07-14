package ast.expresiones;

import ast.expresiones.util.ExpresionAbstracta;
import visitor.Visitor;

/**
 * Clase que representa un literal real.
 * 
 * @author Iván González Mahagamage
 */
public class LiteralReal extends ExpresionAbstracta {
	private double valor;

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
	public LiteralReal(int linea, int columna, double valor) {
		super(linea, columna);
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "LiteralReal [valor=" + valor + ", linea=" + linea + ", columna="
				+ columna + "]";
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

	/**
	 * Método que devuelve el valor de la expresión.
	 * 
	 * @return Expresión.
	 */
	public double getValor() {
		return valor;
	}

}
