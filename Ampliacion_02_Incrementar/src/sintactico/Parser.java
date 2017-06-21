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
public final static short INCREMENTAR=279;
public final static short MENOS_UNARIO=280;
public final static short NEGACION=281;
public final static short CASTP=282;
public final static short MENORQUEELSE=283;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    4,    4,    4,    2,    2,    6,    6,
    6,    9,    7,    7,    3,    3,   10,   10,   10,   10,
   10,   10,   10,   10,   10,   12,   12,    8,    8,    8,
    8,   17,   17,   13,   13,   11,   11,   11,   11,   11,
   11,   11,   11,   11,   11,   11,   11,   11,   11,   11,
   11,   11,   11,   11,   11,   11,   11,   11,   11,   14,
   18,   18,   16,   16,   15,   15,    5,    5,    5,
};
final static short yylen[] = {                            2,
    9,    2,    0,    9,    9,    2,    3,    0,    3,    1,
    0,    2,    1,    0,    2,    1,    4,    5,    5,    7,
    3,    3,    2,    3,    3,    3,    1,    2,    3,    5,
    6,    3,    2,    3,    1,    1,    1,    1,    1,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    2,    2,    3,    3,    4,    1,    4,    4,
    1,    0,    4,    3,    3,    1,    1,    1,    1,
};
final static short yydefred[] = {                         3,
    0,    0,   67,   68,   69,    0,    0,    2,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    6,    0,    0,
    0,    0,    0,    0,    0,    0,   66,    0,   33,    0,
    0,    0,    0,    0,   10,    0,    0,   65,    0,    0,
   32,    8,   12,    0,    0,    0,   63,    0,    0,    8,
    9,    8,   37,   38,   39,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   16,    0,    0,    0,
    0,    0,    0,   58,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    1,   15,    7,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   23,    0,    0,    0,   22,    0,   21,
    0,    0,   24,    0,    0,    0,   56,    0,    0,    0,
    0,    0,    0,   25,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   55,    5,    4,    0,    0,    0,   60,
    0,   17,   59,    0,   27,   18,    0,    0,    0,   26,
   20,
};
final static short yydgoto[] = {                          1,
    2,   49,  105,    8,   19,   34,  106,   66,   35,   67,
   68,  146,   73,   74,   16,   17,   21,  115,
};
final static short yysindex[] = {                         0,
    0, -120,    0,    0,    0,  -71, -180,    0,  -79,    6,
 -114,   19,   44,   61, -147,   67, -153,    0,  -78,   69,
   16,  108, -104, -104,   63, -101,    0,   67,    0,  -78,
  115,   57,  -86,   94,    0,  137,   96,    0,   67, -153,
    0,    0,    0,   68, -104,   73,    0,   67,  284,    0,
    0,    0,    0,    0,    0,  -13,  -13,  161,  162,  -13,
  178,  -13,  515,  -13,  349,  144,    0,   97,  167,  284,
  284,  413,  -30,    0,   31,  -13,  -13,  130,  -13,  -44,
  179,  163,  -44,    0,    0,    0,  -13,  -13,  -13,  -13,
  -13,  -13,  168,  -13,  -13,  -13,  -13,  -13,  -13,  -13,
  -13,  -13,  -50,    0,  488,  109,  110,    0,  -13,    0,
  170,  225,    0,  189,  202,  -13,    0,  425,  425,  251,
  251,  251,  251,    0,  318,  251,  251,  182,  182,  -44,
  -44,  -44,  373,    0,    0,    0,  413,  409,  409,    0,
  -44,    0,    0,  488,    0,    0,   -9,  436,  409,    0,
    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   58,    0,  197,    0,    0,    0,    0,
    0,    0,  154,  154,    0,    0,    0,   69,    0,    0,
    0,    0,    0,    0,    0,    0,  -15,    0,  199,    0,
    0,    0,    0,    0,    0,    0,    0,  201,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  -37,    0,    0,    0,    0,    0,    0,    0,  123,  139,
  139,   -4,    0,    0,    0,    0,    0,    0,  222,  -26,
    0,    0,    1,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  140,    0,    0,    0,    0,    0,
    0,    0,    0,  228,    0,    0,    0,  138,  614,  543,
  572,  581,  717,    0,    0,  742,  768,  447,  521,   27,
   36,   62,    0,    0,    0,    0,   41,    0,    0,    0,
   71,    0,    0,    0,    0,    0,  472,    0,    0,    0,
    0,
};
final static short yygindex[] = {                         0,
    0,  -49,  -19,    0,  491,  262,  218,   55,  245,  -12,
  856, -108,  -29,  766,    9,   21,    0,    0,
};
final static int YYTABLESIZE=1047;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         36,
   70,  103,   71,   36,   36,   36,   36,   36,   36,   36,
   54,   15,   15,  109,   54,   54,   54,   54,   54,   64,
   54,   36,   36,   36,   36,   28,   63,   75,  108,   65,
  147,   62,   54,   54,   54,   54,   35,   53,   39,   35,
  151,   53,   53,   53,   53,   53,  102,   53,   48,  114,
   40,   11,   85,   36,   35,   36,   10,   47,   22,   53,
   53,   53,   53,   41,   18,   20,   54,   41,   41,   41,
   41,   41,   42,   41,  109,   31,   42,   42,   42,   42,
   42,   34,   42,   23,   34,   41,   41,   41,   41,  110,
   12,   13,   85,   53,   42,   42,   42,   42,   44,   34,
   24,   66,   44,   44,   44,   44,   44,   57,   44,   25,
   26,   57,   57,   57,   57,   57,   66,   57,   27,   41,
   44,   44,   44,   44,  148,  145,  145,   29,   42,   57,
   57,   57,   57,  101,   44,   85,  145,   45,   99,   97,
   30,   98,  103,  100,    3,    4,    5,    6,   32,    7,
    3,    4,    5,    6,   44,   37,   96,   94,   95,   58,
    3,    4,    5,   57,   58,   58,  101,   58,   58,   58,
   38,   99,   97,   41,   98,  103,  100,   46,   51,   42,
   45,   51,   58,   58,   58,   43,   15,  102,  113,   96,
   50,   95,   14,   27,   11,   52,   51,   11,   51,  101,
   76,   77,   86,  117,   99,   97,  101,   98,  103,  100,
  138,   99,   97,   58,   98,  103,  100,   79,  101,  116,
  102,  134,   96,   99,   95,  104,  124,  103,  100,   96,
   51,   95,  109,  135,  136,   36,   36,   36,   36,   36,
   36,   36,  140,   53,   54,   55,   54,   54,   54,   54,
   54,   54,   54,  102,  149,   28,   64,   30,   61,   31,
  102,  101,   62,   14,   13,  139,   99,   97,   61,   98,
  103,  100,  102,   53,   53,   53,   53,   53,   53,   53,
    3,    4,    5,    6,   96,   36,   95,  101,  107,   51,
    0,    0,   99,   97,    0,   98,  103,  100,    0,   41,
   41,   41,   41,   41,   41,   41,    0,    0,   42,   42,
   42,   42,   42,   42,   42,  102,   64,    0,    0,    0,
    0,    0,    0,   63,    0,    0,    0,    0,   62,    0,
    0,    0,    0,    0,   44,   44,   44,   44,   44,   44,
   44,  102,    0,   57,   57,   57,   57,   57,   57,   57,
    0,    0,    0,    0,  101,    0,    0,    0,    0,   99,
   97,    0,   98,  103,  100,    0,    0,    0,    0,   87,
   88,   89,   90,   91,   92,   93,  142,   96,    0,   95,
    0,   64,    0,    0,    0,    0,    0,    0,   63,    0,
    0,    0,    0,   62,    0,   58,   58,   58,   58,   58,
   58,   58,   87,   88,   89,   90,   91,   92,  102,  101,
   51,   51,    0,    0,   99,   97,   51,   98,  103,  100,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   96,    0,   95,   87,   88,   89,   90,   91,
   92,   64,   87,   88,   89,   90,   91,   92,   63,  101,
    0,    0,    0,   62,   99,   97,    0,   98,  103,  100,
    0,  101,    0,  102,    0,  143,   99,   97,   64,   98,
  103,  100,   96,   84,   95,   63,    0,    0,    0,    0,
   62,    0,    0,    0,   96,    0,   95,   40,    0,   40,
   40,   40,    9,    0,    0,    0,    0,   87,   88,   89,
   90,   91,   92,  102,   19,   40,   40,   40,   40,    0,
    0,   19,    0,   33,   33,  102,   19,    0,    0,    0,
   64,    0,    0,    0,    0,    0,    0,   63,    0,    0,
    0,  144,   62,    0,    0,   33,    0,    0,    0,   40,
   53,   54,   55,   56,   57,   58,   59,   64,    3,    4,
    5,    6,   60,   81,   63,   61,    0,    0,    0,   62,
  150,   43,    0,   43,   43,   43,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   43,
   43,   43,   43,   47,    0,    0,   47,    0,    0,    0,
   87,   88,   89,   90,   91,   92,   19,    0,    0,    0,
    0,   47,   47,   47,   47,   53,   54,   55,   56,   57,
   58,   59,   48,   43,    0,   48,    0,   60,    0,    0,
   61,   49,    0,    0,   49,    0,    0,    0,    0,    0,
   48,   48,   48,   48,    0,   47,    0,    0,    0,   49,
   49,   49,   49,    0,    0,   87,   88,   89,   90,   91,
   92,    0,    0,    0,   52,    0,    0,   52,    0,    0,
    0,    0,    0,    0,   48,   53,   54,   55,   56,   57,
   58,   59,   52,   49,   52,    0,    0,   60,    0,    0,
   61,    0,    0,    0,    0,   87,   88,   89,   90,   91,
   92,    0,   53,   54,   55,   56,   57,   58,   59,   89,
   90,   91,   92,    0,   60,    0,   52,   61,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   40,
   40,   40,   40,   40,   40,   40,    0,    0,   19,   19,
   19,   19,   19,   19,   19,    0,    0,    0,    0,    0,
   19,    0,    0,   19,   53,   54,   55,   56,   57,   58,
   59,    0,    0,    0,    0,    0,   60,   50,    0,   61,
   50,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   53,   54,   55,    0,   50,   50,   50,   50,    3,
    4,    5,   45,    0,    0,   45,   61,    0,    0,    0,
    0,    0,    0,   43,   43,   43,   43,   43,   43,   43,
   45,   45,   45,   45,    0,    0,    0,    0,   46,   50,
    0,   46,    0,    0,   69,   47,   47,   47,   47,   47,
   47,   47,    0,    0,    0,    0,   46,   46,   46,   46,
   69,    0,    0,    0,   45,   69,   69,    0,    0,    0,
    0,    0,    0,    0,   48,   48,   48,   48,   48,   48,
   48,    0,    0,   49,   49,   49,   49,   49,   49,   49,
   46,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   69,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   52,   52,    0,    0,
    0,    0,   52,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   69,   69,    0,    0,    0,    0,   69,
    0,   72,   72,   69,   69,   78,    0,   80,   82,   83,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  111,  112,    0,   72,    0,    0,    0,    0,    0,
    0,    0,  118,  119,  120,  121,  122,  123,    0,  125,
  126,  127,  128,  129,  130,  131,  132,  133,    0,    0,
    0,    0,    0,    0,  137,    0,    0,    0,    0,    0,
    0,  141,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   50,
   50,   50,   50,   50,   50,   50,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   45,   45,   45,   45,   45,   45,
   45,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   46,   46,   46,   46,   46,   46,   46,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
   50,   46,   52,   41,   42,   43,   44,   45,   46,   47,
   37,   91,   91,   44,   41,   42,   43,   44,   45,   33,
   47,   59,   60,   61,   62,   17,   40,   57,   59,   49,
  139,   45,   59,   60,   61,   62,   41,   37,   30,   44,
  149,   41,   42,   43,   44,   45,   91,   47,   40,   79,
   30,  123,   65,   91,   59,   93,    2,   37,   40,   59,
   60,   61,   62,   37,   59,   11,   93,   41,   42,   43,
   44,   45,   37,   47,   44,   21,   41,   42,   43,   44,
   45,   41,   47,   40,   44,   59,   60,   61,   62,   59,
  271,  272,  105,   93,   59,   60,   61,   62,   37,   59,
   40,   44,   41,   42,   43,   44,   45,   37,   47,  257,
   44,   41,   42,   43,   44,   45,   59,   47,  272,   93,
   59,   60,   61,   62,  144,  138,  139,   59,   93,   59,
   60,   61,   62,   37,   41,  148,  149,   44,   42,   43,
  125,   45,   46,   47,  265,  266,  267,  268,   41,  270,
  265,  266,  267,  268,   93,   93,   60,   61,   62,   37,
  265,  266,  267,   93,   42,   43,   37,   45,   46,   47,
  272,   42,   43,   59,   45,   46,   47,   41,   41,  123,
   44,   44,   60,   61,   62,  272,   91,   91,   59,   60,
  123,   62,  272,  272,   41,  123,   59,   44,   61,   37,
   40,   40,   59,   41,   42,   43,   37,   45,   46,   47,
   41,   42,   43,   91,   45,   46,   47,   40,   37,   41,
   91,  272,   60,   42,   62,   59,   59,   46,   47,   60,
   93,   62,   44,  125,  125,  273,  274,  275,  276,  277,
  278,  279,   41,  257,  258,  259,  273,  274,  275,  276,
  277,  278,  279,   91,  264,   59,  272,   59,  272,   59,
   91,   37,   41,  125,  125,   41,   42,   43,   41,   45,
   46,   47,   91,  273,  274,  275,  276,  277,  278,  279,
  265,  266,  267,  268,   60,   24,   62,   37,   71,   45,
   -1,   -1,   42,   43,   -1,   45,   46,   47,   -1,  273,
  274,  275,  276,  277,  278,  279,   -1,   -1,  273,  274,
  275,  276,  277,  278,  279,   91,   33,   -1,   -1,   -1,
   -1,   -1,   -1,   40,   -1,   -1,   -1,   -1,   45,   -1,
   -1,   -1,   -1,   -1,  273,  274,  275,  276,  277,  278,
  279,   91,   -1,  273,  274,  275,  276,  277,  278,  279,
   -1,   -1,   -1,   -1,   37,   -1,   -1,   -1,   -1,   42,
   43,   -1,   45,   46,   47,   -1,   -1,   -1,   -1,  273,
  274,  275,  276,  277,  278,  279,   59,   60,   -1,   62,
   -1,   33,   -1,   -1,   -1,   -1,   -1,   -1,   40,   -1,
   -1,   -1,   -1,   45,   -1,  273,  274,  275,  276,  277,
  278,  279,  273,  274,  275,  276,  277,  278,   91,   37,
  273,  274,   -1,   -1,   42,   43,  279,   45,   46,   47,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   60,   -1,   62,  273,  274,  275,  276,  277,
  278,   33,  273,  274,  275,  276,  277,  278,   40,   37,
   -1,   -1,   -1,   45,   42,   43,   -1,   45,   46,   47,
   -1,   37,   -1,   91,   -1,   93,   42,   43,   33,   45,
   46,   47,   60,  125,   62,   40,   -1,   -1,   -1,   -1,
   45,   -1,   -1,   -1,   60,   -1,   62,   41,   -1,   43,
   44,   45,    2,   -1,   -1,   -1,   -1,  273,  274,  275,
  276,  277,  278,   91,   33,   59,   60,   61,   62,   -1,
   -1,   40,   -1,   23,   24,   91,   45,   -1,   -1,   -1,
   33,   -1,   -1,   -1,   -1,   -1,   -1,   40,   -1,   -1,
   -1,  123,   45,   -1,   -1,   45,   -1,   -1,   -1,   93,
  257,  258,  259,  260,  261,  262,  263,   33,  265,  266,
  267,  268,  269,   63,   40,  272,   -1,   -1,   -1,   45,
  125,   41,   -1,   43,   44,   45,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   59,
   60,   61,   62,   41,   -1,   -1,   44,   -1,   -1,   -1,
  273,  274,  275,  276,  277,  278,  125,   -1,   -1,   -1,
   -1,   59,   60,   61,   62,  257,  258,  259,  260,  261,
  262,  263,   41,   93,   -1,   44,   -1,  269,   -1,   -1,
  272,   41,   -1,   -1,   44,   -1,   -1,   -1,   -1,   -1,
   59,   60,   61,   62,   -1,   93,   -1,   -1,   -1,   59,
   60,   61,   62,   -1,   -1,  273,  274,  275,  276,  277,
  278,   -1,   -1,   -1,   41,   -1,   -1,   44,   -1,   -1,
   -1,   -1,   -1,   -1,   93,  257,  258,  259,  260,  261,
  262,  263,   59,   93,   61,   -1,   -1,  269,   -1,   -1,
  272,   -1,   -1,   -1,   -1,  273,  274,  275,  276,  277,
  278,   -1,  257,  258,  259,  260,  261,  262,  263,  275,
  276,  277,  278,   -1,  269,   -1,   93,  272,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  273,
  274,  275,  276,  277,  278,  279,   -1,   -1,  257,  258,
  259,  260,  261,  262,  263,   -1,   -1,   -1,   -1,   -1,
  269,   -1,   -1,  272,  257,  258,  259,  260,  261,  262,
  263,   -1,   -1,   -1,   -1,   -1,  269,   41,   -1,  272,
   44,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  257,  258,  259,   -1,   59,   60,   61,   62,  265,
  266,  267,   41,   -1,   -1,   44,  272,   -1,   -1,   -1,
   -1,   -1,   -1,  273,  274,  275,  276,  277,  278,  279,
   59,   60,   61,   62,   -1,   -1,   -1,   -1,   41,   93,
   -1,   44,   -1,   -1,   49,  273,  274,  275,  276,  277,
  278,  279,   -1,   -1,   -1,   -1,   59,   60,   61,   62,
   65,   -1,   -1,   -1,   93,   70,   71,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  273,  274,  275,  276,  277,  278,
  279,   -1,   -1,  273,  274,  275,  276,  277,  278,  279,
   93,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  105,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  273,  274,   -1,   -1,
   -1,   -1,  279,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  138,  139,   -1,   -1,   -1,   -1,  144,
   -1,   56,   57,  148,  149,   60,   -1,   62,   63,   64,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   76,   77,   -1,   79,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   87,   88,   89,   90,   91,   92,   -1,   94,
   95,   96,   97,   98,   99,  100,  101,  102,   -1,   -1,
   -1,   -1,   -1,   -1,  109,   -1,   -1,   -1,   -1,   -1,
   -1,  116,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  273,
  274,  275,  276,  277,  278,  279,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  273,  274,  275,  276,  277,  278,
  279,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  273,  274,  275,  276,  277,  278,  279,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=283;
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
"DISTINTO","IGUALDAD","INCREMENTAR","MENOS_UNARIO","NEGACION","CASTP",
"MENORQUEELSE",
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
"sentencia : expresion INCREMENTAR ';'",
"cuerpoCondicional : '{' sentencias '}'",
"cuerpoCondicional : sentencia",
"declaracionVariable : tipoSimple identificador",
"declaracionVariable : tipoSimple indices identificador",
"declaracionVariable : STRUCT '{' campos '}' identificador",
"declaracionVariable : STRUCT '{' campos '}' indices identificador",
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

