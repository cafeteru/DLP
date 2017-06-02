package ast.expresiones.util;

import ast.expresiones.Expresion;
import ast.tipos.Tipo;
import ast.util.NodoPosicion;

public abstract class ExpresionAbstracta extends NodoPosicion
		implements Expresion {
	protected boolean lValue;
	protected Tipo tipo;

	public ExpresionAbstracta(int linea, int columna) {
		super(linea, columna);
	}

	@Override
	public boolean getLValue() {
		return lValue;
	}

	@Override
	public void setLValue(boolean lValue) {
		this.lValue = lValue;
	}

	@Override
	public Tipo getTipo() {
		return tipo;
	}

	@Override
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

}
