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
   10,   10,   10,   10,   12,   12,   15,   15,    8,    8,
    8,   13,   13,   11,   11,   11,   11,   11,   11,   11,
   11,   11,   11,   11,   11,   11,   11,   11,   11,   11,
   11,   11,   11,   11,   11,   11,   14,   18,   18,   17,
   17,   16,   16,    5,    5,    5,
};
final static short yylen[] = {                            2,
    9,    2,    0,    9,    9,    2,    3,    0,    3,    1,
    0,    2,    1,    0,    2,    1,    4,    7,    5,    7,
    3,    3,    2,    3,    3,    1,    3,    2,    2,    3,
    5,    3,    1,    1,    1,    1,    1,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    2,    2,    3,    3,    4,    1,    4,    1,    0,    4,
    3,    3,    1,    1,    1,    1,
};
final static short yydefred[] = {                         3,
    0,    0,   64,   65,   66,    0,    0,    2,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    6,    0,    0,
    0,    0,    0,    0,    0,    0,   63,    0,    0,   28,
    0,    0,    0,    0,    0,   10,    0,   61,   62,    0,
    0,   27,    8,   12,    0,    0,    0,   60,    0,    8,
    9,    8,   35,   36,   37,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   16,    0,    0,    0,
    0,    0,    0,   56,    0,    0,    0,    0,    0,    0,
   51,   52,    0,    0,    1,   15,    7,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   23,    0,    0,    0,   22,    0,   21,    0,    0,
   24,   53,    0,    0,    0,   54,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   39,   40,   42,
    5,    4,    0,    0,    0,   57,   55,   17,    0,    0,
   26,    0,    0,    0,    0,   18,   25,   20,
};
final static short yydgoto[] = {                          1,
    2,   49,  103,    8,   19,   35,  104,   66,   36,   67,
   68,  142,   73,   74,   21,   16,   17,  114,
};
final static short yysindex[] = {                         0,
    0,  -77,    0,    0,    0, -111, -234,    0,  -90,  -41,
  -55,    2,   14,   21, -227,    4,  -88,    0,  -80,   33,
 -105,   39,  -47,  -47,   28, -158,    0, -142,    4,    0,
 -122,   92,   34, -114,  105,    0,  124,    0,    0,   79,
    4,    0,    0,    0,   53,  -47,   58,    0,   87,    0,
    0,    0,    0,    0,    0,   -5,   -5,  140,  146,   -5,
    1,   -5,   -5,  184,  103,  137,    0,  -28,  141,   87,
   87,   57,   59,    0,   95,   -5,   -5,  -16,   -5,   -5,
    0,    0,  156,    8,    0,    0,    0,   -5,   -5,   -5,
   -5,   -5,   -5,   -5,   -5,   -5,   -5,   -5,   -5,   -5,
   -5,    0,  211,   76,   80,    0,   -5,    0,   15,   22,
    0,    0,  160,  173,   -5,    0,   64,   64,  132,  132,
  132,  132,   29,  132,  132,  100,  100,    0,    0,    0,
    0,    0,   57,   99,  119,    0,    0,    0,  211,  211,
    0,  -49,  138,  154,  119,    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   97,    0,  164,    0,    0,    0,    0,
    0,    0,  129,  129,    0,    0,    0,    0,   33,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  166,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  -37,    0,    0,    0,    0,    0,    0,    0,   36,  101,
  101,   72,    0,    0,    0,    0,    0,    0,    0,  186,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  106,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  189,    0,    0,    0,   49,   94,  343,  403,
  447,  453,    0,  475,  481,  416,  441,    0,    0,    0,
    0,    0,   81,    0,    0,    0,    0,    0,    0,    0,
    0,  176,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,   35,  -10,    0,  121,  208,  163,   84,  197,  130,
  488,   90,  -44,   63,    0,  -15,    0,    0,
};
final static int YYTABLESIZE=759;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         34,
   15,   29,   28,   34,   34,   34,   34,   34,  101,   34,
   15,   11,   75,   99,   97,   41,   98,   18,  100,   31,
  101,   34,   34,   34,   34,   99,   97,   62,   98,   25,
  100,   96,   94,   95,   64,  113,   12,   13,   65,   63,
   80,   22,  111,   96,  101,   95,   79,   26,  116,   99,
   97,  101,   98,   23,  100,  134,   99,   97,  101,   98,
   24,  100,  135,   99,   97,  101,   98,   96,  100,   95,
   99,   97,   56,   98,   96,  100,   95,   56,   56,   33,
   56,   96,   56,   95,   70,   10,   71,  138,   96,   49,
   95,   30,   49,  101,   20,   56,   56,   56,   99,   97,
  101,   98,  107,  100,   32,   99,   97,   49,   98,   49,
  100,   69,   33,   39,   40,   33,   96,  106,   95,   62,
   38,   32,    9,   96,   32,   95,   64,   69,  143,  144,
   33,   63,   69,   69,   50,   62,  101,   50,  107,   32,
   63,   99,   64,   34,   34,   45,  100,   63,   46,   27,
   42,   62,   50,  108,   50,   63,   43,   44,   64,    3,
    4,    5,    6,   63,   47,   69,   34,   46,  101,   11,
   62,   48,   11,   99,   97,   50,   98,   64,  100,   76,
   52,   14,   63,   27,   83,   77,   62,    3,    4,    5,
    6,   27,    7,   64,   86,   87,  115,   69,   63,  102,
  131,   69,   69,  107,  132,   69,   69,   69,   19,    3,
    4,    5,    6,  136,  145,   19,   62,    3,    4,    5,
   19,  139,   29,   64,   31,   14,   59,   85,   63,   58,
   13,   37,   86,  105,  148,   34,   34,   34,   34,   34,
   34,  140,   51,   62,   88,   89,   90,   91,   92,   93,
   64,   53,   54,   55,    0,   63,   88,   89,   90,   91,
   92,   93,  146,    0,  141,    0,   61,    0,    0,    0,
    0,    0,   86,   86,  141,    0,    0,    0,  147,    0,
   88,   89,   90,   91,   92,   93,    0,   88,   89,   90,
   91,   92,   93,    0,   88,   89,   90,   91,   92,   93,
   19,   88,   89,   90,   91,   92,   93,    0,   56,   56,
   56,   56,   56,   56,    0,    0,    0,    0,    0,    0,
    0,   49,   49,    0,    0,    0,    0,    0,    0,   88,
   89,   90,   91,   92,   93,    0,    0,    0,   90,   91,
   92,   93,    0,   53,   54,   55,   56,   57,   58,   59,
    0,    3,    4,    5,    6,   60,    0,    0,   61,   53,
   54,   55,   56,   57,   58,   59,   50,   50,    0,    0,
    0,   60,    0,    0,   61,   53,   54,   55,   56,   57,
   58,   59,    0,   45,    0,    0,   45,   60,    0,    0,
   61,    0,    0,    0,   53,   54,   55,   56,   57,   58,
   59,   45,   45,   45,   45,    0,   60,    0,    0,   61,
   53,   54,   55,   56,   57,   58,   59,    0,    0,    0,
    0,    0,   60,    0,    0,   61,    0,    0,    0,    0,
    0,    0,   19,   19,   19,   19,   19,   19,   19,    0,
   53,   54,   55,   46,   19,    0,   46,   19,    3,    4,
    5,    0,    0,    0,    0,   61,   38,    0,   38,   38,
   38,   46,   46,   46,   46,    0,    0,   53,   54,   55,
   56,   57,   58,   59,   38,   38,   38,   38,    0,   60,
    0,   41,   61,   41,   41,   41,    0,   47,    0,    0,
   47,    0,    0,   48,    0,    0,   48,    0,    0,   41,
   41,   41,   41,    0,    0,   47,   47,   47,   47,    0,
    0,   48,   48,   48,   48,   43,    0,    0,   43,    0,
    0,   44,    0,    0,   44,    0,    0,    0,    0,    0,
    0,    0,    0,   43,   43,   43,   43,    0,    0,   44,
   44,   44,   44,   72,   72,    0,    0,   78,    0,   81,
   82,   84,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  109,  110,    0,  112,   72,    0,    0,
    0,    0,    0,    0,    0,  117,  118,  119,  120,  121,
  122,  123,  124,  125,  126,  127,  128,  129,  130,    0,
    0,    0,    0,    0,  133,    0,    0,    0,    0,    0,
    0,    0,  137,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   45,   45,   45,   45,   45,
   45,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   46,   46,   46,   46,   46,
   46,    0,    0,    0,    0,    0,    0,    0,   38,   38,
   38,   38,   38,   38,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   41,   41,   41,   41,   41,   41,   47,
   47,   47,   47,   47,   47,   48,   48,   48,   48,   48,
   48,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   43,   43,   43,
   43,   43,   43,   44,   44,   44,   44,   44,   44,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
   91,   17,   91,   41,   42,   43,   44,   45,   37,   47,
   91,  123,   57,   42,   43,   31,   45,   59,   47,  125,
   37,   59,   60,   61,   62,   42,   43,   33,   45,  257,
   47,   60,   61,   62,   40,   80,  271,  272,   49,   45,
   40,   40,   59,   60,   37,   62,   46,   44,   41,   42,
   43,   37,   45,   40,   47,   41,   42,   43,   37,   45,
   40,   47,   41,   42,   43,   37,   45,   60,   47,   62,
   42,   43,   37,   45,   60,   47,   62,   42,   43,   41,
   45,   60,   47,   62,   50,    2,   52,   59,   60,   41,
   62,   59,   44,   37,   11,   60,   61,   62,   42,   43,
   37,   45,   44,   47,   21,   42,   43,   59,   45,   61,
   47,   49,   41,  272,  257,   44,   60,   59,   62,   33,
   93,   41,    2,   60,   44,   62,   40,   65,  139,  140,
   59,   45,   70,   71,   41,   33,   37,   44,   44,   59,
   44,   42,   40,   23,   24,   41,   47,   45,   44,  272,
   59,   33,   59,   59,   61,   59,  123,  272,   40,  265,
  266,  267,  268,   45,   41,  103,   46,   44,   37,   41,
   33,   93,   44,   42,   43,  123,   45,   40,   47,   40,
  123,  272,   45,  272,   64,   40,   33,  265,  266,  267,
  268,  272,  270,   40,   65,   59,   41,  135,   45,   59,
  125,  139,  140,   44,  125,  143,  144,  145,   33,  265,
  266,  267,  268,   41,  264,   40,   33,  265,  266,  267,
   45,  123,   59,   40,   59,  125,   41,  125,   45,   41,
  125,   24,  103,   71,  145,  273,  274,  275,  276,  277,
  278,  123,   46,   33,  273,  274,  275,  276,  277,  278,
   40,  257,  258,  259,   -1,   45,  273,  274,  275,  276,
  277,  278,  125,   -1,  135,   -1,  272,   -1,   -1,   -1,
   -1,   -1,  143,  144,  145,   -1,   -1,   -1,  125,   -1,
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
   -1,   41,   -1,   -1,   44,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   59,   60,   61,   62,   -1,   -1,   59,
   60,   61,   62,   56,   57,   -1,   -1,   60,   -1,   62,
   63,   64,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   76,   77,   -1,   79,   80,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   88,   89,   90,   91,   92,
   93,   94,   95,   96,   97,   98,   99,  100,  101,   -1,
   -1,   -1,   -1,   -1,  107,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  115,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
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
"parametros : parametros ',' definicioVariable",
"parametros : definicioVariable",
"parametros :",
"definicioVariable : tipoSimple ID",
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

