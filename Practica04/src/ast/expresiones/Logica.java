package ast.expresiones;

public class Logica extends OperacionBinaria {

	public Logica(int linea, int columna, Expresion izq, String operador,
			Expresion der) {
		super(linea, columna, izq, operador, der);
	}

}
