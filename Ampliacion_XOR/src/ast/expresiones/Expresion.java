package ast.expresiones;

import ast.NodoAST;
import ast.tipos.Tipo;

/**
 * Interfaz para definir el tipo "Expresion" del análizador lexico.
 * 
 * @author Iván González Mahagamage
 *
 */
public interface Expresion extends NodoAST {
	public boolean getLValue();

	public void setLValue(boolean lValue);

	public Tipo getTipo();

	public void setTipo(Tipo tipo);
}
