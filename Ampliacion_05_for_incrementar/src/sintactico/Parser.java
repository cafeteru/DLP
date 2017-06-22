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
public final static short AUMENTAR=280;
public final static short DISMINUIR=281;
public final static short MENOS_UNARIO=282;
public final static short NEGACION=283;
public final static short CASTP=284;
public final static short MENORQUEELSE=285;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    4,    4,    4,    2,    2,    6,    6,
    6,    9,    7,    7,    3,    3,   10,   10,   10,   10,
   10,   10,   10,   10,   10,   10,   15,   15,   16,   16,
   17,   17,   18,   18,   12,   12,    8,    8,    8,    8,
   24,   24,   13,   13,   11,   11,   11,   11,   11,   11,
   11,   11,   11,   11,   11,   11,   11,   21,   21,   21,
   21,   21,   20,   20,   20,   20,   20,   20,   19,   19,
   19,   14,   25,   25,   23,   23,   22,   22,    5,    5,
    5,
};
final static short yylen[] = {                            2,
    9,    2,    0,    9,    9,    2,    3,    0,    3,    1,
    0,    2,    1,    0,    2,    1,    4,    5,    5,    7,
    3,    3,    2,    3,    9,    1,    3,    0,    1,    1,
    3,    1,    2,    2,    3,    1,    2,    3,    5,    6,
    3,    2,    3,    1,    1,    1,    1,    1,    1,    1,
    1,    2,    3,    3,    4,    1,    4,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    2,    4,    1,    0,    4,    3,    3,    1,    1,    1,
    1,
};
final static short yydefred[] = {                         3,
    0,    0,   79,   80,   81,    0,    0,    2,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    6,    0,    0,
    0,    0,    0,    0,    0,    0,   78,    0,   42,    0,
    0,    0,    0,    0,   10,    0,    0,   77,    0,    0,
   41,    8,   12,    0,    0,    0,   75,    0,    0,    8,
    9,    8,   46,   47,   48,    0,    0,    0,    0,    0,
    0,   45,    0,    0,    0,    0,    0,   16,    0,    0,
   26,   51,   50,   49,    0,    0,    0,    0,   56,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    1,   15,
    7,    0,    0,    0,    0,    0,    0,   33,   34,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   23,    0,    0,    0,   22,    0,   21,    0,    0,    0,
    0,   24,    0,   54,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   53,
    0,    0,    5,    4,    0,    0,    0,    0,    0,    0,
   17,   57,   72,    0,   36,   18,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   35,   20,    0,    0,   32,
    0,    0,    0,   25,
};
final static short yydgoto[] = {                          1,
    2,   49,  112,    8,   19,   34,  113,   67,   35,   68,
   69,  156,   78,   79,  121,  160,  169,   71,   72,   73,
   74,   16,   17,   21,  142,
};
final static short yysindex[] = {                         0,
    0,  -40,    0,    0,    0, -120, -209,    0,  -90,  -29,
  -87,   -4,   15,   41, -184,   38, -183,    0,  -89,   37,
 -105,   57,  -69,  -69,   30, -148,    0,   38,    0,  -89,
   74,    4, -129,   76,    0,   77,   47,    0,   38, -183,
    0,    0,    0,   26,  -69,   35,    0,   38,  378,    0,
    0,    0,    0,    0,    0,   79,   79,  113,  130,  146,
   79,    0,   79,  888,   79,  634,  129,    0,   68,  133,
    0,    0,    0,    0,  378,  378,  515,   -6,    0,    2,
   79,   79,   79,  131,  -19,  148,  163,  -19,    0,    0,
    0,   79,   79,   79,   79,   79,   79,    0,    0,   79,
   79,   79,   79,   79,   79,   79,   79,   79,  -79,   79,
    0,  865,   70,   82,    0,   79,    0,  174,  352,  384,
  142,    0,   79,    0,  541,  541,  224,  224,  224,  224,
  417,  224,  224,  287,  287,  -19,  -19,  -19,  441,    0,
  158,  171,    0,    0,  515,  725,  725,   79,   79,  -19,
    0,    0,    0,  865,    0,    0,  -51,  515,  515,  159,
    0,    0,  820,  725,   79,    0,    0,   94,  183,    0,
   79,  725,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    3,    0,  173,    0,    0,    0,    0,
    0,    0,  102,  102,    0,    0,    0,  176,    0,    0,
    0,    0,    0,    0,    0,    0,  -43,    0,  186,    0,
    0,    0,    0,    0,    0,    0,    0,  196,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  105,
    0,    0,    0,    0,  108,  108,  116,    0,    0,    0,
    0,    0,  197,    0,  -37,    0,    0,  -28,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  216,
    0,  135,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  868,  906,  466,  563,  639,  663,
    0,  743,  754,  549,  609,   -2,    7,   33,    0,    0,
  217,    0,    0,    0,  128,    0,    0,    0,    0,   42,
    0,    0,    0,    0,    0,    0,  837,  203,    0,    0,
  452,  476,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  504,    0,
};
final static short yygindex[] = {                         0,
    0,  -24,  -38,    0,  445,  239,  192,   86,  235,  193,
 1101, -135,  -39,  897,    0,    0,    0,  124,  141,  143,
  120,   92,   69,    0,    0,
};
final static int YYTABLESIZE=1272;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         52,
   15,   15,   11,   52,   52,   52,   52,   52,   71,   52,
   66,  157,   71,   71,   71,   71,   71,   80,   71,   30,
  110,   52,   52,   52,   52,   75,  109,   76,  167,   18,
   71,   71,   71,   71,   59,   22,  174,  116,   59,   59,
   59,   59,   59,   60,   59,  116,   78,   60,   60,   60,
   60,   60,  115,   60,   23,   52,   59,   59,   59,   59,
  117,   78,   12,   13,   71,   60,   60,   60,   60,   62,
  141,  108,   25,   62,   62,   62,   62,   62,   55,   62,
   24,   26,   55,   55,   55,   55,   55,   10,   55,   27,
   59,   62,   62,   62,   62,   29,   20,   32,   40,   60,
   55,   55,   55,   55,  107,   47,   31,  110,   28,  105,
  103,   65,  104,  109,  106,  163,   44,   46,   64,   45,
   45,   39,   37,   63,   38,   62,   42,  102,  100,  101,
  107,   48,   41,  110,   55,  105,  103,   15,  104,  109,
  106,   56,   11,   43,   56,   11,   56,   56,   50,   56,
   56,   56,   81,  102,  171,  101,   44,   52,  108,   44,
    3,    4,    5,    6,   56,   56,   56,  107,   43,   82,
  110,   43,  105,  103,   44,  104,  109,  106,    3,    4,
    5,    6,   14,   27,  108,   83,   43,   91,  123,  122,
  102,  111,  101,  140,  143,   56,    3,    4,    5,  107,
  149,  116,  110,  124,  105,  103,  144,  104,  109,  106,
  107,  153,  164,  110,  146,  105,  103,  165,  104,  109,
  106,  108,  102,  172,  101,    3,    4,    5,    6,   76,
    7,   37,   14,  102,   38,  101,   52,   52,   52,   52,
   52,   52,   52,   52,   39,   71,   71,   71,   71,   71,
   71,   71,   71,  108,   40,   28,   74,   73,   90,   13,
  107,   27,   36,  110,  108,  105,  103,  114,  104,  109,
  106,   59,   59,   59,   59,   59,   59,   59,   59,   51,
   60,   60,   60,   60,   60,   60,   60,   60,  170,  161,
  173,  162,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   90,    0,   62,   62,   62,   62,
   62,   62,   62,   62,  108,   55,   55,   55,   55,   55,
   55,   55,   55,  107,    0,    0,  110,    0,  105,    0,
    0,    0,  109,  106,    0,   53,   54,   55,  155,  155,
    0,   92,   93,   94,   95,   96,   97,   98,   99,    0,
    0,   62,    0,    0,    0,   90,  155,    0,    0,    0,
    0,    0,    0,    0,  155,    0,    0,   92,   93,   94,
   95,   96,   97,   98,   99,    0,    0,  108,   56,   56,
   56,   56,   56,   56,   56,   56,    0,    0,  107,    0,
    0,  110,  147,  105,  103,    0,  104,  109,  106,    0,
    0,    0,    0,    0,   92,   93,   94,   95,   96,   97,
   65,  102,    0,  101,    0,    0,    0,   64,    0,    0,
  107,    0,   63,  110,    0,  105,  103,    0,  104,  109,
  106,    0,    0,    0,    0,    0,   92,   93,   94,   95,
   96,   97,  108,  102,  148,  101,    9,   92,   93,   94,
   95,   96,   97,  107,    0,    0,  110,    0,  105,  103,
    0,  104,  109,  106,    0,    0,    0,   33,   33,    0,
    0,    0,    0,    0,  108,  151,  102,  107,  101,    0,
  110,    0,  105,  103,    0,  104,  109,  106,   51,   33,
    0,   51,    0,   51,   51,    0,   51,   51,   51,    0,
  102,    0,  101,    0,    0,    0,   65,  108,   86,   65,
   29,   51,   50,   51,    0,   50,    0,   50,   50,    0,
   50,   50,   50,    0,   65,   65,   65,   65,    0,    0,
    0,  108,    0,  152,   30,   50,    0,   50,    0,    0,
   49,    0,   51,   49,   31,   49,   49,    0,   49,   49,
   49,  107,    0,    0,  110,    0,  105,  103,   65,  104,
  109,  106,    0,   49,    0,   49,   50,    0,    0,    0,
    0,    0,    0,    0,  102,    0,  101,  107,    0,    0,
  110,    0,  105,  103,    0,  104,  109,  106,    0,   58,
    0,   58,   58,   58,   49,    0,    0,    0,    0,    0,
  102,    0,  101,   66,    0,  108,   66,   58,   58,   58,
   58,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   66,   66,   66,   66,   92,   93,   94,   95,   96,
   97,  108,    0,    0,   53,   54,   55,   56,   57,   58,
   59,   58,   60,    3,    4,    5,    6,   61,    0,   61,
   62,   61,   61,   61,    0,   66,    0,   92,   93,   94,
   95,   96,   97,    0,    0,    0,   65,   61,   61,   61,
   61,    0,    0,   64,    0,    0,    0,    0,   63,   67,
    0,    0,   67,    0,    0,    0,    0,    0,    0,    0,
   92,   93,   94,   95,   96,   97,    0,   67,   67,   67,
   67,   61,    0,   68,    0,    0,   68,    0,    0,    0,
    0,    0,    0,    0,   92,   93,   94,   95,   96,   97,
    0,   68,   68,   68,   68,   51,   51,   51,   51,   51,
   51,   67,    0,    0,    0,    0,    0,    0,    0,   65,
   65,   65,   65,   65,   65,   65,   65,    0,    0,   50,
   50,   50,   50,   50,   50,   68,    0,   65,   89,    0,
    0,    0,    0,    0,   64,    0,    0,    0,    0,   63,
    0,    0,    0,    0,    0,    0,    0,   49,   49,   49,
   49,   49,   49,   63,    0,    0,   63,    0,   92,   93,
   94,   95,   96,   97,   64,    0,    0,   64,    0,    0,
    0,   63,   63,   63,   63,    0,    0,    0,    0,    0,
    0,    0,   64,   64,   64,   64,   94,   95,   96,   97,
    0,    0,   58,   58,   58,   58,   58,   58,   58,   58,
    0,    0,    0,    0,    0,   63,   66,   66,   66,   66,
   66,   66,   66,   66,    0,    0,   64,  154,    0,    0,
    0,    0,   65,    0,    0,    0,    0,    0,    0,   64,
    0,    0,    0,    0,   63,    0,    0,    0,    0,   19,
    0,    0,    0,    0,    0,    0,   19,    0,    0,    0,
    0,   19,   61,   61,   61,   61,   61,   61,   61,   61,
   53,   54,   55,   56,   57,   58,   59,   65,   60,    0,
    0,    0,    0,   61,   64,    0,   62,    0,   69,   63,
    0,   69,   67,   67,   67,   67,   67,   67,   67,   67,
   65,    0,    0,    0,    0,    0,   69,   64,   69,    0,
    0,    0,   63,    0,    0,    0,   68,   68,   68,   68,
   68,   68,   68,   68,  166,   70,   70,    0,    0,   70,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   69,   19,   70,    0,   70,    0,   70,    0,    0,    0,
    0,   70,   70,    0,    0,    0,    0,    0,    0,    0,
    0,   53,   54,   55,   56,   57,   58,   59,    0,   60,
    0,    0,    0,    0,   61,    0,    0,   62,   70,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   70,    0,
    0,    0,    0,    0,    0,    0,   63,   63,   63,   63,
   63,   63,   63,   63,    0,    0,    0,   64,   64,   64,
   64,   64,   64,   64,   64,    0,    0,    0,    0,    0,
    0,    0,   70,   70,    0,    0,    0,    0,    0,    0,
   70,    0,    0,    0,    0,    0,    0,    0,    0,   70,
   70,    0,    0,    0,    0,    0,    0,    0,   70,    0,
    0,    0,    0,    0,    0,    0,   53,   54,   55,   56,
   57,   58,   59,    0,   60,    0,    0,    0,    0,   61,
    0,    0,   62,   19,   19,   19,   19,   19,   19,   19,
    0,   19,    0,    0,    0,    0,   19,    0,    0,   19,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   53,   54,   55,   56,   57,   58,   59,    0,   60,
    0,    0,    0,    0,   61,    0,    0,   62,    0,    0,
    0,   69,   69,    0,   53,   54,   55,   69,   69,    0,
    0,    0,    0,    3,    4,    5,   77,   77,    0,    0,
   62,   84,    0,   85,   87,   88,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   70,
   70,  118,  119,  120,    0,   70,   70,    0,    0,    0,
    0,    0,  125,  126,  127,  128,  129,  130,    0,    0,
  131,  132,  133,  134,  135,  136,  137,  138,  139,    0,
   77,    0,    0,    0,    0,    0,  145,    0,    0,    0,
    0,    0,    0,  150,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  158,  159,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  168,    0,    0,    0,    0,
    0,  159,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
   91,   91,  123,   41,   42,   43,   44,   45,   37,   47,
   49,  147,   41,   42,   43,   44,   45,   57,   47,  125,
   40,   59,   60,   61,   62,   50,   46,   52,  164,   59,
   59,   60,   61,   62,   37,   40,  172,   44,   41,   42,
   43,   44,   45,   37,   47,   44,   44,   41,   42,   43,
   44,   45,   59,   47,   40,   93,   59,   60,   61,   62,
   59,   59,  272,  273,   93,   59,   60,   61,   62,   37,
  110,   91,  257,   41,   42,   43,   44,   45,   37,   47,
   40,   44,   41,   42,   43,   44,   45,    2,   47,  273,
   93,   59,   60,   61,   62,   59,   11,   41,   30,   93,
   59,   60,   61,   62,   37,   37,   21,   40,   17,   42,
   43,   33,   45,   46,   47,  154,   41,   41,   40,   44,
   44,   30,   93,   45,  273,   93,  123,   60,   61,   62,
   37,   40,   59,   40,   93,   42,   43,   91,   45,   46,
   47,   37,   41,  273,   40,   44,   42,   43,  123,   45,
   46,   47,   40,   60,   61,   62,   41,  123,   91,   44,
  266,  267,  268,  269,   60,   61,   62,   37,   41,   40,
   40,   44,   42,   43,   59,   45,   46,   47,  266,  267,
  268,  269,  273,  273,   91,   40,   59,   59,   41,   59,
   60,   59,   62,  273,  125,   91,  266,  267,  268,   37,
   59,   44,   40,   41,   42,   43,  125,   45,   46,   47,
   37,   41,  264,   40,   41,   42,   43,   59,   45,   46,
   47,   91,   60,   41,   62,  266,  267,  268,  269,  273,
  271,   59,  125,   60,   59,   62,  274,  275,  276,  277,
  278,  279,  280,  281,   59,  274,  275,  276,  277,  278,
  279,  280,  281,   91,   59,   59,   41,   41,   66,  125,
   37,   59,   24,   40,   91,   42,   43,   76,   45,   46,
   47,  274,  275,  276,  277,  278,  279,  280,  281,   45,
  274,  275,  276,  277,  278,  279,  280,  281,  165,  149,
  171,  149,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  112,   -1,  274,  275,  276,  277,
  278,  279,  280,  281,   91,  274,  275,  276,  277,  278,
  279,  280,  281,   37,   -1,   -1,   40,   -1,   42,   -1,
   -1,   -1,   46,   47,   -1,  257,  258,  259,  146,  147,
   -1,  274,  275,  276,  277,  278,  279,  280,  281,   -1,
   -1,  273,   -1,   -1,   -1,  163,  164,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  172,   -1,   -1,  274,  275,  276,
  277,  278,  279,  280,  281,   -1,   -1,   91,  274,  275,
  276,  277,  278,  279,  280,  281,   -1,   -1,   37,   -1,
   -1,   40,   41,   42,   43,   -1,   45,   46,   47,   -1,
   -1,   -1,   -1,   -1,  274,  275,  276,  277,  278,  279,
   33,   60,   -1,   62,   -1,   -1,   -1,   40,   -1,   -1,
   37,   -1,   45,   40,   -1,   42,   43,   -1,   45,   46,
   47,   -1,   -1,   -1,   -1,   -1,  274,  275,  276,  277,
  278,  279,   91,   60,   61,   62,    2,  274,  275,  276,
  277,  278,  279,   37,   -1,   -1,   40,   -1,   42,   43,
   -1,   45,   46,   47,   -1,   -1,   -1,   23,   24,   -1,
   -1,   -1,   -1,   -1,   91,   59,   60,   37,   62,   -1,
   40,   -1,   42,   43,   -1,   45,   46,   47,   37,   45,
   -1,   40,   -1,   42,   43,   -1,   45,   46,   47,   -1,
   60,   -1,   62,   -1,   -1,   -1,   41,   91,   64,   44,
   59,   60,   37,   62,   -1,   40,   -1,   42,   43,   -1,
   45,   46,   47,   -1,   59,   60,   61,   62,   -1,   -1,
   -1,   91,   -1,   93,   59,   60,   -1,   62,   -1,   -1,
   37,   -1,   91,   40,   41,   42,   43,   -1,   45,   46,
   47,   37,   -1,   -1,   40,   -1,   42,   43,   93,   45,
   46,   47,   -1,   60,   -1,   62,   91,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   60,   -1,   62,   37,   -1,   -1,
   40,   -1,   42,   43,   -1,   45,   46,   47,   -1,   41,
   -1,   43,   44,   45,   91,   -1,   -1,   -1,   -1,   -1,
   60,   -1,   62,   41,   -1,   91,   44,   59,   60,   61,
   62,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   59,   60,   61,   62,  274,  275,  276,  277,  278,
  279,   91,   -1,   -1,  257,  258,  259,  260,  261,  262,
  263,   93,  265,  266,  267,  268,  269,  270,   -1,   41,
  273,   43,   44,   45,   -1,   93,   -1,  274,  275,  276,
  277,  278,  279,   -1,   -1,   -1,   33,   59,   60,   61,
   62,   -1,   -1,   40,   -1,   -1,   -1,   -1,   45,   41,
   -1,   -1,   44,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  274,  275,  276,  277,  278,  279,   -1,   59,   60,   61,
   62,   93,   -1,   41,   -1,   -1,   44,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  274,  275,  276,  277,  278,  279,
   -1,   59,   60,   61,   62,  274,  275,  276,  277,  278,
  279,   93,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  274,
  275,  276,  277,  278,  279,  280,  281,   -1,   -1,  274,
  275,  276,  277,  278,  279,   93,   -1,   33,  125,   -1,
   -1,   -1,   -1,   -1,   40,   -1,   -1,   -1,   -1,   45,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  274,  275,  276,
  277,  278,  279,   41,   -1,   -1,   44,   -1,  274,  275,
  276,  277,  278,  279,   41,   -1,   -1,   44,   -1,   -1,
   -1,   59,   60,   61,   62,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   59,   60,   61,   62,  276,  277,  278,  279,
   -1,   -1,  274,  275,  276,  277,  278,  279,  280,  281,
   -1,   -1,   -1,   -1,   -1,   93,  274,  275,  276,  277,
  278,  279,  280,  281,   -1,   -1,   93,  123,   -1,   -1,
   -1,   -1,   33,   -1,   -1,   -1,   -1,   -1,   -1,   40,
   -1,   -1,   -1,   -1,   45,   -1,   -1,   -1,   -1,   33,
   -1,   -1,   -1,   -1,   -1,   -1,   40,   -1,   -1,   -1,
   -1,   45,  274,  275,  276,  277,  278,  279,  280,  281,
  257,  258,  259,  260,  261,  262,  263,   33,  265,   -1,
   -1,   -1,   -1,  270,   40,   -1,  273,   -1,   41,   45,
   -1,   44,  274,  275,  276,  277,  278,  279,  280,  281,
   33,   -1,   -1,   -1,   -1,   -1,   59,   40,   61,   -1,
   -1,   -1,   45,   -1,   -1,   -1,  274,  275,  276,  277,
  278,  279,  280,  281,  125,   49,   41,   -1,   -1,   44,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   93,  125,   66,   -1,   59,   -1,   61,   -1,   -1,   -1,
   -1,   75,   76,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  257,  258,  259,  260,  261,  262,  263,   -1,  265,
   -1,   -1,   -1,   -1,  270,   -1,   -1,  273,   93,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  112,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  274,  275,  276,  277,
  278,  279,  280,  281,   -1,   -1,   -1,  274,  275,  276,
  277,  278,  279,  280,  281,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  146,  147,   -1,   -1,   -1,   -1,   -1,   -1,
  154,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  163,
  164,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  172,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,  260,
  261,  262,  263,   -1,  265,   -1,   -1,   -1,   -1,  270,
   -1,   -1,  273,  257,  258,  259,  260,  261,  262,  263,
   -1,  265,   -1,   -1,   -1,   -1,  270,   -1,   -1,  273,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  257,  258,  259,  260,  261,  262,  263,   -1,  265,
   -1,   -1,   -1,   -1,  270,   -1,   -1,  273,   -1,   -1,
   -1,  274,  275,   -1,  257,  258,  259,  280,  281,   -1,
   -1,   -1,   -1,  266,  267,  268,   56,   57,   -1,   -1,
  273,   61,   -1,   63,   64,   65,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  274,
  275,   81,   82,   83,   -1,  280,  281,   -1,   -1,   -1,
   -1,   -1,   92,   93,   94,   95,   96,   97,   -1,   -1,
  100,  101,  102,  103,  104,  105,  106,  107,  108,   -1,
  110,   -1,   -1,   -1,   -1,   -1,  116,   -1,   -1,   -1,
   -1,   -1,   -1,  123,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  148,  149,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  165,   -1,   -1,   -1,   -1,
   -1,  171,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=285;
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
"DISTINTO","IGUALDAD","AUMENTAR","DISMINUIR","MENOS_UNARIO","NEGACION","CASTP",
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
"sentencia : FOR '(' inicioFor ';' finFor ';' pasoFor ')' cuerpoCondicional",
"sentencia : incre",
"inicioFor : expresion '=' expresion",
"inicioFor :",
"finFor : expresionLogica",
"finFor : expresionComparacion",
"pasoFor : expresion '=' expresionAritmetica",
"pasoFor : incre",
"incre : expresion AUMENTAR",
"incre : expresion DISMINUIR",
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
"expresion : expresionAritmetica",
"expresion : expresionComparacion",
"expresion : expresionLogica",
"expresion : '-' expresion",
"expresion : expresion '.' ID",
"expresion : '(' expresion ')'",
"expresion : '(' tipoSimple ')' expresion",
"expresion : invocacion",
"expresion : expresion '[' expresion ']'",
"expresionAritmetica : expresion '+' expresion",
"expresionAritmetica : expresion '*' expresion",
"expresionAritmetica : expresion '/' expresion",
"expresionAritmetica : expresion '-' expresion",
"expresionAritmetica : expresion '%' expresion",
"expresionComparacion : expresion '>' expresion",
"expresionComparacion : expresion '<' expresion",
"expresionComparacion : expresion MAYORIGUALQUE expresion",
"expresionComparacion : expresion MENORIGUALQUE expresion",
"expresionComparacion : expresion DISTINTO expresion",
"expresionComparacion : expresion IGUALDAD expresion",
"expresionLogica : expresion Y expresion",
"expresionLogica : expresion O expresion",
"expresionLogica : '!' expresion",
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

