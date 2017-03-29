package ast.tipos;

import ast.tipos.util.TipoAbstracto;
import visitor.Visitor;

public class TipoEntero extends TipoAbstracto {
	private static TipoEntero instancia;

	public static TipoEntero getInstancia() {
		if (instancia == null)
			instancia = new TipoEntero();
		return instancia;
	}

	@Override
	public String toString() {
		return "TipoEntero []";
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

	@Override
	public boolean esLogico() {
		return true;
	}

	@Override
	public Tipo aritmetica(Tipo tipo) {
		if (tipo instanceof TipoReal || tipo instanceof TipoEntero)
			return tipo;
		if (tipo instanceof TipoCaracter)
			return this;
		return null;
	}

	@Override
	public Tipo comparacion(Tipo tipo) {
		if (tipo instanceof TipoReal || tipo instanceof TipoEntero
				|| tipo instanceof TipoCaracter)
			return this;
		return null;
	}

	@Override
	public Tipo logica(Tipo tipo) {
		if (tipo.esLogico())
			return this;
		return null;
	}

	@Override
	public Tipo aritmetica() {
		return this;
	}

	@Override
	public Tipo logica() {
		return this;
	}

	@Override
	public Tipo promocionaA(Tipo tipo) {
		if (tipo instanceof TipoEntero || tipo instanceof TipoCaracter)
			return this;
		return null;
	}
}
