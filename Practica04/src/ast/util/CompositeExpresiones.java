package ast.util;

import java.util.ArrayList;
import java.util.List;

import ast.expresiones.Expresion;

public class CompositeExpresiones extends NodoPosicion {
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
}