//#line 279 "../../src/sintactico/sintactico.y"

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
//#line 641 "Parser.java"
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
//#line 44 "../../src/sintactico/sintactico.y"
{ 	List<Definicion> definiciones = (List<Definicion>)val_peek(8);																						
																											Definicion main = new DefFuncion(lexico.getLine(), lexico.getColumn(), "main",
																												new TipoFuncion(new ArrayList<DefVariable>(), TipoVoid.getInstancia()), 
																												(List<DefVariable>)val_peek(2), (List<Sentencia>)val_peek(1));
																											definiciones.add(main);
																																												
																											this.ast = new Programa(lexico.getLine(), lexico.getColumn(), definiciones); 
																										}
break;
case 2:
//#line 52 "../../src/sintactico/sintactico.y"
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
//#line 60 "../../src/sintactico/sintactico.y"
{	yyval = new ArrayList<Definicion>(); }
break;
case 4:
//#line 63 "../../src/sintactico/sintactico.y"
{ 	List<Definicion> lista = new ArrayList();
																											lista.add(new DefFuncion(lexico.getLine(), lexico.getColumn(), (String) val_peek(7),
																												new TipoFuncion((List<DefVariable>)val_peek(5), (Tipo)val_peek(8)), 
																												(List<DefVariable>)val_peek(2), (List<Sentencia>)val_peek(1)));
																											yyval = lista; 
																										}
