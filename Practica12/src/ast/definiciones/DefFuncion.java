package ast.definiciones;

import java.util.List;

import ast.definiciones.util.DefinicionAbstracta;
import ast.sentencias.Sentencia;
import ast.tipos.TipoFuncion;
import ast.tipos.TipoVoid;
import generacioncodigo.GeneradorCodigo;
import visitor.Visitor;

public class DefFuncion extends DefinicionAbstracta {
	private List<DefVariable> variablesLocales;
	private List<Sentencia> cuerpo;
	private int numBytesLocal;

	public DefFuncion(int linea, int columna, String nombre, TipoFuncion tipo,
			List<DefVariable> variablesLocales, List<Sentencia> cuerpo) {
		super(linea, columna, nombre, tipo);
		this.variablesLocales = variablesLocales;
		this.cuerpo = cuerpo;
		calcularLineaComienzo();
	}

	@Override
	public String toString() {
		return "DefFuncion [variablesLocales=" + variablesLocales + ", cuerpo="
				+ cuerpo + ", linea=" + linea + ", columna=" + columna
				+ ", getTipo()=" + getTipo() + ", getNombre()=" + getNombre()
				+ "]";
	}

	public List<DefVariable> getVariablesLocales() {
		return variablesLocales;
	}

	public List<Sentencia> getCuerpo() {
		return cuerpo;
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

	public int getNumBytesLocal() {
		return numBytesLocal;
	}

	public void setNumBytesLocal(int numBytesLocal) {
		this.numBytesLocal = numBytesLocal;
	}

	public void crearRet(GeneradorCodigo GC) {
		TipoFuncion tp = (TipoFuncion) getTipo();
		int retorno;
		if (getTipo() instanceof TipoVoid) {
			retorno = 0;
		} else {
			retorno = tp.getRetorno().nBytes();
		}
		GC.ret(retorno, getNumBytesLocal(), tp.getNumBytesParam());
	}

	private void calcularLineaComienzo() {
		if (cuerpo.size() > 0) {
			int aux = 0;
			for (Sentencia s : cuerpo)
				aux += s.calcularLineaComienzo();
			int a = getLinea() - variablesLocales.size() - aux - 1;
			setLinea(a);
		}
	}

}
