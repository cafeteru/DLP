package ast.tipos;

import java.util.ArrayList;
import java.util.List;

public class TipoRegistro implements Tipo {
	private List<CampoRegistro> campos = new ArrayList<>();

	public TipoRegistro(List<CampoRegistro> campos) {
		for (CampoRegistro campoRegistro : campos) {
			this.campos.add(campoRegistro);
		}
	}

	@Override
	public String toString() {
		return "TipoRegistro [campos=" + campos + "]";
	}

}
