import java.util.*;

public class Execute extends CalcFracionalBaseVisitor<Fraction> {

   protected Map<String, Fraction> symbolTable = new HashMap<String, Fraction>();

   @Override
   public Fraction visitPrint(CalcFracionalParser.PrintContext ctx) {
      Fraction res = visit(ctx.expr());
      if (res != null) {
         if (res.denominator == 1) {
            System.out.println(res.numerator);
         } else {
            System.out.println(res);
         }

      }
      return res;
   }

   @Override
   public Fraction visitAssignment(CalcFracionalParser.AssignmentContext ctx) {
      Fraction value = visit(ctx.expr());
      String id = ctx.ID().getText();
      symbolTable.put(id, value);
      return value;
   }

   @Override
   public Fraction visitExprAddSub(CalcFracionalParser.ExprAddSubContext ctx) {
      Fraction res = null;
      Fraction num1 = visit(ctx.expr(0));
      Fraction num2 = visit(ctx.expr(1));

      if (num1 != null && num2 != null) {
         switch (ctx.op.getText()) {
         case "+":
            res = num1.add(num2);
            break;
         case "-":
            res = num1.subtract(num2);
            break;
         }
      }
      return res;
   }

   @Override
   public Fraction visitExprParent(CalcFracionalParser.ExprParentContext ctx) {
      return visit(ctx.expr());
   }

   @Override
   public Fraction visitExprInteger(CalcFracionalParser.ExprIntegerContext ctx) {
      int numero = Integer.parseInt(ctx.Integer().getText());
      return new Fraction(numero, 1);
   }

   @Override
   public Fraction visitExprUnitario(CalcFracionalParser.ExprUnitarioContext ctx) {  
      Integer num1 = Integer.parseInt(ctx.Integer(0).getText());
      Integer num2 = Integer.parseInt(ctx.Integer(1).getText());
      if (ctx.op.getText().equals("-")) {
         num1 = 0 - num1;
      }
      return new Fraction(num1, num2);
   }

   @Override
   public Fraction visitExprID(CalcFracionalParser.ExprIDContext ctx) {
      if (!symbolTable.containsKey(ctx.ID().getText())) {
         System.err.println("Nao ha essa chave meu menino");
         System.exit(1);
      }
      return symbolTable.get(ctx.ID().getText());
   }

   @Override
   public Fraction visitExprFraction(CalcFracionalParser.ExprFractionContext ctx) {
      Integer num1 = Integer.parseInt(ctx.Integer(0).getText());
      Integer num2 = Integer.parseInt(ctx.Integer(1).getText());
      if (num2 == 0) {
         System.err.println("Divide by zero");
         System.exit(1);
      }
      return new Fraction(num1, num2);
   }

   @Override
   public Fraction visitExprMultDivMod(CalcFracionalParser.ExprMultDivModContext ctx) {
      Fraction res = null;
      Fraction num1 = visit(ctx.expr(0));
      Fraction num2 = visit(ctx.expr(1));

      if (num1 != null && num2 != null) {
         switch (ctx.op.getText()) {
         case "*":
            res = num1.multiply(num2);
            break;
         case ":":
            if (num2.denominator != 0) {
               res = num1.divide(num2);
            } else {
               System.err.println("ERRO: Divide by zero");
            }
            break;
         }
      }
      return res;
   }

   @Override
   public Fraction visitExprReduce(CalcFracionalParser.ExprReduceContext ctx){
      Fraction fracao = visit(ctx.expr());
      fracao.reduce();
      return fracao;
   }

   @Override
   public Fraction visitExprPotencia(CalcFracionalParser.ExprPotenciaContext ctx){
      Fraction frac = visit(ctx.expr(0));
      Fraction frac2 = visit(ctx.expr(1));

      return frac.powtencia(frac2);
   }


   @Override
   public Fraction visitExprRead(CalcFracionalParser.ExprReadContext ctx){
      //So funciona se for passada so uma fracao
      //print (read "f" + read "x") //worka
      Scanner sc = new Scanner(System.in);
      System.out.print(ctx.expr().getText() + ": ");
      String[] tmpFrac = sc.nextLine().split("/");
      Fraction frac = new Fraction(Integer.parseInt(tmpFrac[0]) ,Integer.parseInt(tmpFrac[1]));

      return frac;
   }

}
