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
    6,    9,    7,    7,    3,    3,   10,   10,   10,   10,
   10,   10,   10,   10,   12,   12,    8,    8,    8,   17,
   17,   13,   13,   11,   11,   11,   11,   11,   11,   11,
   11,   11,   11,   11,   11,   11,   11,   11,   11,   11,
   11,   11,   11,   11,   11,   11,   11,   14,   18,   18,
   16,   16,   15,   15,    5,    5,    5,
};
final static short yylen[] = {                            2,
    9,    2,    0,    9,    9,    2,    3,    0,    3,    1,
    0,    2,    1,    0,    2,    1,    4,    5,    5,    7,
    3,    3,    2,    3,    3,    1,    2,    3,    5,    3,
    2,    3,    1,    1,    1,    1,    1,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    2,    2,    3,    3,    4,    1,    4,    4,    1,    0,
    4,    3,    3,    1,    1,    1,    1,
};
final static short yydefred[] = {                         3,
    0,    0,   65,   66,   67,    0,    0,    2,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    6,    0,    0,
    0,    0,    0,    0,    0,    0,   64,    0,   31,    0,
    0,    0,    0,    0,   10,    0,    0,   63,    0,   30,
    8,   12,    0,    0,    0,   61,    0,    8,    9,    8,
   35,   36,   37,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   16,    0,    0,    0,    0,    0,
    0,   56,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    1,   15,    7,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   23,    0,    0,    0,   22,    0,   21,    0,    0,   24,
    0,    0,    0,   54,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   53,
    5,    4,    0,    0,    0,   58,   55,   17,   57,    0,
   26,   18,    0,    0,    0,   25,   20,
};
final static short yydgoto[] = {                          1,
    2,   47,  102,    8,   19,   34,  103,   64,   35,   65,
   66,  142,   71,   72,   16,   17,   21,  112,
};
final static short yysindex[] = {                         0,
    0, -140,    0,    0,    0,  -70, -180,    0,  -79,   17,
 -130,   44,   53,   61, -155,   66, -161,    0,  -78,   89,
  -10,  111, -208, -208,   19, -119,    0,   66,    0, -161,
   95,   40,  -91,  117,    0,  145,   77,    0,   66,    0,
    0,    0,   64, -208,   68,    0,  275,    0,    0,    0,
    0,    0,    0,  -13,  -13,  156,  162,  -13,  164,  -13,
  220,  -13,  310,  126,    0,   71,  144,  275,  275,  413,
  -30,    0,    7,  -13,  -13,   97,  -13,  -44,  177,  104,
  -44,    0,    0,    0,  -13,  -13,  -13,  -13,  -13,  -13,
  -13,  -13,  -13,  -13,  -13,  -13,  -13,  -13,  -13,  -61,
    0,  409,   94,   99,    0,  -13,    0,  130,  137,    0,
  176,  185,  -13,    0,  420,  420,  251,  251,  251,  251,
  163,  251,  251,  123,  123,  -44,  -44,  -44,  170,    0,
    0,    0,  413,  351,  351,    0,    0,    0,    0,  409,
    0,    0,  -35,  383,  351,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   31,    0,  168,    0,    0,    0,    0,
    0,    0,  157,  157,    0,    0,    0,  172,    0,    0,
    0,    0,    0,    0,    0,    0,  -39,    0,   89,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  -37,    0,
    0,    0,    0,    0,    0,    0,  225,  109,  109,   -4,
    0,    0,    0,    0,    0,    0,  194,  -26,    0,    0,
    1,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  118,    0,    0,    0,    0,    0,    0,    0,    0,
  201,    0,    0,    0,  633,  707,  358,  504,  544,  557,
    0,  641,  676,  469,  533,   27,   36,   62,    0,    0,
    0,    0,   41,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  450,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  -47,  -21,    0,  365,  240,  197,   28,  229,  217,
  699, -104,  -27,  702,   35,  232,    0,    0,
};
final static int YYTABLESIZE=981;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         34,
   68,  100,   69,   34,   34,   34,   34,   34,   34,   34,
   52,   15,   15,  106,   52,   52,   52,   52,   52,   62,
   52,   34,   34,   34,   34,   63,   61,   73,  105,   10,
  143,   60,   52,   52,   52,   52,   33,   51,   20,   33,
  147,   51,   51,   51,   51,   51,   99,   51,   31,  111,
  106,   28,   11,   34,   33,   34,    3,    4,    5,   51,
   51,   51,   51,   39,   39,  107,   52,   39,   39,   39,
   39,   39,   40,   39,   64,   18,   40,   40,   40,   40,
   40,   32,   40,   22,   32,   39,   39,   39,   39,   64,
   12,   13,   23,   51,   40,   40,   40,   40,   42,   32,
   24,   25,   42,   42,   42,   42,   42,   98,   42,   26,
   27,   37,   96,   94,   30,   95,  100,   97,  144,   39,
   42,   42,   42,   42,    3,    4,    5,    6,   40,    7,
   93,   91,   92,   98,    3,    4,    5,    6,   96,   94,
   98,   95,  100,   97,  114,   96,   94,   29,   95,  100,
   97,   32,   38,   40,   42,  110,   93,   43,   92,   98,
   44,   99,   41,   93,   96,   92,   98,   15,  100,   97,
  134,   96,   94,   98,   95,  100,   97,  135,   96,   94,
   42,   95,  100,   97,   84,   45,   48,   99,   44,   93,
   50,   92,   14,   27,   99,   74,   93,   11,   92,   98,
   11,   75,  101,   77,   96,   94,   98,   95,  100,   97,
  130,   96,   94,   99,   95,  100,   97,  113,  131,  106,
   99,  138,   93,  132,   92,  136,   27,   99,  145,   93,
   28,   92,   62,   14,   60,   34,   34,   34,   34,   34,
   34,   59,   13,   51,   52,   53,   52,   52,   52,   52,
   52,   52,   62,   99,    3,    4,    5,    6,   59,   61,
   99,   56,  139,   36,   60,  104,   56,   56,   46,   56,
   56,   56,   49,   51,   51,   51,   51,   51,   51,   83,
    0,    0,    0,    0,   56,   56,   56,   98,    0,    0,
    0,    0,   96,   94,    0,   95,  100,   97,    0,   39,
   39,   39,   39,   39,   39,    0,    0,   62,   40,   40,
   40,   40,   40,   40,   61,   56,    0,    0,   83,   60,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   42,   42,   42,   42,   42,   42,
    0,   99,   62,   85,   86,   87,   88,   89,   90,   61,
  141,  141,    0,    0,   60,    0,    0,    0,    0,    0,
   83,  141,    0,    0,    0,    0,    9,    0,    0,   85,
   86,   87,   88,   89,   90,    0,   85,   86,   87,   88,
   89,   90,    0,   62,    0,    0,    0,   33,   33,    0,
   61,    0,    0,    0,    0,   60,    0,    0,   45,    0,
    0,   45,   85,   86,   87,   88,   89,   90,   33,   85,
   86,   87,   88,   89,   90,   62,   45,   45,   45,   45,
    0,    0,   61,    0,    0,   79,    0,   60,    0,    0,
    0,    0,    0,    0,   82,   85,   86,   87,   88,   89,
   90,   62,   85,   86,   87,   88,   89,   90,   61,   98,
   45,    0,    0,   60,   96,   94,   98,   95,  100,   97,
    0,   96,   94,    0,   95,  100,   97,    0,    0,    0,
    0,    0,   93,  140,   92,    0,   51,   52,   53,   93,
    0,   92,   19,    0,    3,    4,    5,    0,    0,   19,
    0,   59,    0,    0,   19,    0,    0,   56,   56,   56,
   56,   56,   56,   99,    0,    0,    0,  146,    0,   38,
   99,   38,   38,   38,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   38,   38,   38,
   38,   51,   52,   53,   54,   55,   56,   57,    0,    3,
    4,    5,    6,   58,   46,    0,   59,   46,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   38,   46,   46,   46,   46,   51,   52,   53,   54,
   55,   56,   57,   41,   19,   41,   41,   41,   58,    0,
    0,   59,    0,    0,   47,    0,    0,   47,    0,    0,
    0,   41,   41,   41,   41,    0,   46,   48,    0,    0,
   48,    0,   47,   47,   47,   47,    0,   51,   52,   53,
   54,   55,   56,   57,    0,   48,   48,   48,   48,   58,
    0,    0,   59,    0,    0,   41,    0,    0,    0,    0,
   45,   45,   45,   45,   45,   45,   47,    0,    0,   51,
   52,   53,   54,   55,   56,   57,    0,    0,    0,   48,
    0,   58,    0,    0,   59,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   51,   52,   53,   54,   55,
   56,   57,    0,   49,    0,    0,   49,   58,    0,    0,
   59,   43,    0,    0,   43,   85,   86,   87,   88,   89,
   90,   49,    0,   49,   87,   88,   89,   90,    0,   43,
   43,   43,   43,    0,    0,    0,   19,   19,   19,   19,
   19,   19,   19,    0,    0,    0,   44,    0,   19,   44,
    0,   19,    0,    0,    0,   49,    0,    0,    0,    0,
    0,    0,    0,   43,   44,   44,   44,   44,    0,    0,
    0,   38,   38,   38,   38,   38,   38,   50,   67,    0,
   50,    0,   70,   70,    0,    0,   76,    0,   78,   80,
   81,    0,    0,    0,   67,   50,    0,   50,   44,   67,
   67,    0,  108,  109,    0,   70,   46,   46,   46,   46,
   46,   46,    0,  115,  116,  117,  118,  119,  120,  121,
  122,  123,  124,  125,  126,  127,  128,  129,    0,   50,
    0,    0,    0,   67,  133,   41,   41,   41,   41,   41,
   41,  137,    0,    0,    0,    0,   47,   47,   47,   47,
   47,   47,    0,    0,    0,    0,    0,    0,    0,   48,
   48,   48,   48,   48,   48,   67,   67,    0,    0,    0,
    0,   67,    0,    0,    0,   67,   67,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   49,   49,    0,    0,    0,
    0,    0,    0,   43,   43,   43,   43,   43,   43,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   44,   44,
   44,   44,   44,   44,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   50,
   50,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
   48,   46,   50,   41,   42,   43,   44,   45,   46,   47,
   37,   91,   91,   44,   41,   42,   43,   44,   45,   33,
   47,   59,   60,   61,   62,   47,   40,   55,   59,    2,
  135,   45,   59,   60,   61,   62,   41,   37,   11,   44,
  145,   41,   42,   43,   44,   45,   91,   47,   21,   77,
   44,   17,  123,   91,   59,   93,  265,  266,  267,   59,
   60,   61,   62,   37,   30,   59,   93,   41,   42,   43,
   44,   45,   37,   47,   44,   59,   41,   42,   43,   44,
   45,   41,   47,   40,   44,   59,   60,   61,   62,   59,
  271,  272,   40,   93,   59,   60,   61,   62,   37,   59,
   40,  257,   41,   42,   43,   44,   45,   37,   47,   44,
  272,   93,   42,   43,  125,   45,   46,   47,  140,   93,
   59,   60,   61,   62,  265,  266,  267,  268,   93,  270,
   60,   61,   62,   37,  265,  266,  267,  268,   42,   43,
   37,   45,   46,   47,   41,   42,   43,   59,   45,   46,
   47,   41,  272,   59,   93,   59,   60,   41,   62,   37,
   44,   91,  123,   60,   42,   62,   37,   91,   46,   47,
   41,   42,   43,   37,   45,   46,   47,   41,   42,   43,
  272,   45,   46,   47,   59,   41,  123,   91,   44,   60,
  123,   62,  272,  272,   91,   40,   60,   41,   62,   37,
   44,   40,   59,   40,   42,   43,   37,   45,   46,   47,
  272,   42,   43,   91,   45,   46,   47,   41,  125,   44,
   91,   59,   60,  125,   62,   41,   59,   91,  264,   60,
   59,   62,  272,  125,   41,  273,  274,  275,  276,  277,
  278,   41,  125,  257,  258,  259,  273,  274,  275,  276,
  277,  278,   33,   91,  265,  266,  267,  268,  272,   40,
   91,   37,   93,   24,   45,   69,   42,   43,   37,   45,
   46,   47,   44,  273,  274,  275,  276,  277,  278,   63,
   -1,   -1,   -1,   -1,   60,   61,   62,   37,   -1,   -1,
   -1,   -1,   42,   43,   -1,   45,   46,   47,   -1,  273,
  274,  275,  276,  277,  278,   -1,   -1,   33,  273,  274,
  275,  276,  277,  278,   40,   91,   -1,   -1,  102,   45,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  273,  274,  275,  276,  277,  278,
   -1,   91,   33,  273,  274,  275,  276,  277,  278,   40,
  134,  135,   -1,   -1,   45,   -1,   -1,   -1,   -1,   -1,
  144,  145,   -1,   -1,   -1,   -1,    2,   -1,   -1,  273,
  274,  275,  276,  277,  278,   -1,  273,  274,  275,  276,
  277,  278,   -1,   33,   -1,   -1,   -1,   23,   24,   -1,
   40,   -1,   -1,   -1,   -1,   45,   -1,   -1,   41,   -1,
   -1,   44,  273,  274,  275,  276,  277,  278,   44,  273,
  274,  275,  276,  277,  278,   33,   59,   60,   61,   62,
   -1,   -1,   40,   -1,   -1,   61,   -1,   45,   -1,   -1,
   -1,   -1,   -1,   -1,  125,  273,  274,  275,  276,  277,
  278,   33,  273,  274,  275,  276,  277,  278,   40,   37,
   93,   -1,   -1,   45,   42,   43,   37,   45,   46,   47,
   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,   -1,
   -1,   -1,   60,  123,   62,   -1,  257,  258,  259,   60,
   -1,   62,   33,   -1,  265,  266,  267,   -1,   -1,   40,
   -1,  272,   -1,   -1,   45,   -1,   -1,  273,  274,  275,
  276,  277,  278,   91,   -1,   -1,   -1,  125,   -1,   41,
   91,   43,   44,   45,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   59,   60,   61,
   62,  257,  258,  259,  260,  261,  262,  263,   -1,  265,
  266,  267,  268,  269,   41,   -1,  272,   44,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   93,   59,   60,   61,   62,  257,  258,  259,  260,
  261,  262,  263,   41,  125,   43,   44,   45,  269,   -1,
   -1,  272,   -1,   -1,   41,   -1,   -1,   44,   -1,   -1,
   -1,   59,   60,   61,   62,   -1,   93,   41,   -1,   -1,
   44,   -1,   59,   60,   61,   62,   -1,  257,  258,  259,
  260,  261,  262,  263,   -1,   59,   60,   61,   62,  269,
   -1,   -1,  272,   -1,   -1,   93,   -1,   -1,   -1,   -1,
  273,  274,  275,  276,  277,  278,   93,   -1,   -1,  257,
  258,  259,  260,  261,  262,  263,   -1,   -1,   -1,   93,
   -1,  269,   -1,   -1,  272,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  257,  258,  259,  260,  261,
  262,  263,   -1,   41,   -1,   -1,   44,  269,   -1,   -1,
  272,   41,   -1,   -1,   44,  273,  274,  275,  276,  277,
  278,   59,   -1,   61,  275,  276,  277,  278,   -1,   59,
   60,   61,   62,   -1,   -1,   -1,  257,  258,  259,  260,
  261,  262,  263,   -1,   -1,   -1,   41,   -1,  269,   44,
   -1,  272,   -1,   -1,   -1,   93,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   93,   59,   60,   61,   62,   -1,   -1,
   -1,  273,  274,  275,  276,  277,  278,   41,   47,   -1,
   44,   -1,   54,   55,   -1,   -1,   58,   -1,   60,   61,
   62,   -1,   -1,   -1,   63,   59,   -1,   61,   93,   68,
   69,   -1,   74,   75,   -1,   77,  273,  274,  275,  276,
  277,  278,   -1,   85,   86,   87,   88,   89,   90,   91,
   92,   93,   94,   95,   96,   97,   98,   99,   -1,   93,
   -1,   -1,   -1,  102,  106,  273,  274,  275,  276,  277,
  278,  113,   -1,   -1,   -1,   -1,  273,  274,  275,  276,
  277,  278,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  273,
  274,  275,  276,  277,  278,  134,  135,   -1,   -1,   -1,
   -1,  140,   -1,   -1,   -1,  144,  145,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  273,  274,   -1,   -1,   -1,
   -1,   -1,   -1,  273,  274,  275,  276,  277,  278,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  273,  274,
  275,  276,  277,  278,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  273,
  274,
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
"parametros : parametros ',' definicionVariable",
"parametros : definicionVariable",
"parametros :",
"definicionVariable : tipoSimple ID",
"cuerpoDefinicion : sentencias",
"cuerpoDefinicion :",
"sentencias : sentencias sentencia",
"sentencias : sentencia",
"sentencia : expresion '=' expresion ';'",
"sentencia : WHILE '(' expresion ')' cuerpoCondicional",
"sentencia : IF '(' expresion ')' cuerpoCondicional",
"sentencia : IF '(' expresion ')' cuerpoCondicional ELSE cuerpoCondicional",
"sentencia : WRITE expresiones ';'",
"sentencia : READ expresiones ';'",
"sentencia : invocacion ';'",
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
"expresion : '!' expresion",
"expresion : '-' expresion",
"expresion : expresion '.' ID",
"expresion : '(' expresion ')'",
"expresion : '(' tipoSimple ')' expresion",
"expresion : invocacion",
"expresion : expresion '[' expresion ']'",
"invocacion : ID '(' argumentosLlamada ')'",
"argumentosLlamada : expresiones",
"argumentosLlamada :",
"indices : '[' CTE_ENTERA ']' indices",
"indices : '[' CTE_ENTERA ']'",
"identificador : identificador ',' ID",
"identificador : ID",
"tipoSimple : INT",
"tipoSimple : DOUBLE",
"tipoSimple : CHAR",
};