//#line 201 "../../src/sintactico/sintactico.y"

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
//#line 502 "Parser.java"
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
//#line 40 "../../src/sintactico/sintactico.y"
{ 	List<Definicion> definiciones = (List<Definicion>)val_peek(8);
																						
																						Definicion main = new DefFuncion(lexico.getLine(), lexico.getColumn(), "main",
																							new TipoFuncion(new ArrayList(), TipoVoid.getInstancia()), 
																							(List<DefVariable>)val_peek(2), (List<Sentencia>)val_peek(1));
																						definiciones.add(main);																						
																						this.ast = new Programa(lexico.getLine(), lexico.getColumn(), definiciones); 
																					}
break;
case 2:
//#line 48 "../../src/sintactico/sintactico.y"
{	yyval = val_peek(1); for(Definicion aux : (List<Definicion>)val_peek(0)) 
																									((List<Definicion>)yyval).add(aux); 
																					}
break;
case 3:
//#line 51 "../../src/sintactico/sintactico.y"
{	yyval = new ArrayList(); }
break;
case 4:
//#line 54 "../../src/sintactico/sintactico.y"
{ 	List<Definicion> lista = new ArrayList();
																										lista.add(new DefFuncion(lexico.getLine(), lexico.getColumn(), (String) val_peek(7).toString(),
																											new TipoFuncion((List<DefVariable>)val_peek(5), (Tipo)val_peek(8)), 
																											(List<DefVariable>)val_peek(2), (List<Sentencia>)val_peek(1)));
																										yyval = lista; 
																									}
