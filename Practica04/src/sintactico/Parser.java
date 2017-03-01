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




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//## **user defined:Object
String   yytext;//user variable to return contextual strings
Object yyval; //used to return semantic vals from action routines
Object yylval;//the 'lval' (result) I got from yylex()
Object valstk[] = new Object[YYSTACKSIZE];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
final void val_init()
{
  yyval=new Object();
  yylval=new Object();
  valptr=-1;
}
final void val_push(Object val)
{
  try {
    valptr++;
    valstk[valptr]=val;
  }
  catch (ArrayIndexOutOfBoundsException e) {
    int oldsize = valstk.length;
    int newsize = oldsize*2;
    Object[] newstack = new Object[newsize];
    System.arraycopy(valstk,0,newstack,0,oldsize);
    valstk = newstack;
    valstk[valptr]=val;
  }
}
final Object val_pop()
{
  return valstk[valptr--];
}
final void val_drop(int cnt)
{
  valptr -= cnt;
}
final Object val_peek(int relative)
{
  return valstk[valptr-relative];
}
final Object dup_yyval(Object val)
{
  return val;
}
//#### end semantic value section ####
public final static short CTE_ENTERA=257;
public final static short CTE_REAL=258;
public final static short CTE_CARACTER=259;
public final static short READ=260;
public final static short WRITE=261;
public final static short WHILE=262;
public final static short IF=263;
public final static short ELSE=264;
public final static short INT=265;
public final static short DOUBLE=266;
public final static short CHAR=267;
public final static short STRUCT=268;
public final static short RETURN=269;
public final static short VOID=270;
public final static short MAIN=271;
public final static short ID=272;
public final static short Y=273;
public final static short O=274;
public final static short MAYORIGUALQUE=275;
public final static short MENORIGUALQUE=276;
public final static short DISTINTO=277;
public final static short IGUALDAD=278;
public final static short MENOS_UNARIO=279;
public final static short NEGACION=280;
public final static short MENORQUEELSE=281;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    4,    4,    4,    2,    2,    6,    6,
    6,    7,    7,    3,    3,    9,    9,    9,    9,    9,
    9,    9,    9,   11,   11,   14,   14,    8,    8,    8,
   12,   12,   10,   10,   10,   10,   10,   10,   10,   10,
   10,   10,   10,   10,   10,   10,   10,   10,   10,   10,
   10,   10,   10,   10,   10,   13,   17,   17,   16,   16,
   15,   15,    5,    5,    5,
};
final static short yylen[] = {                            2,
    9,    2,    0,    9,    9,    2,    3,    0,    4,    2,
    0,    1,    0,    2,    1,    4,    7,    5,    7,    3,
    3,    2,    3,    3,    1,    3,    2,    2,    3,    5,
    3,    1,    1,    1,    1,    1,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    2,
    2,    3,    3,    4,    1,    4,    1,    0,    4,    3,
    3,    1,    1,    1,    1,
};
final static short yydefred[] = {                         3,
    0,    0,   63,   64,   65,    0,    0,    2,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    6,    0,    0,
    0,    0,    0,    0,    0,    0,   62,    0,    0,   27,
    0,    0,    0,    0,    0,    0,   60,   61,    0,    0,
   26,    8,   10,    0,    0,    0,   59,    0,    8,    0,
    8,   34,   35,   36,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   15,    0,    0,    0,    9,
    0,    0,    0,   55,    0,    0,    0,    0,    0,    0,
   50,   51,    0,    0,    1,   14,    7,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   22,    0,    0,    0,   21,    0,   20,    0,    0,
   23,   52,    0,    0,    0,   53,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   38,   39,   41,
    5,    4,    0,    0,    0,   56,   54,   16,    0,    0,
   25,    0,    0,    0,    0,   17,   24,   19,
};
final static short yydgoto[] = {                          1,
    2,   48,  103,    8,   19,   35,  104,   65,   66,   67,
  142,   73,   74,   21,   16,   17,  114,
};
final static short yysindex[] = {                         0,
    0,  -55,    0,    0,    0, -110, -234,    0,  -90,  -41,
   10,   14,   21,   40, -209,   48,  -88,    0,  -80,   46,
 -105,  109, -180, -180,   35, -131,    0, -101,   48,    0,
 -115,   99,   42, -103,   -2,   96,    0,    0,   74,   48,
    0,    0,    0,   47, -180,   50,    0,   87,    0, -100,
    0,    0,    0,    0,   -5,   -5,  139,  141,   -5,    1,
   -5,   -5,  184,  103,  130,    0,  -28,  132,   87,    0,
   87,   57,   59,    0,   85,   -5,   -5,  -16,   -5,   -5,
    0,    0,  147,    8,    0,    0,    0,   -5,   -5,   -5,
   -5,   -5,   -5,   -5,   -5,   -5,   -5,   -5,   -5,   -5,
   -5,    0,  211,   70,   78,    0,   -5,    0,   15,   22,
    0,    0,  153,  164,   -5,    0,   64,   64,  159,  159,
  159,  159,   29,  159,  159,  143,  143,    0,    0,    0,
    0,    0,   57,   84,  119,    0,    0,    0,  211,  211,
    0,  -56,  138,  154,  119,    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   95,    0,  155,    0,    0,    0,    0,
    0,    0,  101,  101,    0,    0,    0,    0,  160,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   46,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  -37,
    0,    0,    0,    0,    0,    0,    0,   36,   93,    0,
   93,   71,    0,    0,    0,    0,    0,    0,    0,  179,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   97,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  182,    0,    0,    0,   49,   94,  343,  403,
  447,  453,    0,  475,  481,  416,  441,    0,    0,    0,
    0,    0,   72,    0,    0,    0,    0,    0,    0,    0,
    0,  176,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  100,  -18,    0,  123,  201,  156,  112,   31,  468,
   81,  -44,  129,    0,  -15,    0,    0,
};
final static int YYTABLESIZE=759;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         33,
   15,   29,   28,   33,   33,   33,   33,   33,  101,   33,
   15,   75,   11,   99,   97,   40,   98,   18,  100,   31,
  101,   33,   33,   33,   33,   99,   97,   61,   98,   64,
  100,   96,   94,   95,   63,  113,   12,   13,   44,   62,
   80,   45,  111,   96,  101,   95,   79,   25,  116,   99,
   97,  101,   98,   22,  100,  134,   99,   97,  101,   98,
   23,  100,  135,   99,   97,  101,   98,   96,  100,   95,
   99,   97,   55,   98,   96,  100,   95,   55,   55,   24,
   55,   96,   55,   95,    3,    4,    5,  138,   96,   48,
   95,   26,   48,  101,   86,   55,   55,   55,   99,   97,
  101,   98,  107,  100,   30,   99,   97,   48,   98,   48,
  100,   32,   31,   10,   32,   31,   96,  106,   95,   61,
  143,  144,   20,   96,    9,   95,   63,   37,  107,   32,
   31,   62,   32,   86,   49,   61,   46,   49,   62,   45,
   38,   11,   63,  108,   11,   34,   34,   62,   69,   33,
   71,   61,   49,   62,   49,   39,   27,   41,   63,    3,
    4,    5,    6,   62,   42,  141,   47,   50,   43,   49,
   61,   70,   51,   86,   86,  141,   68,   63,   76,  101,
   77,   14,   62,   27,   99,   83,   61,  115,   87,  100,
  102,   27,   68,   63,  131,  101,  107,   68,   62,   68,
   99,   97,  132,   98,  136,  100,  139,  145,   18,    3,
    4,    5,    6,   28,    7,   18,   61,   13,   29,   58,
   18,   12,   57,   63,   36,  148,  105,   85,   62,    0,
    0,   68,    0,    0,    0,   33,   33,   33,   33,   33,
   33,  140,    0,   61,   88,   89,   90,   91,   92,   93,
   63,   52,   53,   54,    0,   62,   88,   89,   90,   91,
   92,   93,  146,   68,    0,    0,   60,   68,   68,    0,
    0,   68,   68,   68,    3,    4,    5,    6,  147,    0,
   88,   89,   90,   91,   92,   93,    0,   88,   89,   90,
   91,   92,   93,    0,   88,   89,   90,   91,   92,   93,
   18,   88,   89,   90,   91,   92,   93,    0,   55,   55,
   55,   55,   55,   55,    0,    0,    0,    0,    0,    0,
    0,   48,   48,    0,    0,    0,    0,    0,    0,   88,
   89,   90,   91,   92,   93,    0,    0,    0,   90,   91,
   92,   93,    0,   52,   53,   54,   55,   56,   57,   58,
    0,    3,    4,    5,    6,   59,    0,    0,   60,   52,
   53,   54,   55,   56,   57,   58,   49,   49,    0,    0,
    0,   59,    0,    0,   60,   52,   53,   54,   55,   56,
   57,   58,    0,   44,    0,    0,   44,   59,    0,    0,
   60,    0,    0,    0,   52,   53,   54,   55,   56,   57,
   58,   44,   44,   44,   44,    0,   59,    0,    0,   60,
   52,   53,   54,   55,   56,   57,   58,    0,    0,    0,
    0,    0,   59,    0,    0,   60,    0,    0,    0,    0,
    0,    0,   18,   18,   18,   18,   18,   18,   18,    0,
   52,   53,   54,   45,   18,    0,   45,   18,    3,    4,
    5,    0,    0,    0,    0,   60,   37,    0,   37,   37,
   37,   45,   45,   45,   45,    0,    0,   52,   53,   54,
   55,   56,   57,   58,   37,   37,   37,   37,    0,   59,
    0,   40,   60,   40,   40,   40,    0,   46,    0,    0,
   46,    0,    0,   47,    0,    0,   47,    0,    0,   40,
   40,   40,   40,    0,    0,   46,   46,   46,   46,    0,
    0,   47,   47,   47,   47,   42,    0,    0,   42,    0,
    0,   43,   72,   72,   43,    0,   78,    0,   81,   82,
   84,    0,    0,   42,   42,   42,   42,    0,    0,   43,
   43,   43,   43,  109,  110,    0,  112,   72,    0,    0,
    0,    0,    0,    0,    0,  117,  118,  119,  120,  121,
  122,  123,  124,  125,  126,  127,  128,  129,  130,    0,
    0,    0,    0,    0,  133,    0,    0,    0,    0,    0,
    0,    0,  137,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   44,   44,   44,   44,   44,
   44,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   45,   45,   45,   45,   45,
   45,    0,    0,    0,    0,    0,    0,    0,   37,   37,
   37,   37,   37,   37,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   40,   40,   40,   40,   40,   40,   46,
   46,   46,   46,   46,   46,   47,   47,   47,   47,   47,
   47,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   42,   42,   42,
   42,   42,   42,   43,   43,   43,   43,   43,   43,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
   91,   17,   91,   41,   42,   43,   44,   45,   37,   47,
   91,   56,  123,   42,   43,   31,   45,   59,   47,  125,
   37,   59,   60,   61,   62,   42,   43,   33,   45,   48,
   47,   60,   61,   62,   40,   80,  271,  272,   41,   45,
   40,   44,   59,   60,   37,   62,   46,  257,   41,   42,
   43,   37,   45,   40,   47,   41,   42,   43,   37,   45,
   40,   47,   41,   42,   43,   37,   45,   60,   47,   62,
   42,   43,   37,   45,   60,   47,   62,   42,   43,   40,
   45,   60,   47,   62,  265,  266,  267,   59,   60,   41,
   62,   44,   44,   37,   64,   60,   61,   62,   42,   43,
   37,   45,   44,   47,   59,   42,   43,   59,   45,   61,
   47,   41,   41,    2,   44,   44,   60,   59,   62,   33,
  139,  140,   11,   60,    2,   62,   40,   93,   44,   59,
   59,   45,   21,  103,   41,   33,   41,   44,   44,   44,
  272,   41,   40,   59,   44,   23,   24,   45,   49,   41,
   51,   33,   59,   59,   61,  257,  272,   59,   40,  265,
  266,  267,  268,   45,  123,  135,   93,   45,  272,  123,
   33,  272,  123,  143,  144,  145,   48,   40,   40,   37,
   40,  272,   45,  272,   42,   63,   33,   41,   59,   47,
   59,  272,   64,   40,  125,   37,   44,   69,   45,   71,
   42,   43,  125,   45,   41,   47,  123,  264,   33,  265,
  266,  267,  268,   59,  270,   40,   33,  125,   59,   41,
   45,  125,   41,   40,   24,  145,   71,  125,   45,   -1,
   -1,  103,   -1,   -1,   -1,  273,  274,  275,  276,  277,
  278,  123,   -1,   33,  273,  274,  275,  276,  277,  278,
   40,  257,  258,  259,   -1,   45,  273,  274,  275,  276,
  277,  278,  125,  135,   -1,   -1,  272,  139,  140,   -1,
   -1,  143,  144,  145,  265,  266,  267,  268,  125,   -1,
  273,  274,  275,  276,  277,  278,   -1,  273,  274,  275,
  276,  277,  278,   -1,  273,  274,  275,  276,  277,  278,
  125,  273,  274,  275,  276,  277,  278,   -1,  273,  274,
  275,  276,  277,  278,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  273,  274,   -1,   -1,   -1,   -1,   -1,   -1,  273,
  274,  275,  276,  277,  278,   -1,   -1,   -1,  275,  276,
  277,  278,   -1,  257,  258,  259,  260,  261,  262,  263,
   -1,  265,  266,  267,  268,  269,   -1,   -1,  272,  257,
  258,  259,  260,  261,  262,  263,  273,  274,   -1,   -1,
   -1,  269,   -1,   -1,  272,  257,  258,  259,  260,  261,
  262,  263,   -1,   41,   -1,   -1,   44,  269,   -1,   -1,
  272,   -1,   -1,   -1,  257,  258,  259,  260,  261,  262,
  263,   59,   60,   61,   62,   -1,  269,   -1,   -1,  272,
  257,  258,  259,  260,  261,  262,  263,   -1,   -1,   -1,
   -1,   -1,  269,   -1,   -1,  272,   -1,   -1,   -1,   -1,
   -1,   -1,  257,  258,  259,  260,  261,  262,  263,   -1,
  257,  258,  259,   41,  269,   -1,   44,  272,  265,  266,
  267,   -1,   -1,   -1,   -1,  272,   41,   -1,   43,   44,
   45,   59,   60,   61,   62,   -1,   -1,  257,  258,  259,
  260,  261,  262,  263,   59,   60,   61,   62,   -1,  269,
   -1,   41,  272,   43,   44,   45,   -1,   41,   -1,   -1,
   44,   -1,   -1,   41,   -1,   -1,   44,   -1,   -1,   59,
   60,   61,   62,   -1,   -1,   59,   60,   61,   62,   -1,
   -1,   59,   60,   61,   62,   41,   -1,   -1,   44,   -1,
   -1,   41,   55,   56,   44,   -1,   59,   -1,   61,   62,
   63,   -1,   -1,   59,   60,   61,   62,   -1,   -1,   59,
   60,   61,   62,   76,   77,   -1,   79,   80,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   88,   89,   90,   91,   92,
   93,   94,   95,   96,   97,   98,   99,  100,  101,   -1,
   -1,   -1,   -1,   -1,  107,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  115,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  273,  274,  275,  276,  277,
  278,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  273,  274,  275,  276,  277,
  278,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  273,  274,
  275,  276,  277,  278,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  273,  274,  275,  276,  277,  278,  273,
  274,  275,  276,  277,  278,  273,  274,  275,  276,  277,
  278,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  273,  274,  275,
  276,  277,  278,  273,  274,  275,  276,  277,  278,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=281;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,"'%'",null,null,"'('","')'","'*'","'+'",
