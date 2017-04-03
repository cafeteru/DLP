package ast.tipos;

import java.util.List;

import ast.NodoAST;

public interface Tipo extends NodoAST {
	public boolean esLogico();

	public Tipo aritmetica(Tipo tipo);

	public Tipo comparacion(Tipo tipo);

	public Tipo logica(Tipo tipo);

	public Tipo aritmetica();

	public Tipo logica();

	public Tipo promocionaA(Tipo tipo);

	public Tipo parentesis(List<Tipo> tipos);

	public Tipo corchetes(Tipo tipo);

	public Tipo punto(String nombreCampo);

	public Tipo cast(Tipo tipo);
}
