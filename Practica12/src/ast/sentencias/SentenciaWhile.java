package ast.sentencias;

import java.util.List;

import ast.expresiones.Expresion;
import ast.sentencias.util.SentenciaAbstracta;
import visitor.Visitor;

public class SentenciaWhile extends SentenciaAbstracta {
	private Expresion condicion;
	private List<Sentencia> sentencias;

	public SentenciaWhile(int linea, int columna, Expresion expresion,
			List<Sentencia> cuerpo) {
		super(linea, columna);
		condicion = expresion;
		sentencias = cuerpo;
	}

	@Override
	public String toString() {
		return "SentenciaWhile [condicion=" + condicion + ", sentencias="
				+ sentencias + ", linea=" + linea + ", columna=" + columna
				+ "]";
	}

	public Expresion getCondicion() {
		return condicion;
	}

	public List<Sentencia> getSentencias() {
		return sentencias;
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

	@Override
	public int calcularLineaComienzo() {
		int aux = 0;
		for (Sentencia s : getSentencias())
			aux += s.calcularLineaComienzo();
		return aux + 1;
	}
}
