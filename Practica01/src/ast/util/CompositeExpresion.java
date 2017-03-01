package ast.util;

import java.util.ArrayList;
import java.util.List;

import ast.expresiones.Expresion;

/**
 * Clase intermedia que asigna una lista de expresiones a los token que la
 * necesiten.
 * 
 * @author Iván González Mahagamage
 *
 */
public class CompositeExpresion extends NodoPosicion {
	private List<Expresion> expresiones;

	/**
	 * Constructor con parámetros.
	 * 
	 * @param linea
	 *            Línea en la que se encuentra el lexema.
	 * @param columna
	 *            Columna en la que se encuentra el lexema.
	 */
	public CompositeExpresion(int linea, int columna) {
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
