package ast.tipos;

import ast.tipos.util.TipoAbstracto;
import visitor.Visitor;

public class TipoArray extends TipoAbstracto {
	private int tamaño;
	private Tipo tipo;

	public TipoArray(int tamaño, Tipo tipo) {
		this.tamaño = tamaño;
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "TipoArray [tama�o=" + tamaño + ", tipo=" + tipo + "]";
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

	public int getTamaño() {
		return tamaño;
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
