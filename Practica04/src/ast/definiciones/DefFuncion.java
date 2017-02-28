package ast.definiciones;

import java.util.List;

import ast.sentencias.Sentencia;
import ast.tipos.Tipo;

public class DefFuncion extends DefinicionAbstracta {
	private List<DefVariable> variablesLocales;
	private List<Sentencia> cuerpo;

	public DefFuncion(int linea, int columna, String nombre, Tipo tipo,
			List<DefVariable> variablesLocales, List<Sentencia> cuerpo) {
		super(linea, columna, nombre, tipo);
		this.variablesLocales = variablesLocales;
		this.cuerpo = cuerpo;
	}

}
