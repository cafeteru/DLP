//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";

package sintactico;

//#line 2 "../../src/sintactico/sintactico.y"
/* * Declaraciones de código Java*/
/* * Se sitúan al comienzo del archivo generado*/
/* * El package lo añade yacc si utilizamos la opción -Jpackage*/
import lexico.Lexico;
import java.io.Reader;
import java.util.*;

import ast.*;
import ast.definiciones.*;
import ast.expresiones.*;
import ast.sentencias.*;
import ast.tipos.*;
//#line 30 "Parser.java"

public class Parser {

	boolean yydebug; // do I want debug output?
	int yynerrs; // number of errors so far
	int yyerrflag; // was there an error?
	int yychar; // the current working character

	// ########## MESSAGES ##########
	// ###############################################################
	// method: debug
	// ###############################################################
	void debug(String msg) {
		if (yydebug)
			System.out.println(msg);
	}

	// ########## STATE STACK ##########
	final static int YYSTACKSIZE = 500; // maximum stack size
	int statestk[] = new int[YYSTACKSIZE]; // state stack
	int stateptr;
	int stateptrmax; // highest index of stackptr
	int statemax; // state when highest index reached
	// ###############################################################
	// methods: state stack push,pop,drop,peek
	// ###############################################################

	final void state_push(int state) {
		try {
			stateptr++;
			statestk[stateptr] = state;
		} catch (ArrayIndexOutOfBoundsException e) {
			int oldsize = statestk.length;
			int newsize = oldsize * 2;
			int[] newstack = new int[newsize];
			System.arraycopy(statestk, 0, newstack, 0, oldsize);
			statestk = newstack;
			statestk[stateptr] = state;
		}
	}

	final int state_pop() {
		return statestk[stateptr--];
	}

	final void state_drop(int cnt) {
		stateptr -= cnt;
	}

	final int state_peek(int relative) {
		return statestk[stateptr - relative];
	}

	// ###############################################################
	// method: init_stacks : allocate and prepare stacks
	// ###############################################################
	final boolean init_stacks() {
		stateptr = -1;
		val_init();
		return true;
	}

	// ###############################################################
	// method: dump_stacks : show n levels of the stacks
	// ###############################################################
	void dump_stacks(int count) {
		int i;
		System.out.println(
				"=index==state====value=     s:" + stateptr + "  v:" + valptr);
		for (i = 0; i < count; i++)
			System.out.println(
					" " + i + "    " + statestk[i] + "      " + valstk[i]);
		System.out.println("======================");
	}

	// ########## SEMANTIC VALUES ##########
	// ## **user defined:Object
	String yytext;// user variable to return contextual strings
	Object yyval; // used to return semantic vals from action routines
	Object yylval;// the 'lval' (result) I got from yylex()
	Object valstk[] = new Object[YYSTACKSIZE];
	int valptr;

	// ###############################################################
	// methods: value stack push,pop,drop,peek.
	// ###############################################################
	final void val_init() {
		yyval = new Object();
		yylval = new Object();
		valptr = -1;
	}

	final void val_push(Object val) {
		try {
			valptr++;
			valstk[valptr] = val;
		} catch (ArrayIndexOutOfBoundsException e) {
			int oldsize = valstk.length;
			int newsize = oldsize * 2;
			Object[] newstack = new Object[newsize];
			System.arraycopy(valstk, 0, newstack, 0, oldsize);
			valstk = newstack;
			valstk[valptr] = val;
		}
	}

	final Object val_pop() {
		return valstk[valptr--];
	}

	final void val_drop(int cnt) {
		valptr -= cnt;
	}

	final Object val_peek(int relative) {
		return valstk[valptr - relative];
	}

	final Object dup_yyval(Object val) {
		return val;
	}

	// #### end semantic value section ####
	public final static short CTE_ENTERA = 257;
	public final static short CTE_REAL = 258;
	public final static short CTE_CARACTER = 259;
	public final static short READ = 260;
	public final static short WRITE = 261;
	public final static short WHILE = 262;
	public final static short IF = 263;
	public final static short ELSE = 264;
	public final static short FOR = 265;
	public final static short INT = 266;
	public final static short DOUBLE = 267;
	public final static short CHAR = 268;
	public final static short STRUCT = 269;
	public final static short RETURN = 270;
	public final static short VOID = 271;
	public final static short MAIN = 272;
	public final static short ID = 273;
	public final static short Y = 274;
	public final static short O = 275;
	public final static short MAYORIGUALQUE = 276;
	public final static short MENORIGUALQUE = 277;
	public final static short DISTINTO = 278;
	public final static short IGUALDAD = 279;
	public final static short MENOS_UNARIO = 280;
	public final static short NEGACION = 281;
	public final static short CASTP = 282;
	public final static short MENORQUEELSE = 283;
	public final static short YYERRCODE = 256;
	final static short yylhs[] = { -1, 0, 1, 1, 4, 4, 4, 2, 2, 6, 6, 6, 9, 7, 7,
			3, 3, 10, 10, 10, 10, 10, 10, 10, 10, 10, 15, 15, 16, 16, 17, 12,
			12, 8, 8, 8, 8, 23, 23, 13, 13, 11, 11, 11, 11, 11, 11, 11, 11, 11,
			11, 11, 11, 11, 20, 20, 20, 20, 20, 19, 19, 19, 19, 19, 19, 18, 18,
			18, 14, 24, 24, 22, 22, 21, 21, 5, 5, 5, };
	final static short yylen[] = { 2, 9, 2, 0, 9, 9, 2, 3, 0, 3, 1, 0, 2, 1, 0,
			2, 1, 4, 5, 5, 7, 3, 3, 2, 3, 9, 3, 0, 1, 1, 3, 3, 1, 2, 3, 5, 6, 3,
			2, 3, 1, 1, 1, 1, 1, 1, 1, 1, 2, 3, 3, 4, 1, 4, 3, 3, 3, 3, 3, 3, 3,
			3, 3, 3, 3, 3, 3, 2, 4, 1, 0, 4, 3, 3, 1, 1, 1, 1, };
	final static short yydefred[] = { 3, 0, 0, 75, 76, 77, 0, 0, 2, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 74, 0, 38, 0, 0, 0, 0, 0,
			10, 0, 0, 73, 0, 0, 37, 8, 12, 0, 0, 0, 71, 0, 0, 8, 9, 8, 42, 43,
			44, 0, 0, 0, 0, 0, 0, 41, 0, 0, 0, 0, 0, 16, 0, 0, 47, 46, 45, 0, 0,
			0, 0, 52, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 15, 7, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 23, 0, 0, 0, 22, 0, 21, 0, 0, 0, 0,
			24, 0, 50, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 49, 0, 0, 5,
			4, 0, 0, 0, 0, 0, 0, 17, 53, 68, 0, 32, 18, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 31, 20, 0, 0, 0, 0, 0, 25, };
	final static short yydgoto[] = { 1, 2, 49, 109, 8, 19, 34, 110, 67, 35, 68,
			69, 153, 77, 78, 118, 157, 166, 71, 72, 73, 16, 17, 21, 139, };
	final static short yysindex[] = { 0, 0, -144, 0, 0, 0, -97, -271, 0, -80,
			-29, -53, 23, 24, 31, -176, 46, -175, 0, -79, 40, -105, 65, -49,
			-49, 14, -161, 0, 46, 0, -79, 57, -6, -141, 77, 0, 116, 47, 0, 46,
			-175, 0, 0, 0, 10, -49, 20, 0, 46, 322, 0, 0, 0, 0, 0, 0, 454, 454,
			104, 109, 128, 454, 0, 454, 563, 454, 410, 120, 0, 68, 139, 0, 0, 0,
			322, 322, 485, 2, 0, 29, 454, 454, 454, 94, -19, 165, 105, -19, 0,
			0, 0, 454, 454, 454, 454, 454, 454, 454, 454, 454, 454, 454, 454,
			454, 454, 454, -63, 454, 0, 445, 87, 96, 0, 454, 0, 129, 141, 162,
			166, 0, 454, 0, 515, 515, 223, 223, 223, 223, 294, 223, 223, 286,
			286, -19, -19, -19, 351, 0, 182, 186, 0, 0, 485, 381, 381, 454, 454,
			-19, 0, 0, 0, 445, 0, 0, -36, 485, 485, 170, 0, 0, 508, 381, 454, 0,
			0, 385, 189, 454, 381, 0, 0, };
	final static short yyrindex[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			38, 0, 172, 0, 0, 0, 0, 0, 0, 156, 156, 0, 0, 0, 174, 0, 0, 0, 0, 0,
			0, 0, 0, -39, 0, 177, 0, 0, 0, 0, 0, 0, 0, 0, 184, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 411, 0, 0, 0, 119, 119,
			114, 0, 0, 0, 0, 0, 193, 0, -37, 0, 0, -28, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 204, 0, 130, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 573, 834, 633, 745, 783, 793, 0, 805, 828, 695, 739,
			-2, 7, 33, 0, 0, 217, 0, 0, 0, 136, 0, 0, 0, 0, 42, 0, 0, 0, 0, 0,
			0, 553, 200, 0, 0, 422, 446, 0, 0, 0, 0, 0, 0, 0, 0, 0, 474, 0, };
	final static short yygindex[] = { 0, 0, 140, -31, 0, 233, 237, 187, 26, 219,
			145, 1030, -106, -54, 807, 0, 0, 0, 121, 125, 112, 79, -1, 0, 0, };
	final static int YYTABLESIZE = 1197;
	static short yytable[];
	static {
		yytable();
	}

