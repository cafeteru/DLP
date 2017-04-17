package generacioncodigo;

import ast.definiciones.DefVariable;
import ast.expresiones.Variable;

public class VisitorGCDireccion extends AbstractVisitorGC {

	private GeneradorCodigo GC;

	public VisitorGCDireccion(String entrada, String salida) {
		GC = GeneradorCodigo.getInstancia(entrada, salida);
	}

	@Override
	public Object visit(Variable v, Object o) {
		if (v.getDefinicion().getAmbito() == 0) {
			GC.pusha(((DefVariable) v.getDefinicion()).getOffset());
		} else {
			GC.pushBP();
			GC.push(((DefVariable) v.getDefinicion()).getOffset());
			GC.add();
		}
		return null;
	}

}
