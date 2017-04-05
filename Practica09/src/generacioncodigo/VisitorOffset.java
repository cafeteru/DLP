package generacioncodigo;

import ast.definiciones.DefFuncion;
import ast.definiciones.DefVariable;
import ast.tipos.CampoRegistro;
import ast.tipos.TipoFuncion;
import ast.tipos.TipoRegistro;
import visitor.util.VisitorAbstract;

/**
 * Calcula el desplazamiento de todas las variables
 * 
 * @author Iván González Mahagamage
 *
 */
public class VisitorOffset extends VisitorAbstract {
	private int desplazamientoGlobal = 0;
	private int desplazamientoLocal = 0;

	@Override
	public Object visit(DefFuncion defFuncion, Object o) {
		desplazamientoLocal = 0;
		super.visit(defFuncion, o);
		return null;
	}

	@Override
	public Object visit(DefVariable d, Object o) {
		super.visit(d, o);
		if (d.getAmbito() == 0) {
			d.setOffset(desplazamientoGlobal);
			desplazamientoGlobal += d.getTipo().nBytes();
		} else {
			desplazamientoLocal -= d.getTipo().nBytes();
			d.setOffset(desplazamientoLocal);
		}
		return null;
	}

	@Override
	public Object visit(TipoFuncion f, Object o) {
		int desplazamiento = 4;
		for (int i = f.getParametros().size() - 1; i >= 0; i--) {
			f.getParametros().get(i).setOffset(desplazamiento);
			desplazamiento += f.getParametros().get(i).getTipo().nBytes();
		}
		f.getRetorno().accept(this, o);
		return null;
	}

	@Override
	public Object visit(TipoRegistro r, Object o) {
		int desplazamiento = 0;
		for (CampoRegistro c : r.getCampos()) {
			c.setOffset(desplazamiento);
			desplazamiento += c.getTipo().nBytes();
		}
		return null;
	}
}