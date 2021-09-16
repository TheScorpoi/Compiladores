import java.util.Map;
import java.util.HashMap;

public class Interpreter extends VectorBaseVisitor<Vector> {

   private Map<String, Vector> assignmentMap = new HashMap<>();

   @Override
   public Vector visitAssignment(VectorParser.AssignmentContext ctx) {
      String id = ctx.ID().getText();
      Vector vector = visit(ctx.expr());

      if (id != null && vector != null) {
         assignmentMap.put(id, vector);
      }
      return vector;
   }

   @Override
   public Vector visitShow(VectorParser.ShowContext ctx) {

      Vector result = visit(ctx.expr());
      if (result != null) {
         System.out.println(result);
      }
      return result;
   }

   @Override public Vector visitExprVector(VectorParser.ExprVectorContext ctx) {
      Vector vector = null;
      int i = 0;
      for (VectorParser.ExprContext number : ctx.expr()) {
         if (i == 0) {
            vector = new Vector(Double.parseDouble(number.getText()));
            i++;
         } else {
            vector.add(Double.parseDouble(number.getText()));
         }
      }
      return vector;
   }

   @Override
   public Vector visitExprUnario(VectorParser.ExprUnarioContext ctx) {
      Vector vector = visit(ctx.expr());
      if (ctx.op != null && ctx.op.getText().equals("-")) {
         vector.unario();
      }
      return vector;
   }

   @Override
   public Vector visitExprProdInter(VectorParser.ExprProdInterContext ctx) {
      Vector result = null;
      Vector v1 = visit(ctx.expr(0));
      Vector v2 = visit(ctx.expr(1));
      if (v1 != null && v2 != null) {
         result = v1.prodInter(v2);
      }

      return result;
   }
   
   @Override
   public Vector visitExprMult(VectorParser.ExprMultContext ctx) {
      Vector result = null;
      Vector v1 = visit(ctx.expr(0));
      Vector v2 = visit(ctx.expr(1));
      if (v1 != null && v2 != null) {
         result = v1.mult(v2);
      }
      
      return result;
   }

   @Override
   public Vector visitExprSumSub(VectorParser.ExprSumSubContext ctx) {
      Vector result = null;
      Vector v1 = visit(ctx.expr(0));
      Vector v2 = visit(ctx.expr(1));
      if (v1 != null && v2 != null) {
         switch (ctx.op.getText()) {
            case "+":
               result = v1.sum(v2);
               break;
            case "-":
               result = v1.sub(v2);
               break;
            
         }
      }
      return result;
   }

   @Override
   public Vector visitExprParent(VectorParser.ExprParentContext ctx) {
      return visit(ctx.expr());
   }


   @Override
   public Vector visitExprID(VectorParser.ExprIDContext ctx) {
      if (!assignmentMap.containsKey(ctx.ID().getText())) {
         System.err.println("ERRO: Variavel nao definida");
         System.exit(1);
      }
      return assignmentMap.get(ctx.ID().getText());
   }

   @Override public Vector visitExprNumber(VectorParser.ExprNumberContext ctx) {
      Vector result = new Vector(Double.parseDouble(ctx.NUMBER().getText()));
      return result;
   }

}
