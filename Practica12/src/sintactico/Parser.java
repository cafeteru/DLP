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
public final static short CASTP=281;
public final static short MENORQUEELSE=282;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    4,    4,    4,    2,    2,    6,    6,
    6,    9,    7,    7,    3,    3,   10,   10,   10,   10,
   10,   10,   10,   10,   12,   12,    8,    8,    8,   17,
   17,   13,   13,   11,   11,   11,   11,   11,   11,   11,
   11,   11,   11,   11,   11,   11,   11,   11,   11,   11,
   11,   11,   11,   11,   11,   11,   11,   14,   19,   19,
   16,   16,   18,   18,   15,   15,    5,    5,    5,
};
final static short yylen[] = {                            2,
    9,    2,    0,    9,    9,    2,    3,    0,    3,    1,
    0,    2,    1,    0,    2,    1,    4,    5,    5,    7,
    3,    3,    2,    3,    3,    1,    2,    3,    6,    3,
    2,    3,    1,    1,    1,    1,    1,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    2,    2,    3,    3,    4,    1,    4,    4,    1,    0,
    4,    3,    1,    0,    3,    1,    1,    1,    1,
};
final static short yydefred[] = {                         3,
    0,    0,   67,   68,   69,    0,    0,    2,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    6,    0,    0,
    0,    0,    0,    0,    0,    0,   66,    0,   31,    0,
    0,    0,    0,    0,   10,    0,    0,   65,   63,    0,
   30,    8,   12,    0,    0,    0,   61,    0,    0,    8,
    9,    8,   35,   36,   37,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   16,    0,    0,    0,
    0,    0,    0,   56,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    1,   15,    7,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   23,    0,    0,    0,   22,    0,   21,    0,
    0,   24,    0,    0,    0,   54,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   53,    5,    4,    0,    0,    0,   58,    0,   17,
   57,    0,   26,   18,    0,    0,    0,   25,   20,
};
final static short yydgoto[] = {                          1,
    2,   49,  104,    8,   19,   34,  105,   66,   35,   67,
   68,  144,   73,   74,   16,   17,   21,   40,  114,
};
final static short yysindex[] = {                         0,
    0, -120,    0,    0,    0, -122, -214,    0,  -88,  -46,
 -140,  -11,   13,   25, -217,   40, -180,    0,  -79,   34,
  -70,   60, -104, -104,    9, -162,    0,   40,    0,   20,
   58,   14, -136,  108,    0,  110,   20,    0,    0, -180,
    0,    0,    0,   18, -104,   33,    0,   40,  283,    0,
    0,    0,    0,    0,    0,  -13,  -13,  134,  139,  -13,
  140,  -13,  481,  -13,  346,  127,    0,   97,  128,  283,
  283,  415,    7,    0,   31,  -13,  -13,  123,  -13,  -44,
  148,  130,  -44,    0,    0,    0,  -13,  -13,  -13,  -13,
  -13,  -13,  -13,  -13,  -13,  -13,  -13,  -13,  -13,  -13,
  -13,  -78,    0,  317,   76,   81,    0,  -13,    0,  162,
  224,    0,  167,  171,  -13,    0,  422,  422,  446,  446,
  446,  446,  173,  446,  446,  243,  243,  -44,  -44,  -44,
  318,    0,    0,    0,  415,  380,  380,    0,  -44,    0,
    0,  317,    0,    0,  -51,  445,  380,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   32,    0,  158,    0,    0,    0,    0,
    0,    0,  137,  137,    0,    0,    0,  164,    0,  -47,
    0,    0,    0,    0,    0,    0,  -45,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   34,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  -37,    0,    0,    0,    0,    0,    0,    0,  381,  101,
  101,   41,    0,    0,    0,    0,    0,    0,  187,  -26,
    0,    0,    1,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  104,    0,    0,    0,    0,    0,    0,
    0,    0,  190,    0,    0,    0,  351,  373,  619,  625,
  701,  710,    0,  723,  745,  523,  569,   27,   36,   62,
    0,    0,    0,    0,   94,    0,    0,    0,   71,    0,
    0,    0,    0,    0,  472,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  150,  -23,    0,  296,  210,  172,   28,  197,  126,
  848, -106,  -29,  687,   -3,   22,    0,    0,    0,
};
final static int YYTABLESIZE=1023;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         34,
   11,  102,   15,   34,   34,   34,   34,   34,   34,   34,
   52,   15,   18,   28,   52,   52,   52,   52,   52,   64,
   52,   34,   34,   34,   34,   65,   63,   75,   22,   10,
  145,   62,   52,   52,   52,   52,   48,   51,   20,   25,
  149,   51,   51,   51,   51,   51,  101,   51,   31,  113,
  108,   39,   23,   34,   30,   34,   12,   13,   47,   51,
   51,   51,   51,   39,   24,  107,   52,   39,   39,   39,
   39,   39,   40,   39,  108,   66,   40,   40,   40,   40,
   40,   33,   40,   26,   33,   39,   39,   39,   39,  109,
   66,   27,   29,   51,   40,   40,   40,   40,   42,   33,
   32,   37,   42,   42,   42,   42,   42,   55,   42,   38,
   15,   55,   55,   55,   55,   55,   41,   55,  146,   39,
   42,   42,   42,   42,    3,    4,    5,    6,   40,   55,
   55,   55,   55,  100,   32,   43,   42,   32,   98,   96,
   50,   97,  102,   99,    3,    4,    5,    6,   44,    7,
   46,   45,   32,   45,   42,   52,   95,   93,   94,  100,
    3,    4,    5,   55,   98,   96,  100,   97,  102,   99,
  116,   98,   96,   76,   97,  102,   99,   11,   77,   79,
   11,  112,   95,   14,   94,   86,  103,  101,  115,   95,
   85,   94,   27,  132,    3,    4,    5,    6,  100,   70,
  133,   71,  136,   98,   96,  134,   97,  102,   99,  100,
  108,  138,  147,  101,   98,   96,   27,   97,  102,   99,
  101,   95,   28,   94,   64,   14,   62,   60,   13,   85,
   59,  140,   95,   36,   94,   34,   34,   34,   34,   34,
   34,   51,  106,   53,   54,   55,   52,   52,   52,   52,
   52,   52,  101,    0,    0,    0,    0,    0,   61,    0,
  100,  143,  143,  101,  137,   98,   96,    0,   97,  102,
   99,   85,  143,   51,   51,   51,   51,   51,   51,  100,
    0,    0,    0,   95,   98,   94,    0,    0,  102,   99,
    0,    0,    0,    0,    0,    0,    0,    9,    0,   39,
   39,   39,   39,   39,   39,    0,    0,    0,   40,   40,
   40,   40,   40,   40,  101,   64,    0,    0,   33,   33,
    0,    0,   63,    0,    0,    0,    0,   62,    0,    0,
    0,    0,    0,  101,   42,   42,   42,   42,   42,   42,
   33,    0,    0,   55,   55,   55,   55,   55,   55,   64,
    0,    0,    0,    0,  100,    0,   63,    0,   81,   98,
   96,   62,   97,  102,   99,    0,    0,    0,    0,   87,
   88,   89,   90,   91,   92,    0,    0,   95,   64,   94,
    0,    0,    0,    0,    0,   63,    0,    0,    0,    0,
   62,   49,    0,    0,   49,   87,   88,   89,   90,   91,
   92,    0,   87,   88,   89,   90,   91,   92,  101,   49,
  141,   49,   64,   50,    0,    0,   50,   56,    0,   63,
    0,    0,   56,   56,   62,   56,   56,   56,    0,    0,
    0,   50,    0,   50,   87,   88,   89,   90,   91,   92,
   56,   56,   56,   49,    0,   87,   88,   89,   90,   91,
   92,  100,    0,    0,    0,    0,   98,   96,  100,   97,
  102,   99,    0,   98,   96,   50,   97,  102,   99,    0,
   84,   56,    0,    0,   95,    0,   94,   64,    0,    0,
    0,   95,  100,   94,   63,    0,    0,   98,   96,   62,
   97,  102,   99,    0,    0,    0,   87,   88,   89,   90,
   91,   92,  142,    0,   19,  101,    0,    0,    0,    0,
    0,   19,  101,   64,    0,    0,   19,    0,    0,    0,
   63,    0,    0,    0,    0,   62,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  101,    0,    0,   53,
   54,   55,   56,   57,   58,   59,    0,    3,    4,    5,
    6,   60,    0,    0,   61,    0,    0,    0,    0,    0,
    0,    0,    0,   38,    0,   38,   38,   38,    0,  148,
    0,    0,    0,   53,   54,   55,   56,   57,   58,   59,
    0,   38,   38,   38,   38,   60,    0,    0,   61,    0,
   87,   88,   89,   90,   91,   92,   19,    0,    0,    0,
    0,    0,   53,   54,   55,   56,   57,   58,   59,   41,
    0,   41,   41,   41,   60,   38,    0,   61,    0,    0,
    0,    0,    0,   49,   49,    0,    0,   41,   41,   41,
   41,    0,    0,    0,    0,    0,   53,   54,   55,   56,
   57,   58,   59,    0,    0,   50,   50,    0,   60,    0,
    0,   61,    0,   56,   56,   56,   56,   56,   56,   45,
    0,   41,   45,    0,    0,   46,    0,    0,   46,    0,
    0,    0,    0,    0,    0,    0,    0,   45,   45,   45,
   45,    0,    0,   46,   46,   46,   46,   87,   88,   89,
   90,   91,   92,    0,    0,    0,   89,   90,   91,   92,
    0,   53,   54,   55,   56,   57,   58,   59,    0,    0,
    0,   45,    0,   60,    0,    0,   61,   46,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   19,   19,
   19,   19,   19,   19,   19,   69,    0,   53,   54,   55,
   19,   47,    0,   19,   47,    3,    4,    5,    0,    0,
   48,   69,   61,   48,    0,    0,   69,   69,    0,   47,
   47,   47,   47,   43,    0,    0,   43,    0,   48,   48,
   48,   48,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   43,   43,   43,   43,   44,    0,    0,   44,    0,
   69,    0,    0,   47,    0,   38,   38,   38,   38,   38,
   38,    0,   48,   44,   44,   44,   44,    0,    0,    0,
    0,    0,    0,    0,    0,   43,    0,    0,    0,    0,
    0,    0,   69,   69,    0,    0,    0,    0,   69,    0,
    0,    0,   69,   69,    0,    0,    0,   44,    0,    0,
    0,   41,   41,   41,   41,   41,   41,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   45,   45,   45,   45,   45,   45,   46,   46,   46,
   46,   46,   46,   72,   72,    0,    0,   78,    0,   80,
   82,   83,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  110,  111,    0,   72,    0,    0,    0,
    0,    0,    0,    0,  117,  118,  119,  120,  121,  122,
  123,  124,  125,  126,  127,  128,  129,  130,  131,    0,
    0,    0,    0,    0,    0,  135,    0,    0,    0,    0,
    0,    0,  139,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   47,   47,   47,   47,   47,   47,    0,
    0,    0,   48,   48,   48,   48,   48,   48,    0,    0,
    0,    0,    0,    0,    0,   43,   43,   43,   43,   43,
   43,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   44,   44,   44,
   44,   44,   44,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
  123,   46,   91,   41,   42,   43,   44,   45,   46,   47,
   37,   91,   59,   17,   41,   42,   43,   44,   45,   33,
   47,   59,   60,   61,   62,   49,   40,   57,   40,    2,
  137,   45,   59,   60,   61,   62,   40,   37,   11,  257,
  147,   41,   42,   43,   44,   45,   91,   47,   21,   79,
   44,   30,   40,   91,  125,   93,  271,  272,   37,   59,
   60,   61,   62,   37,   40,   59,   93,   41,   42,   43,
   44,   45,   37,   47,   44,   44,   41,   42,   43,   44,
   45,   41,   47,   44,   44,   59,   60,   61,   62,   59,
   59,  272,   59,   93,   59,   60,   61,   62,   37,   59,
   41,   93,   41,   42,   43,   44,   45,   37,   47,  272,
   91,   41,   42,   43,   44,   45,   59,   47,  142,   93,
   59,   60,   61,   62,  265,  266,  267,  268,   93,   59,
   60,   61,   62,   37,   41,  272,  123,   44,   42,   43,
  123,   45,   46,   47,  265,  266,  267,  268,   41,  270,
   41,   44,   59,   44,   93,  123,   60,   61,   62,   37,
  265,  266,  267,   93,   42,   43,   37,   45,   46,   47,
   41,   42,   43,   40,   45,   46,   47,   41,   40,   40,
   44,   59,   60,  272,   62,   59,   59,   91,   41,   60,
   65,   62,  272,  272,  265,  266,  267,  268,   37,   50,
  125,   52,   41,   42,   43,  125,   45,   46,   47,   37,
   44,   41,  264,   91,   42,   43,   59,   45,   46,   47,
   91,   60,   59,   62,  272,  125,  272,   41,  125,  104,
   41,   59,   60,   24,   62,  273,  274,  275,  276,  277,
  278,   45,   71,  257,  258,  259,  273,  274,  275,  276,
  277,  278,   91,   -1,   -1,   -1,   -1,   -1,  272,   -1,
   37,  136,  137,   91,   41,   42,   43,   -1,   45,   46,
   47,  146,  147,  273,  274,  275,  276,  277,  278,   37,
   -1,   -1,   -1,   60,   42,   62,   -1,   -1,   46,   47,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,    2,   -1,  273,
  274,  275,  276,  277,  278,   -1,   -1,   -1,  273,  274,
  275,  276,  277,  278,   91,   33,   -1,   -1,   23,   24,
   -1,   -1,   40,   -1,   -1,   -1,   -1,   45,   -1,   -1,
   -1,   -1,   -1,   91,  273,  274,  275,  276,  277,  278,
   45,   -1,   -1,  273,  274,  275,  276,  277,  278,   33,
   -1,   -1,   -1,   -1,   37,   -1,   40,   -1,   63,   42,
   43,   45,   45,   46,   47,   -1,   -1,   -1,   -1,  273,
  274,  275,  276,  277,  278,   -1,   -1,   60,   33,   62,
   -1,   -1,   -1,   -1,   -1,   40,   -1,   -1,   -1,   -1,
   45,   41,   -1,   -1,   44,  273,  274,  275,  276,  277,
  278,   -1,  273,  274,  275,  276,  277,  278,   91,   59,
   93,   61,   33,   41,   -1,   -1,   44,   37,   -1,   40,
   -1,   -1,   42,   43,   45,   45,   46,   47,   -1,   -1,
   -1,   59,   -1,   61,  273,  274,  275,  276,  277,  278,
   60,   61,   62,   93,   -1,  273,  274,  275,  276,  277,
  278,   37,   -1,   -1,   -1,   -1,   42,   43,   37,   45,
   46,   47,   -1,   42,   43,   93,   45,   46,   47,   -1,
  125,   91,   -1,   -1,   60,   -1,   62,   33,   -1,   -1,
   -1,   60,   37,   62,   40,   -1,   -1,   42,   43,   45,
   45,   46,   47,   -1,   -1,   -1,  273,  274,  275,  276,
  277,  278,  123,   -1,   33,   91,   -1,   -1,   -1,   -1,
   -1,   40,   91,   33,   -1,   -1,   45,   -1,   -1,   -1,
   40,   -1,   -1,   -1,   -1,   45,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   91,   -1,   -1,  257,
  258,  259,  260,  261,  262,  263,   -1,  265,  266,  267,
  268,  269,   -1,   -1,  272,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   41,   -1,   43,   44,   45,   -1,  125,
   -1,   -1,   -1,  257,  258,  259,  260,  261,  262,  263,
   -1,   59,   60,   61,   62,  269,   -1,   -1,  272,   -1,
  273,  274,  275,  276,  277,  278,  125,   -1,   -1,   -1,
   -1,   -1,  257,  258,  259,  260,  261,  262,  263,   41,
   -1,   43,   44,   45,  269,   93,   -1,  272,   -1,   -1,
   -1,   -1,   -1,  273,  274,   -1,   -1,   59,   60,   61,
   62,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,  260,
  261,  262,  263,   -1,   -1,  273,  274,   -1,  269,   -1,
   -1,  272,   -1,  273,  274,  275,  276,  277,  278,   41,
   -1,   93,   44,   -1,   -1,   41,   -1,   -1,   44,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   59,   60,   61,
   62,   -1,   -1,   59,   60,   61,   62,  273,  274,  275,
  276,  277,  278,   -1,   -1,   -1,  275,  276,  277,  278,
   -1,  257,  258,  259,  260,  261,  262,  263,   -1,   -1,
   -1,   93,   -1,  269,   -1,   -1,  272,   93,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,
  259,  260,  261,  262,  263,   49,   -1,  257,  258,  259,
  269,   41,   -1,  272,   44,  265,  266,  267,   -1,   -1,
   41,   65,  272,   44,   -1,   -1,   70,   71,   -1,   59,
   60,   61,   62,   41,   -1,   -1,   44,   -1,   59,   60,
   61,   62,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   59,   60,   61,   62,   41,   -1,   -1,   44,   -1,
  104,   -1,   -1,   93,   -1,  273,  274,  275,  276,  277,
  278,   -1,   93,   59,   60,   61,   62,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   93,   -1,   -1,   -1,   -1,
   -1,   -1,  136,  137,   -1,   -1,   -1,   -1,  142,   -1,
   -1,   -1,  146,  147,   -1,   -1,   -1,   93,   -1,   -1,
   -1,  273,  274,  275,  276,  277,  278,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  273,  274,  275,  276,  277,  278,  273,  274,  275,
  276,  277,  278,   56,   57,   -1,   -1,   60,   -1,   62,
   63,   64,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   76,   77,   -1,   79,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   87,   88,   89,   90,   91,   92,
   93,   94,   95,   96,   97,   98,   99,  100,  101,   -1,
   -1,   -1,   -1,   -1,   -1,  108,   -1,   -1,   -1,   -1,
   -1,   -1,  115,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  273,  274,  275,  276,  277,  278,   -1,
   -1,   -1,  273,  274,  275,  276,  277,  278,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  273,  274,  275,  276,  277,
  278,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  273,  274,  275,
  276,  277,  278,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=282;
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
"DISTINTO","IGUALDAD","MENOS_UNARIO","NEGACION","CASTP","MENORQUEELSE",
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
"declaracionVariable : STRUCT '{' campos '}' indicesStruct identificador",
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
"indicesStruct : indices",
"indicesStruct :",
"identificador : identificador ',' ID",
"identificador : ID",
"tipoSimple : INT",
"tipoSimple : DOUBLE",
"tipoSimple : CHAR",
};

