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
public final static short SINCORCHETES=281;
public final static short variables=282;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    2,    1,    1,    4,    8,   10,   10,   10,    6,
    6,    6,    7,    7,   12,   12,    5,    5,    3,    3,
   14,   14,   14,   14,   14,   14,   14,   14,   14,   14,
   14,   14,   14,   14,   14,   18,   18,   17,   19,   19,
   19,   19,   19,   15,   15,   15,   16,   16,   16,    9,
    9,   13,   13,   13,   13,   13,   13,   13,   13,   13,
   13,   13,   13,   13,   13,   13,   13,   13,   13,   13,
   13,   13,   13,   13,   20,   20,   11,   11,   11,
};
final static short yylen[] = {                            2,
    2,    7,    2,    0,    8,    4,    1,    1,    1,    4,
    2,    0,    3,    0,    3,    0,    1,    1,    2,    1,
    1,    1,    4,    4,    6,    2,    7,    7,    5,    4,
    2,    3,    3,    5,    2,    3,    0,    5,    2,    2,
    2,    1,    1,    1,    4,    7,    3,    6,    9,    3,
    1,    1,    1,    1,    1,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    2,
    2,    3,    4,    1,    3,    1,    1,    1,    1,
};
final static short yydefred[] = {                         4,
    0,    0,   77,   78,   79,    0,    1,    3,    0,   18,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   11,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   20,    0,    0,    0,    0,    0,   53,   54,   55,
    0,    0,    0,    0,   74,    0,    0,   52,    0,    0,
    0,    0,   31,    0,    0,    0,    0,    2,   19,   35,
   76,    0,    0,    0,    0,   26,    0,    0,   10,    0,
    0,    0,    0,    0,    0,   33,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   32,    0,    0,    0,    0,   42,   43,    0,    0,    0,
    0,    0,    0,    0,   47,    0,    0,    0,    5,    0,
   72,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   69,    0,    0,   30,    0,
   40,   41,   39,    0,    0,    6,    0,    0,    0,   75,
   23,   24,    0,   13,   73,    0,    0,   29,   38,    0,
    0,   34,    8,    9,    7,   36,    0,    0,    0,    0,
    0,    0,   25,    0,   48,   15,   27,   28,   46,    0,
    0,   49,
};
final static short yydgoto[] = {                          1,
    2,    7,   29,    8,    9,   16,   68,   45,  101,  156,
   31,  144,   47,   32,   48,   34,   35,  102,   98,   63,
};
final static short yysindex[] = {                         0,
    0,   29,    0,    0,    0, -243,    0,    0, -240,    0,
   -3,    4,    8,   50,  -85,  110, -218,  566,  -68,   50,
    0,  108,  108,   42,   44,  121,  -29,   19,  453,   34,
  -89,    0,   40,   66,   52, -163, -149,    0,    0,    0,
  -28,  108,  108,  213,    0,  -30,  246,    0,   31,  108,
  108,  566,    0,  -50,  108, -144,  108,    0,    0,    0,
    0,  108,   58,  108,  108,    0,  566,   20,    0,  108,
  103,  103,  115,   97,  108,    0,  108,  108,  108,  108,
  108,  108,  108,  108,  108,  108,  108,  108,  108,  108,
    0,  124,  131,  505,   67,    0,    0,   85,  -43,  101,
  119,  201,  -31,  -97,    0,  152,  159,  599,    0,  108,
    0,  246,  281,  281,  375,  375,  375,  375,  375,  375,
  283,  283,  103,  103,  103,    0,   56,  308,    0,  -91,
    0,    0,    0,   99,  108,    0,  129, -122,  -65,    0,
    0,    0,  108,    0,    0,  566,  566,    0,    0,  108,
  218,    0,    0,    0,    0,    0,  108,   88,  317,  518,
  532,  -17,    0,   53,    0,    0,    0,    0,    0,  -80,
   93,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,  -69,    0,    0,    0,    0,
    0,    0,    0,  215,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  342,    0,    0,
    0,    0,  373,  387,    0,   83,    0,    0,    0,    0,
  -26,    0,    0,    0,    0,    0,    7,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  225,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   -2,   27,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  350,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   84,    0,    0,
    0,  238,  141,  226,  404,  414,  424,  467,  474,  484,
  351,  397,   36,   62,   71,    0,    0,    0,    0,    0,
    0,    0,    0,  -37,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,  -21,    0,    0,    0,    0,  788,   30,    0,
  535,    0,  875,  697,  783,  -51,  122,    0,    0, -109,
};
final static int YYTABLESIZE=1018;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         45,
   75,   62,   97,   45,   45,   45,   45,   45,   45,   45,
   44,   70,   75,   75,   44,   44,   44,   44,   44,   44,
   44,   45,   45,   45,   45,  157,   75,   11,   76,  158,
   94,   12,   44,   44,   71,   44,   13,   18,   71,   71,
   71,   71,   71,   14,   71,  108,  132,   51,   15,  134,
   51,   46,   49,   21,   36,   45,   71,   71,   57,   71,
  171,  139,   55,   70,   56,   51,   44,   70,   70,   70,
   70,   70,   57,   70,   75,  169,   57,   57,   57,   57,
   57,   50,   57,   51,   99,   70,   70,   45,   70,   91,
   71,  103,   60,   54,   57,   57,   75,   57,   58,   51,
   64,  104,   58,   58,   58,   58,   58,   60,   58,   55,
   66,   60,   60,   60,   60,   60,  105,   60,   67,   70,
   58,   58,   69,   58,  160,  161,   65,  100,   57,   60,
   60,  104,   60,   89,  153,  154,  104,  111,   87,   85,
   42,   86,   90,   88,  109,  170,  165,   44,   90,  155,
   19,  172,   43,   20,   58,  110,   84,   55,   83,  136,
   89,  135,   75,   60,  127,   87,   85,   89,   86,   90,
   88,  128,   87,   85,  140,   86,   90,   88,  146,  162,
  149,   67,   61,   84,   67,   83,  164,  152,   89,  150,
   84,   61,   83,   87,   85,   89,   86,   90,   88,   67,
   87,   85,   17,   86,   90,   88,   61,   14,   16,  130,
  141,   84,    0,   83,    3,    4,    5,  142,   84,  133,
   83,   95,   45,   45,   45,   45,   45,   45,   45,   45,
   45,   45,    0,   67,   45,   45,   45,   45,   45,   45,
   45,  137,    0,   52,  138,   42,   44,   44,   44,   44,
   44,   44,   44,    0,   89,   12,    0,   43,   12,   87,
   85,    0,   86,   90,   88,   37,   68,    0,   37,   68,
   71,   71,   71,   71,   71,   71,  163,   84,   50,   83,
    0,   50,   89,    0,   68,    0,    0,   87,   85,    0,
   86,   90,   88,    3,    4,    5,   50,    0,    6,   70,
   70,   70,   70,   70,   70,   84,    0,   83,   57,   57,
   57,   57,   57,   57,    3,    4,    5,   89,   68,   89,
    0,    0,   87,   85,   87,   86,   90,   88,   90,   88,
   50,    0,    0,    0,   58,   58,   58,   58,   58,   58,
   84,    0,   83,   60,   60,   60,   60,   60,   60,    3,
    4,    5,   27,   89,    0,    0,   95,    0,   87,   85,
    0,   86,   90,   88,   38,   39,   40,    0,    0,   77,
   78,   79,   80,   81,   82,  166,   84,    0,   83,   41,
   22,   23,   24,   25,   26,    3,    4,    5,   27,    0,
    0,   56,   28,   56,   56,   56,   77,   78,   79,   80,
   81,   82,   44,   77,   78,   79,   80,   81,   82,   56,
   56,   89,   56,   67,   67,    0,   87,   85,    0,   86,
   90,   88,    0,    0,   77,   78,   79,   80,   81,   82,
  147,   77,   78,   79,   80,   81,   82,   59,    0,   59,
   59,   59,    0,   56,   63,    0,    0,   63,    0,    0,
    0,    0,    0,    0,   64,   59,   59,   64,   59,    0,
    0,    0,   63,   63,   65,   63,   44,   65,    0,   38,
   39,   40,   64,   64,   44,   64,    0,    3,    4,    5,
    0,    0,   65,   65,   41,   65,    0,    0,    0,   59,
   77,   78,   79,   80,   81,   82,   63,   21,   68,   68,
    0,    0,    0,    0,    0,    0,   64,   66,    0,    0,
   66,   22,    0,    0,   61,    0,   65,   61,   77,   78,
   79,   80,   81,   82,   62,   66,   66,   62,   66,    0,
    0,    0,   61,   61,    0,   61,   10,    0,    0,    0,
    0,    0,   62,   62,    0,   62,    0,    0,   17,    0,
    0,    0,    0,    0,   37,   79,   80,   81,   82,   66,
    0,    0,    0,    0,    0,    0,   61,   22,   23,   24,
   25,   26,    3,    4,    5,   27,   62,   58,   73,   28,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   77,
   78,   79,   80,   81,   82,    0,    0,    0,    0,    0,
    0,   44,   44,   44,   44,   44,   44,   44,   44,   44,
   44,    0,    0,   44,   44,   44,   44,   44,    0,    0,
    0,   44,    0,   56,   56,   56,   56,   56,   56,  129,
    0,    0,   21,   21,   21,   21,   21,   21,   21,   21,
   21,   21,  167,    0,   21,    0,   22,   22,   22,   22,
   22,   22,   22,   22,   22,   22,  168,    0,   22,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   59,
   59,   59,   59,   59,   59,    0,   63,   63,   63,   63,
   63,   63,    0,    0,    0,    0,   64,   64,   64,   64,
   64,   64,    0,    0,    0,    0,   65,   65,   65,   65,
   65,   65,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   22,   23,   24,   25,   26,    3,    4,    5,
   27,    0,   53,    0,   28,   59,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   66,
   66,   66,   66,   66,   66,    0,   61,   61,   61,   61,
   61,   61,    0,    0,    0,    0,   62,   62,   62,   62,
   62,   62,    0,    0,   22,   23,   24,   25,   26,    3,
    4,    5,   27,    0,    0,    0,   28,   22,   23,   24,
   25,   26,    3,    4,    5,   27,    0,    0,    0,   28,
   59,   22,   23,   24,   25,   26,    3,    4,    5,   27,
   33,    0,    0,   28,   59,   30,    0,    0,   33,    0,
    0,   33,    0,   30,    0,    0,   30,    0,    0,    0,
    0,    0,    0,    0,  148,   22,   23,   24,   25,   26,
    3,    4,    5,   27,   33,    0,   96,   28,    0,   30,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   33,
    0,    0,    0,    0,   30,    0,   59,   59,   22,   23,
   24,   25,   26,    3,    4,    5,   27,  143,    0,    0,
   28,    0,    0,    0,    0,    0,   33,    0,    0,    0,
  131,   30,    0,    0,    0,    0,    0,    0,    0,    0,
   33,    0,    0,    0,    0,   30,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   33,    0,    0,    0,    0,   30,   71,   72,   74,    0,
    0,    0,    0,    0,   92,   93,    0,    0,   33,   33,
    0,    0,    0,   30,   30,    0,    0,    0,  106,  107,
    0,    0,   33,   33,    0,    0,    0,   30,   30,  112,
    0,  113,  114,  115,  116,  117,  118,  119,  120,  121,
  122,  123,  124,  125,  126,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  145,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  151,
    0,    0,    0,    0,    0,    0,    0,  159,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
   44,   91,   54,   41,   42,   43,   44,   45,   46,   47,
   37,   40,   44,   44,   41,   42,   43,   44,   45,   46,
   47,   59,   60,   61,   62,   91,   44,  271,   59,  139,
   52,  272,   59,   60,   37,   62,   40,  123,   41,   42,
   43,   44,   45,   40,   47,   67,   98,   41,   41,   93,
   44,   22,   23,  272,  123,   93,   59,   60,   40,   62,
  170,   93,   91,   37,   46,   59,   93,   41,   42,   43,
   44,   45,   37,   47,   44,   93,   41,   42,   43,   44,
   45,   40,   47,   40,   55,   59,   60,  125,   62,   59,
   93,   62,   59,  123,   59,   60,   44,   62,   37,   93,
   61,   44,   41,   42,   43,   44,   45,   37,   47,   91,
   59,   41,   42,   43,   44,   45,   59,   47,  282,   93,
   59,   60,  272,   62,  146,  147,   61,  272,   93,   59,
   60,   44,   62,   37,  257,  258,   44,   41,   42,   43,
   33,   45,   46,   47,  125,   93,   59,   40,   46,  272,
   41,   59,   45,   44,   93,   41,   60,   91,   62,   41,
   37,   61,   44,   93,   41,   42,   43,   37,   45,   46,
   47,   41,   42,   43,  272,   45,   46,   47,  123,  150,
  272,   41,  272,   60,   44,   62,  157,   59,   37,   91,
   60,  272,   62,   42,   43,   37,   45,   46,   47,   59,
   42,   43,  272,   45,   46,   47,  272,  125,  125,  125,
   59,   60,   -1,   62,  265,  266,  267,   59,   60,   98,
   62,  272,  260,  261,  262,  263,  264,  265,  266,  267,
  268,  269,   -1,   93,  272,  273,  274,  275,  276,  277,
  278,   41,   -1,  123,   44,   33,  273,  274,  275,  276,
  277,  278,   40,   -1,   37,   41,   -1,   45,   44,   42,
   43,   -1,   45,   46,   47,   41,   41,   -1,   44,   44,
  273,  274,  275,  276,  277,  278,   59,   60,   41,   62,
   -1,   44,   37,   -1,   59,   -1,   -1,   42,   43,   -1,
   45,   46,   47,  265,  266,  267,   59,   -1,  270,  273,
  274,  275,  276,  277,  278,   60,   -1,   62,  273,  274,
  275,  276,  277,  278,  265,  266,  267,   37,   93,   37,
   -1,   -1,   42,   43,   42,   45,   46,   47,   46,   47,
   93,   -1,   -1,   -1,  273,  274,  275,  276,  277,  278,
   60,   -1,   62,  273,  274,  275,  276,  277,  278,  265,
  266,  267,  268,   37,   -1,   -1,  272,   -1,   42,   43,
   -1,   45,   46,   47,  257,  258,  259,   -1,   -1,  273,
  274,  275,  276,  277,  278,   59,   60,   -1,   62,  272,
  260,  261,  262,  263,  264,  265,  266,  267,  268,   -1,
   -1,   41,  272,   43,   44,   45,  273,  274,  275,  276,
  277,  278,   61,  273,  274,  275,  276,  277,  278,   59,
   60,   37,   62,  273,  274,   -1,   42,   43,   -1,   45,
   46,   47,   -1,   -1,  273,  274,  275,  276,  277,  278,
  123,  273,  274,  275,  276,  277,  278,   41,   -1,   43,
   44,   45,   -1,   93,   41,   -1,   -1,   44,   -1,   -1,
   -1,   -1,   -1,   -1,   41,   59,   60,   44,   62,   -1,
   -1,   -1,   59,   60,   41,   62,  125,   44,   -1,  257,
  258,  259,   59,   60,  125,   62,   -1,  265,  266,  267,
   -1,   -1,   59,   60,  272,   62,   -1,   -1,   -1,   93,
  273,  274,  275,  276,  277,  278,   93,  125,  273,  274,
   -1,   -1,   -1,   -1,   -1,   -1,   93,   41,   -1,   -1,
   44,  125,   -1,   -1,   41,   -1,   93,   44,  273,  274,
  275,  276,  277,  278,   41,   59,   60,   44,   62,   -1,
   -1,   -1,   59,   60,   -1,   62,    2,   -1,   -1,   -1,
   -1,   -1,   59,   60,   -1,   62,   -1,   -1,   14,   -1,
   -1,   -1,   -1,   -1,   20,  275,  276,  277,  278,   93,
   -1,   -1,   -1,   -1,   -1,   -1,   93,  260,  261,  262,
  263,  264,  265,  266,  267,  268,   93,  125,   44,  272,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  273,
  274,  275,  276,  277,  278,   -1,   -1,   -1,   -1,   -1,
   -1,  260,  261,  262,  263,  264,  265,  266,  267,  268,
  269,   -1,   -1,  272,  265,  266,  267,  268,   -1,   -1,
   -1,  272,   -1,  273,  274,  275,  276,  277,  278,  125,
   -1,   -1,  260,  261,  262,  263,  264,  265,  266,  267,
  268,  269,  125,   -1,  272,   -1,  260,  261,  262,  263,
  264,  265,  266,  267,  268,  269,  125,   -1,  272,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  273,
  274,  275,  276,  277,  278,   -1,  273,  274,  275,  276,
  277,  278,   -1,   -1,   -1,   -1,  273,  274,  275,  276,
  277,  278,   -1,   -1,   -1,   -1,  273,  274,  275,  276,
  277,  278,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  260,  261,  262,  263,  264,  265,  266,  267,
  268,   -1,   26,   -1,  272,   29,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  273,
  274,  275,  276,  277,  278,   -1,  273,  274,  275,  276,
  277,  278,   -1,   -1,   -1,   -1,  273,  274,  275,  276,
  277,  278,   -1,   -1,  260,  261,  262,  263,  264,  265,
  266,  267,  268,   -1,   -1,   -1,  272,  260,  261,  262,
  263,  264,  265,  266,  267,  268,   -1,   -1,   -1,  272,
   94,  260,  261,  262,  263,  264,  265,  266,  267,  268,
   18,   -1,   -1,  272,  108,   18,   -1,   -1,   26,   -1,
   -1,   29,   -1,   26,   -1,   -1,   29,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  128,  260,  261,  262,  263,  264,
  265,  266,  267,  268,   52,   -1,   54,  272,   -1,   52,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   67,
   -1,   -1,   -1,   -1,   67,   -1,  160,  161,  260,  261,
  262,  263,  264,  265,  266,  267,  268,  269,   -1,   -1,
  272,   -1,   -1,   -1,   -1,   -1,   94,   -1,   -1,   -1,
   98,   94,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  108,   -1,   -1,   -1,   -1,  108,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  128,   -1,   -1,   -1,   -1,  128,   42,   43,   44,   -1,
   -1,   -1,   -1,   -1,   50,   51,   -1,   -1,  146,  147,
   -1,   -1,   -1,  146,  147,   -1,   -1,   -1,   64,   65,
   -1,   -1,  160,  161,   -1,   -1,   -1,  160,  161,   75,
   -1,   77,   78,   79,   80,   81,   82,   83,   84,   85,
   86,   87,   88,   89,   90,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  110,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  135,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  143,
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
"DISTINTO","IGUALDAD","MENOS_UNARIO","NEGACION","SINCORCHETES","variables",
};
final static String yyrule[] = {
"$accept : programa",
"programa : funciones main",
"main : VOID MAIN '(' ')' '{' sentencias '}'",
"funciones : funciones funcion",
"funciones :",
"funcion : tipoFuncion ID '(' parametro ')' '{' cuerpo '}'",
"llamadaFuncion : ID '(' expresiones ')'",
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
"sentencia : llamadaVariable",
"sentencia : declaracionVariable",
"sentencia : llamadaVariable '=' expresion ';'",
"sentencia : declaracionVariable '=' expresion ';'",
"sentencia : ID '.' ID '=' expresion ';'",
"sentencia : estructura ';'",
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
"campos : campos llamadaVariable",
"campos : campos declaracionVariable",
"campos : llamadaVariable",
"campos : declaracionVariable",
"llamadaVariable : ID",
"llamadaVariable : ID '[' expresiones ']'",
"llamadaVariable : ID '[' expresiones ']' '[' expresiones ']'",
"declaracionVariable : tipoSimple identificador ';'",
"declaracionVariable : tipoSimple '[' expresiones ']' identificador ';'",
"declaracionVariable : tipoSimple '[' expresiones ']' '[' expresiones ']' identificador ';'",
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
"tipoSimple : INT",
"tipoSimple : DOUBLE",
"tipoSimple : CHAR",
};

//#line 154 "../../src/sintactico/sintactico.y"

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
//#line 568 "Parser.java"
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