break;
case 5:
//#line 69 "../../src/sintactico/sintactico.y"
{	List<Definicion> lista = new ArrayList();
																											lista.add(new DefFuncion(lexico.getLine(), lexico.getColumn(),  (String) val_peek(7),
																												new TipoFuncion((List<DefVariable>)val_peek(5), TipoVoid.getInstancia()), 
																												(List<DefVariable>)val_peek(2), (List<Sentencia>)val_peek(1)));
																											yyval = lista;
																										}
break;
case 6:
//#line 75 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(1);  }
break;
case 7:
//#line 78 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(2);
																											List<DefVariable> lista = (List<DefVariable>)yyval;																											
																											for(DefVariable elemento : (List<DefVariable>)val_peek(1))
																												lista.add(elemento); 
																										}
break;
case 8:
//#line 83 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<DefVariable>();}
break;
case 9:
//#line 86 "../../src/sintactico/sintactico.y"
{	yyval = val_peek(2); 
																											List<DefVariable> lista = (List<DefVariable>)yyval;																												
																											for(DefVariable elemento : (List<DefVariable>) val_peek(0))
																												lista.add(elemento);																												
																										}
break;
case 10:
//#line 91 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(0); }
break;
case 11:
//#line 92 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<DefVariable>(); }
break;
case 12:
//#line 95 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<DefVariable>();
																											((List<DefVariable>) yyval).add(new DefVariable(lexico.getLine(), 
																												lexico.getColumn(), (String)val_peek(0), (Tipo)val_peek(1)));  
																										}
