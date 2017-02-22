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
    0,    1,    1,    3,    3,    4,    9,   11,   11,   11,
    7,    7,    7,    8,    8,   12,   12,    2,    2,   14,
   14,   14,   14,   14,   14,   14,   14,   14,   14,   14,
   14,   14,   14,   16,   16,   17,   18,   18,   18,   18,
   15,   15,   15,    5,    5,    5,    5,   10,   10,   13,
   13,   13,   13,   13,   13,   13,   13,   13,   13,   13,
   13,   13,   13,   13,   13,   13,   13,   13,   13,   13,
   13,   13,   19,   19,    6,    6,    6,    6,
};
final static short yylen[] = {                            2,
    8,    2,    0,    1,    2,    8,    4,    1,    1,    1,
    4,    2,    0,    2,    0,    3,    0,    2,    1,    1,
    2,    4,    4,    6,    7,    7,    5,    4,    2,    3,
    3,    5,    2,    3,    0,    5,    2,    3,    2,    1,
    1,    4,    7,    2,    5,    8,    2,    3,    1,    1,
    1,    1,    1,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    2,    2,    3,
    4,    1,    3,    1,    1,    1,    1,    1,
};
final static short yydefred[] = {                         3,
    0,    0,   76,   77,   78,    0,    0,    2,    4,    0,
    0,    0,    0,    0,    5,    0,    0,    0,   47,   75,
    0,    0,    0,    0,    0,    0,   51,   52,   53,    0,
    0,    0,    0,   72,    0,    0,   50,    0,    0,   39,
   74,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   73,
   36,   38,    0,   12,    0,    0,    0,    0,   70,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   67,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   19,    0,    0,    0,
    7,   71,    0,    0,    0,    0,    0,    0,    0,   29,
    0,    0,    1,   18,    0,   21,   33,    0,    0,    0,
   11,    0,    0,   31,   30,    0,    0,    0,    0,    0,
    0,    0,    0,   14,    6,    0,    0,    0,    0,   28,
    0,    0,    0,   23,   22,    0,   43,    0,    0,   27,
    0,   32,    9,   10,    8,   34,   16,    0,    0,   24,
   25,   26,
};
final static short yydgoto[] = {                          1,
    2,  104,    8,    9,  105,   22,   47,  130,   34,   77,
  166,  144,   36,  107,   37,  140,   12,   24,   38,
};
final static short yysindex[] = {                         0,
    0,   41,    0,    0,    0, -122, -257,    0,    0,  -44,
  -89,  -40,  324,    5,    0,   37,  -12,   39,    0,    0,
   25,  -71,  -40,    4,   58,   47,    0,    0,    0,   66,
  -12,  -12,  235,    0,  -41,  299,    0,    2, -135,    0,
    0, -117,  104,  -40,   45, -100,   -3,  -12,  142,  142,
  164,  124,  -54,  -12,  -12,  -12,  -12,  -12,  -12,  -12,
  -12,  -12,  -12,  -12,  -12,  -12,  -12,  -12,  -12,    0,
    0,    0,  519,    0,   83,   47,    3,  -12,    0,  -12,
   39,  299,  334,  334,  369,  369,  369,  369,  369,  369,
  143,  143,  142,  142,  142,    0,  -28,  -12,  -12,  169,
  175,  260,  -29,  473,  117,  162,    0,  161,  519,  -38,
    0,    0,  -26,  151,   -4,   59,  -12,  -12,  519,    0,
  -24,  -12,    0,    0,  -12,    0,    0,  -12,  393,  118,
    0,   13,  -12,    0,    0,  150,  157,  493,  183,   64,
  236,  273,  -12,    0,    0,   39,   35,  153,  380,    0,
  -12,  218, -245,    0,    0,  399,    0,  519,  519,    0,
  406,    0,    0,    0,    0,    0,    0,  560,  573,    0,
    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,  -52,    0,    0,    0,
    0,    0,    0,    0,    0,   91,    0,  120,    0,    0,
    0,    0,   82,    0,    0,   97,    0,    0,    0,   16,
    0,    0,    0,    0,    0,   60,    0,  -11,    0,    0,
    0,    0,    0,  338,    0,    0,    0,    0,   27,   53,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  152,  280,  328,  363,  413,  474,  494,  500,  520,  537,
  426,  436,   80,   89,  115,    0,    0,    0,    0,    0,
    0,    0,  359,    0,    0,    0,    0,  458,  159,    0,
    0,    0,    0,  -37,    0,    0,    0,    0,    0,    0,
    0,  103,    0,    0,    0,    0,    0,    0,  163,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  155,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,
};
final static short yygindex[] = {                         0,
    0,  251,    0,    0,   78,   40,    0,    0,  786,   12,
    0,    0,  815,  760,  792,    0,  130,    0,   32,
};
final static int YYTABLESIZE=966;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         42,
   13,   17,   54,   42,   42,   42,   42,   42,   42,   42,
  122,  163,  164,   14,   15,   54,  121,   54,   19,   17,
   31,   42,   42,   42,   42,   41,  165,   33,   35,   41,
   41,   41,   32,   41,   41,   41,   80,   75,   75,   54,
   76,   11,   18,  111,   25,   39,   54,   41,   41,   41,
   41,   53,   74,   18,  134,   42,   74,   74,   74,   74,
   74,   74,   74,   69,  114,   46,  132,   69,   69,   69,
   69,   69,   51,   69,   74,   74,   26,   74,   54,   10,
   97,   41,   39,   40,   81,   69,   69,   42,   69,   68,
   21,  113,   69,   68,   68,   68,   68,   68,   45,   68,
   49,   43,   54,   49,  152,   48,   74,  153,   74,  115,
  116,   68,   68,   41,   68,  110,   55,  135,   49,   69,
   55,   55,   55,   55,   55,   56,   55,  157,   42,   56,
   56,   56,   56,   56,   74,   56,   70,   13,   55,   55,
   13,   55,   23,   35,  147,   68,   35,   56,   56,   74,
   56,   58,   49,   44,   71,   58,   58,   58,   58,   58,
   67,   58,   72,  146,   79,   65,   63,   73,   64,   68,
   66,   74,   55,   58,   58,  126,   58,  125,   44,   67,
   44,   56,   16,   62,   65,   61,   67,   68,   68,   66,
  148,   65,   63,   67,   64,   68,   66,  149,   65,   63,
   41,   64,   68,   66,   78,  109,   40,   58,  117,   62,
   45,   61,   45,   46,  118,   46,   62,   41,   61,   75,
  127,  128,   42,   42,   42,   42,   42,   42,   42,   42,
   42,   42,   42,  131,   42,   42,   42,   42,   42,   42,
   42,  133,  145,  151,   27,   28,   29,  139,   41,   41,
   41,   41,   41,   41,   41,   41,   41,   41,   41,   30,
   41,   41,   41,   41,   41,   41,   41,   31,    3,    4,
    5,    6,   67,   20,   33,  158,  162,   65,   63,   32,
   64,   68,   66,   15,   41,    0,    0,   17,   74,   74,
   74,   74,   74,   74,  154,   62,    0,   61,    0,   69,
   69,   69,   69,   69,   69,    3,    4,    5,    6,   67,
    7,    3,    4,    5,   65,   63,   20,   64,   68,   66,
   48,    0,    0,   48,    0,   68,   68,   68,   68,   68,
   68,  155,   62,    0,   61,   67,    0,    0,   48,    0,
   65,   63,    0,   64,   68,   66,   40,   40,   40,   40,
    0,   40,   55,   55,   55,   55,   55,   55,   62,  129,
   61,   56,   56,   56,   56,   56,   56,    0,   65,  138,
   67,   65,   48,    0,    0,   65,   63,    0,   64,   68,
   66,    0,  119,    0,    0,    0,   65,   58,   58,   58,
   58,   58,   58,   62,    0,   61,   55,   56,   57,   58,
   59,   60,   74,   66,    0,   67,   66,    0,  168,  169,
   65,   63,    0,   64,   68,   66,    0,    0,    0,   74,
   65,   66,   55,   56,   57,   58,   59,   60,    0,   55,
   56,   57,   58,   59,   60,   67,    0,    0,    0,    0,
   65,   63,   67,   64,   68,   66,    0,   65,   63,   74,
   64,   68,   66,   61,    0,   66,   61,  167,   62,    0,
   61,    0,   37,    0,  170,   62,   54,   61,   54,   54,
   54,   61,   61,    0,   61,    0,   57,    0,   57,   57,
   57,    0,    0,   74,   54,   54,    0,   54,    0,    0,
    0,   27,   28,   29,   57,   57,    0,   57,    0,    3,
    4,    5,  159,    0,   20,   61,   30,    0,   55,   56,
   57,   58,   59,   60,   62,    0,    0,   62,   54,   98,
   99,  100,  101,  102,    3,    4,    5,    6,   57,   20,
    0,  103,   62,   62,   63,   62,    0,   63,    0,    0,
   64,    0,    0,   64,    0,   55,   56,   57,   58,   59,
   60,    0,   63,   63,    0,   63,    0,    0,   64,   64,
   59,   64,    0,   59,    0,    0,   62,    0,    0,    0,
    0,   55,   56,   57,   58,   59,   60,   60,   59,   59,
   60,   59,   20,    0,    0,    0,   63,    0,    3,    4,
    5,    6,   64,   20,    0,   60,   60,  123,   60,    0,
   65,   65,   37,   37,   37,   37,    0,   37,   57,   58,
   59,   60,   59,    0,    0,    0,    0,  150,   74,   74,
   74,   74,   74,   74,   74,   74,   74,   74,   74,   60,
   74,    0,    0,    0,    0,   66,   66,    0,    0,   98,
   99,  100,  101,  102,    3,    4,    5,    6,    0,   20,
    0,  103,   98,   99,  100,  101,  102,    3,    4,    5,
    6,  143,   20,    0,  103,    0,    0,    0,    0,    0,
    0,   55,   56,   57,   58,   59,   60,    0,   55,   56,
   57,   58,   59,   60,  171,   61,   61,   61,   61,   61,
   61,    0,    0,    0,    0,    0,    0,  172,   54,   54,
   54,   54,   54,   54,    0,    0,    0,    0,   57,   57,
   57,   57,   57,   57,    0,    0,    0,   20,   20,   20,
   20,   20,   20,   20,   20,   20,   20,   20,    0,   20,
    0,    0,   98,   99,  100,  101,  102,    3,    4,    5,
    6,    0,   20,    0,  103,    0,   62,   62,   62,   62,
   62,   62,   98,   99,  100,  101,  102,    3,    4,    5,
    6,    0,   20,    0,  103,    0,   63,   63,   63,   63,
   63,   63,   64,   64,   64,   64,   64,   64,   98,   99,
  100,  101,  102,    3,    4,    5,    6,    0,   20,    0,
  103,    0,   59,   59,   59,   59,   59,   59,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   60,
   60,   60,   60,   60,   60,    0,    0,    0,    0,   98,
   99,  100,  101,  102,    3,    4,    5,    6,    0,   20,
    0,  103,   98,   99,  100,  101,  102,    3,    4,    5,
    6,    0,   20,    0,  103,   49,   50,   52,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  106,    0,
    0,  120,    0,  124,  108,    0,    0,    0,   82,   83,
   84,   85,   86,   87,   88,   89,   90,   91,   92,   93,
   94,   95,   96,    0,    0,    0,    0,  106,  124,  106,
    0,    0,  112,  108,  106,  108,    0,  124,    0,    0,
  108,    0,    0,    0,  106,    0,    0,    0,  160,    0,
  108,    0,    0,    0,  106,    0,    0,    0,    0,    0,
  108,    0,    0,  106,    0,    0,    0,  124,  124,  108,
    0,  136,  137,    0,  106,    0,    0,    0,    0,  141,
  108,    0,  142,  106,  106,    0,    0,    0,    0,  108,
  108,    0,    0,  106,  106,    0,    0,  156,    0,  108,
  108,    0,    0,    0,    0,  161,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
  123,   91,   44,   41,   42,   43,   44,   45,   46,   47,
   40,  257,  258,  271,   59,   44,   46,   44,   59,   91,
   33,   59,   60,   61,   62,   37,  272,   40,   17,   41,
   42,   43,   45,   45,   46,   47,   91,   41,   91,   44,
   44,    2,   11,   41,   40,   44,   44,   59,   60,   61,
   62,   93,   37,   22,   59,   93,   41,   42,   43,   44,
   45,   46,   47,   37,   93,   26,   93,   41,   42,   43,
   44,   45,   33,   47,   59,   60,   40,   62,   44,    2,
   69,   93,   44,   59,   53,   59,   60,  125,   62,   37,
   13,   80,   91,   41,   42,   43,   44,   45,   41,   47,
   41,   24,   44,   44,   41,   40,   91,   44,   93,   98,
   99,   59,   60,  125,   62,   76,   37,   59,   59,   93,
   41,   42,   43,   44,   45,   37,   47,   93,  125,   41,
   42,   43,   44,   45,   44,   47,  272,   41,   59,   60,
   44,   62,   13,   41,  133,   93,   44,   59,   60,   59,
   62,   37,   93,   24,  272,   41,   42,   43,   44,   45,
   37,   47,   59,  132,   41,   42,   43,  123,   45,   46,
   47,  272,   93,   59,   60,   59,   62,   61,   59,   37,
   61,   93,  272,   60,   42,   62,   37,   46,   46,   47,
   41,   42,   43,   37,   45,   46,   47,   41,   42,   43,
  272,   45,   46,   47,   41,  123,  125,   93,   40,   60,
   59,   62,   61,   59,   40,   61,   60,  272,   62,  272,
   59,   61,  260,  261,  262,  263,  264,  265,  266,  267,
  268,  269,  270,  272,  272,  273,  274,  275,  276,  277,
  278,   91,  125,   61,  257,  258,  259,  272,  260,  261,
  262,  263,  264,  265,  266,  267,  268,  269,  270,  272,
  272,  273,  274,  275,  276,  277,  278,   33,  265,  266,
  267,  268,   37,  270,   40,  123,   59,   42,   43,   45,
   45,   46,   47,  125,  272,   -1,   -1,  125,  273,  274,
  275,  276,  277,  278,   59,   60,   -1,   62,   -1,  273,
  274,  275,  276,  277,  278,  265,  266,  267,  268,   37,
  270,  265,  266,  267,   42,   43,  270,   45,   46,   47,
   41,   -1,   -1,   44,   -1,  273,  274,  275,  276,  277,
  278,   59,   60,   -1,   62,   37,   -1,   -1,   59,   -1,
   42,   43,   -1,   45,   46,   47,  265,  266,  267,  268,
   -1,  270,  273,  274,  275,  276,  277,  278,   60,  109,
   62,  273,  274,  275,  276,  277,  278,   -1,   41,  119,
   37,   44,   93,   -1,   -1,   42,   43,   -1,   45,   46,
   47,   -1,  123,   -1,   -1,   -1,   59,  273,  274,  275,
  276,  277,  278,   60,   -1,   62,  273,  274,  275,  276,
  277,  278,   44,   41,   -1,   37,   44,   -1,  158,  159,
   42,   43,   -1,   45,   46,   47,   -1,   -1,   -1,   61,
   93,   59,  273,  274,  275,  276,  277,  278,   -1,  273,
  274,  275,  276,  277,  278,   37,   -1,   -1,   -1,   -1,
   42,   43,   37,   45,   46,   47,   -1,   42,   43,   91,
   45,   46,   47,   41,   -1,   93,   44,   59,   60,   -1,
   62,   -1,  125,   -1,   59,   60,   41,   62,   43,   44,
   45,   59,   60,   -1,   62,   -1,   41,   -1,   43,   44,
   45,   -1,   -1,  125,   59,   60,   -1,   62,   -1,   -1,
   -1,  257,  258,  259,   59,   60,   -1,   62,   -1,  265,
  266,  267,  123,   -1,  270,   93,  272,   -1,  273,  274,
  275,  276,  277,  278,   41,   -1,   -1,   44,   93,  260,
  261,  262,  263,  264,  265,  266,  267,  268,   93,  270,
   -1,  272,   59,   60,   41,   62,   -1,   44,   -1,   -1,
   41,   -1,   -1,   44,   -1,  273,  274,  275,  276,  277,
  278,   -1,   59,   60,   -1,   62,   -1,   -1,   59,   60,
   41,   62,   -1,   44,   -1,   -1,   93,   -1,   -1,   -1,
   -1,  273,  274,  275,  276,  277,  278,   41,   59,   60,
   44,   62,  125,   -1,   -1,   -1,   93,   -1,  265,  266,
  267,  268,   93,  270,   -1,   59,   60,  125,   62,   -1,
  273,  274,  265,  266,  267,  268,   -1,  270,  275,  276,
  277,  278,   93,   -1,   -1,   -1,   -1,  125,  260,  261,
  262,  263,  264,  265,  266,  267,  268,  269,  270,   93,
  272,   -1,   -1,   -1,   -1,  273,  274,   -1,   -1,  260,
  261,  262,  263,  264,  265,  266,  267,  268,   -1,  270,
   -1,  272,  260,  261,  262,  263,  264,  265,  266,  267,
  268,  269,  270,   -1,  272,   -1,   -1,   -1,   -1,   -1,
   -1,  273,  274,  275,  276,  277,  278,   -1,  273,  274,
  275,  276,  277,  278,  125,  273,  274,  275,  276,  277,
  278,   -1,   -1,   -1,   -1,   -1,   -1,  125,  273,  274,
  275,  276,  277,  278,   -1,   -1,   -1,   -1,  273,  274,
  275,  276,  277,  278,   -1,   -1,   -1,  260,  261,  262,
  263,  264,  265,  266,  267,  268,  269,  270,   -1,  272,
   -1,   -1,  260,  261,  262,  263,  264,  265,  266,  267,
  268,   -1,  270,   -1,  272,   -1,  273,  274,  275,  276,
  277,  278,  260,  261,  262,  263,  264,  265,  266,  267,
  268,   -1,  270,   -1,  272,   -1,  273,  274,  275,  276,
  277,  278,  273,  274,  275,  276,  277,  278,  260,  261,
  262,  263,  264,  265,  266,  267,  268,   -1,  270,   -1,
  272,   -1,  273,  274,  275,  276,  277,  278,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  273,
  274,  275,  276,  277,  278,   -1,   -1,   -1,   -1,  260,
  261,  262,  263,  264,  265,  266,  267,  268,   -1,  270,
   -1,  272,  260,  261,  262,  263,  264,  265,  266,  267,
  268,   -1,  270,   -1,  272,   31,   32,   33,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   73,   -1,
   -1,  102,   -1,  104,   73,   -1,   -1,   -1,   54,   55,
   56,   57,   58,   59,   60,   61,   62,   63,   64,   65,
   66,   67,   68,   -1,   -1,   -1,   -1,  102,  129,  104,
   -1,   -1,   78,  102,  109,  104,   -1,  138,   -1,   -1,
  109,   -1,   -1,   -1,  119,   -1,   -1,   -1,  149,   -1,
  119,   -1,   -1,   -1,  129,   -1,   -1,   -1,   -1,   -1,
  129,   -1,   -1,  138,   -1,   -1,   -1,  168,  169,  138,
   -1,  117,  118,   -1,  149,   -1,   -1,   -1,   -1,  125,
  149,   -1,  128,  158,  159,   -1,   -1,   -1,   -1,  158,
  159,   -1,   -1,  168,  169,   -1,   -1,  143,   -1,  168,
  169,   -1,   -1,   -1,   -1,  151,
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
"metodo : funcion",
"metodo : declaracionVariable ';'",
"funcion : tipoSimple ID '(' parametro ')' '{' cuerpo '}'",
"llamadaFuncion : ID '(' expresiones ')'",
"tipoParametro : ID",
"tipoParametro : CTE_ENTERA",
"tipoParametro : CTE_REAL",
"parametro : parametro ',' tipoSimple ID",
"parametro : tipoSimple ID",
"parametro :",
"cuerpo : sentencias retorno",
"cuerpo :",
"retorno : RETURN expresion ';'",
"retorno :",
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
"parametroLlamada : parametroLlamada ',' tipoParametro",
"parametroLlamada :",
"estructura : STRUCT '{' campos '}' ID",
"campos : campos estructura",
"campos : campos declaracionVariable ';'",
"campos : declaracionVariable ';'",
"campos : estructura",
"llamadaVariable : identificador",
"llamadaVariable : identificador '[' expresiones ']'",
"llamadaVariable : identificador '[' expresiones ']' '[' expresiones ']'",
"declaracionVariable : tipoSimple identificador",
"declaracionVariable : tipoSimple '[' expresiones ']' identificador",
"declaracionVariable : tipoSimple '[' expresiones ']' '[' expresiones ']' identificador",
"declaracionVariable : estructura ';'",
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
"identificador : identificador ',' ID",
"identificador : ID",
"tipoSimple : VOID",
"tipoSimple : INT",
"tipoSimple : DOUBLE",
"tipoSimple : CHAR",
};

//#line 150 "../../src/sintactico/sintactico.y"

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
//#line 555 "Parser.java"
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
