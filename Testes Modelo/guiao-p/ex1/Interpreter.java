import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Interpreter extends StrLangBaseVisitor<String> {

   Map<String, String> assignmentMap = new HashMap<>();

   @Override
   public String visitProgram(StrLangParser.ProgramContext ctx) {
      return visitChildren(ctx);
   }

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
   public String visitPrint(StrLangParser.PrintContext ctx) {
      String id = null;
      String value = null;

      if (ctx.TEXT() != null) {
         value = ctx.TEXT().getText();
         value = value.replace("\"", "");
         System.out.println(value);
      }

      if (ctx.ID() != null) {
         id = ctx.ID().getText();
         System.out.println(assignmentMap.get(id));
      }

      return null;
   }

   @Override
   public String visitAssignment(StrLangParser.AssignmentContext ctx) {
      String id = ctx.ID().getText();

      if (ctx.TEXT() != null) {
         String value = ctx.TEXT().getText();
         value = value.replace("\"", "");
         assignmentMap.put(id, value);
         return null;
      }

      if (ctx.input() != null) {
         Scanner sc = new Scanner(System.in);
         String input = ctx.input().TEXT().getText();
         input = input.replace("\"", "");
         System.out.println(input);
         String value = null;
         value = sc.next();
         System.out.println("AQUI2");
         assignmentMap.put(id, value);
      
      }

      return null;
   }
}