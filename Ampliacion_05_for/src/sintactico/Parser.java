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
    3,    3,    2,    3,    8,    1,    1,    3,    1,    2,
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
    0,   38,    0,    0,    0,    0,    0,   16,    0,    0,
   47,    0,    0,    0,    0,   59,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    1,   15,    7,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   23,    0,    0,    0,    0,   22,
    0,   21,    0,    0,    0,   24,    0,   57,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   56,    0,    0,    0,    5,    4,    0,    0,
    0,    0,    0,    0,   27,    0,   17,   60,   69,    0,
    0,   29,   18,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   28,   20,    0,   25,
};
final static short yydgoto[] = {                          1,
    2,   49,  107,    8,   19,   34,  108,   67,   35,   68,
   69,  153,   75,   76,  143,   71,  145,   16,   17,   21,
  106,  135,
};
final static short yysindex[] = {                         0,
    0,   58,    0,    0,    0, -104,  -91,    0,  -90,  -38,
 -116,  -17,   -5,   24, -220,   28, -188,    0,  -89,   30,
   -8,   61, -131, -131,   20, -177,    0,   28,    0,  -89,
   68,   38, -155,   85,    0,  121,   52,    0,   28, -188,
    0,    0,    0,   53, -131,   69,    0,   28,  563,    0,
    0,    0,    0,    0,    0,  -20,  -20,  105,  145,  157,
  -20,    0,  -20,  804,  -20,  614,  139,    0,  417,  140,
    0,  563,  563,  671,  -27,    0,    3,  -20,  -20,  697,
  428,  -24,  159,  449,  -24,    0,    0,    0,  -20,  -20,
  -20,  -20,  -20,  -20,  -20,  -20,  -20,  -20,  -20,  -20,
  -20,  -20,  -72,  -20,    0,  -20,  697,   80,   83,    0,
  -20,    0,  470,  481,  -20,    0,  -20,    0,  671,  671,
    6,    6,    6,    6,  492,    6,  757,  757,  -24,  -24,
  -24,  513,    0,  163,  168,  539,    0,    0,  671,  739,
  739,  580,  152,    0,    0,  -24,    0,    0,    0,  -20,
  697,    0,    0,  -49,  -20,  -20,  -20,  -20,  -20,  -20,
  -20,    6,  760,  739,    6,    6,    6,    6,  671,    6,
  591,    0,    0,  739,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   27,    0,  160,    0,    0,    0,    0,
    0,    0,  122,  122,    0,    0,    0,  162,    0,    0,
    0,    0,    0,    0,    0,    0,  -57,    0,  164,    0,
    0,    0,    0,    0,    0,    0,    0,  173,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  638,
    0,   93,   93,  -26,    0,    0,    0,    0,    0,    0,
    0,  -33,    0,    0,   -3,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  181,    0,    0,  108,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   55,  158,
   22,   87,  224,  289,    0,  336,  134,  169,   32,   62,
   97,    0,    0,  193,    0,    0,    0,    0,   57,    0,
    0,    0,    0,   -9,    0,  127,    0,    0,    0,    0,
    0,    0,    0,  794,    0,    0,    0,    0,    0,    0,
    0,  391,    0,    0,  144,  617,  627,  650,  176,  660,
    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  -47,  -43,    0,  109,  212,  174,   59,  206,  886,
 1051,  -96,  -50,  962,    0,  137,    0,   70,   82,    0,
    0,    0,
};
final static int YYTABLESIZE=1212;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         55,
   15,   15,   72,   55,   73,   66,   77,   55,   55,   55,
   55,   55,   65,   55,   37,  104,  111,   37,   11,   64,
   18,  103,   22,   47,   63,   55,   55,   55,   55,   54,
   47,  110,   37,   54,   23,   47,   25,   54,   54,   54,
   54,   54,  101,   54,  154,  104,  111,   99,   97,   26,
   98,  103,  100,  134,   50,   54,   54,   54,   54,   55,
   10,  112,   50,   24,   43,   50,  102,  173,   43,   20,
   75,   26,   43,   43,   43,   43,   43,  175,   43,   31,
   50,   50,   50,   50,   27,   75,   28,   67,   29,   54,
   43,   43,   43,   43,   44,   38,  102,   36,   44,   39,
   36,   32,   44,   44,   44,   44,   44,  163,   44,   48,
    9,   40,   37,   67,   50,   36,   30,   43,   47,   51,
   44,   44,   44,   44,   43,   44,   41,   51,   45,   46,
   51,   33,   33,   46,    3,    4,    5,   46,   46,   46,
   46,   46,   15,   46,   78,   51,   51,   51,   51,    3,
    4,    5,    6,   33,   44,   46,   46,   46,   46,   58,
   42,   46,   11,   58,   45,   11,   42,   58,   58,   58,
   58,   58,   83,   58,   42,   50,   42,   42,   42,   51,
   12,   13,   14,   27,   79,   58,   58,   58,   58,   46,
   68,   52,   42,   42,   42,   42,   80,   88,  105,  117,
  133,   45,   63,   50,  137,   50,  111,  138,  149,   45,
  161,   45,   45,   45,  164,   73,   68,   14,   30,   58,
   31,   71,   32,   55,   55,   55,   42,   45,   45,   45,
   45,   33,   13,   70,   61,   36,   53,   54,   55,   55,
   55,   55,   55,   55,   55,   55,  109,   47,   47,   47,
   51,  144,   62,   54,   54,   54,   52,    3,    4,    5,
    6,   45,    0,   47,   52,    0,    0,   52,    0,   54,
   54,   54,   54,   54,   54,   54,    0,    0,   50,   50,
   50,    0,   52,   52,   52,   52,    0,    0,   43,   43,
   43,    0,    0,    0,   50,   50,   50,   50,   50,   50,
   50,    0,    0,    0,   43,   43,   43,   43,   43,   43,
   43,   67,   67,   67,    0,    0,   52,    0,   44,   44,
   44,   53,    0,    3,    4,    5,    6,   67,    7,   53,
    0,    0,   53,    0,   44,   44,   44,   44,   44,   44,
   44,    0,    0,   51,   51,   51,    0,   53,   53,   53,
   53,    0,    0,   46,   46,   46,    0,    0,    0,   51,
   51,   51,   51,   51,   51,   51,    0,    0,   49,   46,
   46,   46,   46,   46,   46,   46,   49,    0,    0,   49,
    0,   53,    0,   58,   58,   58,    0,    0,    0,    0,
   42,   42,   42,    0,   49,   49,   49,   49,    0,   58,
   58,   58,   58,   58,   58,   58,   42,   42,   42,   42,
   42,   42,   42,    0,   68,   68,   68,   50,   50,   50,
   50,   50,   50,   48,    0,   45,   45,   45,   49,    0,
   68,   48,    0,    0,   48,    0,    0,    0,    0,    0,
    0,   45,   45,   45,   45,   45,   45,   45,    0,   48,
   48,   48,   48,  101,    0,    0,  104,    0,   99,   97,
    0,   98,  103,  100,  101,    0,    0,  104,    0,   99,
   97,    0,   98,  103,  100,    0,   96,   95,    0,    0,
   52,   52,   52,   48,    0,  101,  116,   96,  104,  118,
   99,   97,    0,   98,  103,  100,   52,   52,   52,   52,
   52,   52,   52,    0,    0,    0,  101,  102,   96,  104,
  140,   99,   97,    0,   98,  103,  100,  101,  102,    0,
  104,  141,   99,   97,    0,   98,  103,  100,  101,   96,
    0,  104,    0,   99,   97,    0,   98,  103,  100,  102,
   96,    0,    0,    0,    0,   53,   53,   53,    0,  101,
  147,   96,  104,    0,   99,   97,    0,   98,  103,  100,
  102,   53,   53,   53,   53,   53,   53,   53,    0,    0,
    0,  102,   96,    0,    0,  101,    0,    0,  104,    0,
   99,   97,  102,   98,  103,  100,    0,    0,    0,    0,
    0,    0,   49,   49,   49,   65,    0,    0,   96,    0,
  150,    0,   64,  102,    0,  148,    0,   63,   49,   49,
   49,   49,   49,   49,   49,    0,  101,    0,    0,  104,
    0,   99,   97,    0,   98,  103,  100,  101,    0,  102,
  104,  174,   99,   97,    0,   98,  103,  100,    0,  160,
    0,  159,    0,    0,    0,    0,   65,   48,   48,   48,
   96,    0,    0,   64,    0,    0,    0,    0,   63,    0,
    0,    0,    0,   48,   48,   48,   48,   48,   48,   48,
  102,    0,    0,    0,   59,   64,   51,   59,   51,   59,
   59,  102,   59,   59,   59,   65,   52,    0,   52,    0,
   89,   90,   91,   92,   93,   94,    0,   59,   59,    0,
    0,   89,   90,   91,   92,   93,   94,  101,   66,   53,
  104,   53,   99,   97,    0,   98,  103,  100,   62,   49,
    0,   49,   89,   90,   91,   92,   93,   94,   59,   65,
   96,    0,    0,    0,    0,    0,   64,    0,   86,    0,
    0,   63,    0,   89,   90,   91,   92,   93,   94,    0,
    0,    0,    0,    0,   89,   90,   91,   92,   93,   94,
    0,  102,    0,    0,    0,   89,   90,   91,   92,   93,
   94,   65,    0,    0,    0,    0,    0,    0,   64,    0,
    0,    0,    0,   63,    0,    0,   89,   90,   91,   92,
   93,   94,   65,  101,    0,    0,  104,    0,   99,   64,
    0,    0,  103,  100,   63,    0,    0,    0,    0,    0,
    0,    0,   89,   90,   91,   92,   93,   94,    0,   53,
   54,   55,   56,   57,   58,   59,   19,   60,    3,    4,
    5,    6,   61,   19,    0,   62,   65,    0,   19,    0,
    0,    0,    0,   64,    0,    0,    0,  102,   63,    0,
    0,    0,    0,   89,   90,  155,  156,  157,  158,    0,
    0,  151,    0,    0,   89,   90,   91,   92,   93,   94,
   53,   54,   55,   56,   57,   58,   59,    0,   60,    0,
    0,    0,    0,   61,  172,    0,   62,    0,    0,    0,
   51,   51,   51,   51,   51,   51,    0,    0,    0,    0,
   52,   52,   52,   52,   52,   52,    0,    0,    0,    0,
    0,   59,   59,   59,   59,   59,   59,    0,   19,    0,
    0,    0,    0,   53,   53,   53,   53,   53,   53,    0,
    0,    0,    0,   49,   49,   49,   49,   49,   49,    0,
    0,    0,    0,    0,   89,   90,   91,   92,   93,   94,
    0,   87,    0,   53,   54,   55,   56,   57,   58,   59,
    0,   60,    0,    0,    0,  115,   61,    0,    0,   62,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   87,    0,    0,   53,   54,   55,   56,   57,
   58,   59,    0,   60,    0,    0,    0,    0,   61,    0,
   70,   62,    0,    0,    0,    0,   53,   54,   55,   56,
   57,   58,   59,    0,   60,  152,  152,   70,    0,   61,
    0,    0,   62,   70,   70,    0,    0,    0,    0,    0,
    0,   70,    0,    0,    0,    0,    0,    0,   87,  152,
   19,   19,   19,   19,   19,   19,   19,    0,   19,  152,
   53,   54,   55,   19,    0,    0,   19,    0,   70,    3,
    4,    5,    0,    0,    0,    0,   62,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   70,   70,    0,    0,    0,   74,   74,    0,    0,
    0,   81,   70,   82,   84,   85,    0,    0,    0,    0,
    0,    0,    0,    0,   70,   70,    0,    0,  113,  114,
    0,    0,    0,    0,    0,   70,    0,    0,    0,  119,
  120,  121,  122,  123,  124,  125,  126,  127,  128,  129,
  130,  131,  132,    0,   74,    0,  136,    0,    0,    0,
    0,  139,    0,    0,    0,  142,    0,  146,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  162,    0,    0,    0,    0,  165,  166,  167,  168,  169,
  170,  171,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   91,   91,   50,   37,   52,   49,   57,   41,   42,   43,
   44,   45,   33,   47,   41,   40,   44,   44,  123,   40,
   59,   46,   40,   33,   45,   59,   60,   61,   62,   33,
   40,   59,   59,   37,   40,   45,  257,   41,   42,   43,
   44,   45,   37,   47,  141,   40,   44,   42,   43,   59,
   45,   46,   47,  104,   33,   59,   60,   61,   62,   93,
    2,   59,   41,   40,   33,   44,   91,  164,   37,   11,
   44,   44,   41,   42,   43,   44,   45,  174,   47,   21,
   59,   60,   61,   62,  273,   59,   17,   33,   59,   93,
   59,   60,   61,   62,   33,  273,   91,   41,   37,   30,
   44,   41,   41,   42,   43,   44,   45,  151,   47,   40,
    2,   30,   93,   59,   93,   59,  125,  273,   37,   33,
   59,   60,   61,   62,   93,   41,   59,   41,   44,   33,
   44,   23,   24,   37,  266,  267,  268,   41,   42,   43,
   44,   45,   91,   47,   40,   59,   60,   61,   62,  266,
  267,  268,  269,   45,   93,   59,   60,   61,   62,   33,
  123,   41,   41,   37,   44,   44,   33,   41,   42,   43,
   44,   45,   64,   47,   41,  123,   43,   44,   45,   93,
  272,  273,  273,  273,   40,   59,   60,   61,   62,   93,
   33,  123,   59,   60,   61,   62,   40,   59,   59,   41,
  273,   33,   59,   60,  125,   62,   44,  125,   41,   41,
   59,   43,   44,   45,  264,  273,   59,  125,   59,   93,
   59,   41,   59,  257,  258,  259,   93,   59,   60,   61,
   62,   59,  125,   41,   59,   24,  257,  258,  259,  273,
  274,  275,  276,  277,  278,  279,   73,  257,  258,  259,
   45,  115,  273,  257,  258,  259,   33,  266,  267,  268,
  269,   93,   -1,  273,   41,   -1,   -1,   44,   -1,  273,
  274,  275,  276,  277,  278,  279,   -1,   -1,  257,  258,
  259,   -1,   59,   60,   61,   62,   -1,   -1,  257,  258,
  259,   -1,   -1,   -1,  273,  274,  275,  276,  277,  278,
  279,   -1,   -1,   -1,  273,  274,  275,  276,  277,  278,
  279,  257,  258,  259,   -1,   -1,   93,   -1,  257,  258,
  259,   33,   -1,  266,  267,  268,  269,  273,  271,   41,
   -1,   -1,   44,   -1,  273,  274,  275,  276,  277,  278,
  279,   -1,   -1,  257,  258,  259,   -1,   59,   60,   61,
   62,   -1,   -1,  257,  258,  259,   -1,   -1,   -1,  273,
  274,  275,  276,  277,  278,  279,   -1,   -1,   33,  273,
  274,  275,  276,  277,  278,  279,   41,   -1,   -1,   44,
   -1,   93,   -1,  257,  258,  259,   -1,   -1,   -1,   -1,
  257,  258,  259,   -1,   59,   60,   61,   62,   -1,  273,
  274,  275,  276,  277,  278,  279,  273,  274,  275,  276,
  277,  278,  279,   -1,  257,  258,  259,  274,  275,  276,
  277,  278,  279,   33,   -1,  257,  258,  259,   93,   -1,
  273,   41,   -1,   -1,   44,   -1,   -1,   -1,   -1,   -1,
   -1,  273,  274,  275,  276,  277,  278,  279,   -1,   59,
   60,   61,   62,   37,   -1,   -1,   40,   -1,   42,   43,
   -1,   45,   46,   47,   37,   -1,   -1,   40,   -1,   42,
   43,   -1,   45,   46,   47,   -1,   60,   61,   -1,   -1,
  257,  258,  259,   93,   -1,   37,   59,   60,   40,   41,
   42,   43,   -1,   45,   46,   47,  273,  274,  275,  276,
  277,  278,  279,   -1,   -1,   -1,   37,   91,   60,   40,
   41,   42,   43,   -1,   45,   46,   47,   37,   91,   -1,
   40,   41,   42,   43,   -1,   45,   46,   47,   37,   60,
   -1,   40,   -1,   42,   43,   -1,   45,   46,   47,   91,
   60,   -1,   -1,   -1,   -1,  257,  258,  259,   -1,   37,
   59,   60,   40,   -1,   42,   43,   -1,   45,   46,   47,
   91,  273,  274,  275,  276,  277,  278,  279,   -1,   -1,
   -1,   91,   60,   -1,   -1,   37,   -1,   -1,   40,   -1,
   42,   43,   91,   45,   46,   47,   -1,   -1,   -1,   -1,
   -1,   -1,  257,  258,  259,   33,   -1,   -1,   60,   -1,
   62,   -1,   40,   91,   -1,   93,   -1,   45,  273,  274,
  275,  276,  277,  278,  279,   -1,   37,   -1,   -1,   40,
   -1,   42,   43,   -1,   45,   46,   47,   37,   -1,   91,
   40,   41,   42,   43,   -1,   45,   46,   47,   -1,   60,
   -1,   62,   -1,   -1,   -1,   -1,   33,  257,  258,  259,
   60,   -1,   -1,   40,   -1,   -1,   -1,   -1,   45,   -1,
   -1,   -1,   -1,  273,  274,  275,  276,  277,  278,  279,
   91,   -1,   -1,   -1,   37,   59,   60,   40,   62,   42,
   43,   91,   45,   46,   47,   59,   60,   -1,   62,   -1,
  274,  275,  276,  277,  278,  279,   -1,   60,   61,   -1,
   -1,  274,  275,  276,  277,  278,  279,   37,   59,   60,
   40,   62,   42,   43,   -1,   45,   46,   47,   59,   60,
   -1,   62,  274,  275,  276,  277,  278,  279,   91,   33,
   60,   -1,   -1,   -1,   -1,   -1,   40,   -1,  125,   -1,
   -1,   45,   -1,  274,  275,  276,  277,  278,  279,   -1,
   -1,   -1,   -1,   -1,  274,  275,  276,  277,  278,  279,
   -1,   91,   -1,   -1,   -1,  274,  275,  276,  277,  278,
  279,   33,   -1,   -1,   -1,   -1,   -1,   -1,   40,   -1,
   -1,   -1,   -1,   45,   -1,   -1,  274,  275,  276,  277,
  278,  279,   33,   37,   -1,   -1,   40,   -1,   42,   40,
   -1,   -1,   46,   47,   45,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  274,  275,  276,  277,  278,  279,   -1,  257,
  258,  259,  260,  261,  262,  263,   33,  265,  266,  267,
  268,  269,  270,   40,   -1,  273,   33,   -1,   45,   -1,
   -1,   -1,   -1,   40,   -1,   -1,   -1,   91,   45,   -1,
   -1,   -1,   -1,  274,  275,  276,  277,  278,  279,   -1,
   -1,  123,   -1,   -1,  274,  275,  276,  277,  278,  279,
  257,  258,  259,  260,  261,  262,  263,   -1,  265,   -1,
   -1,   -1,   -1,  270,  125,   -1,  273,   -1,   -1,   -1,
  274,  275,  276,  277,  278,  279,   -1,   -1,   -1,   -1,
  274,  275,  276,  277,  278,  279,   -1,   -1,   -1,   -1,
   -1,  274,  275,  276,  277,  278,  279,   -1,  125,   -1,
   -1,   -1,   -1,  274,  275,  276,  277,  278,  279,   -1,
   -1,   -1,   -1,  274,  275,  276,  277,  278,  279,   -1,
   -1,   -1,   -1,   -1,  274,  275,  276,  277,  278,  279,
   -1,   66,   -1,  257,  258,  259,  260,  261,  262,  263,
   -1,  265,   -1,   -1,   -1,   80,  270,   -1,   -1,  273,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  107,   -1,   -1,  257,  258,  259,  260,  261,
  262,  263,   -1,  265,   -1,   -1,   -1,   -1,  270,   -1,
   49,  273,   -1,   -1,   -1,   -1,  257,  258,  259,  260,
  261,  262,  263,   -1,  265,  140,  141,   66,   -1,  270,
   -1,   -1,  273,   72,   73,   -1,   -1,   -1,   -1,   -1,
   -1,   80,   -1,   -1,   -1,   -1,   -1,   -1,  163,  164,
  257,  258,  259,  260,  261,  262,  263,   -1,  265,  174,
  257,  258,  259,  270,   -1,   -1,  273,   -1,  107,  266,
  267,  268,   -1,   -1,   -1,   -1,  273,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  140,  141,   -1,   -1,   -1,   56,   57,   -1,   -1,
   -1,   61,  151,   63,   64,   65,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  163,  164,   -1,   -1,   78,   79,
   -1,   -1,   -1,   -1,   -1,  174,   -1,   -1,   -1,   89,
   90,   91,   92,   93,   94,   95,   96,   97,   98,   99,
  100,  101,  102,   -1,  104,   -1,  106,   -1,   -1,   -1,
   -1,  111,   -1,   -1,   -1,  115,   -1,  117,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  150,   -1,   -1,   -1,   -1,  155,  156,  157,  158,  159,
  160,  161,
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
"sentencia : FOR '(' sentencia expresionFor ';' expresion ')' cuerpoCondicional",
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
"invocacion : expresion '(' argumentosLlamada ')'",
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

