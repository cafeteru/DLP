package ast.expresiones;

import ast.util.NodoPosicion;

public class AccesoCampo extends NodoPosicion implements Expresion {
	private Expresion expresion;
	private String nombreCampo;

	public AccesoCampo(int linea, int columna, String nombreCampo,
			Expresion expresion) {
		super(linea, columna);
		this.nombreCampo = nombreCampo;
		this.expresion = expresion;
	}

	@Override
	public String toString() {
		return "AccesoCampo [expresion=" + expresion + ", nombreCampo=" + nombreCampo + ", linea=" + linea
				+ ", columna=" + columna + "]";
	}
	
	
}
