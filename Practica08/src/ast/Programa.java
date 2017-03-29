package ast;

import java.util.List;

import ast.definiciones.Definicion;
import ast.util.NodoPosicion;
import visitor.Visitor;

/**
 * Clase que simula en el análizador léxico un token que es un programa
 * 
 * @author Iván González Mahagamage
 *
 */
public class Programa extends NodoPosicion implements NodoAST {
	private List<Definicion> definiciones;

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
	public Programa(int linea, int columna, List<Definicion> definiciones) {
		super(linea, columna);
		this.definiciones = definiciones;
	}

	@Override
	public String toString() {
		return "Programa [definiciones=" + definiciones + ", linea=" + linea
				+ ", columna=" + columna + "]";
	}

	public List<Definicion> getDefiniciones() {
		return definiciones;
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

}