"','","'-'","'.'","'/'",null,null,null,null,null,null,null,null,null,null,null,
"';'","'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,"CTE_ENTERA","CTE_REAL",
"CTE_CARACTER","READ","WRITE","WHILE","IF","ELSE","INT","DOUBLE","CHAR",
"STRUCT","RETURN","VOID","MAIN","ID","Y","O","MAYORIGUALQUE","MENORIGUALQUE",
"DISTINTO","IGUALDAD","MENOS_UNARIO","NEGACION","MENORQUEELSE",
};
final static String yyrule[] = {
"$accept : programa",
"programa : definiciones VOID MAIN '(' ')' '{' declaraciones sentencias '}'",
"definiciones : definiciones definicion",
"definiciones :",
"definicion : tipoSimple ID '(' parametros ')' '{' declaraciones cuerpoDefinicion '}'",
"definicion : VOID ID '(' parametros ')' '{' declaraciones cuerpoDefinicion '}'",
"definicion : declaracionVariable ';'",
"declaraciones : declaraciones declaracionVariable ';'",
"declaraciones :",
"parametros : parametros ',' tipoSimple ID",
"parametros : tipoSimple ID",
"parametros :",
"cuerpoDefinicion : sentencias",
"cuerpoDefinicion :",
"sentencias : sentencias sentencia",
"sentencias : sentencia",
"sentencia : expresion '=' expresion ';'",
"sentencia : WHILE '(' expresion ')' '{' sentencias '}'",
"sentencia : IF '(' expresion ')' cuerpoCondicional",
"sentencia : IF '(' expresion ')' cuerpoCondicional ELSE cuerpoCondicional",
"sentencia : WRITE expresiones ';'",
"sentencia : READ expresiones ';'",
"sentencia : invocacion ';'",
"sentencia : RETURN expresion ';'",
"cuerpoCondicional : '{' sentencias '}'",
"cuerpoCondicional : sentencia",
"campos : campos declaracionVariable ';'",
"campos : declaracionVariable ';'",
"declaracionVariable : tipoSimple identificador",
"declaracionVariable : tipoSimple indices identificador",
"declaracionVariable : STRUCT '{' campos '}' identificador",
"expresiones : expresiones ',' expresion",
"expresiones : expresion",
"expresion : ID",
"expresion : CTE_ENTERA",
"expresion : CTE_REAL",
"expresion : CTE_CARACTER",
"expresion : expresion '+' expresion",
"expresion : expresion '*' expresion",
"expresion : expresion '/' expresion",
"expresion : expresion '-' expresion",
"expresion : expresion '%' expresion",
"expresion : expresion '>' expresion",
"expresion : expresion '<' expresion",
"expresion : expresion MAYORIGUALQUE expresion",
"expresion : expresion MENORIGUALQUE expresion",
"expresion : expresion DISTINTO expresion",
"expresion : expresion IGUALDAD expresion",
"expresion : expresion Y expresion",
"expresion : expresion O expresion",
"expresion : '!' expresion",
"expresion : '-' expresion",
"expresion : ID '.' expresion",
"expresion : '(' expresion ')'",
"expresion : '(' tipoSimple ')' expresion",
"expresion : invocacion",
"invocacion : ID '(' argumentosLlamada ')'",
"argumentosLlamada : expresiones",
"argumentosLlamada :",
"indices : indices '[' CTE_ENTERA ']'",
"indices : '[' CTE_ENTERA ']'",
"identificador : identificador ',' ID",
"identificador : ID",
"tipoSimple : INT",
"tipoSimple : DOUBLE",
"tipoSimple : CHAR",
};

