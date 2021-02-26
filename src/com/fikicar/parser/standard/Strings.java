package com.fikicar.parser.standard;

import com.fikicar.parser.Parser;
import javafx.util.Pair;

public class Strings {

    /**
     * isString checks if token at index is string or is stringStore contains string with tokens name
     *
     * @param index supposed position of string
     * @return Pair with key of 0 if its not a string or index if its a string and value of string
     */
    public static Pair isString(int index) {
        Pair ret = new Pair(0, "");
        if (Parser.tokens.get(index).key.equals("STRING")) {
            return new Pair(index, Parser.tokens.get(index).value);
        }
        if (Parser.stringStore.containsKey(Parser.tokens.get(index).value)) {
            return new Pair(index, Parser.stringStore.get(Parser.tokens.get(index).value));
        }
        if (index + 5 < Parser.tokens.size() && (int) Arrays.getArrayValue(index, 3).getKey() != 0) {
            return new Pair(Arrays.getArrayValue(index, 3).getKey(), Arrays.getArrayValue(index, 3).getValue());
        }
        if (index + 7 < Parser.tokens.size() && (int) Matrixes.getMatrixValue(index, 3).getKey() != 0) {
            return new Pair(Matrixes.getMatrixValue(index, 3).getKey(), Matrixes.getMatrixValue(index, 3).getValue());
        }
        return ret;
    }
}
