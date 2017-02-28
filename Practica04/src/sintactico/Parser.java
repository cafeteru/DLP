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
    0,    1,    1,    4,    4,    4,    2,    2,    9,    9,
   12,   12,   12,    6,    6,    6,    7,    7,    3,    3,
   13,   13,   13,   13,   13,   13,   13,   13,   13,   13,
   16,   16,   11,   11,   14,   14,    8,    8,    8,   20,
   20,   10,   10,   15,   15,   15,   15,   15,   15,   15,
   15,   15,   15,   15,   15,   15,   15,   15,   15,   15,
   15,   15,   15,   15,   15,   15,   17,   17,   19,   19,
   18,   18,    5,    5,    5,
};
final static short yylen[] = {                            2,
    9,    2,    0,    9,    9,    2,    3,    0,    4,    4,
    1,    1,    1,    4,    2,    0,    1,    0,    2,    1,
    1,    4,    6,    7,    5,    7,    3,    3,    2,    3,
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
   40,    8,   15,    0,    0,    0,   69,    0,    8,    0,
    8,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   20,    0,    0,   14,    0,   45,   46,   47,    0,    0,
    0,    0,   66,    0,   44,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    1,   19,    7,   29,    0,    0,
    0,    0,    0,    0,    0,    0,   28,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   27,    0,    0,   30,    0,    0,    0,    0,
    0,    0,    5,    4,    0,   64,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   61,    0,    0,   68,    0,    9,   10,    0,    0,   22,
   65,    0,    0,   32,    0,    0,   12,   13,   11,   33,
   67,    0,    0,    0,   23,   24,   31,   26,
};
final static short yydgoto[] = {                          1,
    2,   48,   90,    8,   19,   35,   91,   59,   73,   74,
  120,  160,   61,   75,   76,  155,   84,   16,   17,   21,
};
final static short yysindex[] = {                         0,
    0,  -56,    0,    0,    0, -120, -222,    0,  -90,  -33,
   -9,   15,   19,   25, -219,   32,  -89,    0,  -64,   42,
    2,   70,   50,   50,   44, -188,    0, -111,   32,    0,
 -124,   99,   39,  -92,   41,   49,    0,    0,   88,    0,
    0,    0,    0,   69,   50,   94,    0,  404,    0,  -54,
    0,  105,  105,  189,  204,  105,    6,  291,  171,  187,
    0,  192,  404,    0,  404,    0,    0,    0,  -28,  105,
  105,  188,    0,   73,    0,  246,  145,  105,  105,   97,
  105,  -18,  105,  175,    0,    0,    0,    0,  105,  312,
  137,  156,  240,  240,  243,  124,    0,  105,  105,  105,
  105,  105,  105,  105,  105,  105,  105,  105,  105,  105,
  105,  105,    0,  131,  153,    0,  -31,  226,   84,  144,
  105,  160,    0,    0,  105,    0,  246,  281,  281,  540,
  540,  540,  540,  540,  540,  248,  248,  240,  240,  240,
    0,  173, -109,    0,  105,    0,    0, -228,   17,    0,
    0,  312,  312,    0,   34,  218,    0,    0,    0,    0,
    0,  307,  343, -109,    0,    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  157,    0,  260,    0,    0,    0,    0,
    0,    0,  190,  190,    0,    0,    0,    0,  261,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  118,    0,    0,    0,
    0,  278,  174,    0,  174,    0,    0,    0,  -26,    0,
    0,    0,    0,    0,    0,    7,    0,    0,    0,    0,
    0,    0,  201,  -37,    0,    0,    0,    0,    0,  182,
    0,    0,   -2,   27,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  238,   82,  438,  380,
  407,  415,  421,  443,  468,  324,  351,   36,   62,   71,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  347,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,   43,  -17,    0,   30,  297,  257,   26,  572,   66,
    0,    0,  428,  585,  683,  161,    0,  313,    0,    0,
};
final static int YYTABLESIZE=828;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         35,
   15,   28,   11,   35,   35,   35,   35,   35,   35,   35,
   36,   83,   98,  153,   36,   36,   36,   36,   36,   36,
   36,   35,   35,   35,   35,   18,   15,   10,  157,  158,
   58,    9,   36,   36,   63,   36,   20,   25,   63,   63,
   63,   63,   63,  159,   63,   83,   32,   43,   12,   13,
   43,   82,   34,   34,   22,   35,   63,   63,   23,   63,
   98,  144,   81,   62,   24,   43,   36,   62,   62,   62,
   62,   62,   49,   62,   50,   26,   49,   49,   49,   49,
   49,   44,   49,   38,   45,   62,   62,   35,   62,   46,
   63,   63,   45,   65,   49,   49,   81,   49,   50,   43,
   30,   95,   50,   50,   50,   50,   50,   52,   50,  161,
   33,   52,   52,   52,   52,   52,   98,   52,   77,   62,
   50,   50,   59,   50,  146,   59,   31,   98,   49,   52,
   52,   97,   52,  111,  162,  163,   37,   70,  109,  107,
   59,  108,  112,  110,   72,   39,  117,   40,  119,   71,
   52,   53,   54,   55,   50,  116,  106,   41,  105,   56,
  111,   42,   57,   52,  126,  109,  107,  111,  108,  112,
  110,  142,  109,  107,   59,  108,  112,  110,   36,   43,
   47,   14,   27,  106,  147,  105,  149,  148,   98,  111,
  106,   49,  105,  143,  109,  107,  111,  108,  112,  110,
   72,  109,  107,  113,  108,  112,  110,   27,    3,    4,
    5,    6,  106,    7,  105,   72,   51,   64,  150,  106,
   70,  105,   35,   35,   35,   35,   35,   72,   78,   87,
   16,   35,   71,   16,   35,   35,   35,   35,   35,   35,
   35,   34,   36,   79,   34,   88,   36,   36,   36,   36,
   36,   36,   89,  118,  111,    3,    4,    5,    6,  109,
  107,  123,  108,  112,  110,  121,    3,    4,    5,    6,
   63,   63,   63,   63,   63,   63,  165,  106,   42,  105,
  124,   42,  111,  125,  111,  112,  145,  109,  107,  109,
  108,  112,  110,  112,  110,  152,   42,  164,   18,   62,
   62,   62,   62,   62,   62,  106,   17,  105,   49,   49,
   49,   49,   49,   49,    3,    4,    5,  111,   37,   38,
   36,   92,  109,  107,  168,  108,  112,  110,    0,   29,
   42,    0,    0,    0,   50,   50,   50,   50,   50,   50,
  106,    0,  105,   52,   52,   52,   52,   52,   52,    0,
    0,    0,    0,    0,   59,   59,    0,    0,    0,    0,
    0,   66,   67,   68,   48,    0,   48,   48,   48,   99,
  100,  101,  102,  103,  104,    0,   69,   36,   36,   36,
   36,   36,   48,   48,    0,   48,   36,    0,    0,   36,
    0,   51,    0,   51,   51,   51,   99,  100,  101,  102,
  103,  104,   21,   99,  100,  101,  102,  103,  104,   51,
   51,    0,   51,    0,    0,   85,   48,    0,    0,    0,
   55,    0,    0,   55,    0,   99,  100,  101,  102,  103,
  104,  166,   99,  100,  101,  102,  103,  104,   55,   55,
    0,   55,    0,   51,   66,   67,   68,   56,    0,    0,
   56,    0,    3,    4,    5,   57,    0,    0,   57,   69,
    0,   58,    0,    0,   58,   56,   56,  167,   56,    0,
    0,   25,   55,   57,   57,    0,   57,    0,   60,   58,
   58,   60,   58,   53,    0,   86,   53,    0,    0,    0,
   99,  100,  101,  102,  103,  104,   60,    0,    0,   56,
    0,   53,   53,    0,   53,    0,    0,   57,   54,    0,
    0,   54,    0,   58,    0,    0,    0,   86,   99,  100,
  101,  102,  103,  104,    0,    0,   54,   54,    0,   54,
   60,    0,    0,    0,    0,   53,    0,   21,   21,   21,
   21,   21,    0,    0,    0,    0,   21,    0,    0,   21,
   52,   53,   54,   55,    0,  101,  102,  103,  104,   56,
   54,    0,   57,    0,    0,    0,   52,   53,   54,   55,
  154,   52,   53,   54,   55,   56,  111,    0,   57,    0,
   56,  109,  107,   57,  108,  112,  110,    0,    0,   86,
   86,  154,    0,    0,    0,    0,   48,   48,   48,   48,
   48,   48,   52,   53,   54,   55,   25,   25,   25,   25,
    0,   56,    0,    0,   57,   25,    0,    0,   25,   60,
    0,    0,    0,   51,   51,   51,   51,   51,   51,   60,
    0,    0,   62,    0,   60,    0,   60,    0,    0,    0,
    0,    0,   62,    0,    0,    0,    0,   62,    0,   62,
    0,    0,   55,   55,   55,   55,   55,   55,    0,    0,
    0,   60,    0,   52,   53,   54,   55,    0,    3,    4,
    5,    6,   56,    0,   62,   57,    0,    0,    0,   56,
   56,   56,   56,   56,   56,    0,    0,   57,   57,   57,
   57,   57,   57,   58,   58,   58,   58,   58,   58,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   60,   60,    0,    0,   60,   53,   53,   53,   53,   53,
   53,    0,    0,   60,   60,    0,    0,   62,    0,    0,
    0,    0,    0,   60,   60,   60,   62,   62,   80,    0,
   54,   54,   54,   54,   54,   54,   62,   62,   62,    0,
    0,    0,   93,   94,   96,    0,    0,    0,    0,    0,
  114,  115,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  122,    0,    0,    0,    0,    0,    0,    0,    0,
  127,  128,  129,  130,  131,  132,  133,  134,  135,  136,
  137,  138,  139,  140,  141,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  151,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  156,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
   91,   91,  123,   41,   42,   43,   44,   45,   46,   47,
   37,   40,   44,  123,   41,   42,   43,   44,   45,   46,
   47,   59,   60,   61,   62,   59,   91,    2,  257,  258,
   48,    2,   59,   60,   37,   62,   11,  257,   41,   42,
   43,   44,   45,  272,   47,   40,   21,   41,  271,  272,
   44,   46,   23,   24,   40,   93,   59,   60,   40,   62,
   44,   93,   91,   37,   40,   59,   93,   41,   42,   43,
   44,   45,   37,   47,   45,   44,   41,   42,   43,   44,
   45,   41,   47,  272,   44,   59,   60,  125,   62,   41,
   93,   49,   44,   51,   59,   60,   91,   62,   37,   93,
   59,   72,   41,   42,   43,   44,   45,   37,   47,   93,
   41,   41,   42,   43,   44,   45,   44,   47,   53,   93,
   59,   60,   41,   62,   41,   44,  125,   44,   93,   59,
   60,   59,   62,   37,  152,  153,   93,   33,   42,   43,
   59,   45,   46,   47,   40,  257,   81,  272,   83,   45,
  260,  261,  262,  263,   93,   59,   60,   59,   62,  269,
   37,  123,  272,   93,   41,   42,   43,   37,   45,   46,
   47,   41,   42,   43,   93,   45,   46,   47,   61,  272,
   93,  272,  272,   60,   41,   62,  121,   44,   44,   37,
   60,  123,   62,   41,   42,   43,   37,   45,   46,   47,
   44,   42,   43,   59,   45,   46,   47,  272,  265,  266,
  267,  268,   60,  270,   62,   59,  123,  272,   59,   60,
   33,   62,  260,  261,  262,  263,  264,   40,   40,   59,
   41,  269,   45,   44,  272,  273,  274,  275,  276,  277,
  278,   41,  125,   40,   44,   59,  273,  274,  275,  276,
  277,  278,   61,  272,   37,  265,  266,  267,  268,   42,
   43,  125,   45,   46,   47,   91,  265,  266,  267,  268,
  273,  274,  275,  276,  277,  278,   59,   60,   41,   62,
  125,   44,   37,   41,   37,   46,   61,   42,   43,   42,
   45,   46,   47,   46,   47,  123,   59,  264,  125,  273,
  274,  275,  276,  277,  278,   60,  125,   62,  273,  274,
  275,  276,  277,  278,  265,  266,  267,   37,   59,   59,
   24,   65,   42,   43,  164,   45,   46,   47,   -1,   17,
   93,   -1,   -1,   -1,  273,  274,  275,  276,  277,  278,
   60,   -1,   62,  273,  274,  275,  276,  277,  278,   -1,
   -1,   -1,   -1,   -1,  273,  274,   -1,   -1,   -1,   -1,
   -1,  257,  258,  259,   41,   -1,   43,   44,   45,  273,
  274,  275,  276,  277,  278,   -1,  272,  260,  261,  262,
  263,  264,   59,   60,   -1,   62,  269,   -1,   -1,  272,
   -1,   41,   -1,   43,   44,   45,  273,  274,  275,  276,
  277,  278,  125,  273,  274,  275,  276,  277,  278,   59,
   60,   -1,   62,   -1,   -1,  125,   93,   -1,   -1,   -1,
   41,   -1,   -1,   44,   -1,  273,  274,  275,  276,  277,
  278,  125,  273,  274,  275,  276,  277,  278,   59,   60,
   -1,   62,   -1,   93,  257,  258,  259,   41,   -1,   -1,
   44,   -1,  265,  266,  267,   41,   -1,   -1,   44,  272,
   -1,   41,   -1,   -1,   44,   59,   60,  125,   62,   -1,
   -1,  125,   93,   59,   60,   -1,   62,   -1,   41,   59,
   60,   44,   62,   41,   -1,   58,   44,   -1,   -1,   -1,
  273,  274,  275,  276,  277,  278,   59,   -1,   -1,   93,
   -1,   59,   60,   -1,   62,   -1,   -1,   93,   41,   -1,
   -1,   44,   -1,   93,   -1,   -1,   -1,   90,  273,  274,
  275,  276,  277,  278,   -1,   -1,   59,   60,   -1,   62,
   93,   -1,   -1,   -1,   -1,   93,   -1,  260,  261,  262,
  263,  264,   -1,   -1,   -1,   -1,  269,   -1,   -1,  272,
  260,  261,  262,  263,   -1,  275,  276,  277,  278,  269,
   93,   -1,  272,   -1,   -1,   -1,  260,  261,  262,  263,
  143,  260,  261,  262,  263,  269,   37,   -1,  272,   -1,
  269,   42,   43,  272,   45,   46,   47,   -1,   -1,  162,
  163,  164,   -1,   -1,   -1,   -1,  273,  274,  275,  276,
  277,  278,  260,  261,  262,  263,  260,  261,  262,  263,
   -1,  269,   -1,   -1,  272,  269,   -1,   -1,  272,   48,
   -1,   -1,   -1,  273,  274,  275,  276,  277,  278,   58,
   -1,   -1,   48,   -1,   63,   -1,   65,   -1,   -1,   -1,
   -1,   -1,   58,   -1,   -1,   -1,   -1,   63,   -1,   65,
   -1,   -1,  273,  274,  275,  276,  277,  278,   -1,   -1,
   -1,   90,   -1,  260,  261,  262,  263,   -1,  265,  266,
  267,  268,  269,   -1,   90,  272,   -1,   -1,   -1,  273,
  274,  275,  276,  277,  278,   -1,   -1,  273,  274,  275,
  276,  277,  278,  273,  274,  275,  276,  277,  278,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  273,  274,   -1,   -1,  143,  273,  274,  275,  276,  277,
  278,   -1,   -1,  152,  153,   -1,   -1,  143,   -1,   -1,
   -1,   -1,   -1,  162,  163,  164,  152,  153,   56,   -1,
  273,  274,  275,  276,  277,  278,  162,  163,  164,   -1,
   -1,   -1,   70,   71,   72,   -1,   -1,   -1,   -1,   -1,
   78,   79,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   89,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   98,   99,  100,  101,  102,  103,  104,  105,  106,  107,
  108,  109,  110,  111,  112,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  125,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  145,
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
"programa : metodos VOID MAIN '(' ')' '{' declaraciones sentencias '}'",
"metodos : metodos metodo",
"metodos :",
"metodo : tipoSimple ID '(' parametros ')' '{' declaraciones cuerpoMetodo '}'",
"metodo : VOID ID '(' parametros ')' '{' declaraciones cuerpoMetodo '}'",
"metodo : declaracionVariable ';'",
"declaraciones : declaraciones declaracionVariable ';'",
"declaraciones :",
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
"sentencia : llamadaVariable '=' expresion ';'",
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

//#line 157 "../../src/sintactico/sintactico.y"

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
case 2:
//#line 42 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(1); ((List)yyval).add(val_peek(0)); }
break;
case 3:
//#line 43 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList();}
break;
//#line 689 "Parser.java"
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
