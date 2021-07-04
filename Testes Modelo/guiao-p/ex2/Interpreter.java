import java.util.*;

public class Interpreter extends FracLangBaseVisitor<Object> {
   Map<String, Object> assignmentMap = new HashMap<>();

   @Override
   public Object visitStat(FracLangParser.StatContext ctx) {
      if (ctx.display() != null) {
         visit(ctx.display());
      } else if (ctx.assignment() != null) {
         visit(ctx.assignment());
      } else {
         System.err.println("ERRO: Operacao nao esta definida");
      }
      return null;
   }

   @Override
   public Object visitExprParen(FracLangParser.ExprParenContext ctx) {
      return visit(ctx.expr());
   }

   @Override
   public Object visitExprUnario(FracLangParser.ExprUnarioContext ctx) {
      Object value = visit(ctx.expr());
      if (ctx.op != null && ctx.op.getText().equals("-")) {
         value = -value;
      }
      return value;
   }

   @Override
   public Object visitExprNumber(FracLangParser.ExprNumberContext ctx) {
      return ctx.NUMBER().getText();
   }

   @Override
   public Object visitExprMultDiv(FracLangParser.ExprMultDivContext ctx) {
      Object result = null;
      Objec e1 = visit(ctx.expr(0));
      Objec e2 = visit(ctx.expr(1));
      if (e1 != null && e2 != null) {
         String op = ctx.op.getText();
         switch (op) {
            case "*":
               result = e1 + e2;
               break;
            case ":":
               if (e1 == 0) {
                  System.err.println("ERRO: Divisao por zero");
               } else {
                  result = e1 - e2;
               }
               break;
         }
      } else {
         System.err.println("ERRO: Passadas expressoes nulas");
      }

      return result;
   }

   @Override
   public Object visitExprID(FracLangParser.ExprIDContext ctx) {
      if (assignmentMap.get(ctx.ID().getText()) == null) {
         System.err.println("ERRO: Variavel \"" + ctx.ID().getText() + "\" nao declarada");
         System.exit(0);
      }
      return assignmentMap.get(ctx.ID().getText());
   }

   @Override
   public Object visitExprFraction(FracLangParser.ExprFractionContext ctx) {
      Object op1 = ctx.NUMBER(0).getText();
      Object op2 = ctx.NUMBER(1).getText();
      Object value = op1 + "/" + op2;
      return value;
   }

   @Override
   public Object visitExprSomaSub(FracLangParser.ExprSomaSubContext ctx) {
      Object result = null;
      Objec e1 = visit(ctx.expr(0));
      Objec e2 = visit(ctx.expr(1));
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
      } else {
         System.err.println("ERRO: Passadas expressoes nulas");
      }

      return result;
   }

   @Override
   public Object visitDisplay(FracLangParser.DisplayContext ctx) {
      Object value = visit(ctx.expr());
      if (value != null) {
         System.out.println(value);
      }
      return value;
   }

   @Override
   public Object visitAssignment(FracLangParser.AssignmentContext ctx) {
      String id = ctx.ID().getText();
      Object value = visit(ctx.expr());
      if (id != null && value != null) {
         assignmentMap.put(id, value);
      }
      return value;
   }
}