//#line 263 "../../src/sintactico/sintactico.y"

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
//#line 560 "Parser.java"
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
//#line 43 "../../src/sintactico/sintactico.y"
{ 	List<Definicion> definiciones = (List<Definicion>)val_peek(8);																						
																											Definicion main = new DefFuncion(lexico.getLine(), lexico.getColumn(), "main",
																												new TipoFuncion(new ArrayList<DefVariable>(), TipoVoid.getInstancia()), 
																												(List<DefVariable>)val_peek(2), (List<Sentencia>)val_peek(1));
																											definiciones.add(main);
																																												
																											this.ast = new Programa(lexico.getLine(), lexico.getColumn(), definiciones); 
																										}
break;
case 2:
//#line 51 "../../src/sintactico/sintactico.y"
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
//#line 64 "../../src/sintactico/sintactico.y"
{	yyval = new ArrayList(); }
break;
case 4:
//#line 67 "../../src/sintactico/sintactico.y"
{ 	List<Definicion> lista = new ArrayList();
																											lista.add(new DefFuncion(lexico.getLine(), lexico.getColumn(), (String) val_peek(7),
																												new TipoFuncion((List<DefVariable>)val_peek(5), (Tipo)val_peek(8)), 
																												(List<DefVariable>)val_peek(2), (List<Sentencia>)val_peek(1)));
																											yyval = lista; 
																										}
