package ast.tipos;

import visitor.Visitor;

public class TipoEntero implements Tipo {
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
