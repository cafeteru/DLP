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
public final static short variableFuncion=280;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    2,    1,    1,    4,    8,    8,    5,    5,    3,
    3,   11,   11,   11,   11,   11,   11,   11,   13,   13,
   15,   15,    7,    7,   12,   12,   12,   12,   12,   12,
   14,   14,    9,    9,    9,    9,    9,    9,    9,    9,
    9,    9,    9,    9,    9,    9,    9,    9,    9,    9,
    9,    9,    9,    6,    6,   10,   10,   10,
};
final static short yylen[] = {                            2,
    2,    8,    2,    0,   10,    2,    0,    1,    1,    2,
    1,    4,    2,    7,    7,   11,    3,    3,    5,    1,
    2,    2,    2,    0,    1,    4,    7,    2,    5,    8,
    3,    1,    1,    1,    1,    1,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    2,    3,    4,    3,    1,    1,    1,    1,
};
final static short yydefred[] = {                         4,
    0,    0,   56,   57,   58,    0,    1,    3,    0,    8,
    0,    0,    0,    0,    0,   55,    0,    0,    0,    0,
    0,   24,   54,    0,    0,    0,    0,    0,    0,    0,
    0,   11,    0,    0,    0,   32,    0,    0,    0,    0,
    0,    2,   10,    0,    0,    0,    0,   13,    0,    0,
   18,    0,   17,   34,   35,   36,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   31,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   22,
    0,   20,   21,    0,    0,   12,    0,    5,    0,   52,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   50,    0,    0,   19,    0,    0,    0,
   53,    0,    0,    0,    0,   14,    0,   27,    0,    0,
    0,    0,    0,   16,
};
final static short yydgoto[] = {                          1,
    2,    7,   29,    8,    9,   60,   35,   69,   61,   31,
   32,   33,   34,   37,   64,
};
final static short yysindex[] = {                         0,
    0,  -48,    0,    0,    0, -270,    0,    0, -269,    0,
   -3,   11,  -10, -234,  -70,    0,  -17, -226,  -67, -213,
  511,    0,    0, -197, -197,   23,   44,  -41,  340,  -42,
  -77,    0,   25,   30,  511,    0,  -31,  -15,  213,  213,
 -145,    0,    0, -165, -163,   51,  213,    0,  495,   25,
    0, -157,    0,    0,    0,    0,   63,  213,  213,   51,
   64,   71,   45,  225,   22,   26,   92,  213,    3,    0,
  213,   79,   99,  213,  213,  213,  213,  213,  213,  213,
  213,  213,  213,  213,  213,  213,  213,    9,   20,    0,
 -125,    0,    0,   57,  -73,    0,  130,    0,  123,    0,
  151,  151,  315,  315,  315,  315,  315,  315,   24,   24,
   79,   79,   79,    0,  511,  511,    0, -108, -107,   51,
    0,  375,  412,   62,   69,    0,  -93,    0, -234,   55,
   51,  511,  425,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,  -92,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  196,
    0,    0,  120,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  295,    0,    0,   56,  312,
    0,    0,    0,    0,    0,    0,  -37,    0,    0,  -26,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   -2,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  327,    0,    0,   66,    0,    0,    0,
  -29,   28,  167,  173,  201,  228,  258,  269,  141,  161,
    5,   31,   38,    0,    0,    0,    0,    0,    0,  362,
    0,    0,    0,    0,    0,    0,  255,    0,    0,    0,
  394,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,   42,    0,    0,  675,    0,    0,  667,  185,
  361,   -9,  125,  180,    0,
};
final static int YYTABLESIZE=808;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         55,
   11,   20,   12,   55,   55,   55,   55,   55,   55,   55,
   33,   48,   52,   45,   33,   33,   33,  119,   33,   33,
   33,   55,   55,   19,   55,   50,   20,   51,   52,   48,
   15,   63,   33,   33,   51,   33,   13,   16,   51,   51,
   51,   38,   51,   53,   51,   38,   38,   38,   44,   38,
   14,   38,   18,   21,   92,   22,   51,   51,   23,   51,
   86,   36,   39,   38,   38,   84,   38,   39,   49,   87,
   85,   39,   39,   39,   41,   39,   49,   39,   41,   41,
   41,   41,   41,   40,   41,   47,   49,   55,   48,   39,
   39,   65,   39,   66,   20,   48,   41,   41,   33,   41,
   86,   70,   71,   90,   88,   84,   82,   86,   83,   87,
   85,   89,   84,   82,   94,   83,   87,   85,   95,    3,
    4,    5,   51,   81,   87,   80,   16,   98,   86,   38,
   81,  115,   80,   84,   82,   86,   83,   87,   85,  100,
   84,   82,  116,   83,   87,   85,  117,  118,  124,  125,
   96,   81,   49,   80,  128,   39,  122,  123,   81,   86,
   80,  129,   41,  121,   84,   82,   86,   83,   87,   85,
  130,   84,   82,  133,   83,   87,   85,  132,   20,    9,
    7,   37,   81,   37,   80,   37,   10,   86,   93,   81,
    6,   80,   84,   82,   16,   83,   87,   85,   16,   37,
   37,   40,   37,   40,   38,   40,    0,   44,    0,    0,
   81,    0,   80,   45,    0,    0,    3,    4,    5,   40,
   40,    6,   40,    0,    0,   44,   44,    0,   44,    0,
    0,   45,   45,    0,   45,   55,   55,   55,   55,   55,
   55,   46,    0,   48,   48,    0,   33,   33,   33,   33,
   33,   33,   59,    0,   25,    0,   25,   58,    0,   46,
   46,    0,   46,    0,    0,   37,    0,    0,   47,    0,
   51,   51,   51,   51,   51,   51,    0,   38,   38,   38,
   38,   38,   38,    0,    0,   40,   47,   47,    0,   47,
    0,   44,    0,    0,    0,    0,    0,   45,   42,    0,
   49,   49,    0,   39,   39,   39,   39,   39,   39,   43,
   41,   41,   41,   41,   41,   41,   42,   42,    0,   42,
   25,    0,    0,    0,    0,   46,    0,   43,   43,    0,
   43,    0,    0,    0,    0,    0,   74,   75,   76,   77,
   78,   79,    0,   74,   75,   76,   77,   78,   79,   91,
    0,   86,   47,   28,    0,   28,   84,   82,    0,   83,
   87,   85,    0,    0,   74,   75,   76,   77,   78,   79,
   20,   74,   75,   76,   77,   78,   79,    0,    0,   15,
    0,    0,   42,    0,    0,   26,    0,   26,    0,   43,
    0,    0,    0,   43,    0,   74,   75,   76,   77,   78,
   79,    0,   74,   75,   76,   77,   78,   79,    0,   43,
    0,    0,    0,   37,   37,   37,   37,   37,   37,   28,
   29,    0,   29,    0,    0,   76,   77,   78,   79,    0,
    0,    0,    0,   40,   40,   40,   40,   40,   40,   44,
   44,   44,   44,   44,   44,   45,   45,   45,   45,   45,
   45,   26,   30,    0,   30,   25,   25,   25,   25,    0,
   25,   25,   25,   25,   42,    0,    0,   25,    0,   54,
   55,   56,    0,   46,   46,   46,   46,   46,   46,    0,
    0,    0,   43,   43,   57,    0,   29,    0,    0,    3,
    4,    5,   28,   43,    0,    0,   16,    0,    0,  126,
   47,   47,   47,   47,   47,   47,    0,    0,    0,    0,
    0,    0,    0,    0,   15,   15,   15,   15,   30,   15,
   15,   15,   15,   15,    0,    0,   15,    0,    0,    0,
   42,   42,   42,   42,   42,   42,  127,    0,    0,    0,
    0,   43,   43,   43,   43,   43,   43,    0,    0,  134,
    0,    0,    0,    0,   28,   28,   28,   28,    0,   28,
   28,   28,   28,    0,    0,    0,   28,    0,    0,    0,
    0,   23,   23,   23,   23,    0,   23,   23,   23,   23,
    0,    0,    0,   23,    0,    0,   26,   26,   26,   26,
    0,   26,   26,   26,   26,    0,    0,    0,   26,   24,
   25,   26,   27,    0,    3,    4,    5,   28,    0,    0,
    0,   16,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   29,   29,   29,   29,    0,   29,   29,   29,   29,
    0,    0,    0,   29,   24,   25,   26,   27,    0,    3,
    4,    5,   28,    0,    0,    0,   16,    0,    0,    0,
    0,    0,    0,   30,   30,   30,   30,    0,   30,   30,
   30,   30,    0,    0,    0,   30,    0,    0,    0,    0,
    0,   24,   25,   26,   27,    0,    3,    4,    5,   28,
    0,    0,    0,   16,   24,   25,   26,   27,   17,    3,
    4,    5,   28,    0,    0,   30,   16,    0,    0,    0,
    0,    0,    0,   30,    0,   46,   62,    0,    0,   30,
    0,    0,    0,   67,    0,   30,    0,    0,    0,    0,
    0,    0,    0,   30,   72,   73,    0,    0,    0,    0,
    0,    0,    0,    0,   97,    0,    0,   99,   30,    0,
  101,  102,  103,  104,  105,  106,  107,  108,  109,  110,
  111,  112,  113,  114,   24,   25,   26,   27,    0,    3,
    4,    5,   28,   68,    0,    0,   16,    0,    0,  120,
   24,   25,   26,   27,    0,    3,    4,    5,   28,    0,
    0,    0,   16,    0,    0,    0,    0,    0,    0,   30,
   30,    0,    0,    0,    0,    0,   30,   30,    0,    0,
    0,    0,    0,  131,    0,    0,   30,   30,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
  271,   44,  272,   41,   42,   43,   44,   45,   46,   47,
   37,   41,   44,   91,   41,   42,   43,   91,   45,   46,
   47,   59,   60,   41,   62,   35,   44,   59,   44,   59,
   41,   41,   59,   60,   37,   62,   40,  272,   41,   42,
   43,   37,   45,   59,   47,   41,   42,   43,   91,   45,
   40,   47,  123,  280,   64,  123,   59,   60,  272,   62,
   37,  259,   40,   59,   60,   42,   62,   37,   41,   46,
   47,   41,   42,   43,   37,   45,   35,   47,   41,   42,
   43,  123,   45,   40,   47,   61,   59,  125,   59,   59,
   60,  257,   62,  257,   44,  125,   59,   60,  125,   62,
   37,  259,   40,   59,   41,   42,   43,   37,   45,   46,
   47,   41,   42,   43,   93,   45,   46,   47,   93,  265,
  266,  267,  125,   60,   46,   62,  272,  125,   37,  125,
   60,  123,   62,   42,   43,   37,   45,   46,   47,   41,
   42,   43,  123,   45,   46,   47,  272,   91,  257,  257,
   59,   60,  125,   62,   93,  125,  115,  116,   60,   37,
   62,   93,  125,   41,   42,   43,   37,   45,   46,   47,
  264,   42,   43,  132,   45,   46,   47,  123,   59,  272,
  125,   41,   60,   43,   62,   45,    2,   37,   64,   60,
  125,   62,   42,   43,  272,   45,   46,   47,  272,   59,
   60,   41,   62,   43,   25,   45,   -1,   41,   -1,   -1,
   60,   -1,   62,   41,   -1,   -1,  265,  266,  267,   59,
   60,  270,   62,   -1,   -1,   59,   60,   -1,   62,   -1,
   -1,   59,   60,   -1,   62,  273,  274,  275,  276,  277,
  278,   41,   -1,  273,  274,   -1,  273,  274,  275,  276,
  277,  278,   40,   -1,   59,   -1,   61,   45,   -1,   59,
   60,   -1,   62,   -1,   -1,  125,   -1,   -1,   41,   -1,
  273,  274,  275,  276,  277,  278,   -1,  273,  274,  275,
  276,  277,  278,   -1,   -1,  125,   59,   60,   -1,   62,
   -1,  125,   -1,   -1,   -1,   -1,   -1,  125,   41,   -1,
  273,  274,   -1,  273,  274,  275,  276,  277,  278,   41,
  273,  274,  275,  276,  277,  278,   59,   60,   -1,   62,
  125,   -1,   -1,   -1,   -1,  125,   -1,   59,   60,   -1,
   62,   -1,   -1,   -1,   -1,   -1,  273,  274,  275,  276,
  277,  278,   -1,  273,  274,  275,  276,  277,  278,  125,
   -1,   37,  125,   59,   -1,   61,   42,   43,   -1,   45,
   46,   47,   -1,   -1,  273,  274,  275,  276,  277,  278,
   59,  273,  274,  275,  276,  277,  278,   -1,   -1,  125,
   -1,   -1,  125,   -1,   -1,   59,   -1,   61,   -1,   29,
   -1,   -1,   -1,  125,   -1,  273,  274,  275,  276,  277,
  278,   -1,  273,  274,  275,  276,  277,  278,   -1,   49,
   -1,   -1,   -1,  273,  274,  275,  276,  277,  278,  125,
   59,   -1,   61,   -1,   -1,  275,  276,  277,  278,   -1,
   -1,   -1,   -1,  273,  274,  275,  276,  277,  278,  273,
  274,  275,  276,  277,  278,  273,  274,  275,  276,  277,
  278,  125,   59,   -1,   61,  260,  261,  262,  263,   -1,
  265,  266,  267,  268,  125,   -1,   -1,  272,   -1,  257,
  258,  259,   -1,  273,  274,  275,  276,  277,  278,   -1,
   -1,   -1,  122,  123,  272,   -1,  125,   -1,   -1,  265,
  266,  267,  268,  133,   -1,   -1,  272,   -1,   -1,  125,
  273,  274,  275,  276,  277,  278,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  260,  261,  262,  263,  125,  265,
  266,  267,  268,  269,   -1,   -1,  272,   -1,   -1,   -1,
  273,  274,  275,  276,  277,  278,  125,   -1,   -1,   -1,
   -1,  273,  274,  275,  276,  277,  278,   -1,   -1,  125,
   -1,   -1,   -1,   -1,  260,  261,  262,  263,   -1,  265,
  266,  267,  268,   -1,   -1,   -1,  272,   -1,   -1,   -1,
   -1,  260,  261,  262,  263,   -1,  265,  266,  267,  268,
   -1,   -1,   -1,  272,   -1,   -1,  260,  261,  262,  263,
   -1,  265,  266,  267,  268,   -1,   -1,   -1,  272,  260,
  261,  262,  263,   -1,  265,  266,  267,  268,   -1,   -1,
   -1,  272,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  260,  261,  262,  263,   -1,  265,  266,  267,  268,
   -1,   -1,   -1,  272,  260,  261,  262,  263,   -1,  265,
  266,  267,  268,   -1,   -1,   -1,  272,   -1,   -1,   -1,
   -1,   -1,   -1,  260,  261,  262,  263,   -1,  265,  266,
  267,  268,   -1,   -1,   -1,  272,   -1,   -1,   -1,   -1,
   -1,  260,  261,  262,  263,   -1,  265,  266,  267,  268,
   -1,   -1,   -1,  272,  260,  261,  262,  263,   14,  265,
  266,  267,  268,   -1,   -1,   21,  272,   -1,   -1,   -1,
   -1,   -1,   -1,   29,   -1,   31,   40,   -1,   -1,   35,
   -1,   -1,   -1,   47,   -1,   41,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   49,   58,   59,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   68,   -1,   -1,   71,   64,   -1,
   74,   75,   76,   77,   78,   79,   80,   81,   82,   83,
   84,   85,   86,   87,  260,  261,  262,  263,   -1,  265,
  266,  267,  268,  269,   -1,   -1,  272,   -1,   -1,   95,
  260,  261,  262,  263,   -1,  265,  266,  267,  268,   -1,
   -1,   -1,  272,   -1,   -1,   -1,   -1,   -1,   -1,  115,
  116,   -1,   -1,   -1,   -1,   -1,  122,  123,   -1,   -1,
   -1,   -1,   -1,  129,   -1,   -1,  132,  133,
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
"DISTINTO","IGUALDAD","MENOS_UNARIO","variableFuncion",
};
final static String yyrule[] = {
"$accept : programa",
"programa : funciones main",
"main : VOID MAIN '(' ')' '{' variableFuncion sentencias '}'",
"funciones : funciones funcion",
"funciones :",
"funcion : tipoFuncion ID '(' identificador ')' '{' variables sentencias retorno '}'",
"retorno : RETURN expresion",
"retorno :",
"tipoFuncion : tipoSimple",
"tipoFuncion : VOID",
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

//#line 122 "../../src/sintactico/sintactico.y"

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
//#line 487 "Parser.java"
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