break;
case 5:
//#line 73 "../../src/sintactico/sintactico.y"
{	List<Definicion> lista = new ArrayList();
																											lista.add(new DefFuncion(lexico.getLine(), lexico.getColumn(),  (String) val_peek(7),
																											new TipoFuncion((List<DefVariable>)val_peek(5), TipoVoid.getInstancia()), 
																											(List<DefVariable>)val_peek(2), (List<Sentencia>)val_peek(1)));
																											yyval = lista;
																										}
break;
case 6:
//#line 79 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(1);  }
break;
case 7:
//#line 82 "../../src/sintactico/sintactico.y"
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
//#line 91 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<DefVariable>();}
break;
case 9:
//#line 94 "../../src/sintactico/sintactico.y"
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
//#line 104 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(0); }
break;
case 11:
//#line 105 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<DefVariable>();}
break;
case 12:
//#line 108 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<>();
																											((List<DefVariable>) yyval).add(new DefVariable(lexico.getLine(), 
																												lexico.getColumn(), (String)val_peek(0), (Tipo)val_peek(1)));  
																										}
break;
case 13:
//#line 114 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(0);}
break;
case 14:
//#line 115 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<Sentencia>();}
break;
case 15:
//#line 118 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(1);((List<Sentencia>)yyval).add((Sentencia)val_peek(0)); }
break;
case 16:
//#line 119 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<Sentencia>(); ((List<Sentencia>)yyval).add((Sentencia)val_peek(0)); }
break;
case 17:
//#line 122 "../../src/sintactico/sintactico.y"
{ 	yyval = new Asignacion(lexico.getLine(), lexico.getColumn(), (Expresion)val_peek(3), (Expresion)val_peek(1)); }
break;
case 18:
//#line 123 "../../src/sintactico/sintactico.y"
{ 	yyval = new SentenciaWhile(lexico.getLine(), lexico.getColumn(), (Expresion)val_peek(2), (List<Sentencia>)val_peek(0));}
break;
case 19:
//#line 124 "../../src/sintactico/sintactico.y"
{ 	yyval = new SentenciaIf(lexico.getLine(), lexico.getColumn(), (Expresion)val_peek(2), (List<Sentencia>)val_peek(0), new ArrayList()); }
break;
case 20:
//#line 125 "../../src/sintactico/sintactico.y"
{ 	yyval = new SentenciaIf(lexico.getLine(), lexico.getColumn(), (Expresion)val_peek(4), (List<Sentencia>)val_peek(2), (List<Sentencia>)val_peek(0));}
break;
case 21:
//#line 126 "../../src/sintactico/sintactico.y"
{ 	yyval = new Escritura(lexico.getLine(), lexico.getColumn(),(List<Expresion>)val_peek(1));}
break;
case 22:
//#line 127 "../../src/sintactico/sintactico.y"
{ 	yyval = new Lectura(lexico.getLine(), lexico.getColumn(),(List<Expresion>)val_peek(1)); }
break;
case 23:
//#line 128 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(1); }
break;
case 24:
//#line 129 "../../src/sintactico/sintactico.y"
{ 	yyval = new Return(lexico.getLine(), lexico.getColumn(), (Expresion)val_peek(1));}
break;
case 25:
//#line 132 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(1);	}
break;
case 26:
//#line 133 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<Sentencia>(); ((List<Sentencia>)yyval).add((Sentencia)val_peek(0));  }
break;
case 27:
//#line 136 "../../src/sintactico/sintactico.y"
{ 	List<DefVariable> variables = new ArrayList();
																											for(String aux: (List<String>)val_peek(0)){
																												variables.add(new DefVariable(lexico.getLine(), lexico.getColumn(), aux, (Tipo)val_peek(1)));
																											}	 
																											yyval = variables; 
																										}
