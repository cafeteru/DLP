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
public final static short NEGACION=280;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    3,    3,    8,   10,   10,   10,    5,
    5,    5,    6,    6,    2,    2,   11,   11,   11,   11,
   11,   11,   11,   11,   11,   11,   11,   11,   11,   11,
   11,   14,   14,   12,   12,   12,    7,    7,    7,   17,
   17,    9,    9,   13,   13,   13,   13,   13,   13,   13,
   13,   13,   13,   13,   13,   13,   13,   13,   13,   13,
   13,   13,   13,   13,   13,   13,   16,   16,   15,   15,
    4,    4,    4,    4,
};
final static short yylen[] = {                            2,
    8,    2,    0,    8,    2,    4,    1,    1,    1,    4,
    2,    0,    1,    0,    2,    1,    1,    2,    4,    4,
    6,    7,    7,    5,    4,    2,    3,    3,    5,    2,
    3,    3,    0,    1,    4,    7,    2,    3,    5,    3,
    2,    3,    1,    1,    1,    1,    1,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    3,    2,    2,    3,    4,    1,    4,    3,    3,    1,
    1,    1,    1,    1,
};
final static short yydefred[] = {                         3,
    0,    0,   72,   73,   74,    0,    0,    2,    0,    0,
    0,    0,    0,    0,    0,    0,    5,   71,    0,    0,
    0,    0,    0,    0,    0,   70,    0,    0,   41,    0,
    0,    0,    0,    0,   68,   69,    0,   39,   40,    0,
   11,    0,    0,   67,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   16,    0,    0,    0,   45,   46,
   47,    0,    0,    0,    0,   66,    0,   44,    0,    0,
    0,    0,    0,   26,    0,    0,    0,    0,    1,   15,
    0,   18,   30,    0,    0,    0,   10,    0,    0,    0,
    0,    0,   28,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   27,    0,
    0,    0,   31,    0,    0,    0,    0,    0,    0,    4,
    0,   64,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   61,    0,    0,   25,
    0,    0,    6,    0,    0,   20,   19,   65,    0,    0,
   24,    0,    0,   29,    8,    9,    7,   32,    0,    0,
    0,   21,   22,   23,   36,
};
final static short yydgoto[] = {                          1,
    2,   52,    8,   19,   34,   86,   53,   66,  116,  158,
   55,   68,   69,  117,   15,   16,   21,
};
final static short yysindex[] = {                         0,
    0,  149,    0,    0,    0, -120, -245,    0,  -90,  -22,
  331,    4,   10, -219,    5,  -89,    0,    0,  -64,   -5,
   85,   14,  -78,  -17, -207,    0, -173,    5,    0, -178,
   60,   31, -145,   41,    0,    0,   35,    0,    0,  612,
    0,   39,  -78,    0,  108,  108,   92,  135,  121,  108,
    6,  307,   86,  126,    0,  140,  612,  -68,    0,    0,
    0,  -28,  108,  108,  213,    0,  -30,    0,  246,  -12,
  108,  108,  612,    0,   97,  108,  -61,  108,    0,    0,
  108,    0,    0,  108,  612,   87,    0,  108,  171,  171,
  177,  124,    0,  108,  108,  108,  108,  108,  108,  108,
  108,  108,  108,  108,  108,  108,  108,  108,    0,  131,
  153,  400,    0,  -31,  173,   49,   82,  160,  218,    0,
  108,    0,  246,  281,  281,  494,  494,  494,  494,  494,
  494,  220,  220,  171,  171,  171,    0,   98,  375,    0,
  151,  108,    0,  184, -122,    0,    0,    0,  612,  612,
    0,  108,  317,    0,    0,    0,    0,    0,  435,  460,
    9,    0,    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,  -63,    0,    0,    0,
    0,    0,   66,    0,  120,    0,    0,    0,    0,    0,
    0,    0,  105,    0,    0,    0,    0,  155,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  342,    0,    0,    0,    0,  521,  129,    0,    0,    0,
    0,  -26,    0,    0,    0,    0,    0,    0,    7,    0,
    0,    0,    0,    0,    0,    0,    0,  119,    0,    0,
    0,    0,    0,    0,  134,    0,    0,    0,   -2,   27,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  238,  240,  275,  404,  415,  440,  468,  485,
  491,  351,  380,   36,   62,   71,    0,    0,    0,    0,
  -37,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    2,    0,  115,    0,    0,   90,  752,  -15,    0,
  403,  754,  750,    0,  229,    0,    0,
};
final static int YYTABLESIZE=914;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         35,
   14,   27,   11,   35,   35,   35,   35,   35,   35,   35,
   34,   88,   94,   94,   34,   34,   34,   34,   34,   34,
   34,   35,   35,   35,   35,   12,   14,   71,   93,   67,
   70,   94,   34,   34,   63,   34,   17,   24,   63,   63,
   63,   63,   63,   22,   63,   78,  109,   43,   25,   23,
   43,   77,   94,   29,   32,   35,   63,   63,   85,   63,
  114,  141,   76,   62,   36,   43,   34,   62,   62,   62,
   62,   62,   49,   62,  112,   35,   49,   49,   49,   49,
   49,   42,   49,   37,   43,   62,   62,   35,   62,  143,
   63,   10,   94,   38,   49,   49,   76,   49,   50,   43,
   20,  165,   50,   50,   50,   50,   50,   52,   50,   70,
   31,   52,   52,   52,   52,   52,    9,   52,   39,   62,
   50,   50,  144,   50,   70,  145,   41,   44,   49,   52,
   52,   71,   52,  107,  155,  156,  161,   33,  105,  103,
   63,  104,  108,  106,   82,   12,   81,   65,   12,  157,
  159,  160,   64,   40,   50,  113,  102,   58,  101,   33,
  107,   57,   33,   52,  122,  105,  103,  107,  104,  108,
  106,  138,  105,  103,   72,  104,  108,  106,   37,   91,
   37,   13,   26,  102,   83,  101,    3,    4,    5,  107,
  102,   18,  101,  139,  105,  103,  107,  104,  108,  106,
   84,  105,  103,   87,  104,  108,  106,   26,   71,   30,
  115,  120,  102,   38,  101,   38,  108,  121,  146,  102,
  149,  101,   35,   35,   35,   35,   35,   35,   35,   35,
   35,   35,   35,  142,   35,   35,   35,   35,   35,   35,
   35,  152,  154,   73,   28,   63,   34,   34,   34,   34,
   34,   34,   65,   14,  107,    0,  107,   64,   13,  105,
  103,  105,  104,  108,  106,  108,  106,    0,    0,    0,
   63,   63,   63,   63,   63,   63,  147,  102,   42,  101,
   59,   42,  107,   59,    0,    0,    0,  105,  103,    0,
  104,  108,  106,    0,    0,    0,   42,    0,   59,   62,
   62,   62,   62,   62,   62,  102,    0,  101,   49,   49,
   49,   49,   49,   49,    0,   60,    0,  107,   60,    0,
    0,    0,  105,  103,    0,  104,  108,  106,    0,    0,
   42,    0,   59,   60,   50,   50,   50,   50,   50,   50,
  102,    0,  101,   52,   52,   52,   52,   52,   52,    3,
    4,    5,    6,  107,   18,    0,    0,    0,  105,  103,
    0,  104,  108,  106,   59,   60,   61,   60,    0,   95,
   96,   97,   98,   99,  100,  162,  102,    0,  101,   62,
   45,   46,   47,   48,   49,    3,    4,    5,    6,   50,
   18,   48,   51,   48,   48,   48,   95,   96,   97,   98,
   99,  100,   34,   95,   96,   97,   98,   99,  100,   48,
   48,    0,   48,    3,    4,    5,    6,    0,    7,    0,
   51,    0,   51,   51,   51,   95,   96,   97,   98,   99,
  100,   79,   95,   96,   97,   98,   99,  100,   51,   51,
    0,   51,    0,   48,   55,    0,    0,   55,    0,    0,
    0,   74,    0,    0,   80,   56,    0,    0,   56,    0,
    0,    0,   55,   55,    0,   55,   34,    0,    0,   59,
   60,   61,   51,   56,   56,    0,   56,    3,    4,    5,
   57,    0,   18,   57,   62,    0,    0,   80,    0,    0,
   95,   96,   97,   98,   99,  100,   55,  150,   57,   57,
    0,   57,    0,    0,    0,    0,    0,   56,   58,    0,
    0,   58,   59,   59,   80,    0,    0,    0,   95,   96,
   97,   98,   99,  100,  140,   53,   58,   58,   53,   58,
  107,   54,   57,    0,   54,  105,  103,    0,  104,  108,
  106,  151,    0,   53,   53,    0,   53,   60,   60,   54,
   54,    0,   54,    0,    0,   97,   98,   99,  100,  163,
   58,   80,   80,    0,    0,    0,   45,   46,   47,   48,
   49,    3,    4,    5,    6,   50,   18,   53,   51,    0,
    0,    0,    0,   54,  164,    0,    0,    0,    0,   95,
   96,   97,   98,   99,  100,    3,    4,    5,    6,    0,
   18,   34,   34,   34,   34,   34,   34,   34,   34,   34,
   34,   34,    0,   34,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   48,   48,   48,   48,   48,   48,    0,
    0,    0,    0,    0,   45,   46,   47,   48,   49,    3,
    4,    5,    6,   50,   18,   17,   51,    0,    0,    0,
    0,    0,   51,   51,   51,   51,   51,   51,    0,   45,
   46,   47,   48,   49,    3,    4,    5,    6,   50,   18,
    0,   51,    0,    0,    0,    0,   55,   55,   55,   55,
   55,   55,    0,    0,    0,    0,    0,   56,   56,   56,
   56,   56,   56,    0,   45,   46,   47,   48,   49,    3,
    4,    5,    6,   50,   18,    0,   51,    0,    0,    0,
    0,    0,   57,   57,   57,   57,   57,   57,    0,   45,
   46,   47,   48,   49,    3,    4,    5,    6,   50,   18,
    0,   51,    0,    0,    0,    0,    0,    0,    0,    0,
   58,   58,   58,   58,   58,   58,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   53,   53,   53,
   53,   53,   53,   54,   54,   54,   54,   54,   54,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   17,   17,   17,   17,   17,   17,   17,   17,   17,   17,
   17,   54,   17,   56,    0,    0,    0,    0,    0,   75,
   54,    0,   56,   54,    0,   56,    0,    0,   54,    0,
   56,    0,   89,   90,   92,    0,    0,    0,    0,    0,
  110,  111,    0,    0,   54,    0,   56,    0,    0,    0,
  118,    0,    0,  119,    0,    0,   54,    0,   56,    0,
    0,    0,    0,  123,  124,  125,  126,  127,  128,  129,
  130,  131,  132,  133,  134,  135,  136,  137,    0,    0,
    0,    0,    0,   54,    0,   56,    0,    0,    0,    0,
  148,   45,   46,   47,   48,   49,    3,    4,    5,    6,
   50,   18,    0,   51,    0,    0,    0,    0,    0,    0,
   54,  153,   56,    0,    0,    0,    0,    0,    0,    0,
   54,   54,   56,   56,    0,    0,    0,    0,    0,    0,
   54,   54,   56,   56,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
   91,   91,  123,   41,   42,   43,   44,   45,   46,   47,
   37,   40,   44,   44,   41,   42,   43,   44,   45,   46,
   47,   59,   60,   61,   62,  271,   91,   91,   59,   45,
   46,   44,   59,   60,   37,   62,   59,  257,   41,   42,
   43,   44,   45,   40,   47,   40,   59,   41,   44,   40,
   44,   46,   44,   59,   41,   93,   59,   60,   57,   62,
   76,   93,   91,   37,  272,   59,   93,   41,   42,   43,
   44,   45,   37,   47,   73,   93,   41,   42,   43,   44,
   45,   41,   47,  257,   44,   59,   60,  125,   62,   41,
   93,    2,   44,  272,   59,   60,   91,   62,   37,   93,
   11,   93,   41,   42,   43,   44,   45,   37,   47,   44,
   21,   41,   42,   43,   44,   45,    2,   47,   59,   93,
   59,   60,   41,   62,   59,   44,  272,   93,   93,   59,
   60,   40,   62,   37,  257,  258,  152,   23,   42,   43,
   33,   45,   46,   47,   59,   41,   61,   40,   44,  272,
  149,  150,   45,  123,   93,   59,   60,   43,   62,   41,
   37,  123,   44,   93,   41,   42,   43,   37,   45,   46,
   47,   41,   42,   43,   40,   45,   46,   47,   59,   65,
   61,  272,  272,   60,   59,   62,  265,  266,  267,   37,
   60,  270,   62,   41,   42,   43,   37,   45,   46,   47,
   61,   42,   43,  272,   45,   46,   47,  272,  272,  125,
  272,  125,   60,   59,   62,   61,   46,   41,   59,   60,
  123,   62,  260,  261,  262,  263,  264,  265,  266,  267,
  268,  269,  270,   61,  272,  273,  274,  275,  276,  277,
  278,   91,   59,  123,   16,   33,  273,  274,  275,  276,
  277,  278,   40,  125,   37,   -1,   37,   45,  125,   42,
   43,   42,   45,   46,   47,   46,   47,   -1,   -1,   -1,
  273,  274,  275,  276,  277,  278,   59,   60,   41,   62,
   41,   44,   37,   44,   -1,   -1,   -1,   42,   43,   -1,
   45,   46,   47,   -1,   -1,   -1,   59,   -1,   59,  273,
  274,  275,  276,  277,  278,   60,   -1,   62,  273,  274,
  275,  276,  277,  278,   -1,   41,   -1,   37,   44,   -1,
   -1,   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   93,   -1,   93,   59,  273,  274,  275,  276,  277,  278,
   60,   -1,   62,  273,  274,  275,  276,  277,  278,  265,
  266,  267,  268,   37,  270,   -1,   -1,   -1,   42,   43,
   -1,   45,   46,   47,  257,  258,  259,   93,   -1,  273,
  274,  275,  276,  277,  278,   59,   60,   -1,   62,  272,
  260,  261,  262,  263,  264,  265,  266,  267,  268,  269,
  270,   41,  272,   43,   44,   45,  273,  274,  275,  276,
  277,  278,   61,  273,  274,  275,  276,  277,  278,   59,
   60,   -1,   62,  265,  266,  267,  268,   -1,  270,   -1,
   41,   -1,   43,   44,   45,  273,  274,  275,  276,  277,
  278,  125,  273,  274,  275,  276,  277,  278,   59,   60,
   -1,   62,   -1,   93,   41,   -1,   -1,   44,   -1,   -1,
   -1,   49,   -1,   -1,   52,   41,   -1,   -1,   44,   -1,
   -1,   -1,   59,   60,   -1,   62,  125,   -1,   -1,  257,
  258,  259,   93,   59,   60,   -1,   62,  265,  266,  267,
   41,   -1,  270,   44,  272,   -1,   -1,   85,   -1,   -1,
  273,  274,  275,  276,  277,  278,   93,  123,   59,   60,
   -1,   62,   -1,   -1,   -1,   -1,   -1,   93,   41,   -1,
   -1,   44,  273,  274,  112,   -1,   -1,   -1,  273,  274,
  275,  276,  277,  278,  125,   41,   59,   60,   44,   62,
   37,   41,   93,   -1,   44,   42,   43,   -1,   45,   46,
   47,  139,   -1,   59,   60,   -1,   62,  273,  274,   59,
   60,   -1,   62,   -1,   -1,  275,  276,  277,  278,  125,
   93,  159,  160,   -1,   -1,   -1,  260,  261,  262,  263,
  264,  265,  266,  267,  268,  269,  270,   93,  272,   -1,
   -1,   -1,   -1,   93,  125,   -1,   -1,   -1,   -1,  273,
  274,  275,  276,  277,  278,  265,  266,  267,  268,   -1,
  270,  260,  261,  262,  263,  264,  265,  266,  267,  268,
  269,  270,   -1,  272,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  273,  274,  275,  276,  277,  278,   -1,
   -1,   -1,   -1,   -1,  260,  261,  262,  263,  264,  265,
  266,  267,  268,  269,  270,  125,  272,   -1,   -1,   -1,
   -1,   -1,  273,  274,  275,  276,  277,  278,   -1,  260,
  261,  262,  263,  264,  265,  266,  267,  268,  269,  270,
   -1,  272,   -1,   -1,   -1,   -1,  273,  274,  275,  276,
  277,  278,   -1,   -1,   -1,   -1,   -1,  273,  274,  275,
  276,  277,  278,   -1,  260,  261,  262,  263,  264,  265,
  266,  267,  268,  269,  270,   -1,  272,   -1,   -1,   -1,
   -1,   -1,  273,  274,  275,  276,  277,  278,   -1,  260,
  261,  262,  263,  264,  265,  266,  267,  268,  269,  270,
   -1,  272,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  273,  274,  275,  276,  277,  278,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  273,  274,  275,
  276,  277,  278,  273,  274,  275,  276,  277,  278,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  260,  261,  262,  263,  264,  265,  266,  267,  268,  269,
  270,   40,  272,   40,   -1,   -1,   -1,   -1,   -1,   50,
   49,   -1,   49,   52,   -1,   52,   -1,   -1,   57,   -1,
   57,   -1,   63,   64,   65,   -1,   -1,   -1,   -1,   -1,
   71,   72,   -1,   -1,   73,   -1,   73,   -1,   -1,   -1,
   81,   -1,   -1,   84,   -1,   -1,   85,   -1,   85,   -1,
   -1,   -1,   -1,   94,   95,   96,   97,   98,   99,  100,
  101,  102,  103,  104,  105,  106,  107,  108,   -1,   -1,
   -1,   -1,   -1,  112,   -1,  112,   -1,   -1,   -1,   -1,
  121,  260,  261,  262,  263,  264,  265,  266,  267,  268,
  269,  270,   -1,  272,   -1,   -1,   -1,   -1,   -1,   -1,
  139,  142,  139,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  149,  150,  149,  150,   -1,   -1,   -1,   -1,   -1,   -1,
  159,  160,  159,  160,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=280;
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
"DISTINTO","IGUALDAD","MENOS_UNARIO","NEGACION",
};
final static String yyrule[] = {
"$accept : programa",
"programa : metodos VOID MAIN '(' ')' '{' sentencias '}'",
"metodos : metodos metodo",
"metodos :",
"metodo : tipoSimple ID '(' parametro ')' '{' cuerpo '}'",
"metodo : declaracionVariable ';'",
"llamadaFuncion : ID '(' expresiones ')'",
"tipoParametro : ID",
"tipoParametro : CTE_ENTERA",
"tipoParametro : CTE_REAL",
"parametro : parametro ',' tipoSimple ID",
"parametro : tipoSimple ID",
"parametro :",
"cuerpo : sentencias",
"cuerpo :",
"sentencias : sentencias sentencia",
"sentencias : sentencia",
"sentencia : llamadaVariable",
"sentencia : declaracionVariable ';'",
"sentencia : llamadaVariable '=' expresion ';'",
"sentencia : declaracionVariable '=' expresion ';'",
"sentencia : ID '.' ID '=' expresion ';'",
"sentencia : WHILE '(' expresion ')' '{' sentencias '}'",
"sentencia : IF '(' expresion ')' '{' sentencias '}'",
"sentencia : IF '(' expresion ')' sentencia",
"sentencia : ELSE '{' sentencias '}'",
"sentencia : ELSE sentencia",
"sentencia : WRITE expresiones ';'",
"sentencia : READ expresiones ';'",
"sentencia : ID '(' parametroLlamada ')' ';'",
"sentencia : llamadaFuncion ';'",
"sentencia : RETURN expresion ';'",
"parametroLlamada : parametroLlamada ',' tipoParametro",
"parametroLlamada :",
"llamadaVariable : ID",
"llamadaVariable : ID '[' expresiones ']'",
"llamadaVariable : ID '[' expresiones ']' '[' expresiones ']'",
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
"indices : indices '[' CTE_ENTERA ']'",
"indices : '[' CTE_ENTERA ']'",
"identificador : identificador ',' ID",
"identificador : ID",
"tipoSimple : VOID",
"tipoSimple : INT",
"tipoSimple : DOUBLE",
"tipoSimple : CHAR",
};

//#line 142 "../../src/sintactico/sintactico.y"

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
//#line 538 "Parser.java"
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
