package ast.sentencias;

import java.util.List;

import ast.expresiones.Expresion;
import ast.expresiones.Variable;
import ast.sentencias.util.SentenciaAbstracta;
import ast.tipos.Tipo;
import visitor.Visitor;

public class Invocacion extends SentenciaAbstracta implements Expresion {
	private List<Expresion> expresiones;
	private Variable variable;
	private boolean lValue;
	private Tipo tipo;

	public Invocacion(int linea, int columna, Variable variable,
			List<Expresion> expresiones) {
		super(linea, columna);
		this.variable = variable;
		this.expresiones = expresiones;
	}

	@Override
	public boolean getLValue() {
		return lValue;
	}

	@Override
	public void setLValue(boolean lValue) {
		this.lValue = lValue;
	}

	public List<Expresion> getExpresiones() {
		return expresiones;
	}

	public Variable getVariable() {
		return variable;
	}

	@Override
	public Tipo getTipo() {
		return tipo;
	}

	@Override
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

	@Override
	public String toString() {
		return "Invocacion [expresiones=" + expresiones + ", variable="
				+ variable + ", linea=" + linea + ", columna=" + columna + "]";
	}

}