//#line 249 "../../src/sintactico/sintactico.y"

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
	this.yylval = lexico.getYylval(); 
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
//#line 549 "Parser.java"
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
//#line 42 "../../src/sintactico/sintactico.y"
{ 	List<Definicion> definiciones = (List<Definicion>)val_peek(8);																						
																											Definicion main = new DefFuncion(lexico.getLine(), lexico.getColumn(), "main",
																												new TipoFuncion(new ArrayList<DefVariable>(), TipoVoid.getInstancia()), 
																												(List<DefVariable>)val_peek(2), (List<Sentencia>)val_peek(1));
																											definiciones.add(main);
																																												
																											this.ast = new Programa(lexico.getLine(), lexico.getColumn(), definiciones); 
																										}
break;
case 2:
//#line 50 "../../src/sintactico/sintactico.y"
{	yyval = val_peek(1); 
																											List<Definicion> lista = (List<Definicion>)yyval;
																											List<String> nombres = new ArrayList();
																											for(Definicion nombre : lista){
																												nombres.add(nombre.getNombre());
																											}
																											List<Definicion> listaDefiniciones = (List<Definicion>)val_peek(0);
																											for(int i = 0; i < listaDefiniciones.size(); i++){
																												Definicion elemento = listaDefiniciones.get(i);
																											    lista.add(elemento);																				
																												
																											}
																										}