//#line 194 "../../src/sintactico/sintactico.y"

// * Código Java
// * Se crea una clase "Parser", lo que aquí ubiquemos será:
//	- Atributos, si son variables
//	- Métodos, si son funciones
//   de la clase "Parser"

private NodoAST ast;
public NodoAST getAST(){ return this.ast; }

// * Estamos obligados a implementar:
//	int yylex()
//	void yyerror(String)

// * Referencia al analizador léxico
private Lexico lexico;

// * Llamada al analizador léxico
private int yylex () {
    int token=0;
    try { 
	token=lexico.yylex(); 
	this.yyval = lexico.getYylval();
    } catch(Throwable e) {
	    System.err.println ("Error Léxico en línea " + lexico.getLine()+
		" y columna "+lexico.getColumn()+":\n\t"+e); 
    }
    return token;
}

// * Manejo de Errores Sintácticos
public void yyerror (String error) {
    System.err.println ("Error Sintáctico en línea " + lexico.getLine()+
		" y columna "+lexico.getColumn()+":\n\t"+error);
}

// * Constructor del Sintáctico
public Parser(Lexico lexico) {
	this.lexico = lexico;
}
//#line 501 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 39 "../../src/sintactico/sintactico.y"
{ 	List<Definicion> aux = (List<Definicion>)val_peek(8);
																						
																						aux.add(new DefFuncion(lexico.getLine(), lexico.getColumn(), "main",
																							new TipoFuncion(new ArrayList(), TipoVoid.getInstancia()), 
																							(List<DefVariable>)val_peek(2), (List<Sentencia>)val_peek(1)));
																						
																						this.ast = new Programa(lexico.getLine(), lexico.getColumn(), aux); }
