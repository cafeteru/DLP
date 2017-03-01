package ast.sentencias;

import java.util.List;

import ast.expresiones.Expresion;
import ast.sentencias.util.CompositeExpresiones;

/**
 * Clase que simula en el an�lizador l�xico un token que es una escritura.
 * 
 * @author Iv�n Gonz�lez Mahagamage
 *
 */
public class Escritura extends CompositeExpresiones {

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
	public Escritura(int linea, int columna, List<Expresion> expresiones) {
		super(linea, columna);
		this.expresiones = expresiones;
	}

	@Override
	public String toString() {
		return "Escritura [expresiones=" + expresiones + ", linea=" + linea
				+ ", columna=" + columna + "]";
	}

}
