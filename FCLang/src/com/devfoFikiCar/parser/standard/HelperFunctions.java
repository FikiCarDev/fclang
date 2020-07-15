package com.devfoFikiCar.parser.standard;

import com.devfoFikiCar.parser.Parser;

public class HelperFunctions {
    // searches for } <- return index from }
    public static int search_r_b(int index) {
        int r_pos = 0;
        int n_skip = 0;
        for (int i = ++index; i < Parser.tokens.size(); i++) {
            if (Parser.tokens.get(i).key == "L_BRACES") n_skip++;
            else if (Parser.tokens.get(i).key == "R_BRACES") {
                if (n_skip == 0) {
                    return i;
                } else {
                    n_skip--;
                }
            }
        }
        return r_pos;
    }
}
