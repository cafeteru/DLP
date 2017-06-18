package ast;

import visitor.Visitor;

/**
 * Interfaz para definir el tipo "NodoAST" del análizador lexico.
 */
public interface NodoAST {

	/**
	 * Método que devuelve el parámetro linea.
	 * 
	 * @return Línea en la que se encuentra el token.
	 */
	public int getLinea();

	/**
	 * Método que devuelve el parámetro columna
	 * 
	 * @return Columna en la que se encuentra el token.
	 */
	public int getColumna();

	/**
	 * Método que usan los visitor para recoger los nodos del árbol.
	 * 
	 * @param v
	 *            Visitor que recorre el nodo.
	 * @param o
	 *            Parámetro que trae consigo el visitor
	 * @return Un objeto dependiendo del visitor.
	 */
	public Object accept(Visitor v, Object o);
}
