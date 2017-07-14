package ast.expresiones;

import visitor.Visitor;
import ast.expresiones.util.ExpresionAbstracta;

public class ListaExamen extends ExpresionAbstracta {
	private String nombre;
	private Expresion expresion;

	public ListaExamen(int linea, int columna, String nombres,
			Expresion expresiones) {
		super(linea, columna);
		this.nombre = nombres;
		this.expresion = expresiones;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombres(String nombres) {
		this.nombre = nombres;
	}

	public Expresion getExpresion() {
		return expresion;
	}

	public void setExpresiones(Expresion expresiones) {
		this.expresion = expresiones;
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}
}
