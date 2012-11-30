// $ANTLR 2.7.7 (20080604): "uperm.g" -> "uPermWalker.java"$

import antlr.TreeParser;
import antlr.Token;
import antlr.collections.AST;
import antlr.RecognitionException;
import antlr.ANTLRException;
import antlr.NoViableAltException;
import antlr.MismatchedTokenException;
import antlr.SemanticException;
import antlr.collections.impl.BitSet;
import antlr.ASTPair;
import antlr.collections.impl.ASTArray;

   import java.lang.Integer;


public class uPermWalker extends antlr.TreeParser       implements uPermWalkerTokenTypes
 {

   final uPermInterpreter upIntr = new uPermInterpreter(this);
public uPermWalker() {
	tokenNames = _tokenNames;
}

	public final void program(AST _t) throws RecognitionException {
		
		AST program_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			
			uPermControl c = null;
			
			{
			_loop130:
			do {
				if (_t==null) _t=ASTNULL;
				if ((_tokenSet_0.member(_t.getType()))) {
					c=statement(_t);
					_t = _retTree;
				}
				else {
					break _loop130;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final uPermControl  statement(AST _t) throws RecognitionException {
		uPermControl c;
		
		AST statement_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		AST s = null;
		AST aid = null;
		AST fname_ = null;
		AST args = null;
		AST aid_ = null;
		AST cond = null;
		AST actn = null;
		AST alt = null;
		AST init = null;
		AST fcond = null;
		AST iter = null;
		AST fstmt = null;
		AST wcond = null;
		AST stmt = null;
		AST f = null;
		AST body = null;
		
		uPermType a = null, b = null, r = null;
		c = upIntr.control();
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case STATEMENT:
			{
				AST __t132 = _t;
				AST tmp1_AST_in = (AST)_t;
				match(_t,STATEMENT);
				_t = _t.getFirstChild();
				s = (AST)_t;
				if ( _t==null ) throw new MismatchedTokenException();
				_t = _t.getNextSibling();
				c = upIntr.execute (s);
				_t = __t132;
				_t = _t.getNextSibling();
				break;
			}
			case ASSIGN:
			{
				AST __t133 = _t;
				AST tmp2_AST_in = (AST)_t;
				match(_t,ASSIGN);
				_t = _t.getFirstChild();
				aid = (AST)_t;
				match(_t,ID);
				_t = _t.getNextSibling();
				a=expression(_t);
				_t = _retTree;
				{
				if (_t==null) _t=ASTNULL;
				switch ( _t.getType()) {
				case PLUS:
				case MINUS:
				case MULT:
				case DIVD:
				case MOD:
				case AND:
				case OR:
				case NOT:
				case EQ:
				case NE:
				case LT:
				case GT:
				case LE:
				case GE:
				case INTEGER:
				case ID:
				case STRING:
				case ADD_INVS:
				case MULT_INVS:
				case FUNC_CALL:
				case EXPR_LIST:
				case PERM_CYCLE:
				case PERM_IMAGE:
				case PERM_ID:
				case LIST:
				case SLICE:
				case LITERAL_sizeof:
				case LITERAL_true:
				case LITERAL_false:
				{
					b=expression(_t);
					_t = _retTree;
					break;
				}
				case 3:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(_t);
				}
				}
				}
				upIntr.assign (aid.getText(), a, b); c=upIntr.control();
				_t = __t133;
				_t = _t.getNextSibling();
				break;
			}
			case FUNC_CALL:
			{
				AST __t135 = _t;
				AST tmp3_AST_in = (AST)_t;
				match(_t,FUNC_CALL);
				_t = _t.getFirstChild();
				fname_ = (AST)_t;
				match(_t,ID);
				_t = _t.getNextSibling();
				args = (AST)_t;
				if ( _t==null ) throw new MismatchedTokenException();
				_t = _t.getNextSibling();
				upIntr.executeFunctionCall (fname_.getText(), args); c=upIntr.control();
				_t = __t135;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_local:
			{
				AST __t136 = _t;
				AST tmp4_AST_in = (AST)_t;
				match(_t,LITERAL_local);
				_t = _t.getFirstChild();
				aid_ = (AST)_t;
				match(_t,ID);
				_t = _t.getNextSibling();
				a=expression(_t);
				_t = _retTree;
				upIntr.bindLocal (aid_.getText(), a); c=upIntr.control();
				_t = __t136;
				_t = _t.getNextSibling();
				break;
			}
			case BLOCK:
			{
				AST __t137 = _t;
				AST tmp5_AST_in = (AST)_t;
				match(_t,BLOCK);
				_t = _t.getFirstChild();
				upIntr.enterBlock(); c=upIntr.control();
				c=statement(_t);
				_t = _retTree;
				
				_t = __t137;
				_t = _t.getNextSibling();
				c = upIntr.exitBlock();
				break;
			}
			case STATEMENTS:
			{
				AST __t138 = _t;
				AST tmp6_AST_in = (AST)_t;
				match(_t,STATEMENTS);
				_t = _t.getFirstChild();
				{
				_loop140:
				do {
					if (_t==null) _t=ASTNULL;
					if ((_tokenSet_0.member(_t.getType()))) {
						c=statement(_t);
						_t = _retTree;
					}
					else {
						break _loop140;
					}
					
				} while (true);
				}
				
				_t = __t138;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_if:
			{
				AST __t141 = _t;
				AST tmp7_AST_in = (AST)_t;
				match(_t,LITERAL_if);
				_t = _t.getFirstChild();
				cond = (AST)_t;
				if ( _t==null ) throw new MismatchedTokenException();
				_t = _t.getNextSibling();
				actn = (AST)_t;
				if ( _t==null ) throw new MismatchedTokenException();
				_t = _t.getNextSibling();
				{
				if (_t==null) _t=ASTNULL;
				switch ( _t.getType()) {
				case LETTER:
				case DIGIT:
				case WS:
				case PLUS:
				case MINUS:
				case MULT:
				case DIVD:
				case MOD:
				case MINVR:
				case AND:
				case OR:
				case NOT:
				case EQ:
				case NE:
				case LT:
				case GT:
				case LE:
				case GE:
				case BACKSL:
				case LPAREN:
				case RPAREN:
				case LBRACK:
				case RBRACK:
				case LBRACE:
				case RBRACE:
				case SEMI:
				case COMMA:
				case COLON:
				case ASSIGN:
				case INTEGER:
				case ID:
				case STRING:
				case COMMENT:
				case STATEMENT:
				case STATEMENTS:
				case BLOCK:
				case ADD_INVS:
				case MULT_INVS:
				case FUNC_CALL:
				case EXPR_LIST:
				case PERM_CYCLE:
				case PERM_IMAGE:
				case PERM_ID:
				case LIST:
				case SLICE:
				case DUMMY:
				case LITERAL_return:
				case LITERAL_break:
				case LITERAL_continue:
				case LITERAL_local:
				case LITERAL_print:
				case LITERAL_if:
				case LITERAL_else:
				case LITERAL_while:
				case LITERAL_for:
				case LITERAL_fun:
				case LITERAL_import:
				case LITERAL_sizeof:
				case LITERAL_true:
				case LITERAL_false:
				{
					alt = (AST)_t;
					if ( _t==null ) throw new MismatchedTokenException();
					_t = _t.getNextSibling();
					break;
				}
				case 3:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(_t);
				}
				}
				}
				upIntr.executeIfStatement (cond, actn, alt); c=upIntr.control();
				_t = __t141;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_for:
			{
				AST __t143 = _t;
				AST tmp8_AST_in = (AST)_t;
				match(_t,LITERAL_for);
				_t = _t.getFirstChild();
				init = (AST)_t;
				if ( _t==null ) throw new MismatchedTokenException();
				_t = _t.getNextSibling();
				fcond = (AST)_t;
				if ( _t==null ) throw new MismatchedTokenException();
				_t = _t.getNextSibling();
				iter = (AST)_t;
				if ( _t==null ) throw new MismatchedTokenException();
				_t = _t.getNextSibling();
				fstmt = (AST)_t;
				if ( _t==null ) throw new MismatchedTokenException();
				_t = _t.getNextSibling();
				upIntr.executeForLoop (init, fcond, iter, fstmt); c=upIntr.control();
				_t = __t143;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_while:
			{
				AST __t144 = _t;
				AST tmp9_AST_in = (AST)_t;
				match(_t,LITERAL_while);
				_t = _t.getFirstChild();
				wcond = (AST)_t;
				if ( _t==null ) throw new MismatchedTokenException();
				_t = _t.getNextSibling();
				stmt = (AST)_t;
				if ( _t==null ) throw new MismatchedTokenException();
				_t = _t.getNextSibling();
				upIntr.executeWhileLoop (wcond, stmt); c=upIntr.control();
				_t = __t144;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_print:
			{
				AST __t145 = _t;
				AST tmp10_AST_in = (AST)_t;
				match(_t,LITERAL_print);
				_t = _t.getFirstChild();
				{
				if (_t==null) _t=ASTNULL;
				switch ( _t.getType()) {
				case PLUS:
				case MINUS:
				case MULT:
				case DIVD:
				case MOD:
				case AND:
				case OR:
				case NOT:
				case EQ:
				case NE:
				case LT:
				case GT:
				case LE:
				case GE:
				case INTEGER:
				case ID:
				case STRING:
				case ADD_INVS:
				case MULT_INVS:
				case FUNC_CALL:
				case EXPR_LIST:
				case PERM_CYCLE:
				case PERM_IMAGE:
				case PERM_ID:
				case LIST:
				case SLICE:
				case LITERAL_sizeof:
				case LITERAL_true:
				case LITERAL_false:
				{
					a=expression(_t);
					_t = _retTree;
					break;
				}
				case 3:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(_t);
				}
				}
				}
				upIntr.printExpression(a); c=upIntr.control();
				_t = __t145;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_fun:
			{
				AST __t147 = _t;
				AST tmp11_AST_in = (AST)_t;
				match(_t,LITERAL_fun);
				_t = _t.getFirstChild();
				f = (AST)_t;
				match(_t,ID);
				_t = _t.getNextSibling();
				body = (AST)_t;
				if ( _t==null ) throw new MismatchedTokenException();
				_t = _t.getNextSibling();
				upIntr.bindFunction (f.getText(), body); c=upIntr.control();
				_t = __t147;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_return:
			{
				AST __t148 = _t;
				AST tmp12_AST_in = (AST)_t;
				match(_t,LITERAL_return);
				_t = _t.getFirstChild();
				{
				if (_t==null) _t=ASTNULL;
				switch ( _t.getType()) {
				case PLUS:
				case MINUS:
				case MULT:
				case DIVD:
				case MOD:
				case AND:
				case OR:
				case NOT:
				case EQ:
				case NE:
				case LT:
				case GT:
				case LE:
				case GE:
				case INTEGER:
				case ID:
				case STRING:
				case ADD_INVS:
				case MULT_INVS:
				case FUNC_CALL:
				case EXPR_LIST:
				case PERM_CYCLE:
				case PERM_IMAGE:
				case PERM_ID:
				case LIST:
				case SLICE:
				case LITERAL_sizeof:
				case LITERAL_true:
				case LITERAL_false:
				{
					a=expression(_t);
					_t = _retTree;
					break;
				}
				case 3:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(_t);
				}
				}
				}
				c = upIntr.setReturnValue(a);
				_t = __t148;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_break:
			{
				AST __t150 = _t;
				AST tmp13_AST_in = (AST)_t;
				match(_t,LITERAL_break);
				_t = _t.getFirstChild();
				c = upIntr.constrainExecution();
				_t = __t150;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_continue:
			{
				AST __t151 = _t;
				AST tmp14_AST_in = (AST)_t;
				match(_t,LITERAL_continue);
				_t = _t.getFirstChild();
				c = upIntr.loopConstrainExecution();
				_t = __t151;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_import:
			{
				AST __t152 = _t;
				AST tmp15_AST_in = (AST)_t;
				match(_t,LITERAL_import);
				_t = _t.getFirstChild();
				a=expression(_t);
				_t = _retTree;
				upIntr.importFile (a); c=upIntr.control();
				_t = __t152;
				_t = _t.getNextSibling();
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
		return c;
	}
	
	public final uPermType  expression(AST _t) throws RecognitionException {
		uPermType r;
		
		AST expression_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		AST id = null;
		AST i = null;
		AST str = null;
		AST sl = null;
		AST d1 = null;
		AST d2 = null;
		AST c = null;
		AST d3 = null;
		AST fname_ = null;
		AST args = null;
		
		r = null;
		uPermType a = null, b = null;
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case ID:
			{
				{
				id = (AST)_t;
				match(_t,ID);
				_t = _t.getNextSibling();
				r = upIntr.valueOf (id.getText());
				}
				break;
			}
			case INTEGER:
			{
				{
				i = (AST)_t;
				match(_t,INTEGER);
				_t = _t.getNextSibling();
				r = new uPermInteger (i.getText());
				}
				break;
			}
			case PERM_CYCLE:
			case PERM_IMAGE:
			case PERM_ID:
			{
				{
				r=permutation(_t);
				_t = _retTree;
				
				}
				break;
			}
			case STRING:
			{
				{
				str = (AST)_t;
				match(_t,STRING);
				_t = _t.getNextSibling();
				r = new uPermString (str.getText());
				}
				break;
			}
			case LIST:
			{
				AST __t158 = _t;
				AST tmp16_AST_in = (AST)_t;
				match(_t,LIST);
				_t = _t.getFirstChild();
				r=expression(_t);
				_t = _retTree;
				
				_t = __t158;
				_t = _t.getNextSibling();
				break;
			}
			case SLICE:
			{
				AST __t159 = _t;
				AST tmp17_AST_in = (AST)_t;
				match(_t,SLICE);
				_t = _t.getFirstChild();
				uPermType l;
				sl = (AST)_t;
				match(_t,ID);
				_t = _t.getNextSibling();
				
				l = upIntr.valueOf (sl.getText()); 
				if (l.type != uPermTypeType.LIST)
				throw new RuntimeException
				(sl.getText() + " is not a list");
				uPermSlice s = new uPermSlice ((uPermList)l);
				
				{
				if (_t==null) _t=ASTNULL;
				switch ( _t.getType()) {
				case DUMMY:
				{
					d1 = (AST)_t;
					match(_t,DUMMY);
					_t = _t.getNextSibling();
					break;
				}
				case PLUS:
				case MINUS:
				case MULT:
				case DIVD:
				case MOD:
				case AND:
				case OR:
				case NOT:
				case EQ:
				case NE:
				case LT:
				case GT:
				case LE:
				case GE:
				case INTEGER:
				case ID:
				case STRING:
				case ADD_INVS:
				case MULT_INVS:
				case FUNC_CALL:
				case EXPR_LIST:
				case PERM_CYCLE:
				case PERM_IMAGE:
				case PERM_ID:
				case LIST:
				case SLICE:
				case LITERAL_sizeof:
				case LITERAL_true:
				case LITERAL_false:
				{
					a=expression(_t);
					_t = _retTree;
					if (d1 == null) s.setLeft(a);
					break;
				}
				default:
				{
					throw new NoViableAltException(_t);
				}
				}
				}
				{
				if (_t==null) _t=ASTNULL;
				switch ( _t.getType()) {
				case DUMMY:
				{
					d2 = (AST)_t;
					match(_t,DUMMY);
					_t = _t.getNextSibling();
					break;
				}
				case COLON:
				{
					c = (AST)_t;
					match(_t,COLON);
					_t = _t.getNextSibling();
					if (d2 == null) s.setMultivalued();
					break;
				}
				default:
				{
					throw new NoViableAltException(_t);
				}
				}
				}
				{
				if (_t==null) _t=ASTNULL;
				switch ( _t.getType()) {
				case DUMMY:
				{
					d3 = (AST)_t;
					match(_t,DUMMY);
					_t = _t.getNextSibling();
					break;
				}
				case PLUS:
				case MINUS:
				case MULT:
				case DIVD:
				case MOD:
				case AND:
				case OR:
				case NOT:
				case EQ:
				case NE:
				case LT:
				case GT:
				case LE:
				case GE:
				case INTEGER:
				case ID:
				case STRING:
				case ADD_INVS:
				case MULT_INVS:
				case FUNC_CALL:
				case EXPR_LIST:
				case PERM_CYCLE:
				case PERM_IMAGE:
				case PERM_ID:
				case LIST:
				case SLICE:
				case LITERAL_sizeof:
				case LITERAL_true:
				case LITERAL_false:
				{
					b=expression(_t);
					_t = _retTree;
					if (d3 == null) s.setRight (b);
					break;
				}
				default:
				{
					throw new NoViableAltException(_t);
				}
				}
				}
				r = s.valueOf();
				_t = __t159;
				_t = _t.getNextSibling();
				break;
			}
			case EXPR_LIST:
			{
				AST __t163 = _t;
				AST tmp18_AST_in = (AST)_t;
				match(_t,EXPR_LIST);
				_t = _t.getFirstChild();
				r = new uPermList();
				{
				_loop165:
				do {
					if (_t==null) _t=ASTNULL;
					if ((_tokenSet_1.member(_t.getType()))) {
						a=expression(_t);
						_t = _retTree;
						r.append(a);
					}
					else {
						break _loop165;
					}
					
				} while (true);
				}
				_t = __t163;
				_t = _t.getNextSibling();
				break;
			}
			case OR:
			{
				AST __t166 = _t;
				AST tmp19_AST_in = (AST)_t;
				match(_t,OR);
				_t = _t.getFirstChild();
				a=expression(_t);
				_t = _retTree;
				b=expression(_t);
				_t = _retTree;
				r = a.or (b);
				_t = __t166;
				_t = _t.getNextSibling();
				break;
			}
			case AND:
			{
				AST __t167 = _t;
				AST tmp20_AST_in = (AST)_t;
				match(_t,AND);
				_t = _t.getFirstChild();
				a=expression(_t);
				_t = _retTree;
				b=expression(_t);
				_t = _retTree;
				r = a.and (b);
				_t = __t167;
				_t = _t.getNextSibling();
				break;
			}
			case EQ:
			{
				AST __t168 = _t;
				AST tmp21_AST_in = (AST)_t;
				match(_t,EQ);
				_t = _t.getFirstChild();
				a=expression(_t);
				_t = _retTree;
				b=expression(_t);
				_t = _retTree;
				r = a.equal (b);
				_t = __t168;
				_t = _t.getNextSibling();
				break;
			}
			case NE:
			{
				AST __t169 = _t;
				AST tmp22_AST_in = (AST)_t;
				match(_t,NE);
				_t = _t.getFirstChild();
				a=expression(_t);
				_t = _retTree;
				b=expression(_t);
				_t = _retTree;
				r = a.notequal (b);
				_t = __t169;
				_t = _t.getNextSibling();
				break;
			}
			case LT:
			{
				AST __t170 = _t;
				AST tmp23_AST_in = (AST)_t;
				match(_t,LT);
				_t = _t.getFirstChild();
				a=expression(_t);
				_t = _retTree;
				b=expression(_t);
				_t = _retTree;
				r = a.lt (b);
				_t = __t170;
				_t = _t.getNextSibling();
				break;
			}
			case GT:
			{
				AST __t171 = _t;
				AST tmp24_AST_in = (AST)_t;
				match(_t,GT);
				_t = _t.getFirstChild();
				a=expression(_t);
				_t = _retTree;
				b=expression(_t);
				_t = _retTree;
				r = a.gt (b);
				_t = __t171;
				_t = _t.getNextSibling();
				break;
			}
			case LE:
			{
				AST __t172 = _t;
				AST tmp25_AST_in = (AST)_t;
				match(_t,LE);
				_t = _t.getFirstChild();
				a=expression(_t);
				_t = _retTree;
				b=expression(_t);
				_t = _retTree;
				r = a.le (b);
				_t = __t172;
				_t = _t.getNextSibling();
				break;
			}
			case GE:
			{
				AST __t173 = _t;
				AST tmp26_AST_in = (AST)_t;
				match(_t,GE);
				_t = _t.getFirstChild();
				a=expression(_t);
				_t = _retTree;
				b=expression(_t);
				_t = _retTree;
				r = a.ge (b);
				_t = __t173;
				_t = _t.getNextSibling();
				break;
			}
			case MULT:
			{
				AST __t174 = _t;
				AST tmp27_AST_in = (AST)_t;
				match(_t,MULT);
				_t = _t.getFirstChild();
				a=expression(_t);
				_t = _retTree;
				b=expression(_t);
				_t = _retTree;
				r = a.multiply (b);
				_t = __t174;
				_t = _t.getNextSibling();
				break;
			}
			case DIVD:
			{
				AST __t175 = _t;
				AST tmp28_AST_in = (AST)_t;
				match(_t,DIVD);
				_t = _t.getFirstChild();
				a=expression(_t);
				_t = _retTree;
				b=expression(_t);
				_t = _retTree;
				r = a.divide (b);
				_t = __t175;
				_t = _t.getNextSibling();
				break;
			}
			case MOD:
			{
				AST __t176 = _t;
				AST tmp29_AST_in = (AST)_t;
				match(_t,MOD);
				_t = _t.getFirstChild();
				a=expression(_t);
				_t = _retTree;
				b=expression(_t);
				_t = _retTree;
				r = a.mod (b);
				_t = __t176;
				_t = _t.getNextSibling();
				break;
			}
			case PLUS:
			{
				AST __t177 = _t;
				AST tmp30_AST_in = (AST)_t;
				match(_t,PLUS);
				_t = _t.getFirstChild();
				a=expression(_t);
				_t = _retTree;
				b=expression(_t);
				_t = _retTree;
				r = a.plus (b);
				_t = __t177;
				_t = _t.getNextSibling();
				break;
			}
			case MINUS:
			{
				AST __t178 = _t;
				AST tmp31_AST_in = (AST)_t;
				match(_t,MINUS);
				_t = _t.getFirstChild();
				a=expression(_t);
				_t = _retTree;
				b=expression(_t);
				_t = _retTree;
				r = a.minus (b);
				_t = __t178;
				_t = _t.getNextSibling();
				break;
			}
			case ADD_INVS:
			{
				AST __t179 = _t;
				AST tmp32_AST_in = (AST)_t;
				match(_t,ADD_INVS);
				_t = _t.getFirstChild();
				a=expression(_t);
				_t = _retTree;
				r = a.addInverse();
				_t = __t179;
				_t = _t.getNextSibling();
				break;
			}
			case MULT_INVS:
			{
				AST __t180 = _t;
				AST tmp33_AST_in = (AST)_t;
				match(_t,MULT_INVS);
				_t = _t.getFirstChild();
				a=expression(_t);
				_t = _retTree;
				r = a.multInverse();
				_t = __t180;
				_t = _t.getNextSibling();
				break;
			}
			case NOT:
			{
				AST __t181 = _t;
				AST tmp34_AST_in = (AST)_t;
				match(_t,NOT);
				_t = _t.getFirstChild();
				a=expression(_t);
				_t = _retTree;
				r = a.not();
				_t = __t181;
				_t = _t.getNextSibling();
				break;
			}
			case FUNC_CALL:
			{
				AST __t182 = _t;
				AST tmp35_AST_in = (AST)_t;
				match(_t,FUNC_CALL);
				_t = _t.getFirstChild();
				fname_ = (AST)_t;
				match(_t,ID);
				_t = _t.getNextSibling();
				args = (AST)_t;
				if ( _t==null ) throw new MismatchedTokenException();
				_t = _t.getNextSibling();
				r = upIntr.executeFunctionCall (fname_.getText(), args);
				_t = __t182;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_true:
			{
				{
				AST tmp36_AST_in = (AST)_t;
				match(_t,LITERAL_true);
				_t = _t.getNextSibling();
				r = new uPermBoolean (true);
				}
				break;
			}
			case LITERAL_false:
			{
				{
				AST tmp37_AST_in = (AST)_t;
				match(_t,LITERAL_false);
				_t = _t.getNextSibling();
				r = new uPermBoolean (false);
				}
				break;
			}
			case LITERAL_sizeof:
			{
				AST __t185 = _t;
				AST tmp38_AST_in = (AST)_t;
				match(_t,LITERAL_sizeof);
				_t = _t.getFirstChild();
				a=expression(_t);
				_t = _retTree;
				r=a.sizeof();
				_t = __t185;
				_t = _t.getNextSibling();
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
		return r;
	}
	
	public final uPermPermutation  permutation(AST _t) throws RecognitionException {
		uPermPermutation p;
		
		AST permutation_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		p = new uPermPermutation();
		uPermType a = null;
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case PERM_CYCLE:
			{
				AST __t187 = _t;
				AST tmp39_AST_in = (AST)_t;
				match(_t,PERM_CYCLE);
				_t = _t.getFirstChild();
				
				{
				_loop189:
				do {
					if (_t==null) _t=ASTNULL;
					if ((_tokenSet_1.member(_t.getType()))) {
						a=expression(_t);
						_t = _retTree;
						p.attach (a);
					}
					else {
						break _loop189;
					}
					
				} while (true);
				}
				_t = __t187;
				_t = _t.getNextSibling();
				p.complete();
				break;
			}
			case PERM_IMAGE:
			{
				AST __t190 = _t;
				AST tmp40_AST_in = (AST)_t;
				match(_t,PERM_IMAGE);
				_t = _t.getFirstChild();
				int count = 0;
				{
				_loop192:
				do {
					if (_t==null) _t=ASTNULL;
					if ((_tokenSet_1.member(_t.getType()))) {
						a=expression(_t);
						_t = _retTree;
						p.setImage (++count, a);
					}
					else {
						break _loop192;
					}
					
				} while (true);
				}
				_t = __t190;
				_t = _t.getNextSibling();
				
				break;
			}
			case PERM_ID:
			{
				AST __t193 = _t;
				AST tmp41_AST_in = (AST)_t;
				match(_t,PERM_ID);
				_t = _t.getFirstChild();
				
				_t = __t193;
				_t = _t.getNextSibling();
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
		return p;
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
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 2232664879683076096L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { -2305281098638168192L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	}
	
