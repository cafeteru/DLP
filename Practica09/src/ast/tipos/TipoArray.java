package ast.tipos;

import ast.tipos.util.TipoAbstracto;
import visitor.Visitor;

public class TipoArray extends TipoAbstracto {
	private int tama�o;
	private Tipo tipo;

	public TipoArray(int tama�o, Tipo tipo) {
		this.tama�o = tama�o;
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "TipoArray [tama�o=" + tama�o + ", tipo=" + tipo + "]";
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

	public int getTama�o() {
		return tama�o;
	}

	public Tipo getTipo() {
		return tipo;
	}

	@Override
	public Tipo corchetes(Tipo tipo) {
		if (tipo instanceof TipoEntero)
			return this.tipo;
		return null;
	}

}
