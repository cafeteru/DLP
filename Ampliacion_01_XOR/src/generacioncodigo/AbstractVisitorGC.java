package generacioncodigo;

import ast.Programa;
import ast.definiciones.*;
import ast.expresiones.*;
import ast.sentencias.*;
import ast.tipos.*;
import visitor.Visitor;

public abstract class AbstractVisitorGC implements Visitor {

	public Object visit(Programa programa, Object o) {
		throw new IllegalStateException();
	}

	public Object visit(DefVariable defVariable, Object o)
			throws IllegalStateException {
		throw new IllegalStateException();
	}

	public Object visit(DefFuncion defFuncion, Object o)
			throws IllegalStateException {
		throw new IllegalStateException();
	}

	public Object visit(AccesoArray accesoArray, Object o)
			throws IllegalStateException {
		throw new IllegalStateException();
	}

	public Object visit(AccesoCampo accesoCampo, Object o)
			throws IllegalStateException {
		throw new IllegalStateException();
	}

	public Object visit(Aritmetica aritmetica, Object o)
			throws IllegalStateException {
		throw new IllegalStateException();
	}

	public Object visit(Cast cast, Object o) throws IllegalStateException {
		throw new IllegalStateException();
	}

	public Object visit(Comparacion comparacion, Object o)
			throws IllegalStateException {
		throw new IllegalStateException();
	}

	public Object visit(LiteralCaracter literalCaracter, Object o)
			throws IllegalStateException {
		throw new IllegalStateException();
	}

	public Object visit(LiteralEntero literalEntero, Object o)
			throws IllegalStateException {
		throw new IllegalStateException();
	}

	public Object visit(LiteralReal literalReal, Object o)
			throws IllegalStateException {
		throw new IllegalStateException();
	}

	public Object visit(Logica logica, Object o) throws IllegalStateException {
		throw new IllegalStateException();
	}

	public Object visit(MenosUnario menosUnario, Object o)
			throws IllegalStateException {
		throw new IllegalStateException();
	}

	public Object visit(Negacion negacion, Object o)
			throws IllegalStateException {
		throw new IllegalStateException();
	}

	public Object visit(Variable variable, Object o)
			throws IllegalStateException {
		throw new IllegalStateException();
	}

	public Object visit(Asignacion asignacion, Object o)
			throws IllegalStateException {
		throw new IllegalStateException();
	}

	public Object visit(Escritura escritura, Object o)
			throws IllegalStateException {
		throw new IllegalStateException();
	}

	public Object visit(Invocacion invocacion, Object o)
			throws IllegalStateException {
		throw new IllegalStateException();
	}

	public Object visit(Lectura lectura, Object o)
			throws IllegalStateException {
		throw new IllegalStateException();
	}

	public Object visit(Return return1, Object o) throws IllegalStateException {
		throw new IllegalStateException();
	}

	public Object visit(SentenciaIf if1, Object o)
			throws IllegalStateException {
		throw new IllegalStateException();
	}

	public Object visit(SentenciaWhile sentenciaWhile, Object o)
			throws IllegalStateException {
		throw new IllegalStateException();
	}

	public Object visit(CampoRegistro campoRegistro, Object o)
			throws IllegalStateException {
		throw new IllegalStateException();
	}

	public Object visit(TipoArray tipoArray, Object o)
			throws IllegalStateException {
		throw new IllegalStateException();
	}

	public Object visit(TipoCaracter caracter, Object o)
			throws IllegalStateException {
		throw new IllegalStateException();
	}

	public Object visit(TipoEntero tipoEntero, Object o)
			throws IllegalStateException {
		throw new IllegalStateException();
	}

	public Object visit(TipoError error, Object o)
			throws IllegalStateException {
		throw new IllegalStateException();
	}

	public Object visit(TipoFuncion funcion, Object o)
			throws IllegalStateException {
		throw new IllegalStateException();
	}

	public Object visit(TipoReal real, Object o) throws IllegalStateException {
		throw new IllegalStateException();
	}

	public Object visit(TipoRegistro registro, Object o)
			throws IllegalStateException {
		throw new IllegalStateException();
	}

	public Object visit(TipoVoid tipoVoid, Object o)
			throws IllegalStateException {
		throw new IllegalStateException();
	}

	@Override
	public Object visit(XOR xor, Object o) throws IllegalStateException {
		throw new IllegalStateException();
	}

}