break;
case 3:
//#line 63 "../../src/sintactico/sintactico.y"
{	yyval = new ArrayList(); }
break;
case 4:
//#line 66 "../../src/sintactico/sintactico.y"
{ 	List<Definicion> lista = new ArrayList();
																											lista.add(new DefFuncion(lexico.getLine(), lexico.getColumn(), (String) val_peek(7),
																												new TipoFuncion((List<DefVariable>)val_peek(5), (Tipo)val_peek(8)), 
																												(List<DefVariable>)val_peek(2), (List<Sentencia>)val_peek(1)));
																											yyval = lista; 
																										}
break;
case 5:
//#line 72 "../../src/sintactico/sintactico.y"
{	List<Definicion> lista = new ArrayList();
																											lista.add(new DefFuncion(lexico.getLine(), lexico.getColumn(),  (String) val_peek(7),
																											new TipoFuncion((List<DefVariable>)val_peek(5), TipoVoid.getInstancia()), 
																											(List<DefVariable>)val_peek(2), (List<Sentencia>)val_peek(1)));
																											yyval = lista;
																										}
break;
case 6:
//#line 78 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(1);  }
break;
case 7:
//#line 81 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(2);
																											List<DefVariable> lista = (List<DefVariable>)yyval;
																											List<String> nombres = new ArrayList();
																											for(Definicion nombre : lista){
																												nombres.add(nombre.getNombre());
																											}
																											for(DefVariable elemento : (List<DefVariable>)val_peek(1))
																													lista.add(elemento); 
																										}
