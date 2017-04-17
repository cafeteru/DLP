package ast.tipos;

import ast.tipos.util.TipoAbstracto;
import visitor.Visitor;

public class TipoCaracter extends TipoAbstracto {
	private static TipoCaracter instancia;

	public static TipoCaracter getInstancia() {
		if (instancia == null)
			instancia = new TipoCaracter();
		return instancia;
	}

	@Override
	public String toString() {
		return "TipoCaracter []";
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
			return TipoEntero.getInstancia();
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
	public Tipo aritmetica() {
		return TipoEntero.getInstancia();
	}

	@Override
	public Tipo logica() {
		return TipoEntero.getInstancia();
	}

	@Override
	public Tipo promocionaA(Tipo tipo) {
		if (tipo instanceof TipoCaracter)
			return this;
		return null;
	}

	@Override
	public Tipo cast(Tipo tipo) {
		return tipo;
	}

	@Override
	public int nBytes() {
		return 1;
	}

	@Override
	public String sufijo() {
		return "b";
	}

	@Override
	public Tipo Mayor(Tipo tipo) {
		return tipo;
	}
}
