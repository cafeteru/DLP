package ast.sentencias;

import java.util.List;

import ast.expresiones.Expresion;
import ast.sentencias.util.CompositeExpresiones;

/**
 * Clase que simula en el análizador léxico un token que es una escritura.
 * 
 * @author Iván González Mahagamage
 *
 */
public class Escritura extends CompositeExpresiones {

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
