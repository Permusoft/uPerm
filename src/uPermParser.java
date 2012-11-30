// $ANTLR 2.7.7 (20080604): "uperm.g" -> "uPermParser.java"$

import antlr.TokenBuffer;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.ANTLRException;
import antlr.LLkParser;
import antlr.Token;
import antlr.TokenStream;
import antlr.RecognitionException;
import antlr.NoViableAltException;
import antlr.MismatchedTokenException;
import antlr.SemanticException;
import antlr.ParserSharedInputState;
import antlr.collections.impl.BitSet;
import antlr.collections.AST;
import java.util.Hashtable;
import antlr.ASTFactory;
import antlr.ASTPair;
import antlr.collections.impl.ASTArray;

public class uPermParser extends antlr.LLkParser       implements uPermVocabularyTokenTypes
 {

protected uPermParser(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public uPermParser(TokenBuffer tokenBuf) {
  this(tokenBuf,2);
}

protected uPermParser(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public uPermParser(TokenStream lexer) {
  this(lexer,2);
}

public uPermParser(ParserSharedInputState state) {
  super(state,2);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

	public final void file() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST file_AST = null;
		
		try {      // for error handling
			statements();
			astFactory.addASTChild(currentAST, returnAST);
			match(Token.EOF_TYPE);
			file_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
		returnAST = file_AST;
	}
	
	public final void statements() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST statements_AST = null;
		
		try {      // for error handling
			{
			_loop50:
			do {
				if ((_tokenSet_1.member(LA(1)))) {
					statement();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop50;
				}
				
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				statements_AST = (AST)currentAST.root;
				
				statements_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(STATEMENTS,"STATEMENTS")).add(statements_AST));
				
				currentAST.root = statements_AST;
				currentAST.child = statements_AST!=null &&statements_AST.getFirstChild()!=null ?
					statements_AST.getFirstChild() : statements_AST;
				currentAST.advanceChildToEnd();
			}
			statements_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_2);
			} else {
			  throw ex;
			}
		}
		returnAST = statements_AST;
	}
	
	public final void statement() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST statement_AST = null;
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case LITERAL_fun:
			{
				function_declaration();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case LITERAL_print:
			{
				print_statement();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case LITERAL_if:
			{
				if_statement();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case LITERAL_while:
			{
				while_statement();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case LITERAL_for:
			{
				for_statement();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case LITERAL_return:
			{
				return_statement();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case LITERAL_break:
			{
				break_statement();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case LITERAL_continue:
			{
				continue_statement();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case LITERAL_import:
			{
				import_statement();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case LBRACE:
			{
				block();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			default:
				if ((LA(1)==ID||LA(1)==LITERAL_local) && (LA(2)==ASSIGN||LA(2)==ID)) {
					assignment_statement();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else if ((LA(1)==ID) && (LA(2)==LPAREN)) {
					function_call_statement();
					astFactory.addASTChild(currentAST, returnAST);
				}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			if ( inputState.guessing==0 ) {
				statement_AST = (AST)currentAST.root;
				
				statement_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(STATEMENT,"STATEMENT")).add(statement_AST));
				
				currentAST.root = statement_AST;
				currentAST.child = statement_AST!=null &&statement_AST.getFirstChild()!=null ?
					statement_AST.getFirstChild() : statement_AST;
				currentAST.advanceChildToEnd();
			}
			statement_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_3);
			} else {
			  throw ex;
			}
		}
		returnAST = statement_AST;
	}
	
	public final void assignment_statement() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST assignment_statement_AST = null;
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case ID:
			{
				assignment();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case LITERAL_local:
			{
				local_assignment();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(SEMI);
			assignment_statement_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_3);
			} else {
			  throw ex;
			}
		}
		returnAST = assignment_statement_AST;
	}
	
	public final void function_declaration() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST function_declaration_AST = null;
		
		try {      // for error handling
			AST tmp44_AST = null;
			tmp44_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp44_AST);
			match(LITERAL_fun);
			identifier();
			astFactory.addASTChild(currentAST, returnAST);
			block();
			astFactory.addASTChild(currentAST, returnAST);
			function_declaration_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_3);
			} else {
			  throw ex;
			}
		}
		returnAST = function_declaration_AST;
	}
	
	public final void function_call_statement() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST function_call_statement_AST = null;
		
		try {      // for error handling
			function_call();
			astFactory.addASTChild(currentAST, returnAST);
			match(SEMI);
			function_call_statement_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_3);
			} else {
			  throw ex;
			}
		}
		returnAST = function_call_statement_AST;
	}
	
	public final void print_statement() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST print_statement_AST = null;
		
		try {      // for error handling
			AST tmp46_AST = null;
			tmp46_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp46_AST);
			match(LITERAL_print);
			{
			switch ( LA(1)) {
			case MINUS:
			case MINVR:
			case NOT:
			case BACKSL:
			case LPAREN:
			case LBRACK:
			case INTEGER:
			case ID:
			case STRING:
			case LITERAL_sizeof:
			case LITERAL_true:
			case LITERAL_false:
			{
				expression();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case SEMI:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(SEMI);
			print_statement_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_3);
			} else {
			  throw ex;
			}
		}
		returnAST = print_statement_AST;
	}
	
	public final void if_statement() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST if_statement_AST = null;
		
		try {      // for error handling
			AST tmp48_AST = null;
			tmp48_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp48_AST);
			match(LITERAL_if);
			match(LPAREN);
			expression();
			astFactory.addASTChild(currentAST, returnAST);
			match(RPAREN);
			statement();
			astFactory.addASTChild(currentAST, returnAST);
			{
			if ((LA(1)==LITERAL_else) && (_tokenSet_1.member(LA(2)))) {
				match(LITERAL_else);
				statement();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((_tokenSet_3.member(LA(1))) && (_tokenSet_4.member(LA(2)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			if_statement_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_3);
			} else {
			  throw ex;
			}
		}
		returnAST = if_statement_AST;
	}
	
	public final void while_statement() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST while_statement_AST = null;
		
		try {      // for error handling
			AST tmp52_AST = null;
			tmp52_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp52_AST);
			match(LITERAL_while);
			match(LPAREN);
			expression();
			astFactory.addASTChild(currentAST, returnAST);
			match(RPAREN);
			statement();
			astFactory.addASTChild(currentAST, returnAST);
			while_statement_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_3);
			} else {
			  throw ex;
			}
		}
		returnAST = while_statement_AST;
	}
	
	public final void for_statement() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST for_statement_AST = null;
		
		try {      // for error handling
			AST tmp55_AST = null;
			tmp55_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp55_AST);
			match(LITERAL_for);
			match(LPAREN);
			assignment();
			astFactory.addASTChild(currentAST, returnAST);
			match(SEMI);
			expression();
			astFactory.addASTChild(currentAST, returnAST);
			match(SEMI);
			{
			if ((LA(1)==ID) && (LA(2)==ASSIGN)) {
				assignment();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((LA(1)==ID) && (LA(2)==LPAREN)) {
				function_call();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			match(RPAREN);
			statement();
			astFactory.addASTChild(currentAST, returnAST);
			for_statement_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_3);
			} else {
			  throw ex;
			}
		}
		returnAST = for_statement_AST;
	}
	
	public final void return_statement() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST return_statement_AST = null;
		
		try {      // for error handling
			AST tmp60_AST = null;
			tmp60_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp60_AST);
			match(LITERAL_return);
			{
			switch ( LA(1)) {
			case MINUS:
			case MINVR:
			case NOT:
			case BACKSL:
			case LPAREN:
			case LBRACK:
			case INTEGER:
			case ID:
			case STRING:
			case LITERAL_sizeof:
			case LITERAL_true:
			case LITERAL_false:
			{
				expression();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case SEMI:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(SEMI);
			return_statement_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_3);
			} else {
			  throw ex;
			}
		}
		returnAST = return_statement_AST;
	}
	
	public final void break_statement() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST break_statement_AST = null;
		
		try {      // for error handling
			AST tmp62_AST = null;
			tmp62_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp62_AST);
			match(LITERAL_break);
			match(SEMI);
			break_statement_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_3);
			} else {
			  throw ex;
			}
		}
		returnAST = break_statement_AST;
	}
	
	public final void continue_statement() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST continue_statement_AST = null;
		
		try {      // for error handling
			AST tmp64_AST = null;
			tmp64_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp64_AST);
			match(LITERAL_continue);
			match(SEMI);
			continue_statement_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_3);
			} else {
			  throw ex;
			}
		}
		returnAST = continue_statement_AST;
	}
	
	public final void import_statement() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST import_statement_AST = null;
		
		try {      // for error handling
			AST tmp66_AST = null;
			tmp66_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp66_AST);
			match(LITERAL_import);
			AST tmp67_AST = null;
			tmp67_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp67_AST);
			match(STRING);
			match(SEMI);
			import_statement_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_3);
			} else {
			  throw ex;
			}
		}
		returnAST = import_statement_AST;
	}
	
	public final void block() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST block_AST = null;
		
		try {      // for error handling
			match(LBRACE);
			statements();
			astFactory.addASTChild(currentAST, returnAST);
			match(RBRACE);
			if ( inputState.guessing==0 ) {
				block_AST = (AST)currentAST.root;
				block_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(BLOCK,"BLOCK")).add(block_AST));
				currentAST.root = block_AST;
				currentAST.child = block_AST!=null &&block_AST.getFirstChild()!=null ?
					block_AST.getFirstChild() : block_AST;
				currentAST.advanceChildToEnd();
			}
			block_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_3);
			} else {
			  throw ex;
			}
		}
		returnAST = block_AST;
	}
	
	public final void expression() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST expression_AST = null;
		
		try {      // for error handling
			bool();
			astFactory.addASTChild(currentAST, returnAST);
			expression_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_5);
			} else {
			  throw ex;
			}
		}
		returnAST = expression_AST;
	}
	
	public final void assignment() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST assignment_AST = null;
		
		try {      // for error handling
			identifier();
			astFactory.addASTChild(currentAST, returnAST);
			AST tmp71_AST = null;
			tmp71_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp71_AST);
			match(ASSIGN);
			expression();
			astFactory.addASTChild(currentAST, returnAST);
			assignment_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_6);
			} else {
			  throw ex;
			}
		}
		returnAST = assignment_AST;
	}
	
	public final void local_assignment() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST local_assignment_AST = null;
		
		try {      // for error handling
			AST tmp72_AST = null;
			tmp72_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp72_AST);
			match(LITERAL_local);
			identifier();
			astFactory.addASTChild(currentAST, returnAST);
			match(ASSIGN);
			expression();
			astFactory.addASTChild(currentAST, returnAST);
			local_assignment_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_7);
			} else {
			  throw ex;
			}
		}
		returnAST = local_assignment_AST;
	}
	
	public final void identifier() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST identifier_AST = null;
		
		try {      // for error handling
			AST tmp74_AST = null;
			tmp74_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp74_AST);
			match(ID);
			identifier_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_8);
			} else {
			  throw ex;
			}
		}
		returnAST = identifier_AST;
	}
	
	public final void function_call() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST function_call_AST = null;
		
		try {      // for error handling
			identifier();
			astFactory.addASTChild(currentAST, returnAST);
			match(LPAREN);
			expr_list();
			astFactory.addASTChild(currentAST, returnAST);
			match(RPAREN);
			if ( inputState.guessing==0 ) {
				function_call_AST = (AST)currentAST.root;
				
				function_call_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(FUNC_CALL,"FUNC_CALL")).add(function_call_AST));
				
				currentAST.root = function_call_AST;
				currentAST.child = function_call_AST!=null &&function_call_AST.getFirstChild()!=null ?
					function_call_AST.getFirstChild() : function_call_AST;
				currentAST.advanceChildToEnd();
			}
			function_call_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_9);
			} else {
			  throw ex;
			}
		}
		returnAST = function_call_AST;
	}
	
	public final void expr_list() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST expr_list_AST = null;
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case MINUS:
			case MINVR:
			case NOT:
			case BACKSL:
			case LPAREN:
			case LBRACK:
			case INTEGER:
			case ID:
			case STRING:
			case LITERAL_sizeof:
			case LITERAL_true:
			case LITERAL_false:
			{
				expression();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop76:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						expression();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop76;
					}
					
				} while (true);
				}
				break;
			}
			case RPAREN:
			case RBRACK:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			if ( inputState.guessing==0 ) {
				expr_list_AST = (AST)currentAST.root;
				
				expr_list_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(EXPR_LIST,"EXPR_LIST")).add(expr_list_AST));
				
				currentAST.root = expr_list_AST;
				currentAST.child = expr_list_AST!=null &&expr_list_AST.getFirstChild()!=null ?
					expr_list_AST.getFirstChild() : expr_list_AST;
				currentAST.advanceChildToEnd();
			}
			expr_list_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_10);
			} else {
			  throw ex;
			}
		}
		returnAST = expr_list_AST;
	}
	
	public final void bool() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST bool_AST = null;
		
		try {      // for error handling
			join();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop80:
			do {
				if ((LA(1)==OR)) {
					AST tmp78_AST = null;
					tmp78_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp78_AST);
					match(OR);
					join();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop80;
				}
				
			} while (true);
			}
			bool_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_5);
			} else {
			  throw ex;
			}
		}
		returnAST = bool_AST;
	}
	
	public final void join() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST join_AST = null;
		
		try {      // for error handling
			equl();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop83:
			do {
				if ((LA(1)==AND)) {
					AST tmp79_AST = null;
					tmp79_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp79_AST);
					match(AND);
					equl();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop83;
				}
				
			} while (true);
			}
			join_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_11);
			} else {
			  throw ex;
			}
		}
		returnAST = join_AST;
	}
	
	public final void equl() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST equl_AST = null;
		
		try {      // for error handling
			rel();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop87:
			do {
				if ((LA(1)==EQ||LA(1)==NE)) {
					{
					switch ( LA(1)) {
					case EQ:
					{
						AST tmp80_AST = null;
						tmp80_AST = astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp80_AST);
						match(EQ);
						break;
					}
					case NE:
					{
						AST tmp81_AST = null;
						tmp81_AST = astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp81_AST);
						match(NE);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					rel();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop87;
				}
				
			} while (true);
			}
			equl_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_12);
			} else {
			  throw ex;
			}
		}
		returnAST = equl_AST;
	}
	
	public final void rel() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST rel_AST = null;
		
		try {      // for error handling
			arith();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop91:
			do {
				if (((LA(1) >= LT && LA(1) <= GE))) {
					{
					switch ( LA(1)) {
					case LT:
					{
						AST tmp82_AST = null;
						tmp82_AST = astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp82_AST);
						match(LT);
						break;
					}
					case GT:
					{
						AST tmp83_AST = null;
						tmp83_AST = astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp83_AST);
						match(GT);
						break;
					}
					case LE:
					{
						AST tmp84_AST = null;
						tmp84_AST = astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp84_AST);
						match(LE);
						break;
					}
					case GE:
					{
						AST tmp85_AST = null;
						tmp85_AST = astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp85_AST);
						match(GE);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					arith();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop91;
				}
				
			} while (true);
			}
			rel_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_13);
			} else {
			  throw ex;
			}
		}
		returnAST = rel_AST;
	}
	
	public final void arith() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST arith_AST = null;
		
		try {      // for error handling
			mult();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop95:
			do {
				if ((LA(1)==PLUS||LA(1)==MINUS)) {
					{
					switch ( LA(1)) {
					case PLUS:
					{
						AST tmp86_AST = null;
						tmp86_AST = astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp86_AST);
						match(PLUS);
						break;
					}
					case MINUS:
					{
						AST tmp87_AST = null;
						tmp87_AST = astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp87_AST);
						match(MINUS);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					mult();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop95;
				}
				
			} while (true);
			}
			arith_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_14);
			} else {
			  throw ex;
			}
		}
		returnAST = arith_AST;
	}
	
	public final void mult() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST mult_AST = null;
		
		try {      // for error handling
			unary();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop99:
			do {
				if (((LA(1) >= MULT && LA(1) <= MOD))) {
					{
					switch ( LA(1)) {
					case MULT:
					{
						AST tmp88_AST = null;
						tmp88_AST = astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp88_AST);
						match(MULT);
						break;
					}
					case DIVD:
					{
						AST tmp89_AST = null;
						tmp89_AST = astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp89_AST);
						match(DIVD);
						break;
					}
					case MOD:
					{
						AST tmp90_AST = null;
						tmp90_AST = astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp90_AST);
						match(MOD);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					unary();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop99;
				}
				
			} while (true);
			}
			mult_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_15);
			} else {
			  throw ex;
			}
		}
		returnAST = mult_AST;
	}
	
	public final void unary() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST unary_AST = null;
		Token  m = null;
		AST m_AST = null;
		Token  i = null;
		AST i_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case MINUS:
			{
				m = LT(1);
				m_AST = astFactory.create(m);
				astFactory.makeASTRoot(currentAST, m_AST);
				match(MINUS);
				atom();
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					m_AST.setType (ADD_INVS);
				}
				unary_AST = (AST)currentAST.root;
				break;
			}
			case MINVR:
			{
				i = LT(1);
				i_AST = astFactory.create(i);
				astFactory.makeASTRoot(currentAST, i_AST);
				match(MINVR);
				atom();
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					i_AST.setType (MULT_INVS);
				}
				unary_AST = (AST)currentAST.root;
				break;
			}
			case NOT:
			{
				AST tmp91_AST = null;
				tmp91_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp91_AST);
				match(NOT);
				atom();
				astFactory.addASTChild(currentAST, returnAST);
				unary_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_sizeof:
			{
				AST tmp92_AST = null;
				tmp92_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp92_AST);
				match(LITERAL_sizeof);
				atom();
				astFactory.addASTChild(currentAST, returnAST);
				unary_AST = (AST)currentAST.root;
				break;
			}
			case BACKSL:
			case LPAREN:
			case LBRACK:
			case INTEGER:
			case ID:
			case STRING:
			case LITERAL_true:
			case LITERAL_false:
			{
				atom();
				astFactory.addASTChild(currentAST, returnAST);
				unary_AST = (AST)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_16);
			} else {
			  throw ex;
			}
		}
		returnAST = unary_AST;
	}
	
	public final void atom() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST atom_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LPAREN:
			{
				lparen_predicate();
				astFactory.addASTChild(currentAST, returnAST);
				atom_AST = (AST)currentAST.root;
				break;
			}
			case LBRACK:
			{
				list();
				astFactory.addASTChild(currentAST, returnAST);
				atom_AST = (AST)currentAST.root;
				break;
			}
			case INTEGER:
			{
				integer_literal();
				astFactory.addASTChild(currentAST, returnAST);
				atom_AST = (AST)currentAST.root;
				break;
			}
			case STRING:
			{
				string_literal();
				astFactory.addASTChild(currentAST, returnAST);
				atom_AST = (AST)currentAST.root;
				break;
			}
			case LITERAL_true:
			case LITERAL_false:
			{
				boolean_literal();
				astFactory.addASTChild(currentAST, returnAST);
				atom_AST = (AST)currentAST.root;
				break;
			}
			default:
				if ((LA(1)==BACKSL) && (LA(2)==INTEGER||LA(2)==ID)) {
					permutation_image_literal();
					astFactory.addASTChild(currentAST, returnAST);
					atom_AST = (AST)currentAST.root;
				}
				else if ((LA(1)==BACKSL) && (LA(2)==BACKSL)) {
					permutation_image_identity_literal();
					astFactory.addASTChild(currentAST, returnAST);
					atom_AST = (AST)currentAST.root;
				}
				else if ((LA(1)==ID) && (LA(2)==LPAREN)) {
					function_call();
					astFactory.addASTChild(currentAST, returnAST);
					atom_AST = (AST)currentAST.root;
				}
				else if ((LA(1)==ID) && (LA(2)==LBRACK)) {
					slice();
					astFactory.addASTChild(currentAST, returnAST);
					atom_AST = (AST)currentAST.root;
				}
				else if ((LA(1)==ID) && (_tokenSet_16.member(LA(2)))) {
					identifier();
					astFactory.addASTChild(currentAST, returnAST);
					atom_AST = (AST)currentAST.root;
				}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_16);
			} else {
			  throw ex;
			}
		}
		returnAST = atom_AST;
	}
	
	public final void lparen_predicate() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST lparen_predicate_AST = null;
		
		try {      // for error handling
			boolean synPredMatched106 = false;
			if (((LA(1)==LPAREN) && (LA(2)==INTEGER||LA(2)==ID))) {
				int _m106 = mark();
				synPredMatched106 = true;
				inputState.guessing++;
				try {
					{
					match(LPAREN);
					{
					if ((LA(1)==INTEGER)) {
						integer_literal();
					}
					else if ((LA(1)==ID) && (LA(2)==INTEGER||LA(2)==ID)) {
						identifier();
					}
					else if ((LA(1)==ID) && (LA(2)==LBRACK)) {
						slice();
					}
					else if ((LA(1)==ID) && (LA(2)==LPAREN)) {
						function_call();
					}
					else {
						throw new NoViableAltException(LT(1), getFilename());
					}
					
					}
					{
					if ((LA(1)==INTEGER)) {
						integer_literal();
					}
					else if ((LA(1)==ID) && (true)) {
						identifier();
					}
					else if ((LA(1)==ID) && (LA(2)==LBRACK)) {
						slice();
					}
					else if ((LA(1)==ID) && (LA(2)==LPAREN)) {
						function_call();
					}
					else {
						throw new NoViableAltException(LT(1), getFilename());
					}
					
					}
					}
				}
				catch (RecognitionException pe) {
					synPredMatched106 = false;
				}
				rewind(_m106);
inputState.guessing--;
			}
			if ( synPredMatched106 ) {
				permutation_cycle_literal();
				astFactory.addASTChild(currentAST, returnAST);
				lparen_predicate_AST = (AST)currentAST.root;
			}
			else {
				boolean synPredMatched108 = false;
				if (((LA(1)==LPAREN) && (LA(2)==RPAREN))) {
					int _m108 = mark();
					synPredMatched108 = true;
					inputState.guessing++;
					try {
						{
						match(LPAREN);
						match(RPAREN);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched108 = false;
					}
					rewind(_m108);
inputState.guessing--;
				}
				if ( synPredMatched108 ) {
					permutation_cycle_identity_literal();
					astFactory.addASTChild(currentAST, returnAST);
					lparen_predicate_AST = (AST)currentAST.root;
				}
				else if ((LA(1)==LPAREN) && (_tokenSet_17.member(LA(2)))) {
					parenthesized_expression();
					astFactory.addASTChild(currentAST, returnAST);
					lparen_predicate_AST = (AST)currentAST.root;
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
			}
			catch (RecognitionException ex) {
				if (inputState.guessing==0) {
					reportError(ex);
					recover(ex,_tokenSet_16);
				} else {
				  throw ex;
				}
			}
			returnAST = lparen_predicate_AST;
		}
		
	public final void permutation_image_literal() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST permutation_image_literal_AST = null;
		
		try {      // for error handling
			match(BACKSL);
			point_list();
			astFactory.addASTChild(currentAST, returnAST);
			match(BACKSL);
			if ( inputState.guessing==0 ) {
				permutation_image_literal_AST = (AST)currentAST.root;
				permutation_image_literal_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(PERM_IMAGE,"PERM_IMAGE")).add(permutation_image_literal_AST));
				currentAST.root = permutation_image_literal_AST;
				currentAST.child = permutation_image_literal_AST!=null &&permutation_image_literal_AST.getFirstChild()!=null ?
					permutation_image_literal_AST.getFirstChild() : permutation_image_literal_AST;
				currentAST.advanceChildToEnd();
			}
			permutation_image_literal_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_16);
			} else {
			  throw ex;
			}
		}
		returnAST = permutation_image_literal_AST;
	}
	
	public final void permutation_image_identity_literal() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST permutation_image_identity_literal_AST = null;
		
		try {      // for error handling
			match(BACKSL);
			match(BACKSL);
			if ( inputState.guessing==0 ) {
				permutation_image_identity_literal_AST = (AST)currentAST.root;
				permutation_image_identity_literal_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(PERM_ID,"PERM_ID")).add(permutation_image_identity_literal_AST));
				currentAST.root = permutation_image_identity_literal_AST;
				currentAST.child = permutation_image_identity_literal_AST!=null &&permutation_image_identity_literal_AST.getFirstChild()!=null ?
					permutation_image_identity_literal_AST.getFirstChild() : permutation_image_identity_literal_AST;
				currentAST.advanceChildToEnd();
			}
			permutation_image_identity_literal_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_16);
			} else {
			  throw ex;
			}
		}
		returnAST = permutation_image_identity_literal_AST;
	}
	
	public final void list() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST list_AST = null;
		
		try {      // for error handling
			match(LBRACK);
			expr_list();
			astFactory.addASTChild(currentAST, returnAST);
			match(RBRACK);
			if ( inputState.guessing==0 ) {
				list_AST = (AST)currentAST.root;
				
				list_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(LIST,"LIST")).add(list_AST));
				
				currentAST.root = list_AST;
				currentAST.child = list_AST!=null &&list_AST.getFirstChild()!=null ?
					list_AST.getFirstChild() : list_AST;
				currentAST.advanceChildToEnd();
			}
			list_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_16);
			} else {
			  throw ex;
			}
		}
		returnAST = list_AST;
	}
	
	public final void slice() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST slice_AST = null;
		AST id_AST = null;
		AST l_AST = null;
		Token  c = null;
		AST c_AST = null;
		AST r_AST = null;
		
		try {      // for error handling
			identifier();
			id_AST = (AST)returnAST;
			match(LBRACK);
			{
			switch ( LA(1)) {
			case MINUS:
			case MINVR:
			case NOT:
			case BACKSL:
			case LPAREN:
			case LBRACK:
			case INTEGER:
			case ID:
			case STRING:
			case LITERAL_sizeof:
			case LITERAL_true:
			case LITERAL_false:
			{
				expression();
				l_AST = (AST)returnAST;
				break;
			}
			case RBRACK:
			case COLON:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case COLON:
			{
				c = LT(1);
				c_AST = astFactory.create(c);
				match(COLON);
				{
				switch ( LA(1)) {
				case MINUS:
				case MINVR:
				case NOT:
				case BACKSL:
				case LPAREN:
				case LBRACK:
				case INTEGER:
				case ID:
				case STRING:
				case LITERAL_sizeof:
				case LITERAL_true:
				case LITERAL_false:
				{
					expression();
					r_AST = (AST)returnAST;
					break;
				}
				case RBRACK:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				break;
			}
			case RBRACK:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(RBRACK);
			if ( inputState.guessing==0 ) {
				slice_AST = (AST)currentAST.root;
				
				if (l_AST == null) l_AST = (AST)astFactory.make( (new ASTArray(1)).add(astFactory.create(DUMMY,"DUMMY")));
				if (c_AST == null) c_AST = (AST)astFactory.make( (new ASTArray(1)).add(astFactory.create(DUMMY,"DUMMY")));
				if (r_AST == null) r_AST = (AST)astFactory.make( (new ASTArray(1)).add(astFactory.create(DUMMY,"DUMMY")));
				
				slice_AST = (AST)astFactory.make( (new ASTArray(5)).add(astFactory.create(SLICE,"SLICE")).add(id_AST).add(l_AST).add(c_AST).add(r_AST));
				
				currentAST.root = slice_AST;
				currentAST.child = slice_AST!=null &&slice_AST.getFirstChild()!=null ?
					slice_AST.getFirstChild() : slice_AST;
				currentAST.advanceChildToEnd();
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_9);
			} else {
			  throw ex;
			}
		}
		returnAST = slice_AST;
	}
	
	public final void integer_literal() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST integer_literal_AST = null;
		
		try {      // for error handling
			AST tmp101_AST = null;
			tmp101_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp101_AST);
			match(INTEGER);
			integer_literal_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_9);
			} else {
			  throw ex;
			}
		}
		returnAST = integer_literal_AST;
	}
	
	public final void string_literal() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST string_literal_AST = null;
		
		try {      // for error handling
			AST tmp102_AST = null;
			tmp102_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp102_AST);
			match(STRING);
			string_literal_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_16);
			} else {
			  throw ex;
			}
		}
		returnAST = string_literal_AST;
	}
	
	public final void boolean_literal() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST boolean_literal_AST = null;
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case LITERAL_true:
			{
				AST tmp103_AST = null;
				tmp103_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp103_AST);
				match(LITERAL_true);
				break;
			}
			case LITERAL_false:
			{
				AST tmp104_AST = null;
				tmp104_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp104_AST);
				match(LITERAL_false);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			boolean_literal_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_16);
			} else {
			  throw ex;
			}
		}
		returnAST = boolean_literal_AST;
	}
	
	public final void permutation_cycle_literal() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST permutation_cycle_literal_AST = null;
		
		try {      // for error handling
			match(LPAREN);
			point_list();
			astFactory.addASTChild(currentAST, returnAST);
			match(RPAREN);
			if ( inputState.guessing==0 ) {
				permutation_cycle_literal_AST = (AST)currentAST.root;
				permutation_cycle_literal_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(PERM_CYCLE,"PERM_CYCLE")).add(permutation_cycle_literal_AST));
				currentAST.root = permutation_cycle_literal_AST;
				currentAST.child = permutation_cycle_literal_AST!=null &&permutation_cycle_literal_AST.getFirstChild()!=null ?
					permutation_cycle_literal_AST.getFirstChild() : permutation_cycle_literal_AST;
				currentAST.advanceChildToEnd();
			}
			permutation_cycle_literal_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_16);
			} else {
			  throw ex;
			}
		}
		returnAST = permutation_cycle_literal_AST;
	}
	
	public final void permutation_cycle_identity_literal() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST permutation_cycle_identity_literal_AST = null;
		
		try {      // for error handling
			match(LPAREN);
			match(RPAREN);
			if ( inputState.guessing==0 ) {
				permutation_cycle_identity_literal_AST = (AST)currentAST.root;
				permutation_cycle_identity_literal_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(PERM_ID,"PERM_ID")).add(permutation_cycle_identity_literal_AST));
				currentAST.root = permutation_cycle_identity_literal_AST;
				currentAST.child = permutation_cycle_identity_literal_AST!=null &&permutation_cycle_identity_literal_AST.getFirstChild()!=null ?
					permutation_cycle_identity_literal_AST.getFirstChild() : permutation_cycle_identity_literal_AST;
				currentAST.advanceChildToEnd();
			}
			permutation_cycle_identity_literal_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_16);
			} else {
			  throw ex;
			}
		}
		returnAST = permutation_cycle_identity_literal_AST;
	}
	
	public final void parenthesized_expression() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST parenthesized_expression_AST = null;
		
		try {      // for error handling
			match(LPAREN);
			expression();
			astFactory.addASTChild(currentAST, returnAST);
			match(RPAREN);
			parenthesized_expression_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_16);
			} else {
			  throw ex;
			}
		}
		returnAST = parenthesized_expression_AST;
	}
	
	public final void point_list() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST point_list_AST = null;
		
		try {      // for error handling
			{
			if ((LA(1)==INTEGER)) {
				integer_literal();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((LA(1)==ID) && (LA(2)==INTEGER||LA(2)==ID)) {
				identifier();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((LA(1)==ID) && (LA(2)==LBRACK)) {
				slice();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((LA(1)==ID) && (LA(2)==LPAREN)) {
				function_call();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			int _cnt117=0;
			_loop117:
			do {
				if ((LA(1)==INTEGER)) {
					integer_literal();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else if ((LA(1)==ID) && (_tokenSet_18.member(LA(2)))) {
					identifier();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else if ((LA(1)==ID) && (LA(2)==LBRACK)) {
					slice();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else if ((LA(1)==ID) && (LA(2)==LPAREN)) {
					function_call();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					if ( _cnt117>=1 ) { break _loop117; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt117++;
			} while (true);
			}
			point_list_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_19);
			} else {
			  throw ex;
			}
		}
		returnAST = point_list_AST;
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"LETTER",
		"DIGIT",
		"WS",
		"PLUS",
		"MINUS",
		"MULT",
		"DIVD",
		"MOD",
		"MINVR",
		"AND",
		"OR",
		"NOT",
		"EQ",
		"NE",
		"LT",
		"GT",
		"LE",
		"GE",
		"BACKSL",
		"LPAREN",
		"RPAREN",
		"LBRACK",
		"RBRACK",
		"LBRACE",
		"RBRACE",
		"SEMI",
		"COMMA",
		"COLON",
		"ASSIGN",
		"INTEGER",
		"ID",
		"STRING",
		"COMMENT",
		"STATEMENT",
		"STATEMENTS",
		"BLOCK",
		"ADD_INVS",
		"MULT_INVS",
		"FUNC_CALL",
		"EXPR_LIST",
		"PERM_CYCLE",
		"PERM_IMAGE",
		"PERM_ID",
		"LIST",
		"SLICE",
		"DUMMY",
		"\"return\"",
		"\"break\"",
		"\"continue\"",
		"\"local\"",
		"\"print\"",
		"\"if\"",
		"\"else\"",
		"\"while\"",
		"\"for\"",
		"\"fun\"",
		"\"import\"",
		"\"sizeof\"",
		"\"true\"",
		"\"false\""
	};
	
	protected void buildTokenTypeASTClassMap() {
		tokenTypeToASTClassMap=null;
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 2L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 2232659532583010304L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 268435458L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 2304717126889373698L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { -1125834496634622L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = { 3841982464L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	private static final long[] mk_tokenSet_6() {
		long[] data = { 553648128L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());
	private static final long[] mk_tokenSet_7() {
		long[] data = { 536870912L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_7 = new BitSet(mk_tokenSet_7());
	private static final long[] mk_tokenSet_8() {
		long[] data = { 34091265920L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_8 = new BitSet(mk_tokenSet_8());
	private static final long[] mk_tokenSet_9() {
		long[] data = { 29620137856L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_9 = new BitSet(mk_tokenSet_9());
	private static final long[] mk_tokenSet_10() {
		long[] data = { 83886080L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_10 = new BitSet(mk_tokenSet_10());
	private static final long[] mk_tokenSet_11() {
		long[] data = { 3841998848L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_11 = new BitSet(mk_tokenSet_11());
	private static final long[] mk_tokenSet_12() {
		long[] data = { 3842007040L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_12 = new BitSet(mk_tokenSet_12());
	private static final long[] mk_tokenSet_13() {
		long[] data = { 3842203648L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_13 = new BitSet(mk_tokenSet_13());
	private static final long[] mk_tokenSet_14() {
		long[] data = { 3846135808L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_14 = new BitSet(mk_tokenSet_14());
	private static final long[] mk_tokenSet_15() {
		long[] data = { 3846136192L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_15 = new BitSet(mk_tokenSet_15());
	private static final long[] mk_tokenSet_16() {
		long[] data = { 3846139776L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_16 = new BitSet(mk_tokenSet_16());
	private static final long[] mk_tokenSet_17() {
		long[] data = { -2305842949037977344L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_17 = new BitSet(mk_tokenSet_17());
	private static final long[] mk_tokenSet_18() {
		long[] data = { 25790775296L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_18 = new BitSet(mk_tokenSet_18());
	private static final long[] mk_tokenSet_19() {
		long[] data = { 20971520L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_19 = new BitSet(mk_tokenSet_19());
	
	}
