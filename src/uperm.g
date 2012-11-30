/*
 * uperm.g
 *
 * The uPerm syntax and AST builder, an ANTLR 2.3 grammar.
 */

/*
 * Copyright (c) 2008 Permusoft Corporation. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 * provided that the following conditions are met:
 * 
 * 1) Redistributions of source code must retain the above copyright notice, this list of conditions and
 * the following disclaimer.
 *
 * 2) Redistributions in binary form must reproduce the above copyright notice, this list of conditions
 * and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY
 * WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */


class uPermLexer extends Lexer;
   options {
      testLiterals = true;
      k = 2;
      exportVocab = uPermVocabulary;
   }

// Atoms
protected LETTER : ('a' .. 'z' | 'A' .. 'Z' | '_');
protected DIGIT : '0' .. '9';
WS : (' ' | '\t' | '\n' { newline(); } | '\r') { $setType (Token.SKIP); } ;

// Arithmetic
PLUS : '+';
MINUS : '-'; // Binary subtraction and unary additive inverse
MULT : '*';
DIVD : '/';
MOD : '%';
MINVR : '~'; // Unary multiplicative inverse
AND : "&&";
OR : "||";
NOT: '!';    // Boolean inverse

// Comparators
EQ : "==";
NE : "!=";
LT : '<';
GT : '>';
LE : "<=";
GE : ">=";

// Delimiters
BACKSL : '\\';
LPAREN : '(';
RPAREN : ')';
LBRACK : '[';
RBRACK : ']';
LBRACE : '{';
RBRACE : '}';

// Punctuation
SEMI : ';';
COMMA : ',';
COLON : ':';
ASSIGN : '=';

// Literals, identifiers
INTEGER : (DIGIT)+;
ID : LETTER (LETTER | DIGIT)* ;

STRING : '"'!  ( ~('"' | '\n') | ( '"'! '"') )* '"'! ; // '" clean up vim colors

COMMENT : "$" ((~'\n'))* '\n' { newline(); $setType(Token.SKIP); } ;


class uPermParser extends Parser;

   options {
      buildAST = true;
      k = 2;
      exportVocab = uPermVocabulary;
   }

   tokens {
      STATEMENT;
      STATEMENTS;
      BLOCK;
      ADD_INVS; // additive inverse
      MULT_INVS; // multiplicative inverse
      FUNC_CALL;
      EXPR_LIST;
      PERM_CYCLE;
      PERM_IMAGE;
      PERM_ID;
      LIST;
      SLICE;
      DUMMY; // used for slices
      //DEBUG;
   }

file : statements EOF! ;

statements : (statement)*
   {
      #statements = #([STATEMENTS, "STATEMENTS"], statements);
   }
;

statement : ( assignment_statement
            | function_declaration
            | function_call_statement
            | print_statement
            | if_statement
            | while_statement
            | for_statement
            | return_statement
            | break_statement
            | continue_statement
            | import_statement
            | block
            //| debug_statement
            )
   {
      #statement = #([STATEMENT, "STATEMENT"], statement);
   }
;


/*
debug_statement : COLON! ("symtab"^ | "control"^) SEMI!

   {
      #debug_statement = #([DEBUG, "DEBUG"], debug_statement);
   }
;
*/

return_statement : "return"^ (expression)? SEMI! ;

break_statement : "break"^ SEMI! ;

continue_statement : "continue"^ SEMI! ;