break;
case 2:
//#line 47 "../../src/sintactico/sintactico.y"
{	yyval = val_peek(1); for(Definicion aux : (List<Definicion>)val_peek(0)) 
																									((List<Definicion>)yyval).add(aux); }
break;
case 3:
//#line 49 "../../src/sintactico/sintactico.y"
{	yyval = new ArrayList();}
break;
case 4:
//#line 52 "../../src/sintactico/sintactico.y"
{ 	List<Definicion> lista = new ArrayList();
																										lista.add(new DefFuncion(lexico.getLine(), lexico.getColumn(), (String) val_peek(7),
																											new TipoFuncion((List<DefVariable>)val_peek(5), (Tipo)val_peek(8)), 
																											(List<DefVariable>)val_peek(2), (List<Sentencia>)val_peek(1)));
																										yyval = lista; }
break;
case 5:
//#line 58 "../../src/sintactico/sintactico.y"
{	List<Definicion> lista = new ArrayList();
																										lista.add(new DefFuncion(lexico.getLine(), lexico.getColumn(),  (String) val_peek(7),
																										new TipoFuncion((List<DefVariable>)val_peek(5), TipoVoid.getInstancia()), 
																										(List<DefVariable>)val_peek(2), (List<Sentencia>)val_peek(1)));
																										yyval = lista;}
