package ast.expresiones;

import ast.expresiones.util.ExpresionAbstracta;

public class AccesoArray extends ExpresionAbstracta {
	private Expresion izq;
	private Expresion der;

	public AccesoArray(int linea, int columna, Expresion izq, Expresion der) {
		super(linea, columna);
		this.izq = izq;
		this.der = der;
	}

	@Override
	public String toString() {
		return "AccesoArray [izq=" + izq + ", der=" + der + ", linea=" + linea
				+ ", columna=" + columna + "]";
	}

}
