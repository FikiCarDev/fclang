package com.devfoFikiCar.parser.standard;

import com.devfoFikiCar.parser.Parser;
import javafx.util.Pair;

public class Strings {

    /**
     * isString checks if token at index is string or is stringStore contains string with tokens name
     *
     * @param index supposed position of string
     * @return Pair with key of 0 if its not a string or index if its a string and value of string
     */
    public static Pair<Integer, String> isString(int index) {
        Pair ret = new Pair(0, "");
        if (Parser.tokens.get(index).key == "STRING") {
            Pair ret1 = new Pair(index, Parser.tokens.get(index).value);
            return ret1;
        }
        if (Parser.stringStore.containsKey(Parser.tokens.get(index).value)) {
            Pair ret1 = new Pair(index, Parser.stringStore.get(Parser.tokens.get(index).value));
            return ret1;
        }
        return ret;
    }
}
