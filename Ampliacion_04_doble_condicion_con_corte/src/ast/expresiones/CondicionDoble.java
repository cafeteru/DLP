package ast.expresiones;

import ast.expresiones.util.ExpresionAbstracta;
import visitor.Visitor;

public class CondicionDoble extends ExpresionAbstracta {
	private Expresion izq, cen, der;
	private String operador;

	public CondicionDoble(int linea, int columna) {
		super(linea, columna);
	}

	public CondicionDoble(int linea, int columna, Expresion izq, Expresion cen,
			Expresion der, String operador) {
		super(linea, columna);
		this.izq = izq;
		this.cen = cen;
		this.der = der;
		this.operador = operador;
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

	public Expresion getIzq() {
		return izq;
	}

	public void setIzq(Expresion izq) {
		this.izq = izq;
	}

	public Expresion getCen() {
		return cen;
	}

	public void setCen(Expresion cen) {
		this.cen = cen;
	}

	public Expresion getDer() {
		return der;
	}

	public void setDer(Expresion der) {
		this.der = der;
	}

	public String getOperador() {
		return operador;
	}

	public void setOperador(String operador) {
		this.operador = operador;
	}

	@Override
	public String toString() {
		return "CondicionDoble [izq=" + izq + ", cen=" + cen + ", der=" + der
				+ ", linea=" + linea + ", columna=" + columna + "]";
	}

}
