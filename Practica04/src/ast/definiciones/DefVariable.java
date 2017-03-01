package ast.definiciones;

import ast.tipos.Tipo;

public class DefVariable extends DefinicionAbstracta {
	private int ambito;
	private int OFFSET;

	public DefVariable(int linea, int columna, String nombre, Tipo tipo) {
		super(linea, columna, nombre, tipo); 
	}

}
