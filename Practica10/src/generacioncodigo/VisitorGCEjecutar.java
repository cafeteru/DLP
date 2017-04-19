package generacioncodigo;

import ast.Programa;
import ast.definiciones.DefFuncion;
import ast.definiciones.DefVariable;
import ast.definiciones.Definicion;
import ast.expresiones.Expresion;
import ast.sentencias.Asignacion;
import ast.sentencias.Escritura;
import ast.sentencias.Lectura;
import ast.sentencias.Sentencia;
import ast.tipos.TipoFuncion;

public class VisitorGCEjecutar extends AbstractVisitorGC {

	private GeneradorCodigo GC;
	private VisitorGCDireccion direccion;
	private VisitorGCValor valor;

	public VisitorGCEjecutar(String entrada, String salida) {
		GC = GeneradorCodigo.getInstancia(entrada, salida);
		direccion = VisitorGCDireccion.getInstance(entrada, salida);
		valor = VisitorGCValor.getInstance(entrada, salida);
	}

	@Override
	public Object visit(Programa p, Object o) {
		for (Definicion d : p.getDefiniciones()) {
			if (d instanceof DefVariable) {
				d.accept(this, o);
			}
		}
		GC.llamadaMain();
		for (Definicion d : p.getDefiniciones()) {
			if (d instanceof DefFuncion) {
				d.accept(this, o);
			}
		}
		return null;
	}

	@Override
	public Object visit(DefVariable d, Object o) {
		GC.comentarioSentencia("* " + d.getTipo() + " " + d.getNombre()
				+ " (offset " + d.getOffset() + ")");
		return null;
	}

	@Override
	public Object visit(DefFuncion d, Object o) {
		GC.line(d.getLinea());
		GC.id(d.getNombre());
		GC.comentarioSentencia("* Parameters");
		for (Definicion d2 : ((TipoFuncion) d.getTipo()).getParametros()) {
			d2.accept(this, o);
		}
		GC.comentarioSentencia("* Local variables");
		for (Definicion d2 : d.getVariablesLocales()) {
			d2.accept(this, o);
		}
		GC.enter(d.getNumBytesLocal());
		for (Sentencia s : d.getCuerpo()) {
			s.accept(this, o);
		}
		GC.ret(((TipoFuncion) d.getTipo()).getRetorno().nBytes(),
				d.getNumBytesLocal(), 0);
		return null;
	}

	@Override
	public Object visit(Asignacion a, Object o) {
		GC.line(a.getLinea());
		a.getVariable().accept(direccion, o);
		a.getValor().accept(valor, o);
		GC.convertirA(a.getValor().getTipo(), a.getVariable().getTipo());
		GC.store(a.getVariable().getTipo().sufijo());
		return null;
	}

	@Override
	public Object visit(Escritura e, Object o) {
		GC.line(e.getLinea());
		for (Expresion e1 : e.getExpresiones()) {
			GC.comentarioSentencia("* Write");
			e1.accept(valor, o);
			GC.out(e1.getTipo().sufijo());
		}
		return null;
	}

	@Override
	public Object visit(Lectura l, Object o) {
		GC.line(l.getLinea());
		for (Expresion e1 : l.getExpresiones()) {
			GC.comentarioSentencia("* Read");
			e1.accept(direccion, o);
			GC.in(e1.getTipo().sufijo());
			GC.store(e1.getTipo().sufijo());
		}
		return null;
	}

}
