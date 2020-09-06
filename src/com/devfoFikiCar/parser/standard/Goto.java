package com.devfoFikiCar.parser.standard;

import com.devfoFikiCar.parser.Parser;

public class Goto {

    /**
     * safeGoto detects all end goto labels, and stores their position.
     */
    public static void safeGoto() {
        for (int index = 0; index < Parser.tokens.size(); index++) {
            if (Parser.tokens.get(index).key == "L_GOTO") {
                Parser.gotoStore.put(Parser.tokens.get(index).value, index);
            }
        }
    }

    /**
     * gotoFunction skips to the correct goto label.
     *
     * @param index begin position for parsing
     * @return index to continue parsing from
     */
    public static int gotoFunction(int index) {
        if (Parser.tokens.get(index + 1).key == "L_GOTO") {
            index++;
        } else return -1;

        if (Parser.gotoStore.containsKey(Parser.tokens.get(index).value)) {
            index = Parser.gotoStore.get(Parser.tokens.get(index).value);
        } else return -1;

        return index;
    }
}
