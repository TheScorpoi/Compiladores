// Generated from ReadNumbers.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ReadNumbersParser}.
 */
public interface ReadNumbersListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ReadNumbersParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(ReadNumbersParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReadNumbersParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(ReadNumbersParser.FileContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReadNumbersParser#line}.
	 * @param ctx the parse tree
	 */
	void enterLine(ReadNumbersParser.LineContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReadNumbersParser#line}.
	 * @param ctx the parse tree
	 */
	void exitLine(ReadNumbersParser.LineContext ctx);
}