//#line 248 "../../src/sintactico/sintactico.y"

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
//#line 569 "Parser.java"
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
//#line 45 "../../src/sintactico/sintactico.y"
{ 	List<Definicion> definiciones = (List<Definicion>)val_peek(8);																						
																											Definicion main = new DefFuncion(lexico.getLine(), lexico.getColumn(), "main",
																												new TipoFuncion(new ArrayList<DefVariable>(), TipoVoid.getInstancia()), 
																												(List<DefVariable>)val_peek(2), (List<Sentencia>)val_peek(1));
																											definiciones.add(main);
																																												
																											this.ast = new Programa(lexico.getLine(), lexico.getColumn(), definiciones); 
																										}
break;
case 2:
//#line 53 "../../src/sintactico/sintactico.y"
{	yyval = val_peek(1); 
																											List<Definicion> lista = (List<Definicion>)yyval;																											
																											List<Definicion> listaDefiniciones = (List<Definicion>)val_peek(0);
																											for(int i = 0; i < listaDefiniciones.size(); i++) {
																												Definicion elemento = listaDefiniciones.get(i);
																											    lista.add(elemento);																
																											}
																										}
break;
case 3:
//#line 61 "../../src/sintactico/sintactico.y"
{	yyval = new ArrayList<Definicion>(); }
break;
case 4:
//#line 64 "../../src/sintactico/sintactico.y"
{ 	List<Definicion> lista = new ArrayList();
																											lista.add(new DefFuncion(lexico.getLine(), lexico.getColumn(), (String) val_peek(7),
																												new TipoFuncion((List<DefVariable>)val_peek(5), (Tipo)val_peek(8)), 
																												(List<DefVariable>)val_peek(2), (List<Sentencia>)val_peek(1)));
																											yyval = lista; 
																										}
