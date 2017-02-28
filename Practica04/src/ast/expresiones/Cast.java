package ast.expresiones;

import ast.tipos.Tipo;

public class Cast extends OperacionUnaria {
	private Tipo tipoCast;

	public Cast(Expresion expresion, Tipo tipo) {
		super(expresion);
		this.tipoCast = tipo;
	}
}
