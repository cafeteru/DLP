package ast.expresiones;

import ast.expresiones.util.OperacionUnaria;
import visitor.Visitor;

/**
 * Clase que representa una negacioón lógica.
 * 
 * @author Iván González Mahagamage
 */
public class Negacion extends OperacionUnaria {

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
	public Negacion(int linea, int columna, String operador,
			Expresion expresion) {
		super(linea, columna, operador, expresion);
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}
}