break;
case 5:
//#line 70 "../../src/sintactico/sintactico.y"
{	List<Definicion> lista = new ArrayList();
																											lista.add(new DefFuncion(lexico.getLine(), lexico.getColumn(),  (String) val_peek(7),
																												new TipoFuncion((List<DefVariable>)val_peek(5), TipoVoid.getInstancia()), 
																												(List<DefVariable>)val_peek(2), (List<Sentencia>)val_peek(1)));
																											yyval = lista;
																										}
break;
case 6:
//#line 76 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(1);  }
break;
case 7:
//#line 79 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(2);
																											List<DefVariable> lista = (List<DefVariable>)yyval;																											
																											for(DefVariable elemento : (List<DefVariable>)val_peek(1))
																												lista.add(elemento); 
																										}
break;
case 8:
//#line 84 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<DefVariable>();}
break;
case 9:
//#line 87 "../../src/sintactico/sintactico.y"
{	yyval = val_peek(2); 
																											List<DefVariable> lista = (List<DefVariable>)yyval;																												
																											for(DefVariable elemento : (List<DefVariable>) val_peek(0))
																												lista.add(elemento);																												
																										}
break;
case 10:
//#line 92 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(0); }
break;
case 11:
//#line 93 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<DefVariable>(); }
break;
case 12:
//#line 96 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<DefVariable>();
																											((List<DefVariable>) yyval).add(new DefVariable(lexico.getLine(), 
																												lexico.getColumn(), (String)val_peek(0), (Tipo)val_peek(1)));  
																										}
