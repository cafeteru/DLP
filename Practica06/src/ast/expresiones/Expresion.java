package ast.expresiones;

import ast.NodoAST;

/**
 * Interfaz para definir el tipo "Expresion" del an�lizador lexico.
 * 
 * @author Iv�n Gonz�lez Mahagamage
 *
 */
public interface Expresion extends NodoAST {
	public boolean getLValue();

	public void setLValue(boolean lValue);
}
