package ast.tipos;

import ast.tipos.util.TipoAbstracto;
import visitor.Visitor;

public class TipoVoid extends TipoAbstracto {
	private static TipoVoid instancia;

	public static TipoVoid getInstancia() {
		if (instancia == null)
			instancia = new TipoVoid();
		return instancia;
	}

	@Override
	public String toString() {
		return "TipoVoid []";
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

	@Override
	public int nBytes() {
		return 0;
	}
}
