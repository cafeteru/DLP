package ast.util;

import java.util.ArrayList;
import java.util.List;

import ast.expresiones.Expresion;
import ast.sentencias.Sentencia;

/**
 * Clase intermedia que asigna una lista de expresiones a los token que la
 * necesiten.
 * 
 * @author Iv�n Gonz�lez Mahagamage
 *
 */
public class CompositeSentencia extends NodoPosicion {
	protected List<Sentencia> sentencias;

	/**
	 * Constructor con par�metros.
	 * 
	 * @param linea
	 *            L�nea en la que se encuentra el lexema.
	 * @param columna
	 *            Columna en la que se encuentra el lexema.
	 */
	public CompositeSentencia(int linea, int columna) {
		super(linea, columna);
		sentencias = new ArrayList<>();
	}

	/**
	 * M�todo que devuelve el par�metro expresiones.
	 * 
	 * @return Lista de expresiones.
	 */
	public List<Sentencia> getSentencias() {
		return sentencias;
	}
}
