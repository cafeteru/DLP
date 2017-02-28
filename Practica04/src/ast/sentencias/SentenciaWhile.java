package ast.sentencias;

import java.util.List;

import ast.expresiones.Expresion;
import ast.util.CompositeSentencia;

public class SentenciaWhile extends CompositeSentencia {
	private Expresion condicion;

	public SentenciaWhile(int linea, int columna, Expresion expresion,
			List<Sentencia> cuerpo) {
		super(linea, columna);
		condicion = expresion;
		sentencias = cuerpo;
	}

}
