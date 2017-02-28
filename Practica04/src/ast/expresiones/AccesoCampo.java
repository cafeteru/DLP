package ast.expresiones;

import ast.util.NodoPosicion;

public class AccesoCampo extends NodoPosicion implements Expresion {
	private String nombreCampo;
	private Expresion variable;

	public AccesoCampo(int linea, int columna, String nombreCampo,
			Expresion variable) {
		super(linea, columna);
		this.nombreCampo = nombreCampo;
		this.variable = variable;
	}
}
