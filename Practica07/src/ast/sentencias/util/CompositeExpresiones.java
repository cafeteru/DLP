package ast.sentencias.util;

import java.util.ArrayList;
import java.util.List;

import ast.expresiones.Expresion;
import ast.sentencias.Sentencia;
import ast.util.NodoPosicion;
import visitor.Visitor;

public abstract class CompositeExpresiones extends NodoPosicion
		implements Sentencia {
	protected List<Expresion> expresiones;

	/**
	 * Constructor con parámetros.
	 * 
	 * @param linea
	 *            Línea en la que se encuentra el lexema.
	 * @param columna
	 *            Columna en la que se encuentra el lexema.
	 */
	public CompositeExpresiones(int linea, int columna) {
		super(linea, columna);
		expresiones = new ArrayList<>();
	}

	/**
	 * Método que devuelve el parámetro expresiones.
	 * 
	 * @return Lista de expresiones.
	 */
	public List<Expresion> getExpresiones() {
		return expresiones;
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return accept2(v, o);
	}

	protected abstract Object accept2(Visitor v, Object o);
}
