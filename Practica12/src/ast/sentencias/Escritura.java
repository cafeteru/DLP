package ast.sentencias;

import java.util.List;

import ast.expresiones.Expresion;
import ast.sentencias.util.SentenciaAbstracta;
import visitor.Visitor;

/**
 * Clase que simula una escritura.
 * 
 * @author Iván González Mahagamage
 *
 */
public class Escritura extends SentenciaAbstracta {
	private List<Expresion> expresiones;

	/**
	 * Constructor con parámetros.
	 * 
	 * @param linea
	 *            Línea en la que se encuentra el lexema.
	 * @param columna
	 *            Columna en la que se encuentra el lexema.
	 * @param expresion
	 *            Expresión que queremos escribir.
	 */
	public Escritura(int linea, int columna, List<Expresion> expresiones) {
		super(linea, columna);
		this.expresiones = expresiones;
	}

	@Override
	public String toString() {
		return "Escritura [expresiones=" + expresiones + ", linea=" + linea
				+ ", columna=" + columna + "]";
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

	/**
	 * Método que devuelve todas las expresiones a escribir.
	 * 
	 * @return Expresiones a escribir
	 */
	public List<Expresion> getExpresiones() {
		return expresiones;
	}

}
