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
	 * Indica si se puede utilizar como boolean.
	 * 
	 * @return True, si se puede usar. En caso contrario, false.
	 */
	public boolean esLogico();

	/**
	 * Comprueba que el tipo le pasamos por parámetro se puede usar en una
	 * operación aritmétrica.
	 * 
	 * @param tipo
	 *            Tipo que queremos comprobar.
	 * @return True, si se puede usar. En caso contrario, false.
	 */
	public Tipo aritmetica(Tipo tipo);

	/**
	 * Comprueba que el tipo le pasamos por parámetro se puede usar en una
	 * comparación.
	 * 
	 * @param tipo
	 *            Tipo que queremos comprobar.
	 * @return True, si se puede usar. En caso contrario, false.
	 */
	public Tipo comparacion(Tipo tipo);

	/**
	 * Comprueba que el tipo le pasamos por parámetro se puede usar en una
	 * operación lógica.
	 * 
	 * @param tipo
	 *            Tipo que queremos comprobar.
	 * @return True, si se puede usar. En caso contrario, false.
	 */
	public Tipo logica(Tipo tipo);

	/**
	 * Indica si se puede utilizar en una operación aritmétrica.
	 * 
	 * @return True, si se puede usar. En caso contrario, false.
	 */
	public Tipo aritmetica();

	/**
	 * Indica si se puede utilizar en una operación logica.
	 * 
	 * @return True, si se puede usar. En caso contrario, false.
	 */
	public Tipo logica();

	/**
	 * Comprueba que el tipo le pasamos por parámetro se puede convertir a otro
	 * tipo.
	 * 
	 * @param tipo
	 *            Tipo que queremos comprobar.
	 * @return True, si se puede. En caso contrario, false.
	 */
	public Tipo promocionaA(Tipo tipo);

	/**
	 * Comprueba los tipos de los parámetros de una función.
	 * 
	 * @param tipos
	 *            Tipos de los parámetros.
	 * @return Si no fracasa devuelve el tipo de retorno de la función. En caso
	 *         contrario, null.
	 */
	public Tipo parentesis(List<Tipo> tipos);

	/**
	 * Comprueba que dentro de los corchetes de un array solo puede ir un número
	 * entero.
	 * 
	 * @param tipo
	 *            Tipo que queremos comprobar.
	 * @return True si es tipoEntero. En caso contrario, false.
	 */
	public Tipo corchetes(Tipo tipo);

	/**
	 * Comprueba que el campo existe dentro del struct.
	 * 
	 * @param nombreCampo
	 *            Nombre del campo que queremos comprobar.
	 * @return True, si esxiste. En caso contrario, false
	 */
	public Tipo punto(String nombreCampo);

	/**
	 * Comprueba que se pueda castear este tipo al tipo que le pasamos por
	 * parámetro.
	 * 
	 * @param tipo
	 *            Tipo al que queremos convertir este tipo.
	 * @return True, si es posible. En caso contrario, false.
	 */
	public Tipo cast(Tipo tipo);

	/**
	 * Devuelve el número de bits que ocupa en la pila.
	 * 
	 * @return Número de bits que ocupa en la pila.
	 */
	public int nBytes();

	/**
	 * Devuelve el sufijo de su tipo.
	 * 
	 * @return Sufijo del tipo.
	 */
	public String sufijo();

	/**
	 * Compara dos tipos y devuelve el de mayor tamaño.
	 * 
	 * @param tipo
	 *            Tipo con el que comparamos.
	 * @return El tipo de amyor tamaño.
	 */
	public Tipo Mayor(Tipo tipo);

	/**
	 * Devuelve el desplazamiento en memoria. (Si es local, global)
	 * 
	 * @param id
	 *            Identificador de la variable.
	 * @return Desplazamiento en memoria
	 */
	public int offset(String id);
}