	static void yytable() {
		yytable = new short[] { 48, 12, 13, 79, 48, 48, 48, 48, 48, 67, 48, 15,
				15, 67, 67, 67, 67, 67, 66, 67, 30, 107, 48, 48, 48, 48, 11,
				106, 10, 40, 18, 67, 67, 67, 67, 55, 47, 20, 154, 55, 55, 55,
				55, 55, 56, 55, 113, 31, 56, 56, 56, 56, 56, 138, 56, 164, 48,
				55, 55, 55, 55, 112, 170, 22, 23, 67, 56, 56, 56, 56, 58, 24,
				105, 113, 58, 58, 58, 58, 58, 51, 58, 25, 74, 51, 51, 51, 51,
				51, 114, 51, 26, 55, 58, 58, 58, 58, 28, 74, 27, 29, 56, 51, 51,
				51, 51, 104, 32, 37, 107, 39, 102, 100, 38, 101, 106, 103, 41,
				42, 44, 48, 160, 45, 3, 4, 5, 6, 58, 7, 99, 97, 98, 104, 43, 50,
				107, 51, 102, 100, 15, 101, 106, 103, 104, 52, 80, 107, 121,
				102, 100, 81, 101, 106, 103, 119, 99, 40, 98, 46, 40, 105, 45,
				3, 4, 5, 6, 99, 104, 98, 82, 107, 143, 102, 100, 40, 101, 106,
				103, 39, 104, 90, 39, 107, 144, 102, 100, 105, 101, 106, 103,
				99, 74, 98, 75, 14, 27, 39, 105, 11, 108, 104, 11, 99, 107, 98,
				102, 100, 120, 101, 106, 103, 137, 89, 140, 3, 4, 5, 6, 3, 4, 5,
				105, 141, 99, 145, 98, 146, 113, 150, 161, 162, 168, 33, 105,
				34, 72, 9, 35, 48, 48, 48, 48, 48, 48, 36, 14, 70, 67, 67, 67,
				67, 67, 67, 27, 105, 89, 13, 33, 33, 69, 26, 104, 36, 111, 107,
				51, 102, 100, 158, 101, 106, 103, 159, 55, 55, 55, 55, 55, 55,
				33, 169, 0, 56, 56, 56, 56, 56, 56, 0, 152, 152, 0, 0, 0, 0, 0,
				0, 0, 85, 0, 0, 0, 0, 0, 0, 0, 89, 152, 58, 58, 58, 58, 58, 58,
				152, 105, 0, 51, 51, 51, 51, 51, 51, 0, 104, 0, 0, 107, 0, 102,
				0, 0, 104, 106, 103, 107, 0, 102, 100, 0, 101, 106, 103, 91, 92,
				93, 94, 95, 96, 0, 0, 0, 0, 0, 148, 99, 65, 98, 0, 0, 0, 0, 0,
				64, 0, 0, 0, 0, 63, 91, 92, 93, 94, 95, 96, 0, 0, 0, 105, 0, 91,
				92, 93, 94, 95, 96, 105, 0, 0, 104, 0, 0, 107, 0, 102, 100, 0,
				101, 106, 103, 0, 0, 0, 0, 91, 92, 93, 94, 95, 96, 0, 0, 99, 0,
				98, 65, 91, 92, 93, 94, 95, 96, 64, 104, 0, 0, 107, 63, 102,
				100, 0, 101, 106, 103, 0, 0, 0, 91, 92, 93, 94, 95, 96, 105, 65,
				149, 99, 167, 98, 52, 0, 64, 52, 0, 52, 52, 63, 52, 52, 52, 47,
				0, 0, 47, 0, 47, 47, 0, 47, 47, 47, 0, 52, 52, 52, 0, 0, 105, 0,
				65, 0, 0, 28, 47, 46, 47, 64, 46, 65, 46, 46, 63, 46, 46, 46,
				64, 0, 0, 0, 0, 63, 0, 0, 52, 0, 151, 29, 46, 0, 46, 0, 0, 45,
				0, 47, 45, 30, 45, 45, 0, 45, 45, 45, 104, 0, 0, 107, 0, 102,
				100, 0, 101, 106, 103, 0, 45, 88, 45, 46, 0, 0, 0, 65, 0, 0, 0,
				99, 0, 98, 64, 0, 0, 0, 104, 63, 0, 107, 0, 102, 100, 0, 101,
				106, 103, 0, 0, 45, 0, 0, 91, 92, 93, 94, 95, 96, 0, 99, 105,
				98, 0, 53, 54, 55, 56, 57, 58, 59, 19, 60, 3, 4, 5, 6, 61, 19,
				0, 62, 65, 0, 19, 0, 0, 0, 0, 64, 0, 0, 105, 0, 63, 0, 0, 0, 0,
				0, 65, 0, 0, 65, 0, 0, 0, 0, 0, 0, 0, 91, 92, 93, 94, 95, 96, 0,
				65, 163, 65, 0, 0, 0, 53, 54, 55, 56, 57, 58, 59, 0, 60, 0, 0,
				0, 0, 61, 0, 0, 62, 0, 0, 0, 0, 91, 92, 93, 94, 95, 96, 0, 65,
				53, 54, 55, 56, 57, 58, 59, 61, 60, 0, 61, 19, 0, 61, 0, 0, 62,
				0, 52, 52, 52, 52, 52, 52, 0, 61, 61, 61, 61, 47, 47, 47, 47,
				47, 47, 53, 54, 55, 56, 57, 58, 59, 0, 60, 53, 54, 55, 0, 61, 0,
				0, 62, 0, 46, 46, 46, 46, 46, 46, 61, 62, 0, 0, 0, 0, 0, 0, 0,
				0, 54, 0, 54, 54, 54, 0, 0, 0, 0, 0, 0, 0, 45, 45, 45, 45, 45,
				45, 54, 54, 54, 54, 0, 91, 92, 93, 94, 95, 96, 53, 54, 55, 56,
				57, 58, 59, 0, 60, 0, 0, 0, 0, 61, 0, 57, 62, 57, 57, 57, 0, 62,
				0, 54, 62, 0, 93, 94, 95, 96, 0, 0, 0, 57, 57, 57, 57, 0, 0, 62,
				62, 62, 62, 0, 0, 19, 19, 19, 19, 19, 19, 19, 0, 19, 0, 53, 54,
				55, 19, 63, 0, 19, 63, 0, 3, 4, 5, 57, 0, 64, 0, 62, 64, 62, 0,
				0, 0, 63, 63, 63, 63, 59, 65, 65, 59, 0, 0, 64, 64, 64, 64, 70,
				0, 0, 0, 0, 0, 0, 0, 59, 59, 59, 59, 0, 60, 0, 0, 60, 70, 0, 66,
				63, 0, 66, 0, 0, 70, 70, 0, 0, 0, 64, 60, 60, 60, 60, 0, 0, 66,
				0, 66, 0, 0, 59, 0, 0, 0, 0, 0, 0, 0, 0, 61, 61, 61, 61, 61, 61,
				0, 0, 0, 70, 0, 0, 0, 0, 60, 0, 0, 0, 0, 0, 66, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 70, 70, 0, 0,
				0, 0, 0, 0, 70, 0, 0, 0, 0, 0, 0, 0, 0, 70, 70, 54, 54, 54, 54,
				54, 54, 70, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 57,
				57, 57, 57, 57, 57, 62, 62, 62, 62, 62, 62, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 63, 63, 63, 63, 63, 63, 0, 0, 0, 0, 64, 64, 64, 64,
				64, 64, 0, 0, 0, 0, 0, 0, 59, 59, 59, 59, 59, 59, 0, 76, 76, 0,
				0, 0, 83, 0, 84, 86, 87, 0, 0, 0, 0, 0, 0, 60, 60, 60, 60, 60,
				60, 66, 66, 115, 116, 117, 0, 0, 0, 0, 0, 0, 0, 0, 122, 123,
				124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136,
				0, 76, 0, 0, 0, 0, 0, 142, 0, 0, 0, 0, 0, 0, 147, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 155,
				156, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 165, 0, 0, 0,
				0, 156, };
	}

