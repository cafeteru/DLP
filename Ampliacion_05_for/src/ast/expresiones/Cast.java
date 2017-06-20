package ast.expresiones;

import ast.expresiones.util.ExpresionAbstracta;
import ast.tipos.Tipo;
import visitor.Visitor;

/**
 * Clase que representa un cast
 * 
 * @author Iván González Mahagamage
 */
public class Cast extends ExpresionAbstracta {
	private Tipo tipoCast;
	private Expresion expresion;

	/**
	 * Constructor con parámetros.
	 * 
	 * @param linea
	 *            Linea en la que se encuentra la expresión.
	 * @param columna
	 *            Columna en la que se encuentra la expresión.
	 * @param tipoCast
	 *            Tipo del cast
	 * @param expresion
	 *            Expresión a la cual vamos a cambiar el tipo.
	 */
	public Cast(int linea, int columna, Tipo tipoCast, Expresion expresion) {
		super(linea, columna);
		this.tipoCast = tipoCast;
		this.expresion = expresion;
	}

	@Override
	public String toString() {
		return "Cast [tipoCast=" + tipoCast + ", expresion=" + expresion
				+ ", linea=" + linea + ", columna=" + columna + "]";
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

	/**
	 * Método que devuelve el tipo del cast.
	 * 
	 * @return Tipo del caste.
	 */
	public Tipo getTipoCast() {
		return tipoCast;
	}

	/**
	 * Método que devuelve la expresión a castear.
	 * 
	 * @return Expresión.
	 */
	public Expresion getExpresion() {
		return expresion;
	}

}
