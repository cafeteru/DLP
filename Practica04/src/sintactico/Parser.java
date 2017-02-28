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
import ast.*;
//#line 24 "Parser.java"




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
    0,    1,    1,    3,    3,    3,    8,    8,   11,   11,
   11,    5,    5,    5,    6,    6,    2,    2,   12,   12,
   12,   12,   12,   12,   12,   12,   12,   12,   12,   12,
   15,   15,   10,   10,   13,   13,    7,    7,    7,   19,
   19,    9,    9,   14,   14,   14,   14,   14,   14,   14,
   14,   14,   14,   14,   14,   14,   14,   14,   14,   14,
   14,   14,   14,   14,   14,   14,   16,   16,   18,   18,
   17,   17,    4,    4,    4,
};
final static short yylen[] = {                            2,
    8,    2,    0,    8,    8,    2,    4,    4,    1,    1,
    1,    4,    2,    0,    1,    0,    2,    1,    1,    2,
    4,    4,    6,    7,    5,    7,    3,    3,    2,    3,
    3,    1,    3,    0,    2,    1,    2,    3,    5,    3,
    2,    3,    1,    1,    1,    1,    1,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    3,    2,    2,    3,    4,    1,    4,    3,    4,    3,
    3,    1,    1,    1,    1,
};
final static short yydefred[] = {                         3,
    0,    0,   73,   74,   75,    0,    0,    2,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    6,    0,    0,
    0,    0,    0,    0,    0,    0,   72,    0,    0,   41,
    0,    0,    0,    0,    0,    0,   70,   71,    0,   39,
   40,    0,   13,    0,    0,    0,   69,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   18,    0,    0,    0,
    0,   45,   46,   47,    0,    0,    0,    0,   66,    0,
   44,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    1,   17,    0,   20,   29,    0,    0,    0,   12,    0,
    0,    0,    0,    0,   28,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   27,    0,    0,   30,    0,    0,    0,    0,    0,    0,
    0,    5,    4,    0,   64,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   61,
    0,    0,   68,    0,    7,    8,    0,    0,   22,   21,
   65,    0,    0,   32,    0,    0,   10,   11,    9,   33,
   67,    0,    0,    0,   23,   24,   31,   26,
};
final static short yydgoto[] = {                          1,
    2,   87,    8,   19,   35,   88,   55,   69,   70,  118,
  160,   57,   71,   72,  155,   80,   16,   17,   21,
};
final static short yysindex[] = {                         0,
    0,  -56,    0,    0,    0, -120, -222,    0,  -90,  -45,
 -116,   -8,   14,   15, -219,   21,  -89,    0,  -64,   17,
   29,   43, -173, -173,   30, -145,    0, -129,   21,    0,
 -134,   87,   35, -110,   41,   91,    0,    0,   82,    0,
    0,  578,    0,   57, -173,   64,    0,  108,  108,  161,
  164,  108,    6,  400,   58,  157,    0,  156,  578,  -39,
  578,    0,    0,    0,  -28,  108,  108,  213,    0,   31,
    0,  246,   66,  108,  108,   97,  108,  -38,  108,  151,
    0,    0,  108,    0,    0,  108,  578,  118,    0,  129,
  199,  199,  215,  124,    0,  108,  108,  108,  108,  108,
  108,  108,  108,  108,  108,  108,  108,  108,  108,  108,
    0,  131,  153,    0,  -31,  196,  119,  144,  108,  160,
  218,    0,    0,  108,    0,  246,  281,  281,  529,  529,
  529,  529,  529,  529,  283,  283,  199,  199,  199,    0,
  136,  121,    0,  108,    0,    0, -228,    9,    0,    0,
    0,  578,  578,    0,    2,  317,    0,    0,    0,    0,
    0,  443,  456,  121,    0,    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   67,    0,   86,    0,    0,    0,    0,
    0,    0,  148,  148,    0,    0,    0,    0,  120,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  342,    0,    0,    0,    0,  373,  137,    0,
  137,    0,    0,    0,  -26,    0,    0,    0,    0,    0,
    0,    7,    0,    0,    0,    0,    0,    0,  177,  -37,
    0,    0,    0,    0,    0,    0,  143,    0,    0,    0,
   -2,   27,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  410,  226,  240,  404,  415,
  424,  468,  474,  485,  351,  380,   36,   62,   71,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  505,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  -16,    0,  487,  245,  221,   26,  401,  -18,    0,
    0,  532,  722,  727,  115,    0,  269,    0,    0,
};
final static int YYTABLESIZE=886;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         35,
   15,   28,   11,   35,   35,   35,   35,   35,   35,   35,
   36,   79,   96,   18,   36,   36,   36,   36,   36,   36,
   36,   35,   35,   35,   35,   54,   15,   10,  157,  158,
   73,   22,   36,   36,   63,   36,   20,   25,   63,   63,
   63,   63,   63,  159,   63,   79,   32,   43,   12,   13,
   43,   78,   96,   23,   24,   35,   63,   63,  115,   63,
  117,  143,   77,   62,   26,   43,   36,   62,   62,   62,
   62,   62,   49,   62,   96,   30,   49,   49,   49,   49,
   49,   44,   49,   33,   45,   62,   62,   35,   62,   95,
   63,    3,    4,    5,   49,   49,   77,   49,   50,   43,
  148,  161,   50,   50,   50,   50,   50,   52,   50,   96,
   72,   52,   52,   52,   52,   52,   84,   52,   83,   62,
   50,   50,   37,   50,  111,   72,   38,   39,   49,   52,
   52,   46,   52,  109,   45,  162,  163,   40,  107,  105,
   66,  106,  110,  108,   37,   41,   37,   68,    3,    4,
    5,    6,   67,   31,   50,  114,  104,   42,  103,  145,
  109,   43,   96,   52,  125,  107,  105,  109,  106,  110,
  108,  141,  107,  105,   47,  106,  110,  108,   38,   59,
   38,   14,   27,  104,  146,  103,   61,  147,   14,  109,
  104,   14,  103,  142,  107,  105,  109,  106,  110,  108,
   74,  107,  105,   75,  106,  110,  108,   27,    3,    4,
    5,    6,  104,    7,  103,   85,   86,   34,  149,  104,
   34,  103,   35,   35,   35,   35,   35,   35,   35,   35,
   35,   35,   89,  116,   35,   35,   35,   35,   35,   35,
   35,  119,  122,  153,  110,   66,   36,   36,   36,   36,
   36,   36,   68,  123,  109,  124,  144,   67,  152,  107,
  105,   16,  106,  110,  108,  164,   59,   15,   36,   59,
   63,   63,   63,   63,   63,   63,  150,  104,  168,  103,
   60,   90,  109,   60,   59,   29,    0,  107,  105,    0,
  106,  110,  108,    3,    4,    5,    6,    0,   60,   62,
   62,   62,   62,   62,   62,  104,    0,  103,   49,   49,
   49,   49,   49,   49,    0,    0,    0,  109,   59,  109,
    0,    0,  107,  105,  107,  106,  110,  108,  110,  108,
    0,    0,   60,    0,   50,   50,   50,   50,   50,   50,
  104,    0,  103,   52,   52,   52,   52,   52,   52,    0,
    0,    0,    0,  109,    0,    0,    0,    0,  107,  105,
    0,  106,  110,  108,   62,   63,   64,    0,    0,   97,
   98,   99,  100,  101,  102,  165,  104,    0,  103,   65,
   48,   49,   50,   51,    0,    3,    4,    5,    6,   52,
    0,   48,   53,   48,   48,   48,   97,   98,   99,  100,
  101,  102,   36,   97,   98,   99,  100,  101,  102,   48,
   48,    0,   48,    0,    0,    0,    0,    0,    0,    0,
   51,    0,   51,   51,   51,   97,   98,   99,  100,  101,
  102,    0,   97,   98,   99,  100,  101,  102,   51,   51,
    0,   51,   56,   48,   55,    0,    0,   55,    0,    0,
   42,    0,    0,   42,   56,   56,    0,    0,   56,   56,
    0,   56,   55,   55,   57,   55,   36,   57,   42,   62,
   63,   64,   51,   56,   56,    0,   56,    3,    4,    5,
    0,    0,   57,   57,   65,   57,    0,   56,    9,    0,
   97,   98,   99,  100,  101,  102,   55,   19,   59,   59,
    0,    0,   42,    0,    0,    0,    0,   56,   58,   34,
   34,   58,   60,   60,   53,    0,   57,   53,   97,   98,
   99,  100,  101,  102,   81,   54,   58,   58,   54,   58,
    0,   60,   53,   53,    0,   53,    0,    0,    0,    0,
    0,    0,   56,   54,   54,    0,   54,    0,    0,    0,
    0,    0,   56,   56,   93,   99,  100,  101,  102,    0,
   58,    0,   56,   56,   56,  109,   53,  166,    0,    0,
  107,  105,    0,  106,  110,  108,    0,   54,    0,    0,
  167,    0,    0,    0,    0,   82,    0,    0,    0,   97,
   98,   99,  100,  101,  102,    0,    0,    0,    0,    0,
    0,   36,   36,   36,   36,   36,   36,   36,   36,   36,
   36,    0,    0,   36,    0,    0,    0,    0,   82,    0,
    0,    0,    0,   48,   48,   48,   48,   48,   48,   25,
    0,    0,   19,   19,   19,   19,   19,   19,   19,   19,
   19,   19,    0,    0,   19,    0,    0,    0,    0,    0,
    0,    0,   51,   51,   51,   51,   51,   51,    0,   48,
   49,   50,   51,    0,    3,    4,    5,    6,   52,    0,
    0,   53,    0,  154,    0,    0,   55,   55,   55,   55,
   55,   55,    0,    0,    0,    0,    0,   56,   56,   56,
   56,   56,   56,   82,   82,  154,   57,   57,   57,   57,
   57,   57,   48,   49,   50,   51,    0,    3,    4,    5,
    6,   52,    0,    0,   53,   48,   49,   50,   51,    0,
    3,    4,    5,    6,   52,    0,    0,   53,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   58,   58,   58,   58,   58,   58,   53,   53,   53,   53,
   53,   53,    0,    0,    0,    0,    0,   54,   54,   54,
   54,   54,   54,   58,   25,   25,   25,   25,    0,   25,
   25,   25,   25,   25,    0,   58,   25,    0,   76,    0,
   58,    0,   58,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   91,   92,   94,    0,    0,    0,    0,    0,
  112,  113,    0,    0,    0,    0,    0,    0,   58,  120,
    0,    0,  121,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  126,  127,  128,  129,  130,  131,  132,  133,
  134,  135,  136,  137,  138,  139,  140,   48,   49,   50,
   51,    0,    3,    4,    5,    6,   52,    0,    0,   53,
  151,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   58,    0,    0,    0,    0,    0,    0,
  156,    0,    0,   58,   58,    0,    0,    0,    0,    0,
    0,    0,    0,   58,   58,   58,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
   91,   91,  123,   41,   42,   43,   44,   45,   46,   47,
   37,   40,   44,   59,   41,   42,   43,   44,   45,   46,
   47,   59,   60,   61,   62,   42,   91,    2,  257,  258,
   49,   40,   59,   60,   37,   62,   11,  257,   41,   42,
   43,   44,   45,  272,   47,   40,   21,   41,  271,  272,
   44,   46,   44,   40,   40,   93,   59,   60,   77,   62,
   79,   93,   91,   37,   44,   59,   93,   41,   42,   43,
   44,   45,   37,   47,   44,   59,   41,   42,   43,   44,
   45,   41,   47,   41,   44,   59,   60,  125,   62,   59,
   93,  265,  266,  267,   59,   60,   91,   62,   37,   93,
  119,   93,   41,   42,   43,   44,   45,   37,   47,   44,
   44,   41,   42,   43,   44,   45,   59,   47,   61,   93,
   59,   60,   93,   62,   59,   59,  272,  257,   93,   59,
   60,   41,   62,   37,   44,  152,  153,  272,   42,   43,
   33,   45,   46,   47,   59,   59,   61,   40,  265,  266,
  267,  268,   45,  125,   93,   59,   60,  123,   62,   41,
   37,  272,   44,   93,   41,   42,   43,   37,   45,   46,
   47,   41,   42,   43,   93,   45,   46,   47,   59,  123,
   61,  272,  272,   60,   41,   62,  123,   44,   41,   37,
   60,   44,   62,   41,   42,   43,   37,   45,   46,   47,
   40,   42,   43,   40,   45,   46,   47,  272,  265,  266,
  267,  268,   60,  270,   62,   59,   61,   41,   59,   60,
   44,   62,  260,  261,  262,  263,  264,  265,  266,  267,
  268,  269,  272,  272,  272,  273,  274,  275,  276,  277,
  278,   91,  125,  123,   46,   33,  273,  274,  275,  276,
  277,  278,   40,  125,   37,   41,   61,   45,  123,   42,
   43,  125,   45,   46,   47,  264,   41,  125,   24,   44,
  273,  274,  275,  276,  277,  278,   59,   60,  164,   62,
   41,   61,   37,   44,   59,   17,   -1,   42,   43,   -1,
   45,   46,   47,  265,  266,  267,  268,   -1,   59,  273,
  274,  275,  276,  277,  278,   60,   -1,   62,  273,  274,
  275,  276,  277,  278,   -1,   -1,   -1,   37,   93,   37,
   -1,   -1,   42,   43,   42,   45,   46,   47,   46,   47,
   -1,   -1,   93,   -1,  273,  274,  275,  276,  277,  278,
   60,   -1,   62,  273,  274,  275,  276,  277,  278,   -1,
   -1,   -1,   -1,   37,   -1,   -1,   -1,   -1,   42,   43,
   -1,   45,   46,   47,  257,  258,  259,   -1,   -1,  273,
  274,  275,  276,  277,  278,   59,   60,   -1,   62,  272,
  260,  261,  262,  263,   -1,  265,  266,  267,  268,  269,
   -1,   41,  272,   43,   44,   45,  273,  274,  275,  276,
  277,  278,   61,  273,  274,  275,  276,  277,  278,   59,
   60,   -1,   62,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   41,   -1,   43,   44,   45,  273,  274,  275,  276,  277,
  278,   -1,  273,  274,  275,  276,  277,  278,   59,   60,
   -1,   62,   42,   93,   41,   -1,   -1,   44,   -1,   -1,
   41,   -1,   -1,   44,   54,   41,   -1,   -1,   44,   59,
   -1,   61,   59,   60,   41,   62,  125,   44,   59,  257,
  258,  259,   93,   59,   60,   -1,   62,  265,  266,  267,
   -1,   -1,   59,   60,  272,   62,   -1,   87,    2,   -1,
  273,  274,  275,  276,  277,  278,   93,  125,  273,  274,
   -1,   -1,   93,   -1,   -1,   -1,   -1,   93,   41,   23,
   24,   44,  273,  274,   41,   -1,   93,   44,  273,  274,
  275,  276,  277,  278,  125,   41,   59,   60,   44,   62,
   -1,   45,   59,   60,   -1,   62,   -1,   -1,   -1,   -1,
   -1,   -1,  142,   59,   60,   -1,   62,   -1,   -1,   -1,
   -1,   -1,  152,  153,   68,  275,  276,  277,  278,   -1,
   93,   -1,  162,  163,  164,   37,   93,  125,   -1,   -1,
   42,   43,   -1,   45,   46,   47,   -1,   93,   -1,   -1,
  125,   -1,   -1,   -1,   -1,   54,   -1,   -1,   -1,  273,
  274,  275,  276,  277,  278,   -1,   -1,   -1,   -1,   -1,
   -1,  260,  261,  262,  263,  264,  265,  266,  267,  268,
  269,   -1,   -1,  272,   -1,   -1,   -1,   -1,   87,   -1,
   -1,   -1,   -1,  273,  274,  275,  276,  277,  278,  125,
   -1,   -1,  260,  261,  262,  263,  264,  265,  266,  267,
  268,  269,   -1,   -1,  272,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  273,  274,  275,  276,  277,  278,   -1,  260,
  261,  262,  263,   -1,  265,  266,  267,  268,  269,   -1,
   -1,  272,   -1,  142,   -1,   -1,  273,  274,  275,  276,
  277,  278,   -1,   -1,   -1,   -1,   -1,  273,  274,  275,
  276,  277,  278,  162,  163,  164,  273,  274,  275,  276,
  277,  278,  260,  261,  262,  263,   -1,  265,  266,  267,
  268,  269,   -1,   -1,  272,  260,  261,  262,  263,   -1,
  265,  266,  267,  268,  269,   -1,   -1,  272,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  273,  274,  275,  276,  277,  278,  273,  274,  275,  276,
  277,  278,   -1,   -1,   -1,   -1,   -1,  273,  274,  275,
  276,  277,  278,   42,  260,  261,  262,  263,   -1,  265,
  266,  267,  268,  269,   -1,   54,  272,   -1,   52,   -1,
   59,   -1,   61,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   66,   67,   68,   -1,   -1,   -1,   -1,   -1,
   74,   75,   -1,   -1,   -1,   -1,   -1,   -1,   87,   83,
   -1,   -1,   86,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   96,   97,   98,   99,  100,  101,  102,  103,
  104,  105,  106,  107,  108,  109,  110,  260,  261,  262,
  263,   -1,  265,  266,  267,  268,  269,   -1,   -1,  272,
  124,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  142,   -1,   -1,   -1,   -1,   -1,   -1,
  144,   -1,   -1,  152,  153,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  162,  163,  164,
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
"programa : metodos VOID MAIN '(' ')' '{' sentencias '}'",
"metodos : metodos metodo",
"metodos :",
"metodo : tipoSimple ID '(' parametros ')' '{' cuerpoMetodo '}'",
"metodo : VOID ID '(' parametros ')' '{' cuerpoMetodo '}'",
"metodo : declaracionVariable ';'",
"llamadaFuncion : ID '(' expresiones ')'",
"llamadaFuncion : ID '(' argumentos ')'",
"tipoParametro : ID",
"tipoParametro : CTE_ENTERA",
"tipoParametro : CTE_REAL",
"parametros : parametros ',' tipoSimple ID",
"parametros : tipoSimple ID",
"parametros :",
"cuerpoMetodo : sentencias",
"cuerpoMetodo :",
"sentencias : sentencias sentencia",
"sentencias : sentencia",
"sentencia : llamadaVariable",
"sentencia : declaracionVariable ';'",
"sentencia : llamadaVariable '=' expresion ';'",
"sentencia : declaracionVariable '=' expresion ';'",
"sentencia : ID '.' ID '=' expresion ';'",
"sentencia : WHILE '(' expresion ')' '{' sentencias '}'",
"sentencia : IF '(' expresion ')' cuerpoCondicional",
"sentencia : IF '(' expresion ')' cuerpoCondicional ELSE cuerpoCondicional",
"sentencia : WRITE expresiones ';'",
"sentencia : READ expresiones ';'",
"sentencia : llamadaFuncion ';'",
"sentencia : RETURN expresion ';'",
"cuerpoCondicional : '{' sentencias '}'",
"cuerpoCondicional : sentencia",
"argumentos : argumentos ',' tipoParametro",
"argumentos :",
"llamadaVariable : ID llamadaArray",
"llamadaVariable : ID",
"declaracionVariable : tipoSimple identificador",
"declaracionVariable : tipoSimple indices identificador",
"declaracionVariable : STRUCT '{' campos '}' ID",
"campos : campos declaracionVariable ';'",
"campos : declaracionVariable ';'",
"expresiones : expresiones ',' expresion",
"expresiones : expresion",
"expresion : llamadaVariable",
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
"expresion : expresion '.' expresion",
"expresion : '-' expresion",
"expresion : '!' expresion",
"expresion : '(' expresion ')'",
"expresion : '(' tipoSimple ')' expresion",
"expresion : llamadaFuncion",
"llamadaArray : llamadaArray '[' expresiones ']'",
"llamadaArray : '[' expresiones ']'",
"indices : indices '[' CTE_ENTERA ']'",
"indices : '[' CTE_ENTERA ']'",
"identificador : identificador ',' ID",
"identificador : ID",
"tipoSimple : INT",
"tipoSimple : DOUBLE",
"tipoSimple : CHAR",
};

//#line 150 "../../src/sintactico/sintactico.y"

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
//#line 539 "Parser.java"
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
//#line 34 "../../src/sintactico/sintactico.y"
{ this.ast = new Programa();}
break;
//#line 692 "Parser.java"
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