	static short yycheck[];
	static {
		yycheck();
	}

	static void yycheck() {
		yycheck = new short[] { 37, 272, 273, 57, 41, 42, 43, 44, 45, 37, 47,
				91, 91, 41, 42, 43, 44, 45, 49, 47, 125, 40, 59, 60, 61, 62,
				123, 46, 2, 30, 59, 59, 60, 61, 62, 37, 37, 11, 144, 41, 42, 43,
				44, 45, 37, 47, 44, 21, 41, 42, 43, 44, 45, 107, 47, 161, 93,
				59, 60, 61, 62, 59, 168, 40, 40, 93, 59, 60, 61, 62, 37, 40, 91,
				44, 41, 42, 43, 44, 45, 37, 47, 257, 44, 41, 42, 43, 44, 45, 59,
				47, 44, 93, 59, 60, 61, 62, 17, 59, 273, 59, 93, 59, 60, 61, 62,
				37, 41, 93, 40, 30, 42, 43, 273, 45, 46, 47, 59, 123, 41, 40,
				151, 44, 266, 267, 268, 269, 93, 271, 60, 61, 62, 37, 273, 123,
				40, 93, 42, 43, 91, 45, 46, 47, 37, 123, 40, 40, 41, 42, 43, 40,
				45, 46, 47, 59, 60, 41, 62, 41, 44, 91, 44, 266, 267, 268, 269,
				60, 37, 62, 40, 40, 41, 42, 43, 59, 45, 46, 47, 41, 37, 59, 44,
				40, 41, 42, 43, 91, 45, 46, 47, 60, 50, 62, 52, 273, 273, 59,
				91, 41, 59, 37, 44, 60, 40, 62, 42, 43, 41, 45, 46, 47, 273, 66,
				125, 266, 267, 268, 269, 266, 267, 268, 91, 125, 60, 61, 62, 59,
				44, 41, 264, 59, 41, 59, 91, 59, 273, 2, 59, 274, 275, 276, 277,
				278, 279, 59, 125, 41, 274, 275, 276, 277, 278, 279, 59, 91,
				109, 125, 23, 24, 41, 59, 37, 24, 75, 40, 45, 42, 43, 146, 45,
				46, 47, 146, 274, 275, 276, 277, 278, 279, 45, 167, -1, 274,
				275, 276, 277, 278, 279, -1, 143, 144, -1, -1, -1, -1, -1, -1,
				-1, 64, -1, -1, -1, -1, -1, -1, -1, 160, 161, 274, 275, 276,
				277, 278, 279, 168, 91, -1, 274, 275, 276, 277, 278, 279, -1,
				37, -1, -1, 40, -1, 42, -1, -1, 37, 46, 47, 40, -1, 42, 43, -1,
				45, 46, 47, 274, 275, 276, 277, 278, 279, -1, -1, -1, -1, -1,
				59, 60, 33, 62, -1, -1, -1, -1, -1, 40, -1, -1, -1, -1, 45, 274,
				275, 276, 277, 278, 279, -1, -1, -1, 91, -1, 274, 275, 276, 277,
				278, 279, 91, -1, -1, 37, -1, -1, 40, -1, 42, 43, -1, 45, 46,
				47, -1, -1, -1, -1, 274, 275, 276, 277, 278, 279, -1, -1, 60,
				-1, 62, 33, 274, 275, 276, 277, 278, 279, 40, 37, -1, -1, 40,
				45, 42, 43, -1, 45, 46, 47, -1, -1, -1, 274, 275, 276, 277, 278,
				279, 91, 33, 93, 60, 61, 62, 37, -1, 40, 40, -1, 42, 43, 45, 45,
				46, 47, 37, -1, -1, 40, -1, 42, 43, -1, 45, 46, 47, -1, 60, 61,
				62, -1, -1, 91, -1, 33, -1, -1, 59, 60, 37, 62, 40, 40, 33, 42,
				43, 45, 45, 46, 47, 40, -1, -1, -1, -1, 45, -1, -1, 91, -1, 123,
				59, 60, -1, 62, -1, -1, 37, -1, 91, 40, 41, 42, 43, -1, 45, 46,
				47, 37, -1, -1, 40, -1, 42, 43, -1, 45, 46, 47, -1, 60, 125, 62,
				91, -1, -1, -1, 33, -1, -1, -1, 60, -1, 62, 40, -1, -1, -1, 37,
				45, -1, 40, -1, 42, 43, -1, 45, 46, 47, -1, -1, 91, -1, -1, 274,
				275, 276, 277, 278, 279, -1, 60, 91, 62, -1, 257, 258, 259, 260,
				261, 262, 263, 33, 265, 266, 267, 268, 269, 270, 40, -1, 273,
				33, -1, 45, -1, -1, -1, -1, 40, -1, -1, 91, -1, 45, -1, -1, -1,
				-1, -1, 41, -1, -1, 44, -1, -1, -1, -1, -1, -1, -1, 274, 275,
				276, 277, 278, 279, -1, 59, 125, 61, -1, -1, -1, 257, 258, 259,
				260, 261, 262, 263, -1, 265, -1, -1, -1, -1, 270, -1, -1, 273,
				-1, -1, -1, -1, 274, 275, 276, 277, 278, 279, -1, 93, 257, 258,
				259, 260, 261, 262, 263, 41, 265, -1, 44, 125, -1, 270, -1, -1,
				273, -1, 274, 275, 276, 277, 278, 279, -1, 59, 60, 61, 62, 274,
				275, 276, 277, 278, 279, 257, 258, 259, 260, 261, 262, 263, -1,
				265, 257, 258, 259, -1, 270, -1, -1, 273, -1, 274, 275, 276,
				277, 278, 279, 93, 273, -1, -1, -1, -1, -1, -1, -1, -1, 41, -1,
				43, 44, 45, -1, -1, -1, -1, -1, -1, -1, 274, 275, 276, 277, 278,
				279, 59, 60, 61, 62, -1, 274, 275, 276, 277, 278, 279, 257, 258,
				259, 260, 261, 262, 263, -1, 265, -1, -1, -1, -1, 270, -1, 41,
				273, 43, 44, 45, -1, 41, -1, 93, 44, -1, 276, 277, 278, 279, -1,
				-1, -1, 59, 60, 61, 62, -1, -1, 59, 60, 61, 62, -1, -1, 257,
				258, 259, 260, 261, 262, 263, -1, 265, -1, 257, 258, 259, 270,
				41, -1, 273, 44, -1, 266, 267, 268, 93, -1, 41, -1, 273, 44, 93,
				-1, -1, -1, 59, 60, 61, 62, 41, 274, 275, 44, -1, -1, 59, 60,
				61, 62, 49, -1, -1, -1, -1, -1, -1, -1, 59, 60, 61, 62, -1, 41,
				-1, -1, 44, 66, -1, 41, 93, -1, 44, -1, -1, 74, 75, -1, -1, -1,
				93, 59, 60, 61, 62, -1, -1, 59, -1, 61, -1, -1, 93, -1, -1, -1,
				-1, -1, -1, -1, -1, 274, 275, 276, 277, 278, 279, -1, -1, -1,
				109, -1, -1, -1, -1, 93, -1, -1, -1, -1, -1, 93, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, 143, 144, -1, -1, -1, -1, -1, -1, 151, -1, -1, -1, -1,
				-1, -1, -1, -1, 160, 161, 274, 275, 276, 277, 278, 279, 168, -1,
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, 274, 275, 276, 277, 278, 279, 274, 275, 276,
				277, 278, 279, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, 274, 275, 276, 277, 278, 279, -1, -1, -1, -1,
				274, 275, 276, 277, 278, 279, -1, -1, -1, -1, -1, -1, 274, 275,
				276, 277, 278, 279, -1, 56, 57, -1, -1, -1, 61, -1, 63, 64, 65,
				-1, -1, -1, -1, -1, -1, 274, 275, 276, 277, 278, 279, 274, 275,
				80, 81, 82, -1, -1, -1, -1, -1, -1, -1, -1, 91, 92, 93, 94, 95,
				96, 97, 98, 99, 100, 101, 102, 103, 104, 105, -1, 107, -1, -1,
				-1, -1, -1, 113, -1, -1, -1, -1, -1, -1, 120, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, 145, 146, -1, -1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, 162, -1, -1, -1, -1, 167, };
	}

