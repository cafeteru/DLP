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
//#line 29 "Parser.java"




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
   10,   10,   10,   10,   10,   13,   17,   17,   15,   15,
   14,   14,    5,    5,    5,
};
final static short yylen[] = {                            2,
    9,    2,    0,    9,    9,    2,    3,    0,    4,    2,
    0,    1,    0,    2,    1,    4,    7,    5,    7,    3,
    3,    2,    3,    3,    1,    2,    3,    5,    3,    2,
    3,    1,    1,    1,    1,    1,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    2,    2,    3,    4,    1,    4,    1,    0,    4,    3,
    3,    1,    1,    1,    1,
};
final static short yydefred[] = {                         3,
    0,    0,   63,   64,   65,    0,    0,    2,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    6,    0,    0,
    0,    0,    0,    0,    0,    0,   62,    0,    0,   30,
    0,    0,    0,    0,    0,    0,   60,   61,    0,    0,
   29,    8,   10,    0,    0,    0,   59,    0,    8,    0,
    8,   34,   35,   36,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   15,    0,    0,    0,    9,
    0,    0,    0,   55,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    1,   14,    7,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   22,    0,    0,    0,   21,    0,   20,    0,    0,
   23,    0,    0,    0,   53,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   50,
    5,    4,    0,    0,    0,   56,   54,   16,    0,    0,
   25,    0,    0,    0,    0,   17,   24,   19,
};
final static short yydgoto[] = {                          1,
    2,   48,  103,    8,   19,   35,  104,   65,   66,   67,
  142,   73,   74,   16,   17,   21,  113,
};
final static short yysindex[] = {                         0,
    0,   60,    0,    0,    0,  -96, -187,    0,  -90,  -27,
 -200,   -1,   18,   53, -163,   58,  -89,    0,  -88,   55,
 -113,   83, -191, -191,   37, -143,    0, -121,   58,    0,
 -135,   89,   33, -112,  -15,  117,    0,    0,   81,   58,
    0,    0,    0,   54, -191,   62,    0,  213,    0,  -91,
    0,    0,    0,    0,  408,  408,  149,  169,  408,  171,
  408,  408,  380,  258,  135,    0,   66,  143,  213,    0,
  213,  161,  -31,    0,   -3,  408,  408,   73,  408,  166,
  166,  172,   97,    0,    0,    0,  408,  408,  408,  408,
  408,  408,  408,  408,  408,  408,  408,  408,  408,  408,
  408,    0,  400,   93,  105,    0,  408,    0,  104,  126,
    0,  178,  191,  408,    0,  182,  182,  217,  217,  217,
  217,  133,  217,  217,  224,  224,  166,  166,  166,    0,
    0,    0,  161,  111,  291,    0,    0,    0,  400,  400,
    0,  -21,  319,  352,  291,    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    5,    0,  186,    0,    0,    0,    0,
    0,    0,  121,  121,    0,    0,    0,    0,  196,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  197,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  -37,
    0,    0,    0,    0,    0,    0,    0,  154,  132,    0,
  132,  146,    0,    0,    0,    0,    0,    0,  226,  -26,
    1,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  144,    0,    0,    0,    0,    0,    0,    0,
    0,  227,    0,    0,    0,  325,  402,  294,  481,  542,
  627,    0,  633,  640,  443,  449,   10,   36,   45,    0,
    0,    0,  176,    0,    0,    0,    0,    0,    0,    0,
    0,  372,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,   74,  -18,    0,  270,  249,  209,   29,  -44,  673,
  136,  -42,  162,  100,    0,    0,    0,
};
final static int YYTABLESIZE=918;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         33,
   15,   28,   15,   33,   33,   33,   33,   33,   33,   33,
   52,   31,  107,   75,   52,   52,   52,   52,   52,   85,
   52,   33,   33,   33,   33,   44,   11,  106,   45,   64,
   10,   18,   52,   52,   52,   52,  112,   51,   22,   20,
  107,   51,   51,   51,   51,   51,   38,   51,   62,   32,
   38,   38,   38,   38,   38,  108,   38,   23,   85,   51,
   51,   51,   51,   62,    3,    4,    5,    6,   38,   38,
   38,   38,   39,    3,    4,    5,   39,   39,   39,   39,
   39,   41,   39,   12,   13,   41,   41,   41,   41,   41,
  141,   41,   24,   25,   39,   39,   39,   39,   85,   85,
  141,   26,  100,   41,   41,   41,   41,   98,   96,  100,
   97,  101,   99,   30,   98,   96,   29,   97,  101,   99,
  143,  144,   69,   33,   71,   95,   93,   94,   38,   37,
   40,  111,   95,  100,   94,   39,   27,  115,   98,   96,
  100,   97,  101,   99,  134,   98,   96,   41,   97,  101,
   99,    3,    4,    5,    6,   42,   95,   46,   94,   43,
   45,   11,  100,   95,   11,   94,  135,   98,   96,  100,
   97,  101,   99,   47,   98,   96,   49,   97,  101,   99,
   70,   14,   27,   27,   51,   95,   32,   94,   76,   32,
   55,  138,   95,   86,   94,   55,   55,  100,   55,   55,
   55,  102,   98,   96,   32,   97,  101,   99,   77,   68,
   79,  101,  114,   55,   55,   55,   31,  131,  100,   31,
   95,  107,   94,   98,   96,   68,   97,  101,   99,  132,
   68,  136,   68,  139,   31,   33,   33,   33,   33,   33,
   33,   95,  145,   94,   26,   61,   52,   52,   52,   52,
   52,   52,   63,  100,   27,   28,   13,   62,   98,   96,
  100,   97,  101,   99,   68,   98,   58,   57,   12,  101,
   99,    9,   36,   51,   51,   51,   51,   51,   51,  105,
  148,    0,   38,   38,   38,   38,   38,   38,    0,    0,
   61,    0,   34,   34,    0,    0,   68,   63,    0,    0,
   68,   68,   62,    0,   68,   68,   68,    0,   39,   39,
   39,   39,   39,   39,   50,    0,    0,   41,   41,   41,
   41,   41,   41,   61,    3,    4,    5,    6,    0,    7,
   63,    0,   82,    0,   44,   62,    0,   44,   87,   88,
   89,   90,   91,   92,    0,   87,   88,   89,   90,   91,
   92,   61,   44,   44,   44,   44,    0,    0,   63,    0,
    0,    0,    0,   62,    0,   48,    0,    0,   48,   87,
   88,   89,   90,   91,   92,    0,   87,   88,   89,   90,
   91,   92,   84,   48,   61,   48,    0,    0,    0,    0,
    0,   63,    0,    0,    0,    0,   62,    0,   87,   88,
   89,   90,   91,   92,   18,   87,   88,   89,   90,   91,
   92,   18,   61,  140,    0,    0,   18,    0,    0,   63,
    0,    0,    0,    0,   62,    0,   55,   55,   55,   55,
   55,   55,   61,   87,   88,   89,   90,   91,   92,   63,
   61,    0,   49,  146,   62,   49,    0,   63,    0,    0,
    0,    0,   62,    0,    0,    0,   89,   90,   91,   92,
   49,    0,   49,    0,    0,    0,    0,    0,    0,   52,
   53,   54,   55,   56,   57,   58,  147,    3,    4,    5,
    6,   59,    0,   37,   60,   37,   37,   37,    0,   40,
    0,   40,   40,   40,    0,    0,   18,    0,    0,    0,
    0,   37,   37,   37,   37,    0,    0,   40,   40,   40,
   40,    0,    0,    0,   52,   53,   54,   55,   56,   57,
   58,   45,    0,    0,   45,    0,   59,    0,    0,   60,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   45,
   45,   45,   45,    0,    0,    0,    0,   52,   53,   54,
   55,   56,   57,   58,    0,    0,    0,    0,    0,   59,
    0,    0,   60,    0,    0,    0,   44,   44,   44,   44,
   44,   44,    0,    0,    0,   52,   53,   54,   55,   56,
   57,   58,   46,    0,    0,   46,    0,   59,    0,    0,
   60,    0,    0,    0,    0,    0,    0,   48,   48,    0,
   46,   46,   46,   46,    0,    0,    0,    0,   52,   53,
   54,   55,   56,   57,   58,    0,    0,    0,    0,    0,
   59,    0,    0,   60,    0,    0,    0,    0,   18,   18,
   18,   18,   18,   18,   18,    0,   52,   53,   54,    0,
   18,    0,    0,   18,    3,    4,    5,    0,    0,    0,
    0,   60,    0,    0,    0,    0,   52,   53,   54,   55,
   56,   57,   58,    0,   52,   53,   54,   47,   59,    0,
   47,   60,    0,   42,   49,   49,   42,    0,    0,   60,
   43,    0,    0,   43,    0,   47,   47,   47,   47,    0,
    0,   42,   42,   42,   42,    0,    0,    0,   43,   43,
   43,   43,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   37,   37,   37,   37,   37,
   37,   40,   40,   40,   40,   40,   40,   72,   72,    0,
    0,   78,    0,   80,   81,   83,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  109,  110,
    0,   72,    0,   45,   45,   45,   45,   45,   45,  116,
  117,  118,  119,  120,  121,  122,  123,  124,  125,  126,
  127,  128,  129,  130,    0,    0,    0,    0,    0,  133,
    0,    0,    0,    0,    0,    0,  137,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   46,   46,   46,   46,   46,   46,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   47,
   47,   47,   47,   47,   47,   42,   42,   42,   42,   42,
   42,    0,   43,   43,   43,   43,   43,   43,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
   91,   91,   91,   41,   42,   43,   44,   45,   46,   47,
   37,  125,   44,   56,   41,   42,   43,   44,   45,   64,
   47,   59,   60,   61,   62,   41,  123,   59,   44,   48,
    2,   59,   59,   60,   61,   62,   79,   37,   40,   11,
   44,   41,   42,   43,   44,   45,   37,   47,   44,   21,
   41,   42,   43,   44,   45,   59,   47,   40,  103,   59,
   60,   61,   62,   59,  265,  266,  267,  268,   59,   60,
   61,   62,   37,  265,  266,  267,   41,   42,   43,   44,
   45,   37,   47,  271,  272,   41,   42,   43,   44,   45,
  135,   47,   40,  257,   59,   60,   61,   62,  143,  144,
  145,   44,   37,   59,   60,   61,   62,   42,   43,   37,
   45,   46,   47,   59,   42,   43,   17,   45,   46,   47,
  139,  140,   49,   41,   51,   60,   61,   62,  272,   93,
   31,   59,   60,   37,   62,  257,  272,   41,   42,   43,
   37,   45,   46,   47,   41,   42,   43,   59,   45,   46,
   47,  265,  266,  267,  268,  123,   60,   41,   62,  272,
   44,   41,   37,   60,   44,   62,   41,   42,   43,   37,
   45,   46,   47,   93,   42,   43,  123,   45,   46,   47,
  272,  272,  272,  272,  123,   60,   41,   62,   40,   44,
   37,   59,   60,   59,   62,   42,   43,   37,   45,   46,
   47,   59,   42,   43,   59,   45,   46,   47,   40,   48,
   40,   46,   41,   60,   61,   62,   41,  125,   37,   44,
   60,   44,   62,   42,   43,   64,   45,   46,   47,  125,
   69,   41,   71,  123,   59,  273,  274,  275,  276,  277,
  278,   60,  264,   62,   59,   33,  273,  274,  275,  276,
  277,  278,   40,   37,   59,   59,  125,   45,   42,   43,
   37,   45,   46,   47,  103,   42,   41,   41,  125,   46,
   47,    2,   24,  273,  274,  275,  276,  277,  278,   71,
  145,   -1,  273,  274,  275,  276,  277,  278,   -1,   -1,
   33,   -1,   23,   24,   -1,   -1,  135,   40,   -1,   -1,
  139,  140,   45,   -1,  143,  144,  145,   -1,  273,  274,
  275,  276,  277,  278,   45,   -1,   -1,  273,  274,  275,
  276,  277,  278,   33,  265,  266,  267,  268,   -1,  270,
   40,   -1,   63,   -1,   41,   45,   -1,   44,  273,  274,
  275,  276,  277,  278,   -1,  273,  274,  275,  276,  277,
  278,   33,   59,   60,   61,   62,   -1,   -1,   40,   -1,
   -1,   -1,   -1,   45,   -1,   41,   -1,   -1,   44,  273,
  274,  275,  276,  277,  278,   -1,  273,  274,  275,  276,
  277,  278,  125,   59,   33,   61,   -1,   -1,   -1,   -1,
   -1,   40,   -1,   -1,   -1,   -1,   45,   -1,  273,  274,
  275,  276,  277,  278,   33,  273,  274,  275,  276,  277,
  278,   40,   33,  123,   -1,   -1,   45,   -1,   -1,   40,
   -1,   -1,   -1,   -1,   45,   -1,  273,  274,  275,  276,
  277,  278,   33,  273,  274,  275,  276,  277,  278,   40,
   33,   -1,   41,  125,   45,   44,   -1,   40,   -1,   -1,
   -1,   -1,   45,   -1,   -1,   -1,  275,  276,  277,  278,
   59,   -1,   61,   -1,   -1,   -1,   -1,   -1,   -1,  257,
  258,  259,  260,  261,  262,  263,  125,  265,  266,  267,
  268,  269,   -1,   41,  272,   43,   44,   45,   -1,   41,
   -1,   43,   44,   45,   -1,   -1,  125,   -1,   -1,   -1,
   -1,   59,   60,   61,   62,   -1,   -1,   59,   60,   61,
   62,   -1,   -1,   -1,  257,  258,  259,  260,  261,  262,
  263,   41,   -1,   -1,   44,   -1,  269,   -1,   -1,  272,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   59,
   60,   61,   62,   -1,   -1,   -1,   -1,  257,  258,  259,
  260,  261,  262,  263,   -1,   -1,   -1,   -1,   -1,  269,
   -1,   -1,  272,   -1,   -1,   -1,  273,  274,  275,  276,
  277,  278,   -1,   -1,   -1,  257,  258,  259,  260,  261,
  262,  263,   41,   -1,   -1,   44,   -1,  269,   -1,   -1,
  272,   -1,   -1,   -1,   -1,   -1,   -1,  273,  274,   -1,
   59,   60,   61,   62,   -1,   -1,   -1,   -1,  257,  258,
  259,  260,  261,  262,  263,   -1,   -1,   -1,   -1,   -1,
  269,   -1,   -1,  272,   -1,   -1,   -1,   -1,  257,  258,
  259,  260,  261,  262,  263,   -1,  257,  258,  259,   -1,
  269,   -1,   -1,  272,  265,  266,  267,   -1,   -1,   -1,
   -1,  272,   -1,   -1,   -1,   -1,  257,  258,  259,  260,
  261,  262,  263,   -1,  257,  258,  259,   41,  269,   -1,
   44,  272,   -1,   41,  273,  274,   44,   -1,   -1,  272,
   41,   -1,   -1,   44,   -1,   59,   60,   61,   62,   -1,
   -1,   59,   60,   61,   62,   -1,   -1,   -1,   59,   60,
   61,   62,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  273,  274,  275,  276,  277,
  278,  273,  274,  275,  276,  277,  278,   55,   56,   -1,
   -1,   59,   -1,   61,   62,   63,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   76,   77,
   -1,   79,   -1,  273,  274,  275,  276,  277,  278,   87,
   88,   89,   90,   91,   92,   93,   94,   95,   96,   97,
   98,   99,  100,  101,   -1,   -1,   -1,   -1,   -1,  107,
   -1,   -1,   -1,   -1,   -1,   -1,  114,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  273,  274,  275,  276,  277,  278,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  273,
  274,  275,  276,  277,  278,  273,  274,  275,  276,  277,
  278,   -1,  273,  274,  275,  276,  277,  278,
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