//#line 255 "../../src/sintactico/sintactico.y"

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
//#line 621 "Parser.java"
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
																											List<Definicion> listaDefiniciones = (List<Definicion>)val_peek(0);
																											for(int i = 0; i < listaDefiniciones.size(); i++) {
																												Definicion elemento = listaDefiniciones.get(i);
																											    lista.add(elemento);																
																											}
																										}
break;
case 3:
//#line 59 "../../src/sintactico/sintactico.y"
{	yyval = new ArrayList<Definicion>(); }
break;
case 4:
//#line 62 "../../src/sintactico/sintactico.y"
{ 	List<Definicion> lista = new ArrayList();
																											lista.add(new DefFuncion(lexico.getLine(), lexico.getColumn(), (String) val_peek(7),
																												new TipoFuncion((List<DefVariable>)val_peek(5), (Tipo)val_peek(8)), 
																												(List<DefVariable>)val_peek(2), (List<Sentencia>)val_peek(1)));
																											yyval = lista; 
																										}
break;
case 5:
//#line 68 "../../src/sintactico/sintactico.y"
{	List<Definicion> lista = new ArrayList();
																											lista.add(new DefFuncion(lexico.getLine(), lexico.getColumn(),  (String) val_peek(7),
																												new TipoFuncion((List<DefVariable>)val_peek(5), TipoVoid.getInstancia()), 
																												(List<DefVariable>)val_peek(2), (List<Sentencia>)val_peek(1)));
																											yyval = lista;
																										}
