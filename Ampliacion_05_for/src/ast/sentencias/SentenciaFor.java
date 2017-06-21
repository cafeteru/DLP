package ast.sentencias;

import java.util.List;

import ast.expresiones.Expresion;
import ast.sentencias.util.SentenciaAbstracta;
import visitor.Visitor;

/**
 * Clase que simula una sentencia for.
 * 
 * @author Iván González Mahagamage
 */
public class SentenciaFor extends SentenciaAbstracta {
	private Expresion fin;
	private Sentencia inicio, paso;
	private List<Sentencia> sentencias;

	public SentenciaFor(int linea, int columna, Sentencia inicio, Expresion fin,
			Sentencia paso, List<Sentencia> sentencias) {
		super(linea, columna);
		this.fin = fin;
		this.inicio = inicio;
		this.paso = paso;
		this.sentencias = sentencias;
	}

	public Expresion getFin() {
		return fin;
	}

	public void setFin(Expresion fin) {
		this.fin = fin;
	}

	public Sentencia getInicio() {
		return inicio;
	}

	public void setInicio(Sentencia inicio) {
		this.inicio = inicio;
	}

	public Sentencia getPaso() {
		return paso;
	}

	public void setPaso(Sentencia paso) {
		this.paso = paso;
	}

	public List<Sentencia> getSentencias() {
		return sentencias;
	}

	public void setSentencias(List<Sentencia> sentencias) {
		this.sentencias = sentencias;
	}

	@Override
	public String toString() {
		return "SentenciaFor [fin=" + fin + ", inicio=" + inicio + ", paso="
				+ paso + ", sentencias=" + sentencias + "]";
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}
}
