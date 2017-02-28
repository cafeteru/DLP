package ast.tipos;

public class TipoCaracter implements Tipo {
	private static TipoCaracter instancia;

	public static TipoCaracter getInstancia() {
		if (instancia == null)
			instancia = new TipoCaracter();
		return instancia;
	}
}
