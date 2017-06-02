package ast.tipos;

import java.util.List;

import ast.NodoAST;

/**
 * Interfaz para los tipos.
 * 
 * @author Iván González Mahagamage
 */
public interface Tipo extends NodoAST {
	/**
	 * Indica si se puede utilizar en un operación logica.
	 * 
	 * @return
	 */
	public boolean esLogico();

	/**
	 * 
	 * @param tipo
	 * @return
	 */
	public Tipo aritmetica(Tipo tipo);

	/**
	 * 
	 * @param tipo
	 * @return
	 */
	public Tipo comparacion(Tipo tipo);

	/**
	 * 
	 * @param tipo
	 * @return
	 */
	public Tipo logica(Tipo tipo);

	/**
	 * 
	 * @return
	 */
	public Tipo aritmetica();

	/**
	 * 
	 * @return
	 */
	public Tipo logica();

	/**
	 * 
	 * @param tipo
	 * @return
	 */
	public Tipo promocionaA(Tipo tipo);

	/**
	 * 
	 * @param tipos
	 * @return
	 */
	public Tipo parentesis(List<Tipo> tipos);

	/**
	 * 
	 * @param tipo
	 * @return
	 */
	public Tipo corchetes(Tipo tipo);

	/**
	 * 
	 * @param nombreCampo
	 * @return
	 */
	public Tipo punto(String nombreCampo);

	/**
	 * 
	 * @param tipo
	 * @return
	 */
	public Tipo cast(Tipo tipo);

	/**
	 * 
	 * @return
	 */
	public int nBytes();

	/**
	 * 
	 * @return
	 */
	public String sufijo();

	/**
	 * 
	 * @param tipo
	 * @return
	 */
	public Tipo Mayor(Tipo tipo);

	/**
	 * 
	 * @param id
	 * @return
	 */
	public int offset(String id);
}
