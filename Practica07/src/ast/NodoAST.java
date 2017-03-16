package ast;

/**
 * Interfaz para definir el tipo "NodoAST" del análizador lexico.
 */
public interface NodoAST {

	/**
	 * Método que devuelve el parámetro linea.
	 * 
	 * @return Línea en la que se encuentra el token.
	 */
	public int getLinea();

	/**
	 * Método que devuelve el parámetro columna
	 * 
	 * @return Columna en la que se encuentra el token.
	 */
	public int getColumna();
}
