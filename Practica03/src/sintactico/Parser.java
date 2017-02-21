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
    0,    0,    1,    1,    1,    1,    1,    1,    4,    4,
    6,    6,    7,    7,    2,    2,    2,    2,    2,    5,
    5,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    8,    8,    9,    9,    9,
};
final static short yylen[] = {                            2,
    2,    1,    4,    2,    7,    7,   11,    3,    5,    1,
    2,    2,    2,    0,    1,    4,    7,    2,    5,    3,
    1,    1,    1,    1,    1,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    2,    3,
    3,    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    0,   43,   44,   45,    0,   42,    0,    2,
    0,    0,    0,    0,   21,    0,    0,    0,    0,    1,
    0,    4,    0,    0,    0,    0,    8,    0,   23,   24,
   25,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   41,    0,   20,   39,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   12,    0,   10,   11,    3,    0,    0,   40,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   27,   28,
   30,    0,    0,    9,    0,    0,    0,    0,    0,    5,
    0,   17,    0,    0,    0,    7,
};
final static short yydgoto[] = {                          9,
   10,   11,   34,   12,   16,   38,    0,   35,   14,
};
final static short yysindex[] = {                       151,
 -235,  -13,  -12,    0,    0,    0,  -92,    0,  151,    0,
  -28,  -25,  -41,  -82,    0,  -23,  107,  107, -131,    0,
  107,    0, -222, -230, -209,   21,    0, -185,    0,    0,
    0,  107,  107,  -30,   21,    2,   23,  -99,    9,   -7,
    0,   -5,    0,    0,   16,  107,  107,  107,  107,  107,
  107,  107,  107,  107,  107,  107,  107,  107,  -27,  -21,
    0, -177,    0,    0,    0,   10, -159,    0,   30,   30,
  138,  138,  138,  138,  138,  138,   86,   86,    0,    0,
    0,  151,  151,    0, -143,   21, -107,  -70,   28,    0,
 -148,    0,   -1,  151,  -55,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   65,    0,  -45,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  103,    0,    0,    0,    0,
    0,    0,    0,    0,  -37,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  117,    0,    0,  -39,   25,
  -22,   50,   58,   70,   78,   84,   38,   44,    0,    0,
    0,    0,    0,    0,    0,  125,    0,    0,    0,    0,
    1,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                        11,
   20,   22,  389,   89,    0,    0,    0,  367,    0,
};
final static int YYTABLESIZE=462;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         22,
    6,   37,   24,   22,   22,   22,   58,   22,   25,   22,
   59,   56,   54,   15,   55,   15,   57,   90,   33,   37,
   28,   22,   22,   15,   22,   62,   17,   18,   20,   53,
   19,   52,   21,   22,   40,   27,   33,   33,   58,   33,
   37,   41,   60,   56,   54,   58,   55,   42,   57,   23,
   56,   54,   58,   55,   91,   57,   68,   56,   54,   63,
   55,   53,   57,   52,   24,   38,   58,   65,   53,   96,
   52,   56,   54,   43,   55,   53,   57,   52,   26,   15,
   26,   61,   26,   38,   29,   66,   29,   67,   29,   53,
   34,   52,   87,   88,   84,   82,   26,   26,   35,   26,
   85,   83,   29,   29,   95,   29,   20,   20,   34,   34,
   36,   34,    8,   89,   20,   93,   35,   35,   31,   35,
   92,   94,   58,   10,   32,    6,   64,   56,   36,   36,
    0,   36,   57,    4,    5,    6,   31,   31,    0,   31,
    8,    0,   32,   32,    0,   32,   33,    0,    0,    0,
    0,   32,    0,    1,    2,    3,    0,    4,    5,    6,
    7,   18,    0,   18,    8,    4,    5,    6,    7,    0,
    0,    0,    8,    0,   58,   16,    0,   16,    0,   56,
   54,    0,   55,   19,   57,   19,    0,    0,    0,    8,
    1,    2,    3,    0,    4,    5,    6,    7,    0,    0,
    0,    8,    0,    0,    0,    1,    2,    3,    0,    4,
    5,    6,    7,    0,    0,    0,    8,    0,    0,   15,
   15,   15,   15,    0,    0,    0,   15,   18,    0,    0,
    0,    0,    0,   37,   37,   22,   22,   22,   22,   22,
   22,   16,   46,   47,   48,   49,   50,   51,    0,   19,
   33,   33,   33,   33,   33,   33,    0,    0,    0,    0,
    0,    6,    6,    6,    0,    6,    6,    6,    6,    0,
    0,    0,    6,    0,   46,   47,   48,   49,   50,   51,
    0,   46,   47,   48,   49,   50,   51,    0,   46,   47,
   48,   49,   50,   51,    0,    0,    0,   38,   38,    0,
    0,    0,    0,    0,   48,   49,   50,   51,    0,    0,
   26,   26,   26,   26,   26,   26,   29,   29,   29,   29,
   29,   29,   34,   34,   34,   34,   34,   34,    0,    0,
   35,   35,   35,   35,   35,   35,    0,    0,    0,    0,
    0,    0,   36,   36,   36,   36,   36,   36,    0,    0,
   31,   31,   31,   31,   31,   31,   32,   32,   32,   32,
   32,   32,    0,   29,   30,   31,   13,   18,   18,   18,
   18,    0,    0,    0,   18,   13,    0,    0,    8,    0,
   26,   16,   16,   16,   16,   13,    0,    0,   16,   19,
   19,   19,   19,    0,    0,    0,   19,    0,    0,    0,
    0,    0,    0,    0,   13,    0,   36,    0,    0,   39,
    0,    1,    2,    3,    0,    4,    5,    6,    7,    0,
   44,   45,    8,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   86,   69,   70,   71,   72,   73,   74,
   75,   76,   77,   78,   79,   80,   81,    0,   13,   13,
    0,    0,    0,   13,   13,    0,    0,    0,    0,    0,
   13,   13,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
    0,   41,   44,   41,   42,   43,   37,   45,   91,   47,
   41,   42,   43,   59,   45,   61,   47,  125,   41,   59,
   44,   59,   60,  259,   62,  125,   40,   40,    9,   60,
  123,   62,   61,   59,  257,   59,   59,   60,   37,   62,
   19,  272,   41,   42,   43,   37,   45,  257,   47,   91,
   42,   43,   37,   45,  125,   47,   41,   42,   43,   38,
   45,   60,   47,   62,   44,   41,   37,   59,   60,  125,
   62,   42,   43,  259,   45,   60,   47,   62,   41,  125,
   43,   59,   45,   59,   41,   93,   43,   93,   45,   60,
   41,   62,   82,   83,  272,  123,   59,   60,   41,   62,
   91,  123,   59,   60,   94,   62,   87,   88,   59,   60,
   41,   62,  272,  257,   95,  264,   59,   60,   41,   62,
   93,  123,   37,   59,   41,  125,   38,   42,   59,   60,
   -1,   62,   47,  265,  266,  267,   59,   60,   -1,   62,
  272,   -1,   59,   60,   -1,   62,   40,   -1,   -1,   -1,
   -1,   45,   -1,  261,  262,  263,   -1,  265,  266,  267,
  268,   59,   -1,   61,  272,  265,  266,  267,  268,   -1,
   -1,   -1,  272,   -1,   37,   59,   -1,   61,   -1,   42,
   43,   -1,   45,   59,   47,   61,   -1,   -1,   -1,  272,
  261,  262,  263,   -1,  265,  266,  267,  268,   -1,   -1,
   -1,  272,   -1,   -1,   -1,  261,  262,  263,   -1,  265,
  266,  267,  268,   -1,   -1,   -1,  272,   -1,   -1,  265,
  266,  267,  268,   -1,   -1,   -1,  272,  125,   -1,   -1,
   -1,   -1,   -1,  273,  274,  273,  274,  275,  276,  277,
  278,  125,  273,  274,  275,  276,  277,  278,   -1,  125,
  273,  274,  275,  276,  277,  278,   -1,   -1,   -1,   -1,
   -1,  261,  262,  263,   -1,  265,  266,  267,  268,   -1,
   -1,   -1,  272,   -1,  273,  274,  275,  276,  277,  278,
   -1,  273,  274,  275,  276,  277,  278,   -1,  273,  274,
  275,  276,  277,  278,   -1,   -1,   -1,  273,  274,   -1,
   -1,   -1,   -1,   -1,  275,  276,  277,  278,   -1,   -1,
  273,  274,  275,  276,  277,  278,  273,  274,  275,  276,
  277,  278,  273,  274,  275,  276,  277,  278,   -1,   -1,
  273,  274,  275,  276,  277,  278,   -1,   -1,   -1,   -1,
   -1,   -1,  273,  274,  275,  276,  277,  278,   -1,   -1,
  273,  274,  275,  276,  277,  278,  273,  274,  275,  276,
  277,  278,   -1,  257,  258,  259,    0,  265,  266,  267,
  268,   -1,   -1,   -1,  272,    9,   -1,   -1,  272,   -1,
   14,  265,  266,  267,  268,   19,   -1,   -1,  272,  265,
  266,  267,  268,   -1,   -1,   -1,  272,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   38,   -1,   18,   -1,   -1,   21,
   -1,  261,  262,  263,   -1,  265,  266,  267,  268,   -1,
   32,   33,  272,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   67,   46,   47,   48,   49,   50,   51,
   52,   53,   54,   55,   56,   57,   58,   -1,   82,   83,
   -1,   -1,   -1,   87,   88,   -1,   -1,   -1,   -1,   -1,
   94,   95,
};
}
final static short YYFINAL=9;
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
"$accept : sentencias",
"sentencias : sentencias sentencia",
"sentencias : sentencia",
"sentencia : variable '=' expresion ';'",
"sentencia : estructura ';'",
"sentencia : WHILE '(' expresion ')' '{' sentencias '}'",
"sentencia : IF '(' expresion ')' '{' sentencias '}'",
"sentencia : IF '(' expresion ')' '{' sentencias '}' ELSE '{' sentencias '}'",
"sentencia : WRITE expresiones ';'",
"estructura : STRUCT '{' campos '}' ID",
"estructura : variable",
"campos : campos estructura",
"campos : variable ';'",
"variableFuncion : variableFuncion variable",
"variableFuncion :",
"variable : identificador",
"variable : identificador '[' CTE_ENTERA ']'",
"variable : identificador '[' CTE_ENTERA ']' '[' CTE_ENTERA ']'",
"variable : tipoSimple identificador",
"variable : tipoSimple '[' CTE_ENTERA ']' identificador",
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
"expresion : '-' expresion",
"expresion : '(' expresion ')'",
"identificador : identificador ',' ID",
"identificador : ID",
"tipoSimple : INT",
"tipoSimple : DOUBLE",
"tipoSimple : CHAR",
};

//#line 97 "../../src/sintactico/sintactico.y"

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
//#line 389 "Parser.java"
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