	final static short YYFINAL = 1;
	final static short YYMAXTOKEN = 283;
	final static String yyname[] = { "end-of-file", null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, "'!'", null, null, null, "'%'",
			null, null, "'('", "')'", "'*'", "'+'", "','", "'-'", "'.'", "'/'",
			null, null, null, null, null, null, null, null, null, null, null,
			"';'", "'<'", "'='", "'>'", null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null,
			"'['", null, "']'", null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, "'{'",
			null, "'}'", null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null,
			null, "CTE_ENTERA", "CTE_REAL", "CTE_CARACTER", "READ", "WRITE",
			"WHILE", "IF", "ELSE", "FOR", "INT", "DOUBLE", "CHAR", "STRUCT",
			"RETURN", "VOID", "MAIN", "ID", "Y", "O", "MAYORIGUALQUE",
			"MENORIGUALQUE", "DISTINTO", "IGUALDAD", "MENOS_UNARIO", "NEGACION",
			"CASTP", "MENORQUEELSE", };
	final static String yyrule[] = { "$accept : programa",
			"programa : definiciones VOID MAIN '(' ')' '{' declaraciones sentencias '}'",
			"definiciones : definiciones definicion", "definiciones :",
			"definicion : tipoSimple ID '(' parametros ')' '{' declaraciones cuerpoDefinicion '}'",
			"definicion : VOID ID '(' parametros ')' '{' declaraciones cuerpoDefinicion '}'",
			"definicion : declaracionVariable ';'",
			"declaraciones : declaraciones declaracionVariable ';'",
			"declaraciones :", "parametros : parametros ',' definicionVariable",
			"parametros : definicionVariable", "parametros :",
			"definicionVariable : tipoSimple ID",
			"cuerpoDefinicion : sentencias", "cuerpoDefinicion :",
			"sentencias : sentencias sentencia", "sentencias : sentencia",
			"sentencia : expresion '=' expresion ';'",
			"sentencia : WHILE '(' expresion ')' cuerpoCondicional",
			"sentencia : IF '(' expresion ')' cuerpoCondicional",
			"sentencia : IF '(' expresion ')' cuerpoCondicional ELSE cuerpoCondicional",
			"sentencia : WRITE expresiones ';'",
			"sentencia : READ expresiones ';'", "sentencia : invocacion ';'",
			"sentencia : RETURN expresion ';'",
			"sentencia : FOR '(' inicioFor ';' finFor ';' pasoFor ')' cuerpoCondicional",
			"inicioFor : expresion '=' expresion", "inicioFor :",
			"finFor : expresionLogica", "finFor : expresionComparacion",
			"pasoFor : expresion '=' expresionAritmetica",
			"cuerpoCondicional : '{' sentencias '}'",
			"cuerpoCondicional : sentencia",
			"declaracionVariable : tipoSimple identificador",
			"declaracionVariable : tipoSimple indices identificador",
			"declaracionVariable : STRUCT '{' campos '}' identificador",
			"declaracionVariable : STRUCT '{' campos '}' indices identificador",
			"campos : campos declaracionVariable ';'",
			"campos : declaracionVariable ';'",
			"expresiones : expresiones ',' expresion",
			"expresiones : expresion", "expresion : ID",
			"expresion : CTE_ENTERA", "expresion : CTE_REAL",
			"expresion : CTE_CARACTER", "expresion : expresionAritmetica",
			"expresion : expresionComparacion", "expresion : expresionLogica",
			"expresion : '-' expresion", "expresion : expresion '.' ID",
			"expresion : '(' expresion ')'",
			"expresion : '(' tipoSimple ')' expresion",
			"expresion : invocacion", "expresion : expresion '[' expresion ']'",
			"expresionAritmetica : expresion '+' expresion",
			"expresionAritmetica : expresion '*' expresion",
			"expresionAritmetica : expresion '/' expresion",
			"expresionAritmetica : expresion '-' expresion",
			"expresionAritmetica : expresion '%' expresion",
			"expresionComparacion : expresion '>' expresion",
			"expresionComparacion : expresion '<' expresion",
			"expresionComparacion : expresion MAYORIGUALQUE expresion",
			"expresionComparacion : expresion MENORIGUALQUE expresion",
			"expresionComparacion : expresion DISTINTO expresion",
			"expresionComparacion : expresion IGUALDAD expresion",
			"expresionLogica : expresion Y expresion",
			"expresionLogica : expresion O expresion",
			"expresionLogica : '!' expresion",
			"invocacion : expresion '(' argumentosLlamada ')'",
			"argumentosLlamada : expresiones", "argumentosLlamada :",
			"indices : '[' CTE_ENTERA ']' indices",
			"indices : '[' CTE_ENTERA ']'",
			"identificador : identificador ',' ID", "identificador : ID",
			"tipoSimple : INT", "tipoSimple : DOUBLE", "tipoSimple : CHAR", };