break;
case 13:
//#line 101 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(0); }
break;
case 14:
//#line 102 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<Sentencia>(); }
break;
case 15:
//#line 105 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(1);((List<Sentencia>)yyval).add((Sentencia)val_peek(0)); }
break;
case 16:
//#line 106 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<Sentencia>(); ((List<Sentencia>)yyval).add((Sentencia)val_peek(0)); }
break;
case 17:
//#line 109 "../../src/sintactico/sintactico.y"
{ 	yyval = new Asignacion(lexico.getLine(), lexico.getColumn(), (Expresion)val_peek(3), (Expresion)val_peek(1)); }
break;
case 18:
//#line 110 "../../src/sintactico/sintactico.y"
{ 	yyval = new SentenciaWhile(lexico.getLine(), lexico.getColumn(), (Expresion)val_peek(2), (List<Sentencia>)val_peek(0)); }
break;
case 19:
//#line 111 "../../src/sintactico/sintactico.y"
{ 	yyval = new SentenciaIf(lexico.getLine(), lexico.getColumn(), (Expresion)val_peek(2), (List<Sentencia>)val_peek(0), new ArrayList()); }
break;
case 20:
//#line 112 "../../src/sintactico/sintactico.y"
{ 	yyval = new SentenciaIf(lexico.getLine(), lexico.getColumn(), (Expresion)val_peek(4), (List<Sentencia>)val_peek(2), (List<Sentencia>)val_peek(0)); }
break;
case 21:
//#line 113 "../../src/sintactico/sintactico.y"
{ 	yyval = new Escritura(lexico.getLine(), lexico.getColumn(),(List<Expresion>)val_peek(1)); }
break;
case 22:
//#line 114 "../../src/sintactico/sintactico.y"
{ 	yyval = new Lectura(lexico.getLine(), lexico.getColumn(),(List<Expresion>)val_peek(1)); }
break;
case 23:
//#line 115 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(1); }
break;
case 24:
//#line 116 "../../src/sintactico/sintactico.y"
{ 	yyval = new Return(lexico.getLine(), lexico.getColumn(), (Expresion)val_peek(1));}
break;
case 25:
//#line 117 "../../src/sintactico/sintactico.y"
{   yyval = new SentenciaFor(lexico.getLine(), lexico.getColumn(), (List<Sentencia>)val_peek(6), (Expresion)val_peek(4), (Sentencia)val_peek(2), (List<Sentencia>)val_peek(0));}
break;
case 26:
//#line 118 "../../src/sintactico/sintactico.y"
{   yyval = val_peek(0); }
break;
case 27:
//#line 121 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<Sentencia>();
																											Asignacion a = new Asignacion(lexico.getLine(), lexico.getColumn(), (Expresion)val_peek(2), (Expresion)val_peek(0)); 
																											((List<Sentencia>)yyval).add(a);
																										}