break;
case 5:
//#line 60 "../../src/sintactico/sintactico.y"
{	List<Definicion> lista = new ArrayList();
																										lista.add(new DefFuncion(lexico.getLine(), lexico.getColumn(),  (String) val_peek(7).toString(),
																										new TipoFuncion((List<DefVariable>)val_peek(5), TipoVoid.getInstancia()), 
																										(List<DefVariable>)val_peek(2), (List<Sentencia>)val_peek(1)));
																										yyval = lista;
																									}
break;
case 6:
//#line 66 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(1);  }
break;
case 7:
//#line 69 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(2);
															for(DefVariable aux : (List<DefVariable>)val_peek(1)) 
																((List<DefVariable>)yyval).add(aux); }
break;
case 8:
//#line 72 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<DefVariable>();}
break;
case 9:
//#line 75 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(2); 
												  for(DefVariable aux : (List<DefVariable>) val_peek(0)){
													((List<DefVariable>)yyval).add(aux); 
													}
												}
break;
case 10:
//#line 80 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0); }
break;
case 11:
//#line 81 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<DefVariable>();}
break;
case 12:
//#line 84 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<>();
												  ((List<DefVariable>) yyval).add(new DefVariable(lexico.getLine(), 
		  										  lexico.getColumn(), (String)val_peek(0).toString(), (Tipo)val_peek(1)));  }
