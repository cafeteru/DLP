package ast.sentencias;

import ast.expresiones.Expresion;
import ast.util.NodoPosicion;

/**
 * Clase que simula en el an�lizador l�xico un token que es una asignaci�n.
 * 
 * @author Iv�n Gonz�lez Mahagamage
 *
 */
public class Asignacion extends NodoPosicion implements Sentencia {
	private Expresion variable;
	private Expresion valor;

	/**
	 * Constructor con par�metros.
	 * 
	 * @param linea
	 *            L�nea en la que se encuentra el lexema.
	 * @param columna
	 *            Columna en la que se encuentra el lexema.
	 * @param variable
	 *            Variable a la que se le asigna un valor.
	 * @param valor
	 *            Valor a asignar a la variable.
	 */
	public Asignacion(int linea, int columna, Expresion variable,
			Expresion valor) {
		super(linea, columna);
		this.variable = variable;
		this.valor = valor;
	}

	/**
	 * M�todo que devuelve el par�metro variable.
	 * 
	 * @return Variable a la que se le asigna un valor.
	 */
	public Expresion getVariable() {
		return variable;
	}

	/**
	 * M�todo que devuelve el par�metro valor.
	 * 
	 * @return Valor a asignar a la variable.
	 */
	public Expresion getValor() {
		return valor;
	}

	/**
	 * Redefinici�n del m�todo toString().
	 */
	@Override
	public String toString() {
		return "=";
	}
}
