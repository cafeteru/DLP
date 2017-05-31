package ast.sentencias;

import ast.NodoAST;

/**
 * Interfaz para definir el tipo "Sentencia" del an�lizador lexico.
 * 
 * @author Iv�n Gonz�lez Mahagamage
 *
 */
public interface Sentencia extends NodoAST {
	public int calcularLineaComienzo();
}
