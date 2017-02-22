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
    0,    2,    1,    1,    4,    8,    8,    8,    6,    6,
    6,    7,    7,   10,   10,    5,    5,    3,    3,   12,
   12,   12,   12,   12,   12,   12,   12,   12,   12,   12,
   12,   12,   12,   17,   17,   15,   18,   18,   18,   18,
   18,   13,   13,   13,   14,   14,   14,   16,   16,   11,
   11,   11,   11,   11,   11,   11,   11,   11,   11,   11,
   11,   11,   11,   11,   11,   11,   11,   11,   11,   11,
   11,   11,   11,   19,   19,    9,    9,    9,
};
final static short yylen[] = {                            2,
    2,    7,    2,    0,    8,    1,    1,    1,    4,    2,
    0,    3,    0,    3,    0,    1,    1,    2,    1,    1,
    1,    4,    4,    6,    2,    7,    7,    5,   11,    7,
    2,    2,    5,    3,    0,    5,    2,    2,    2,    1,
    1,    1,    4,    7,    3,    6,    9,    3,    2,    1,
    4,    7,    1,    1,    1,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    2,
    2,    3,    4,    3,    1,    1,    1,    1,
};
final static short yydefred[] = {                         4,
    0,    0,   76,   77,   78,    0,    1,    3,    0,   17,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   10,    0,    0,    0,    0,    0,    0,    0,    0,   19,
    0,    0,    0,    0,    0,   53,   54,   55,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   35,    2,   18,   75,    0,    0,    0,    0,   25,    0,
    0,    9,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   49,    0,    0,    0,    0,   40,   41,    0,    0,
    0,    0,    0,    0,   45,    0,    0,    0,    5,    0,
    0,   72,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   69,    0,    0,    0,    0,
   38,   39,   37,    0,    0,    0,    0,    0,   74,   22,
   23,    0,   12,    0,   73,    0,    0,    0,   36,    0,
    0,   33,    7,    8,    6,   34,    0,    0,    0,    0,
    0,    0,    0,    0,   24,    0,   46,   14,    0,   26,
    0,   30,   44,    0,   52,    0,    0,    0,   47,    0,
   29,
};
final static short yydgoto[] = {                          1,
    2,    7,   28,    8,    9,   16,   61,  146,   29,  133,
   43,   30,   31,   32,   33,   44,   92,   89,   56,
};
final static short yysindex[] = {                         0,
    0, -133,    0,    0,    0, -252,    0,    0, -231,    0,
    3,    5,   10, -198,  -66,   -4, -209,  942,  -48, -198,
    0,  370,  370,   41,   47,  -34,  -29,  347,  -89,    0,
   32,   37,   40, -174, -163,    0,    0,    0,   25,  370,
  370,  359,  120,   73,   73,  370,  370, -182, -135, -143,
    0,    0,    0,    0, -126,  -24,  370,  370,    0,  942,
   19,    0, -110,  102,  102,  108,  144,  370,  370,  370,
  370,  370,  370,  370,  370,  370,  370,  370,  370,  370,
  370,    0,  370,  151,  393,   64,    0,    0,  -65,   66,
  100,   20,   71, -102,    0,  400,  421,  305,    0,   79,
  -99,    0,  477,  477,   68,   68,   68,   68,   68,   68,
  484,  484,  102,  102,  102,    0,  456,   51,  325,  -97,
    0,    0,    0,   85,  370,  118, -245,  -77,    0,    0,
    0,  370,    0,   87,    0,  942,  942,  -59,    0,  -58,
  428,    0,    0,    0,    0,    0,  -39,  -23,  449,  -38,
  898,  912,  942,  116,    0,  117,    0,    0,  119,    0,
  -44,    0,    0,  -50,    0,  110,    6,  942,    0,  925,
    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,  -28,    0,    0,    0,    0,
    0,    0,    0,   94,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  475,    0,    0,    0,
  706,  745,    0,  121,    0,    0,    0,    0,  -37,    0,
    0,    0,    0,  767,  780,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   11,   35,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  -51,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  132,    0,    0,
    0,    0,  800,  832,  539,  561,  589,  621,  859,  879,
  497,  519,   59,   83,  109,    0,  286,    0,    0,    0,
    0,    0,    0,  684,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  -13,    0,    0,    0,  720,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  815,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,
};
final static short yygindex[] = {                         0,
    0,    0,  -45,    0,    0,    0,    0,    0,   24,    0,
 1154,  -12,  -47,  -30,  153,  220,    0,    0, -125,
};
final static int YYTABLESIZE=1286;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         50,
   87,   55,  148,   50,   50,   50,   50,   50,   50,   50,
   51,  143,  144,  147,   98,   53,   50,   88,   11,   94,
   94,   50,   50,   51,   50,   10,  145,   51,   51,   51,
   51,   51,   51,   51,   95,  157,   19,   17,  167,   20,
   12,  121,   13,   35,   14,   51,   51,   71,   51,   94,
   15,   71,   71,   71,   71,   71,   18,   71,  122,  120,
  126,   49,   21,  127,  169,   66,    3,    4,    5,   71,
   71,   70,   71,   42,   34,   70,   70,   70,   70,   70,
   46,   70,    3,    4,    5,   53,   47,   50,   48,   86,
  151,  152,   57,   70,   70,   57,   70,   58,   59,   57,
   57,   57,   57,   57,   80,   57,  138,   60,   62,   78,
   76,   51,   77,   81,   79,   63,   83,   57,   57,   58,
   57,   90,  170,   58,   58,   58,   58,   58,   91,   58,
   93,    3,    4,    5,   11,   71,    6,   11,   53,   53,
  162,   58,   58,   99,   58,   60,  100,   81,  101,   60,
   60,   60,   60,   60,   49,   60,   80,   53,  124,   70,
  125,   78,   76,  128,   77,   81,   79,   60,   60,  129,
   60,  134,  135,  136,  139,  140,  142,  150,   82,   75,
   80,   74,   54,   57,  102,   78,   76,   80,   77,   81,
   79,  118,   78,   76,   54,   77,   81,   79,  154,    3,
    4,    5,   26,   75,  153,   74,   86,   58,  163,  164,
   75,  165,   74,   42,   42,   42,   42,  156,  159,  166,
   42,   54,   50,   50,   50,   50,   50,   50,   50,   50,
   50,   50,  168,   60,   50,   50,   50,   50,   50,   50,
   50,  123,   45,   16,    0,   13,   51,   51,   51,   51,
   51,   51,   51,   51,   51,   51,   15,    0,   51,   51,
   51,   51,   51,   51,   51,    0,    0,    0,    0,    0,
   71,   71,   71,   71,   71,   71,   71,   71,   71,   71,
    0,    0,   71,   71,   71,   71,   71,   71,   71,    0,
    0,    0,    0,    0,   70,   70,   70,   70,   70,   70,
   70,   70,   70,   70,    0,    0,   70,   70,   70,   70,
   70,   70,   70,    0,    0,    0,    0,    0,   57,   57,
   57,   57,   57,   57,   57,   57,   57,   57,    0,   48,
   57,   57,   57,   57,   57,   57,   57,    0,    0,    0,
    0,    0,   58,   58,   58,   58,   58,   58,   58,   58,
   58,   58,    0,    0,   58,   58,   58,   58,   58,   58,
   58,    0,    0,    0,    0,    0,    0,    0,   60,   60,
   60,   60,   60,   60,   60,   60,   60,   60,    0,    0,
   60,   60,   60,   60,   60,   60,   60,    0,    0,    0,
    0,   40,   68,   69,   70,   71,   72,   73,   42,    0,
    0,    0,   40,   41,    0,    0,    0,    0,    0,   42,
   48,    0,    0,    0,   41,    0,   68,   69,   70,   71,
   72,   73,    0,   68,   69,   70,   71,   72,   73,   80,
    0,    0,    0,  119,   78,   76,   80,   77,   81,   79,
    0,   78,   76,    0,   77,   81,   79,  137,    0,    0,
    0,    0,   75,    0,   74,    0,    0,   80,  130,   75,
    0,   74,   78,   76,   80,   77,   81,   79,    0,   78,
   76,   52,   77,   81,   79,    0,    0,    0,    0,  131,
   75,    0,   74,    0,    0,   80,  155,   75,    0,   74,
   78,   76,   80,   77,   81,   79,    0,   78,   76,    0,
   77,   81,   79,    0,    0,    0,    0,  158,   75,    0,
   74,    0,    0,   80,    0,   75,    0,   74,   78,   76,
   80,   77,   81,   79,    0,   78,    0,    0,    0,   81,
   79,    0,    0,    0,    0,   42,   75,   56,   74,   56,
   56,   56,    0,    0,    0,   48,   48,   48,   48,   48,
   48,   48,   48,   48,   48,   56,   56,   48,   56,   59,
    0,   59,   59,   59,   22,   23,   24,   25,    0,    3,
    4,    5,   26,  132,    0,    0,   27,   59,   59,   63,
   59,    0,   63,    0,   22,   23,   24,   25,    0,    3,
    4,    5,   26,    0,    0,    0,   27,   63,   63,   42,
   63,   64,    0,    0,   64,    0,   22,   23,   24,   25,
    0,    3,    4,    5,   26,   36,   37,   38,   27,   64,
   64,   56,   64,    3,    4,    5,   36,   37,   38,   65,
   39,    0,   65,    0,    0,    0,    0,    0,    0,    0,
    0,   39,    0,   59,    0,    0,    0,   65,   65,    0,
   65,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   66,    0,   63,   66,   68,   69,   70,   71,   72,
   73,    0,   68,   69,   70,   71,   72,   73,    0,   66,
   66,    0,   66,    0,    0,   64,    0,    0,    0,    0,
    0,    0,    0,   68,   69,   70,   71,   72,   73,    0,
   68,   69,   70,   71,   72,   73,    0,    0,    0,    0,
    0,    0,    0,   65,    0,    0,    0,    0,    0,    0,
    0,   68,   69,   70,   71,   72,   73,    0,   68,   69,
   70,   71,   72,   73,   42,   42,   42,   42,   42,   42,
   42,   42,   42,   42,   43,   66,   42,    0,    0,    0,
    0,   70,   71,   72,   73,    0,   56,   56,   56,   56,
   56,   56,   56,   56,   56,   56,    0,    0,   56,   56,
   56,   56,   56,   56,   56,    0,    0,    0,   59,   59,
   59,   59,   59,   59,   59,   59,   59,   59,    0,    0,
   59,   59,   59,   59,   59,   59,   59,    0,   63,   63,
   63,   63,   63,   63,   63,   63,   63,   63,   43,    0,
   63,   63,   63,   63,   63,   63,   63,    0,    0,    0,
   64,   64,   64,   64,   64,   64,   64,   64,   64,   64,
   20,    0,   64,   64,   64,   64,   64,   64,   64,    0,
   67,    0,    0,   67,   28,    0,    0,    0,   65,   65,
   65,   65,   65,   65,   65,   65,   65,   65,   67,    0,
   65,   65,   65,   65,   65,   65,   65,    0,    0,   21,
    0,    0,   68,    0,    0,   68,    0,    0,    0,    0,
   66,   66,   66,   66,   66,   66,   66,   66,   66,   66,
   68,   32,   66,   66,   66,   66,   66,   66,   66,   61,
    0,    0,   61,    0,   31,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   61,   61,   62,
   61,    0,   62,    0,   67,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   62,   62,   27,
   62,    0,    0,   43,   43,   43,   43,   43,   43,   43,
   43,   43,   43,    0,    0,   43,   68,    0,    0,    0,
    0,    0,    0,    0,    0,   20,   20,   20,   20,   20,
   20,   20,   20,   20,   20,    0,    0,   20,    0,   28,
   28,   28,   28,   61,   28,   28,   28,   28,   28,    0,
    0,   28,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   62,   21,   21,   21,   21,   21,   21,
   21,   21,   21,   21,    0,    0,   21,    0,    0,    0,
    0,    0,  160,    0,    0,    0,   32,   32,   32,   32,
   32,   32,   32,   32,   32,   32,  161,    0,   32,   31,
   31,   31,   31,   31,   31,   31,   31,   31,   31,  171,
    0,   31,    0,    0,    0,    0,    0,    0,    0,   67,
   67,   67,   67,   67,   67,   67,   67,   67,   67,    0,
    0,   67,   67,   67,   27,   27,   27,   27,    0,   27,
   27,   27,   27,   27,    0,    0,   27,    0,    0,    0,
    0,   68,   68,   68,   68,   68,   68,   68,   68,   68,
   68,    0,    0,   68,   68,   68,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   61,   61,
   61,   61,   61,   61,   61,   61,   61,   61,    0,    0,
   61,   61,   61,   61,   61,   61,   61,    0,   62,   62,
   62,   62,   62,   62,   62,   62,   62,   62,    0,    0,
   62,   62,   62,   62,   62,   62,   62,   22,   23,   24,
   25,    0,    3,    4,    5,   26,    0,    0,    0,   27,
    0,   22,   23,   24,   25,    0,    3,    4,    5,   26,
    0,    0,    0,   27,   22,   23,   24,   25,    0,    3,
    4,    5,   26,   64,   65,   67,   27,    0,    0,   84,
   85,   22,   23,   24,   25,    0,    3,    4,    5,   26,
   96,   97,    0,   27,    0,    0,    0,    0,    0,    0,
    0,  103,  104,  105,  106,  107,  108,  109,  110,  111,
  112,  113,  114,  115,  116,    0,  117,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  141,    0,
    0,    0,    0,    0,    0,  149,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
   48,   91,  128,   41,   42,   43,   44,   45,   46,   47,
   40,  257,  258,   91,   60,   28,   46,   48,  271,   44,
   44,   59,   60,   37,   62,    2,  272,   41,   42,   43,
   44,   45,   46,   47,   59,   59,   41,   14,  164,   44,
  272,   89,   40,   20,   40,   59,   60,   37,   62,   44,
   41,   41,   42,   43,   44,   45,  123,   47,   89,  125,
   41,   91,  272,   44,   59,   42,  265,  266,  267,   59,
   60,   37,   62,  125,  123,   41,   42,   43,   44,   45,
   40,   47,  265,  266,  267,   98,   40,  125,  123,  272,
  136,  137,   61,   59,   60,   37,   62,   61,   59,   41,
   42,   43,   44,   45,   37,   47,  119,  282,  272,   42,
   43,  125,   45,   46,   47,   91,   44,   59,   60,   37,
   62,  257,  168,   41,   42,   43,   44,   45,  272,   47,
  257,  265,  266,  267,   41,  125,  270,   44,  151,  152,
  153,   59,   60,  125,   62,   37,  257,   46,   41,   41,
   42,   43,   44,   45,   91,   47,   37,  170,   93,  125,
   61,   42,   43,   93,   45,   46,   47,   59,   60,  272,
   62,   93,  272,  123,  272,   91,   59,   91,   59,   60,
   37,   62,  272,  125,   41,   42,   43,   37,   45,   46,
   47,   41,   42,   43,  272,   45,   46,   47,  257,  265,
  266,  267,  268,   60,  264,   62,  272,  125,   93,   93,
   60,   93,   62,  265,  266,  267,  268,  257,  257,  264,
  272,  272,  260,  261,  262,  263,  264,  265,  266,  267,
  268,  269,  123,  125,  272,  273,  274,  275,  276,  277,
  278,   89,   23,  272,   -1,  125,  260,  261,  262,  263,
  264,  265,  266,  267,  268,  269,  125,   -1,  272,  273,
  274,  275,  276,  277,  278,   -1,   -1,   -1,   -1,   -1,
  260,  261,  262,  263,  264,  265,  266,  267,  268,  269,
   -1,   -1,  272,  273,  274,  275,  276,  277,  278,   -1,
   -1,   -1,   -1,   -1,  260,  261,  262,  263,  264,  265,
  266,  267,  268,  269,   -1,   -1,  272,  273,  274,  275,
  276,  277,  278,   -1,   -1,   -1,   -1,   -1,  260,  261,
  262,  263,  264,  265,  266,  267,  268,  269,   -1,   44,
  272,  273,  274,  275,  276,  277,  278,   -1,   -1,   -1,
   -1,   -1,  260,  261,  262,  263,  264,  265,  266,  267,
  268,  269,   -1,   -1,  272,  273,  274,  275,  276,  277,
  278,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  260,  261,
  262,  263,  264,  265,  266,  267,  268,  269,   -1,   -1,
  272,  273,  274,  275,  276,  277,  278,   -1,   -1,   -1,
   -1,   33,  273,  274,  275,  276,  277,  278,   40,   -1,
   -1,   -1,   33,   45,   -1,   -1,   -1,   -1,   -1,   40,
  125,   -1,   -1,   -1,   45,   -1,  273,  274,  275,  276,
  277,  278,   -1,  273,  274,  275,  276,  277,  278,   37,
   -1,   -1,   -1,   41,   42,   43,   37,   45,   46,   47,
   -1,   42,   43,   -1,   45,   46,   47,  123,   -1,   -1,
   -1,   -1,   60,   -1,   62,   -1,   -1,   37,   59,   60,
   -1,   62,   42,   43,   37,   45,   46,   47,   -1,   42,
   43,  125,   45,   46,   47,   -1,   -1,   -1,   -1,   59,
   60,   -1,   62,   -1,   -1,   37,   59,   60,   -1,   62,
   42,   43,   37,   45,   46,   47,   -1,   42,   43,   -1,
   45,   46,   47,   -1,   -1,   -1,   -1,   59,   60,   -1,
   62,   -1,   -1,   37,   -1,   60,   -1,   62,   42,   43,
   37,   45,   46,   47,   -1,   42,   -1,   -1,   -1,   46,
   47,   -1,   -1,   -1,   -1,   61,   60,   41,   62,   43,
   44,   45,   -1,   -1,   -1,  260,  261,  262,  263,  264,
  265,  266,  267,  268,  269,   59,   60,  272,   62,   41,
   -1,   43,   44,   45,  260,  261,  262,  263,   -1,  265,
  266,  267,  268,  269,   -1,   -1,  272,   59,   60,   41,
   62,   -1,   44,   -1,  260,  261,  262,  263,   -1,  265,
  266,  267,  268,   -1,   -1,   -1,  272,   59,   60,  125,
   62,   41,   -1,   -1,   44,   -1,  260,  261,  262,  263,
   -1,  265,  266,  267,  268,  257,  258,  259,  272,   59,
   60,  125,   62,  265,  266,  267,  257,  258,  259,   41,
  272,   -1,   44,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  272,   -1,  125,   -1,   -1,   -1,   59,   60,   -1,
   62,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   41,   -1,  125,   44,  273,  274,  275,  276,  277,
  278,   -1,  273,  274,  275,  276,  277,  278,   -1,   59,
   60,   -1,   62,   -1,   -1,  125,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  273,  274,  275,  276,  277,  278,   -1,
  273,  274,  275,  276,  277,  278,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  125,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  273,  274,  275,  276,  277,  278,   -1,  273,  274,
  275,  276,  277,  278,  260,  261,  262,  263,  264,  265,
  266,  267,  268,  269,   61,  125,  272,   -1,   -1,   -1,
   -1,  275,  276,  277,  278,   -1,  260,  261,  262,  263,
  264,  265,  266,  267,  268,  269,   -1,   -1,  272,  273,
  274,  275,  276,  277,  278,   -1,   -1,   -1,  260,  261,
  262,  263,  264,  265,  266,  267,  268,  269,   -1,   -1,
  272,  273,  274,  275,  276,  277,  278,   -1,  260,  261,
  262,  263,  264,  265,  266,  267,  268,  269,  125,   -1,
  272,  273,  274,  275,  276,  277,  278,   -1,   -1,   -1,
  260,  261,  262,  263,  264,  265,  266,  267,  268,  269,
  125,   -1,  272,  273,  274,  275,  276,  277,  278,   -1,
   41,   -1,   -1,   44,  125,   -1,   -1,   -1,  260,  261,
  262,  263,  264,  265,  266,  267,  268,  269,   59,   -1,
  272,  273,  274,  275,  276,  277,  278,   -1,   -1,  125,
   -1,   -1,   41,   -1,   -1,   44,   -1,   -1,   -1,   -1,
  260,  261,  262,  263,  264,  265,  266,  267,  268,  269,
   59,  125,  272,  273,  274,  275,  276,  277,  278,   41,
   -1,   -1,   44,   -1,  125,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   59,   60,   41,
   62,   -1,   44,   -1,  125,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   59,   60,  125,
   62,   -1,   -1,  260,  261,  262,  263,  264,  265,  266,
  267,  268,  269,   -1,   -1,  272,  125,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  260,  261,  262,  263,  264,
  265,  266,  267,  268,  269,   -1,   -1,  272,   -1,  260,
  261,  262,  263,  125,  265,  266,  267,  268,  269,   -1,
   -1,  272,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  125,  260,  261,  262,  263,  264,  265,
  266,  267,  268,  269,   -1,   -1,  272,   -1,   -1,   -1,
   -1,   -1,  125,   -1,   -1,   -1,  260,  261,  262,  263,
  264,  265,  266,  267,  268,  269,  125,   -1,  272,  260,
  261,  262,  263,  264,  265,  266,  267,  268,  269,  125,
   -1,  272,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  260,
  261,  262,  263,  264,  265,  266,  267,  268,  269,   -1,
   -1,  272,  273,  274,  260,  261,  262,  263,   -1,  265,
  266,  267,  268,  269,   -1,   -1,  272,   -1,   -1,   -1,
   -1,  260,  261,  262,  263,  264,  265,  266,  267,  268,
  269,   -1,   -1,  272,  273,  274,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  260,  261,
  262,  263,  264,  265,  266,  267,  268,  269,   -1,   -1,
  272,  273,  274,  275,  276,  277,  278,   -1,  260,  261,
  262,  263,  264,  265,  266,  267,  268,  269,   -1,   -1,
  272,  273,  274,  275,  276,  277,  278,  260,  261,  262,
  263,   -1,  265,  266,  267,  268,   -1,   -1,   -1,  272,
   -1,  260,  261,  262,  263,   -1,  265,  266,  267,  268,
   -1,   -1,   -1,  272,  260,  261,  262,  263,   -1,  265,
  266,  267,  268,   40,   41,   42,  272,   -1,   -1,   46,
   47,  260,  261,  262,  263,   -1,  265,  266,  267,  268,
   57,   58,   -1,  272,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   68,   69,   70,   71,   72,   73,   74,   75,   76,
   77,   78,   79,   80,   81,   -1,   83,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  125,   -1,
   -1,   -1,   -1,   -1,   -1,  132,
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
"sentencia : IF '(' expresion ')' '{' sentencias '}' ELSE '{' sentencias '}'",
"sentencia : IF '(' expresion ')' sentencia ELSE sentencia",
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
"expresion : '!' expresion",
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
