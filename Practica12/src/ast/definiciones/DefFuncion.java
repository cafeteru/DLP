package ast.definiciones;

import java.util.List;

import ast.definiciones.util.DefinicionAbstracta;
import ast.sentencias.Sentencia;
import ast.tipos.TipoFuncion;
import ast.tipos.TipoVoid;
import generacioncodigo.GeneradorCodigo;
import visitor.Visitor;

/**
 * Clase que simboliza la definición de una función.
 * 
 * @author Iván González Mahagamage
 */
public class DefFuncion extends DefinicionAbstracta {
	private List<DefVariable> variablesLocales;
	private List<Sentencia> cuerpo;
	private int numBytesLocal;

	/**
	 * Constructor con parámetros.
	 * 
	 * @param linea
	 *            Linea en la que se encuentra la definición.
	 * @param columna
	 *            Columna en la que se encuentra la definición.
	 * @param nombre
	 *            Nombre de la definición.
	 * @param tipo
	 *            Tipo de la definición.
	 * @param variablesLocales
	 *            Lista de variables locales.
	 * @param cuerpo
	 *            Lista de sentencias que forman el cuerpo.
	 */
	public DefFuncion(int linea, int columna, String nombre, TipoFuncion tipo,
			List<DefVariable> variablesLocales, List<Sentencia> cuerpo) {
		super(linea, columna, nombre, tipo);
		this.variablesLocales = variablesLocales;
		this.cuerpo = cuerpo;
		calcularLineaComienzo();
	}

	@Override
	public String toString() {
		return "DefFuncion [variablesLocales=" + variablesLocales + ", cuerpo="
				+ cuerpo + ", linea=" + linea + ", columna=" + columna
				+ ", getTipo()=" + getTipo() + ", getNombre()=" + getNombre()
				+ "]";
	}

	/**
	 * Método que devuelve la lista de variables locales.
	 * 
	 * @return Variables locales de la función.
	 */
	public List<DefVariable> getVariablesLocales() {
		return variablesLocales;
	}

	/**
	 * Método que devuelve la lista de sentencias que forman el cuerpo.
	 * 
	 * @return Cuerpo de la función.
	 */
	public List<Sentencia> getCuerpo() {
		return cuerpo;
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

	/**
	 * Método que devuelve el número de bytes de las variables locales.
	 * 
	 * @return Número de bytes de las variables locales.
	 */
	public int getNumBytesLocal() {
		return numBytesLocal;
	}

	/**
	 * Método que modifica el número de bytes de las variables locales.
	 * 
	 * @param numBytesLocal
	 *            Nuevo número de bytes.
	 */
	public void setNumBytesLocal(int numBytesLocal) {
		this.numBytesLocal = numBytesLocal;
	}

	/**
	 * Método que genera la sentencia ret del generador de código.
	 * 
	 * @param GC
	 *            Generador de código.
	 */
	public void crearRet(GeneradorCodigo GC) {
		TipoFuncion tp = (TipoFuncion) getTipo();
		int retorno;
		if (getTipo() instanceof TipoVoid) {
			retorno = 0;
		} else {
			retorno = tp.getRetorno().nBytes();
		}
		GC.ret(retorno, getNumBytesLocal(), tp.getNumBytesParam());
	}

	/**
	 * Método que truca el número de lineas de las funciones. No es perfecto.
	 */
	private void calcularLineaComienzo() {
		List<DefVariable> parametros = ((TipoFuncion) getTipo())
				.getParametros();
		if (!parametros.isEmpty())
			setLinea(parametros.get(0).getLinea());
		else if (!variablesLocales.isEmpty())
			setLinea(variablesLocales.get(0).getLinea() - 1);
		else if (!cuerpo.isEmpty())
			setLinea(cuerpo.get(0).getLinea() - 1);
	}

}
