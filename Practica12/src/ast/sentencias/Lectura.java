package ast.sentencias;

import java.util.List;

import ast.expresiones.Expresion;
import ast.sentencias.util.SentenciaAbstracta;
import visitor.Visitor;

/**
 * Clase que simula una lectura.
 * 
 * @author Iván González Mahagamage
 *
 */
public class Lectura extends SentenciaAbstracta {
	private List<Expresion> expresiones;

	/**
	 * Constructor con par�metros.
	 * 
	 * @param linea
	 *            Línea en la que se encuentra el lexema.
	 * @param columna
	 *            Columna en la que se encuentra el lexema.
	 * @param sentecia
	 *            Expresión que queremos leer.
	 */
	public Lectura(int linea, int columna, List<Expresion> expresiones) {
		super(linea, columna);
		this.expresiones = expresiones;
	}

	@Override
	public String toString() {
		return "Lectura [expresiones=" + expresiones + ", linea=" + linea
				+ ", columna=" + columna + "]";
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

	/**
	 * Método que devuelve todas las expresiones a leer.
	 * 
	 * @return Expresiones a leer.
	 */
	public List<Expresion> getExpresiones() {
		return expresiones;
	}
}
