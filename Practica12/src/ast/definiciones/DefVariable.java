package ast.definiciones;

import ast.definiciones.util.DefinicionAbstracta;
import ast.tipos.Tipo;
import visitor.Visitor;

/**
 * Clase que simboliza la definición de una variable.
 * 
 * @author Iván González Mahagamage
 */
public class DefVariable extends DefinicionAbstracta {
	private int offset;

	/**
	 * 
	 * @param linea
	 *            Linea en la que se encuentra la definición.
	 * @param columna
	 *            Columna en la que se encuentra la definición.
	 * @param nombre
	 *            Nombre de la definición.
	 * @param tipo
	 *            Tipo de la definición.
	 */
	public DefVariable(int linea, int columna, String nombre, Tipo tipo) {
		super(linea, columna, nombre, tipo);
	}

	@Override
	public String toString() {
		return "DefVariable [ambito=" + getAmbito() + ", OFFSET=" + offset
				+ ", linea=" + linea + ", columna=" + columna + ", getTipo()="
				+ getTipo() + ", getNombre()=" + getNombre() + "]";
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

}
