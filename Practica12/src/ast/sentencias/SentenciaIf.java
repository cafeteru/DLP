package ast.sentencias;

import java.util.ArrayList;
import java.util.List;

import ast.expresiones.Expresion;
import ast.sentencias.util.SentenciaAbstracta;
import visitor.Visitor;

/**
 * Clase que simula una sentencia if.
 * 
 * @author Iván González Mahagamage
 */
public class SentenciaIf extends SentenciaAbstracta {
	private Expresion condicion;
	private List<Sentencia> cuerpoIf = new ArrayList<>();
	private List<Sentencia> cuerpoElse = new ArrayList<>();

	/**
	 * Constructor con parámetros.
	 * 
	 * @param linea
	 *            Linea en la que se encuentra la sentencía.
	 * @param columna
	 *            Columna en la que se encuentra la sentencía.
	 * @param condicion
	 *            Expresión a examinar.
	 * @param cuerpoIf
	 *            Lista de sentencias que se ejecutan si se cumple la función.
	 * @param cuerpoElse
	 *            Lista de sentecias que se ejecutan si no se cumple la función.
	 */
	public SentenciaIf(int linea, int columna, Expresion condicion,
			List<Sentencia> cuerpoIf, List<Sentencia> cuerpoElse) {
		super(linea, columna);
		this.condicion = condicion;
		this.cuerpoIf = cuerpoIf;
		this.cuerpoElse = cuerpoElse;
		setLinea(condicion.getLinea());
	}

	@Override
	public String toString() {
		return "SentenciaIf [condicion=" + condicion + ", cuerpoIf=" + cuerpoIf
				+ ", cuerpoElse=" + cuerpoElse + ", linea=" + linea
				+ ", columna=" + columna + "]";
	}

	/**
	 * Método que devuelve todas la condición.
	 * 
	 * @return Expresion.
	 */
	public Expresion getCondicion() {
		return condicion;
	}

	/**
	 * Método que devuelve la lista de sentencias que se ejecutan si se cumple
	 * la condición.
	 * 
	 * @return List<Sentencia>.
	 */
	public List<Sentencia> getCuerpoIf() {
		return cuerpoIf;
	}

	/**
	 * Método que devuelve la lista de sentencias que se ejecutan si no se
	 * cumple la condición.
	 * 
	 * @return List<Sentencia>.
	 */
	public List<Sentencia> getCuerpoElse() {
		return cuerpoElse;
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}
}
