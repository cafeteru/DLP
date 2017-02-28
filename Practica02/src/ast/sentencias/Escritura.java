package ast.sentencias;

import ast.expresiones.Expresion;
import ast.util.CompositeExpresion;

/**
 * Clase que simula en el análizador léxico un token que es una escritura.
 * 
 * @author Iván González Mahagamage
 *
 */
public class Escritura extends CompositeExpresion implements Sentencia {

	/**
	 * Constructor con parámetros.
	 * 
	 * @param linea
	 *            Línea en la que se encuentra el lexema.
	 * @param columna
	 *            Columna en la que se encuentra el lexema.
	 * @param expresion
	 *            Expresión que queremos escribir.
	 */
	public Escritura(int linea, int columna, Expresion expresion) {
		super(linea, columna);
		getExpresiones().add(expresion);
	}

	/**
	 * Redefinición del método toString().
	 */
	@Override
	public String toString() {
		return "write";
	}
}