break;
case 8:
//#line 90 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<DefVariable>();}
break;
case 9:
//#line 93 "../../src/sintactico/sintactico.y"
{	yyval = val_peek(2); 
																											List<DefVariable> lista = (List<DefVariable>)yyval;
																											List<String> nombres = new ArrayList();																											
																											for(Definicion nombre : lista)
																												nombres.add(nombre.getNombre());
																												
																											for(DefVariable elemento : (List<DefVariable>) val_peek(0)){
																												lista.add(elemento);																												
																											}
																										}
break;
case 10:
//#line 103 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(0); }
break;
case 11:
//#line 104 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<DefVariable>();}
break;
case 12:
//#line 107 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<>();
																											((List<DefVariable>) yyval).add(new DefVariable(lexico.getLine(), 
																												lexico.getColumn(), (String)val_peek(0), (Tipo)val_peek(1)));  
																										}
break;
case 13:
//#line 113 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(0);}
break;
case 14:
//#line 114 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<Sentencia>();}
break;
case 15:
//#line 117 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(1);((List<Sentencia>)yyval).add((Sentencia)val_peek(0)); }
break;
case 16:
//#line 118 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<Sentencia>(); ((List<Sentencia>)yyval).add((Sentencia)val_peek(0)); }
break;
case 17:
//#line 121 "../../src/sintactico/sintactico.y"
{ 	yyval = new Asignacion(lexico.getLine(), lexico.getColumn(), (Expresion)val_peek(3), (Expresion)val_peek(1)); }
break;
case 18:
//#line 122 "../../src/sintactico/sintactico.y"
{ 	yyval = new SentenciaWhile(lexico.getLine(), lexico.getColumn(), (Expresion)val_peek(2), (List<Sentencia>)val_peek(0));}
break;
case 19:
//#line 123 "../../src/sintactico/sintactico.y"
{ 	yyval = new SentenciaIf(lexico.getLine(), lexico.getColumn(), (Expresion)val_peek(2), (List<Sentencia>)val_peek(0), new ArrayList()); }
break;
case 20:
//#line 124 "../../src/sintactico/sintactico.y"
{ 	yyval = new SentenciaIf(lexico.getLine(), lexico.getColumn(), (Expresion)val_peek(4), (List<Sentencia>)val_peek(2), (List<Sentencia>)val_peek(0));}
break;
case 21:
//#line 125 "../../src/sintactico/sintactico.y"
{ 	yyval = new Escritura(lexico.getLine(), lexico.getColumn(),(List<Expresion>)val_peek(1));}
break;
case 22:
//#line 126 "../../src/sintactico/sintactico.y"
{ 	yyval = new Lectura(lexico.getLine(), lexico.getColumn(),(List<Expresion>)val_peek(1)); }
break;
case 23:
//#line 127 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(1); }
break;
case 24:
//#line 128 "../../src/sintactico/sintactico.y"
{ 	yyval = new Return(lexico.getLine(), lexico.getColumn(), (Expresion)val_peek(1));}
break;
case 25:
//#line 131 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(1);	}
break;
case 26:
//#line 132 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<Sentencia>(); ((List<Sentencia>)yyval).add((Sentencia)val_peek(0));  }
break;
case 27:
//#line 135 "../../src/sintactico/sintactico.y"
{ 	List<DefVariable> variables = new ArrayList();
																											for(String aux: (List<String>)val_peek(0)){
																												variables.add(new DefVariable(lexico.getLine(), lexico.getColumn(), aux, (Tipo)val_peek(1)));
																											}	 
																											yyval = variables; 
																										}
