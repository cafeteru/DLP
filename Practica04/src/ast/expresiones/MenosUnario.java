package ast.expresiones;

/**
 * Clase que simula en el análizador léxico un token que es un menos unario.
 * (Vuelve a negativo el valor de la expresión a su derecha.)
 * 
 * @author Iván González Mahagamage
 *
 */
public class MenosUnario extends OperacionUnaria {

	public MenosUnario(int linea, int columna, String operador,
			Expresion expresion) {
		super(linea, columna, operador, expresion);
	}

}
