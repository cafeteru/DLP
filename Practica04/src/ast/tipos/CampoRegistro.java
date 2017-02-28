package ast.tipos;

public class CampoRegistro {
	private String nombre;
	private int OFFSET;
	private Tipo tipo;
	private TipoRegistro tipoRegistro;

	public CampoRegistro(String nombre, Tipo tipo, TipoRegistro tipoRegistro) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.tipoRegistro = tipoRegistro;
	}
}
