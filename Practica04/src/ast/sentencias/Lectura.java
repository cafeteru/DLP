package ast.sentencias;

import java.util.List;

import ast.expresiones.Expresion;
import ast.util.CompositeExpresiones;

/**
 * Clase que simula en el análizador léxico un token que es una lectura.
 * 
 * @author Iván González Mahagamage
 *
 */
public class Lectura extends CompositeExpresiones implements Sentencia {

	/**
	 * Constructor con parámetros.
	 * 
	 * @param linea
	 *            Línea en la que se encuentra el lexema.
	 * @param columna
	 *            Columna en la que se encuentra el lexema.
	 * @param sentecia
	 *            Expresión que queremos leer.
	 */
	public Lectura(int linea, int columna, List<Expresion> expresiones) {
		super(linea, columna);
		this.expresiones = expresiones;
	}

	/**
	 * Redefinición del método toString().
	 */
	@Override
	public String toString() {
		return "read";
	}
}
