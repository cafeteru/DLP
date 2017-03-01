package ast.expresiones;

import ast.util.NodoPosicion;

public class AccesoArray extends NodoPosicion implements Expresion {
	private Expresion izq;
	private Expresion der;

	public AccesoArray(int linea, int columna, Expresion izq, Expresion der) {
		super(linea, columna);
		this.izq = izq;
		this.der = der;
	}

	@Override
	public String toString() {
		return "AccesoArray [izq=" + izq + ", der=" + der + ", linea=" + linea + ", columna=" + columna + "]";
	}
	
	
}
