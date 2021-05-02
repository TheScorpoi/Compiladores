import java.io.FileReader;
import java.io.IOException;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Scanner;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class ListenersNumber extends NumbersBaseListener {
HashMap<String, Integer> dictionary = new HashMap<>();
String arguments;

   @Override public void enterLine(NumbersParser.LineContext ctx) {
      //podia ser aqui, mas é indiferente
   }

   @Override public void exitLine(NumbersParser.LineContext ctx) {
      //melhor aqui, garante que já iterou a árvore sintática 
      int num = Integer.parseInt(ctx.NUM().getText());
      dictionary.put(ctx.WORD().getText(),num);

      HashMap<String,String> dicionario = new HashMap<>();
     
        Scanner reader=null;

        try {
            reader= new Scanner(new FileReader("numbers.txt"));
        
        } catch (IOException e) {
            System.err.println("Não é possível ler o ficheiro!");
            System.exit(0);
        }
        
         while(reader.hasNextLine()){
            String linha = reader.nextLine();
            String [] dic = linha.split(" - "); //sugestao:usar trim
            dicionario.put(dic[1],dic[0]);
        }
        
        System.out.println("What numbers would you like to read?\n");
        Scanner sc = new Scanner(System.in);
        String input = "";

                while (sc.hasNext()){
                    input= sc.next();
                    if (dicionario.containsKey(input)){
                        input=dicionario.get(input);   
                    }
                    System.out.print(input);
                }

   }

   
}
