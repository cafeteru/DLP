package ast.sentencias;

import java.util.List;

import ast.expresiones.Expresion;
import ast.sentencias.util.SentenciaAbstracta;
import visitor.Visitor;

/**
 * Clase que simula una sentencia while.
 * 
 * @author Iván González Mahagamage
 */
public class SentenciaWhile extends SentenciaAbstracta {
	private Expresion condicion;
	private List<Sentencia> sentencias;

	/**
	 * Constructor con parámetros.
	 * 
	 * @param linea
	 *            Linea en la que se encuentra la sentencía.
	 * @param columna
	 *            Columna en la que se encuentra la sentencía.
	 * @param expresion
	 *            Expresión a examinar.
	 * @param cuerpo
	 *            Lista de sentencias que se ejecutan si se cumple la función.
	 */
	public SentenciaWhile(int linea, int columna, Expresion expresion,
			List<Sentencia> cuerpo) {
		super(linea, columna);
		condicion = expresion;
		sentencias = cuerpo;
		setLinea(condicion.getLinea());
	}

	@Override
	public String toString() {
		return "SentenciaWhile [condicion=" + condicion + ", sentencias="
				+ sentencias + ", linea=" + linea + ", columna=" + columna
				+ "]";
	}

	/**
	 * Método que devuelve todas la condición.
	 * 
	 * @return Expresion.
	 */
	public Expresion getCondicion() {
		return condicion;
	}

	/**
	 * Método que devuelve la lista de sentencias que se ejecutan si se cumple
	 * la condición.
	 * 
	 * @return List<Sentencia>.
	 */
	public List<Sentencia> getSentencias() {
		return sentencias;
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}
}
