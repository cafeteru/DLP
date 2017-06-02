package ast.sentencias;

import ast.expresiones.Expresion;
import ast.sentencias.util.SentenciaAbstracta;
import visitor.Visitor;

/**
 * Clase que simula el retorno de un método.
 * 
 * @author Iván González Mahagamage
 */
public class Return extends SentenciaAbstracta {
	private Expresion expresion;

	/**
	 * Constructor con parámetros.
	 * 
	 * @param linea
	 *            Linea en la que se encuentra la sentencía.
	 * @param columna
	 *            Columna en la que se encuentra la sentencía.
	 * @param expresion
	 *            Expresión que devuelve.
	 */
	public Return(int linea, int columna, Expresion expresion) {
		super(linea, columna);
		this.expresion = expresion;
	}

	@Override
	public String toString() {
		return "Return [expresion=" + expresion + ", linea=" + linea
				+ ", columna=" + columna + "]";
	}

	/**
	 * Método que devuelve la expresión indicada.
	 * 
	 * @return Expresión.
	 */
	public Expresion getExpresion() {
		return expresion;
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}
}
