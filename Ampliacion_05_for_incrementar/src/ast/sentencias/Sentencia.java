package ast.sentencias;

import ast.NodoAST;

/**
 * Interfaz para definir Sentencias.
 * 
 * @author Iván González Mahagamage
 *
 */
public interface Sentencia extends NodoAST {

	public boolean esAsignacion();
}
