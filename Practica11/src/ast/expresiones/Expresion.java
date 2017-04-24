package ast.expresiones;

import ast.NodoAST;
import ast.tipos.Tipo;

/**
 * Interfaz para definir el tipo "Expresion" del an�lizador lexico.
 * 
 * @author Iv�n Gonz�lez Mahagamage
 *
 */
public interface Expresion extends NodoAST {
	public boolean getLValue();

	public void setLValue(boolean lValue);

	public Tipo getTipo();

	public void setTipo(Tipo tipo);
}
