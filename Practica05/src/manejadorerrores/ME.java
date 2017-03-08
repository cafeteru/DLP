package manejadorerrores;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import ast.tipos.TipoError;

public class ME {
	private static ME instancia;
	private List<TipoError> errores = new ArrayList<>();

	public boolean huboErrores() {
		if (errores.isEmpty())
			return false;
		return true;
	}

	public void addError(TipoError error) {
		errores.add(error);
	}

	public void mostrarErrores(PrintStream stream) {
		for (TipoError error : errores)
			stream.println(error.getMensaje());
	}

	public static ME getME() {
		if (instancia == null)
			instancia = new ME();
		return instancia;
	}
}
