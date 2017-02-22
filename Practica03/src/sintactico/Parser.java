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
    0,    0,    1,    3,    3,    4,    4,    7,    7,    2,
    2,    6,    6,   10,   10,   10,   10,   10,   10,   10,
   12,   12,   14,   14,    5,    5,   11,   11,   11,   11,
   11,   11,   13,   13,    8,    8,    8,    8,    8,    8,
    8,    8,    8,    8,    8,    8,    8,    8,    8,    8,
    8,    8,    8,    8,    8,   15,   15,    9,    9,    9,
};
final static short yylen[] = {                            2,
    2,    0,    8,    3,    0,    3,    0,    3,    0,    1,
    1,    2,    1,    4,    2,    7,    7,   11,    3,    3,
    5,    1,    2,    2,    2,    0,    1,    4,    7,    2,
    5,    8,    3,    1,    1,    1,    1,    1,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    2,    3,    4,    3,    1,    1,    1,    1,
};
final static short yydefred[] = {                         2,
    0,   58,   59,   60,   10,    1,    0,   11,    0,    5,
    0,    0,    0,    0,   57,    4,    0,   25,    0,    0,
    0,    0,    0,    0,    0,    3,    0,    0,    0,    0,
    0,    0,   13,    0,    0,    0,    0,   56,   34,    0,
    0,    0,    0,    0,    0,    6,   12,    0,    0,   15,
    0,    0,    0,   20,   19,   36,   37,   38,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   33,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   24,    0,   22,   23,    8,   14,    0,    0,    0,
   54,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   52,    0,    0,   21,    0,   29,
   55,    0,    0,    0,   16,    0,    0,    0,    0,   18,
};
final static short yydgoto[] = {                          1,
    6,    7,   11,   20,   13,   32,   46,   62,   17,   33,
   48,   35,   40,   66,   63,
};
final static short yysindex[] = {                         0,
  128,    0,    0,    0,    0,    0, -258,    0,  -16,    0,
  -15,  -96,   -6,    0,    0,    0,  -90,    0,  -32,  -95,
  437, -228,  -12, -208, -219,    0, -205, -205,   17,   21,
  -61,  313,    0,   16,    7,   -3,    3,    0,    0,  -41,
  -31,  213,  213, -183,  213,    0,    0,   16,  213,    0,
  -89,  -10, -172,    0,    0,    0,    0,    0,   51,  213,
  213,   -2,  -12,    5,   38,  383,   26,   33, -142,  -12,
 -137,    0,  213,   77,   57,  213,  213,  213,  213,  213,
  213,  213,  213,  213,  213,  213,  213,  213,  213,    2,
    4,    0, -143,    0,    0,    0,    0,   41,   48,   64,
    0,  133,  133,  276,  276,  276,  276,  276,  276,  345,
  345,   77,   77,   77,    0,  437,  437,    0, -126,    0,
    0,  300,  341,  -12,    0, -116,   28,  437,  357,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   20,    0,    0,  374,    0,    0,    0,    0,  235,    0,
    0,    0,  256,    0,    0,    0,    0,    0,    0,    0,
    0,   31,    0,  270,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   94,    0,    0,
    0,   91,    0,    0,    0,    0,    0,    0,  -37,    0,
    0,    0,  -26,    0,    0,    0,    0,    0,    0,  327,
    0,    0,    0,   71,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   -4,   15,  140,  160,  166,  173,  183,  203,  146,
  153,   95,  102,  126,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  404,    0,  287,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,    0,    0,  144,   49,    0,  635,  158,   -1,
  190,  106,  132,    0,  641,
};
final static int YYTABLESIZE=770;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         57,
   22,   69,   53,   57,   57,   57,   57,   57,   57,   57,
   35,   25,   53,    9,   35,   35,   35,   54,   35,   35,
   35,   57,   57,   10,   57,   12,   14,   55,   36,   26,
   47,   25,   35,   35,   88,   35,   50,   16,   90,   86,
   84,   88,   85,   89,   87,   91,   86,   84,   37,   85,
   89,   87,   38,   39,   50,   51,   42,   83,   24,   82,
   43,   44,   88,   26,   83,   50,   82,   86,   84,   88,
   85,   89,   87,   51,   86,   84,   49,   85,   89,   87,
   71,    2,    3,    4,   96,   83,   72,   82,   15,   51,
   73,   97,   83,   88,   82,   52,   92,  101,   86,   84,
   88,   85,   89,   87,  121,   86,   84,   53,   85,   89,
   87,   53,   53,   53,   98,   53,   83,   53,   82,   99,
   47,   47,   89,   83,  116,   82,  117,   47,  118,   53,
   53,   40,   53,  119,   28,   40,   40,   40,   41,   40,
  120,   40,   41,   41,   41,   15,   41,  127,   41,   28,
  128,   28,   22,   40,   40,    9,   40,   21,    8,   41,
   41,   41,   43,   41,  122,  123,   43,   43,   43,   88,
   43,   95,   43,    0,   86,   84,  129,   85,   89,   87,
   46,   15,   15,    0,   43,   43,   39,   43,   39,    0,
   39,    0,   83,   42,   82,   42,    0,   42,   46,   46,
   47,   46,   18,    0,   39,   39,   48,   39,    0,    0,
   34,   42,   42,   49,   42,   28,    0,    0,   47,   47,
    0,   47,    0,   44,   48,   48,    0,   48,    0,    0,
    0,   49,   49,   65,   49,   57,   57,   57,   57,   57,
   57,   44,   44,   45,   44,    0,   35,   35,   35,   35,
   35,   35,   61,    0,    0,   94,    0,   60,    2,    3,
    4,   45,   45,    0,   45,   15,    0,    0,   50,   50,
   76,   77,   78,   79,   80,   81,    0,   76,   77,   78,
   79,   80,   81,    0,   26,   26,   26,   51,   51,    0,
    0,   26,    0,   27,    0,   27,    0,    0,   76,   77,
   78,   79,   80,   81,    0,   76,   77,   78,   79,   80,
   81,    0,   88,    0,   30,    0,   30,   86,   84,    0,
   85,   89,   87,    0,    0,    0,    0,    0,   22,   76,
   77,   78,   79,   80,   81,    0,   76,   77,   78,   79,
   80,   81,    0,   53,   53,   53,   53,   53,   53,    0,
   28,   28,   28,   28,    0,   28,   28,   28,   28,   27,
    0,    0,   28,    0,    0,    0,    0,   40,   40,   40,
   40,   40,   40,    0,   41,   41,   41,   41,   41,   41,
   30,   88,    0,    0,    0,   31,   86,   31,    0,    0,
   89,   87,    2,    3,    4,    0,    0,    5,   43,   43,
   43,   43,   43,   43,    0,    0,    0,   78,   79,   80,
   81,   17,   46,   46,   46,   46,   46,   46,   39,   39,
   39,   39,   39,   39,  125,   42,   42,   42,   42,   42,
   42,    0,   47,   47,   47,   47,   47,   47,   48,   48,
   48,   48,   48,   48,    0,   49,   49,   49,   49,   49,
   49,   31,    0,    0,    0,   44,   44,   44,   44,   44,
   44,    0,   32,    0,   32,  126,    0,    0,    0,   56,
   57,   58,    0,    0,    0,   45,   45,   45,   45,   45,
   45,  130,    0,    0,   59,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   27,   27,   27,   27,    7,   27,
   27,   27,   27,    0,    0,    0,   27,   93,    0,    0,
    0,    0,    0,    0,    0,   30,   30,   30,   30,    0,
   30,   30,   30,   30,    0,    0,    0,   30,   32,   25,
   25,   25,   25,    0,   25,   25,   25,   25,    0,    0,
    0,   25,    0,    0,    0,    0,   17,   17,   17,   17,
    0,   17,   17,   17,   17,   17,    0,    0,   17,   27,
   28,   29,   30,    0,    2,    3,    4,   31,    0,    0,
    0,   15,   27,   28,   29,   30,    0,    2,    3,    4,
   31,   45,    0,    0,   15,    0,   31,   31,   31,   31,
    0,   31,   31,   31,   31,    0,    0,    0,   31,    0,
   27,   28,   29,   30,    0,    2,    3,    4,   31,    0,
    0,    0,   15,    0,    0,    0,   27,   28,   29,   30,
    0,    2,    3,    4,   31,    0,    0,    0,   15,    0,
    0,    0,    0,   26,   26,   26,   26,    0,   26,   26,
   26,   26,    0,    0,    0,   26,    0,    2,    3,    4,
   31,    0,    0,   19,   15,    0,    0,   23,    0,    0,
    0,   19,    0,   32,   32,   32,   32,    0,   32,   32,
   32,   32,   19,    0,    0,   32,    0,   64,    0,   67,
    0,    0,    0,   68,   19,    0,    0,    0,    0,    0,
    0,   70,    0,    0,   74,   75,   27,   28,   29,   30,
    0,    2,    3,    4,   31,    0,   19,  100,   15,    0,
  102,  103,  104,  105,  106,  107,  108,  109,  110,  111,
  112,  113,  114,  115,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   19,   19,    0,  124,
    0,    0,   19,   19,    0,    0,    0,    0,   19,   19,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
   91,   91,   44,   41,   42,   43,   44,   45,   46,   47,
   37,   44,   44,  272,   41,   42,   43,   59,   45,   46,
   47,   59,   60,   40,   62,   41,  123,   59,  257,  125,
   32,   44,   59,   60,   37,   62,   41,   44,   41,   42,
   43,   37,   45,   46,   47,   41,   42,   43,  257,   45,
   46,   47,  272,  259,   59,   41,   40,   60,   91,   62,
   40,  123,   37,   44,   60,   59,   62,   42,   43,   37,
   45,   46,   47,   59,   42,   43,   61,   45,   46,   47,
   91,  265,  266,  267,   59,   60,  259,   62,  272,   93,
   40,   59,   60,   37,   62,   93,   59,   41,   42,   43,
   37,   45,   46,   47,   41,   42,   43,   37,   45,   46,
   47,   41,   42,   43,  257,   45,   60,   47,   62,  257,
  122,  123,   46,   60,  123,   62,  123,  129,  272,   59,
   60,   37,   62,   93,   44,   41,   42,   43,   37,   45,
   93,   47,   41,   42,   43,  272,   45,  264,   47,   59,
  123,   61,   59,   59,   60,  125,   62,   14,    1,   28,
   59,   60,   37,   62,  116,  117,   41,   42,   43,   37,
   45,   66,   47,   -1,   42,   43,  128,   45,   46,   47,
   41,  272,  272,   -1,   59,   60,   41,   62,   43,   -1,
   45,   -1,   60,   41,   62,   43,   -1,   45,   59,   60,
   41,   62,   13,   -1,   59,   60,   41,   62,   -1,   -1,
   21,   59,   60,   41,   62,  125,   -1,   -1,   59,   60,
   -1,   62,   -1,   41,   59,   60,   -1,   62,   -1,   -1,
   -1,   59,   60,   44,   62,  273,  274,  275,  276,  277,
  278,   59,   60,   41,   62,   -1,  273,  274,  275,  276,
  277,  278,   40,   -1,   -1,   66,   -1,   45,  265,  266,
  267,   59,   60,   -1,   62,  272,   -1,   -1,  273,  274,
  273,  274,  275,  276,  277,  278,   -1,  273,  274,  275,
  276,  277,  278,   -1,  265,  266,  267,  273,  274,   -1,
   -1,  272,   -1,   59,   -1,   61,   -1,   -1,  273,  274,
  275,  276,  277,  278,   -1,  273,  274,  275,  276,  277,
  278,   -1,   37,   -1,   59,   -1,   61,   42,   43,   -1,
   45,   46,   47,   -1,   -1,   -1,   -1,   -1,   59,  273,
  274,  275,  276,  277,  278,   -1,  273,  274,  275,  276,
  277,  278,   -1,  273,  274,  275,  276,  277,  278,   -1,
  260,  261,  262,  263,   -1,  265,  266,  267,  268,  125,
   -1,   -1,  272,   -1,   -1,   -1,   -1,  273,  274,  275,
  276,  277,  278,   -1,  273,  274,  275,  276,  277,  278,
  125,   37,   -1,   -1,   -1,   59,   42,   61,   -1,   -1,
   46,   47,  265,  266,  267,   -1,   -1,  270,  273,  274,
  275,  276,  277,  278,   -1,   -1,   -1,  275,  276,  277,
  278,  125,  273,  274,  275,  276,  277,  278,  273,  274,
  275,  276,  277,  278,  125,  273,  274,  275,  276,  277,
  278,   -1,  273,  274,  275,  276,  277,  278,  273,  274,
  275,  276,  277,  278,   -1,  273,  274,  275,  276,  277,
  278,  125,   -1,   -1,   -1,  273,  274,  275,  276,  277,
  278,   -1,   59,   -1,   61,  125,   -1,   -1,   -1,  257,
  258,  259,   -1,   -1,   -1,  273,  274,  275,  276,  277,
  278,  125,   -1,   -1,  272,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  260,  261,  262,  263,  125,  265,
  266,  267,  268,   -1,   -1,   -1,  272,  125,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  260,  261,  262,  263,   -1,
  265,  266,  267,  268,   -1,   -1,   -1,  272,  125,  260,
  261,  262,  263,   -1,  265,  266,  267,  268,   -1,   -1,
   -1,  272,   -1,   -1,   -1,   -1,  260,  261,  262,  263,
   -1,  265,  266,  267,  268,  269,   -1,   -1,  272,  260,
  261,  262,  263,   -1,  265,  266,  267,  268,   -1,   -1,
   -1,  272,  260,  261,  262,  263,   -1,  265,  266,  267,
  268,  269,   -1,   -1,  272,   -1,  260,  261,  262,  263,
   -1,  265,  266,  267,  268,   -1,   -1,   -1,  272,   -1,
  260,  261,  262,  263,   -1,  265,  266,  267,  268,   -1,
   -1,   -1,  272,   -1,   -1,   -1,  260,  261,  262,  263,
   -1,  265,  266,  267,  268,   -1,   -1,   -1,  272,   -1,
   -1,   -1,   -1,  260,  261,  262,  263,   -1,  265,  266,
  267,  268,   -1,   -1,   -1,  272,   -1,  265,  266,  267,
  268,   -1,   -1,   13,  272,   -1,   -1,   17,   -1,   -1,
   -1,   21,   -1,  260,  261,  262,  263,   -1,  265,  266,
  267,  268,   32,   -1,   -1,  272,   -1,   43,   -1,   45,
   -1,   -1,   -1,   49,   44,   -1,   -1,   -1,   -1,   -1,
   -1,   51,   -1,   -1,   60,   61,  260,  261,  262,  263,
   -1,  265,  266,  267,  268,   -1,   66,   73,  272,   -1,
   76,   77,   78,   79,   80,   81,   82,   83,   84,   85,
   86,   87,   88,   89,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  116,  117,   -1,  119,
   -1,   -1,  122,  123,   -1,   -1,   -1,   -1,  128,  129,
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
"$accept : funciones",
"funciones : funciones funcion",
"funciones :",
"funcion : tipoFuncion ID '(' parametro ')' '{' cuerpo '}'",
"parametro : parametro variables ','",
"parametro :",
"cuerpo : variables sentencias retorno",
"cuerpo :",
"retorno : RETURN expresion ';'",
"retorno :",
"tipoFuncion : VOID",
"tipoFuncion : tipoSimple",
"sentencias : sentencias sentencia",
"sentencias : sentencia",
"sentencia : variable '=' expresion ';'",
"sentencia : estructura ';'",
"sentencia : WHILE '(' expresion ')' '{' sentencias '}'",
"sentencia : IF '(' expresion ')' '{' sentencias '}'",
"sentencia : IF '(' expresion ')' '{' sentencias '}' ELSE '{' sentencias '}'",
"sentencia : WRITE expresiones ';'",
"sentencia : READ expresiones ';'",
"estructura : STRUCT '{' campos '}' ID",
"estructura : variable",
"campos : campos estructura",
"campos : variable ';'",
"variables : variables variable",
"variables :",
"variable : identificador",
"variable : identificador '[' CTE_ENTERA ']'",
"variable : identificador '[' CTE_ENTERA ']' '[' CTE_ENTERA ']'",
"variable : tipoSimple identificador",
"variable : tipoSimple '[' CTE_ENTERA ']' identificador",
"variable : tipoSimple '[' CTE_ENTERA ']' '[' CTE_ENTERA ']' identificador",
"expresiones : expresiones ',' CTE_CARACTER",
"expresiones : CTE_CARACTER",
"expresion : identificador",
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
"expresion : ID '(' expresion ')'",
"identificador : identificador ',' ID",
"identificador : ID",
"tipoSimple : INT",
"tipoSimple : DOUBLE",
"tipoSimple : CHAR",
};

//#line 130 "../../src/sintactico/sintactico.y"

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
//#line 477 "Parser.java"
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
