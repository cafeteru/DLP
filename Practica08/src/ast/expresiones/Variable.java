package ast.expresiones;

import ast.definiciones.Definicion;
import ast.expresiones.util.ExpresionAbstracta;
import ast.sentencias.Sentencia;
import visitor.Visitor;

/**
 * Clase que simula en el análizador léxico un token que es una variable.
 * 
 * @author Iván González Mahagamage
 *
 */
public class Variable extends ExpresionAbstracta implements Sentencia {
	private String clave;
	private Definicion definicion;

	/**
	 * Constructor con parámetros.
	 * 
	 * @param linea
	 *            Línea en la que se encuentra el lexema.
	 * @param columna
	 *            Columna en la que se encuentra el lexema.
	 * @param clave
	 *            Clave de la variable.
	 */
	public Variable(int linea, int columna, String clave) {
		super(linea, columna);
		this.clave = clave;
	}

	/**
	 * Método que devuelve el parámetro clase.
	 * 
	 * @return Clave de la variable.
	 */
	public String getClave() {
		return clave;
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

	public Definicion getDefinicion() {
		return definicion;
	}

	public void setDefinicion(Definicion definicion) {
		this.definicion = definicion;
	}

	@Override
	public String toString() {
		return "Variable [clave=" + clave + ", definicion=" + definicion + "]";
	}

}
