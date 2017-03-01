import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import ast.definiciones.DefFuncion;
import ast.definiciones.Definicion;
import ast.tipos.TipoFuncion;
import introspector.model.IntrospectorModel;
import introspector.view.IntrospectorTree;
import lexico.Lexico;
import sintactico.Parser;

/**
 * Prueba del analizador léxico.<br/>
 * Diseño de Lenguajes de Programación.<br/>
 * Escuela de Ingeniería Informática.<br/>
 * Universidad de Oviedo <br/>
 * 
 * @author Francisco Ortin
 */

public class Main {

	public static void main(String args[]) throws IOException {
		if (args.length < 1) {
			System.err.println("Necesito el archivo de entrada.");
			return;
		}

		FileReader fr = null;
		try {
			fr = new FileReader(args[0]);
		} catch (IOException io) {
			System.err.println("El archivo " + args[0] + " no se ha podido abrir.");
			return;
		}

		// * Creamos léxico y sintáctico
		Lexico lexico = new Lexico(fr);

		Parser parser = new Parser(lexico);
		// int token;
		// while ((token=lexico.yylex())!=0) {
		// System.out.println("Linea: "+lexico.getLine()+
		// ", columna: "+lexico.getColumn()+
		// ", token: "+token+
		// ", valor semántico: "+lexico.getYylval()+".");
		// }
		// * "Parseamos"
		parser.run();
		IntrospectorModel modelo = new IntrospectorModel("Programa", parser.getAST());
		new IntrospectorTree("Introspector", modelo);
	}

}