break;
case 13:
//#line 102 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(0); }
break;
case 14:
//#line 103 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<Sentencia>(); }
break;
case 15:
//#line 106 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(1);((List<Sentencia>)yyval).add((Sentencia)val_peek(0)); }
break;
case 16:
//#line 107 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<Sentencia>(); ((List<Sentencia>)yyval).add((Sentencia)val_peek(0)); }
break;
case 17:
//#line 110 "../../src/sintactico/sintactico.y"
{ 	yyval = new Asignacion(lexico.getLine(), lexico.getColumn(), (Expresion)val_peek(3), (Expresion)val_peek(1)); }
break;
case 18:
//#line 111 "../../src/sintactico/sintactico.y"
{ 	yyval = new SentenciaWhile(lexico.getLine(), lexico.getColumn(), (Expresion)val_peek(2), (List<Sentencia>)val_peek(0)); }
break;
case 19:
//#line 112 "../../src/sintactico/sintactico.y"
{ 	yyval = new SentenciaIf(lexico.getLine(), lexico.getColumn(), (Expresion)val_peek(2), (List<Sentencia>)val_peek(0), new ArrayList()); }
break;
case 20:
//#line 113 "../../src/sintactico/sintactico.y"
{ 	yyval = new SentenciaIf(lexico.getLine(), lexico.getColumn(), (Expresion)val_peek(4), (List<Sentencia>)val_peek(2), (List<Sentencia>)val_peek(0)); }
break;
case 21:
//#line 114 "../../src/sintactico/sintactico.y"
{ 	yyval = new Escritura(lexico.getLine(), lexico.getColumn(),(List<Expresion>)val_peek(1)); }
break;
case 22:
//#line 115 "../../src/sintactico/sintactico.y"
{ 	yyval = new Lectura(lexico.getLine(), lexico.getColumn(),(List<Expresion>)val_peek(1)); }
break;
case 23:
//#line 116 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(1); }
break;
case 24:
//#line 117 "../../src/sintactico/sintactico.y"
{ 	yyval = new Return(lexico.getLine(), lexico.getColumn(), (Expresion)val_peek(1));}
break;
case 25:
//#line 118 "../../src/sintactico/sintactico.y"
{  	LiteralEntero a = new LiteralEntero(lexico.getLine(), lexico.getColumn(), 1);
			 																								Aritmetica b = new Aritmetica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "+", a);	 
																											yyval = new Asignacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), b); 
																										}
