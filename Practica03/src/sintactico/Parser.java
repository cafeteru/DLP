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
//#line 25 "Parser.java"




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
    9,    9,    9,   11,   11,    8,    8,    8,   16,   16,
   12,   12,   10,   10,   10,   10,   10,   10,   10,   10,
   10,   10,   10,   10,   10,   10,   10,   10,   10,   10,
   10,   10,   10,   10,   10,   10,   13,   17,   17,   15,
   15,   14,   14,    5,    5,    5,
};
final static short yylen[] = {                            2,
    9,    2,    0,    9,    9,    2,    3,    0,    4,    2,
    0,    1,    0,    2,    1,    4,    7,    5,    7,    3,
    3,    2,    3,    3,    1,    2,    3,    5,    3,    2,
    3,    1,    1,    1,    1,    1,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    2,    2,    3,    4,    1,    4,    4,    1,    0,    4,
    3,    3,    1,    1,    1,    1,
};
final static short yydefred[] = {                         3,
    0,    0,   64,   65,   66,    0,    0,    2,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    6,    0,    0,
    0,    0,    0,    0,    0,    0,   63,    0,    0,   30,
    0,    0,    0,    0,    0,    0,   61,   62,    0,    0,
   29,    8,   10,    0,    0,    0,   60,    0,    8,    0,
    8,   34,   35,   36,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   15,    0,    0,    0,    9,
    0,    0,    0,   55,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    1,   14,    7,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   22,    0,    0,    0,   21,    0,   20,    0,
    0,   23,    0,    0,    0,   53,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   50,    5,    4,    0,    0,    0,   57,   54,   16,
   56,    0,    0,   25,    0,    0,    0,    0,   17,   24,
   19,
};
final static short yydgoto[] = {                          1,
    2,   48,  104,    8,   19,   35,  105,   65,   66,   67,
  145,   73,   74,   16,   17,   21,  114,
};
final static short yysindex[] = {                         0,
    0,   57,    0,    0,    0, -122, -196,    0,  -79,  -31,
 -175,   11,   17,   26, -216,   40,  -78,    0,  -61,   42,
  -10,   61, -155, -155,   35, -153,    0, -121,   40,    0,
 -135,   89,   45, -109,   14,   86,    0,    0,   92,   40,
    0,    0,    0,   63, -155,   64,    0,  275,    0,  -83,
    0,    0,    0,    0,  -13,  -13,  151,  156,  -13,  158,
  -13,  -13,  220,  310,  142,    0,   71,  145,  275,    0,
  275,  413,  -30,    0,   -7,  -13,  -13,   97,  -13,  -44,
  -44,  177,  104,    0,    0,    0,  -13,  -13,  -13,  -13,
  -13,  -13,  -13,  -13,  -13,  -13,  -13,  -13,  -13,  -13,
  -13,  -13,    0,  409,   95,  101,    0,  -13,    0,  130,
  137,    0,  175,  186,  -13,    0,  420,  420,  251,  251,
  251,  251,  163,  251,  251,  123,  123,  -44,  -44,  -44,
  170,    0,    0,    0,  413,  106,  351,    0,    0,    0,
    0,  409,  409,    0,  -33,  383,  450,  351,    0,    0,
    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    6,    0,  174,    0,    0,    0,    0,
    0,    0,  117,  117,    0,    0,    0,    0,  176,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  184,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  -37,
    0,    0,    0,    0,    0,    0,    0,  225,  109,    0,
  109,   41,    0,    0,    0,    0,    0,    0,  223,  -26,
    1,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  141,    0,    0,    0,    0,    0,    0,
    0,    0,  228,    0,    0,    0,  358,  546,  677,  699,
  705,  712,    0,  734,  740,  533,  540,   27,   36,   62,
    0,    0,    0,    0,   94,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  472,    0,    0,    0,    0,    0,
    0,
};
final static short yygindex[] = {                         0,
    0,  103,  -17,    0,  179,  249,  209,   38,  217,  767,
  134,  -53,  482,   22,    0,    0,    0,
};
final static int YYTABLESIZE=1018;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         33,
   11,  102,   75,   33,   33,   33,   33,   33,   33,   33,
   52,   15,   28,  108,   52,   52,   52,   52,   52,   61,
   52,   33,   33,   33,   33,  113,   63,   18,  107,   15,
   64,   62,   52,   52,   52,   52,  108,   51,   29,   10,
   25,   51,   51,   51,   51,   51,  101,   51,   20,   63,
   22,  109,   40,   33,   44,   33,   23,   45,   32,   51,
   51,   51,   51,   38,   63,   24,   52,   38,   38,   38,
   38,   38,   39,   38,   12,   13,   39,   39,   39,   39,
   39,   32,   39,   26,   32,   38,   38,   38,   38,    3,
    4,    5,    6,   51,   39,   39,   39,   39,   41,   32,
   30,   33,   41,   41,   41,   41,   41,  100,   41,    3,
    4,    5,   98,   96,   31,   97,  102,   99,   38,   38,
   41,   41,   41,   41,  146,  147,   46,   37,   39,   45,
   95,   93,   94,  100,   31,   39,   27,   31,   98,   96,
  100,   97,  102,   99,  116,   98,   96,   41,   97,  102,
   99,   69,   31,   71,   41,  112,   95,   11,   94,  100,
   11,  101,   43,   95,   98,   94,  100,   42,  102,   99,
  136,   98,   96,  100,   97,  102,   99,  137,   98,   96,
    9,   97,  102,   99,   47,   49,   51,  101,   70,   95,
   76,   94,   14,   27,  101,   77,   95,   79,   94,  100,
   86,   34,   34,  103,   98,   96,  100,   97,  102,   99,
   27,   98,   96,  101,   97,  102,   99,  115,  108,  133,
  101,  140,   95,   50,   94,  134,  138,  101,  142,   95,
  148,   94,   26,   13,   27,   33,   33,   33,   33,   33,
   33,   82,   28,   52,   53,   54,   52,   52,   52,   52,
   52,   52,   61,  101,    3,    4,    5,    6,   60,   63,
  101,   55,  141,   59,   62,   12,   55,   55,   58,   55,
   55,   55,   36,   51,   51,   51,   51,   51,   51,  106,
   85,  151,    0,    0,   55,   55,   55,  100,    0,    0,
    0,    0,   98,   96,    0,   97,  102,   99,    0,   38,
   38,   38,   38,   38,   38,    0,    0,   61,   39,   39,
   39,   39,   39,   39,   63,   55,    0,    0,    0,   62,
   85,    3,    4,    5,    6,    0,    7,    0,    0,    0,
    0,    0,    0,    0,   41,   41,   41,   41,   41,   41,
    0,  101,   61,   87,   88,   89,   90,   91,   92,   63,
    0,    0,    0,  144,   62,    0,    0,    0,    0,    0,
    0,    0,   85,   85,  144,    0,    0,    0,    0,   87,
   88,   89,   90,   91,   92,    0,   87,   88,   89,   90,
   91,   92,    0,   61,    0,    0,    0,    0,    0,    0,
   63,    0,    0,    0,    0,   62,    0,    0,   48,    0,
    0,   48,   87,   88,   89,   90,   91,   92,    0,   87,
   88,   89,   90,   91,   92,   61,   48,    0,   48,    0,
    0,    0,   63,    0,    0,    0,    0,   62,    0,    0,
    0,    0,    0,    0,   84,   87,   88,   89,   90,   91,
   92,   61,   87,   88,   89,   90,   91,   92,   63,  100,
   48,    0,    0,   62,   98,   96,  100,   97,  102,   99,
    0,   98,   96,    0,   97,  102,   99,    0,    0,    0,
    0,    0,   95,  143,   94,    0,   52,   53,   54,   95,
    0,   94,   61,    0,    3,    4,    5,    0,    0,   63,
    0,   60,    0,    0,   62,    0,    0,   55,   55,   55,
   55,   55,   55,  101,   18,    0,    0,  149,    0,    0,
  101,   18,    0,    0,    0,    0,   18,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   68,
    0,   52,   53,   54,   55,   56,   57,   58,    0,    3,
    4,    5,    6,   59,    0,   68,   60,    0,    0,    0,
   68,    0,   68,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   52,   53,   54,   55,
   56,   57,   58,   37,  150,   37,   37,   37,   59,    0,
   40,   60,   40,   40,   40,   68,   49,    0,    0,   49,
    0,   37,   37,   37,   37,    0,   18,    0,   40,   40,
   40,   40,    0,    0,   49,    0,   49,   52,   53,   54,
   55,   56,   57,   58,    0,    0,    0,    0,   68,   59,
    0,    0,   60,   68,   68,   37,    0,   68,   68,   68,
   48,   48,   40,    0,    0,    0,    0,    0,   49,   52,
   53,   54,   55,   56,   57,   58,    0,    0,    0,    0,
    0,   59,    0,    0,   60,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   52,   53,   54,   55,   56,
   57,   58,    0,    0,    0,    0,    0,   59,    0,    0,
   60,    0,    0,    0,    0,   87,   88,   89,   90,   91,
   92,    0,    0,    0,   89,   90,   91,   92,    0,    0,
    0,    0,    0,    0,    0,    0,   52,   53,   54,   55,
   56,   57,   58,    0,    0,    0,    0,   44,   59,    0,
   44,   60,    0,    0,    0,    0,    0,    0,   18,   18,
   18,   18,   18,   18,   18,   44,   44,   44,   44,   45,
   18,    0,   45,   18,    0,   46,    0,    0,   46,    0,
    0,    0,   47,    0,    0,   47,    0,   45,   45,   45,
   45,    0,    0,   46,   46,   46,   46,    0,    0,   44,
   47,   47,   47,   47,   42,    0,    0,   42,    0,    0,
   43,    0,    0,   43,    0,    0,    0,    0,    0,    0,
    0,   45,   42,   42,   42,   42,    0,   46,   43,   43,
   43,   43,    0,    0,   47,   37,   37,   37,   37,   37,
   37,    0,   40,   40,   40,   40,   40,   40,   49,   49,
    0,   72,   72,    0,    0,   78,   42,   80,   81,   83,
    0,    0,   43,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  110,  111,    0,   72,    0,    0,    0,    0,
    0,    0,    0,  117,  118,  119,  120,  121,  122,  123,
  124,  125,  126,  127,  128,  129,  130,  131,  132,    0,
    0,    0,    0,    0,  135,    0,    0,    0,    0,    0,
    0,  139,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   44,
   44,   44,   44,   44,   44,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   45,   45,   45,   45,   45,   45,   46,   46,   46,
   46,   46,   46,    0,   47,   47,   47,   47,   47,   47,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   42,   42,   42,   42,
   42,   42,   43,   43,   43,   43,   43,   43,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
  123,   46,   56,   41,   42,   43,   44,   45,   46,   47,
   37,   91,   91,   44,   41,   42,   43,   44,   45,   33,
   47,   59,   60,   61,   62,   79,   40,   59,   59,   91,
   48,   45,   59,   60,   61,   62,   44,   37,   17,    2,
  257,   41,   42,   43,   44,   45,   91,   47,   11,   44,
   40,   59,   31,   91,   41,   93,   40,   44,   21,   59,
   60,   61,   62,   37,   59,   40,   93,   41,   42,   43,
   44,   45,   37,   47,  271,  272,   41,   42,   43,   44,
   45,   41,   47,   44,   44,   59,   60,   61,   62,  265,
  266,  267,  268,   93,   59,   60,   61,   62,   37,   59,
   59,   41,   41,   42,   43,   44,   45,   37,   47,  265,
  266,  267,   42,   43,  125,   45,   46,   47,  272,   93,
   59,   60,   61,   62,  142,  143,   41,   93,   93,   44,
   60,   61,   62,   37,   41,  257,  272,   44,   42,   43,
   37,   45,   46,   47,   41,   42,   43,   59,   45,   46,
   47,   49,   59,   51,   93,   59,   60,   41,   62,   37,
   44,   91,  272,   60,   42,   62,   37,  123,   46,   47,
   41,   42,   43,   37,   45,   46,   47,   41,   42,   43,
    2,   45,   46,   47,   93,  123,  123,   91,  272,   60,
   40,   62,  272,  272,   91,   40,   60,   40,   62,   37,
   59,   23,   24,   59,   42,   43,   37,   45,   46,   47,
  272,   42,   43,   91,   45,   46,   47,   41,   44,  125,
   91,   59,   60,   45,   62,  125,   41,   91,  123,   60,
  264,   62,   59,  125,   59,  273,  274,  275,  276,  277,
  278,   63,   59,  257,  258,  259,  273,  274,  275,  276,
  277,  278,   33,   91,  265,  266,  267,  268,  272,   40,
   91,   37,   93,   41,   45,  125,   42,   43,   41,   45,
   46,   47,   24,  273,  274,  275,  276,  277,  278,   71,
   64,  148,   -1,   -1,   60,   61,   62,   37,   -1,   -1,
   -1,   -1,   42,   43,   -1,   45,   46,   47,   -1,  273,
  274,  275,  276,  277,  278,   -1,   -1,   33,  273,  274,
  275,  276,  277,  278,   40,   91,   -1,   -1,   -1,   45,
  104,  265,  266,  267,  268,   -1,  270,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  273,  274,  275,  276,  277,  278,
   -1,   91,   33,  273,  274,  275,  276,  277,  278,   40,
   -1,   -1,   -1,  137,   45,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  146,  147,  148,   -1,   -1,   -1,   -1,  273,
  274,  275,  276,  277,  278,   -1,  273,  274,  275,  276,
  277,  278,   -1,   33,   -1,   -1,   -1,   -1,   -1,   -1,
   40,   -1,   -1,   -1,   -1,   45,   -1,   -1,   41,   -1,
   -1,   44,  273,  274,  275,  276,  277,  278,   -1,  273,
  274,  275,  276,  277,  278,   33,   59,   -1,   61,   -1,
   -1,   -1,   40,   -1,   -1,   -1,   -1,   45,   -1,   -1,
   -1,   -1,   -1,   -1,  125,  273,  274,  275,  276,  277,
  278,   33,  273,  274,  275,  276,  277,  278,   40,   37,
   93,   -1,   -1,   45,   42,   43,   37,   45,   46,   47,
   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,   -1,
   -1,   -1,   60,  123,   62,   -1,  257,  258,  259,   60,
   -1,   62,   33,   -1,  265,  266,  267,   -1,   -1,   40,
   -1,  272,   -1,   -1,   45,   -1,   -1,  273,  274,  275,
  276,  277,  278,   91,   33,   -1,   -1,  125,   -1,   -1,
   91,   40,   -1,   -1,   -1,   -1,   45,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   48,
   -1,  257,  258,  259,  260,  261,  262,  263,   -1,  265,
  266,  267,  268,  269,   -1,   64,  272,   -1,   -1,   -1,
   69,   -1,   71,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,  260,
  261,  262,  263,   41,  125,   43,   44,   45,  269,   -1,
   41,  272,   43,   44,   45,  104,   41,   -1,   -1,   44,
   -1,   59,   60,   61,   62,   -1,  125,   -1,   59,   60,
   61,   62,   -1,   -1,   59,   -1,   61,  257,  258,  259,
  260,  261,  262,  263,   -1,   -1,   -1,   -1,  137,  269,
   -1,   -1,  272,  142,  143,   93,   -1,  146,  147,  148,
  273,  274,   93,   -1,   -1,   -1,   -1,   -1,   93,  257,
  258,  259,  260,  261,  262,  263,   -1,   -1,   -1,   -1,
   -1,  269,   -1,   -1,  272,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  257,  258,  259,  260,  261,
  262,  263,   -1,   -1,   -1,   -1,   -1,  269,   -1,   -1,
  272,   -1,   -1,   -1,   -1,  273,  274,  275,  276,  277,
  278,   -1,   -1,   -1,  275,  276,  277,  278,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,  260,
  261,  262,  263,   -1,   -1,   -1,   -1,   41,  269,   -1,
   44,  272,   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,
  259,  260,  261,  262,  263,   59,   60,   61,   62,   41,
  269,   -1,   44,  272,   -1,   41,   -1,   -1,   44,   -1,
   -1,   -1,   41,   -1,   -1,   44,   -1,   59,   60,   61,
   62,   -1,   -1,   59,   60,   61,   62,   -1,   -1,   93,
   59,   60,   61,   62,   41,   -1,   -1,   44,   -1,   -1,
   41,   -1,   -1,   44,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   93,   59,   60,   61,   62,   -1,   93,   59,   60,
   61,   62,   -1,   -1,   93,  273,  274,  275,  276,  277,
  278,   -1,  273,  274,  275,  276,  277,  278,  273,  274,
   -1,   55,   56,   -1,   -1,   59,   93,   61,   62,   63,
   -1,   -1,   93,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   76,   77,   -1,   79,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   87,   88,   89,   90,   91,   92,   93,
   94,   95,   96,   97,   98,   99,  100,  101,  102,   -1,
   -1,   -1,   -1,   -1,  108,   -1,   -1,   -1,   -1,   -1,
   -1,  115,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  273,
  274,  275,  276,  277,  278,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  273,  274,  275,  276,  277,  278,  273,  274,  275,
  276,  277,  278,   -1,  273,  274,  275,  276,  277,  278,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  273,  274,  275,  276,
  277,  278,  273,  274,  275,  276,  277,  278,
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
"sentencia : llamadaFuncion ';'",
"sentencia : RETURN expresion ';'",
"cuerpoCondicional : '{' sentencias '}'",
"cuerpoCondicional : sentencia",
"declaracionVariable : tipoSimple identificador",
"declaracionVariable : tipoSimple indices identificador",
"declaracionVariable : STRUCT '{' campos '}' identificador",
"campos : campos declaracionVariable ';'",
"campos : declaracionVariable ';'",
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
"expresion : expresion '.' expresion",
"expresion : '-' expresion",
"expresion : '!' expresion",
"expresion : '(' expresion ')'",
"expresion : '(' tipoSimple ')' expresion",
"expresion : llamadaFuncion",
"expresion : expresion '[' expresion ']'",
"llamadaFuncion : ID '(' argumentosLlamada ')'",
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

//#line 138 "../../src/sintactico/sintactico.y"

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
//#line 552 "Parser.java"
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
