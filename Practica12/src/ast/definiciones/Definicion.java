package ast.definiciones;

import ast.NodoAST;
import ast.tipos.Tipo;

/**
 * Interfaz de las definiciones.
 * 
 * @author Iván González Mahagamage
 */
public interface Definicion extends NodoAST {

	/**
	 * Devuelve el tipo de la definición.
	 * 
	 * @return Tipo de la definición.
	 */
	public Tipo getTipo();

	/**
	 * Devuelve el nombre (identificador) de la definición.
	 * 
	 * @return Nombre de la defición.
	 */
	public String getNombre();

	/**
	 * Devuelve el ámbito de la definición.
	 * 
	 * @return Ámbito de la definición
	 */
	public int getAmbito();

	/**
	 * Modifica el ámbito de la definición.
	 * 
	 * @param ambito
	 *            Nuevo ámbito de la definición.
	 */
	public void setAmbito(int ambito);
}