break;
case 26:
//#line 124 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(1);	}
break;
case 27:
//#line 125 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<Sentencia>(); ((List<Sentencia>)yyval).add((Sentencia)val_peek(0));  }
break;
case 28:
//#line 128 "../../src/sintactico/sintactico.y"
{ 	List<DefVariable> variables = new ArrayList<DefVariable>();
																											for(String aux: (List<String>)val_peek(0))
																												variables.add(new DefVariable(lexico.getLine(), lexico.getColumn(), aux, (Tipo)val_peek(1)));																												 
																											yyval = variables; 
																										}
break;
case 29:
//#line 133 "../../src/sintactico/sintactico.y"
{	List<Integer> indices = (List<Integer>)val_peek(1);
																											TipoArray tipo = new TipoArray(indices.get(0), (Tipo)val_peek(2));
																											for(int i = 1; i < indices.size(); i++)
																												tipo = new TipoArray(indices.get(i), tipo);																										           												
																											List<DefVariable> variables = new ArrayList();
																											for(String id: (List<String>)val_peek(0))
																												variables.add(new DefVariable(lexico.getLine(), lexico.getColumn(), id, tipo));
																											yyval = variables;  
																										}
break;
case 30:
//#line 142 "../../src/sintactico/sintactico.y"
{ 	List<CampoRegistro> registrosStruct = new ArrayList();
		           																							for(DefVariable var : (List<DefVariable>) val_peek(2))
		           																								registrosStruct.add(new CampoRegistro(lexico.getLine(), lexico.getColumn(), var.getNombre(), var.getTipo()));		           												
																											TipoRegistro registro = new TipoRegistro(registrosStruct);		           												          												
																											List<DefVariable> variables = new ArrayList();
																											for(String aux: (List<String>)val_peek(0))
																												variables.add(new DefVariable(lexico.getLine(), lexico.getColumn(), aux, registro));
																											yyval = variables; 															 
																										}
