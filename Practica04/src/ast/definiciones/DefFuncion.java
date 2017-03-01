package ast.definiciones;

import java.util.List;

import ast.sentencias.Sentencia;
import ast.tipos.TipoFuncion;

public class DefFuncion extends DefinicionAbstracta {
	private List<DefVariable> variablesLocales;
	private List<Sentencia> cuerpo;

	public DefFuncion(int linea, int columna, String nombre, TipoFuncion tipo, List<DefVariable> variablesLocales,
			List<Sentencia> cuerpo) {
		super(linea, columna, nombre, tipo);
		this.variablesLocales = variablesLocales;
		this.cuerpo = cuerpo;
	}

	@Override
	public String toString() {
		return "DefFuncion [variablesLocales=" + variablesLocales + ", cuerpo=" + cuerpo + ", linea=" + linea
				+ ", columna=" + columna + ", getTipo()=" + getTipo() + ", getNombre()=" + getNombre() + ", getLinea()="
				+ getLinea() + ", getColumna()=" + getColumna() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

}
