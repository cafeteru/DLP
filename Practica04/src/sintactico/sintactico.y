%{
// * Declaraciones de c�digo Java
// * Se sit�an al comienzo del archivo generado
// * El package lo a�ade yacc si utilizamos la opci�n -Jpackage
import lexico.Lexico;
import java.io.Reader;
import java.util.*;

import ast.*;
import ast.definiciones.*;
import ast.expresiones.*;
import ast.sentencias.*;
import ast.tipos.*;
%}

// * Declaraciones Yacc
%token CTE_ENTERA CTE_REAL CTE_CARACTER
%token READ WRITE WHILE IF ELSE 
%token INT DOUBLE CHAR STRUCT 
%token RETURN VOID MAIN ID 
%token Y O MAYORIGUALQUE MENORIGUALQUE DISTINTO IGUALDAD

// M�s arriba, menos precedencia
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

%%
// * Gram�tica y acciones Yacc
programa: definiciones VOID MAIN '(' ')' '{' declaraciones sentencias '}'			{ 	List<Definicion> aux = (List<Definicion>)$1;
																						
																						aux.add(new DefFuncion(lexico.getLine(), lexico.getColumn(), "main",
																							new TipoFuncion(new ArrayList(), TipoVoid.getInstancia()), 
																							(List<DefVariable>)$7, (List<Sentencia>)$8));
																						
																						this.ast = new Programa(lexico.getLine(), lexico.getColumn(), aux); } ;

definiciones: definiciones definicion     											{	$$ = $1; for(Definicion aux : (List<Definicion>)$2) 
																									((List<Definicion>)$$).add(aux); }
       |/*vacio*/																	{	$$ = new ArrayList();}
       ;
  
definicion: tipoSimple ID '(' parametros ')' '{' declaraciones cuerpoDefinicion '}' /*funcion*/  	{ 	List<Definicion> lista = new ArrayList();
																										lista.add(new DefFuncion(lexico.getLine(), lexico.getColumn(), (String) $2,
																											new TipoFuncion((List<DefVariable>)$4, (Tipo)$1), 
																											(List<DefVariable>)$7, (List<Sentencia>)$8));
																										$$ = lista; }
																										
	  | VOID ID '(' parametros ')' '{' declaraciones cuerpoDefinicion '}' /*procedimiento*/			{	List<Definicion> lista = new ArrayList();
																										lista.add(new DefFuncion(lexico.getLine(), lexico.getColumn(),  (String) $2,
																										new TipoFuncion((List<DefVariable>)$4, TipoVoid.getInstancia()), 
																										(List<DefVariable>)$7, (List<Sentencia>)$8));
																										$$ = lista;}
																										
	  | declaracionVariable ';'																		{ $$ = $1;  }
	  ;	
	  
declaraciones: declaraciones declaracionVariable ';'	{ $$ = $1;
															for(DefVariable aux : (List<DefVariable>)$2) 
																((List<DefVariable>)$$).add(aux); }
			| /*vacio*/									{ $$ = new ArrayList<DefVariable>();}
			;
	   
parametros: parametros ',' tipoSimple ID		{ $$ = $1; ((List<DefVariable>)$$).add(new DefVariable(lexico.getLine(), 
		  										  lexico.getColumn(), (String)$2, (Tipo)$1)); }
		  | tipoSimple ID						{ $$ = new ArrayList<DefVariable>(); ((List<DefVariable>)$$).add(new DefVariable(lexico.getLine(), 
		  										  lexico.getColumn(), (String)$2, (Tipo)$1));  }
		  | /*vacio*/ 							{ $$ = new ArrayList<DefVariable>();}
		  ;
		 
cuerpoDefinicion: sentencias					{ $$ = $1;}
	  | /*vacio*/								{ $$ = new ArrayList<Sentencia>();}
	  ;

sentencias: sentencias sentencia 				{ $$ = $1;((List<Sentencia>)$$).add((Sentencia)$2); }
	     | sentencia 							{ $$ = new ArrayList<Sentencia>(); ((List<Sentencia>)$$).add((Sentencia)$1); }
		 ;
		 	
