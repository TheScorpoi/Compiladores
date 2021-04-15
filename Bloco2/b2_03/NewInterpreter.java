public class NewInterpreter extends CalculatorBaseVisitor<Long> {

   @Override
   public Long visitStat(CalculatorParser.StatContext ctx) {
      if (ctx.expr() != null) {
         Long result = visit(ctx.expr());
         if (result != null) {
            System.out.println("Resultado: " + result);
         }
      }
      return null;
   }

   @Override
   public Long visitExprAddSub(CalculatorParser.ExprAddSubContext ctx) {
      Long result = null;
      Long e1 = (Long) visit(ctx.expr(0));
      Long e2 = (Long) visit(ctx.expr(1));
      if (e1 != null && e2 != null) {
         String op = ctx.op.getText();
         switch (op) {
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

   @Override
   public Long visitExprParent(CalculatorParser.ExprParentContext ctx) {
      return visit(ctx.expr());
   }

   @Override
   public Long visitExprInteger(CalculatorParser.ExprIntegerContext ctx) {
      return Long.parseLong(ctx.Integer().getText());
   }

   @Override
   public Long visitExprMultDivMod(CalculatorParser.ExprMultDivModContext ctx) {
      Long result = null;
      Long e1 = (Long) visit(ctx.expr(0));
      Long e2 = (Long) visit(ctx.expr(1));
      if (e1 != null && e2 != null) {
         String op = ctx.op.getText();
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
         case "%":
            if (e2 == 0) {
               System.err.println("Erro: Divide by Zero");
            } else {
               result = e1 % e2;
            }
            break;
         }
      }
      return result;
   }

   @Override
   public Long visitExprUnario(CalculatorParser.ExprUnarioContext ctx) {
      Long result = visit(ctx.expr());
      if (ctx.op != null && ctx.op.getText().equals("-")) {
         result = -result;
      }
      return result;
   }
}
