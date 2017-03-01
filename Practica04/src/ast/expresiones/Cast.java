package ast.expresiones;

import ast.tipos.Tipo;
import ast.util.NodoPosicion;

public class Cast extends NodoPosicion implements Expresion {
	private Tipo tipoCast;
	private Expresion expresion;

	public Cast(int linea, int columna, Tipo tipoCast, Expresion expresion) {
		super(linea, columna);
		this.tipoCast = tipoCast;
		this.expresion = expresion;
	}

	@Override
	public String toString() {
		return "Cast [tipoCast=" + tipoCast + ", expresion=" + expresion + ", linea=" + linea + ", columna=" + columna
				+ "]";
	}
	
	
}
