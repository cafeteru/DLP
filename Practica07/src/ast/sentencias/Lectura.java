package ast.sentencias;

import java.util.List;

import ast.expresiones.Expresion;
import ast.sentencias.util.CompositeExpresiones;

/**
 * Clase que simula en el an�lizador l�xico un token que es una lectura.
 * 
 * @author Iv�n Gonz�lez Mahagamage
 *
 */
public class Lectura extends CompositeExpresiones {

	/**
	 * Constructor con par�metros.
	 * 
	 * @param linea
	 *            L�nea en la que se encuentra el lexema.
	 * @param columna
	 *            Columna en la que se encuentra el lexema.
	 * @param sentecia
	 *            Expresi�n que queremos leer.
	 */
	public Lectura(int linea, int columna, List<Expresion> expresiones) {
		super(linea, columna);
		this.expresiones = expresiones;
	}

	@Override
	public String toString() {
		return "Lectura [expresiones=" + expresiones + ", linea=" + linea
				+ ", columna=" + columna + "]";
	}
}
