import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;


public class ReadNumbersMain {
   public static void main(String[] args) {
      InputStream in_stream = null;
      try {
         try {
            in_stream = new FileInputStream(new File("numbers.txt"));
         } catch (FileNotFoundException e) {
            System.err.println("ERROR: reading number file!");
            System.exit(1);
         }
         // create a CharStream that reads from standard input:
         // CharStream input = CharStreams.fromStream(System.in);
         ANTLRInputStream input = new ANTLRInputStream(System.in);
         // create a lexer that feeds off of input CharStream:
         ReadNumbersLexer lexer = new ReadNumbersLexer(input);
         // create a buffer of tokens pulled from the lexer:
         CommonTokenStream tokens = new CommonTokenStream(lexer);
         // create a parser that feeds off the tokens buffer:
         ReadNumbersParser parser = new ReadNumbersParser(tokens);
         // replace error listener:
         // parser.removeErrorListeners(); // remove ConsoleErrorListener
         // parser.addErrorListener(new ErrorHandlingListener());
         // begin parsing at file rule:
         ParseTree tree = parser.file();
         if (parser.getNumberOfSyntaxErrors() == 0) {
            ParseTreeWalker walker = new ParseTreeWalker();
            ConstructNumberMap map = new ConstructNumberMap();
            walker.walk(map, tree);

            Scanner in = new Scanner(System.in);
            while (in.hasNextLine()) {
               String line = in.nextLine();
               String[] words = line.split(" - ");
               for (int i = 0; i < words.length; i++) {
                  if (i != 0) {
                     System.out.println(" ");
                     if (map.exists(words[i])) {
                        System.out.println(map.value(words[i]).toString());
                     } else {
                        System.out.println(words[i]);
                     }
                     System.out.println();
                  }
               }
            }
         }
      } catch (IOException e) {
         e.printStackTrace();
         System.exit(1);
      } catch (RecognitionException e) {
         e.printStackTrace();
         System.exit(1);
      }
   }
}
