package ast.expresiones;

import java.util.List;

public class OperacionBinaria {
	private List<Expresion> expresion;

	public OperacionBinaria(Expresion expresion1, Expresion expresion2) {
		this.expresion.add(expresion1);
		this.expresion.add(expresion2);
	}

}
