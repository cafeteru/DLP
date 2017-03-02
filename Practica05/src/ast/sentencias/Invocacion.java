package ast.sentencias;

import java.util.List;

import ast.expresiones.Expresion;
import ast.expresiones.Variable;
import ast.sentencias.util.SentenciaAbstracta;

public class Invocacion extends SentenciaAbstracta implements Expresion {
	private List<Expresion> expresiones;
	private Variable variable;

	public Invocacion(int linea, int columna, Variable variable,
			List<Expresion> expresiones) {
		super(linea, columna);
		this.variable = variable;
		this.expresiones = expresiones;
	}

	@Override
	public String toString() {
		return "Invocacion [expresiones=" + expresiones + ", variable="
				+ variable + ", linea=" + linea + ", columna=" + columna + "]";
	}

}