break;
case 28:
//#line 125 "../../src/sintactico/sintactico.y"
{   yyval = new ArrayList<Sentencia>(); }
break;
case 29:
//#line 128 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(0);  }
break;
case 30:
//#line 129 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(0);  }
break;
case 31:
//#line 132 "../../src/sintactico/sintactico.y"
{ 	yyval = new Asignacion(lexico.getLine(), lexico.getColumn(), (Expresion)val_peek(2), (Expresion)val_peek(0)); }
break;
case 32:
//#line 133 "../../src/sintactico/sintactico.y"
{   yyval = val_peek(0); }
break;
case 33:
//#line 137 "../../src/sintactico/sintactico.y"
{  	LiteralEntero a = new LiteralEntero(lexico.getLine(), lexico.getColumn(), 1);
			 																								Aritmetica b = new Aritmetica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(1), "+", a);	 
																											yyval = new Asignacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(1), b); 
																										}
break;
case 34:
//#line 141 "../../src/sintactico/sintactico.y"
{  	LiteralEntero a = new LiteralEntero(lexico.getLine(), lexico.getColumn(), 1);
			 																								Aritmetica b = new Aritmetica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(1), "-", a);	 
																											yyval = new Asignacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(1), b); 
																										}
break;
case 35:
//#line 147 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(1);	}
break;
case 36:
//#line 148 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<Sentencia>(); ((List<Sentencia>)yyval).add((Sentencia)val_peek(0));  }
break;
case 37:
//#line 151 "../../src/sintactico/sintactico.y"
{ 	List<DefVariable> variables = new ArrayList<DefVariable>();
																											for(String aux: (List<String>)val_peek(0))
																												variables.add(new DefVariable(lexico.getLine(), lexico.getColumn(), aux, (Tipo)val_peek(1)));																												 
																											yyval = variables; 
																										}
