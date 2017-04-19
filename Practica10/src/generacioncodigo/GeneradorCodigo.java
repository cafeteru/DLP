package generacioncodigo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import ast.tipos.Tipo;
import ast.tipos.TipoCaracter;
import ast.tipos.TipoEntero;
import ast.tipos.TipoReal;

public class GeneradorCodigo {
	private static GeneradorCodigo instance;
	private static PrintWriter out;

	public static GeneradorCodigo getInstancia(String entrada, String salida) {
		if (instance == null)
			instance = new GeneradorCodigo(entrada, salida);
		return instance;
	}

	private GeneradorCodigo(String entrada, String salida) {
		try {
			out = new PrintWriter(new File(salida));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		source(entrada);
	}

	public void push(char c) {
		out.println("\tpushb\t" + (int) c);
		out.flush();
	}

	public void push(int c) {
		out.println("\tpushi\t" + c);
		out.flush();
	}

	public void push(double c) {
		out.println("\tpushf\t" + c);
		out.flush();
	}

	public void pusha(int c) {
		out.println("\tpusha\t" + c);
		out.flush();
	}

	public void pushBP() {
		out.println("\tpush\tbp");
		out.flush();
	}

	public void load(String sufijo) {
		out.println("\tload" + sufijo);
		out.flush();
	}

	public void store(String sufijo) {
		out.println("\tstore" + sufijo);
		out.flush();
	}

	public void pop(String sufijo) {
		out.println("\tpop" + sufijo);
		out.flush();
	}

	public void dup(String sufijo) {
		out.println("\tdup" + sufijo);
		out.flush();
	}

	public void add(String sufijo) {
		out.println("\tadd" + sufijo);
		out.flush();
	}

	public void add() {
		out.println("\taddi");
		out.flush();
	}

	public void sub(String sufijo) {
		out.println("\tsub" + sufijo);
		out.flush();
	}

	public void mul(String sufijo) {
		out.println("\tmul" + sufijo);
		out.flush();
	}

	public void div(String sufijo) {
		out.println("\tdiv" + sufijo);
		out.flush();
	}

	public void mod(String sufijo) {
		out.println("\tmod" + sufijo);
		out.flush();
	}

	public void gt(String sufijo) {
		out.println("\tgt" + sufijo);
		out.flush();
	}

	public void lt(String sufijo) {
		out.println("\tlt" + sufijo);
		out.flush();
	}

	public void ge(String sufijo) {
		out.println("\tge" + sufijo);
		out.flush();
	}

	public void le(String sufijo) {
		out.println("\tle" + sufijo);
		out.flush();
	}

	public void eq(String sufijo) {
		out.println("\teq" + sufijo);
		out.flush();
	}

	public void ne(String sufijo) {
		out.println("\tne" + sufijo);
		out.flush();
	}

	public void and() {
		out.println("\tand");
		out.flush();
	}

	public void or() {
		out.println("\tor");
		out.flush();
	}

	public void not() {
		out.println("\tnot");
		out.flush();
	}

	public void out(String sufijo) {
		out.println("\tout" + sufijo);
		out.flush();
	}

	public void in(String sufijo) {
		out.println("\tin" + sufijo);
		out.flush();
	}

	public void b2i() {
		out.println("\tb2i");
		out.flush();
	}

	public void i2f() {
		out.println("\ti2f");
		out.flush();
	}

	public void f2i() {
		out.println("\tf2i");
		out.flush();
	}

	public void i2b() {
		out.println("\ti2b");
		out.flush();
	}

	public void id(String etiqueta) {
		out.println(etiqueta + ":");
		out.flush();
	}

	public void call(String etiqueta) {
		out.println("call " + etiqueta);
		out.flush();
	}

	public void enter(int nBytes) {
		out.println("\tenter\t" + nBytes);
		out.flush();
	}

	public void ret(int nBytesRetorno, int nBytesVariablesLocales,
			int nBytesParametros) {
		out.println("\tret " + nBytesRetorno + ", " + nBytesVariablesLocales
				+ ", " + nBytesParametros);
		out.flush();
	}

	public void halt() {
		out.println("halt\n");
		out.flush();
	}

	public void source(String constant) {
		out.println("\n#source \"" + constant + "\"\n");
		out.flush();
	}

	public void line(int constant) {
		out.println("\n#line\t" + constant);
		out.flush();
	}

	public void aritmetrica(Tipo tipo, String operador) {
		switch (operador) {
		case "+":
			add(tipo.sufijo());
			break;
		case "-":
			sub(tipo.sufijo());
			break;
		case "*":
			mul(tipo.sufijo());
			break;
		case "/":
			div(tipo.sufijo());
			break;
		case "%":
			mod(tipo.sufijo());
			break;
		default:
			throw new IllegalStateException(
					"Operación no válida ->" + this.getClass());
		}
	}

	public void comparacion(Tipo tipo, String operador) {
		switch (operador) {
		case ">":
			gt(tipo.sufijo());
			break;
		case ">=":
			ge(tipo.sufijo());
			break;
		case "<":
			lt(tipo.sufijo());
			break;
		case "<=":
			le(tipo.sufijo());
			break;
		case "==":
			eq(tipo.sufijo());
			break;
		case "!=":
			ne(tipo.sufijo());
			break;
		default:
			throw new IllegalStateException(
					"Comparación no válida ->" + this.getClass());
		}
	}

	// Logica son int internamente
	public void logica(String operador) {
		switch (operador) {
		case "&&":
			and();
			break;
		case "||":
			or();
			break;
		case "!":
			not();
			break;
		default:
			throw new IllegalStateException(
					"Comparación no válida ->" + this.getClass());
		}
	}

	public void comentario(String comentario) {
		out.println("' " + comentario);
		out.flush();
	}

	public void comentarioSentencia(String comentario) {
		out.println("\t' " + comentario);
		out.flush();
	}

	public void llamadaMain() {
		out.println();
		comentario("Invocation to the main function");
		call("main");
		halt();
	}

	public void convertirA(Tipo tipo, Tipo tipo2) {
		if (tipo instanceof TipoReal && tipo2 instanceof TipoEntero)
			f2i();
		else if (tipo instanceof TipoCaracter && tipo2 instanceof TipoEntero)
			b2i();
		else if (tipo instanceof TipoCaracter && tipo2 instanceof TipoReal) {
			b2i();
			i2f();
		} else if (tipo instanceof TipoReal && tipo2 instanceof TipoCaracter) {
			f2i();
			i2b();
		} else if (tipo instanceof TipoEntero && tipo2 instanceof TipoReal)
			i2f();
		else if (tipo instanceof TipoEntero && tipo2 instanceof TipoCaracter)
			i2b();
	}
}