break;
case 28:
//#line 141 "../../src/sintactico/sintactico.y"
{	List<Integer> indices = (List<Integer>)val_peek(1);
																											TipoArray tipo = new TipoArray(indices.get(0), (Tipo)val_peek(2));
																											for(int i = 1; i < indices.size(); i++){
																												tipo = new TipoArray(indices.get(i), tipo);
																											}	           												
		           												
																											List<DefVariable> variables = new ArrayList();
																											for(String id: (List<String>)val_peek(0)){
																												variables.add(new DefVariable(lexico.getLine(), lexico.getColumn(), id, tipo));
																											}	 
																											yyval = variables;  
																										}
break;
case 29:
//#line 153 "../../src/sintactico/sintactico.y"
{ 	List<CampoRegistro> registrosStruct = new ArrayList();
		           																							for(DefVariable var : (List<DefVariable>) val_peek(2)){
		           																								registrosStruct.add(new CampoRegistro(lexico.getLine(), lexico.getColumn(), var.getNombre(), var.getTipo()));
																											}
		           												
																											TipoRegistro registro = new TipoRegistro(registrosStruct);
		           												          												
																											List<DefVariable> variables = new ArrayList();
																											for(String aux: (List<String>)val_peek(0)){
																												variables.add(new DefVariable(lexico.getLine(), lexico.getColumn(), aux, registro));
																											}	 
																											yyval = variables; 															 
																										}