break;
case 38:
//#line 156 "../../src/sintactico/sintactico.y"
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
case 39:
//#line 165 "../../src/sintactico/sintactico.y"
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
case 40:
//#line 174 "../../src/sintactico/sintactico.y"
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
case 41:
//#line 190 "../../src/sintactico/sintactico.y"
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
case 42:
//#line 202 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(1); }
break;
case 43:
//#line 205 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(2); 
																											List<Expresion> lista = (List<Expresion>)yyval;
																											Expresion elemento = (Expresion)val_peek(0);
																											lista.add(elemento);																																																								
																										}
break;
case 44:
//#line 210 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<Expresion>(); ((List<Expresion>)yyval).add((Expresion)val_peek(0));  	}
break;
case 45:
//#line 213 "../../src/sintactico/sintactico.y"
{ 	yyval = new Variable(lexico.getLine(), lexico.getColumn(), (String) val_peek(0)); 	}
break;
case 46:
//#line 214 "../../src/sintactico/sintactico.y"
{ 	yyval = new LiteralEntero(lexico.getLine(), lexico.getColumn(), (Integer) val_peek(0));		}
break;
case 47:
//#line 215 "../../src/sintactico/sintactico.y"
{ 	yyval = new LiteralReal(lexico.getLine(), lexico.getColumn(), (Double) val_peek(0));	}
break;
case 48:
//#line 216 "../../src/sintactico/sintactico.y"
{ 	yyval = new LiteralCaracter(lexico.getLine(), lexico.getColumn(), (Character)val_peek(0));		}
break;
case 49:
//#line 217 "../../src/sintactico/sintactico.y"
{   yyval = val_peek(0); }
break;
case 50:
//#line 218 "../../src/sintactico/sintactico.y"
{   yyval = val_peek(0); }
break;
case 51:
//#line 219 "../../src/sintactico/sintactico.y"
{   yyval = val_peek(0); }
break;
case 52:
//#line 220 "../../src/sintactico/sintactico.y"
{ 	yyval = new MenosUnario(lexico.getLine(), lexico.getColumn(),  "-",(Expresion) val_peek(0));	}
break;
case 53:
//#line 221 "../../src/sintactico/sintactico.y"
{ 	yyval = new AccesoCampo(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), (String) val_peek(0));	}
break;
case 54:
//#line 222 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(1);}
break;
case 55:
//#line 223 "../../src/sintactico/sintactico.y"
{ 	yyval = new Cast(lexico.getLine(), lexico.getColumn(), (Tipo) val_peek(2), (Expresion) val_peek(0));	}
break;
case 56:
//#line 224 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(0);}
break;
case 57:
//#line 225 "../../src/sintactico/sintactico.y"
{ 	yyval = new AccesoArray(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(3), (Expresion) val_peek(1)); }
break;
case 58:
//#line 228 "../../src/sintactico/sintactico.y"
{ 	yyval = new Aritmetica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "+", (Expresion) val_peek(0));		}
break;
case 59:
//#line 229 "../../src/sintactico/sintactico.y"
{ 	yyval = new Aritmetica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "*", (Expresion) val_peek(0));		}
break;
case 60:
//#line 230 "../../src/sintactico/sintactico.y"
{ 	yyval = new Aritmetica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "/", (Expresion) val_peek(0));		}
break;
case 61:
//#line 231 "../../src/sintactico/sintactico.y"
{ 	yyval = new Aritmetica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "-", (Expresion) val_peek(0));		}
break;
case 62:
//#line 232 "../../src/sintactico/sintactico.y"
{ 	yyval = new Aritmetica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "%", (Expresion) val_peek(0));		}
break;
case 63:
//#line 235 "../../src/sintactico/sintactico.y"
{ 	yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), ">", (Expresion) val_peek(0));	}
break;
case 64:
//#line 236 "../../src/sintactico/sintactico.y"
{ 	yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "<", (Expresion) val_peek(0));	}
break;
case 65:
//#line 237 "../../src/sintactico/sintactico.y"
{ 	yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), ">=", (Expresion) val_peek(0));	}
break;
case 66:
//#line 238 "../../src/sintactico/sintactico.y"
{ 	yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "<=", (Expresion) val_peek(0));	}
break;
case 67:
//#line 239 "../../src/sintactico/sintactico.y"
{ 	yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "!=", (Expresion) val_peek(0));	}
break;
case 68:
//#line 240 "../../src/sintactico/sintactico.y"
{ 	yyval = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "==", (Expresion) val_peek(0));	}
break;
case 69:
//#line 242 "../../src/sintactico/sintactico.y"
{ 	yyval = new Logica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "&&", (Expresion) val_peek(0));	}
break;
case 70:
//#line 243 "../../src/sintactico/sintactico.y"
{ 	yyval = new Logica(lexico.getLine(), lexico.getColumn(), (Expresion) val_peek(2), "||", (Expresion) val_peek(0));	}
break;
case 71:
//#line 244 "../../src/sintactico/sintactico.y"
{ 	yyval = new Negacion(lexico.getLine(), lexico.getColumn(),  "!", (Expresion) val_peek(0));	}
break;
case 72:
//#line 247 "../../src/sintactico/sintactico.y"
{ 	yyval = new Invocacion(lexico.getLine(), lexico.getColumn(), new Variable(lexico.getLine(), lexico.getColumn(), (String)val_peek(3)),(List<Expresion>)val_peek(1)); 	}
break;
case 73:
//#line 250 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(0);}
break;
case 74:
//#line 251 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<Expresion>();	}
break;
case 75:
//#line 255 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(0); 
																											List<Integer> lista = (List<Integer>)yyval;
																											Integer elemento = (Integer)val_peek(2);
																											lista.add(elemento);
																										}
break;
case 76:
//#line 260 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList<Integer>(); ((List<Integer>)yyval).add((Integer)val_peek(1)); 	}
break;
case 77:
//#line 263 "../../src/sintactico/sintactico.y"
{ 	yyval = val_peek(2); 
																											List<String> lista = (List<String>)yyval;
																											String elemento = (String)val_peek(0);
																											if(!lista.contains(elemento))
																												lista.add(elemento);
																											else
																												new TipoError(lexico.getLine(), lexico.getColumn(),"Identificador duplicado -> " + elemento);																																																							
																										}
break;
case 78:
//#line 271 "../../src/sintactico/sintactico.y"
{ 	yyval = new ArrayList(); ((List<String>)yyval).add(((String) val_peek(0))); 	}
break;
case 79:
//#line 274 "../../src/sintactico/sintactico.y"
{ 	yyval = TipoEntero.getInstancia(); 	}
break;
case 80:
//#line 275 "../../src/sintactico/sintactico.y"
{ 	yyval = TipoReal.getInstancia(); 	}
break;
case 81:
//#line 276 "../../src/sintactico/sintactico.y"
{ 	yyval = TipoCaracter.getInstancia(); 	}
break;
//#line 1217 "Parser.java"
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
