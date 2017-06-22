package ast.expresiones;

import ast.NodoAST;
import ast.tipos.Tipo;

/**
 * Interfaz para las expresiones.
 * 
 * @author Iván González Mahagamage
 *
 */
public interface Expresion extends NodoAST {
	/**
	 * Método que devuelve el valor que guarda la expresión.
	 * 
	 * @return Valor guardado en la expresión.
	 */
	public boolean getLValue();

	/**
	 * Método que modifica el valor guardado dentro de la expresión.
	 * 
	 * @param lValue
	 *            Nuevo valor.
	 */
	public void setLValue(boolean lValue);

	/**
	 * Método que devuelve el tipo de la expresión.
	 * 
	 * @return Tipo de la expresión.
	 */
	public Tipo getTipo();

	/**
	 * Método que modifica el tipo de la expresión.
	 * 
	 * @param lValue
	 *            Nuevo Tipo.
	 */
	public void setTipo(Tipo tipo);

	public boolean getFor();

	public void setFor(boolean a);
}
