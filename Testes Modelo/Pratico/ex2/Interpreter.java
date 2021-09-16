import java.util.*;

public class Interpreter extends FracLangBaseVisitor<Fraction> {
   
   Map<String, Fraction> assignmentMap = new HashMap<>();

   @Override
   public Fraction visitStat(FracLangParser.StatContext ctx) {
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
   public Fraction visitExprParen(FracLangParser.ExprParenContext ctx) {
      return visit(ctx.expr());
   }

   @Override
   public Fraction visitExprUnario(FracLangParser.ExprUnarioContext ctx) {
      Fraction value = visit(ctx.expr());
      int num = 0;
      if (ctx.op != null && ctx.op.getText().equals("-")) {
         num = -value.getNumerator();
      }
      return new Fraction(num, value.getDenominator());
   }

   @Override
   public Fraction visitExprNumber(FracLangParser.ExprNumberContext ctx) {
      int num = Integer.parseInt(ctx.NUMBER().getText());
      return new Fraction(num, 1);
   }

   @Override
   public Fraction visitExprMultDiv(FracLangParser.ExprMultDivContext ctx) {
      Fraction result = null;
      Fraction e1 = visit(ctx.expr(0));
      Fraction e2 = visit(ctx.expr(1));
      if (e1 != null && e2 != null) {
         String op = ctx.op.getText();
         switch (op) {
            case "*":
               result = e1.mult(e2);
               break;
            case ":":
               if (e2.getDenominator() == 0) {
                  System.err.println("ERRO: Divisao por zero");
               } else {
                  result = e1.div(e2);
               }
               break;
         }
      } else {
         System.err.println("ERRO: Passadas expressoes nulas");
      }

      return result;
   }

   @Override
   public Fraction visitExprID(FracLangParser.ExprIDContext ctx) {
      if (!assignmentMap.containsKey(ctx.ID().getText())) {
         System.err.println("ERRO: Variavel \"" + ctx.ID().getText() + "\" nao declarada");
         System.exit(1);
      }
      return assignmentMap.get(ctx.ID().getText());

   }

   @Override
   public Fraction visitExprFraction(FracLangParser.ExprFractionContext ctx) {
      int num = Integer.parseInt(ctx.NUMBER(0).getText());
      int dem = Integer.parseInt(ctx.NUMBER(1).getText());
      if (dem == 0) {
         System.err.println("ERRO: Divisao por zero");
         System.exit(1);
      }
      return new Fraction(num, dem);
   }

   @Override
   public Fraction visitExprReduce(FracLangParser.ExprReduceContext ctx) {
      Fraction result = visit(ctx.expr());
      result.reduce();
      return result;
   }
   
   @Override
   public Fraction visitExprSomaSub(FracLangParser.ExprSomaSubContext ctx) {
      Fraction result = null;
      Fraction e1 = visit(ctx.expr(0));
      Fraction e2 = visit(ctx.expr(1));
      if (e1 != null && e2 != null) {
         String op = ctx.op.getText();
         switch (op) {
            case "+":
               result = e1.add(e2);
               break;
            case "-":
               result = e1.sub(e2);
               break;
         }
      } else {
         System.err.println("ERRO: Passadas expressoes nulas");
      }
      return result;
   }

   @Override
   public Fraction visitDisplay(FracLangParser.DisplayContext ctx) {
      Fraction value = visit(ctx.expr());
      if (value != null) {
         System.out.println(value);
      }
      return value;
   }

   @Override
   public Fraction visitAssignment(FracLangParser.AssignmentContext ctx) {
      String id = ctx.ID().getText();
      Fraction value = visit(ctx.expr());
      if (id != null && value != null) {
         assignmentMap.put(id, value);
      }
      return value;
   }
}
