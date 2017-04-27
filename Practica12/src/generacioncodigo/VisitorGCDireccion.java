package generacioncodigo;

import ast.definiciones.DefVariable;
import ast.expresiones.AccesoArray;
import ast.expresiones.AccesoCampo;
import ast.expresiones.Variable;
import ast.tipos.TipoEntero;

public class VisitorGCDireccion extends AbstractVisitorGC {

	private GeneradorCodigo GC;
	private VisitorGCValor valor;

	public VisitorGCDireccion(String entrada, String salida) {
		GC = GeneradorCodigo.getInstancia(entrada, salida);
	}

	public VisitorGCValor getValor() {
		return valor;
	}

	public void setValor(VisitorGCValor valor) {
		this.valor = valor;
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

	@Override
	public Object visit(AccesoCampo a, Object o) {
		a.getExpresion().accept(this, o);
		GC.push(a.getExpresion().getTipo().offset(a.getNombreCampo()));
		GC.add();
		return null;
	}

	@Override
	public Object visit(AccesoArray a, Object o) {
		a.getIzq().accept(this, o);
		a.getDer().accept(valor, o);
		GC.convertirA(a.getDer().getTipo(), TipoEntero.getInstancia());
		GC.push(a.getTipo().nBytes());
		GC.mul("i");
		GC.add();
		return null;
	}
}
