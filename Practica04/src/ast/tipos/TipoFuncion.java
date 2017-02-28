package ast.tipos;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TipoFuncion implements Tipo {
	private Set<DefVariable> parametros = new HashSet<>();
	private Set<Tipo> retorno = new HashSet<>();

	public TipoFuncion(List<DefVariable> parametros, Tipo retorno) {
		for (DefVariable defVariable : parametros) {
			this.parametros.add(defVariable);
		}
		this.retorno.add(retorno);
	}
}
