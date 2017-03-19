package ast.expresiones;

import ast.NodoAST;

/**
 * Interfaz para definir el tipo "Expresion" del análizador lexico.
 * 
 * @author Iván González Mahagamage
 *
 */
public interface Expresion extends NodoAST {
	public boolean getLValue();

	public void setLValue(boolean lValue);
}
