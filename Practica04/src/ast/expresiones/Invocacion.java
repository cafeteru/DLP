package ast.expresiones;

import ast.sentencias.Sentencia;
import ast.util.NodoPosicion;

public class Invocacion extends NodoPosicion implements Sentencia, Expresion {
	private Variable variable;

	public Invocacion(int linea, int columna, Variable variable) {
		super(linea, columna);
	}

}
