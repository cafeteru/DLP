package ast.expresiones;

import ast.expresiones.util.OperacionBinaria;
import visitor.Visitor;

/**
 * Clase que representa una operación lógica.
 * 
 * @author Iván González Mahagamage
 */
public class Logica extends OperacionBinaria {

	/**
	 * Constructor con parámetros.
	 * 
	 * @param linea
	 *            Linea en la que se encuentra la expresión.
	 * @param columna
	 *            Columna en la que se encuentra la expresión.
	 * @param izq
	 *            Expresión izquierda.
	 * @param operador
	 *            Operador.
	 * @param der
	 *            Expresión derecha.
	 */
	public Logica(int linea, int columna, Expresion izq, String operador,
			Expresion der) {
		super(linea, columna, izq, operador, der);
	}

	@Override
	public String toString() {
		return "Logica [operador=" + operador + ", linea=" + linea
				+ ", columna=" + columna + ", getIzq()=" + getIzq()
				+ ", getDer()=" + getDer() + "]";
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}
}
