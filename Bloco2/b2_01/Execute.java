
import java.util.Iterator;
import org.antlr.v4.runtime.tree.*;

public class Execute extends HelloBaseVisitor<String> {

   @Override
   public String visitGreetings(HelloParser.GreetingsContext ctx) {
      String tmp = "";
      for (TerminalNode word : ctx.ID()) {
         tmp += word + " ";
      }
      System.out.println("Ola " + tmp);
      return null;
   }

   @Override
   public String visitBye(HelloParser.ByeContext ctx) {
      String tmp = "";
      for (TerminalNode word : ctx.ID()) {
         tmp += word + " ";
      }
      System.out.println("Adeus " + tmp);
      return null;
   }
}
