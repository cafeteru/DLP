package ast.tipos;

import ast.NodoAST;
import ast.tipos.util.TipoAbstracto;
import manejadorerrores.ME;
import visitor.Visitor;

public class TipoError extends TipoAbstracto {
	private String mensaje;
	private int linea, columna;

	public TipoError(int linea, int columna, String mensaje) {
		this.linea = linea;
		this.columna = columna;
		this.mensaje = mensaje;
		ME.getME().addError(this);
	}

	public TipoError(NodoAST ast, String mensaje) {
		this.linea = ast.getLinea();
		this.columna = ast.getLinea();
		this.mensaje = mensaje;
		ME.getME().addError(this);
	}

	public String getMensaje() {
		return mensaje;
	}

	@Override
	public String toString() {
		return "TipoError [mensaje=" + mensaje + ", linea=" + linea
				+ ", columna=" + columna + "]";
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

}
