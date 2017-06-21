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
public final static short FOR=265;
public final static short INT=266;
public final static short DOUBLE=267;
public final static short CHAR=268;
public final static short STRUCT=269;
public final static short RETURN=270;
public final static short VOID=271;
public final static short MAIN=272;
public final static short ID=273;
public final static short Y=274;
public final static short O=275;
public final static short MAYORIGUALQUE=276;
public final static short MENORIGUALQUE=277;
public final static short DISTINTO=278;
public final static short IGUALDAD=279;
public final static short MENOS_UNARIO=280;
public final static short NEGACION=281;
public final static short CASTP=282;
public final static short MENORQUEELSE=283;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    4,    4,    4,    2,    2,    6,    6,
    6,    9,    7,    7,    3,    3,   10,   10,   10,   10,
   10,   10,   10,   10,   10,   15,   15,   12,   12,    8,
    8,    8,    8,   20,   20,   13,   13,   11,   11,   11,
   11,   11,   11,   11,   11,   11,   21,   11,   11,   11,
   11,   11,   11,   11,   11,   11,   11,   11,   11,   11,
   17,   17,   17,   17,   17,   17,   16,   16,   14,   22,
   22,   19,   19,   18,   18,    5,    5,    5,
};
final static short yylen[] = {                            2,
    9,    2,    0,    9,    9,    2,    3,    0,    3,    1,
    0,    2,    1,    0,    2,    1,    4,    5,    5,    7,
    3,    3,    2,    3,    9,    1,    1,    3,    1,    2,
    3,    5,    6,    3,    2,    3,    1,    1,    1,    1,
    1,    3,    3,    3,    3,    3,    0,    5,    3,    3,
    3,    3,    3,    2,    2,    3,    3,    4,    1,    4,
    3,    3,    3,    3,    3,    3,    3,    3,    4,    1,
    0,    4,    3,    3,    1,    1,    1,    1,
};
final static short yydefred[] = {                         3,
    0,    0,   76,   77,   78,    0,    0,    2,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    6,    0,    0,
    0,    0,    0,    0,    0,    0,   75,    0,   35,    0,
    0,    0,    0,    0,   10,    0,    0,   74,    0,    0,
   34,    8,   12,    0,    0,    0,   72,    0,    0,    8,
    9,    8,   39,   40,   41,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   16,    0,    0,
   47,    0,    0,    0,    0,   59,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    1,   15,    7,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   23,    0,    0,    0,    0,   22,
    0,   21,    0,    0,    0,   24,    0,    0,    0,   57,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   56,    0,    5,    4,    0,    0,
    0,    0,   69,    0,   17,   60,    0,    0,   29,   18,
    0,    0,    0,    0,   27,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   28,   20,    0,    0,    0,
    0,    0,    0,    0,    0,   25,
};
final static short yydgoto[] = {                          1,
    2,   49,  107,    8,   19,   34,  108,   67,   35,   68,
   69,  150,   75,   76,  153,   71,  155,   16,   17,   21,
  106,  118,
};
final static short yysindex[] = {                         0,
    0,   46,    0,    0,    0, -117, -119,    0,  -90,  -26,
  -81,   26,   28,   30, -205,   45, -176,    0,  -89,   44,
   27,   72,  -13,  -13,   38, -152,    0,   45,    0,  -89,
   64,   43, -124,  -25,    0,  120,   78,    0,   45, -176,
    0,    0,    0,   49,  -13,   57,    0,   45,  673,    0,
    0,    0,    0,    0,    0,  110,  110,  133,  142,  151,
  110,  157,  110,  830,  110,  700,  139,    0,  512,  145,
    0,  673,  673,  732,  -29,    0,  -21,  110,  110,  861,
  483,  110,  -41,  160,  534,  -41,    0,    0,    0,  110,
  110,  110,  110,  110,  110,  110,  110,  110,  110,  110,
  110,  110,  110,  -68,    0,  110,  861,   82,   83,    0,
  110,    0,  541,  563,  156,    0,  173,  181,  110,    0,
  732,  732,   41,   41,   41,   41,  570,   41,   -6,   -6,
  -41,  -41,  -41,  605,    0,  674,    0,    0,  732,  821,
  821,  110,    0,  -41,    0,    0,  110,  861,    0,    0,
  -17,  704,  164,    0,    0,   41,  760,  821,  110,  110,
  110,  110,  110,  110,  861,    0,    0,   41,   41,   41,
   41,  732,   41,  197,  821,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  103,    0,  180,    0,    0,    0,    0,
    0,    0,  124,  124,    0,    0,    0,  189,    0,    0,
    0,    0,    0,    0,    0,    0,  -16,    0,  199,    0,
    0,    0,    0,    0,    0,    0,    0,  227,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  -33,    0,    0,    0,    0,    0,    0,    0,  621,
    0,  162,  162,   12,    0,    0,    0,    0,    0,    0,
    0,  247,    2,    0,    0,   32,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  172,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  257,    0,    0,    0,
  130,  629,  159,  289,  361,  416,    0,  426,  223,  259,
   67,   97,  134,    0,    0,    0,    0,    0,   92,    0,
    0,    0,    0,  169,    0,    0,    0,    0,    0,    0,
  791,    0,    0,   -8,    0,  451,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  -42,   -5,   60,
   86,  242,  711,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  -28,  -46,    0,  355,  299,  254,   94,  283,  -59,
 1085,  -40,  -23,  997,    0,  190,    0,   50,   87,    0,
    0,    0,
};
final static int YYTABLESIZE=1249;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         38,
   15,   15,   66,   38,  104,   11,   88,   38,   38,   38,
   38,   38,   38,   38,  111,   44,   63,   50,   45,   50,
  115,   72,  111,   73,   47,   38,   38,   38,   38,  110,
  102,   47,   18,   77,   55,  100,   47,  112,   55,  104,
  101,   55,   55,   55,   55,   55,   55,   88,   55,  103,
   26,   25,   37,   64,   51,   37,   51,   38,  117,   38,
   55,   55,   55,   55,   54,   22,   28,   23,   54,   24,
   37,   54,   54,   54,   54,   54,   54,  102,   54,   39,
  149,  149,  100,   98,  103,   99,  104,  101,   26,   48,
   54,   54,   54,   54,   55,   10,   27,   88,  149,   43,
  151,  157,   29,   43,   20,  174,   43,   43,   43,   43,
   43,   43,   32,   43,   31,  149,   40,  167,   65,   52,
   38,   52,   41,   47,   54,   43,   43,   43,   43,   44,
   37,  103,   36,   44,  176,   36,   44,   44,   44,   44,
   44,   44,   65,   44,   66,   53,   75,   53,   43,   64,
   36,   30,   12,   13,   63,   44,   44,   44,   44,   43,
   46,   75,   67,   45,   11,   42,   46,   11,   15,   67,
   46,   50,   78,   46,   46,   46,   46,   46,   46,   52,
   46,   79,   14,   27,    3,    4,    5,    6,   67,   44,
   80,   50,   46,   46,   46,   46,   82,   89,   50,   50,
  119,   58,   50,  105,  135,   58,  137,  138,   58,   58,
   58,   58,   58,   58,  142,   58,  111,   50,   50,   50,
   50,  143,  165,   38,   38,   38,   46,   58,   58,   58,
   58,   50,   50,   50,   50,   50,   50,  175,   30,   38,
   38,   38,   38,   38,   38,   38,  158,   31,   47,   47,
   47,   50,    3,    4,    5,   42,   73,   32,   55,   55,
   55,   58,   42,   42,   47,   42,   42,   42,   51,   51,
   51,   51,   51,   51,   55,   55,   55,   55,   55,   55,
   55,   42,   42,   42,   42,   33,   14,   71,   54,   54,
   54,   45,    3,    4,    5,    6,   13,   70,   45,   45,
   61,   45,   45,   45,   54,   54,   54,   54,   54,   54,
   54,    3,    4,    5,    6,   42,    7,   45,   45,   45,
   45,   51,   36,   43,   43,   43,  109,   51,   51,   51,
    0,  154,   51,   52,   52,   52,   52,   52,   52,   43,
   43,   43,   43,   43,   43,   43,    0,   51,   51,   51,
   51,   45,    0,   44,   44,   44,    9,    0,    0,   53,
   53,   53,   53,   53,   53,    0,   53,   54,   55,   44,
   44,   44,   44,   44,   44,   44,    0,   33,   33,    0,
    0,   51,   62,    0,    0,    0,   67,   67,   67,    0,
   46,   46,   46,   52,    0,    0,    0,    0,    0,   33,
   52,   52,   67,    0,   52,    0,   46,   46,   46,   46,
   46,   46,   46,    0,    0,   50,   50,   50,   84,   52,
   52,   52,   52,    0,    0,   58,   58,   58,    0,    0,
    0,   50,   50,   50,   50,   50,   50,   50,    0,    0,
    0,   58,   58,   58,   58,   58,   58,   58,   53,    0,
    0,    0,    0,   52,    0,   53,   53,    0,   49,   53,
    0,    0,    0,    0,    0,   49,   49,    0,    0,   49,
    0,    0,    0,    0,   53,   53,   53,   53,    0,   42,
   42,   42,    0,   48,   49,   49,   49,   49,    0,    0,
   48,   48,    0,    0,   48,   42,   42,   42,   42,   42,
   42,   42,    0,    0,    0,    0,    0,    0,   53,   48,
   48,   48,   48,    0,    0,   45,   45,   45,   49,  102,
    0,    0,    0,    0,  100,   98,    0,   99,  104,  101,
    0,   45,   45,   45,   45,   45,   45,   45,    0,    0,
    0,  116,   97,   48,    0,   51,   51,   51,  102,    0,
    0,    0,    0,  100,   98,    0,   99,  104,  101,    0,
    0,   51,   51,   51,   51,   51,   51,   51,    0,    0,
  102,   97,   96,  103,  120,  100,   98,  102,   99,  104,
  101,  140,  100,   98,    0,   99,  104,  101,    0,    0,
    0,    0,    0,   97,    0,    0,    0,    0,    0,  102,
   97,    0,  103,  141,  100,   98,  102,   99,  104,  101,
    0,  100,   98,    0,   99,  104,  101,   52,   52,   52,
    0,    0,   97,    0,  103,    0,    0,    0,  145,   97,
    0,  103,    0,   52,   52,   52,   52,   52,   52,   52,
    0,  102,    0,    0,    0,    0,  100,   98,    0,   99,
  104,  101,    0,  103,    0,    0,    0,   59,    0,    0,
  103,   68,   59,   59,   97,   59,   59,   59,   68,    0,
    0,    0,   53,   53,   53,    0,    0,    0,    0,    0,
   59,   59,   49,   49,   49,    0,    0,   68,   53,   53,
   53,   53,   53,   53,   53,  103,    0,  146,   49,   49,
   49,   49,   49,   49,   49,   65,    0,   48,   48,   48,
  102,   59,   64,    0,    0,  100,   98,   63,   99,  104,
  101,    0,    0,   48,   48,   48,   48,   48,   48,   48,
    0,    0,   65,   97,    0,  147,    0,    0,    0,   64,
  102,    0,    0,    0,   63,  100,   98,    0,   99,  104,
  101,    0,    0,    0,    0,    0,   90,   91,   92,   93,
   94,   95,    0,  164,  103,  163,    0,    0,  102,   62,
   49,    0,   49,  100,   98,    0,   99,  104,  101,    0,
    0,    0,    0,    0,    0,   90,   91,   92,   93,   94,
   95,   97,   65,    0,  103,    0,    0,    0,    0,   64,
    0,    0,    0,    0,   63,    0,    0,   90,   91,   92,
   93,   94,   95,    0,   90,   91,   92,   93,   94,   95,
    0,    0,  103,   19,   87,    0,    0,    0,    0,    0,
   19,   19,    0,    0,    0,   19,   90,   91,   92,   93,
   94,   95,    0,   90,   91,   92,   93,   94,   95,   19,
    0,    0,    0,   65,    0,    0,    0,    0,    0,    0,
   64,    0,   65,    0,    0,   63,    0,    0,    0,   64,
    0,    0,    0,    0,   63,    0,    0,    0,   90,   91,
   92,   93,   94,   95,  166,   68,   68,   68,    0,    0,
    0,    0,    0,   65,   59,   59,   59,   59,   59,   59,
   64,   68,    0,    0,    0,   63,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   19,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   53,
   54,   55,   56,   57,   58,   59,    0,   60,    3,    4,
    5,    6,   61,  148,    0,   62,    0,   90,   91,   92,
   93,   94,   95,    0,    0,    0,   53,   54,   55,   56,
   57,   58,   59,    0,   60,    0,    0,    0,    0,   61,
    0,    0,   62,    0,    0,    0,    0,   90,   91,  159,
  160,  161,  162,    0,   49,   49,   49,   49,   49,   49,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   90,   91,   92,   93,   94,
   95,    0,    0,    0,    0,    0,   53,   54,   55,   56,
   57,   58,   59,    0,   60,    0,    0,    0,    0,   61,
    0,    0,   62,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   70,    0,   19,   19,   19,
   19,   19,   19,   19,    0,   19,    0,    0,    0,    0,
   19,    0,   70,   19,    0,    0,    0,    0,   70,   70,
    0,    0,    0,    0,    0,    0,   70,   53,   54,   55,
   56,   57,   58,   59,    0,   60,   53,   54,   55,    0,
   61,    0,    0,   62,    0,    3,    4,    5,    0,    0,
    0,    0,   62,   70,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   53,   54,   55,
   56,   57,   58,   59,    0,   60,    0,    0,    0,    0,
   61,    0,    0,   62,    0,    0,   70,   70,    0,    0,
   74,   74,    0,    0,   70,   81,    0,   83,   85,   86,
    0,    0,    0,   70,   70,    0,    0,    0,    0,    0,
    0,   70,  113,  114,    0,    0,   74,    0,    0,    0,
    0,   70,    0,    0,  121,  122,  123,  124,  125,  126,
  127,  128,  129,  130,  131,  132,  133,  134,    0,    0,
  136,    0,    0,    0,    0,  139,    0,    0,    0,    0,
    0,    0,    0,  144,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  152,    0,    0,    0,
    0,  156,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  168,  169,  170,  171,  172,  173,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   91,   91,   49,   37,   46,  123,   66,   41,   42,   43,
   44,   45,   46,   47,   44,   41,   59,   60,   44,   62,
   80,   50,   44,   52,   33,   59,   60,   61,   62,   59,
   37,   40,   59,   57,   33,   42,   45,   59,   37,   46,
   47,   40,   41,   42,   43,   44,   45,  107,   47,   91,
   59,  257,   41,   59,   60,   44,   62,   91,   82,   93,
   59,   60,   61,   62,   33,   40,   17,   40,   37,   40,
   59,   40,   41,   42,   43,   44,   45,   37,   47,   30,
  140,  141,   42,   43,   91,   45,   46,   47,   44,   40,
   59,   60,   61,   62,   93,    2,  273,  157,  158,   33,
  141,  148,   59,   37,   11,  165,   40,   41,   42,   43,
   44,   45,   41,   47,   21,  175,   30,  158,   59,   60,
  273,   62,   59,   37,   93,   59,   60,   61,   62,   33,
   93,   91,   41,   37,  175,   44,   40,   41,   42,   43,
   44,   45,   33,   47,   59,   60,   44,   62,  273,   40,
   59,  125,  272,  273,   45,   59,   60,   61,   62,   93,
   41,   59,   33,   44,   41,  123,   33,   44,   91,   40,
   37,  123,   40,   40,   41,   42,   43,   44,   45,  123,
   47,   40,  273,  273,  266,  267,  268,  269,   59,   93,
   40,   33,   59,   60,   61,   62,   40,   59,   40,   41,
   41,   33,   44,   59,  273,   37,  125,  125,   40,   41,
   42,   43,   44,   45,   59,   47,   44,   59,   60,   61,
   62,   41,   59,  257,  258,  259,   93,   59,   60,   61,
   62,  274,  275,  276,  277,  278,  279,   41,   59,  273,
  274,  275,  276,  277,  278,  279,  264,   59,  257,  258,
  259,   93,  266,  267,  268,   33,  273,   59,  257,  258,
  259,   93,   40,   41,  273,   43,   44,   45,  274,  275,
  276,  277,  278,  279,  273,  274,  275,  276,  277,  278,
  279,   59,   60,   61,   62,   59,  125,   41,  257,  258,
  259,   33,  266,  267,  268,  269,  125,   41,   40,   41,
   59,   43,   44,   45,  273,  274,  275,  276,  277,  278,
  279,  266,  267,  268,  269,   93,  271,   59,   60,   61,
   62,   33,   24,  257,  258,  259,   73,   45,   40,   41,
   -1,  142,   44,  274,  275,  276,  277,  278,  279,  273,
  274,  275,  276,  277,  278,  279,   -1,   59,   60,   61,
   62,   93,   -1,  257,  258,  259,    2,   -1,   -1,  274,
  275,  276,  277,  278,  279,   -1,  257,  258,  259,  273,
  274,  275,  276,  277,  278,  279,   -1,   23,   24,   -1,
   -1,   93,  273,   -1,   -1,   -1,  257,  258,  259,   -1,
  257,  258,  259,   33,   -1,   -1,   -1,   -1,   -1,   45,
   40,   41,  273,   -1,   44,   -1,  273,  274,  275,  276,
  277,  278,  279,   -1,   -1,  257,  258,  259,   64,   59,
   60,   61,   62,   -1,   -1,  257,  258,  259,   -1,   -1,
   -1,  273,  274,  275,  276,  277,  278,  279,   -1,   -1,
   -1,  273,  274,  275,  276,  277,  278,  279,   33,   -1,
   -1,   -1,   -1,   93,   -1,   40,   41,   -1,   33,   44,
   -1,   -1,   -1,   -1,   -1,   40,   41,   -1,   -1,   44,
   -1,   -1,   -1,   -1,   59,   60,   61,   62,   -1,  257,
  258,  259,   -1,   33,   59,   60,   61,   62,   -1,   -1,
   40,   41,   -1,   -1,   44,  273,  274,  275,  276,  277,
  278,  279,   -1,   -1,   -1,   -1,   -1,   -1,   93,   59,
   60,   61,   62,   -1,   -1,  257,  258,  259,   93,   37,
   -1,   -1,   -1,   -1,   42,   43,   -1,   45,   46,   47,
   -1,  273,  274,  275,  276,  277,  278,  279,   -1,   -1,
   -1,   59,   60,   93,   -1,  257,  258,  259,   37,   -1,
   -1,   -1,   -1,   42,   43,   -1,   45,   46,   47,   -1,
   -1,  273,  274,  275,  276,  277,  278,  279,   -1,   -1,
   37,   60,   61,   91,   41,   42,   43,   37,   45,   46,
   47,   41,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   -1,   -1,   -1,   60,   -1,   -1,   -1,   -1,   -1,   37,
   60,   -1,   91,   41,   42,   43,   37,   45,   46,   47,
   -1,   42,   43,   -1,   45,   46,   47,  257,  258,  259,
   -1,   -1,   60,   -1,   91,   -1,   -1,   -1,   59,   60,
   -1,   91,   -1,  273,  274,  275,  276,  277,  278,  279,
   -1,   37,   -1,   -1,   -1,   -1,   42,   43,   -1,   45,
   46,   47,   -1,   91,   -1,   -1,   -1,   37,   -1,   -1,
   91,   33,   42,   43,   60,   45,   46,   47,   40,   -1,
   -1,   -1,  257,  258,  259,   -1,   -1,   -1,   -1,   -1,
   60,   61,  257,  258,  259,   -1,   -1,   59,  273,  274,
  275,  276,  277,  278,  279,   91,   -1,   93,  273,  274,
  275,  276,  277,  278,  279,   33,   -1,  257,  258,  259,
   37,   91,   40,   -1,   -1,   42,   43,   45,   45,   46,
   47,   -1,   -1,  273,  274,  275,  276,  277,  278,  279,
   -1,   -1,   33,   60,   -1,   62,   -1,   -1,   -1,   40,
   37,   -1,   -1,   -1,   45,   42,   43,   -1,   45,   46,
   47,   -1,   -1,   -1,   -1,   -1,  274,  275,  276,  277,
  278,  279,   -1,   60,   91,   62,   -1,   -1,   37,   59,
   60,   -1,   62,   42,   43,   -1,   45,   46,   47,   -1,
   -1,   -1,   -1,   -1,   -1,  274,  275,  276,  277,  278,
  279,   60,   33,   -1,   91,   -1,   -1,   -1,   -1,   40,
   -1,   -1,   -1,   -1,   45,   -1,   -1,  274,  275,  276,
  277,  278,  279,   -1,  274,  275,  276,  277,  278,  279,
   -1,   -1,   91,   33,  125,   -1,   -1,   -1,   -1,   -1,
   40,   41,   -1,   -1,   -1,   45,  274,  275,  276,  277,
  278,  279,   -1,  274,  275,  276,  277,  278,  279,   59,
   -1,   -1,   -1,   33,   -1,   -1,   -1,   -1,   -1,   -1,
   40,   -1,   33,   -1,   -1,   45,   -1,   -1,   -1,   40,
   -1,   -1,   -1,   -1,   45,   -1,   -1,   -1,  274,  275,
  276,  277,  278,  279,  125,  257,  258,  259,   -1,   -1,
   -1,   -1,   -1,   33,  274,  275,  276,  277,  278,  279,
   40,  273,   -1,   -1,   -1,   45,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  125,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,
  258,  259,  260,  261,  262,  263,   -1,  265,  266,  267,
  268,  269,  270,  123,   -1,  273,   -1,  274,  275,  276,
  277,  278,  279,   -1,   -1,   -1,  257,  258,  259,  260,
  261,  262,  263,   -1,  265,   -1,   -1,   -1,   -1,  270,
   -1,   -1,  273,   -1,   -1,   -1,   -1,  274,  275,  276,
  277,  278,  279,   -1,  274,  275,  276,  277,  278,  279,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  274,  275,  276,  277,  278,
  279,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,  260,
  261,  262,  263,   -1,  265,   -1,   -1,   -1,   -1,  270,
   -1,   -1,  273,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   49,   -1,  257,  258,  259,
  260,  261,  262,  263,   -1,  265,   -1,   -1,   -1,   -1,
  270,   -1,   66,  273,   -1,   -1,   -1,   -1,   72,   73,
   -1,   -1,   -1,   -1,   -1,   -1,   80,  257,  258,  259,
  260,  261,  262,  263,   -1,  265,  257,  258,  259,   -1,
  270,   -1,   -1,  273,   -1,  266,  267,  268,   -1,   -1,
   -1,   -1,  273,  107,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,
  260,  261,  262,  263,   -1,  265,   -1,   -1,   -1,   -1,
  270,   -1,   -1,  273,   -1,   -1,  140,  141,   -1,   -1,
   56,   57,   -1,   -1,  148,   61,   -1,   63,   64,   65,
   -1,   -1,   -1,  157,  158,   -1,   -1,   -1,   -1,   -1,
   -1,  165,   78,   79,   -1,   -1,   82,   -1,   -1,   -1,
   -1,  175,   -1,   -1,   90,   91,   92,   93,   94,   95,
   96,   97,   98,   99,  100,  101,  102,  103,   -1,   -1,
  106,   -1,   -1,   -1,   -1,  111,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  119,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  142,   -1,   -1,   -1,
   -1,  147,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  159,  160,  161,  162,  163,  164,
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
"CTE_CARACTER","READ","WRITE","WHILE","IF","ELSE","FOR","INT","DOUBLE","CHAR",
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
"sentencia : FOR '(' sentencia ';' expresionFor ';' sentencia ')' cuerpoCondicional",
"expresionFor : expresionLogica",
"expresionFor : expresionComparacion",
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
"$$1 :",
"expresion : expresionLogica $$1 expresion '>' expresion",
"expresion : expresion '<' expresion",
"expresion : expresion MAYORIGUALQUE expresion",
"expresion : expresion MENORIGUALQUE expresion",
"expresion : expresion DISTINTO expresion",
"expresion : expresion IGUALDAD expresion",
"expresion : '!' expresion",
"expresion : '-' expresion",
"expresion : expresion '.' ID",
"expresion : '(' expresion ')'",
"expresion : '(' tipoSimple ')' expresion",
"expresion : invocacion",
"expresion : expresion '[' expresion ']'",
"expresionComparacion : expresion '>' expresion",
"expresionComparacion : expresion '<' expresion",
"expresionComparacion : expresion MAYORIGUALQUE expresion",
"expresionComparacion : expresion MENORIGUALQUE expresion",
"expresionComparacion : expresion DISTINTO expresion",
"expresionComparacion : expresion IGUALDAD expresion",
"expresionLogica : expresion Y expresion",
"expresionLogica : expresion O expresion",
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

