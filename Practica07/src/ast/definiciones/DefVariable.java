package ast.definiciones;

import ast.definiciones.util.DefinicionAbstracta;
import ast.tipos.Tipo;
import visitor.Visitor;

public class DefVariable extends DefinicionAbstracta {
	private int ambito;
	private int offset;

	public DefVariable(int linea, int columna, String nombre, Tipo tipo) {
		super(linea, columna, nombre, tipo);
	}

	@Override
	public String toString() {
		return "DefVariable [ambito=" + ambito + ", OFFSET=" + offset
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


}
