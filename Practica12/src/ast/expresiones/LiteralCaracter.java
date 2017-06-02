package ast.expresiones;

import ast.expresiones.util.ExpresionAbstracta;
import visitor.Visitor;

/**
 * Clase que representa un caracter.
 * 
 * @author Iván González Mahagamage
 */
public class LiteralCaracter extends ExpresionAbstracta {
	private char valor;

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
	public LiteralCaracter(int linea, int columna, char valor) {
		super(linea, columna);
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "LiteralCaracter [valor=" + valor + ", linea=" + linea
				+ ", columna=" + columna + "]";
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

	/**
	 * Método que devuelve el caracter.
	 * 
	 * @return Caracter.
	 */
	public char getValor() {
		return valor;
	}

}