	// #line 265 "../../src/sintactico/sintactico.y"

	// * Código Java
	// * Se crea una clase "Parser", lo que aquí ubiquemos será:
	// - Atributos, si son variables
	// - Métodos, si son funciones
	// de la clase "Parser"

	private NodoAST ast;

	public NodoAST getAST() {
		return this.ast;
	}

	// * Estamos obligados a implementar:
	// int yylex()
	// void yyerror(String)

	// * Referencia al analizador léxico
	private Lexico lexico;

	// * Llamada al analizador léxico
	private int yylex() {
		int token = 0;
		try {
			token = lexico.yylex();
			this.yylval = lexico.getYylval();
		} catch (Throwable e) {
			System.err.println("Error Léxico en línea " + lexico.getLine()
					+ " y columna " + lexico.getColumn() + ":\n\t" + e);
		}
		return token;
	}

	// * Manejo de Errores Sintácticos
	public void yyerror(String error) {
		System.err.println("Error Sintáctico en línea " + lexico.getLine()
				+ " y columna " + lexico.getColumn() + ":\n\t" + error);
	}

	// * Constructor del Sintáctico
	public Parser(Lexico lexico) {
		this.lexico = lexico;
	}

	// #line 613 "Parser.java"
	// ###############################################################
	// method: yylexdebug : check lexer state
	// ###############################################################
	void yylexdebug(int state, int ch) {
		String s = null;
		if (ch < 0)
			ch = 0;
		if (ch <= YYMAXTOKEN) // check index bounds
			s = yyname[ch]; // now get it
		if (s == null)
			s = "illegal-symbol";
		debug("state " + state + ", reading " + ch + " (" + s + ")");
	}

	// The following are now global, to aid in error reporting
	int yyn; // next next thing to do
	int yym; //
	int yystate; // current parsing state from state table
	String yys; // current token string

