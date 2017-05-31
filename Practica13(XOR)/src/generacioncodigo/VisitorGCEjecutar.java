package generacioncodigo;

import ast.Programa;
import ast.definiciones.DefFuncion;
import ast.definiciones.DefVariable;
import ast.definiciones.Definicion;
import ast.expresiones.Expresion;
import ast.sentencias.Asignacion;
import ast.sentencias.Escritura;
import ast.sentencias.Invocacion;
import ast.sentencias.Lectura;
import ast.sentencias.Return;
import ast.sentencias.Sentencia;
import ast.sentencias.SentenciaIf;
import ast.sentencias.SentenciaWhile;
import ast.tipos.TipoEntero;
import ast.tipos.TipoFuncion;
import ast.tipos.TipoVoid;

public class VisitorGCEjecutar extends AbstractVisitorGC {

	private GeneradorCodigo GC;
	private VisitorGCDireccion direccion;
	private VisitorGCValor valor;

	public VisitorGCEjecutar(String entrada, String salida,
			VisitorGCDireccion direccion, VisitorGCValor valor) {
		GC = GeneradorCodigo.getInstancia(entrada, salida);
		this.direccion = direccion;
		this.valor = valor;
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
		GC.lineFuncion(d.getLinea());
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
			s.accept(this, d);
		}
		d.crearRet(GC);
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

	@Override
	public Object visit(SentenciaWhile w, Object o) {
		GC.comentarioSentencia("* While");
		GC.line(w.getLinea());
		int count = GC.getLabels(2);
		GC.etiqueta(count);
		w.getCondicion().accept(valor, o);
		GC.convertirA(w.getCondicion().getTipo(), TipoEntero.getInstancia());
		GC.jz(count + 1);
		GC.comentarioSentencia("* Body of the while statement");
		for (Sentencia s : w.getSentencias()) {
			s.accept(this, o);
		}
		GC.jmp(count);
		GC.etiqueta(count + 1);
		return null;
	}

	@Override
	public Object visit(SentenciaIf i, Object o) {
		GC.comentarioSentencia("* If statement");
		GC.line(i.getLinea());
		int count = GC.getLabels(2);
		i.getCondicion().accept(valor, o);
		GC.convertirA(i.getCondicion().getTipo(), TipoEntero.getInstancia());
		GC.jz(count);
		GC.comentarioSentencia("* Body of the if branch");
		for (Sentencia s : i.getCuerpoIf()) {
			s.accept(this, o);
		}
		GC.jmp(count + 1);
		GC.etiqueta(count);
		GC.comentarioSentencia("* Body of the else branch");
		for (Sentencia s : i.getCuerpoElse()) {
			s.accept(this, o);
		}
		GC.etiqueta(count + 1);
		return null;
	}

	@Override
	public Object visit(Invocacion i, Object o) {
		GC.line(i.getLinea());
		valor.visit(i, o);
		if (!(i.getTipo() instanceof TipoVoid))
			GC.pop(i.getTipo().sufijo());
		return null;
	}

	@Override
	public Object visit(Return r, Object o) {
		DefFuncion df = (DefFuncion) o;
		TipoFuncion tp = (TipoFuncion) df.getTipo();
		GC.line(r.getLinea());
		GC.comentarioSentencia("* Return");
		r.getExpresion().accept(valor, o);
		GC.convertirA(r.getExpresion().getTipo(), tp);
		return null;
	}

}