break;
case 6:
//#line 74 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(1);  }
break;
case 7:
//#line 77 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(2);
																											List<DefVariable> lista = (List<DefVariable>)yyval;																											
																											for(DefVariable elemento : (List<DefVariable>)val_peek(1))
																												lista.add(elemento); 
																										}
break;
case 8:
//#line 82 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<DefVariable>();}
break;
case 9:
//#line 85 "../../src/sintactico/sintactico.y"
{	yyval = val_peek(2); 
																											List<DefVariable> lista = (List<DefVariable>)yyval;																												
																											for(DefVariable elemento : (List<DefVariable>) val_peek(0))
																												lista.add(elemento);																												
																										}
break;
case 10:
//#line 90 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(0); }
break;
case 11:
//#line 91 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<DefVariable>(); }
break;
case 12:
//#line 94 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<DefVariable>();
																											((List<DefVariable>) yyval).add(new DefVariable(lexico.getLine(), 
																												lexico.getColumn(), (String)val_peek(0), (Tipo)val_peek(1)));  
																										}
break;
case 13:
//#line 100 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(0); }
break;
case 14:
//#line 101 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<Sentencia>(); }
break;
case 15:
//#line 104 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(1);((List<Sentencia>)yyval).add((Sentencia)val_peek(0)); }
break;
case 16:
//#line 105 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<Sentencia>(); ((List<Sentencia>)yyval).add((Sentencia)val_peek(0)); }
break;
case 17:
//#line 108 "../../src/sintactico/sintactico.y"
{ 	yyval = new Asignacion(lexico.getLine(), lexico.getColumn(), (Expresion)val_peek(3), (Expresion)val_peek(1)); }
break;
case 18:
//#line 109 "../../src/sintactico/sintactico.y"
{ 	yyval = new SentenciaWhile(lexico.getLine(), lexico.getColumn(), (Expresion)val_peek(2), (List<Sentencia>)val_peek(0)); }
break;
case 19:
//#line 110 "../../src/sintactico/sintactico.y"
{ 	yyval = new SentenciaIf(lexico.getLine(), lexico.getColumn(), (Expresion)val_peek(2), (List<Sentencia>)val_peek(0), new ArrayList()); }
break;
case 20:
//#line 111 "../../src/sintactico/sintactico.y"
{ 	yyval = new SentenciaIf(lexico.getLine(), lexico.getColumn(), (Expresion)val_peek(4), (List<Sentencia>)val_peek(2), (List<Sentencia>)val_peek(0)); }
break;
case 21:
//#line 112 "../../src/sintactico/sintactico.y"
{ 	yyval = new Escritura(lexico.getLine(), lexico.getColumn(),(List<Expresion>)val_peek(1)); }
break;
case 22:
//#line 113 "../../src/sintactico/sintactico.y"
{ 	yyval = new Lectura(lexico.getLine(), lexico.getColumn(),(List<Expresion>)val_peek(1)); }
break;
case 23:
//#line 114 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(1); }
break;
case 24:
//#line 115 "../../src/sintactico/sintactico.y"
{ 	yyval = new Return(lexico.getLine(), lexico.getColumn(), (Expresion)val_peek(1));}
break;
case 25:
//#line 116 "../../src/sintactico/sintactico.y"
{   yyval = new SentenciaFor(lexico.getLine(), lexico.getColumn(), (Sentencia)val_peek(5), (Expresion)val_peek(4), (Sentencia)val_peek(2), (List<Sentencia>)val_peek(0));}
break;
case 26:
//#line 119 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(0); }
break;
case 27:
//#line 120 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(0); }
break;
case 28:
//#line 123 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(1);	}
break;
case 29:
//#line 124 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<Sentencia>(); ((List<Sentencia>)yyval).add((Sentencia)val_peek(0));  }
break;
case 30:
//#line 127 "../../src/sintactico/sintactico.y"
{ 	List<DefVariable> variables = new ArrayList<DefVariable>();
																											for(String aux: (List<String>)val_peek(0))
																												variables.add(new DefVariable(lexico.getLine(), lexico.getColumn(), aux, (Tipo)val_peek(1)));																												 
																											yyval = variables; 
																										}
