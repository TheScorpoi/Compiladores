import java.util.*;

public class Execute extends conjCalcBaseVisitor<Conjunto> {

   protected Map<String, Conjunto> symbolTable = new HashMap<String, Conjunto>();

   @Override public Conjunto visitPrint(conjCalcParser.PrintContext ctx) {
      Conjunto conj = visit(ctx.expr());
      if(conj != null){
         System.out.println(visit(ctx.expr()));
      }
      return conj;
   }

   @Override public Conjunto visitExprInterset(conjCalcParser.ExprIntersetContext ctx) {
      Conjunto c1 = visit(ctx.expr(0));
      Conjunto c2 = visit(ctx.expr(1));
      
      return c1.interset(c2);
   }

   @Override public Conjunto visitExprSubtract(conjCalcParser.ExprSubtractContext ctx) {
      Conjunto c1 = visit(ctx.expr(0));
      Conjunto c2 = visit(ctx.expr(1));
      return c1.subtract(c2);
   }

   @Override public Conjunto visitExprParent(conjCalcParser.ExprParentContext ctx) {
      return visit(ctx.expr());
   }

   @Override public Conjunto visitExprUnion(conjCalcParser.ExprUnionContext ctx) {
      Conjunto c1 = visit(ctx.expr(0));
      Conjunto c2 = visit(ctx.expr(1));

      return c1.union(c2);
   }

   @Override public Conjunto visitExprAssign(conjCalcParser.ExprAssignContext ctx) {
      Conjunto value = visit(ctx.expr());
      String id = ctx.ID().getText();
      symbolTable.put(id, value);
      return value;
   }

   @Override public Conjunto visitExprID(conjCalcParser.ExprIDContext ctx) {
      if (!symbolTable.containsKey(ctx.ID().getText())) {
         System.err.println("Nao ha essa chave meu menino");
         System.exit(1);
      }
      return symbolTable.get(ctx.ID().getText());
   }

   @Override public Conjunto visitExprConj(conjCalcParser.ExprConjContext ctx) {
      int sizeStr = ctx.conjunto().getText().length() ;
      String semChaveta = ctx.conjunto().getText().substring(1, sizeStr - 1);
      String[] elementos = semChaveta.split(",");
      return new Conjunto(elementos);
   }

   //@Override public Conjunto visitConjunto(conjCalcParser.ConjuntoContext ctx) {
   //  return visitChildren(ctx);
   //}

   @Override public Conjunto visitElem(conjCalcParser.ElemContext ctx) {
      return visitChildren(ctx);
   }
}