break;
case 13:
//#line 89 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0);}
break;
case 14:
//#line 90 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<Sentencia>();}
break;
case 15:
//#line 93 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(1);((List<Sentencia>)yyval).add((Sentencia)val_peek(0)); }
break;
case 16:
//#line 94 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<Sentencia>(); ((List<Sentencia>)yyval).add((Sentencia)val_peek(0)); }
break;
case 18:
//#line 98 "../../src/sintactico/sintactico.y"
{ yyval = new SentenciaWhile(lexico.getLine(), lexico.getColumn(), (Expresion)val_peek(4), (List<Sentencia>)val_peek(1));}
break;
case 19:
//#line 99 "../../src/sintactico/sintactico.y"
{ yyval = new SentenciaIf(lexico.getLine(), lexico.getColumn(), (Expresion)val_peek(2), (List<Sentencia>)val_peek(0), new ArrayList()); }
break;
case 20:
//#line 100 "../../src/sintactico/sintactico.y"
{ yyval = new SentenciaIf(lexico.getLine(), lexico.getColumn(), (Expresion)val_peek(4), (List<Sentencia>)val_peek(2), (List<Sentencia>)val_peek(0));}
break;
case 21:
//#line 101 "../../src/sintactico/sintactico.y"
{ yyval = new Escritura(lexico.getLine(), lexico.getColumn(),(List<Expresion>)val_peek(1));}
break;
case 22:
//#line 102 "../../src/sintactico/sintactico.y"
{ yyval = new Lectura(lexico.getLine(), lexico.getColumn(),(List<Expresion>)val_peek(1)); }
break;
case 23:
//#line 103 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(1); }
break;
case 24:
//#line 104 "../../src/sintactico/sintactico.y"
{ yyval = new Return(lexico.getLine(), lexico.getColumn(), (Expresion)val_peek(1));}
break;
case 25:
//#line 107 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(1); }
break;
case 26:
//#line 108 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<Sentencia>(); ((List<Sentencia>)yyval).add((Sentencia)val_peek(0));  }
break;
case 27:
//#line 112 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(2);
													for(DefVariable aux : (List<DefVariable>)val_peek(1)) 
														((List<DefVariable>)yyval).add(aux); }
break;
case 28:
//#line 115 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(1);  }
break;
case 29:
//#line 118 "../../src/sintactico/sintactico.y"
{ 	List<DefVariable> variables = new ArrayList();
																for(String aux: (List<String>)val_peek(0)){
																	variables.add(new DefVariable(lexico.getLine(), lexico.getColumn(), aux, (Tipo)val_peek(1)));
															   }	 
															   yyval = variables; 
															}
