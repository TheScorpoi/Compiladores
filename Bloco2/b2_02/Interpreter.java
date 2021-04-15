public class Interpreter extends SuffixCalculatorBaseVisitor<Double> {

   @Override
   public Double visitStat(SuffixCalculatorParser.StatContext ctx) {
      if (ctx.expr() != null) {
         Double result = visit(ctx.expr());
         if (result != null) {
            System.out.println("Resultado: " + result);
         }
      }
      return null;
   }

   @Override
   public Double visitExprNumber(SuffixCalculatorParser.ExprNumberContext ctx) {
      return Double.parseDouble(ctx.Number().getText());
   }

   @Override
   public Double visitExprSuffix(SuffixCalculatorParser.ExprSuffixContext ctx) {
      Double result = null;
      Double e1 = visit(ctx.expr(0));
      Double e2 = visit(ctx.expr(1));
      String op = ctx.op.getText();
      if (e1 != null && e2 != null) {
         
         switch (op) {
            case "*":
            result = e1 * e2;
            break;
            case "/":
            if (e2 == 0) {
               System.err.println("Erro: Divide by Zero");
            } else {
               result = e1 / e2;
            }
            break;
            case "+":
            result = e1 + e2;
            break;
            case "-":
            result = e1 - e2;
            break;
         }
      }
         
      return result;
   }
}