//#line 254 "../../src/sintactico/sintactico.y"

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
//#line 627 "Parser.java"
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
																											List<Definicion> listaDefiniciones = (List<Definicion>)val_peek(0);
																											for(int i = 0; i < listaDefiniciones.size(); i++) {
																												Definicion elemento = listaDefiniciones.get(i);
																											    lista.add(elemento);																
																											}
																										}
break;
case 3:
//#line 58 "../../src/sintactico/sintactico.y"
{	yyval = new ArrayList<Definicion>(); }
break;
case 4:
//#line 61 "../../src/sintactico/sintactico.y"
{ 	List<Definicion> lista = new ArrayList();
																											lista.add(new DefFuncion(lexico.getLine(), lexico.getColumn(), (String) val_peek(7),
																												new TipoFuncion((List<DefVariable>)val_peek(5), (Tipo)val_peek(8)), 
																												(List<DefVariable>)val_peek(2), (List<Sentencia>)val_peek(1)));
																											yyval = lista; 
																										}
break;
case 5:
//#line 67 "../../src/sintactico/sintactico.y"
{	List<Definicion> lista = new ArrayList();
																											lista.add(new DefFuncion(lexico.getLine(), lexico.getColumn(),  (String) val_peek(7),
																												new TipoFuncion((List<DefVariable>)val_peek(5), TipoVoid.getInstancia()), 
																												(List<DefVariable>)val_peek(2), (List<Sentencia>)val_peek(1)));
																											yyval = lista;
																										}
