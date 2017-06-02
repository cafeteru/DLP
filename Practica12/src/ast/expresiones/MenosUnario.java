package ast.expresiones;

import ast.expresiones.util.OperacionUnaria;
import visitor.Visitor;

/**
 * Clase que simula un menos unario. (Vuelve a negativo el valor de la expresión
 * a su derecha.)
 * 
 * @author Iván González Mahagamage
 *
 */
public class MenosUnario extends OperacionUnaria {

	/**
	 * Constructor con parámetro.
	 * 
	 * @param linea
	 *            Linea en la que se encuentra la expresión.
	 * @param columna
	 *            Columna en la que se encuentra la expresión.
	 * @param operador
	 *            Operador de la expresión
	 * @param expresion
	 *            Expresión afectada por el operador
	 */
	public MenosUnario(int linea, int columna, String operador,
			Expresion expresion) {
		super(linea, columna, operador, expresion);
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}
}
