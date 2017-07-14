package ast.sentencias;

import java.util.ArrayList;
import java.util.List;

import ast.definiciones.DefVariable;
import ast.expresiones.Expresion;
import ast.expresiones.ListaExamen;
import ast.expresiones.Variable;
import ast.sentencias.util.SentenciaAbstracta;
import ast.tipos.Tipo;
import ast.tipos.TipoFuncion;
import visitor.Visitor;

/**
 * Clase que simula la invocación a un método con o sin parámetros.
 * 
 * @author Iván González Mahagamage
 */
public class Invocacion extends SentenciaAbstracta implements Expresion {
	private List<Expresion> listaExamen;
	private List<Expresion> expresiones = new ArrayList<Expresion>();
	private Variable variable;
	private boolean lValue;
	private Tipo tipo;

	/**
	 * Constructor con parámetros.
	 * 
	 * @param linea
	 *            Linea en la que se encuentra la sentencía.
	 * @param columna
	 *            Columna en la que se encuentra la sentencía.
	 * @param variable
	 *            Variable (nombre) del método.
	 * @param expresiones
	 *            Parámetros del método.
	 */
	public Invocacion(int linea, int columna, Variable variable,
			List<Expresion> listaExamen) {
		super(linea, columna);
		this.variable = variable;
		this.listaExamen = listaExamen;
	}

	@Override
	public String toString() {
		return "Invocacion [expresiones=" + expresiones + ", variable="
				+ variable + ", linea=" + linea + ", columna=" + columna + "]";
	}

	@Override
	public boolean getLValue() {
		return lValue;
	}

	@Override
	public void setLValue(boolean lValue) {
		this.lValue = lValue;
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

	/**
	 * Método que devuelve la lista de parámetros del método.
	 * 
	 * @return Lista de parámetros del método.
	 */
	public List<Expresion> getExpresiones() {
		return expresiones;
	}

	/**
	 * Método que devuelve el variable(nombre) del método.
	 * 
	 * @return Variable del método
	 */
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

	public List<Expresion> getListaExamen() {
		return listaExamen;
	}

	public void setListaExamen(List<Expresion> listaExamen) {
		this.listaExamen = listaExamen;
	}

	public List<Tipo> examen() {
		List<Tipo> tipos = new ArrayList<Tipo>();
		TipoFuncion f = (TipoFuncion) getVariable().getDefinicion().getTipo();
		for (DefVariable a : f.getParametros()) {
			for (Expresion c : getListaExamen()) {
				if (a.getNombre().equals(((ListaExamen) c).getNombre())) {
					getExpresiones().add(((ListaExamen) c).getExpresion());
					tipos.add(((ListaExamen) c).getExpresion().getTipo());
				}
			}
		}
		return tipos;
	}
}
