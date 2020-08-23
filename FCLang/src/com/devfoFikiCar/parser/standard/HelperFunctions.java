package com.devfoFikiCar.parser.standard;

import com.devfoFikiCar.parser.Parser;

public class HelperFunctions {
    // searches for } <- return index from }
    public static int searchRightBracket(int index) {
        int rPos = 0;
        int nSkip = 0;
        for (int i = ++index; i < Parser.tokens.size(); i++) {
            if (Parser.tokens.get(i).key == "L_BRACES") nSkip++;
            else if (Parser.tokens.get(i).key == "R_BRACES") {
                if (nSkip == 0) {
                    return i;
                } else {
                    nSkip--;
                }
            }
        }
        return rPos;
    }
}
