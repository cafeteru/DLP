package ast.tipos;

import visitor.Visitor;

public class TipoReal implements Tipo {
	private static TipoReal instancia;

	public static TipoReal getInstancia() {
		if (instancia == null)
			instancia = new TipoReal();
		return instancia;
	}

	@Override
	public String toString() {
		return "TipoReal []";
	}

	@Override
	public int getLinea() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getColumna() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}
}
