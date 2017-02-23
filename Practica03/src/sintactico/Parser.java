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
    0,    1,    1,    3,    3,    8,   10,   10,   10,    5,
    5,    5,    6,    6,    2,    2,   11,   11,   11,   11,
   11,   11,   11,   11,   11,   11,   11,   11,   11,   11,
   11,   14,   14,   12,   12,    7,    7,    7,   18,   18,
    9,    9,   13,   13,   13,   13,   13,   13,   13,   13,
   13,   13,   13,   13,   13,   13,   13,   13,   13,   13,
   13,   13,   13,   13,   13,   15,   15,   17,   17,   16,
   16,    4,    4,    4,    4,
};
final static short yylen[] = {                            2,
    8,    2,    0,    8,    2,    4,    1,    1,    1,    4,
    2,    0,    1,    0,    2,    1,    1,    2,    4,    4,
    6,    7,    7,    5,    4,    2,    3,    3,    5,    2,
    3,    3,    0,    2,    1,    2,    3,    5,    3,    2,
    3,    1,    1,    1,    1,    1,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    2,    2,    3,    4,    1,    4,    3,    4,    3,    3,
    1,    1,    1,    1,    1,
};
final static short yydefred[] = {                         3,
    0,    0,   73,   74,   75,    0,    0,    2,    0,    0,
    0,    0,    0,    0,    0,    0,    5,   72,    0,    0,
    0,    0,    0,    0,    0,   71,    0,    0,   40,    0,
    0,    0,    0,    0,   69,   70,    0,   38,   39,    0,
   11,    0,    0,   68,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   16,    0,    0,    0,   44,   45,
   46,    0,    0,    0,    0,   65,    0,   43,    0,    0,
    0,    0,    0,   26,    0,    0,    0,    0,    0,    1,
   15,    0,   18,   30,    0,    0,    0,   10,    0,    0,
    0,    0,    0,   28,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   27,
    0,    0,    0,   31,    0,    0,    0,    0,    0,    0,
    0,    4,    0,   63,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   60,    0,
    0,   25,   67,    0,    6,    0,    0,    0,   20,   19,
   64,    0,    0,   24,    0,   29,    8,    9,    7,   32,
   66,    0,    0,   21,   22,   23,
};
final static short yydgoto[] = {                          1,
    2,   52,    8,   19,   34,   87,   53,   66,  117,  160,
   55,   68,   69,  118,   79,   15,   16,   21,
};
final static short yysindex[] = {                         0,
    0, -116,    0,    0,    0,  -97, -233,    0,  -90,  -15,
  149,    9,   15, -198,   17,  -89,    0,    0,  -64,   26,
   85,   52,  -78,   30, -170,    0, -147,   17,    0, -153,
   69,   13, -135,   53,    0,    0,   82,    0,    0,  614,
    0,   23,  -78,    0,  108,  108,  122,  145,  121,  108,
   -9,  307,   86,  142,    0,  143,  614,  -60,    0,    0,
    0,  -28,  108,  108,  213,    0,  -30,    0,  246,  -12,
  108,  108,  614,    0,   97,  108,  -56,  108,  126,    0,
    0,  108,    0,    0,  108,  614,  109,    0,  108,  196,
  196,  215,  124,    0,  108,  108,  108,  108,  108,  108,
  108,  108,  108,  108,  108,  108,  108,  108,  108,    0,
  131,  153,  400,    0,  -41,  193,   91,  119,  108,  160,
  218,    0,  108,    0,  246,  281,  281,  494,  494,  494,
  494,  494,  494,  220,  220,  196,  196,  196,    0,  136,
  375,    0,    0,  108,    0,  209, -182,  -31,    0,    0,
    0,  614,  614,    0,  317,    0,    0,    0,    0,    0,
    0,  435,  460,    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,  -63,    0,    0,    0,
    0,    0,    6,    0,  120,    0,    0,    0,    0,    0,
    0,    0,  170,    0,    0,    0,    0,  184,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  342,    0,    0,    0,    0,  521,  144,    0,    0,    0,
    0,  -26,    0,    0,    0,    0,    0,    0,    7,    0,
    0,    0,    0,    0,    0,    0,    0,  177,  -37,    0,
    0,    0,    0,    0,    0,  161,    0,    0,    0,   -2,
   27,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  238,  240,  275,  404,  415,  440,
  468,  485,  491,  351,  380,   36,   62,   71,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  -27,    0,  115,    0,    0,   90,  752,    8,    0,
  402,  754,  750,    0,    0,  254,    0,    0,
};
final static int YYTABLESIZE=917;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         34,
   14,   27,   95,   34,   34,   34,   34,   34,   34,   34,
   35,   89,   95,   95,   35,   35,   35,   35,   35,   35,
   35,   34,   34,   34,   34,   11,   14,   72,   94,   86,
   78,   95,   35,   35,   62,   35,   77,   12,   62,   62,
   62,   62,   62,   17,   62,  113,  110,   42,   22,   71,
   42,  143,   67,   70,   23,   34,   62,   62,   24,   62,
   25,  161,   76,   61,   71,   42,   35,   61,   61,   61,
   61,   61,   48,   61,  157,  158,   48,   48,   48,   48,
   48,   76,   48,  115,   29,   61,   61,   34,   61,  159,
   62,   10,   32,   42,   48,   48,   43,   48,   49,   42,
   20,   36,   49,   49,   49,   49,   49,   51,   49,   37,
   31,   51,   51,   51,   51,   51,    9,   51,   38,   61,
   49,   49,   35,   49,  162,  163,  148,   39,   48,   51,
   51,  145,   51,  108,   95,   40,   41,   33,  106,  104,
   63,  105,  109,  107,   83,   57,   82,   65,    3,    4,
    5,    6,   64,    7,   49,  114,  103,   58,  102,  146,
  108,   71,  147,   51,  124,  106,  104,  108,  105,  109,
  107,  140,  106,  104,   44,  105,  109,  107,   36,   92,
   36,   13,   26,  103,   72,  102,    3,    4,    5,  108,
  103,   18,  102,  141,  106,  104,  108,  105,  109,  107,
   84,  106,  104,   85,  105,  109,  107,   26,   72,   30,
   12,   88,  103,   12,  102,  116,  119,   33,  149,  103,
   33,  102,   34,   34,   34,   34,   34,   34,   34,   34,
   34,   34,   34,  122,   34,   34,   34,   34,   34,   34,
   34,  109,   37,   73,   37,   63,   35,   35,   35,   35,
   35,   35,   65,  144,  108,  123,  108,   64,  152,  106,
  104,  106,  105,  109,  107,  109,  107,  156,   14,   28,
   62,   62,   62,   62,   62,   62,  150,  103,   41,  102,
   58,   41,  108,   58,    0,   13,    0,  106,  104,    0,
  105,  109,  107,    0,    0,    0,   41,    0,   58,   61,
   61,   61,   61,   61,   61,  103,    0,  102,   48,   48,
   48,   48,   48,   48,    0,   59,    0,  108,   59,    0,
    0,    0,  106,  104,    0,  105,  109,  107,    0,    0,
   41,    0,   58,   59,   49,   49,   49,   49,   49,   49,
  103,    0,  102,   51,   51,   51,   51,   51,   51,    3,
    4,    5,    6,  108,   18,    0,    0,    0,  106,  104,
    0,  105,  109,  107,   59,   60,   61,   59,    0,   96,
   97,   98,   99,  100,  101,  164,  103,    0,  102,   62,
   45,   46,   47,   48,   49,    3,    4,    5,    6,   50,
   18,   47,   51,   47,   47,   47,   96,   97,   98,   99,
  100,  101,   35,   96,   97,   98,   99,  100,  101,   47,
   47,    0,   47,    3,    4,    5,    6,    0,   18,    0,
   50,    0,   50,   50,   50,   96,   97,   98,   99,  100,
  101,   80,   96,   97,   98,   99,  100,  101,   50,   50,
    0,   50,    0,   47,   54,    0,    0,   54,    0,    0,
   74,    0,    0,   81,    0,   55,    0,    0,   55,    0,
    0,    0,   54,   54,    0,   54,   35,    0,    0,   59,
   60,   61,   50,   55,   55,    0,   55,    3,    4,    5,
   56,    0,   18,   56,   62,    0,    0,   81,    0,    0,
   96,   97,   98,   99,  100,  101,   54,  153,   56,   56,
    0,   56,    0,    0,    0,    0,    0,   55,   57,    0,
    0,   57,   58,   58,   81,    0,    0,    0,   96,   97,
   98,   99,  100,  101,  142,   52,   57,   57,   52,   57,
  108,   53,   56,    0,   53,  106,  104,    0,  105,  109,
  107,    0,  154,   52,   52,    0,   52,   59,   59,   53,
   53,    0,   53,    0,    0,   98,   99,  100,  101,  165,
   57,    0,    0,   81,   81,    0,   45,   46,   47,   48,
   49,    3,    4,    5,    6,   50,   18,   52,   51,    0,
    0,    0,    0,   53,  166,    0,    0,    0,    0,   96,
   97,   98,   99,  100,  101,    0,    0,    0,    0,    0,
    0,   35,   35,   35,   35,   35,   35,   35,   35,   35,
   35,   35,    0,   35,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   47,   47,   47,   47,   47,   47,    0,
    0,    0,    0,    0,   45,   46,   47,   48,   49,    3,
    4,    5,    6,   50,   18,   17,   51,    0,    0,    0,
    0,    0,   50,   50,   50,   50,   50,   50,    0,   45,
   46,   47,   48,   49,    3,    4,    5,    6,   50,   18,
    0,   51,    0,    0,    0,    0,   54,   54,   54,   54,
   54,   54,    0,    0,    0,    0,    0,   55,   55,   55,
   55,   55,   55,    0,   45,   46,   47,   48,   49,    3,
    4,    5,    6,   50,   18,    0,   51,    0,    0,    0,
    0,    0,   56,   56,   56,   56,   56,   56,    0,   45,
   46,   47,   48,   49,    3,    4,    5,    6,   50,   18,
    0,   51,    0,    0,    0,    0,    0,    0,    0,    0,
   57,   57,   57,   57,   57,   57,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   52,   52,   52,
   52,   52,   52,   53,   53,   53,   53,   53,   53,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   17,   17,   17,   17,   17,   17,   17,   17,   17,   17,
   17,   54,   17,   56,    0,    0,    0,    0,    0,   75,
   54,    0,   56,   54,    0,   56,    0,    0,   54,    0,
   56,    0,   90,   91,   93,    0,    0,    0,    0,    0,
  111,  112,    0,    0,   54,    0,   56,    0,    0,    0,
    0,  120,    0,    0,  121,    0,    0,   54,    0,   56,
    0,    0,    0,    0,  125,  126,  127,  128,  129,  130,
  131,  132,  133,  134,  135,  136,  137,  138,  139,    0,
    0,    0,    0,    0,   54,    0,   56,    0,    0,    0,
    0,    0,  151,   45,   46,   47,   48,   49,    3,    4,
    5,    6,   50,   18,    0,   51,    0,    0,    0,    0,
    0,    0,   54,  155,   56,    0,    0,    0,    0,    0,
    0,    0,    0,   54,   54,   56,   56,    0,    0,    0,
    0,    0,    0,   54,   54,   56,   56,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
   91,   91,   44,   41,   42,   43,   44,   45,   46,   47,
   37,   40,   44,   44,   41,   42,   43,   44,   45,   46,
   47,   59,   60,   61,   62,  123,   91,   91,   59,   57,
   40,   44,   59,   60,   37,   62,   46,  271,   41,   42,
   43,   44,   45,   59,   47,   73,   59,   41,   40,   44,
   44,   93,   45,   46,   40,   93,   59,   60,  257,   62,
   44,   93,   91,   37,   59,   59,   93,   41,   42,   43,
   44,   45,   37,   47,  257,  258,   41,   42,   43,   44,
   45,   91,   47,   76,   59,   59,   60,  125,   62,  272,
   93,    2,   41,   41,   59,   60,   44,   62,   37,   93,
   11,  272,   41,   42,   43,   44,   45,   37,   47,  257,
   21,   41,   42,   43,   44,   45,    2,   47,  272,   93,
   59,   60,   93,   62,  152,  153,  119,   59,   93,   59,
   60,   41,   62,   37,   44,  123,  272,   23,   42,   43,
   33,   45,   46,   47,   59,  123,   61,   40,  265,  266,
  267,  268,   45,  270,   93,   59,   60,   43,   62,   41,
   37,   40,   44,   93,   41,   42,   43,   37,   45,   46,
   47,   41,   42,   43,   93,   45,   46,   47,   59,   65,
   61,  272,  272,   60,   40,   62,  265,  266,  267,   37,
   60,  270,   62,   41,   42,   43,   37,   45,   46,   47,
   59,   42,   43,   61,   45,   46,   47,  272,  272,  125,
   41,  272,   60,   44,   62,  272,   91,   41,   59,   60,
   44,   62,  260,  261,  262,  263,  264,  265,  266,  267,
  268,  269,  270,  125,  272,  273,  274,  275,  276,  277,
  278,   46,   59,  123,   61,   33,  273,  274,  275,  276,
  277,  278,   40,   61,   37,   41,   37,   45,  123,   42,
   43,   42,   45,   46,   47,   46,   47,   59,  125,   16,
  273,  274,  275,  276,  277,  278,   59,   60,   41,   62,
   41,   44,   37,   44,   -1,  125,   -1,   42,   43,   -1,
   45,   46,   47,   -1,   -1,   -1,   59,   -1,   59,  273,
  274,  275,  276,  277,  278,   60,   -1,   62,  273,  274,
  275,  276,  277,  278,   -1,   41,   -1,   37,   44,   -1,
   -1,   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   93,   -1,   93,   59,  273,  274,  275,  276,  277,  278,
   60,   -1,   62,  273,  274,  275,  276,  277,  278,  265,
  266,  267,  268,   37,  270,   -1,   -1,   -1,   42,   43,
   -1,   45,   46,   47,  257,  258,  259,   93,   -1,  273,
  274,  275,  276,  277,  278,   59,   60,   -1,   62,  272,
  260,  261,  262,  263,  264,  265,  266,  267,  268,  269,
  270,   41,  272,   43,   44,   45,  273,  274,  275,  276,
  277,  278,   61,  273,  274,  275,  276,  277,  278,   59,
   60,   -1,   62,  265,  266,  267,  268,   -1,  270,   -1,
   41,   -1,   43,   44,   45,  273,  274,  275,  276,  277,
  278,  125,  273,  274,  275,  276,  277,  278,   59,   60,
   -1,   62,   -1,   93,   41,   -1,   -1,   44,   -1,   -1,
   49,   -1,   -1,   52,   -1,   41,   -1,   -1,   44,   -1,
   -1,   -1,   59,   60,   -1,   62,  125,   -1,   -1,  257,
  258,  259,   93,   59,   60,   -1,   62,  265,  266,  267,
   41,   -1,  270,   44,  272,   -1,   -1,   86,   -1,   -1,
  273,  274,  275,  276,  277,  278,   93,  123,   59,   60,
   -1,   62,   -1,   -1,   -1,   -1,   -1,   93,   41,   -1,
   -1,   44,  273,  274,  113,   -1,   -1,   -1,  273,  274,
  275,  276,  277,  278,  125,   41,   59,   60,   44,   62,
   37,   41,   93,   -1,   44,   42,   43,   -1,   45,   46,
   47,   -1,  141,   59,   60,   -1,   62,  273,  274,   59,
   60,   -1,   62,   -1,   -1,  275,  276,  277,  278,  125,
   93,   -1,   -1,  162,  163,   -1,  260,  261,  262,  263,
  264,  265,  266,  267,  268,  269,  270,   93,  272,   -1,
   -1,   -1,   -1,   93,  125,   -1,   -1,   -1,   -1,  273,
  274,  275,  276,  277,  278,   -1,   -1,   -1,   -1,   -1,
   -1,  260,  261,  262,  263,  264,  265,  266,  267,  268,
  269,  270,   -1,  272,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  273,  274,  275,  276,  277,  278,   -1,
   -1,   -1,   -1,   -1,  260,  261,  262,  263,  264,  265,
  266,  267,  268,  269,  270,  125,  272,   -1,   -1,   -1,
   -1,   -1,  273,  274,  275,  276,  277,  278,   -1,  260,
  261,  262,  263,  264,  265,  266,  267,  268,  269,  270,
   -1,  272,   -1,   -1,   -1,   -1,  273,  274,  275,  276,
  277,  278,   -1,   -1,   -1,   -1,   -1,  273,  274,  275,
  276,  277,  278,   -1,  260,  261,  262,  263,  264,  265,
  266,  267,  268,  269,  270,   -1,  272,   -1,   -1,   -1,
   -1,   -1,  273,  274,  275,  276,  277,  278,   -1,  260,
  261,  262,  263,  264,  265,  266,  267,  268,  269,  270,
   -1,  272,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  273,  274,  275,  276,  277,  278,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  273,  274,  275,
  276,  277,  278,  273,  274,  275,  276,  277,  278,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  260,  261,  262,  263,  264,  265,  266,  267,  268,  269,
  270,   40,  272,   40,   -1,   -1,   -1,   -1,   -1,   50,
   49,   -1,   49,   52,   -1,   52,   -1,   -1,   57,   -1,
   57,   -1,   63,   64,   65,   -1,   -1,   -1,   -1,   -1,
   71,   72,   -1,   -1,   73,   -1,   73,   -1,   -1,   -1,
   -1,   82,   -1,   -1,   85,   -1,   -1,   86,   -1,   86,
   -1,   -1,   -1,   -1,   95,   96,   97,   98,   99,  100,
  101,  102,  103,  104,  105,  106,  107,  108,  109,   -1,
   -1,   -1,   -1,   -1,  113,   -1,  113,   -1,   -1,   -1,
   -1,   -1,  123,  260,  261,  262,  263,  264,  265,  266,
  267,  268,  269,  270,   -1,  272,   -1,   -1,   -1,   -1,
   -1,   -1,  141,  144,  141,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  152,  153,  152,  153,   -1,   -1,   -1,
   -1,   -1,   -1,  162,  163,  162,  163,
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
"sentencia : IF '(' expresion ')' '{' sentencias '}'",
"sentencia : IF '(' expresion ')' sentencia",
"sentencia : ELSE '{' sentencias '}'",
"sentencia : ELSE sentencia",
"sentencia : WRITE expresiones ';'",
"sentencia : READ expresiones ';'",
"sentencia : ID '(' parametroLlamadaMetodo ')' ';'",
"sentencia : llamadaFuncion ';'",
"sentencia : RETURN expresion ';'",
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
"tipoSimple : VOID",
"tipoSimple : INT",
"tipoSimple : DOUBLE",
"tipoSimple : CHAR",
};

//#line 145 "../../src/sintactico/sintactico.y"

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