break;
case 31:
//#line 151 "../../src/sintactico/sintactico.y"
{
					   																						List<CampoRegistro> registrosStruct = new ArrayList();
		           																							for(DefVariable var : (List<DefVariable>) val_peek(3))
		           																								registrosStruct.add(new CampoRegistro(lexico.getLine(), lexico.getColumn(), var.getNombre(), var.getTipo()));		           												
																											TipoRegistro registro = new TipoRegistro(registrosStruct);

																											List<Integer> indices = (List<Integer>)val_peek(1);
																											TipoArray tipo = new TipoArray(indices.get(0), registro);

																											List<DefVariable> variables = new ArrayList();
																											for(String id: (List<String>)val_peek(0))
																												variables.add(new DefVariable(lexico.getLine(), lexico.getColumn(), id, tipo));
																											yyval = variables;
				   																						}
break;
case 32:
//#line 167 "../../src/sintactico/sintactico.y"
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
case 33:
//#line 179 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(1); }
break;
case 34:
//#line 182 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(2); 
																											List<Expresion> lista = (List<Expresion>)yyval;
																											Expresion elemento = (Expresion)val_peek(0);
																											lista.add(elemento);																																																								
																										}
break;
case 35:
//#line 187 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<Expresion>(); ((List<Expresion>)yyval).add((Expresion)val_peek(0));  	}
break;
case 36:
//#line 190 "../../src/sintactico/sintactico.y"
{ 	yyval = new Variable(lexico.getLine(), lexico.getColumn(), (String) val_peek(0)); 	}
break;
case 37:
//#line 191 "../../src/sintactico/sintactico.y"
{ 	yyval = new LiteralEntero(lexico.getLine(), lexico.getColumn(), (Integer) val_peek(0));		}
break;
case 38:
//#line 192 "../../src/sintactico/sintactico.y"
{ 	yyval = new LiteralReal(lexico.getLine(), lexico.getColumn(), (Double) val_peek(0));	}
break;
case 39:
//#line 193 "../../src/sintactico/sintactico.y"
{ 	yyval = new LiteralCaracter(lexico.getLine(), lexico.getColumn(), (Character)val_peek(0));		}
break;
case 40:
//#line 194 "../../src/sintactico/sintactico.y"
{ 	yyval = new Aritmetica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "+", (Expresion) val_peek(0));		}
break;
case 41:
//#line 195 "../../src/sintactico/sintactico.y"
{ 	yyval = new Aritmetica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "*", (Expresion) val_peek(0));		}
break;
case 42:
//#line 196 "../../src/sintactico/sintactico.y"
{ 	yyval = new Aritmetica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "/", (Expresion) val_peek(0));		}
break;
case 43:
//#line 197 "../../src/sintactico/sintactico.y"
{ 	yyval = new Aritmetica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "-", (Expresion) val_peek(0));		}
break;
case 44:
//#line 198 "../../src/sintactico/sintactico.y"
{ 	yyval = new Aritmetica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "%", (Expresion) val_peek(0));		}
break;
case 45:
//#line 199 "../../src/sintactico/sintactico.y"
{ 	yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), ">", (Expresion) val_peek(0));	}
break;
case 46:
//#line 200 "../../src/sintactico/sintactico.y"
{ 	yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "<", (Expresion) val_peek(0));	}
break;
case 47:
//#line 201 "../../src/sintactico/sintactico.y"
{ 	yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), ">=", (Expresion) val_peek(0));	}
break;
case 48:
//#line 202 "../../src/sintactico/sintactico.y"
{ 	yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "<=", (Expresion) val_peek(0));	}
break;
case 49:
//#line 203 "../../src/sintactico/sintactico.y"
{ 	yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "!=", (Expresion) val_peek(0));	}
break;
case 50:
//#line 204 "../../src/sintactico/sintactico.y"
{ 	yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "==", (Expresion) val_peek(0));	}
break;
case 51:
//#line 205 "../../src/sintactico/sintactico.y"
{ 	yyval = new Logica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "&&", (Expresion) val_peek(0));	}
break;
case 52:
//#line 206 "../../src/sintactico/sintactico.y"
{ 	yyval = new Logica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "||", (Expresion) val_peek(0));	}
break;
case 53:
//#line 207 "../../src/sintactico/sintactico.y"
{ 	yyval = new Negacion(lexico.getLine(), lexico.getColumn(),  "!", (Expresion) val_peek(0));	}
break;
case 54:
//#line 208 "../../src/sintactico/sintactico.y"
{ 	yyval = new MenosUnario(lexico.getLine(), lexico.getColumn(),  "-",(Expresion) val_peek(0));	}
break;
case 55:
//#line 209 "../../src/sintactico/sintactico.y"
{ 	yyval = new AccesoCampo(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), (String) val_peek(0));	}
break;
case 56:
//#line 210 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(1);}
break;
case 57:
//#line 211 "../../src/sintactico/sintactico.y"
{ 	yyval = new Cast(lexico.getLine(), lexico.getColumn(), (Tipo) val_peek(2), (Expresion) val_peek(0));	}
break;
case 58:
//#line 212 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(0);}
break;
case 59:
//#line 213 "../../src/sintactico/sintactico.y"
{ 	yyval = new AccesoArray(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(3), (Expresion) val_peek(1)); }
break;
case 60:
//#line 216 "../../src/sintactico/sintactico.y"
{ 	yyval = new Invocacion(lexico.getLine(), lexico.getColumn(), new Variable(lexico.getLine(), lexico.getColumn(), (String)val_peek(3)),(List<Expresion>)val_peek(1)); 	}
break;
case 61:
//#line 219 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(0);}
break;
case 62:
//#line 220 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<Expresion>();	}
break;
case 63:
//#line 224 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(0); 
																											List<Integer> lista = (List<Integer>)yyval;
																											Integer elemento = (Integer)val_peek(2);
																											lista.add(elemento);
																										}
break;
case 64:
//#line 229 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<Integer>(); ((List<Integer>)yyval).add((Integer)val_peek(1)); 	}
break;
case 65:
//#line 232 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(2); 
																											List<String> lista = (List<String>)yyval;
																											String elemento = (String)val_peek(0);
																											if(!lista.contains(elemento))
																												lista.add(elemento);
																											else
																												new TipoError(lexico.getLine(), lexico.getColumn(),"Identificador duplicado -> " + elemento);																																																							
																										}
break;
case 66:
//#line 240 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList(); ((List<String>)yyval).add(((String) val_peek(0))); 	}
break;
case 67:
//#line 243 "../../src/sintactico/sintactico.y"
{ 	yyval = TipoEntero.getInstancia(); 	}
break;
case 68:
//#line 244 "../../src/sintactico/sintactico.y"
{ 	yyval = TipoReal.getInstancia(); 	}
break;
case 69:
//#line 245 "../../src/sintactico/sintactico.y"
{ 	yyval = TipoCaracter.getInstancia(); 	}
break;
//#line 1091 "Parser.java"
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