//#line 153 "../../src/sintactico/sintactico.y"

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
//#line 532 "Parser.java"
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
																						this.ast = new Programa(lexico.getLine(), lexico.getColumn(), aux); 	}
break;
case 2:
//#line 45 "../../src/sintactico/sintactico.y"
{	yyval = val_peek(1); ((List<Definicion>)yyval).add((Definicion)val_peek(0)); }
break;
case 3:
//#line 46 "../../src/sintactico/sintactico.y"
{	yyval = new ArrayList();}
break;
case 4:
//#line 49 "../../src/sintactico/sintactico.y"
{ 	yyval = new DefFuncion(lexico.getLine(), lexico.getColumn(), "main",
																										new TipoFuncion(new ArrayList(), TipoVoid.getInstancia()), 
																									(	List<DefVariable>)val_peek(2), (List<Sentencia>)val_peek(1)); }
break;
case 5:
//#line 52 "../../src/sintactico/sintactico.y"
{	new DefFuncion(lexico.getLine(), lexico.getColumn(), "main",
																										new TipoFuncion(new ArrayList(), TipoVoid.getInstancia()), 
																										(List<DefVariable>)val_peek(2), (List<Sentencia>)val_peek(1));}
break;
case 59:
//#line 140 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(3); ((List<Integer>)yyval).add((int)val_peek(1)); }
break;
case 60:
//#line 141 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<Integer>(); ((List<Integer>)yyval).add((int)val_peek(1));  }
break;
case 61:
//#line 144 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(2); ((List<String>)yyval).add((String)val_peek(0)); }
break;
case 62:
//#line 145 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<String>(); ((List<String>)yyval).add((String)val_peek(0));  }
break;
case 63:
//#line 148 "../../src/sintactico/sintactico.y"
{ yyval = TipoEntero.getInstancia(); }
break;
case 64:
//#line 149 "../../src/sintactico/sintactico.y"
{ yyval = TipoReal.getInstancia(); }
break;
case 65:
//#line 150 "../../src/sintactico/sintactico.y"
{ yyval = TipoCaracter.getInstancia(); }
break;
//#line 737 "Parser.java"
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