	// ###############################################################
	// method: yyparse : parse input and execute indicated items
	// ###############################################################
	int yyparse() {
		boolean doaction;
		init_stacks();
		yynerrs = 0;
		yyerrflag = 0;
		yychar = -1; // impossible char forces a read
		yystate = 0; // initial state
		state_push(yystate); // save it
		val_push(yylval); // save empty value
		while (true) // until parsing is done, either correctly, or w/error
		{
			doaction = true;
			if (yydebug)
				debug("loop");
			// #### NEXT ACTION (from reduction table)
			for (yyn = yydefred[yystate]; yyn == 0; yyn = yydefred[yystate]) {
				if (yydebug)
					debug("yyn:" + yyn + "  state:" + yystate + "  yychar:"
							+ yychar);
				if (yychar < 0) // we want a char?
				{
					yychar = yylex(); // get next token
					if (yydebug)
						debug(" next yychar:" + yychar);
					// #### ERROR CHECK ####
					if (yychar < 0) // it it didn't work/error
					{
						yychar = 0; // change it to default string (no -1!)
						if (yydebug)
							yylexdebug(yystate, yychar);
					}
				} // yychar<0
				yyn = yysindex[yystate]; // get amount to shift by (shift index)
				if ((yyn != 0) && (yyn += yychar) >= 0 && yyn <= YYTABLESIZE
						&& yycheck[yyn] == yychar) {
					if (yydebug)
						debug("state " + yystate + ", shifting to state "
								+ yytable[yyn]);
					// #### NEXT STATE ####
					yystate = yytable[yyn];// we are in a new state
					state_push(yystate); // save it
					val_push(yylval); // push our lval as the input for next
										// rule
					yychar = -1; // since we have 'eaten' a token, say we need
									// another
					if (yyerrflag > 0) // have we recovered an error?
						--yyerrflag; // give ourselves credit
					doaction = false; // but don't process yet
					break; // quit the yyn=0 loop
				}

				yyn = yyrindex[yystate]; // reduce
				if ((yyn != 0) && (yyn += yychar) >= 0 && yyn <= YYTABLESIZE
						&& yycheck[yyn] == yychar) { // we reduced!
					if (yydebug)
						debug("reduce");
					yyn = yytable[yyn];
					doaction = true; // get ready to execute
					break; // drop down to actions
				} else // ERROR RECOVERY
				{
					if (yyerrflag == 0) {
						yyerror("syntax error");
						yynerrs++;
					}
					if (yyerrflag < 3) // low error count?
					{
						yyerrflag = 3;
						while (true) // do until break
						{
							if (stateptr < 0) // check for under & overflow here
							{
								yyerror("stack underflow. aborting..."); // note
																			// lower
																			// case
																			// 's'
								return 1;
							}
							yyn = yysindex[state_peek(0)];
							if ((yyn != 0) && (yyn += YYERRCODE) >= 0
									&& yyn <= YYTABLESIZE
									&& yycheck[yyn] == YYERRCODE) {
								if (yydebug)
									debug("state " + state_peek(0)
											+ ", error recovery shifting to state "
											+ yytable[yyn] + " ");
								yystate = yytable[yyn];
								state_push(yystate);
								val_push(yylval);
								doaction = false;
								break;
							} else {
								if (yydebug)
									debug("error recovery discarding state "
											+ state_peek(0) + " ");
								if (stateptr < 0) // check for under & overflow
													// here
								{
									yyerror("Stack underflow. aborting..."); // capital
																				// 'S'
									return 1;
								}
								state_pop();
								val_pop();
							}
						}
					} else // discard this token
					{
						if (yychar == 0)
							return 1; // yyabort
						if (yydebug) {
							yys = null;
							if (yychar <= YYMAXTOKEN)
								yys = yyname[yychar];
							if (yys == null)
								yys = "illegal-symbol";
							debug("state " + yystate
									+ ", error recovery discards token "
									+ yychar + " (" + yys + ")");
						}
						yychar = -1; // read another
					}
				} // end error recovery
			} // yyn=0 loop
			if (!doaction) // any reason not to proceed?
				continue; // skip action
			yym = yylen[yyn]; // get count of terminals on rhs
			if (yydebug)
				debug("state " + yystate + ", reducing " + yym + " by rule "
						+ yyn + " (" + yyrule[yyn] + ")");
			if (yym > 0) // if count of rhs not 'nil'
				yyval = val_peek(yym - 1); // get current semantic value
			yyval = dup_yyval(yyval); // duplicate yyval if ParserVal is used as
										// semantic value
			switch (yyn) {
			// ########## USER-SUPPLIED ACTIONS ##########
			case 1:
			// #line 43 "../../src/sintactico/sintactico.y"
			{
				List<Definicion> definiciones = (List<Definicion>) val_peek(8);
				Definicion main = new DefFuncion(lexico.getLine(),
						lexico.getColumn(), "main",
						new TipoFuncion(new ArrayList<DefVariable>(),
								TipoVoid.getInstancia()),
						(List<DefVariable>) val_peek(2),
						(List<Sentencia>) val_peek(1));
				definiciones.add(main);

				this.ast = new Programa(lexico.getLine(), lexico.getColumn(),
						definiciones);
			}
				break;
			case 2:
			// #line 51 "../../src/sintactico/sintactico.y"
			{
				yyval = val_peek(1);
				List<Definicion> lista = (List<Definicion>) yyval;
				List<Definicion> listaDefiniciones = (List<Definicion>) val_peek(
						0);
				for (int i = 0; i < listaDefiniciones.size(); i++) {
					Definicion elemento = listaDefiniciones.get(i);
					lista.add(elemento);
				}
			}
				break;
			case 3:
			// #line 59 "../../src/sintactico/sintactico.y"
			{
				yyval = new ArrayList<Definicion>();
			}
				break;
			case 4:
			// #line 62 "../../src/sintactico/sintactico.y"
			{
				List<Definicion> lista = new ArrayList();
				lista.add(new DefFuncion(lexico.getLine(), lexico.getColumn(),
						(String) val_peek(7),
						new TipoFuncion((List<DefVariable>) val_peek(5),
								(Tipo) val_peek(8)),
						(List<DefVariable>) val_peek(2),
						(List<Sentencia>) val_peek(1)));
				yyval = lista;
			}
				break;
			case 5:
			// #line 68 "../../src/sintactico/sintactico.y"
			{
				List<Definicion> lista = new ArrayList();
				lista.add(new DefFuncion(lexico.getLine(), lexico.getColumn(),
						(String) val_peek(7),
						new TipoFuncion((List<DefVariable>) val_peek(5),
								TipoVoid.getInstancia()),
						(List<DefVariable>) val_peek(2),
						(List<Sentencia>) val_peek(1)));
				yyval = lista;
			}
				break;
			case 6:
			// #line 74 "../../src/sintactico/sintactico.y"
			{
				yyval = val_peek(1);
			}
				break;
			case 7:
			// #line 77 "../../src/sintactico/sintactico.y"
			{
				yyval = val_peek(2);
				List<DefVariable> lista = (List<DefVariable>) yyval;
				for (DefVariable elemento : (List<DefVariable>) val_peek(1))
					lista.add(elemento);
			}
				break;
			case 8:
			// #line 82 "../../src/sintactico/sintactico.y"
			{
				yyval = new ArrayList<DefVariable>();
			}
				break;
			case 9:
			// #line 85 "../../src/sintactico/sintactico.y"
			{
				yyval = val_peek(2);
				List<DefVariable> lista = (List<DefVariable>) yyval;
				for (DefVariable elemento : (List<DefVariable>) val_peek(0))
					lista.add(elemento);
			}
				break;
			case 10:
			// #line 90 "../../src/sintactico/sintactico.y"
			{
				yyval = val_peek(0);
			}
				break;
			case 11:
			// #line 91 "../../src/sintactico/sintactico.y"
			{
				yyval = new ArrayList<DefVariable>();
			}
				break;
			case 12:
			// #line 94 "../../src/sintactico/sintactico.y"
			{
				yyval = new ArrayList<DefVariable>();
				((List<DefVariable>) yyval).add(
						new DefVariable(lexico.getLine(), lexico.getColumn(),
								(String) val_peek(0), (Tipo) val_peek(1)));
			}
				break;
			case 13:
			// #line 100 "../../src/sintactico/sintactico.y"
			{
				yyval = val_peek(0);
			}
				break;
			case 14:
			// #line 101 "../../src/sintactico/sintactico.y"
			{
				yyval = new ArrayList<Sentencia>();
			}
				break;
			case 15:
			// #line 104 "../../src/sintactico/sintactico.y"
			{
				yyval = val_peek(1);
				((List<Sentencia>) yyval).add((Sentencia) val_peek(0));
			}
				break;
			case 16:
			// #line 105 "../../src/sintactico/sintactico.y"
			{
				yyval = new ArrayList<Sentencia>();
				((List<Sentencia>) yyval).add((Sentencia) val_peek(0));
			}
				break;
			case 17:
			// #line 108 "../../src/sintactico/sintactico.y"
			{
				yyval = new Asignacion(lexico.getLine(), lexico.getColumn(),
						(Expresion) val_peek(3), (Expresion) val_peek(1));
			}
				break;
			case 18:
			// #line 109 "../../src/sintactico/sintactico.y"
			{
				yyval = new SentenciaWhile(lexico.getLine(), lexico.getColumn(),
						(Expresion) val_peek(2), (List<Sentencia>) val_peek(0));
			}
				break;
			case 19:
			// #line 110 "../../src/sintactico/sintactico.y"
			{
				yyval = new SentenciaIf(lexico.getLine(), lexico.getColumn(),
						(Expresion) val_peek(2), (List<Sentencia>) val_peek(0),
						new ArrayList());
			}
				break;
			case 20:
			// #line 111 "../../src/sintactico/sintactico.y"
			{
				yyval = new SentenciaIf(lexico.getLine(), lexico.getColumn(),
						(Expresion) val_peek(4), (List<Sentencia>) val_peek(2),
						(List<Sentencia>) val_peek(0));
			}
				break;
			case 21:
			// #line 112 "../../src/sintactico/sintactico.y"
			{
				yyval = new Escritura(lexico.getLine(), lexico.getColumn(),
						(List<Expresion>) val_peek(1));
			}
				break;
			case 22:
			// #line 113 "../../src/sintactico/sintactico.y"
			{
				yyval = new Lectura(lexico.getLine(), lexico.getColumn(),
						(List<Expresion>) val_peek(1));
			}
				break;
			case 23:
			// #line 114 "../../src/sintactico/sintactico.y"
			{
				yyval = val_peek(1);
			}
				break;
			case 24:
			// #line 115 "../../src/sintactico/sintactico.y"
			{
				yyval = new Return(lexico.getLine(), lexico.getColumn(),
						(Expresion) val_peek(1));
			}
				break;
			case 25:
			// #line 116 "../../src/sintactico/sintactico.y"
			{
				yyval = new SentenciaFor(lexico.getLine(), lexico.getColumn(),
						(List<Sentencia>) val_peek(6), (Expresion) val_peek(4),
						(Sentencia) val_peek(2), (List<Sentencia>) val_peek(0));
			}
				break;
			case 26:
			// #line 119 "../../src/sintactico/sintactico.y"
			{
				yyval = new ArrayList<Sentencia>();
				Asignacion a = new Asignacion(lexico.getLine(),
						lexico.getColumn(), (Expresion) val_peek(2),
						(Expresion) val_peek(0));
				((List<Sentencia>) yyval).add(a);
			}
				break;
			case 27:
			// #line 123 "../../src/sintactico/sintactico.y"
			{
				yyval = new ArrayList<Sentencia>();
			}
				break;
			case 28:
			// #line 126 "../../src/sintactico/sintactico.y"
			{
				yyval = val_peek(0);
			}
				break;
			case 29:
			// #line 127 "../../src/sintactico/sintactico.y"
			{
				yyval = val_peek(0);
			}
				break;
			case 30:
			// #line 130 "../../src/sintactico/sintactico.y"
			{
				yyval = new Asignacion(lexico.getLine(), lexico.getColumn(),
						(Expresion) val_peek(2), (Expresion) val_peek(0));
			}
				break;
			case 31:
			// #line 133 "../../src/sintactico/sintactico.y"
			{
				yyval = val_peek(1);
			}
				break;
			case 32:
			// #line 134 "../../src/sintactico/sintactico.y"
			{
				yyval = new ArrayList<Sentencia>();
				((List<Sentencia>) yyval).add((Sentencia) val_peek(0));
			}
				break;
			case 33:
			// #line 137 "../../src/sintactico/sintactico.y"
			{
				List<DefVariable> variables = new ArrayList<DefVariable>();
				for (String aux : (List<String>) val_peek(0))
					variables.add(new DefVariable(lexico.getLine(),
							lexico.getColumn(), aux, (Tipo) val_peek(1)));
				yyval = variables;
			}
				break;
			case 34:
			// #line 142 "../../src/sintactico/sintactico.y"
			{
				List<Integer> indices = (List<Integer>) val_peek(1);
				TipoArray tipo = new TipoArray(indices.get(0),
						(Tipo) val_peek(2));
				for (int i = 1; i < indices.size(); i++)
					tipo = new TipoArray(indices.get(i), tipo);
				List<DefVariable> variables = new ArrayList();
				for (String id : (List<String>) val_peek(0))
					variables.add(new DefVariable(lexico.getLine(),
							lexico.getColumn(), id, tipo));
				yyval = variables;
			}
				break;
			case 35:
			// #line 151 "../../src/sintactico/sintactico.y"
			{
				List<CampoRegistro> registrosStruct = new ArrayList();
				for (DefVariable var : (List<DefVariable>) val_peek(2))
					registrosStruct.add(new CampoRegistro(lexico.getLine(),
							lexico.getColumn(), var.getNombre(),
							var.getTipo()));
				TipoRegistro registro = new TipoRegistro(registrosStruct);
				List<DefVariable> variables = new ArrayList();
				for (String aux : (List<String>) val_peek(0))
					variables.add(new DefVariable(lexico.getLine(),
							lexico.getColumn(), aux, registro));
				yyval = variables;
			}
				break;
			case 36:
			// #line 160 "../../src/sintactico/sintactico.y"
			{
				List<CampoRegistro> registrosStruct = new ArrayList();
				for (DefVariable var : (List<DefVariable>) val_peek(3))
					registrosStruct.add(new CampoRegistro(lexico.getLine(),
							lexico.getColumn(), var.getNombre(),
							var.getTipo()));
				TipoRegistro registro = new TipoRegistro(registrosStruct);

				List<Integer> indices = (List<Integer>) val_peek(1);
				TipoArray tipo = new TipoArray(indices.get(0), registro);

				List<DefVariable> variables = new ArrayList();
				for (String id : (List<String>) val_peek(0))
					variables.add(new DefVariable(lexico.getLine(),
							lexico.getColumn(), id, tipo));
				yyval = variables;
			}
				break;
			case 37:
			// #line 176 "../../src/sintactico/sintactico.y"
			{
				yyval = val_peek(2);
				List<DefVariable> lista = (List<DefVariable>) yyval;
				List<String> nombres = new ArrayList();
				for (DefVariable nombre : lista)
					nombres.add(nombre.getNombre());
				for (DefVariable elemento : (List<DefVariable>) val_peek(1)) {
					if (!nombres.contains(elemento.getNombre()))
						(lista).add(elemento);
					else
						new TipoError(lexico.getLine(), lexico.getColumn(),
								"Campo duplicado -> " + elemento.getNombre());
				}
			}
				break;
			case 38:
			// #line 188 "../../src/sintactico/sintactico.y"
			{
				yyval = val_peek(1);
			}
				break;
			case 39:
			// #line 191 "../../src/sintactico/sintactico.y"
			{
				yyval = val_peek(2);
				List<Expresion> lista = (List<Expresion>) yyval;
				Expresion elemento = (Expresion) val_peek(0);
				lista.add(elemento);
			}
				break;
			case 40:
			// #line 196 "../../src/sintactico/sintactico.y"
			{
				yyval = new ArrayList<Expresion>();
				((List<Expresion>) yyval).add((Expresion) val_peek(0));
			}
				break;
			case 41:
			// #line 199 "../../src/sintactico/sintactico.y"
			{
				yyval = new Variable(lexico.getLine(), lexico.getColumn(),
						(String) val_peek(0));
			}
				break;
			case 42:
			// #line 200 "../../src/sintactico/sintactico.y"
			{
				yyval = new LiteralEntero(lexico.getLine(), lexico.getColumn(),
						(Integer) val_peek(0));
			}
				break;
			case 43:
			// #line 201 "../../src/sintactico/sintactico.y"
			{
				yyval = new LiteralReal(lexico.getLine(), lexico.getColumn(),
						(Double) val_peek(0));
			}
				break;
			case 44:
			// #line 202 "../../src/sintactico/sintactico.y"
			{
				yyval = new LiteralCaracter(lexico.getLine(),
						lexico.getColumn(), (Character) val_peek(0));
			}
				break;
			case 45:
			// #line 203 "../../src/sintactico/sintactico.y"
			{
				yyval = val_peek(0);
			}
				break;
			case 46:
			// #line 204 "../../src/sintactico/sintactico.y"
			{
				yyval = val_peek(0);
			}
				break;
			case 47:
			// #line 205 "../../src/sintactico/sintactico.y"
			{
				yyval = val_peek(0);
			}
				break;
			case 48:
			// #line 206 "../../src/sintactico/sintactico.y"
			{
				yyval = new MenosUnario(lexico.getLine(), lexico.getColumn(),
						"-", (Expresion) val_peek(0));
			}
				break;
			case 49:
			// #line 207 "../../src/sintactico/sintactico.y"
			{
				yyval = new AccesoCampo(lexico.getLine(), lexico.getColumn(),
						(Expresion) val_peek(2), (String) val_peek(0));
			}
				break;
			case 50:
			// #line 208 "../../src/sintactico/sintactico.y"
			{
				yyval = val_peek(1);
			}
				break;
			case 51:
			// #line 209 "../../src/sintactico/sintactico.y"
			{
				yyval = new Cast(lexico.getLine(), lexico.getColumn(),
						(Tipo) val_peek(2), (Expresion) val_peek(0));
			}
				break;
			case 52:
			// #line 210 "../../src/sintactico/sintactico.y"
			{
				yyval = val_peek(0);
			}
				break;
			case 53:
			// #line 211 "../../src/sintactico/sintactico.y"
			{
				yyval = new AccesoArray(lexico.getLine(), lexico.getColumn(),
						(Expresion) val_peek(3), (Expresion) val_peek(1));
			}
				break;
			case 54:
			// #line 214 "../../src/sintactico/sintactico.y"
			{
				yyval = new Aritmetica(lexico.getLine(), lexico.getColumn(),
						(Expresion) val_peek(2), "+", (Expresion) val_peek(0));
			}
				break;
			case 55:
			// #line 215 "../../src/sintactico/sintactico.y"
			{
				yyval = new Aritmetica(lexico.getLine(), lexico.getColumn(),
						(Expresion) val_peek(2), "*", (Expresion) val_peek(0));
			}
				break;
			case 56:
			// #line 216 "../../src/sintactico/sintactico.y"
			{
				yyval = new Aritmetica(lexico.getLine(), lexico.getColumn(),
						(Expresion) val_peek(2), "/", (Expresion) val_peek(0));
			}
				break;
			case 57:
			// #line 217 "../../src/sintactico/sintactico.y"
			{
				yyval = new Aritmetica(lexico.getLine(), lexico.getColumn(),
						(Expresion) val_peek(2), "-", (Expresion) val_peek(0));
			}
				break;
			case 58:
			// #line 218 "../../src/sintactico/sintactico.y"
			{
				yyval = new Aritmetica(lexico.getLine(), lexico.getColumn(),
						(Expresion) val_peek(2), "%", (Expresion) val_peek(0));
			}
				break;
			case 59:
			// #line 221 "../../src/sintactico/sintactico.y"
			{
				yyval = new Comparacion(lexico.getLine(), lexico.getColumn(),
						(Expresion) val_peek(2), ">", (Expresion) val_peek(0));
			}
				break;
			case 60:
			// #line 222 "../../src/sintactico/sintactico.y"
			{
				yyval = new Comparacion(lexico.getLine(), lexico.getColumn(),
						(Expresion) val_peek(2), "<", (Expresion) val_peek(0));
			}
				break;
			case 61:
			// #line 223 "../../src/sintactico/sintactico.y"
			{
				yyval = new Comparacion(lexico.getLine(), lexico.getColumn(),
						(Expresion) val_peek(2), ">=", (Expresion) val_peek(0));
			}
				break;
			case 62:
			// #line 224 "../../src/sintactico/sintactico.y"
			{
				yyval = new Comparacion(lexico.getLine(), lexico.getColumn(),
						(Expresion) val_peek(2), "<=", (Expresion) val_peek(0));
			}
				break;
			case 63:
			// #line 225 "../../src/sintactico/sintactico.y"
			{
				yyval = new Comparacion(lexico.getLine(), lexico.getColumn(),
						(Expresion) val_peek(2), "!=", (Expresion) val_peek(0));
			}
				break;
			case 64:
			// #line 226 "../../src/sintactico/sintactico.y"
			{
				yyval = new Comparacion(lexico.getLine(), lexico.getColumn(),
						(Expresion) val_peek(2), "==", (Expresion) val_peek(0));
			}
				break;
			case 65:
			// #line 228 "../../src/sintactico/sintactico.y"
			{
				yyval = new Logica(lexico.getLine(), lexico.getColumn(),
						(Expresion) val_peek(2), "&&", (Expresion) val_peek(0));
			}
				break;
			case 66:
			// #line 229 "../../src/sintactico/sintactico.y"
			{
				yyval = new Logica(lexico.getLine(), lexico.getColumn(),
						(Expresion) val_peek(2), "||", (Expresion) val_peek(0));
			}
				break;
			case 67:
			// #line 230 "../../src/sintactico/sintactico.y"
			{
				yyval = new Negacion(lexico.getLine(), lexico.getColumn(), "!",
						(Expresion) val_peek(0));
			}
				break;
			case 68:
			// #line 233 "../../src/sintactico/sintactico.y"
			{
				yyval = new Invocacion(lexico.getLine(), lexico.getColumn(),
						new Variable(lexico.getLine(), lexico.getColumn(),
								(String) val_peek(3)),
						(List<Expresion>) val_peek(1));
			}
				break;
			case 69:
			// #line 236 "../../src/sintactico/sintactico.y"
			{
				yyval = val_peek(0);
			}
				break;
			case 70:
			// #line 237 "../../src/sintactico/sintactico.y"
			{
				yyval = new ArrayList<Expresion>();
			}
				break;
			case 71:
			// #line 241 "../../src/sintactico/sintactico.y"
			{
				yyval = val_peek(0);
				List<Integer> lista = (List<Integer>) yyval;
				Integer elemento = (Integer) val_peek(2);
				lista.add(elemento);
			}
				break;
			case 72:
			// #line 246 "../../src/sintactico/sintactico.y"
			{
				yyval = new ArrayList<Integer>();
				((List<Integer>) yyval).add((Integer) val_peek(1));
			}
				break;
			case 73:
			// #line 249 "../../src/sintactico/sintactico.y"
			{
				yyval = val_peek(2);
				List<String> lista = (List<String>) yyval;
				String elemento = (String) val_peek(0);
				if (!lista.contains(elemento))
					lista.add(elemento);
				else
					new TipoError(lexico.getLine(), lexico.getColumn(),
							"Identificador duplicado -> " + elemento);
			}
				break;
			case 74:
			// #line 257 "../../src/sintactico/sintactico.y"
			{
				yyval = new ArrayList();
				((List<String>) yyval).add(((String) val_peek(0)));
			}
				break;
			case 75:
			// #line 260 "../../src/sintactico/sintactico.y"
			{
				yyval = TipoEntero.getInstancia();
			}
				break;
			case 76:
			// #line 261 "../../src/sintactico/sintactico.y"
			{
				yyval = TipoReal.getInstancia();
			}
				break;
			case 77:
			// #line 262 "../../src/sintactico/sintactico.y"
			{
				yyval = TipoCaracter.getInstancia();
			}
				break;
			// #line 1167 "Parser.java"
			// ########## END OF USER-SUPPLIED ACTIONS ##########
			}// switch
				// #### Now let's reduce... ####
			if (yydebug)
				debug("reduce");
			state_drop(yym); // we just reduced yylen states
			yystate = state_peek(0); // get new state
			val_drop(yym); // corresponding value drop
			yym = yylhs[yyn]; // select next TERMINAL(on lhs)
			if (yystate == 0 && yym == 0)// done? 'rest' state and at first
											// TERMINAL
			{
				if (yydebug)
					debug("After reduction, shifting from state 0 to state "
							+ YYFINAL + "");
				yystate = YYFINAL; // explicitly say we're done
				state_push(YYFINAL); // and save it
				val_push(yyval); // also save the semantic value of parsing
				if (yychar < 0) // we want another character?
				{
					yychar = yylex(); // get next character
					if (yychar < 0)
						yychar = 0; // clean, if necessary
					if (yydebug)
						yylexdebug(yystate, yychar);
				}
				if (yychar == 0) // Good exit (if lex returns 0 ;-)
					break; // quit the loop--all DONE
			} // if yystate
			else // else not done yet
			{ // get next state and push, for next yydefred[]
				yyn = yygindex[yym]; // find out where to go
				if ((yyn != 0) && (yyn += yystate) >= 0 && yyn <= YYTABLESIZE
						&& yycheck[yyn] == yystate)
					yystate = yytable[yyn]; // get new state
				else
					yystate = yydgoto[yym]; // else go to new defred
				if (yydebug)
					debug("after reduction, shifting from state "
							+ state_peek(0) + " to state " + yystate + "");
				state_push(yystate); // going again, so push state & val...
				val_push(yyval); // for next action
			}
		} // main loop
		return 0;// yyaccept!!
	}
	// ## end of method parse() ######################################

	// ## run() --- for Thread #######################################
	/**
	 * A default run method, used for operating this parser object in the
	 * background. It is intended for extending Thread or implementing Runnable.
	 * Turn off with -Jnorun .
	 */
	public void run() {
		yyparse();
	}
	// ## end of method run() ########################################

	// ## Constructors ###############################################
	/**
	 * Default constructor. Turn off with -Jnoconstruct .
	 * 
	 */
	public Parser() {
		// nothing to do
	}

	/**
	 * Create a parser, setting the debug to true or false.
	 * 
	 * @param debugMe
	 *            true for debugging, false for no debug.
	 */
	public Parser(boolean debugMe) {
		yydebug = debugMe;
	}
	// ###############################################################

}
// ################### END OF CLASS ##############################
