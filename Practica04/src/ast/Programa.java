package ast;

import java.util.List;

import ast.sentencias.Sentencia;
import ast.util.NodoPosicion;

/**
 * Clase que simula en el análizador léxico un token que es un programa
 * 
 * @author Iván González Mahagamage
 *
 */
public class Programa extends NodoPosicion implements NodoAST {
	private List<Sentencia> sentencias;

	/**
	 * Constructor con parámetros.
	 * 
	 * @param linea
	 *            Línea en la que se encuentra el lexema.
	 * @param columna
	 *            Columna en la que se encuentra el lexema.
	 * @param sentencias
	 *            Conjunto de tokens que forman el programa.
	 */
	public Programa(int linea, int columna, List<Sentencia> sentencias) {
		super(linea, columna);
		this.sentencias = sentencias;
	}

	/**
	 * Método que devuelve el parámetro sentencias.
	 * 
	 * @return Conjunto de tokens que forman el programa.
	 */
	public List<Sentencia> getSentencias() {
		return sentencias;
	}

	/**
	 * Redefinición del método toString().
	 */
	@Override
	public String toString() {
		return "Programa";
	}

}