break;
case 6:
//#line 64 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(1);  }
break;
case 7:
//#line 67 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(2);
															for(DefVariable aux : (List<DefVariable>)val_peek(1)) 
																((List<DefVariable>)yyval).add(aux); }
break;
case 8:
//#line 70 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<DefVariable>();}
break;
case 9:
//#line 73 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(3); ((List<DefVariable>)yyval).add(new DefVariable(lexico.getLine(), 
		  										  lexico.getColumn(), (String)val_peek(2), (Tipo)val_peek(3))); }
break;
case 10:
//#line 75 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<DefVariable>(); ((List<DefVariable>)yyval).add(new DefVariable(lexico.getLine(), 
		  										  lexico.getColumn(), (String)val_peek(0), (Tipo)val_peek(1)));  }
break;
case 11:
//#line 77 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<DefVariable>();}
break;
case 12:
//#line 80 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0);}
break;
case 13:
//#line 81 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<Sentencia>();}
break;
case 14:
//#line 84 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(1);((List<Sentencia>)yyval).add((Sentencia)val_peek(0)); }
break;
case 15:
//#line 85 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<Sentencia>(); ((List<Sentencia>)yyval).add((Sentencia)val_peek(0)); }
break;
case 17:
//#line 89 "../../src/sintactico/sintactico.y"
{ yyval = new SentenciaWhile(lexico.getLine(), lexico.getColumn(), (Expresion)val_peek(4), (List<Sentencia>)val_peek(1));}
break;
case 18:
//#line 90 "../../src/sintactico/sintactico.y"
{ yyval = new SentenciaIf(lexico.getLine(), lexico.getColumn(), (Expresion)val_peek(2), (List<Sentencia>)val_peek(0), new ArrayList()); }
break;
case 19:
//#line 91 "../../src/sintactico/sintactico.y"
{ yyval = new SentenciaIf(lexico.getLine(), lexico.getColumn(), (Expresion)val_peek(4), (List<Sentencia>)val_peek(2), (List<Sentencia>)val_peek(0));}
break;
case 20:
//#line 92 "../../src/sintactico/sintactico.y"
{ yyval = new Escritura(lexico.getLine(), lexico.getColumn(),(List<Expresion>)val_peek(1));}
break;
case 21:
//#line 93 "../../src/sintactico/sintactico.y"
{ yyval = new Lectura(lexico.getLine(), lexico.getColumn(),(List<Expresion>)val_peek(1)); }
break;
case 22:
//#line 94 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(1); }
break;
case 23:
//#line 95 "../../src/sintactico/sintactico.y"
{ yyval = new Return(lexico.getLine(), lexico.getColumn(), (Expresion)val_peek(1));}
break;
case 24:
//#line 98 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(1); }
break;
case 25:
//#line 99 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<Sentencia>(); ((List<Sentencia>)yyval).add((Sentencia)val_peek(0));  }
break;
case 26:
//#line 103 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(2);
													for(DefVariable aux : (List<DefVariable>)val_peek(1)) 
														((List<DefVariable>)yyval).add(aux); }
