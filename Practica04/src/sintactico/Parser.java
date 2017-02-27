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
//#line 23 "Parser.java"




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
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    3,    3,    3,    8,   10,   10,   10,
    5,    5,    5,    6,    6,    2,    2,   11,   11,   11,
   11,   11,   11,   11,   11,   11,   11,   11,   11,   11,
   14,   14,   15,   15,   12,   12,    7,    7,    7,   19,
   19,    9,    9,   13,   13,   13,   13,   13,   13,   13,
   13,   13,   13,   13,   13,   13,   13,   13,   13,   13,
   13,   13,   13,   13,   13,   13,   16,   16,   18,   18,
   17,   17,    4,    4,    4,
};
final static short yylen[] = {                            2,
    8,    2,    0,    8,    8,    2,    4,    1,    1,    1,
    4,    2,    0,    1,    0,    2,    1,    1,    2,    4,
    4,    6,    7,    5,    2,    3,    3,    5,    2,    3,
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
   40,    0,   12,    0,    0,    0,   69,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   17,    0,    0,
    0,    0,   45,   46,   47,    0,    0,    0,    0,   66,
    0,   44,    0,    0,    0,    0,    0,   32,   25,    0,
    0,    0,    0,    0,    1,   16,    0,   19,   29,    0,
    0,    0,   11,    0,    0,    0,    0,    0,    0,   27,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   26,    0,    0,    0,   30,
    0,    0,    0,    0,    0,    0,    0,    5,    4,    0,
   64,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   61,    0,    0,   31,   68,
    0,    7,    0,    0,    0,   21,   20,   65,    0,   24,
    0,   28,    9,   10,    8,   33,   67,    0,   22,   23,
};
final static short yydgoto[] = {                          1,
    2,   91,    8,   19,   35,   92,   56,   70,  123,  166,
   58,   72,   73,   79,  124,   84,   16,   17,   21,
};
final static short yysindex[] = {                         0,
    0,  -56,    0,    0,    0,  -97, -217,    0,  -90,  -10,
 -116,   25,   45,   52, -181,   49,  -89,    0,  -64,   38,
   29,   69,  -86,  -86,   26, -161,    0, -111,   49,    0,
 -125,   99,   39,  -80,   18,   82,    0,    0,  111,    0,
    0,  549,    0,   78,  -86,   98,    0,  108,  108,  135,
  177,  121,  108,   -9,  373,  128,  174,    0,  173,  549,
  -30,  549,    0,    0,    0,   10,  108,  108,  213,    0,
  -12,    0,  246,   -6,  108,  108,  549,    0,    0,   97,
  108,  -18,  108,  165,    0,    0,  108,    0,    0,  108,
  549,  132,    0,  134,  108,  216,  216,  225,  124,    0,
  108,  108,  108,  108,  108,  108,  108,  108,  108,  108,
  108,  108,  108,  108,  108,    0,  131,  153,  400,    0,
  -41,  207,   84,   94,  108,  160,  218,    0,    0,  108,
    0,  246,  281,  281,  527,  527,  527,  527,  527,  527,
   90,   90,  216,  216,  216,    0,  146,  121,    0,    0,
  108,    0,  220, -228,  -32,    0,    0,    0,  549,    0,
  317,    0,    0,    0,    0,    0,    0,  443,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   58,    0,  157,    0,    0,    0,    0,
    0,    0,  119,  119,    0,    0,    0,    0,  184,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  342,    0,    0,    0,    0,  456,  161,
    0,  161,    0,    0,    0,  -26,    0,    0,    0,    0,
    0,    0,    7,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  144,  -37,    0,    0,    0,    0,    0,    0,
  162,    0,    0,    0,    0,   -2,   27,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  417,  226,  240,  404,  415,  424,  468,  474,  485,
  351,  380,   36,   62,   71,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  -14,    0,  530,  258,  228,   73,  712,  -35,    0,
  363,  713,  732,  150,    0,    0,  290,    0,    0,
};
final static int YYTABLESIZE=883;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         35,
   15,   28,  101,   35,   35,   35,   35,   35,   35,   35,
   36,  101,   71,   74,   36,   36,   36,   36,   36,   36,
   36,   35,   35,   35,   35,   11,   15,   55,  163,  164,
   83,  101,   36,   36,   63,   36,   82,  101,   63,   63,
   63,   63,   63,  165,   63,  121,  100,   43,   18,   95,
   43,  150,  116,   12,   13,   35,   63,   63,   44,   63,
  167,   45,  119,   62,   22,   43,   36,   62,   62,   62,
   62,   62,   49,   62,   10,   25,   49,   49,   49,   49,
   49,   81,   49,   20,   23,   62,   62,   35,   62,  155,
   63,   24,   26,   32,   49,   49,   30,   49,   50,   43,
   81,   72,   50,   50,   50,   50,   50,   52,   50,   33,
   38,   52,   52,   52,   52,   52,   72,   52,   37,   62,
   50,   50,   46,   50,  152,   45,  114,  101,   49,   52,
   52,  112,   52,  114,  153,  115,  113,  154,  112,  110,
   67,  111,  115,  113,  168,   39,   40,   69,    3,    4,
    5,    6,   68,   31,   50,  120,  109,   41,  108,   13,
  114,   42,   13,   52,  131,  112,  110,  114,  111,  115,
  113,  147,  112,  110,   75,  111,  115,  113,    3,    4,
    5,   14,   27,  109,   34,  108,   88,   34,   87,  114,
  109,   43,  108,  148,  112,  110,  114,  111,  115,  113,
   60,  112,  110,   47,  111,  115,  113,   27,    3,    4,
    5,    6,  109,    7,  108,   37,   76,   37,  156,  109,
   62,  108,   35,   35,   35,   35,   35,   35,   35,   35,
   35,   35,   89,   90,   35,   35,   35,   35,   35,   35,
   35,   93,   38,   77,   38,   67,   36,   36,   36,   36,
   36,   36,   69,  122,  114,  125,  128,   68,  129,  112,
  110,  115,  111,  115,  113,  130,   59,  151,  159,   59,
   63,   63,   63,   63,   63,   63,  157,  109,  162,  108,
   60,   36,  114,   60,   59,   15,   14,  112,  110,   94,
  111,  115,  113,    3,    4,    5,    6,  160,   60,   62,
   62,   62,   62,   62,   62,  109,   29,  108,   49,   49,
   49,   49,   49,   49,    0,    0,    0,  114,   59,    0,
    0,    0,  112,  110,    0,  111,  115,  113,    0,    0,
    0,    0,   60,    0,   50,   50,   50,   50,   50,   50,
  109,    0,  108,   52,   52,   52,   52,   52,   52,    0,
    0,    0,    0,  114,    0,    0,    0,    0,  112,  110,
    0,  111,  115,  113,   63,   64,   65,    0,    0,  102,
  103,  104,  105,  106,  107,  169,  109,    0,  108,   66,
   48,   49,   50,   51,   52,    3,    4,    5,    6,   53,
    0,   48,   54,   48,   48,   48,  102,  103,  104,  105,
  106,  107,   36,  102,  103,  104,  105,  106,  107,   48,
   48,    0,   48,    0,   78,    0,    0,   86,    0,    0,
   51,    0,   51,   51,   51,  102,  103,  104,  105,  106,
  107,    0,  102,  103,  104,  105,  106,  107,   51,   51,
    0,   51,    0,   48,   55,    0,    0,   55,    0,    0,
    0,    0,    0,   86,    0,   56,    0,   42,   56,    0,
   42,    0,   55,   55,   57,   55,   36,   57,    0,   63,
   64,   65,   51,   56,   56,   42,   56,    3,    4,    5,
    0,   86,   57,   57,   66,   57,    0,    0,    0,    0,
  102,  103,  104,  105,  106,  107,   55,   85,   59,   59,
    0,    0,    0,    0,    0,    0,    0,   56,   58,   42,
   78,   58,   60,   60,   53,    0,   57,   53,  102,  103,
  104,  105,  106,  107,  149,   54,   58,   58,   54,   58,
   86,    9,   53,   53,    0,   53,    0,    0,    0,    0,
    0,    0,    0,   54,   54,    0,   54,    0,    0,    0,
    0,    0,   34,   34,    0,  104,  105,  106,  107,    0,
   58,    0,    0,  114,    0,    0,   53,  170,  112,  110,
    0,  111,  115,  113,   61,    0,    0,   54,    0,    0,
   18,    0,    0,    0,    0,    0,    0,    0,    0,  102,
  103,  104,  105,  106,  107,    0,    0,    0,   98,    0,
    0,   36,   36,   36,   36,   36,   36,   36,   36,   36,
   36,    0,    0,   36,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   48,   48,   48,   48,   48,   48,    0,
    0,    0,   48,   49,   50,   51,   52,    3,    4,    5,
    6,   53,    0,    0,   54,    0,    0,    0,    0,    0,
    0,    0,   51,   51,   51,   51,   51,   51,    0,   48,
   49,   50,   51,   52,    3,    4,    5,    6,   53,    0,
    0,   54,    0,    0,    0,    0,   55,   55,   55,   55,
   55,   55,    0,    0,    0,    0,    0,   56,   56,   56,
   56,   56,   56,    0,    0,    0,   57,   57,   57,   57,
   57,   57,   48,   49,   50,   51,   52,    3,    4,    5,
    6,   53,    0,    0,   54,   18,   18,   18,   18,   18,
   18,   18,   18,   18,   18,    0,    0,   18,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   58,   58,   58,   58,   58,   58,   53,   53,   53,   53,
   53,   53,    0,   57,   59,    0,    0,   54,   54,   54,
   54,   54,   54,   57,   59,    0,   57,   59,    0,    0,
    0,   57,   59,   57,   59,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   80,    0,    0,    0,   57,   59,
    0,    0,    0,    0,    0,    0,    0,    0,   96,   97,
   99,    0,   57,   59,    0,    0,  117,  118,   48,   49,
   50,   51,   52,    3,    4,    5,    6,   53,  126,    0,
   54,  127,    0,    0,    0,    0,    0,    0,    0,    0,
   57,   59,  132,  133,  134,  135,  136,  137,  138,  139,
  140,  141,  142,  143,  144,  145,  146,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   57,
   59,  158,    0,    0,    0,    0,    0,    0,    0,    0,
   57,   59,    0,    0,    0,    0,    0,    0,    0,   57,
   59,    0,  161,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
   91,   91,   44,   41,   42,   43,   44,   45,   46,   47,
   37,   44,   48,   49,   41,   42,   43,   44,   45,   46,
   47,   59,   60,   61,   62,  123,   91,   42,  257,  258,
   40,   44,   59,   60,   37,   62,   46,   44,   41,   42,
   43,   44,   45,  272,   47,   81,   59,   41,   59,   40,
   44,   93,   59,  271,  272,   93,   59,   60,   41,   62,
   93,   44,   77,   37,   40,   59,   93,   41,   42,   43,
   44,   45,   37,   47,    2,  257,   41,   42,   43,   44,
   45,   91,   47,   11,   40,   59,   60,  125,   62,  125,
   93,   40,   44,   21,   59,   60,   59,   62,   37,   93,
   91,   44,   41,   42,   43,   44,   45,   37,   47,   41,
  272,   41,   42,   43,   44,   45,   59,   47,   93,   93,
   59,   60,   41,   62,   41,   44,   37,   44,   93,   59,
   60,   42,   62,   37,   41,   46,   47,   44,   42,   43,
   33,   45,   46,   47,  159,  257,  272,   40,  265,  266,
  267,  268,   45,  125,   93,   59,   60,   59,   62,   41,
   37,  123,   44,   93,   41,   42,   43,   37,   45,   46,
   47,   41,   42,   43,   40,   45,   46,   47,  265,  266,
  267,  272,  272,   60,   41,   62,   59,   44,   61,   37,
   60,  272,   62,   41,   42,   43,   37,   45,   46,   47,
  123,   42,   43,   93,   45,   46,   47,  272,  265,  266,
  267,  268,   60,  270,   62,   59,   40,   61,   59,   60,
  123,   62,  260,  261,  262,  263,  264,  265,  266,  267,
  268,  269,   59,   61,  272,  273,  274,  275,  276,  277,
  278,  272,   59,  123,   61,   33,  273,  274,  275,  276,
  277,  278,   40,  272,   37,   91,  125,   45,  125,   42,
   43,   46,   45,   46,   47,   41,   41,   61,  123,   44,
  273,  274,  275,  276,  277,  278,   59,   60,   59,   62,
   41,   24,   37,   44,   59,  125,  125,   42,   43,   62,
   45,   46,   47,  265,  266,  267,  268,  148,   59,  273,
  274,  275,  276,  277,  278,   60,   17,   62,  273,  274,
  275,  276,  277,  278,   -1,   -1,   -1,   37,   93,   -1,
   -1,   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   -1,   -1,   93,   -1,  273,  274,  275,  276,  277,  278,
   60,   -1,   62,  273,  274,  275,  276,  277,  278,   -1,
   -1,   -1,   -1,   37,   -1,   -1,   -1,   -1,   42,   43,
   -1,   45,   46,   47,  257,  258,  259,   -1,   -1,  273,
  274,  275,  276,  277,  278,   59,   60,   -1,   62,  272,
  260,  261,  262,  263,  264,  265,  266,  267,  268,  269,
   -1,   41,  272,   43,   44,   45,  273,  274,  275,  276,
  277,  278,   61,  273,  274,  275,  276,  277,  278,   59,
   60,   -1,   62,   -1,   52,   -1,   -1,   55,   -1,   -1,
   41,   -1,   43,   44,   45,  273,  274,  275,  276,  277,
  278,   -1,  273,  274,  275,  276,  277,  278,   59,   60,
   -1,   62,   -1,   93,   41,   -1,   -1,   44,   -1,   -1,
   -1,   -1,   -1,   91,   -1,   41,   -1,   41,   44,   -1,
   44,   -1,   59,   60,   41,   62,  125,   44,   -1,  257,
  258,  259,   93,   59,   60,   59,   62,  265,  266,  267,
   -1,  119,   59,   60,  272,   62,   -1,   -1,   -1,   -1,
  273,  274,  275,  276,  277,  278,   93,  125,  273,  274,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   93,   41,   93,
  148,   44,  273,  274,   41,   -1,   93,   44,  273,  274,
  275,  276,  277,  278,  125,   41,   59,   60,   44,   62,
  168,    2,   59,   60,   -1,   62,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   59,   60,   -1,   62,   -1,   -1,   -1,
   -1,   -1,   23,   24,   -1,  275,  276,  277,  278,   -1,
   93,   -1,   -1,   37,   -1,   -1,   93,  125,   42,   43,
   -1,   45,   46,   47,   45,   -1,   -1,   93,   -1,   -1,
  125,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  273,
  274,  275,  276,  277,  278,   -1,   -1,   -1,   69,   -1,
   -1,  260,  261,  262,  263,  264,  265,  266,  267,  268,
  269,   -1,   -1,  272,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  273,  274,  275,  276,  277,  278,   -1,
   -1,   -1,  260,  261,  262,  263,  264,  265,  266,  267,
  268,  269,   -1,   -1,  272,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  273,  274,  275,  276,  277,  278,   -1,  260,
  261,  262,  263,  264,  265,  266,  267,  268,  269,   -1,
   -1,  272,   -1,   -1,   -1,   -1,  273,  274,  275,  276,
  277,  278,   -1,   -1,   -1,   -1,   -1,  273,  274,  275,
  276,  277,  278,   -1,   -1,   -1,  273,  274,  275,  276,
  277,  278,  260,  261,  262,  263,  264,  265,  266,  267,
  268,  269,   -1,   -1,  272,  260,  261,  262,  263,  264,
  265,  266,  267,  268,  269,   -1,   -1,  272,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  273,  274,  275,  276,  277,  278,  273,  274,  275,  276,
  277,  278,   -1,   42,   42,   -1,   -1,  273,  274,  275,
  276,  277,  278,   52,   52,   -1,   55,   55,   -1,   -1,
   -1,   60,   60,   62,   62,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   53,   -1,   -1,   -1,   77,   77,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   67,   68,
   69,   -1,   91,   91,   -1,   -1,   75,   76,  260,  261,
  262,  263,  264,  265,  266,  267,  268,  269,   87,   -1,
  272,   90,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  119,  119,  101,  102,  103,  104,  105,  106,  107,  108,
  109,  110,  111,  112,  113,  114,  115,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  148,
  148,  130,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  159,  159,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  168,
  168,   -1,  151,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=280;
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
"DISTINTO","IGUALDAD","MENOS_UNARIO","NEGACION",
};
final static String yyrule[] = {
"$accept : programa",
"programa : metodos VOID MAIN '(' ')' '{' sentencias '}'",
"metodos : metodos metodo",
"metodos :",
"metodo : tipoSimple ID '(' parametro ')' '{' cuerpoMetodo '}'",
"metodo : VOID ID '(' parametro ')' '{' cuerpoMetodo '}'",
"metodo : declaracionVariable ';'",
"llamadaFuncion : ID '(' expresiones ')'",
"tipoParametro : ID",
"tipoParametro : CTE_ENTERA",
"tipoParametro : CTE_REAL",
"parametro : parametro ',' tipoSimple ID",
"parametro : tipoSimple ID",
"parametro :",
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
"sentencia : IF '(' expresion ')' cuerpoIf",
"sentencia : ELSE cuerpoIf",
"sentencia : WRITE expresiones ';'",
"sentencia : READ expresiones ';'",
"sentencia : ID '(' parametroLlamadaMetodo ')' ';'",
"sentencia : llamadaFuncion ';'",
"sentencia : RETURN expresion ';'",
"cuerpoIf : '{' sentencias '}'",
"cuerpoIf : sentencia",
"parametroLlamadaMetodo : parametroLlamadaMetodo ',' tipoParametro",
"parametroLlamadaMetodo :",
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

//#line 147 "../../src/sintactico/sintactico.y"

// * Código Java
// * Se crea una clase "Parser", lo que aquí ubiquemos será:
//	- Atributos, si son variables
//	- Métodos, si son funciones
//   de la clase "Parser"

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
//#line 533 "Parser.java"
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
