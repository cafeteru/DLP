package ast.definiciones.util;

import ast.definiciones.Definicion;
import ast.tipos.Tipo;
import ast.util.NodoPosicion;

/**
 * Implementación abstracta de la interfaz Definicion para implementar las
 * partes comunes de todas las clases que heredan de esta interfaz.
 * 
 * @author Iván González Mahagamage
 */
public abstract class DefinicionAbstracta extends NodoPosicion
		implements Definicion {
	private String nombre;
	private Tipo tipo;
	private int ambito;

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
	 */
	public DefinicionAbstracta(int linea, int columna, String nombre,
			Tipo tipo) {
		super(linea, columna);
		this.nombre = nombre;
		this.tipo = tipo;
	}

	@Override
	public Tipo getTipo() {
		return tipo;
	}

	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DefinicionAbstracta other = (DefinicionAbstracta) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

	@Override
	public int getAmbito() {
		return ambito;
	}

	@Override
	public void setAmbito(int ambito) {
		this.ambito = ambito;
	}

}