break;
case 6:
//#line 73 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(1);  }
break;
case 7:
//#line 76 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(2);
																											List<DefVariable> lista = (List<DefVariable>)yyval;																											
																											for(DefVariable elemento : (List<DefVariable>)val_peek(1))
																												lista.add(elemento); 
																										}
break;
case 8:
//#line 81 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<DefVariable>();}
break;
case 9:
//#line 84 "../../src/sintactico/sintactico.y"
{	yyval = val_peek(2); 
																											List<DefVariable> lista = (List<DefVariable>)yyval;																												
																											for(DefVariable elemento : (List<DefVariable>) val_peek(0))
																												lista.add(elemento);																												
																										}
break;
case 10:
//#line 89 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(0); }
break;
case 11:
//#line 90 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<DefVariable>(); }
break;
case 12:
//#line 93 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<DefVariable>();
																											((List<DefVariable>) yyval).add(new DefVariable(lexico.getLine(), 
																												lexico.getColumn(), (String)val_peek(0), (Tipo)val_peek(1)));  
																										}
break;
case 13:
//#line 99 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(0); }
break;
case 14:
//#line 100 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<Sentencia>(); }
break;
case 15:
//#line 103 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(1);((List<Sentencia>)yyval).add((Sentencia)val_peek(0)); }
break;
case 16:
//#line 104 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<Sentencia>(); ((List<Sentencia>)yyval).add((Sentencia)val_peek(0)); }
break;
case 17:
//#line 107 "../../src/sintactico/sintactico.y"
{ 	yyval = new Asignacion(lexico.getLine(), lexico.getColumn(), (Expresion)val_peek(3), (Expresion)val_peek(1)); }
break;
case 18:
//#line 108 "../../src/sintactico/sintactico.y"
{ 	yyval = new SentenciaWhile(lexico.getLine(), lexico.getColumn(), (Expresion)val_peek(2), (List<Sentencia>)val_peek(0)); }
break;
case 19:
//#line 109 "../../src/sintactico/sintactico.y"
{ 	yyval = new SentenciaIf(lexico.getLine(), lexico.getColumn(), (Expresion)val_peek(2), (List<Sentencia>)val_peek(0), new ArrayList()); }
break;
case 20:
//#line 110 "../../src/sintactico/sintactico.y"
{ 	yyval = new SentenciaIf(lexico.getLine(), lexico.getColumn(), (Expresion)val_peek(4), (List<Sentencia>)val_peek(2), (List<Sentencia>)val_peek(0)); }
break;
case 21:
//#line 111 "../../src/sintactico/sintactico.y"
{ 	yyval = new Escritura(lexico.getLine(), lexico.getColumn(),(List<Expresion>)val_peek(1)); }
break;
case 22:
//#line 112 "../../src/sintactico/sintactico.y"
{ 	yyval = new Lectura(lexico.getLine(), lexico.getColumn(),(List<Expresion>)val_peek(1)); }
break;
case 23:
//#line 113 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(1); }
break;
case 24:
//#line 114 "../../src/sintactico/sintactico.y"
{ 	yyval = new Return(lexico.getLine(), lexico.getColumn(), (Expresion)val_peek(1));}
break;
case 25:
//#line 115 "../../src/sintactico/sintactico.y"
{   yyval = new SentenciaFor(lexico.getLine(), lexico.getColumn(), (Sentencia)val_peek(6), (Expresion)val_peek(4), (Sentencia)val_peek(2), (List<Sentencia>)val_peek(0));}
break;
case 26:
//#line 118 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(0); }
break;
case 27:
//#line 119 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(0); }
break;
case 28:
//#line 122 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(1);	}
break;
case 29:
//#line 123 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<Sentencia>(); ((List<Sentencia>)yyval).add((Sentencia)val_peek(0));  }
break;
case 30:
//#line 126 "../../src/sintactico/sintactico.y"
{ 	List<DefVariable> variables = new ArrayList<DefVariable>();
																											for(String aux: (List<String>)val_peek(0))
																												variables.add(new DefVariable(lexico.getLine(), lexico.getColumn(), aux, (Tipo)val_peek(1)));																												 
																											yyval = variables; 
																										}
