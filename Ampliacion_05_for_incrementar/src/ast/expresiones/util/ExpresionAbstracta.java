package ast.expresiones.util;

import ast.expresiones.Expresion;
import ast.tipos.Tipo;
import ast.util.NodoPosicion;

/**
 * Implementación abstracta de la interfaz Expresion para implementar las partes
 * comunes de todas las clases que heredan de esta interfaz.
 * 
 * @author Iván González Mahagamage
 */
public abstract class ExpresionAbstracta extends NodoPosicion
		implements Expresion {
	protected boolean lValue, fora;
	protected Tipo tipo;

	/**
	 * Constructor con parámetros.
	 * 
	 * @param linea
	 *            Linea en la que se encuentra la expresión.
	 * @param columna
	 *            Columna en la que se encuentra la expresión.
	 */
	public ExpresionAbstracta(int linea, int columna) {
		super(linea, columna);
		setFor(false);
	}

	@Override
	public boolean getLValue() {
		return lValue;
	}

	@Override
	public void setLValue(boolean lValue) {
		this.lValue = lValue;
	}

	@Override
	public Tipo getTipo() {
		return tipo;
	}

	@Override
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	@Override
	public boolean getFor() {
		return fora;
	}

	@Override
	public void setFor(boolean a) {
		this.fora = a;
	}

}
