package generacioncodigo;

import ast.expresiones.*;
import ast.sentencias.Invocacion;
import ast.tipos.Tipo;
import ast.tipos.TipoEntero;
import ast.tipos.TipoFuncion;

/**
 * Expresiones
 * 
 * @author Iván González Mahagamage
 */
public class VisitorGCValor extends AbstractVisitorGC {
	private static VisitorGCValor valor;

	private GeneradorCodigo GC;
	private VisitorGCDireccion direccion;

	public VisitorGCValor(String entrada, String salida,
			VisitorGCDireccion direccion) {
		GC = GeneradorCodigo.getInstancia(entrada, salida);
		this.direccion = direccion;
	}

	@Override
	public Object visit(LiteralCaracter c, Object o) {
		GC.push(c.getValor());
		return null;
	}

	@Override
	public Object visit(LiteralEntero c, Object o) {
		GC.push(c.getValor());
		return null;
	}

	@Override
	public Object visit(LiteralReal c, Object o) {
		GC.push(c.getValor());
		return null;
	}

	@Override
	public Object visit(Cast c, Object o) {
		c.getExpresion().accept(this, o);
		GC.convertirA(c.getExpresion().getTipo(), c.getTipoCast());
		return null;
	}

	@Override
	public Object visit(Aritmetica a, Object o) {
		a.getIzq().accept(this, o);
		GC.convertirA(a.getIzq().getTipo(), a.getTipo());
		a.getDer().accept(this, o);
		GC.convertirA(a.getDer().getTipo(), a.getTipo());
		GC.aritmetrica(a.getTipo(), a.getOperador());
		return null;
	}

	@Override
	public Object visit(Comparacion a, Object o) {
		Tipo mayor = a.getIzq().getTipo().Mayor(a.getDer().getTipo());
		a.getIzq().accept(this, o);
		GC.convertirA(a.getIzq().getTipo(), mayor);
		a.getDer().accept(this, o);
		GC.convertirA(a.getDer().getTipo(), mayor);
		GC.comparacion(a.getTipo(), a.getOperador());
		return null;
	}

	@Override
	public Object visit(Logica a, Object o) {
		a.getIzq().accept(this, o);
		a.getDer().accept(this, o);
		GC.logica(a.getOperador());
		return null;
	}

	@Override
	public Object visit(Negacion a, Object o) {
		a.getExpresion().accept(this, o);
		GC.logica(a.getOperador());
		return null;
	}

	@Override
	public Object visit(MenosUnario a, Object o) {
		a.getExpresion().accept(this, o); // Valor en la pila
		GC.push(-1);
		GC.convertirA(TipoEntero.getInstancia(), a.getExpresion().getTipo());
		GC.mul(a.getExpresion().getTipo().sufijo());
		return null;
	}

	@Override
	public Object visit(Variable v, Object o) {
		v.accept(direccion, o);
		GC.load(v.getTipo().sufijo());
		return null;
	}

	@Override
	public Object visit(AccesoCampo a, Object o) {
		a.accept(direccion, o);
		GC.load(a.getTipo().sufijo());
		return null;
	}

	@Override
	public Object visit(AccesoArray a, Object o) {
		a.accept(direccion, o);
		GC.load(a.getTipo().sufijo());
		return null;
	}

	@Override
	public Object visit(Invocacion i, Object o) {
		int contador = 0;
		for (Expresion e : i.getExpresiones()) {
			e.accept(this, o);
			GC.convertirA(e.getTipo(), ((TipoFuncion) i.getVariable().getTipo())
					.getParametros().get(contador).getTipo());
			contador++;
		}
		GC.call(i.getVariable().getClave());
		return null;
	}

	public static VisitorGCValor getValor() {
		return valor;
	}

	public static void setValor(VisitorGCValor valor) {
		VisitorGCValor.valor = valor;
	}

}
