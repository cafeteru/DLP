import java.io.FileReader;
import java.io.IOException;

import ast.NodoAST;
import ast.Programa;
import generacioncodigo.VisitorGCEjecutar;
import generacioncodigo.VisitorOffset;
import introspector.model.IntrospectorModel;
import introspector.view.IntrospectorTree;
import lexico.Lexico;
import sintactico.Parser;
import visitor.Visitor;
import visitor.VisitorComprobacionTipos;
import visitor.VisitorIdentificacion;
import manejadorerrores.ME;

public class Main {

	public static void main(String args[]) throws IOException {
		if (args.length < 1) {
			System.err.println("Necesito el archivo de entrada.");
			return;
		} else if (args.length < 2) {
			System.err.println("Necesito el archivo de salida.");
			return;
		}

		FileReader fr = null;
		try {
			fr = new FileReader(args[0]);
		} catch (IOException io) {
			System.err.println(
					"El archivo " + args[0] + " no se ha podido abrir.");
			return;
		}

		// * Creamos léxico y sintáctico
		Lexico lexico = new Lexico(fr);
		Parser parser = new Parser(lexico);
		// * "Parseamos"
		parser.run();

		NodoAST root = parser.getAST();
		Visitor vIdent = new VisitorIdentificacion();
		vIdent.visit((Programa) root, null);

		Visitor vTipos = new VisitorComprobacionTipos();
		vTipos.visit((Programa) root, null);

		// * Comprobamos si hubo errores
		if (ME.getME().huboErrores()) {
			// * Mostramos los errores
			ME.getME().mostrarErrores(System.err);
		} else {
			// * Mostramos el AST
			Visitor vOffset = new VisitorOffset();
			vOffset.visit((Programa) root, null);
			VisitorGCEjecutar ejecutar = new VisitorGCEjecutar(args[0],
					args[1]);
			ejecutar.visit((Programa) root, null);
			IntrospectorModel modelo = new IntrospectorModel("Programa",
					parser.getAST());
			new IntrospectorTree("Introspector", modelo);
		}
	}

}