package ast.expresiones;

import ast.expresiones.util.ExpresionAbstracta;
import visitor.Visitor;

public class AccesoArray extends ExpresionAbstracta {
	private Expresion izq;
	private Expresion der;

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

	public Expresion getIzq() {
		return izq;
	}

	public Expresion getDer() {
		return der;
	}

}