break;
case 28:
//#line 142 "../../src/sintactico/sintactico.y"
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
//#line 154 "../../src/sintactico/sintactico.y"
{ 	
		           																							List<CampoRegistro> registrosStruct = new ArrayList();
		           																							for(DefVariable var : (List<DefVariable>) val_peek(3)){
		           																								registrosStruct.add(new CampoRegistro(lexico.getLine(), lexico.getColumn(), var.getNombre(), var.getTipo()));
																											}
		           												
																											TipoRegistro registro = new TipoRegistro(registrosStruct);
		           												          									
		           												          									if(!val_peek(1).equals(new ArrayList())){
		           																								List<Integer> indices = (List<Integer>)val_peek(1);
																												TipoArray tipo = new TipoArray(indices.get(0), registro);
																												for(int i = 1; i < indices.size(); i++){
																													tipo = new TipoArray(indices.get(i), tipo);
																												}
																											}
		           												          												
																											List<DefVariable> variables = new ArrayList();
																											for(String aux: (List<String>)val_peek(1)){
																												variables.add(new DefVariable(lexico.getLine(), lexico.getColumn(), aux, registro));
																											}	 
																											yyval = variables; 															 
																										}
break;
case 30:
//#line 178 "../../src/sintactico/sintactico.y"
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
//#line 190 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(1);  }
break;
case 32:
//#line 193 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(2); 
																											List<Expresion> lista = (List<Expresion>)yyval;
																											Expresion elemento = (Expresion)val_peek(0);
																											lista.add(elemento);																																																								
																										}
