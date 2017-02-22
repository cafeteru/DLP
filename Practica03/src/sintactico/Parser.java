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
//#line 23 "Parser.java"




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
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    2,    3,    1,    1,    6,   10,   10,   10,    8,
    8,    8,    9,    9,   12,   12,    7,    7,    5,    5,
   14,   14,   14,   14,   14,   14,   14,   14,   14,   19,
   19,   17,   20,   20,   20,   20,   20,    4,    4,   15,
   15,   15,   16,   16,   16,   18,   18,   13,   13,   13,
   13,   13,   13,   13,   13,   13,   13,   13,   13,   13,
   13,   13,   13,   13,   13,   13,   13,   13,   13,   13,
   21,   21,   11,   11,   11,
};
final static short yylen[] = {                            2,
    2,    7,    2,    2,    0,    8,    1,    1,    1,    4,
    2,    0,    3,    0,    3,    0,    1,    1,    2,    1,
    4,    4,    2,    7,    7,   11,    2,    2,    5,    3,
    0,    5,    2,    2,    2,    1,    1,    3,    0,    1,
    4,    7,    3,    6,    9,    3,    2,    1,    4,    7,
    1,    1,    1,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    2,    3,    4,
    3,    1,    1,    1,    1,
};
final static short yydefred[] = {                         5,
    0,    0,   73,   74,   75,    0,    1,    4,    0,   18,
    0,    0,    0,    0,    0,    0,    0,   39,    0,    0,
   11,    0,    0,    0,    0,    2,    0,    0,    0,    0,
    0,    0,    0,    0,   20,    0,    0,    0,    0,    0,
   10,   51,   52,   53,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   31,   19,    0,   72,    0,    0,
    0,    0,   38,   23,    0,    6,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   47,    0,    0,    0,    0,   36,
   37,    0,    0,    0,    0,    0,   43,    0,    0,    0,
   13,    0,    0,   69,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   67,    0,    0,
    0,    0,   34,   35,   33,    0,    0,    0,    0,   71,
   21,   22,    0,    0,   70,    0,    0,   32,    0,   29,
    8,    9,    7,   30,    0,    0,   15,    0,    0,    0,
    0,    0,   44,    0,   24,    0,   42,    0,   50,    0,
    0,    0,   45,    0,   26,
};
final static short yydgoto[] = {                          1,
    2,    7,   22,   23,   33,    8,    9,   16,   40,  144,
   34,  101,   48,   35,   36,   57,   38,   49,   94,   92,
   60,
};
final static short yysindex[] = {                         0,
    0, -157,    0,    0,    0, -258,    0,    0, -254,    0,
  -21,  -14,    2, -180,  -96,   -6, -205,    0,  -54, -180,
    0,  -51,  364,    0, -181,    0,  438,  438,   52,   53,
  -25,  -29,  364,  -90,    0,   38,    4,   46,  364,  -18,
    0,    0,    0,    0,   25,  438,  588,   92,   67,   67,
  438,  438, -251, -140,    0,    0,   62,    0, -126,   16,
  438,  438,    0,    0,  318,    0, -125,   87,  100,  116,
  438,  438,  438,  438,  438,  438,  438,  438,  438,  438,
  438,  438,  438,  438,    0,  438,  127,  149,   55,    0,
    0,  228,   51,   -4,   54, -124,    0,  156,  336,  438,
    0,   56, -122,    0,  426,  426,  619,  619,  619,  619,
  619,  619,  370,  370,   87,   87,   87,    0,  405,   32,
   33, -107,    0,    0,    0,   75,  108, -213,  -89,    0,
    0,    0,  398,   80,    0,  364,  364,    0,  -82,    0,
    0,    0,    0,    0,  -80,   22,    0,  -78,  644,  664,
   88,   95,    0,  104,    0,  -84,    0,  -87,    0,   77,
   24,  364,    0,  678,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,  -67,    0,    0,    0,    0,
    0,    0,    0,   20,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  691,    0,    0,    0,    0,    0,    0,
    0,  151,   85,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  -37,    0,    0,    0,  295,  605,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   89,    0,    0,   11,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  272,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  771,  817,  377,  490,  510,  533,
  559,  797,  446,  466,   35,   59,   83,    0,  255,    0,
    0,    0,    0,    0,    0,  143,    0,    0,    0,    0,
    0,    0,    0,  -13,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  620,    0,    0,    0,    0,
    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,    0,  189,  -22,    0,    0,    0,    0,    0,
   37,    0,  914,   57,  -50,   -3,  125,  191,    0,    0,
 -117,
};
final static int YYTABLESIZE=1091;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         48,
   59,  145,   90,   48,   48,   48,   48,   48,   48,   48,
   55,  146,   11,    3,    4,    5,   65,   12,   13,   37,
   89,   48,   48,   49,   48,   14,   18,   49,   49,   49,
   49,   49,   49,   49,   19,   37,  127,   20,   10,  128,
  161,  123,   15,  141,  142,   49,   49,   68,   49,   91,
   17,   68,   68,   68,   68,   68,   25,   68,  143,   96,
   12,   54,   63,   12,   62,   96,   21,   96,   24,   68,
   68,   55,   68,   26,   97,   55,   55,   55,   55,   55,
  153,   55,  163,   69,    3,    4,    5,   48,  124,   56,
   41,   51,   52,   55,   55,   56,   55,   53,   61,   56,
   56,   56,   56,   56,   64,   56,   66,    3,    4,    5,
   86,   49,    6,  149,  150,   67,   93,   56,   56,   58,
   56,   56,   62,   58,   58,   58,   58,   58,   83,   58,
   95,  102,   84,   81,   79,   68,   80,   84,   82,  164,
  103,   58,   58,  126,   58,   54,  129,  130,  134,  135,
   85,   78,   83,   77,  136,  137,  104,   81,   79,   55,
   80,   84,   82,   83,  138,  139,  140,  120,   81,   79,
  148,   80,   84,   82,  151,   78,  152,   77,  154,  160,
  157,   58,   58,   56,   58,   83,   78,  158,   77,  121,
   81,   79,   83,   80,   84,   82,  159,   81,   79,  162,
   80,   84,   82,   41,   17,   56,   56,   58,   78,    3,
   77,   40,   39,   16,  131,   78,  125,   77,   50,    0,
   56,    0,   48,   48,   48,   48,    0,   48,   48,   48,
   48,   48,    0,    0,   48,   48,   48,   48,   48,   48,
   48,    0,    0,    0,    0,    0,   49,   49,   49,   49,
    0,   49,   49,   49,   49,   49,    0,    0,   49,   49,
   49,   49,   49,   49,   49,    0,    0,   41,    0,    0,
   68,   68,   68,   68,    0,   68,   68,   68,   68,   68,
    0,    0,   68,   68,   68,   68,   68,   68,   68,    0,
    0,    0,    0,    0,   55,   55,   55,   55,   46,   55,
   55,   55,   55,   55,    0,    0,   55,   55,   55,   55,
   55,   55,   55,    0,    0,    0,    0,    0,   56,   56,
   56,   56,    0,   56,   56,   56,   56,   56,    0,    0,
   56,   56,   56,   56,   56,   56,   56,    0,    0,    0,
    0,    0,   58,   58,   58,   58,    0,   58,   58,   58,
   58,   58,  122,    0,   58,   58,   58,   58,   58,   58,
   58,    0,    0,    0,   71,   72,   73,   74,   75,   76,
    0,    0,   83,    0,    0,    0,    0,   81,   79,   46,
   80,   84,   82,    0,    0,    0,    0,    0,   71,   72,
   73,   74,   75,   76,  132,   78,   40,   77,    0,   71,
   72,   73,   74,   75,   76,    0,   83,   41,   41,   41,
   41,   81,    0,    0,   41,   84,   82,   61,    0,   28,
   61,   71,   72,   73,   74,   75,   76,    0,   71,   72,
   73,   74,   75,   76,   83,   61,   61,    0,   61,   81,
   79,   83,   80,   84,   82,    0,   81,   79,    0,   80,
   84,   82,    0,    0,    0,    0,  147,   78,    0,   77,
    0,    0,   83,    0,   78,    0,   77,   81,   79,    0,
   80,   84,   82,    0,    0,    0,    0,   47,    0,    0,
    0,    0,   46,    0,    0,   78,   54,   77,   54,   54,
   54,    0,    3,    4,    5,   31,    0,    0,    0,   89,
    0,   61,    0,    0,   54,   54,   57,   54,   57,   57,
   57,    0,    0,    0,   46,   46,   46,   46,    0,   46,
   46,   46,   46,   46,   57,   57,   46,   57,    0,    0,
   62,    0,    0,   62,    0,    0,   40,   40,   40,   40,
    0,    0,    0,   40,    0,    0,    0,    0,   62,   62,
   63,   62,    0,   63,   28,   28,   28,   28,    0,   28,
   28,   28,   28,   28,    0,    0,   28,    0,   63,   63,
   54,   63,    0,   64,    0,    0,   64,   27,   28,   29,
   30,    0,    3,    4,    5,   31,  100,    0,    0,   32,
   57,   64,   64,    0,   64,    0,    0,    0,    0,   59,
    0,    0,   59,    0,    0,    0,    0,    0,   71,   72,
   73,   74,   75,   76,   62,    0,    0,   59,   59,    0,
   59,    0,    0,   27,   28,   29,   30,   47,    3,    4,
    5,   31,   46,    0,   63,   32,   61,   61,   61,   61,
    0,   61,   61,   61,   61,   61,    0,    0,   61,   61,
   61,   61,   61,   61,   61,   83,    0,   64,    0,    0,
   81,   79,    0,   80,   84,   82,    0,    0,    0,    0,
   71,   72,   73,   74,   75,   76,    0,   71,   72,   73,
   74,   75,   76,   59,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   42,   43,   44,    0,    0,    0,
   73,   74,   75,   76,    0,   54,   54,   54,   54,   45,
   54,   54,   54,   54,   54,    0,    0,   54,   54,   54,
   54,   54,   54,   54,    0,   57,   57,   57,   57,   27,
   57,   57,   57,   57,   57,    0,    0,   57,   57,   57,
   57,   57,   57,   57,   25,    0,    0,    0,    0,   62,
   62,   62,   62,    0,   62,   62,   62,   62,   62,    0,
    0,   62,   62,   62,   62,   62,   62,   62,  155,   63,
   63,   63,   63,    0,   63,   63,   63,   63,   63,    0,
    0,   63,   63,   63,   63,   63,   63,   63,  156,    0,
    0,    0,   64,   64,   64,   64,    0,   64,   64,   64,
   64,   64,  165,    0,   64,   64,   64,   64,   64,   64,
   64,   65,    0,    0,   65,   14,    0,    0,   59,   59,
   59,   59,    0,   59,   59,   59,   59,   59,    0,   65,
   59,   59,   59,   59,   59,   59,   59,   60,    0,    0,
   60,    0,    0,    0,   42,   43,   44,    0,    0,    0,
    0,    0,    3,    4,    5,   60,   60,   66,   60,   45,
   66,    0,    0,    0,   27,   27,   27,   27,    0,   27,
   27,   27,   27,   27,    0,   66,   27,    0,    0,   25,
   25,   25,   25,    0,   25,   25,   25,   25,   25,    0,
    0,   25,    0,    0,    0,   65,    0,    0,    0,    0,
    0,    0,    0,   27,   28,   29,   30,    0,    3,    4,
    5,   31,    0,    0,    0,   32,    0,    0,    0,    0,
    0,   60,    0,   27,   28,   29,   30,    0,    3,    4,
    5,   31,    0,    0,    0,   32,    0,   27,   28,   29,
   30,   66,    3,    4,    5,   31,    0,    0,    0,   32,
   39,   39,   39,   39,    0,   39,   39,   39,   39,   68,
   70,    0,   39,    0,   87,   88,    0,    0,    0,    0,
    0,    0,    0,    0,   98,   99,    0,    0,    0,    0,
    0,    0,    0,    0,  105,  106,  107,  108,  109,  110,
  111,  112,  113,  114,  115,  116,  117,  118,    0,  119,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  133,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   65,   65,   65,   65,    0,   65,   65,   65,   65,   65,
    0,    0,   65,   65,   65,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   60,   60,   60,   60,
    0,   60,   60,   60,   60,   60,    0,    0,   60,   60,
   60,   60,   60,   60,   60,    0,   66,   66,   66,   66,
    0,   66,   66,   66,   66,   66,    0,    0,   66,   66,
   66,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
   91,   91,   53,   41,   42,   43,   44,   45,   46,   47,
   40,  129,  271,  265,  266,  267,   39,  272,   40,   23,
  272,   59,   60,   37,   62,   40,  123,   41,   42,   43,
   44,   45,   46,   47,   41,   39,   41,   44,    2,   44,
  158,   92,   41,  257,  258,   59,   60,   37,   62,   53,
   14,   41,   42,   43,   44,   45,   20,   47,  272,   44,
   41,   91,   59,   44,   61,   44,  272,   44,  123,   59,
   60,   37,   62,  125,   59,   41,   42,   43,   44,   45,
   59,   47,   59,   47,  265,  266,  267,  125,   92,   33,
  272,   40,   40,   59,   60,   37,   62,  123,   61,   41,
   42,   43,   44,   45,   59,   47,  125,  265,  266,  267,
   44,  125,  270,  136,  137,   91,  257,   59,   60,   37,
   62,   65,   61,   41,   42,   43,   44,   45,   37,   47,
  257,  257,   46,   42,   43,  125,   45,   46,   47,  162,
   41,   59,   60,   93,   62,   91,   93,  272,   93,  272,
   59,   60,   37,   62,  123,  123,   41,   42,   43,  125,
   45,   46,   47,   37,  272,   91,   59,   41,   42,   43,
   91,   45,   46,   47,  257,   60,  257,   62,  257,  264,
   93,  272,  272,  125,  272,   37,   60,   93,   62,   41,
   42,   43,   37,   45,   46,   47,   93,   42,   43,  123,
   45,   46,   47,   61,  272,  149,  150,  125,   60,  125,
   62,   61,   24,  125,   59,   60,   92,   62,   28,   -1,
  164,   -1,  260,  261,  262,  263,   -1,  265,  266,  267,
  268,  269,   -1,   -1,  272,  273,  274,  275,  276,  277,
  278,   -1,   -1,   -1,   -1,   -1,  260,  261,  262,  263,
   -1,  265,  266,  267,  268,  269,   -1,   -1,  272,  273,
  274,  275,  276,  277,  278,   -1,   -1,  125,   -1,   -1,
  260,  261,  262,  263,   -1,  265,  266,  267,  268,  269,
   -1,   -1,  272,  273,  274,  275,  276,  277,  278,   -1,
   -1,   -1,   -1,   -1,  260,  261,  262,  263,   44,  265,
  266,  267,  268,  269,   -1,   -1,  272,  273,  274,  275,
  276,  277,  278,   -1,   -1,   -1,   -1,   -1,  260,  261,
  262,  263,   -1,  265,  266,  267,  268,  269,   -1,   -1,
  272,  273,  274,  275,  276,  277,  278,   -1,   -1,   -1,
   -1,   -1,  260,  261,  262,  263,   -1,  265,  266,  267,
  268,  269,  125,   -1,  272,  273,  274,  275,  276,  277,
  278,   -1,   -1,   -1,  273,  274,  275,  276,  277,  278,
   -1,   -1,   37,   -1,   -1,   -1,   -1,   42,   43,  125,
   45,   46,   47,   -1,   -1,   -1,   -1,   -1,  273,  274,
  275,  276,  277,  278,   59,   60,  125,   62,   -1,  273,
  274,  275,  276,  277,  278,   -1,   37,  265,  266,  267,
  268,   42,   -1,   -1,  272,   46,   47,   41,   -1,  125,
   44,  273,  274,  275,  276,  277,  278,   -1,  273,  274,
  275,  276,  277,  278,   37,   59,   60,   -1,   62,   42,
   43,   37,   45,   46,   47,   -1,   42,   43,   -1,   45,
   46,   47,   -1,   -1,   -1,   -1,   59,   60,   -1,   62,
   -1,   -1,   37,   -1,   60,   -1,   62,   42,   43,   -1,
   45,   46,   47,   -1,   -1,   -1,   -1,   40,   -1,   -1,
   -1,   -1,   45,   -1,   -1,   60,   41,   62,   43,   44,
   45,   -1,  265,  266,  267,  268,   -1,   -1,   -1,  272,
   -1,  125,   -1,   -1,   59,   60,   41,   62,   43,   44,
   45,   -1,   -1,   -1,  260,  261,  262,  263,   -1,  265,
  266,  267,  268,  269,   59,   60,  272,   62,   -1,   -1,
   41,   -1,   -1,   44,   -1,   -1,  265,  266,  267,  268,
   -1,   -1,   -1,  272,   -1,   -1,   -1,   -1,   59,   60,
   41,   62,   -1,   44,  260,  261,  262,  263,   -1,  265,
  266,  267,  268,  269,   -1,   -1,  272,   -1,   59,   60,
  125,   62,   -1,   41,   -1,   -1,   44,  260,  261,  262,
  263,   -1,  265,  266,  267,  268,  269,   -1,   -1,  272,
  125,   59,   60,   -1,   62,   -1,   -1,   -1,   -1,   41,
   -1,   -1,   44,   -1,   -1,   -1,   -1,   -1,  273,  274,
  275,  276,  277,  278,  125,   -1,   -1,   59,   60,   -1,
   62,   -1,   -1,  260,  261,  262,  263,   40,  265,  266,
  267,  268,   45,   -1,  125,  272,  260,  261,  262,  263,
   -1,  265,  266,  267,  268,  269,   -1,   -1,  272,  273,
  274,  275,  276,  277,  278,   37,   -1,  125,   -1,   -1,
   42,   43,   -1,   45,   46,   47,   -1,   -1,   -1,   -1,
  273,  274,  275,  276,  277,  278,   -1,  273,  274,  275,
  276,  277,  278,  125,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  257,  258,  259,   -1,   -1,   -1,
  275,  276,  277,  278,   -1,  260,  261,  262,  263,  272,
  265,  266,  267,  268,  269,   -1,   -1,  272,  273,  274,
  275,  276,  277,  278,   -1,  260,  261,  262,  263,  125,
  265,  266,  267,  268,  269,   -1,   -1,  272,  273,  274,
  275,  276,  277,  278,  125,   -1,   -1,   -1,   -1,  260,
  261,  262,  263,   -1,  265,  266,  267,  268,  269,   -1,
   -1,  272,  273,  274,  275,  276,  277,  278,  125,  260,
  261,  262,  263,   -1,  265,  266,  267,  268,  269,   -1,
   -1,  272,  273,  274,  275,  276,  277,  278,  125,   -1,
   -1,   -1,  260,  261,  262,  263,   -1,  265,  266,  267,
  268,  269,  125,   -1,  272,  273,  274,  275,  276,  277,
  278,   41,   -1,   -1,   44,  125,   -1,   -1,  260,  261,
  262,  263,   -1,  265,  266,  267,  268,  269,   -1,   59,
  272,  273,  274,  275,  276,  277,  278,   41,   -1,   -1,
   44,   -1,   -1,   -1,  257,  258,  259,   -1,   -1,   -1,
   -1,   -1,  265,  266,  267,   59,   60,   41,   62,  272,
   44,   -1,   -1,   -1,  260,  261,  262,  263,   -1,  265,
  266,  267,  268,  269,   -1,   59,  272,   -1,   -1,  260,
  261,  262,  263,   -1,  265,  266,  267,  268,  269,   -1,
   -1,  272,   -1,   -1,   -1,  125,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  260,  261,  262,  263,   -1,  265,  266,
  267,  268,   -1,   -1,   -1,  272,   -1,   -1,   -1,   -1,
   -1,  125,   -1,  260,  261,  262,  263,   -1,  265,  266,
  267,  268,   -1,   -1,   -1,  272,   -1,  260,  261,  262,
  263,  125,  265,  266,  267,  268,   -1,   -1,   -1,  272,
  260,  261,  262,  263,   -1,  265,  266,  267,  268,   46,
   47,   -1,  272,   -1,   51,   52,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   61,   62,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   71,   72,   73,   74,   75,   76,
   77,   78,   79,   80,   81,   82,   83,   84,   -1,   86,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  100,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  260,  261,  262,  263,   -1,  265,  266,  267,  268,  269,
   -1,   -1,  272,  273,  274,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  260,  261,  262,  263,
   -1,  265,  266,  267,  268,  269,   -1,   -1,  272,  273,
  274,  275,  276,  277,  278,   -1,  260,  261,  262,  263,
   -1,  265,  266,  267,  268,  269,   -1,   -1,  272,  273,
  274,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=279;
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
"DISTINTO","IGUALDAD","MENOS_UNARIO",
};
final static String yyrule[] = {
"$accept : programa",
"programa : funciones main",
"main : VOID MAIN '(' ')' '{' cuerpoMain '}'",
"cuerpoMain : variables sentencias",
"funciones : funciones funcion",
"funciones :",
"funcion : tipoFuncion ID '(' parametro ')' '{' cuerpo '}'",
"tipoParametro : ID",
"tipoParametro : CTE_ENTERA",
"tipoParametro : CTE_REAL",
"parametro : parametro ',' tipoSimple ID",
"parametro : tipoSimple ID",
"parametro :",
"cuerpo : variables sentencias retorno",
"cuerpo :",
"retorno : RETURN expresion ';'",
"retorno :",
"tipoFuncion : VOID",
"tipoFuncion : tipoSimple",
"sentencias : sentencias sentencia",
"sentencias : sentencia",
"sentencia : llamadaVariable '=' expresion ';'",
"sentencia : declaracionVariable '=' expresion ';'",
"sentencia : estructura ';'",
"sentencia : WHILE '(' expresion ')' '{' sentencias '}'",
"sentencia : IF '(' expresion ')' '{' sentencias '}'",
"sentencia : IF '(' expresion ')' '{' sentencias '}' ELSE '{' sentencias '}'",
"sentencia : WRITE expresiones",
"sentencia : READ expresiones",
"sentencia : ID '(' parametroLlamada ')' ';'",
"parametroLlamada : parametroLlamada ',' tipoParametro",
"parametroLlamada :",
"estructura : STRUCT '{' campos '}' ID",
"campos : campos estructura",
"campos : campos llamadaVariable",
"campos : campos declaracionVariable",
"campos : llamadaVariable",
"campos : declaracionVariable",
"variables : variables declaracionVariable ';'",
"variables :",
"llamadaVariable : ID",
"llamadaVariable : ID '[' CTE_ENTERA ']'",
"llamadaVariable : ID '[' CTE_ENTERA ']' '[' CTE_ENTERA ']'",
"declaracionVariable : tipoSimple identificador ';'",
"declaracionVariable : tipoSimple '[' CTE_ENTERA ']' identificador ';'",
"declaracionVariable : tipoSimple '[' CTE_ENTERA ']' '[' CTE_ENTERA ']' identificador ';'",
"expresiones : expresiones ',' expresion",
"expresiones : expresion ';'",
"expresion : ID",
"expresion : ID '[' CTE_ENTERA ']'",
"expresion : ID '[' CTE_ENTERA ']' '[' CTE_ENTERA ']'",
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
"expresion : '(' expresion ')'",
"expresion : '(' tipoSimple ')' ID",
"identificador : identificador ',' ID",
"identificador : ID",
"tipoSimple : INT",
"tipoSimple : DOUBLE",
"tipoSimple : CHAR",
};

//#line 151 "../../src/sintactico/sintactico.y"

// * Código Java
// * Se crea una clase "Parser", lo que aquí ubiquemos será:
//	- Atributos, si son variables
//	- Métodos, si son funciones
//   de la clase "Parser"

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
//#line 576 "Parser.java"
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
