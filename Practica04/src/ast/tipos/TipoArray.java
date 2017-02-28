package ast.tipos;

import java.util.HashSet;
import java.util.Set;

public class TipoArray implements Tipo {
	private int tama�o;
	private Set<Tipo> elementos = new HashSet<>();

	public TipoArray(int tama�o, Tipo tipo) {
		this.tama�o = tama�o;
		elementos.add(tipo);
	}
}
