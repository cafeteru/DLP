package ast.expresiones.util;

import ast.expresiones.Expresion;
import ast.util.NodoPosicion;

public class ExpresionAbstracta extends NodoPosicion implements Expresion {

	public ExpresionAbstracta(int linea, int columna) {
		super(linea, columna);
	}

}
