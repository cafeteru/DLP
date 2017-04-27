package ast.tipos;

import java.util.ArrayList;
import java.util.List;

import ast.tipos.util.TipoAbstracto;
import visitor.Visitor;

public class TipoRegistro extends TipoAbstracto implements Tipo {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((campos == null) ? 0 : campos.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoRegistro other = (TipoRegistro) obj;
		if (campos == null) {
			if (other.campos != null)
				return false;
		} else if (!campos.equals(other.campos))
			return false;
		return true;
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

	public List<CampoRegistro> getCampos() {
		return campos;
	}

	@Override
	public Tipo punto(String nombreCampo) {
		for (int i = 0; i < campos.size(); i++) {
			if (campos.get(i).getNombre().equals(nombreCampo)) {
				return campos.get(i).getTipo();
			}
		}
		return null;
	}

	@Override
	public int nBytes() {
		int suma = 0;
		for (CampoRegistro c : campos)
			suma += c.getTipo().nBytes();
		return suma;
	}

	@Override
	public int offset(String id) {
		for (CampoRegistro c : campos) {
			if (c.getNombre().equals(id)) {
				return c.getOffset();
			}
		}
		throw new IllegalStateException();
	}

}
