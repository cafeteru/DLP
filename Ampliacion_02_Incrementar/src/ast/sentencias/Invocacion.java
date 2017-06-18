package ast.sentencias;

import java.util.List;

import ast.expresiones.Expresion;
import ast.expresiones.Variable;
import ast.sentencias.util.SentenciaAbstracta;
import ast.tipos.Tipo;
import visitor.Visitor;

/**
 * Clase que simula la invocación a un método con o sin parámetros.
 * 
 * @author Iván González Mahagamage
 */
public class Invocacion extends SentenciaAbstracta implements Expresion {
	private List<Expresion> expresiones;
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

}