break;
case 31:
//#line 131 "../../src/sintactico/sintactico.y"
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
case 32:
//#line 140 "../../src/sintactico/sintactico.y"
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
case 33:
//#line 149 "../../src/sintactico/sintactico.y"
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
case 34:
//#line 165 "../../src/sintactico/sintactico.y"
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
case 35:
//#line 177 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(1); }
break;
case 36:
//#line 180 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(2); 
																											List<Expresion> lista = (List<Expresion>)yyval;
																											Expresion elemento = (Expresion)val_peek(0);
																											lista.add(elemento);																																																								
																										}
break;
case 37:
//#line 185 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<Expresion>(); ((List<Expresion>)yyval).add((Expresion)val_peek(0));  	}
break;
case 38:
//#line 188 "../../src/sintactico/sintactico.y"
{ 	yyval = new Variable(lexico.getLine(), lexico.getColumn(), (String) val_peek(0)); 	}
break;
case 39:
//#line 189 "../../src/sintactico/sintactico.y"
{ 	yyval = new LiteralEntero(lexico.getLine(), lexico.getColumn(), (Integer) val_peek(0));		}
break;
case 40:
//#line 190 "../../src/sintactico/sintactico.y"
{ 	yyval = new LiteralReal(lexico.getLine(), lexico.getColumn(), (Double) val_peek(0));	}
break;
case 41:
//#line 191 "../../src/sintactico/sintactico.y"
{ 	yyval = new LiteralCaracter(lexico.getLine(), lexico.getColumn(), (Character)val_peek(0));		}
break;
case 42:
//#line 192 "../../src/sintactico/sintactico.y"
{ 	yyval = new Aritmetica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "+", (Expresion) val_peek(0));		}
break;
case 43:
//#line 193 "../../src/sintactico/sintactico.y"
{ 	yyval = new Aritmetica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "*", (Expresion) val_peek(0));		}
break;
case 44:
//#line 194 "../../src/sintactico/sintactico.y"
{ 	yyval = new Aritmetica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "/", (Expresion) val_peek(0));		}
break;
case 45:
//#line 195 "../../src/sintactico/sintactico.y"
{ 	yyval = new Aritmetica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "-", (Expresion) val_peek(0));		}
break;
case 46:
//#line 196 "../../src/sintactico/sintactico.y"
{ 	yyval = new Aritmetica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "%", (Expresion) val_peek(0));		}
break;
case 47:
//#line 197 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(0); }
break;
case 48:
//#line 197 "../../src/sintactico/sintactico.y"
{ 	yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(4), ">", (Expresion) val_peek(2));	}
break;
case 49:
//#line 198 "../../src/sintactico/sintactico.y"
{ 	yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "<", (Expresion) val_peek(0));	}
break;
case 50:
//#line 199 "../../src/sintactico/sintactico.y"
{ 	yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), ">=", (Expresion) val_peek(0));	}
break;
case 51:
//#line 200 "../../src/sintactico/sintactico.y"
{ 	yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "<=", (Expresion) val_peek(0));	}
break;
case 52:
//#line 201 "../../src/sintactico/sintactico.y"
{ 	yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "!=", (Expresion) val_peek(0));	}
break;
case 53:
//#line 202 "../../src/sintactico/sintactico.y"
{ 	yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "==", (Expresion) val_peek(0));	}
break;
case 54:
//#line 203 "../../src/sintactico/sintactico.y"
{ 	yyval = new Negacion(lexico.getLine(), lexico.getColumn(),  "!", (Expresion) val_peek(0));	}
break;
case 55:
//#line 204 "../../src/sintactico/sintactico.y"
{ 	yyval = new MenosUnario(lexico.getLine(), lexico.getColumn(),  "-",(Expresion) val_peek(0));	}
break;
case 56:
//#line 205 "../../src/sintactico/sintactico.y"
{ 	yyval = new AccesoCampo(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), (String) val_peek(0));	}
break;
case 57:
//#line 206 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(1);}
break;
case 58:
//#line 207 "../../src/sintactico/sintactico.y"
{ 	yyval = new Cast(lexico.getLine(), lexico.getColumn(), (Tipo) val_peek(2), (Expresion) val_peek(0));	}
break;
case 59:
//#line 208 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(0);}
break;
case 60:
//#line 209 "../../src/sintactico/sintactico.y"
{ 	yyval = new AccesoArray(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(3), (Expresion) val_peek(1)); }
break;
case 61:
//#line 212 "../../src/sintactico/sintactico.y"
{ 	yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), ">", (Expresion) val_peek(0));	}
break;
case 62:
//#line 213 "../../src/sintactico/sintactico.y"
{ 	yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "<", (Expresion) val_peek(0));	}
break;
case 63:
//#line 214 "../../src/sintactico/sintactico.y"
{ 	yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), ">=", (Expresion) val_peek(0));	}
break;
case 64:
//#line 215 "../../src/sintactico/sintactico.y"
{ 	yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "<=", (Expresion) val_peek(0));	}
break;
case 65:
//#line 216 "../../src/sintactico/sintactico.y"
{ 	yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "!=", (Expresion) val_peek(0));	}
break;
case 66:
//#line 217 "../../src/sintactico/sintactico.y"
{ 	yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "==", (Expresion) val_peek(0));	}
break;
case 67:
//#line 219 "../../src/sintactico/sintactico.y"
{ 	yyval = new Logica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "&&", (Expresion) val_peek(0));	}
break;
case 68:
//#line 220 "../../src/sintactico/sintactico.y"
{ 	yyval = new Logica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "||", (Expresion) val_peek(0));	}
break;
case 69:
//#line 222 "../../src/sintactico/sintactico.y"
{ 	yyval = new Invocacion(lexico.getLine(), lexico.getColumn(), new Variable(lexico.getLine(), lexico.getColumn(), (String)val_peek(3)),(List<Expresion>)val_peek(1)); 	}
break;
case 70:
//#line 225 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(0);}
break;
case 71:
//#line 226 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<Expresion>();	}
break;
case 72:
//#line 230 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(0); 
																											List<Integer> lista = (List<Integer>)yyval;
																											Integer elemento = (Integer)val_peek(2);
																											lista.add(elemento);
																										}
break;
case 73:
//#line 235 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<Integer>(); ((List<Integer>)yyval).add((Integer)val_peek(1)); 	}
break;
case 74:
//#line 238 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(2); 
																											List<String> lista = (List<String>)yyval;
																											String elemento = (String)val_peek(0);
																											if(!lista.contains(elemento))
																												lista.add(elemento);
																											else
																												new TipoError(lexico.getLine(), lexico.getColumn(),"Identificador duplicado -> " + elemento);																																																							
																										}
break;
case 75:
//#line 246 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList(); ((List<String>)yyval).add(((String) val_peek(0))); 	}
break;
case 76:
//#line 249 "../../src/sintactico/sintactico.y"
{ 	yyval = TipoEntero.getInstancia(); 	}
break;
case 77:
//#line 250 "../../src/sintactico/sintactico.y"
{ 	yyval = TipoReal.getInstancia(); 	}
break;
case 78:
//#line 251 "../../src/sintactico/sintactico.y"
{ 	yyval = TipoCaracter.getInstancia(); 	}
break;
//#line 1182 "Parser.java"
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
