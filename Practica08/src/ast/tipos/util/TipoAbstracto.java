package ast.tipos.util;

import java.util.List;

import ast.tipos.Tipo;

public abstract class TipoAbstracto implements Tipo {

	@Override
	public boolean esLogico() {
		return false;
	}

	@Override
	public Tipo parentesis(List<Tipo> tipos) {
		return null;
	}
}
