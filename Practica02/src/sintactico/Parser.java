package sintactico;

import lexico.Lexico;

/**
 * Clase Analizador Sintáctico (Parser).<br/>
 * Diseño de Lenguajes de Programación.<br/>
 * Escuela de Ingeniería Informática.<br/>
 * Universidad de Oviedo.<br/>
 * 
 * @author Francisco Ortin
 */

public class Parser {

	// * Tokens
	public final static int CTE_ENTERA = 257;
	public final static int READ = 258;
	public final static int WRITE = 259;
	public final static int WHILE = 260;
	public final static int IF = 261;
	public final static int ELSE = 262;
	public final static int INT = 263;
	public final static int DOUBLE = 264;
	public final static int CHAR = 265;
	public final static int STRUCT = 266;
	public final static int RETURN = 267;
	public final static int VOID = 268;
	public final static int MAIN = 269;
	public static final int ID = 270;
	public static final int CTE_REAL = 271;
	public static final int CTE_CARACTER = 272;
	public static final int Y = 273;
	public static final int O = 274;
	public static final int MAYORIGUALQUE = 275;
	public static final int MENORIGUALQUE = 276;
	public static final int DISTINTO = 277;
	public static final int IGUALDAD = 278;

	/**
	 * Referencia al analizador léxico
	 */
	private Lexico lexico;

	// * Constructor del Sintáctico
	public Parser(Lexico lexico) {
		// * El sintático conoce al léxico
		this.setLexico(lexico);
	}

	public Lexico getLexico() {
		return lexico;
	}

	public void setLexico(Lexico lexico) {
		this.lexico = lexico;
	}

}