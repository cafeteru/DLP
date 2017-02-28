package ast.expresiones;

public class Negacion extends OperacionUnaria {

	public Negacion(int linea, int columna, String operador,
			Expresion expresion) {
		super(linea, columna, operador, expresion);
	}

}
