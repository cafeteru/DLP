package ast;

import java.util.List;

import ast.sentencias.Sentencia;
import ast.util.NodoPosicion;

/**
 * Clase que simula en el an�lizador l�xico un token que es un programa
 * 
 * @author Iv�n Gonz�lez Mahagamage
 *
 */
public class Programa extends NodoPosicion implements NodoAST {
	private List<Sentencia> sentencias;

	/**
	 * Constructor con par�metros.
	 * 
	 * @param linea
	 *            L�nea en la que se encuentra el lexema.
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
	 * M�todo que devuelve el par�metro sentencias.
	 * 
	 * @return Conjunto de tokens que forman el programa.
	 */
	public List<Sentencia> getSentencias() {
		return sentencias;
	}

	/**
	 * Redefinici�n del m�todo toString().
	 */
	@Override
	public String toString() {
		return "Programa";
	}

}
