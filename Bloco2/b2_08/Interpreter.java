public class Interpreter extends ConvertSuffixBaseVisitor<String> {

   @Override public String visitStat(ConvertSuffixParser.StatContext ctx) {
      if (ctx.expr() != null) {
         String result = visit(ctx.expr());
         if (result != null) {
            System.out.println(result);
         }
      }
      return null;
   }

   @Override public String visitExprAddSub(ConvertSuffixParser.ExprAddSubContext ctx) {
      String result = null;
      String e1 = visit(ctx.expr(0));
      String e2 = visit(ctx.expr(1));
      String op = ctx.op.getText();
      if (e1 != null && e2 != null) {
         result = e1 + " " + e2 + " "  + op;
      }
      return result;
   }

   @Override public String visitExprUnario(ConvertSuffixParser.ExprUnarioContext ctx) {
      String result = visit(ctx.expr());
      if (ctx.op != null && ctx.op.getText().equals("-")) {
         result = result + " !-";
      } else if (ctx.op != null && ctx.op.getText().equals("+")){
         result = result + " !+";
      }
      return result;
   }

   @Override public String visitExprParent(ConvertSuffixParser.ExprParentContext ctx) {
      return visit(ctx.expr());
   }

   @Override
   public String visitExprInteger(ConvertSuffixParser.ExprIntegerContext ctx) {
      return ctx.Integer().getText();
   }

   @Override
   public String visitAssignment(ConvertSuffixParser.AssignmentContext ctx) {
      System.out.println(ctx.ID().getText() + " = " + visit(ctx.expr()));
      return null; 
   }
   
   @Override
   public String visitExprID(ConvertSuffixParser.ExprIDContext ctx) {
      return ctx.ID().getText();
   }

   @Override public String visitExprMultDivMod(ConvertSuffixParser.ExprMultDivModContext ctx) {
      String result = null;
      String e1 = String.valueOf(visit(ctx.expr(0)));
      String e2 = String.valueOf(visit(ctx.expr(1)));
      String op = ctx.op.getText();
      if (e1 != null && e2 != null) {
         result = e1 + " " + e2 + " "  + op;
      }
      return result;
   }
}