sentencia: expresion '=' expresion ';' // Asignaci�n							{ $$ = new Asignacion(lexico.getLine(), lexico.getColumn(), (Expresion)$1, (Expresion)$3); }			
		 | WHILE '(' expresion ')' '{' sentencias '}'							{ $$ = new SentenciaWhile(lexico.getLine(), lexico.getColumn(), (Expresion)$3, (List<Sentencia>)$6);}
		 | IF '(' expresion ')' cuerpoCondicional %prec MENORQUEELSE			{ $$ = new SentenciaIf(lexico.getLine(), lexico.getColumn(), (Expresion)$3, (List<Sentencia>)$5, new ArrayList()); }
		 | IF '(' expresion ')' cuerpoCondicional ELSE cuerpoCondicional 		{ $$ = new SentenciaIf(lexico.getLine(), lexico.getColumn(), (Expresion)$3, (List<Sentencia>)$5, (List<Sentencia>)$7);}
		 | WRITE expresiones ';'												{ $$ = new Escritura(lexico.getLine(), lexico.getColumn(),(List<Expresion>)$2);}
		 | READ expresiones ';'													{ $$ = new Lectura(lexico.getLine(), lexico.getColumn(),(List<Expresion>)$2); }
		 | invocacion ';'														{ $$ = $1; }
		 | RETURN expresion ';'													{ $$ = new Return(lexico.getLine(), lexico.getColumn(), (Expresion)$2);}
         ;
         
cuerpoCondicional: '{' sentencias '}'				{ $$ = $2; }
		         | sentencia 						{ $$ = new ArrayList<Sentencia>(); ((List<Sentencia>)$$).add((Sentencia)$1);  }
		         ;
		           
// Campos dentro de un Struct		   
campos: campos declaracionVariable ';'				{ $$ = $1;
													for(DefVariable aux : (List<DefVariable>)$2) 
														((List<DefVariable>)$$).add(aux); }
	  | declaracionVariable ';'						{ $$ = $1;  }
      ;
      
declaracionVariable: tipoSimple identificador				{ 	List<DefVariable> variables = new ArrayList();
																for(String aux: (List<String>)$2){
																	variables.add(new DefVariable(lexico.getLine(), lexico.getColumn(), aux, (Tipo)$1));
															   }	 
															   $$ = variables; 
															 }
															 
		           | tipoSimple indices identificador 		{	List<Integer> indices = (List<Integer>)$2;
		           												TipoArray tipo = new TipoArray(indices.get(0), (Tipo)$1);
		           												for(int i = 1; i < indices.size(); i++){
		           													tipo = new TipoArray(indices.get(i), tipo);
		           												}	           												
		           												
		           												List<DefVariable> variables = new ArrayList();
																for(String id: (List<String>)$3){
																	variables.add(new DefVariable(lexico.getLine(), lexico.getColumn(), id, tipo));
															    }	 
															    $$ = variables;  }
		           | STRUCT '{' campos '}' identificador    { 	List<CampoRegistro> registrosStruct = new ArrayList();
		           												for(DefVariable var : (List<DefVariable>) $3){
		           													registrosStruct.add(new CampoRegistro(var.getNombre(), var.getTipo()));
		           												}
		           												
		           												TipoRegistro registro = new TipoRegistro(registrosStruct);
		           												          												
		           											    List<DefVariable> variables = new ArrayList();
																for(String aux: (List<String>)$5){
																	variables.add(new DefVariable(lexico.getLine(), lexico.getColumn(), aux, registro));
															    }	 
															    $$ = variables; 															 
															 }
		           ;

expresiones: expresiones ',' expresion				{ $$ = $1; ((List<Expresion>)$$).add((Expresion)$3); }
		   | expresion								{ $$ = new ArrayList<Expresion>(); ((List<Expresion>)$$).add((Expresion)$1);  }
		   ;	   
	  		     
