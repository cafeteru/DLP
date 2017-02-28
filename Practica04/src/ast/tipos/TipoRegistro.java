package ast.tipos;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TipoRegistro implements Tipo {
	private Set<CampoRegistro> campos = new HashSet<>();

	public TipoRegistro(List<CampoRegistro> campos) {
		for (CampoRegistro campoRegistro : campos) {
			this.campos.add(campoRegistro);
		}
	}

}
