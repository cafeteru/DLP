package ast.tipos;

import java.util.ArrayList;
import java.util.List;

import ast.definiciones.DefVariable;

public class TipoFuncion implements Tipo {
	private List<DefVariable> parametros = new ArrayList<>();
	private Tipo retorno;

	public TipoFuncion(List<DefVariable> parametros, Tipo retorno) {
		for (DefVariable defVariable : parametros) {
			this.parametros.add(defVariable);
		}
		this.retorno = retorno;
	}

	@Override
	public String toString() {
		return "TipoFuncion [parametros=" + parametros + ", retorno=" + retorno
				+ "]";
	}

	@Override
	public int getLinea() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getColumna() {
		// TODO Auto-generated method stub
		return 0;
	}
}
