package ast.expresiones;

import java.util.List;

public class OperacionUnaria {
	private List<Expresion> expresion;

	public OperacionUnaria(Expresion expresion) {
		this.expresion.add(expresion);
	}
}
