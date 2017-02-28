package ast.tipos;

import java.util.HashSet;
import java.util.Set;

public class TipoArray implements Tipo {
	private int tamaño;
	private Set<Tipo> elementos = new HashSet<>();

	public TipoArray(int tamaño, Tipo tipo) {
		this.tamaño = tamaño;
		elementos.add(tipo);
	}
}
