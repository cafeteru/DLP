package ast.sentencias;

import ast.expresiones.Expresion;
import ast.util.CompositeExpresion;

/**
 * Clase que simula en el an�lizador l�xico un token que es una escritura.
 * 
 * @author Iv�n Gonz�lez Mahagamage
 *
 */
public class Escritura extends CompositeExpresion implements Sentencia {

	/**
	 * Constructor con par�metros.
	 * 
	 * @param linea
	 *            L�nea en la que se encuentra el lexema.
	 * @param columna
	 *            Columna en la que se encuentra el lexema.
	 * @param expresion
	 *            Expresi�n que queremos escribir.
	 */
	public Escritura(int linea, int columna, Expresion expresion) {
		super(linea, columna);
		getExpresiones().add(expresion);
	}

	/**
	 * Redefinici�n del m�todo toString().
	 */
	@Override
	public String toString() {
		return "write";
	}
}
