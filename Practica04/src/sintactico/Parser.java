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
   11,   11,   11,   11,   11,   11,   11,   11,   11,   14,
   14,   15,   15,   12,   12,    7,    7,    7,   19,   19,
    9,    9,   13,   13,   13,   13,   13,   13,   13,   13,
   13,   13,   13,   13,   13,   13,   13,   13,   13,   13,
   13,   13,   13,   13,   13,   16,   16,   18,   18,   17,
   17,    4,    4,    4,
};
final static short yylen[] = {                            2,
    8,    2,    0,    8,    8,    2,    4,    1,    1,    1,
    4,    2,    0,    1,    0,    2,    1,    1,    2,    4,
    4,    6,    7,    7,    3,    3,    5,    2,    3,    3,
    1,    3,    0,    2,    1,    2,    3,    5,    3,    2,
    3,    1,    1,    1,    1,    1,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    2,    2,    3,    4,    1,    4,    3,    4,    3,    3,
    1,    1,    1,    1,
};
final static short yydefred[] = {                         3,
    0,    0,   72,   73,   74,    0,    0,    2,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    6,    0,    0,
    0,    0,    0,    0,    0,    0,   71,    0,    0,   40,
    0,    0,    0,    0,    0,    0,   69,   70,    0,   38,
   39,    0,   12,    0,    0,    0,   68,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   17,    0,    0,    0,
    0,   44,   45,   46,    0,    0,    0,    0,   65,    0,
   43,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    1,   16,    0,   19,   28,    0,    0,    0,   11,    0,
    0,    0,    0,    0,    0,   26,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   25,    0,    0,   29,    0,    0,    0,    0,    0,
    0,    0,    5,    4,    0,   63,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   60,    0,    0,   67,    0,    7,    0,    0,    0,   21,
   20,   64,    0,    0,   31,    0,    0,   27,    9,   10,
    8,   32,   66,    0,    0,    0,   22,   23,   30,   24,
};
final static short yydgoto[] = {                          1,
    2,   87,    8,   19,   35,   88,   55,   69,  118,  162,
   57,   71,   72,  156,  119,   80,   16,   17,   21,
};
final static short yysindex[] = {                         0,
    0,  -56,    0,    0,    0, -120, -217,    0,  -90,  -33,
 -130,   -8,    9,   44, -147,   67,  -89,    0,  -64,   60,
   29,   82, -173, -173,   34, -144,    0, -112,   67,    0,
 -121,   93,   35, -110,   41,  105,    0,    0,   87,    0,
    0,  514,    0,   52, -173,   64,    0,  108,  108,  161,
  164,  108,    6,  400,    4,  158,    0,  172,  514,  -51,
  514,    0,    0,    0,   10,  108,  108,  213,    0,  -15,
    0,  246,   -6,  108,  108,   97,  108,  -38,  108,  151,
    0,    0,  108,    0,    0,  108,  514,  118,    0,  129,
  108,  199,  199,  215,  124,    0,  108,  108,  108,  108,
  108,  108,  108,  108,  108,  108,  108,  108,  108,  108,
  108,    0,  131,  153,    0,  -32,  196,  106,  119,  108,
  160,  218,    0,    0,  108,    0,  246,  281,  281,  527,
  527,  527,  527,  527,  527,  283,  283,  199,  199,  199,
    0,  136,  121,    0,  108,    0,  203, -182,  -31,    0,
    0,    0,  514,  514,    0,    2,  317,    0,    0,    0,
    0,    0,    0,  443,  456,  121,    0,    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   73,    0,  120,    0,    0,    0,    0,
    0,    0,  144,  144,    0,    0,    0,    0,  157,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  342,    0,    0,    0,    0,  373,  143,    0,
  143,    0,    0,    0,  -26,    0,    0,    0,    0,    0,
    0,    7,    0,    0,    0,    0,    0,    0,  148,  -37,
    0,    0,    0,    0,    0,    0,  154,    0,    0,    0,
    0,   -2,   27,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  410,  226,  240,  404,
  415,  424,  468,  474,  485,  351,  380,   36,   62,   71,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  -28,    0,  575,  245,  221,   26,  678,  -18,    0,
  389,  712,  784,  132,    0,    0,  269,    0,    0,
};
final static int YYTABLESIZE=929;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         34,
   15,   28,   11,   34,   34,   34,   34,   34,   34,   34,
   35,   97,   97,   54,   35,   35,   35,   35,   35,   35,
   35,   34,   34,   34,   34,   18,   15,   10,   97,   70,
   73,   22,   35,   35,   62,   35,   20,   97,   62,   62,
   62,   62,   62,   96,   62,   79,   32,   42,   23,   91,
   42,   78,  112,   12,   13,   34,   62,   62,  116,   62,
  144,  163,   84,   61,   83,   42,   35,   61,   61,   61,
   61,   61,   48,   61,  159,  160,   48,   48,   48,   48,
   48,   44,   48,   24,   45,   61,   61,   34,   61,  161,
   62,    3,    4,    5,   48,   48,   77,   48,   49,   42,
   77,  149,   49,   49,   49,   49,   49,   51,   49,   25,
   26,   51,   51,   51,   51,   51,   71,   51,   30,   61,
   49,   49,   33,   49,  164,  165,   37,   38,   48,   51,
   51,   71,   51,  110,    3,    4,    5,    6,  108,  106,
   66,  107,  111,  109,   39,   46,  146,   68,   45,   97,
   40,   41,   67,   31,   49,  115,  105,   42,  104,  147,
  110,   43,  148,   51,  126,  108,  106,  110,  107,  111,
  109,  142,  108,  106,   59,  107,  111,  109,   36,   47,
   36,   14,   27,  105,   13,  104,   61,   13,   33,  110,
  105,   33,  104,  143,  108,  106,  110,  107,  111,  109,
   74,  108,  106,   75,  107,  111,  109,   27,    3,    4,
    5,    6,  105,    7,  104,   37,   85,   37,  150,  105,
   89,  104,   34,   34,   34,   34,   34,   34,   34,   34,
   34,   34,   86,  117,   34,   34,   34,   34,   34,   34,
   34,  120,  123,  154,  111,   66,   35,   35,   35,   35,
   35,   35,   68,  124,  110,  125,  145,   67,  153,  108,
  106,  158,  107,  111,  109,  166,   58,   15,   36,   58,
   62,   62,   62,   62,   62,   62,  151,  105,   14,  104,
   59,   90,  110,   59,   58,   29,    0,  108,  106,    0,
  107,  111,  109,    3,    4,    5,    6,  170,   59,   61,
   61,   61,   61,   61,   61,  105,    0,  104,   48,   48,
   48,   48,   48,   48,    0,    0,    0,  110,   58,  110,
    0,    0,  108,  106,  108,  107,  111,  109,  111,  109,
    0,    0,   59,    0,   49,   49,   49,   49,   49,   49,
  105,    0,  104,   51,   51,   51,   51,   51,   51,    0,
    0,    0,    0,  110,    0,    0,    0,    0,  108,  106,
    0,  107,  111,  109,   62,   63,   64,    0,    0,   98,
   99,  100,  101,  102,  103,  167,  105,    0,  104,   65,
   48,   49,   50,   51,    0,    3,    4,    5,    6,   52,
    0,   47,   53,   47,   47,   47,   98,   99,  100,  101,
  102,  103,   35,   98,   99,  100,  101,  102,  103,   47,
   47,    0,   47,    0,    0,    0,    0,    0,    0,    0,
   50,    0,   50,   50,   50,   98,   99,  100,  101,  102,
  103,    0,   98,   99,  100,  101,  102,  103,   50,   50,
    0,   50,   82,   47,   54,    0,    0,   54,    0,    0,
   41,    0,    0,   41,    0,   55,    0,    0,   55,    0,
    0,    0,   54,   54,   56,   54,   35,   56,   41,   62,
   63,   64,   50,   55,   55,   82,   55,    3,    4,    5,
    0,    0,   56,   56,   65,   56,    0,    0,    0,    0,
   98,   99,  100,  101,  102,  103,   54,   18,   58,   58,
    0,    0,   41,    0,    0,    0,    0,   55,   57,    0,
    0,   57,   59,   59,   52,    0,   56,   52,   98,   99,
  100,  101,  102,  103,   81,   53,   57,   57,   53,   57,
    0,  155,   52,   52,    0,   52,    0,    0,    0,    0,
    0,    0,    0,   53,   53,    0,   53,    0,    0,    0,
    0,    0,   82,   82,  155,  100,  101,  102,  103,    0,
   57,    0,    0,  110,    0,    0,   52,  168,  108,  106,
    0,  107,  111,  109,    0,    0,    9,   53,    0,    0,
  169,    0,    0,    0,    0,    0,    0,    0,    0,   98,
   99,  100,  101,  102,  103,    0,    0,   34,   34,    0,
    0,   35,   35,   35,   35,   35,   35,   35,   35,   35,
   35,    0,    0,   35,    0,    0,    0,    0,    0,   60,
    0,    0,    0,   47,   47,   47,   47,   47,   47,    0,
    0,    0,   18,   18,   18,   18,   18,   18,   18,   18,
   18,   18,   94,    0,   18,    0,    0,    0,    0,    0,
    0,    0,   50,   50,   50,   50,   50,   50,    0,   48,
   49,   50,   51,    0,    3,    4,    5,    6,   52,    0,
    0,   53,    0,    0,    0,    0,   54,   54,   54,   54,
   54,   54,    0,    0,    0,    0,    0,   55,   55,   55,
   55,   55,   55,    0,    0,    0,   56,   56,   56,   56,
   56,   56,   48,   49,   50,   51,    0,    3,    4,    5,
    6,   52,    0,    0,   53,   48,   49,   50,   51,   56,
    3,    4,    5,    6,   52,    0,    0,   53,    0,    0,
    0,   56,    0,    0,    0,    0,   56,    0,   56,    0,
   57,   57,   57,   57,   57,   57,   52,   52,   52,   52,
   52,   52,    0,   58,    0,    0,    0,   53,   53,   53,
   53,   53,   53,    0,   56,   58,    0,    0,    0,    0,
   58,    0,   58,   48,   49,   50,   51,    0,    3,    4,
    5,    6,   52,    0,    0,   53,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   58,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   56,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   56,   56,    0,    0,    0,   76,    0,    0,    0,    0,
    0,   56,   56,   56,    0,    0,    0,    0,    0,   92,
   93,   95,    0,    0,   58,    0,    0,  113,  114,    0,
    0,    0,    0,    0,   58,   58,  121,    0,    0,  122,
    0,    0,    0,    0,    0,   58,   58,   58,    0,    0,
  127,  128,  129,  130,  131,  132,  133,  134,  135,  136,
  137,  138,  139,  140,  141,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  152,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  157,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
   91,   91,  123,   41,   42,   43,   44,   45,   46,   47,
   37,   44,   44,   42,   41,   42,   43,   44,   45,   46,
   47,   59,   60,   61,   62,   59,   91,    2,   44,   48,
   49,   40,   59,   60,   37,   62,   11,   44,   41,   42,
   43,   44,   45,   59,   47,   40,   21,   41,   40,   40,
   44,   46,   59,  271,  272,   93,   59,   60,   77,   62,
   93,   93,   59,   37,   61,   59,   93,   41,   42,   43,
   44,   45,   37,   47,  257,  258,   41,   42,   43,   44,
   45,   41,   47,   40,   44,   59,   60,  125,   62,  272,
   93,  265,  266,  267,   59,   60,   91,   62,   37,   93,
   91,  120,   41,   42,   43,   44,   45,   37,   47,  257,
   44,   41,   42,   43,   44,   45,   44,   47,   59,   93,
   59,   60,   41,   62,  153,  154,   93,  272,   93,   59,
   60,   59,   62,   37,  265,  266,  267,  268,   42,   43,
   33,   45,   46,   47,  257,   41,   41,   40,   44,   44,
  272,   59,   45,  125,   93,   59,   60,  123,   62,   41,
   37,  272,   44,   93,   41,   42,   43,   37,   45,   46,
   47,   41,   42,   43,  123,   45,   46,   47,   59,   93,
   61,  272,  272,   60,   41,   62,  123,   44,   41,   37,
   60,   44,   62,   41,   42,   43,   37,   45,   46,   47,
   40,   42,   43,   40,   45,   46,   47,  272,  265,  266,
  267,  268,   60,  270,   62,   59,   59,   61,   59,   60,
  272,   62,  260,  261,  262,  263,  264,  265,  266,  267,
  268,  269,   61,  272,  272,  273,  274,  275,  276,  277,
  278,   91,  125,  123,   46,   33,  273,  274,  275,  276,
  277,  278,   40,  125,   37,   41,   61,   45,  123,   42,
   43,   59,   45,   46,   47,  264,   41,  125,   24,   44,
  273,  274,  275,  276,  277,  278,   59,   60,  125,   62,
   41,   61,   37,   44,   59,   17,   -1,   42,   43,   -1,
   45,   46,   47,  265,  266,  267,  268,  166,   59,  273,
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
   -1,   62,   54,   93,   41,   -1,   -1,   44,   -1,   -1,
   41,   -1,   -1,   44,   -1,   41,   -1,   -1,   44,   -1,
   -1,   -1,   59,   60,   41,   62,  125,   44,   59,  257,
  258,  259,   93,   59,   60,   87,   62,  265,  266,  267,
   -1,   -1,   59,   60,  272,   62,   -1,   -1,   -1,   -1,
  273,  274,  275,  276,  277,  278,   93,  125,  273,  274,
   -1,   -1,   93,   -1,   -1,   -1,   -1,   93,   41,   -1,
   -1,   44,  273,  274,   41,   -1,   93,   44,  273,  274,
  275,  276,  277,  278,  125,   41,   59,   60,   44,   62,
   -1,  143,   59,   60,   -1,   62,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   59,   60,   -1,   62,   -1,   -1,   -1,
   -1,   -1,  164,  165,  166,  275,  276,  277,  278,   -1,
   93,   -1,   -1,   37,   -1,   -1,   93,  125,   42,   43,
   -1,   45,   46,   47,   -1,   -1,    2,   93,   -1,   -1,
  125,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  273,
  274,  275,  276,  277,  278,   -1,   -1,   23,   24,   -1,
   -1,  260,  261,  262,  263,  264,  265,  266,  267,  268,
  269,   -1,   -1,  272,   -1,   -1,   -1,   -1,   -1,   45,
   -1,   -1,   -1,  273,  274,  275,  276,  277,  278,   -1,
   -1,   -1,  260,  261,  262,  263,  264,  265,  266,  267,
  268,  269,   68,   -1,  272,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  273,  274,  275,  276,  277,  278,   -1,  260,
  261,  262,  263,   -1,  265,  266,  267,  268,  269,   -1,
   -1,  272,   -1,   -1,   -1,   -1,  273,  274,  275,  276,
  277,  278,   -1,   -1,   -1,   -1,   -1,  273,  274,  275,
  276,  277,  278,   -1,   -1,   -1,  273,  274,  275,  276,
  277,  278,  260,  261,  262,  263,   -1,  265,  266,  267,
  268,  269,   -1,   -1,  272,  260,  261,  262,  263,   42,
  265,  266,  267,  268,  269,   -1,   -1,  272,   -1,   -1,
   -1,   54,   -1,   -1,   -1,   -1,   59,   -1,   61,   -1,
  273,  274,  275,  276,  277,  278,  273,  274,  275,  276,
  277,  278,   -1,   42,   -1,   -1,   -1,  273,  274,  275,
  276,  277,  278,   -1,   87,   54,   -1,   -1,   -1,   -1,
   59,   -1,   61,  260,  261,  262,  263,   -1,  265,  266,
  267,  268,  269,   -1,   -1,  272,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   87,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  143,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  153,  154,   -1,   -1,   -1,   52,   -1,   -1,   -1,   -1,
   -1,  164,  165,  166,   -1,   -1,   -1,   -1,   -1,   66,
   67,   68,   -1,   -1,  143,   -1,   -1,   74,   75,   -1,
   -1,   -1,   -1,   -1,  153,  154,   83,   -1,   -1,   86,
   -1,   -1,   -1,   -1,   -1,  164,  165,  166,   -1,   -1,
   97,   98,   99,  100,  101,  102,  103,  104,  105,  106,
  107,  108,  109,  110,  111,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  125,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  145,
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
"sentencia : IF '(' expresion ')' cuerpoCondicional ELSE cuerpoCondicional",
"sentencia : WRITE expresiones ';'",
"sentencia : READ expresiones ';'",
"sentencia : ID '(' parametroLlamadaMetodo ')' ';'",
"sentencia : llamadaFuncion ';'",
"sentencia : RETURN expresion ';'",
"cuerpoCondicional : '{' sentencias '}'",
"cuerpoCondicional : sentencia",
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

//#line 146 "../../src/sintactico/sintactico.y"

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
//#line 540 "Parser.java"
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
