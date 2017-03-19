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
	 * Constructor con par�metros.
	 * 
	 * @param linea
	 *            L�nea en la que se encuentra el lexema.
	 * @param columna
	 *            Columna en la que se encuentra el lexema.
	 */
	public CompositeExpresiones(int linea, int columna) {
		super(linea, columna);
		expresiones = new ArrayList<>();
	}

	/**
	 * M�todo que devuelve el par�metro expresiones.
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
