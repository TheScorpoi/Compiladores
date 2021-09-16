import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Interpreter extends StrLangBaseVisitor<String> {

   private Map<String, String> assignmentMap = new HashMap<>();
   private Scanner sc = new Scanner(System.in);

   @Override
   public String visitStat(StrLangParser.StatContext ctx) {
      if (ctx.print() != null) {
         visit(ctx.print());
      } else if (ctx.assignment() != null) {
         visit(ctx.assignment());
      }
      return null;
   }

   @Override
   public String visitExprConRem(StrLangParser.ExprConRemContext ctx) {
      String result = null;
      String e1 = visit(ctx.expr(0));
      String e2 = visit(ctx.expr(1));
      String op = ctx.op.getText();
      if (e1 != null && e2 != null) {
         switch (op) {
            case "+":
               result = e1 + e2;
               break;
            case "-":
               result = e1;
               result = result.replaceAll(e2, "");
               break;
         }
      }
      return result;
   }

   @Override
   public String visitExprSubs(StrLangParser.ExprSubsContext ctx) {
      String result = null;
      String id = visit(ctx.expr(0));
      String rep1 = visit(ctx.expr(1));;
      String rep2 = visit(ctx.expr(2));

      if (assignmentMap.containsKey(id)) {
         result = assignmentMap.get(id);
      } else {
         result = id;
      }
      result = result.replaceAll(rep1, rep2);

      return result;
   }

   @Override
   public String visitExprParent(StrLangParser.ExprParentContext ctx) {
      return visit(ctx.expr());
   }

   @Override
   public String visitExprText(StrLangParser.ExprTextContext ctx) {
      String result = ctx.TEXT().getText();
      result = result.replaceAll("\"", "");
      return result;
   }

   @Override
   public String visitExprInput(StrLangParser.ExprInputContext ctx) {
      String inputText = ctx.TEXT().getText();
      inputText = inputText.replaceAll("\"", "");
      System.out.println(inputText);
      String input = sc.nextLine();

      return input;
   }

   @Override
   public String visitExprTrim(StrLangParser.ExprTrimContext ctx) {
      String expr = visit(ctx.expr());
      if (expr != null) {
         expr = expr.trim();
      }
      return expr;
   }

   @Override
   public String visitExprID(StrLangParser.ExprIDContext ctx) {
      String id = ctx.ID().getText();
      if (assignmentMap.containsKey(id)) {
         return assignmentMap.get(id);
      } else {
         System.err.println("ERRO: Variavel nao definida");
      }
      return "";
   }

   @Override
   public String visitPrint(StrLangParser.PrintContext ctx) {
      String result = visit(ctx.expr());
      if (result != null) {
         System.out.println(result);
      } else {
         System.err.println("Text ou ID mal definidos");
      }

      return "";
   }

   @Override
   public String visitAssignment(StrLangParser.AssignmentContext ctx) {
      String id = ctx.ID().getText();
      String value = visit(ctx.expr());
      if (id != null || value != null) {
         assignmentMap.put(id, value);
      } else {
         System.err.println("ERRO: Assignment feito de forma incorreta!");
      }
      return "";
   }
}