break;
case 27:
//#line 106 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(1);  }
break;
case 28:
//#line 109 "../../src/sintactico/sintactico.y"
{ 	List<DefVariable> variables = new ArrayList();
																for(String aux: (List<String>)val_peek(0)){
																	variables.add(new DefVariable(lexico.getLine(), lexico.getColumn(), aux, (Tipo)val_peek(1)));
															   }	 
															   yyval = variables; 
															 }
break;
case 29:
//#line 116 "../../src/sintactico/sintactico.y"
{	List<Integer> indices = (List<Integer>)val_peek(1);
		           												TipoArray tipo = new TipoArray(indices.get(0), (Tipo)val_peek(2));
		           												for(int i = 1; i < indices.size(); i++){
		           													tipo = new TipoArray(indices.get(i), tipo);
		           												}	           												
		           												
		           												List<DefVariable> variables = new ArrayList();
																for(String id: (List<String>)val_peek(0)){
																	variables.add(new DefVariable(lexico.getLine(), lexico.getColumn(), id, tipo));
															    }	 
															    yyval = variables;  }
break;
case 30:
//#line 127 "../../src/sintactico/sintactico.y"
{ 	List<CampoRegistro> registrosStruct = new ArrayList();
		           												for(DefVariable var : (List<DefVariable>) val_peek(2)){
		           													registrosStruct.add(new CampoRegistro(var.getNombre(), var.getTipo()));
		           												}
		           												
		           												TipoRegistro registro = new TipoRegistro(registrosStruct);
		           												          												
		           											    List<DefVariable> variables = new ArrayList();
																for(String aux: (List<String>)val_peek(0)){
																	variables.add(new DefVariable(lexico.getLine(), lexico.getColumn(), aux, registro));
															    }	 
															    yyval = variables; 															 
															 }
