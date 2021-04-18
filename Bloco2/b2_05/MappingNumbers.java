package Bloco2.b2_05;

import java.util.HashMap;
import java.util.Map;

public class MappingNumbers extends ReadNumbersBaseListener{
    
    Map<String, Integer> numbersMap = new HashMap<String, Integer>();

    public boolean exists(String key) {
        assert key != null;
        return numbersMap.containsKey(key);
    }

    public Integer value(String key) {

        assert key != null;
        assert exists(key);
        return numbersMap.get(key);
    }

    @Override
    public void exitLine(ReadNumbersParser.LineContext ctx) {
        String key = ctx.WORD().getText();
        Integer value = Integer.parseInt(ctx.NUM().getText());

        if (exists(key)) {
            System.err.println("ERROR: repeated key");
            System.exit(1);
        }
        numbersMap.put(key, value);
    }
}
