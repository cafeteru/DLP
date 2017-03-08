package ast.definiciones.util;

import ast.definiciones.Definicion;
import ast.tipos.Tipo;
import ast.util.NodoPosicion;

public class DefinicionAbstracta extends NodoPosicion implements Definicion {
	private String nombre;
	private Tipo tipo;

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

}
