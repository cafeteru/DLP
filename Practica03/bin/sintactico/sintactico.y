%{
// * Declaraciones de código Java
// * Se sitúan al comienzo del archivo generado
// * El package lo añade yacc si utilizamos la opción -Jpackage
import lexico.Lexico;
import java.io.Reader;
import java.util.*;
import ast.*;
%}

// * Declaraciones Yacc
%token CTE_ENTERA CTE_REAL CTE_CARACTER
%token READ WRITE WHILE IF ELSE 
%token INT DOUBLE CHAR STRUCT 
%token RETURN VOID MAIN ID 
%token Y O MAYORIGUALQUE MENORIGUALQUE DISTINTO IGUALDAD

// Más arriba, menos precedencia
%right '='
%left Y O '!'
%left '>' MAYORIGUALQUE MENORIGUALQUE '<' DISTINTO IGUALDAD
%left '+' '-'
%left '*' '/' '%'
%right MENOS_UNARIO
%right NEGACION
%nonassoc '[' ']'
%left '.'
%nonassoc MENORQUEELSE
%nonassoc ELSE
%nonassoc '(' ')'
%nonassoc '{' '}'

%%
// * Gramática y acciones Yacc
programa: definiciones VOID MAIN '(' ')' '{' declaraciones sentencias '}'			

definiciones: definiciones definicion     											
       |/*vacio*/																	
       ;
  
definicion: tipoSimple ID '(' parametros ')' '{' declaraciones cuerpoDefinicion '}' /*funcion*/  	
																								
	  | VOID ID '(' parametros ')' '{' declaraciones cuerpoDefinicion '}' /*procedimiento*/			
	  | declaracionVariable ';'
	  ;	
	  
declaraciones: declaraciones declaracionVariable ';'
			| /*vacio*/
			;
	   
parametros: parametros ',' tipoSimple ID
		  | tipoSimple ID
		  | /*vacio*/
		  ;
		 
cuerpoDefinicion: sentencias
	  | /*vacio*/
	  ;

sentencias: sentencias sentencia
	     | sentencia
		 ;
		 	
sentencia: expresion '=' expresion ';' // Asignación
		 | WHILE '(' expresion ')' '{' sentencias '}'
		 | IF '(' expresion ')' cuerpoCondicional %prec MENORQUEELSE
		 | IF '(' expresion ')' cuerpoCondicional ELSE cuerpoCondicional 
		 | WRITE expresiones ';'
		 | READ expresiones ';'
		 | llamadaFuncion ';'
		 | RETURN expresion ';'
         ;
         
cuerpoCondicional: '{' sentencias '}'
		         | sentencia
		         ;
		
declaracionVariable: tipoSimple identificador
		           | tipoSimple indices identificador 
		           | STRUCT '{' campos '}' identificador
		           ;
		           
// Campos dentro de un Struct		   
campos: campos declaracionVariable ';'
	  | declaracionVariable ';'
      ;

expresiones: expresiones ',' expresion
		   | expresion
		   ;	   
	  		     
expresion: ID
		 | CTE_ENTERA
         | CTE_REAL
         | CTE_CARACTER
         | expresion '+' expresion
         | expresion '*' expresion 
         | expresion '/' expresion 
         | expresion '-' expresion
         | expresion '%' expresion
         | expresion '>' expresion
         | expresion '<' expresion 
         | expresion MAYORIGUALQUE expresion 
         | expresion MENORIGUALQUE expresion
         | expresion DISTINTO expresion
         | expresion IGUALDAD expresion 
         | expresion Y expresion 
         | expresion O expresion
         | expresion '.' expresion
         | '-' expresion %prec MENOS_UNARIO 
         | '!' expresion %prec NEGACION 
         | '(' expresion ')'
         | '(' tipoSimple ')' expresion
         | llamadaFuncion
         | expresion '[' expresion ']'					
         ;
         
llamadaFuncion: ID '(' argumentosLlamada ')'
			  ;
			  
argumentosLlamada: expresiones
				 | /*vacio*/ 
				 ;
         
indices: indices '[' CTE_ENTERA ']'				
	   | '[' CTE_ENTERA ']'                 	
	   ;
         
identificador: identificador ',' ID 			
		     | ID								
		     ;
	
tipoSimple: INT									
	      | DOUBLE								
	      | CHAR 								
	      ;
%%

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
	this.yyval = lexico.getYylval();
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