block : LBRACE! statements RBRACE!
   { #block = #([BLOCK, "BLOCK"], block); }
;

assignment_statement : (assignment | local_assignment) SEMI!;

assignment : identifier ASSIGN^ expression;

local_assignment : "local"^ identifier ASSIGN! expression;

print_statement : "print"^ (expression)? SEMI!;

if_statement : "if"^ LPAREN! expression RPAREN! statement ( options { greedy=true; }: "else"! statement)? ;

while_statement : "while"^ LPAREN! expression RPAREN! statement ;

for_statement : "for"^ LPAREN! assignment SEMI! expression SEMI! (assignment | function_call) RPAREN! statement;

function_declaration : "fun"^ identifier block ;

function_call : identifier LPAREN! expr_list RPAREN!
   {
      #function_call = #([FUNC_CALL, "FUNC_CALL"], function_call);
   }
;

function_call_statement : function_call SEMI! ;

import_statement : "import"^ STRING SEMI! ;


expr_list : (expression (COMMA! expression)* )?
   {
      #expr_list = #([EXPR_LIST, "EXPR_LIST"], expr_list);
   }
;


expression : bool;
bool  : join (OR^ join)* ;
join  : equl (AND^ equl)* ;
equl  : rel ((EQ^ | NE^) rel)* ;
rel   : arith ((LT^ | GT^ | LE^ | GE^) arith)* ;
arith : mult ((PLUS^ | MINUS^) mult)* ;
mult  : unary ((MULT^ | DIVD^ | MOD^) unary)* ;
unary : m:MINUS^ atom { #m.setType (ADD_INVS); }
      | i:MINVR^ atom { #i.setType (MULT_INVS); }
      | NOT^ atom
      | "sizeof"^ atom
      | atom
;

atom : lparen_predicate
     | permutation_image_literal
     | permutation_image_identity_literal
     | function_call
     | list
     | slice
     | identifier
     | integer_literal
     | string_literal
     | boolean_literal
;


// Handles permutation cycles (including the identity) and parenthesized expressions
lparen_predicate : (LPAREN (integer_literal | identifier | slice | function_call)
                           (integer_literal | identifier | slice | function_call) ) => permutation_cycle_literal
                 | (LPAREN RPAREN) => permutation_cycle_identity_literal
                 | parenthesized_expression
;

parenthesized_expression : LPAREN! expression RPAREN!;
  
permutation_cycle_literal : LPAREN! point_list RPAREN!
   { #permutation_cycle_literal = #([PERM_CYCLE, "PERM_CYCLE"], permutation_cycle_literal); }
;

permutation_image_literal : BACKSL! point_list BACKSL!
   { #permutation_image_literal = #([PERM_IMAGE, "PERM_IMAGE"], permutation_image_literal); }
;

permutation_cycle_identity_literal : LPAREN! RPAREN!
   { #permutation_cycle_identity_literal = #([PERM_ID, "PERM_ID"], permutation_cycle_identity_literal); }
;

permutation_image_identity_literal : BACKSL! BACKSL!
   { #permutation_image_identity_literal = #([PERM_ID, "PERM_ID"], permutation_image_identity_literal); }
;


// We must allow not only integer literals, but also variables and function calls inside of
// permutation literals. This allows for run-time creation of permutations. But we stop here
// and disallow arbitrary arithmetic inside of a cyclic form.
point_list : (integer_literal | identifier | slice | function_call)
             (integer_literal | identifier | slice | function_call)+
;


list : LBRACK! expr_list RBRACK!
   {
      #list = #([LIST, "LIST"], list);
   }
;


slice! : id:identifier LBRACK! (l:expression)? (c:COLON (r:expression)?)? RBRACK!
   {
      if (#l == null) #l = #([DUMMY, "DUMMY"]);
      if (#c == null) #c = #([DUMMY, "DUMMY"]);
      if (#r == null) #r = #([DUMMY, "DUMMY"]);
    
      #slice = #([SLICE, "SLICE"], #id, #l, #c, #r);
   }
;


integer_literal : INTEGER ;

string_literal : STRING ;

boolean_literal : ("true" | "false");

identifier : ID ;



{
   import java.lang.Integer;
}

class uPermWalker extends TreeParser;
options {
   importVocab = uPermVocabulary;
}

{
   final uPermInterpreter upIntr = new uPermInterpreter(this);
}

program :
   {
      uPermControl c = null;
   }
   (c=statement)*
;


statement returns [uPermControl c]
   {
      uPermType a = null, b = null, r = null;
      c = upIntr.control();
   }

   : #(STATEMENT s:.                       { c = upIntr.execute (#s); } )
   | #(ASSIGN aid:ID a=expression (b=expression)?
                                           { upIntr.assign (aid.getText(), a, b); c=upIntr.control(); } )

   // this rule also handles permutation image calls, which can be called as statement but have no side effects
   | #(FUNC_CALL fname_:ID args:.          { upIntr.executeFunctionCall (fname_.getText(), #args); c=upIntr.control(); } )
   | #("local" aid_:ID a=expression        { upIntr.bindLocal (aid_.getText(), a); c=upIntr.control(); } )

   | #(BLOCK                               { upIntr.enterBlock(); c=upIntr.control(); }
         c=statement                       { }
      )                                    { c = upIntr.exitBlock(); }

   | #(STATEMENTS (c=statement)*           { } )
   | #("if" cond:. actn:. (alt:.)?         { upIntr.executeIfStatement (#cond, #actn, #alt); c=upIntr.control(); } )
   | #("for" init:. fcond:. iter:. fstmt:. { upIntr.executeForLoop (#init, #fcond, #iter, #fstmt); c=upIntr.control(); } )
   | #("while" wcond:. stmt:.              { upIntr.executeWhileLoop (#wcond, #stmt); c=upIntr.control(); } )
   | #("print" (a=expression)?             { upIntr.printExpression(a); c=upIntr.control(); } )
   | #("fun" f:ID body:.                   { upIntr.bindFunction (f.getText(), #body); c=upIntr.control(); } )
   | #("return" (a=expression)?            { c = upIntr.setReturnValue(a); } )
   | #("break"                             { c = upIntr.constrainExecution(); } )
   | #("continue"                          { c = upIntr.loopConstrainExecution(); } )
   | #("import" a=expression               { upIntr.importFile (a); c=upIntr.control(); } )

/*
   | #(DEBUG ( #("symtab"                  { System.err.println (upIntr.currentSymbolTableString()); } )
             | #("control"                 { System.err.println (upIntr.control()); } )
             //| #("add more"                { } )
             )
      )
*/

;


expression returns [uPermType r]
   {
      r = null;
      uPermType a = null, b = null;
   }

   // We order the expressions from most to least common, roughly.
   // It should speed up processing, if only a little.
   : (id:ID                                { r = upIntr.valueOf (id.getText()); } )
   | (i:INTEGER                            { r = new uPermInteger (i.getText()); } )
   | (r=permutation                        { } )
   | (str:STRING                           { r = new uPermString (str.getText()); } )
   | #(LIST r=expression                   { } )

   | #(SLICE                               { uPermType l; }
         sl:ID                             {
                                             l = upIntr.valueOf (sl.getText()); 
                                             if (l.type != uPermTypeType.LIST)
                                               throw new RuntimeException
                                                  (sl.getText() + " is not a list");
                                             uPermSlice s = new uPermSlice ((uPermList)l);
                                           }
         (d1:DUMMY | a=expression          { if (d1 == null) s.setLeft(a); } )
	 (d2:DUMMY | c:COLON               { if (d2 == null) s.setMultivalued(); } )
         (d3:DUMMY | b=expression          { if (d3 == null) s.setRight (b); } )
                                           { r = s.valueOf(); }
      )

   | #(EXPR_LIST                           { r = new uPermList(); }
         (a=expression                     { r.append(a); }
         )*
      )
   | #(OR a=expression b=expression        { r = a.or (b); } )
   | #(AND a=expression b=expression       { r = a.and (b); } )
   | #(EQ a=expression b=expression        { r = a.equal (b); } )
   | #(NE a=expression b=expression        { r = a.notequal (b); } )
   | #(LT a=expression b=expression        { r = a.lt (b); } )
   | #(GT a=expression b=expression        { r = a.gt (b); } )
   | #(LE a=expression b=expression        { r = a.le (b); } )
   | #(GE a=expression b=expression        { r = a.ge (b); } )
   | #(MULT a=expression b=expression      { r = a.multiply (b); } )
   | #(DIVD a=expression b=expression      { r = a.divide (b); } )
   | #(MOD a=expression b=expression       { r = a.mod (b); } )
   | #(PLUS a=expression b=expression      { r = a.plus (b); } )
   | #(MINUS a=expression b=expression     { r = a.minus (b); } )
   | #(ADD_INVS a=expression               { r = a.addInverse(); } )
   | #(MULT_INVS a=expression              { r = a.multInverse(); } )
   | #(NOT a=expression                    { r = a.not(); } )

   // this rule also handles permutation image calls
   | #(FUNC_CALL fname_:ID args:.          { r = upIntr.executeFunctionCall (fname_.getText(), #args); } )

   | ("true"                               { r = new uPermBoolean (true); } )
   | ("false"                              { r = new uPermBoolean (false); } )
   | #("sizeof" a=expression               { r=a.sizeof(); } )

;


permutation returns [uPermPermutation p]
   {
      p = new uPermPermutation();
      uPermType a = null;
   }

   : #(PERM_CYCLE                        { }
         ( a=expression                  { p.attach (a); }
         )*
      )                                  { p.complete(); }

   // preincrementing count prevents image form from expressing a permutation which moves 0
   | #(PERM_IMAGE                        { int count = 0; }
         ( a=expression                  { p.setImage (++count, a); }
         ) *
      )                                  { }

   | #(PERM_ID                           { } ) // p is initialized to the identity.
;