break;
case 30:
//#line 168 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(2);
																											List<DefVariable> lista = (List<DefVariable>)yyval;
																											List<String> nombres = new ArrayList();																											
																											for(DefVariable nombre : lista)
																												nombres.add(nombre.getNombre());																											
																											for(DefVariable elemento : (List<DefVariable>)val_peek(1)){ 
																											if(!nombres.contains(elemento.getNombre()))
																												(lista).add(elemento); 
																											else
																												new TipoError(lexico.getLine(), lexico.getColumn(),"Campo duplicado -> " + elemento.getNombre());
																											}
																										}
break;
case 31:
//#line 180 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(1);  }
break;
case 32:
//#line 183 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(2); 
																											List<Expresion> lista = (List<Expresion>)yyval;
																											Expresion elemento = (Expresion)val_peek(0);
																											lista.add(elemento);																																																								
																										}
break;
case 33:
//#line 188 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<Expresion>(); ((List<Expresion>)yyval).add((Expresion)val_peek(0));  	}
break;
case 34:
//#line 191 "../../src/sintactico/sintactico.y"
{ 	yyval = new Variable(lexico.getLine(), lexico.getColumn(), (String) val_peek(0)); 	}
break;
case 35:
//#line 192 "../../src/sintactico/sintactico.y"
{ 	yyval = new LiteralEntero(lexico.getLine(), lexico.getColumn(), (Integer) val_peek(0));		}
break;
case 36:
//#line 193 "../../src/sintactico/sintactico.y"
{ 	yyval = new LiteralReal(lexico.getLine(), lexico.getColumn(), (Double) val_peek(0));	}
break;
case 37:
//#line 194 "../../src/sintactico/sintactico.y"
{ 	yyval = new LiteralCaracter(lexico.getLine(), lexico.getColumn(), (Character)val_peek(0));		}
break;
case 38:
//#line 195 "../../src/sintactico/sintactico.y"
{ 	yyval = new Aritmetica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "+", (Expresion) val_peek(0));		}
break;
case 39:
//#line 196 "../../src/sintactico/sintactico.y"
{ 	yyval = new Aritmetica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "*", (Expresion) val_peek(0));		}
break;
case 40:
//#line 197 "../../src/sintactico/sintactico.y"
{ 	yyval = new Aritmetica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "/", (Expresion) val_peek(0));		}
break;
case 41:
//#line 198 "../../src/sintactico/sintactico.y"
{ 	yyval = new Aritmetica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "-", (Expresion) val_peek(0));		}
break;
case 42:
//#line 199 "../../src/sintactico/sintactico.y"
{ 	yyval = new Aritmetica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "%", (Expresion) val_peek(0));		}
break;
case 43:
//#line 200 "../../src/sintactico/sintactico.y"
{ 	yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), ">", (Expresion) val_peek(0));	}
break;
case 44:
//#line 201 "../../src/sintactico/sintactico.y"
{ 	yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "<", (Expresion) val_peek(0));	}
break;
case 45:
//#line 202 "../../src/sintactico/sintactico.y"
{ 	yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), ">=", (Expresion) val_peek(0));	}
break;
case 46:
//#line 203 "../../src/sintactico/sintactico.y"
{ 	yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "<=", (Expresion) val_peek(0));	}
break;
case 47:
//#line 204 "../../src/sintactico/sintactico.y"
{ 	yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "!=", (Expresion) val_peek(0));	}
break;
case 48:
//#line 205 "../../src/sintactico/sintactico.y"
{ 	yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "==", (Expresion) val_peek(0));	}
break;
case 49:
//#line 206 "../../src/sintactico/sintactico.y"
{ 	yyval = new Logica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "&&", (Expresion) val_peek(0));	}
break;
case 50:
//#line 207 "../../src/sintactico/sintactico.y"
{ 	yyval = new Logica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "||", (Expresion) val_peek(0));	}
break;
case 51:
//#line 208 "../../src/sintactico/sintactico.y"
{ 	yyval = new Negacion(lexico.getLine(), lexico.getColumn(),  "!", (Expresion) val_peek(0));	}
break;
case 52:
//#line 209 "../../src/sintactico/sintactico.y"
{ 	yyval = new MenosUnario(lexico.getLine(), lexico.getColumn(),  "-",(Expresion) val_peek(0));	}
break;
case 53:
//#line 210 "../../src/sintactico/sintactico.y"
{ 	yyval = new AccesoCampo(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), (String) val_peek(0));	}
break;
case 54:
//#line 211 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(1);}
break;
case 55:
//#line 212 "../../src/sintactico/sintactico.y"
{ 	yyval = new Cast(lexico.getLine(), lexico.getColumn(), (Tipo) val_peek(2), (Expresion) val_peek(0));	}
break;
case 56:
//#line 213 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(0);}
break;
case 57:
//#line 214 "../../src/sintactico/sintactico.y"
{ 	yyval = new AccesoArray(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(3), (Expresion) val_peek(1)); }
break;
case 58:
//#line 217 "../../src/sintactico/sintactico.y"
{ 	yyval = new Invocacion(lexico.getLine(), lexico.getColumn(), new Variable(lexico.getLine(), lexico.getColumn(), (String)val_peek(3)),(List<Expresion>)val_peek(1)); 	}
break;
case 59:
//#line 220 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(0);}
break;
case 60:
//#line 221 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<Expresion>();	}
break;
case 61:
//#line 225 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(0); 
																											List<Integer> lista = (List<Integer>)yyval;
																											Integer elemento = (Integer)val_peek(2);
																											lista.add(elemento);
																										}
break;
case 62:
//#line 230 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<Integer>(); ((List<Integer>)yyval).add((Integer)val_peek(1)); 	}
break;
case 63:
//#line 233 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(2); 
																											List<String> lista = (List<String>)yyval;
																											String elemento = (String)val_peek(0);
																											if(!lista.contains(elemento))
																												lista.add(elemento);
																											else
																												new TipoError(lexico.getLine(), lexico.getColumn(),"Identificador duplicado -> " + elemento);																																																							
																										}
break;
case 64:
//#line 241 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList(); ((List<String>)yyval).add(((String) val_peek(0))); 	}
break;
case 65:
//#line 244 "../../src/sintactico/sintactico.y"
{ 	yyval = TipoEntero.getInstancia(); 	}
break;
case 66:
//#line 245 "../../src/sintactico/sintactico.y"
{ 	yyval = TipoReal.getInstancia(); 	}
break;
case 67:
//#line 246 "../../src/sintactico/sintactico.y"
{ 	yyval = TipoCaracter.getInstancia(); 	}
break;
//#line 1069 "Parser.java"
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
