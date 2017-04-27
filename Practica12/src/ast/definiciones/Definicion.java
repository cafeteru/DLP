package ast.definiciones;

import ast.NodoAST;
import ast.tipos.Tipo;

public interface Definicion extends NodoAST {

	public Tipo getTipo();

	public String getNombre();

	public int getAmbito();

	public void setAmbito(int ambito);

}
