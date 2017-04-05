package ast.tipos;

public class TipoArray implements Tipo {
	private int tamaño;
	private Tipo tipo;

	public TipoArray(int tamaño, Tipo tipo) {
		this.tamaño = tamaño;
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "TipoArray [tamaño=" + tamaño + ", tipo=" + tipo + "]";
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

}