break;
case 33:
//#line 198 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<Expresion>(); ((List<Expresion>)yyval).add((Expresion)val_peek(0));  	}
break;
case 34:
//#line 201 "../../src/sintactico/sintactico.y"
{ 	yyval = new Variable(lexico.getLine(), lexico.getColumn(), (String) val_peek(0)); 	}
break;
case 35:
//#line 202 "../../src/sintactico/sintactico.y"
{ 	yyval = new LiteralEntero(lexico.getLine(), lexico.getColumn(), (Integer) val_peek(0));		}
break;
case 36:
//#line 203 "../../src/sintactico/sintactico.y"
{ 	yyval = new LiteralReal(lexico.getLine(), lexico.getColumn(), (Double) val_peek(0));	}
break;
case 37:
//#line 204 "../../src/sintactico/sintactico.y"
{ 	yyval = new LiteralCaracter(lexico.getLine(), lexico.getColumn(), (Character)val_peek(0));		}
break;
case 38:
//#line 205 "../../src/sintactico/sintactico.y"
{ 	yyval = new Aritmetica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "+", (Expresion) val_peek(0));		}
break;
case 39:
//#line 206 "../../src/sintactico/sintactico.y"
{ 	yyval = new Aritmetica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "*", (Expresion) val_peek(0));		}
break;
case 40:
//#line 207 "../../src/sintactico/sintactico.y"
{ 	yyval = new Aritmetica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "/", (Expresion) val_peek(0));		}
break;
case 41:
//#line 208 "../../src/sintactico/sintactico.y"
{ 	yyval = new Aritmetica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "-", (Expresion) val_peek(0));		}
break;
case 42:
//#line 209 "../../src/sintactico/sintactico.y"
{ 	yyval = new Aritmetica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "%", (Expresion) val_peek(0));		}
break;
case 43:
//#line 210 "../../src/sintactico/sintactico.y"
{ 	yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), ">", (Expresion) val_peek(0));	}
break;
case 44:
//#line 211 "../../src/sintactico/sintactico.y"
{ 	yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "<", (Expresion) val_peek(0));	}
break;
case 45:
//#line 212 "../../src/sintactico/sintactico.y"
{ 	yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), ">=", (Expresion) val_peek(0));	}
break;
case 46:
//#line 213 "../../src/sintactico/sintactico.y"
{ 	yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "<=", (Expresion) val_peek(0));	}
break;
case 47:
//#line 214 "../../src/sintactico/sintactico.y"
{ 	yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "!=", (Expresion) val_peek(0));	}
break;
case 48:
//#line 215 "../../src/sintactico/sintactico.y"
{ 	yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "==", (Expresion) val_peek(0));	}
break;
case 49:
//#line 216 "../../src/sintactico/sintactico.y"
{ 	yyval = new Logica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "&&", (Expresion) val_peek(0));	}
break;
case 50:
//#line 217 "../../src/sintactico/sintactico.y"
{ 	yyval = new Logica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "||", (Expresion) val_peek(0));	}
break;
case 51:
//#line 218 "../../src/sintactico/sintactico.y"
{ 	yyval = new Negacion(lexico.getLine(), lexico.getColumn(),  "!", (Expresion) val_peek(0));	}
break;
case 52:
//#line 219 "../../src/sintactico/sintactico.y"
{ 	yyval = new MenosUnario(lexico.getLine(), lexico.getColumn(),  "-",(Expresion) val_peek(0));	}
break;
case 53:
//#line 220 "../../src/sintactico/sintactico.y"
{ 	yyval = new AccesoCampo(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), (String) val_peek(0));	}
break;
case 54:
//#line 221 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(1);}
break;
case 55:
//#line 222 "../../src/sintactico/sintactico.y"
{ 	yyval = new Cast(lexico.getLine(), lexico.getColumn(), (Tipo) val_peek(2), (Expresion) val_peek(0));	}
break;
case 56:
//#line 223 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(0);}
break;
case 57:
//#line 224 "../../src/sintactico/sintactico.y"
{ 	yyval = new AccesoArray(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(3), (Expresion) val_peek(1)); }
break;
case 58:
//#line 227 "../../src/sintactico/sintactico.y"
{ 	yyval = new Invocacion(lexico.getLine(), lexico.getColumn(), new Variable(lexico.getLine(), lexico.getColumn(), (String)val_peek(3)),(List<Expresion>)val_peek(1)); 	}
break;
case 59:
//#line 230 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(0);}
break;
case 60:
//#line 231 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<Expresion>();	}
break;
case 61:
//#line 235 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(0); 
																											List<Integer> lista = (List<Integer>)yyval;
																											Integer elemento = (Integer)val_peek(2);
																											lista.add(elemento);
																										}
break;
case 62:
//#line 240 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<Integer>(); ((List<Integer>)yyval).add((Integer)val_peek(1)); 	}
break;
case 63:
//#line 243 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0);}
break;
case 64:
//#line 244 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<Integer>();}
break;
case 65:
//#line 247 "../../src/sintactico/sintactico.y"
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
//#line 255 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList(); ((List<String>)yyval).add(((String) val_peek(0))); 	}
break;
case 67:
//#line 258 "../../src/sintactico/sintactico.y"
{ 	yyval = TipoEntero.getInstancia(); 	}
break;
case 68:
//#line 259 "../../src/sintactico/sintactico.y"
{ 	yyval = TipoReal.getInstancia(); 	}
break;
case 69:
//#line 260 "../../src/sintactico/sintactico.y"
{ 	yyval = TipoCaracter.getInstancia(); 	}
break;
//#line 1097 "Parser.java"
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