expresion: ID										{ $$ = new Variable(lexico.getLine(), lexico.getColumn(), (String) $1); }
		 | CTE_ENTERA                               { $$ = new LiteralEntero(lexico.getLine(), lexico.getColumn(), (Integer) $1);}
         | CTE_REAL 								{ $$ = new LiteralReal(lexico.getLine(), lexico.getColumn(), (Double) $1);}
         | CTE_CARACTER 							{ $$ = new LiteralCaracter(lexico.getLine(), lexico.getColumn(), (char) $1);}
         | expresion '+' expresion 					{ $$ = new Aritmetica(lexico.getLine(), lexico.getColumn(), (Expresion) $1, "+", (Expresion) $3);}
         | expresion '*' expresion  				{ $$ = new Aritmetica(lexico.getLine(), lexico.getColumn(), (Expresion) $1, "*", (Expresion) $3);}
         | expresion '/' expresion  				{ $$ = new Aritmetica(lexico.getLine(), lexico.getColumn(), (Expresion) $1, "/", (Expresion) $3);}
         | expresion '-' expresion 					{ $$ = new Aritmetica(lexico.getLine(), lexico.getColumn(), (Expresion) $1, "-", (Expresion) $3);}
         | expresion '%' expresion 					{ $$ = new Aritmetica(lexico.getLine(), lexico.getColumn(), (Expresion) $1, "%", (Expresion) $3);}
         | expresion '>' expresion 					{ $$ = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) $1, ">", (Expresion) $3);}
         | expresion '<' expresion  				{ $$ = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) $1, "<", (Expresion) $3);}
         | expresion MAYORIGUALQUE expresion  		{ $$ = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) $1, ">=", (Expresion) $3);}
         | expresion MENORIGUALQUE expresion 		{ $$ = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) $1, "<=", (Expresion) $3);}
         | expresion DISTINTO expresion 			{ $$ = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) $1, "!=", (Expresion) $3);}
         | expresion IGUALDAD expresion  			{ $$ = new Comparacion(lexico.getLine(), lexico.getColumn(), (Expresion) $1, "==", (Expresion) $3);}
         | expresion Y expresion  					{ $$ = new Logica(lexico.getLine(), lexico.getColumn(), (Expresion) $1, "&&", (Expresion) $3);}
         | expresion O expresion 					{ $$ = new Logica(lexico.getLine(), lexico.getColumn(), (Expresion) $1, "||", (Expresion) $3);}
         | '!' expresion %prec NEGACION  			{ $$ = new Negacion(lexico.getLine(), lexico.getColumn(),  "!", (Expresion) $1);}
         | '-' expresion %prec MENOS_UNARIO  		{ $$ = new MenosUnario(lexico.getLine(), lexico.getColumn(),  "-",(Expresion) $1);}
         | ID '.' expresion 					    { $$ = new AccesoCampo(lexico.getLine(), lexico.getColumn(), (String) $1, (Expresion) $3);}
         | '(' expresion ')' 						{ $$ = $2;}
         | '(' tipoSimple ')' expresion 			{ $$ = new Cast(lexico.getLine(), lexico.getColumn(), (Tipo) $2, (Expresion) $4);}
         | invocacion						 		{ $$ = $1;}							   
         ;
         
invocacion: ID '(' argumentosLlamada ')' 		{ $$ = new Invocacion(lexico.getLine(), lexico.getColumn(), 
											      new Variable(lexico.getLine(), lexico.getColumn(), 
											      (String)$1),(List<Expresion>)$3); }
		   ;
			     
argumentosLlamada: expresiones 					{ $$ = $1;}
				 | /*vacio*/  					{ $$ = new ArrayList<Expresion>();}
				 ;							  
				
         
indices: indices '[' CTE_ENTERA ']'				{ $$ = $1; ((List<Integer>)$$).add((int)$3); }
	   | '[' CTE_ENTERA ']'                 	{ $$ = new ArrayList<Integer>(); ((List<Integer>)$$).add((Integer)$2);  }
	   ;
         
identificador: identificador ',' ID 			{ $$ = $1; ((List<String>)$$).add((String)$3); }
		     | ID								{$$ = new ArrayList<String>(); ((List<String>)$$).add((String)$1);  }	
		     ;
	
tipoSimple: INT									{ $$ = TipoEntero.getInstancia(); }
	      | DOUBLE								{ $$ = TipoReal.getInstancia(); }
	      | CHAR 								{ $$ = TipoCaracter.getInstancia(); }
	      ;
%%

// * C�digo Java
// * Se crea una clase "Parser", lo que aqu� ubiquemos ser�:
//	- Atributos, si son variables
//	- M�todos, si son funciones
//   de la clase "Parser"

private NodoAST ast;
public NodoAST getAST(){ return this.ast; }

// * Estamos obligados a implementar:
//	int yylex()
//	void yyerror(String)

// * Referencia al analizador l�xico
private Lexico lexico;

// * Llamada al analizador l�xico
private int yylex () {
    int token=0;
    try { 
	token=lexico.yylex(); 
	this.yyval = lexico.getYylval();
    } catch(Throwable e) {
	    System.err.println ("Error L�xico en l�nea " + lexico.getLine()+
		" y columna "+lexico.getColumn()+":\n\t"+e); 
    }
    return token;
}

// * Manejo de Errores Sint�cticos
public void yyerror (String error) {
    System.err.println ("Error Sint�ctico en l�nea " + lexico.getLine()+
		" y columna "+lexico.getColumn()+":\n\t"+error);
}

// * Constructor del Sint�ctico
public Parser(Lexico lexico) {
	this.lexico = lexico;
}
