package ast.sentencias;

import java.util.List;

import ast.expresiones.Expresion;
import ast.sentencias.util.SentenciaAbstracta;

public class SentenciaWhile extends SentenciaAbstracta {
	private Expresion condicion;
	private List<Sentencia> sentencias;

	public SentenciaWhile(int linea, int columna, Expresion expresion,
			List<Sentencia> cuerpo) {
		super(linea, columna);
		condicion = expresion;
		sentencias = cuerpo;
	}

	@Override
	public String toString() {
		return "SentenciaWhile [condicion=" + condicion + ", sentencias="
				+ sentencias + ", linea=" + linea + ", columna=" + columna
				+ "]";
	}

}
