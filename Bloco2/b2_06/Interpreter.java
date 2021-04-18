import java.util.HashMap;
import java.util.Map;

public class Interpreter extends CalculatorBaseVisitor<Long> {

   protected Map<String, Long> assignmentMap = new HashMap<String, Long>();

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

   @Override
   public Long visitAssignment(CalculatorParser.AssignmentContext ctx) {
      assignmentMap.put(ctx.ID().getText(), (Long) visit(ctx.expr()));
      return null;
   }

   @Override
   public Long visitExprID(CalculatorParser.ExprIDContext ctx) {
      if (assignmentMap.containsKey(ctx.ID().getText())) {
         return assignmentMap.get(ctx.ID().getText());
      } else {
         System.err.println("ERROR: Assignment not defined!");
         System.exit(1);
         return null;
      }
   }

}