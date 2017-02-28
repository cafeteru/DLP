package ast.util;

import java.util.ArrayList;
import java.util.List;

import ast.expresiones.Expresion;
import ast.sentencias.Sentencia;

/**
 * Clase intermedia que asigna una lista de expresiones a los token que la
 * necesiten.
 * 
 * @author Iván González Mahagamage
 *
 */
public class CompositeSentencia extends NodoPosicion {
	protected List<Sentencia> sentencias;

	/**
	 * Constructor con parámetros.
	 * 
	 * @param linea
	 *            Línea en la que se encuentra el lexema.
	 * @param columna
	 *            Columna en la que se encuentra el lexema.
	 */
	public CompositeSentencia(int linea, int columna) {
		super(linea, columna);
		sentencias = new ArrayList<>();
	}

	/**
	 * Método que devuelve el parámetro expresiones.
	 * 
	 * @return Lista de expresiones.
	 */
	public List<Sentencia> getSentencias() {
		return sentencias;
	}
}
