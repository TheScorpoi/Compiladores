public class Interpreter extends QuestionBaseVisitor<String> {

   @Override public String visitProgram(QuestionParser.ProgramContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitQuestion(QuestionParser.QuestionContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitOption(QuestionParser.OptionContext ctx) {
      return visitChildren(ctx);
   }
}