break;
case 30:
//#line 124 "../../src/sintactico/sintactico.y"
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
case 31:
//#line 136 "../../src/sintactico/sintactico.y"
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
case 32:
//#line 151 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(2); ((List<Expresion>)yyval).add((Expresion)val_peek(0)); }
break;
case 33:
//#line 152 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<Expresion>(); ((List<Expresion>)yyval).add((Expresion)val_peek(0));  }
break;
case 34:
//#line 155 "../../src/sintactico/sintactico.y"
{ yyval = new Variable(lexico.getLine(), lexico.getColumn(), (String) val_peek(0).toString()); }
break;
case 35:
//#line 156 "../../src/sintactico/sintactico.y"
{ yyval = new LiteralEntero(lexico.getLine(), lexico.getColumn(), (Integer) val_peek(0));}
break;
case 36:
//#line 157 "../../src/sintactico/sintactico.y"
{ yyval = new LiteralReal(lexico.getLine(), lexico.getColumn(), (Double) val_peek(0));}
break;
case 37:
//#line 158 "../../src/sintactico/sintactico.y"
{ yyval = new LiteralCaracter(lexico.getLine(), lexico.getColumn(), (char) val_peek(0));}
break;
case 38:
//#line 159 "../../src/sintactico/sintactico.y"
{ yyval = new Aritmetica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "+", (Expresion) val_peek(0));}
break;
case 39:
//#line 160 "../../src/sintactico/sintactico.y"
{ yyval = new Aritmetica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "*", (Expresion) val_peek(0));}
break;
case 40:
//#line 161 "../../src/sintactico/sintactico.y"
{ yyval = new Aritmetica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "/", (Expresion) val_peek(0));}
break;
case 41:
//#line 162 "../../src/sintactico/sintactico.y"
{ yyval = new Aritmetica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "-", (Expresion) val_peek(0));}
break;
case 42:
//#line 163 "../../src/sintactico/sintactico.y"
{ yyval = new Aritmetica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "%", (Expresion) val_peek(0));}
break;
case 43:
//#line 164 "../../src/sintactico/sintactico.y"
{ yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), ">", (Expresion) val_peek(0));}
break;
case 44:
//#line 165 "../../src/sintactico/sintactico.y"
{ yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "<", (Expresion) val_peek(0));}
break;
case 45:
//#line 166 "../../src/sintactico/sintactico.y"
{ yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), ">=", (Expresion) val_peek(0));}
break;
case 46:
//#line 167 "../../src/sintactico/sintactico.y"
{ yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "<=", (Expresion) val_peek(0));}
break;
case 47:
//#line 168 "../../src/sintactico/sintactico.y"
{ yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "!=", (Expresion) val_peek(0));}
break;
case 48:
//#line 169 "../../src/sintactico/sintactico.y"
{ yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "==", (Expresion) val_peek(0));}
break;
case 49:
//#line 170 "../../src/sintactico/sintactico.y"
{ yyval = new Logica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "&&", (Expresion) val_peek(0));}
break;
case 50:
//#line 171 "../../src/sintactico/sintactico.y"
{ yyval = new Logica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "||", (Expresion) val_peek(0));}
break;
case 51:
//#line 172 "../../src/sintactico/sintactico.y"
{ yyval = new Negacion(lexico.getLine(), lexico.getColumn(),  "!", (Expresion) val_peek(1));}
break;
case 52:
//#line 173 "../../src/sintactico/sintactico.y"
{ yyval = new MenosUnario(lexico.getLine(), lexico.getColumn(),  "-",(Expresion) val_peek(1));}
break;
case 53:
//#line 174 "../../src/sintactico/sintactico.y"
{ yyval = new AccesoCampo(lexico.getLine(), lexico.getColumn(), (String) val_peek(2), (Expresion) val_peek(0));}
break;
case 54:
//#line 175 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(1);}
break;
case 55:
//#line 176 "../../src/sintactico/sintactico.y"
{ yyval = new Cast(lexico.getLine(), lexico.getColumn(), (Tipo) val_peek(2), (Expresion) val_peek(0));}
break;
case 56:
//#line 177 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0);}
break;
case 57:
//#line 180 "../../src/sintactico/sintactico.y"
{ yyval = new Invocacion(lexico.getLine(), lexico.getColumn(), new Variable(lexico.getLine(), lexico.getColumn(), (String)val_peek(3).toString()),(List<Expresion>)val_peek(1)); }
break;
case 58:
//#line 183 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0);}
break;
case 59:
//#line 184 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<Expresion>();}
break;
case 60:
//#line 188 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(3); ((List<Integer>)yyval).add((int)val_peek(1)); }
break;
case 61:
//#line 189 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<Integer>(); ((List<Integer>)yyval).add((Integer)val_peek(1)); }
break;
case 62:
//#line 192 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(2); ((List<String>)yyval).add((String)val_peek(0).toString()); }
break;
case 63:
//#line 193 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList(); String st = (String) val_peek(0).toString(); ((List<String>)yyval).add((st)); }
break;
case 64:
//#line 196 "../../src/sintactico/sintactico.y"
{ yyval = TipoEntero.getInstancia(); }
break;
case 65:
//#line 197 "../../src/sintactico/sintactico.y"
{ yyval = TipoReal.getInstancia(); }
break;
case 66:
//#line 198 "../../src/sintactico/sintactico.y"
{ yyval = TipoCaracter.getInstancia(); }
break;
//#line 968 "Parser.java"
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
