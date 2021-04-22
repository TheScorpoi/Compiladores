public class Interpreter extends FractionalCalculatorBaseVisitor<Fraction> {

   @Override public Fraction visitExprPrint(FractionalCalculatorParser.ExprPrintContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Fraction visitPrintExpr(FractionalCalculatorParser.PrintExprContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Fraction visitAssignment(FractionalCalculatorParser.AssignmentContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Fraction visitInteger(FractionalCalculatorParser.IntegerContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Fraction visitExprAddSub(FractionalCalculatorParser.ExprAddSubContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Fraction visitParenthesis(FractionalCalculatorParser.ParenthesisContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Fraction visitInput(FractionalCalculatorParser.InputContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Fraction visitIdentifier(FractionalCalculatorParser.IdentifierContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Fraction visitFraction(FractionalCalculatorParser.FractionContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Fraction visitPotency(FractionalCalculatorParser.PotencyContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Fraction visitExprMultDiv(FractionalCalculatorParser.ExprMultDivContext ctx) {
      return visitChildren(ctx);
   }
}
