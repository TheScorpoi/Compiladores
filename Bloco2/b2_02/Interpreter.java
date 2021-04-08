public class Interpreter extends SuffixCalculatorBaseVisitor<Double> {

   @Override public Double visitProgram(SuffixCalculatorParser.ProgramContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Double visitStat(SuffixCalculatorParser.StatContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Double visitExpr(SuffixCalculatorParser.ExprContext ctx) {
      return visitChildren(ctx);
   }
}
