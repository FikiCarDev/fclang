package com.devfoFikiCar.parser.standard;

import com.devfoFikiCar.parser.Parser;

public class Goto {
    public static void safe_goto() {
        for (int index = 0; index < Parser.tokens.size(); index++) {
            if (Parser.tokens.get(index).key == "L_GOTO") {
                Parser.goto_store.put(Parser.tokens.get(index).value, index);
            }
        }
    }

    public static int fgoto(int index) {
        if (Parser.tokens.get(index + 1).key == "L_GOTO") {
            index++;
        } else return -1;

        if (Parser.goto_store.containsKey(Parser.tokens.get(index).value)) {
            index = Parser.goto_store.get(Parser.tokens.get(index).value);
        } else return -1;

        return index;
    }
}
