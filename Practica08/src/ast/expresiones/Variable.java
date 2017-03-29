package ast.expresiones;

import ast.definiciones.Definicion;
import ast.expresiones.util.ExpresionAbstracta;
import ast.sentencias.Sentencia;
import visitor.Visitor;

/**
 * Clase que simula en el an�lizador l�xico un token que es una variable.
 * 
 * @author Iv�n Gonz�lez Mahagamage
 *
 */
public class Variable extends ExpresionAbstracta implements Sentencia {
	private String clave;
	private Definicion definicion;

	/**
	 * Constructor con par�metros.
	 * 
	 * @param linea
	 *            L�nea en la que se encuentra el lexema.
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
	 * M�todo que devuelve el par�metro clase.
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
