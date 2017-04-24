package ast.expresiones;

import ast.expresiones.util.OperacionUnaria;
import visitor.Visitor;

/**
 * Clase que simula en el an�lizador l�xico un token que es un menos unario.
 * (Vuelve a negativo el valor de la expresi�n a su derecha.)
 * 
 * @author Iv�n Gonz�lez Mahagamage
 *
 */
public class MenosUnario extends OperacionUnaria {

	public MenosUnario(int linea, int columna, String operador,
			Expresion expresion) {
		super(linea, columna, operador, expresion);
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}
}
