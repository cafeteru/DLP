package ast.sentencias;

import java.util.ArrayList;
import java.util.List;

import ast.expresiones.Expresion;
import ast.expresiones.Variable;
import ast.util.NodoPosicion;

public class Invocacion extends NodoPosicion implements Sentencia, Expresion {
	private Variable variable;
	private List<Expresion> argumentos = new ArrayList<>();

	public Invocacion(int linea, int columna, Variable variable,
			List<Expresion> argumentos) {
		super(linea, columna);
		this.variable = variable;
		this.argumentos = argumentos;
	}

}
