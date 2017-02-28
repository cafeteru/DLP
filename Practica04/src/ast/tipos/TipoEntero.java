package ast.tipos;

public class TipoEntero implements Tipo {
	private static TipoEntero instancia;

	public static TipoEntero getInstancia() {
		if (instancia == null)
			instancia = new TipoEntero();
		return instancia;
	}
}
