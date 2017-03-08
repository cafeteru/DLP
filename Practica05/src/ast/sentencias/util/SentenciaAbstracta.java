package ast.sentencias.util;

import ast.sentencias.Sentencia;
import ast.util.NodoPosicion;

public class SentenciaAbstracta extends NodoPosicion implements Sentencia {

	public SentenciaAbstracta(int linea, int columna) {
		super(linea, columna);
	}

}
