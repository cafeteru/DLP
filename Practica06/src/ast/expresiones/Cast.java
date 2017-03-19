package ast.expresiones;

import ast.expresiones.util.ExpresionAbstracta;
import ast.tipos.Tipo;
import visitor.Visitor;

public class Cast extends ExpresionAbstracta {
	private Tipo tipoCast;
	private Expresion expresion;

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

	public Tipo getTipoCast() {
		return tipoCast;
	}

	public Expresion getExpresion() {
		return expresion;
	}

}
