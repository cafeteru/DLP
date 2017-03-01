package ast.sentencias;

import ast.expresiones.Expresion;
import ast.util.NodoPosicion;

public class Return extends NodoPosicion implements Sentencia{
	private Expresion expresion;

	public Return(int linea, int columna, Expresion expresion) {
		super(linea, columna);
		this.expresion = expresion;
	}

	@Override
	public String toString() {
		return "Return [expresion=" + expresion + ", linea=" + linea + ", columna=" + columna + "]";
	}
	
	

}
