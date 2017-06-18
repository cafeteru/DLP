package ast.expresiones;

import ast.expresiones.util.ExpresionAbstracta;
import visitor.Visitor;

/**
 * Clase que representa el acceso a un elemento de un array.
 * 
 * @author Iván González Mahagamage
 */
public class AccesoArray extends ExpresionAbstracta {
	private Expresion izq;
	private Expresion der;

	/**
	 * Constructor con parámetros.
	 * 
	 * @param linea
	 *            Linea en la que se encuentra la expresión.
	 * @param columna
	 *            Columna en la que se encuentra la expresión.
	 * @param izq
	 *            Expresión situada a la izquierda. (Array)
	 * @param der
	 *            Expresión situada a la derecha. (Posición normalmente)
	 */
	public AccesoArray(int linea, int columna, Expresion izq, Expresion der) {
		super(linea, columna);
		this.izq = izq;
		this.der = der;
	}

	@Override
	public String toString() {
		return "AccesoArray [izq=" + izq + ", der=" + der + ", linea=" + linea
				+ ", columna=" + columna + "]";
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

	/**
	 * Método que devuelve la expresión izquierda.
	 * 
	 * @return Expresión de la izquierda.
	 */
	public Expresion getIzq() {
		return izq;
	}

	/**
	 * Método que devuelve la expresión derecha.
	 * 
	 * @return Expresión de la derecha.
	 */
	public Expresion getDer() {
		return der;
	}

}