break;
case 31:
//#line 142 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(2); ((List<Expresion>)yyval).add((Expresion)val_peek(0)); }
break;
case 32:
//#line 143 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<Expresion>(); ((List<Expresion>)yyval).add((Expresion)val_peek(0));  }
break;
case 33:
//#line 146 "../../src/sintactico/sintactico.y"
{ yyval = new Variable(lexico.getLine(), lexico.getColumn(), (String) val_peek(0)); }
break;
case 34:
//#line 147 "../../src/sintactico/sintactico.y"
{ yyval = new LiteralEntero(lexico.getLine(), lexico.getColumn(), (Integer) val_peek(0));}
break;
case 35:
//#line 148 "../../src/sintactico/sintactico.y"
{ yyval = new LiteralReal(lexico.getLine(), lexico.getColumn(), (Double) val_peek(0));}
break;
case 36:
//#line 149 "../../src/sintactico/sintactico.y"
{ yyval = new LiteralCaracter(lexico.getLine(), lexico.getColumn(), (char) val_peek(0));}
break;
case 37:
//#line 150 "../../src/sintactico/sintactico.y"
{ yyval = new Aritmetica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "+", (Expresion) val_peek(0));}
break;
case 38:
//#line 151 "../../src/sintactico/sintactico.y"
{ yyval = new Aritmetica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "*", (Expresion) val_peek(0));}
break;
case 39:
//#line 152 "../../src/sintactico/sintactico.y"
{ yyval = new Aritmetica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "/", (Expresion) val_peek(0));}
break;
case 40:
//#line 153 "../../src/sintactico/sintactico.y"
{ yyval = new Aritmetica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "-", (Expresion) val_peek(0));}
break;
case 41:
//#line 154 "../../src/sintactico/sintactico.y"
{ yyval = new Aritmetica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "%", (Expresion) val_peek(0));}
break;
case 42:
//#line 155 "../../src/sintactico/sintactico.y"
{ yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), ">", (Expresion) val_peek(0));}
break;
case 43:
//#line 156 "../../src/sintactico/sintactico.y"
{ yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "<", (Expresion) val_peek(0));}
break;
case 44:
//#line 157 "../../src/sintactico/sintactico.y"
{ yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), ">=", (Expresion) val_peek(0));}
break;
case 45:
//#line 158 "../../src/sintactico/sintactico.y"
{ yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "<=", (Expresion) val_peek(0));}
break;
case 46:
//#line 159 "../../src/sintactico/sintactico.y"
{ yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "!=", (Expresion) val_peek(0));}
break;
case 47:
//#line 160 "../../src/sintactico/sintactico.y"
{ yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "==", (Expresion) val_peek(0));}
break;
case 48:
//#line 161 "../../src/sintactico/sintactico.y"
{ yyval = new Logica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "&&", (Expresion) val_peek(0));}
break;
case 49:
//#line 162 "../../src/sintactico/sintactico.y"
{ yyval = new Logica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "||", (Expresion) val_peek(0));}
break;
case 50:
//#line 163 "../../src/sintactico/sintactico.y"
{ yyval = new Negacion(lexico.getLine(), lexico.getColumn(),  "!", (Expresion) val_peek(1));}
break;
case 51:
//#line 164 "../../src/sintactico/sintactico.y"
{ yyval = new MenosUnario(lexico.getLine(), lexico.getColumn(),  "-",(Expresion) val_peek(1));}
break;
case 52:
//#line 165 "../../src/sintactico/sintactico.y"
{ yyval = new AccesoCampo(lexico.getLine(), lexico.getColumn(), (String) val_peek(2), (Expresion) val_peek(0));}
break;
case 53:
//#line 166 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(1);}
break;
case 54:
//#line 167 "../../src/sintactico/sintactico.y"
{ yyval = new Cast(lexico.getLine(), lexico.getColumn(), (Tipo) val_peek(2), (Expresion) val_peek(0));}
break;
case 55:
//#line 168 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0);}
break;
case 56:
//#line 171 "../../src/sintactico/sintactico.y"
{ yyval = new Invocacion(lexico.getLine(), lexico.getColumn(), 
											      new Variable(lexico.getLine(), lexico.getColumn(), 
											      (String)val_peek(3)),(List<Expresion>)val_peek(1)); }
break;
case 57:
//#line 176 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0);}
break;
case 58:
//#line 177 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<Expresion>();}
break;
case 59:
//#line 181 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(3); ((List<Integer>)yyval).add((int)val_peek(1)); }
break;
case 60:
//#line 182 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<Integer>(); ((List<Integer>)yyval).add((Integer)val_peek(1));  }
break;
case 61:
//#line 185 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(2); ((List<String>)yyval).add((String)val_peek(0)); }
break;
case 62:
//#line 186 "../../src/sintactico/sintactico.y"
{yyval = new ArrayList<String>(); ((List<String>)yyval).add((String)val_peek(0));  }
break;
case 63:
//#line 189 "../../src/sintactico/sintactico.y"
{ yyval = TipoEntero.getInstancia(); }
break;
case 64:
//#line 190 "../../src/sintactico/sintactico.y"
{ yyval = TipoReal.getInstancia(); }
break;
case 65:
//#line 191 "../../src/sintactico/sintactico.y"
{ yyval = TipoCaracter.getInstancia(); }
break;
//#line 956 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