break;
case 31:
//#line 132 "../../src/sintactico/sintactico.y"
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
//#line 141 "../../src/sintactico/sintactico.y"
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
//#line 150 "../../src/sintactico/sintactico.y"
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
//#line 166 "../../src/sintactico/sintactico.y"
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
//#line 178 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(1); }
break;
case 36:
//#line 181 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(2); 
																											List<Expresion> lista = (List<Expresion>)yyval;
																											Expresion elemento = (Expresion)val_peek(0);
																											lista.add(elemento);																																																								
																										}
break;
case 37:
//#line 186 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<Expresion>(); ((List<Expresion>)yyval).add((Expresion)val_peek(0));  	}
break;
case 38:
//#line 189 "../../src/sintactico/sintactico.y"
{ 	yyval = new Variable(lexico.getLine(), lexico.getColumn(), (String) val_peek(0)); 	}
break;
case 39:
//#line 190 "../../src/sintactico/sintactico.y"
{ 	yyval = new LiteralEntero(lexico.getLine(), lexico.getColumn(), (Integer) val_peek(0));		}
break;
case 40:
//#line 191 "../../src/sintactico/sintactico.y"
{ 	yyval = new LiteralReal(lexico.getLine(), lexico.getColumn(), (Double) val_peek(0));	}
break;
case 41:
//#line 192 "../../src/sintactico/sintactico.y"
{ 	yyval = new LiteralCaracter(lexico.getLine(), lexico.getColumn(), (Character)val_peek(0));		}
break;
case 42:
//#line 193 "../../src/sintactico/sintactico.y"
{ 	yyval = new Aritmetica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "+", (Expresion) val_peek(0));		}
break;
case 43:
//#line 194 "../../src/sintactico/sintactico.y"
{ 	yyval = new Aritmetica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "*", (Expresion) val_peek(0));		}
break;
case 44:
//#line 195 "../../src/sintactico/sintactico.y"
{ 	yyval = new Aritmetica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "/", (Expresion) val_peek(0));		}
break;
case 45:
//#line 196 "../../src/sintactico/sintactico.y"
{ 	yyval = new Aritmetica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "-", (Expresion) val_peek(0));		}
break;
case 46:
//#line 197 "../../src/sintactico/sintactico.y"
{ 	yyval = new Aritmetica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "%", (Expresion) val_peek(0));		}
break;
case 47:
//#line 198 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(0); }
break;
case 48:
//#line 198 "../../src/sintactico/sintactico.y"
{ 	yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(4), ">", (Expresion) val_peek(2));	}
break;
case 49:
//#line 199 "../../src/sintactico/sintactico.y"
{ 	yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "<", (Expresion) val_peek(0));	}
break;
case 50:
//#line 200 "../../src/sintactico/sintactico.y"
{ 	yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), ">=", (Expresion) val_peek(0));	}
break;
case 51:
//#line 201 "../../src/sintactico/sintactico.y"
{ 	yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "<=", (Expresion) val_peek(0));	}
break;
case 52:
//#line 202 "../../src/sintactico/sintactico.y"
{ 	yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "!=", (Expresion) val_peek(0));	}
break;
case 53:
//#line 203 "../../src/sintactico/sintactico.y"
{ 	yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "==", (Expresion) val_peek(0));	}
break;
case 54:
//#line 204 "../../src/sintactico/sintactico.y"
{ 	yyval = new Negacion(lexico.getLine(), lexico.getColumn(),  "!", (Expresion) val_peek(0));	}
break;
case 55:
//#line 205 "../../src/sintactico/sintactico.y"
{ 	yyval = new MenosUnario(lexico.getLine(), lexico.getColumn(),  "-",(Expresion) val_peek(0));	}
break;
case 56:
//#line 206 "../../src/sintactico/sintactico.y"
{ 	yyval = new AccesoCampo(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), (String) val_peek(0));	}
break;
case 57:
//#line 207 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(1);}
break;
case 58:
//#line 208 "../../src/sintactico/sintactico.y"
{ 	yyval = new Cast(lexico.getLine(), lexico.getColumn(), (Tipo) val_peek(2), (Expresion) val_peek(0));	}
break;
case 59:
//#line 209 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(0);}
break;
case 60:
//#line 210 "../../src/sintactico/sintactico.y"
{ 	yyval = new AccesoArray(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(3), (Expresion) val_peek(1)); }
break;
case 61:
//#line 213 "../../src/sintactico/sintactico.y"
{ 	yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), ">", (Expresion) val_peek(0));	}
break;
case 62:
//#line 214 "../../src/sintactico/sintactico.y"
{ 	yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "<", (Expresion) val_peek(0));	}
break;
case 63:
//#line 215 "../../src/sintactico/sintactico.y"
{ 	yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), ">=", (Expresion) val_peek(0));	}
break;
case 64:
//#line 216 "../../src/sintactico/sintactico.y"
{ 	yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "<=", (Expresion) val_peek(0));	}
break;
case 65:
//#line 217 "../../src/sintactico/sintactico.y"
{ 	yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "!=", (Expresion) val_peek(0));	}
break;
case 66:
//#line 218 "../../src/sintactico/sintactico.y"
{ 	yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "==", (Expresion) val_peek(0));	}
break;
case 67:
//#line 220 "../../src/sintactico/sintactico.y"
{ 	yyval = new Logica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "&&", (Expresion) val_peek(0));	}
break;
case 68:
//#line 221 "../../src/sintactico/sintactico.y"
{ 	yyval = new Logica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "||", (Expresion) val_peek(0));	}
break;
case 69:
//#line 223 "../../src/sintactico/sintactico.y"
{ 	yyval = new Invocacion(lexico.getLine(), lexico.getColumn(), new Variable(lexico.getLine(), lexico.getColumn(), (String)val_peek(3)),(List<Expresion>)val_peek(1)); 	}
break;
case 70:
//#line 226 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(0);}
break;
case 71:
//#line 227 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<Expresion>();	}
break;
case 72:
//#line 231 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(0); 
																											List<Integer> lista = (List<Integer>)yyval;
																											Integer elemento = (Integer)val_peek(2);
																											lista.add(elemento);
																										}
break;
case 73:
//#line 236 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<Integer>(); ((List<Integer>)yyval).add((Integer)val_peek(1)); 	}
break;
case 74:
//#line 239 "../../src/sintactico/sintactico.y"
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
//#line 247 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList(); ((List<String>)yyval).add(((String) val_peek(0))); 	}
break;
case 76:
//#line 250 "../../src/sintactico/sintactico.y"
{ 	yyval = TipoEntero.getInstancia(); 	}
break;
case 77:
//#line 251 "../../src/sintactico/sintactico.y"
{ 	yyval = TipoReal.getInstancia(); 	}
break;
case 78:
//#line 252 "../../src/sintactico/sintactico.y"
{ 	yyval = TipoCaracter.getInstancia(); 	}
break;
//#line 1176 "Parser.java"
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
