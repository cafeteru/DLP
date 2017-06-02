package ast.expresiones;

import ast.expresiones.util.ExpresionAbstracta;
import visitor.Visitor;

/**
 * Clase que representa el acceso a un campo de un objeto.
 * 
 * @author Iván González Mahagamage
 */
public class AccesoCampo extends ExpresionAbstracta {
	private Expresion expresion;
	private String nombreCampo;

	/**
	 * Constructor con parámetros.
	 * 
	 * @param linea
	 *            Linea en la que se encuentra la expresión.
	 * @param columna
	 *            Columna en la que se encuentra la expresión.
	 * @param expresion
	 *            Expresión que contiene. (Objeto)
	 * @param nombreCampo
	 *            Nombre del campo al que queremos acceder.
	 */
	public AccesoCampo(int linea, int columna, Expresion expresion,
			String nombreCampo) {
		super(linea, columna);
		this.nombreCampo = nombreCampo;
		this.expresion = expresion;
	}

	@Override
	public String toString() {
		return "AccesoCampo [expresion=" + expresion + ", nombreCampo="
				+ nombreCampo + ", linea=" + linea + ", columna=" + columna
				+ "]";
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

	/**
	 * Método que devuelve la expresión.
	 * 
	 * @return Expresión.
	 */
	public Expresion getExpresion() {
		return expresion;
	}

	/**
	 * Método que devuelve el nombre del campo.
	 * 
	 * @return Nombre del campo.
	 */
	public String getNombreCampo() {
		return nombreCampo;
	}

}
