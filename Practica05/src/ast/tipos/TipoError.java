package ast.tipos;

import ast.util.NodoPosicion;
import manejadorerrores.ME;

public class TipoError extends NodoPosicion implements Tipo {
	private String mensaje;

	public TipoError(int linea, int columna, String mensaje) {
		super(linea, columna);
		this.mensaje = mensaje;
		ME.getME().addError(this);
	}

	public TipoError(NodoPosicion ast, String mensaje) {
		super(ast.getLinea(), ast.getLinea());
		this.mensaje = mensaje;
	}

	public String getMensaje() {
		return mensaje;
	}

	@Override
	public String toString() {
		return "TipoError [mensaje=" + mensaje + ", linea=" + linea
				+ ", columna=" + columna + "]";
	}

}
