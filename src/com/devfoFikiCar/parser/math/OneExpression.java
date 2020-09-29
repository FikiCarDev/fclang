package com.devfoFikiCar.parser.math;

import com.devfoFikiCar.parser.Parser;
import com.devfoFikiCar.parser.standard.Decimals;
import com.devfoFikiCar.parser.standard.Integers;

public class OneExpression {
    public static int[] getOneIntExpression(int index) {
        int[] ret = new int[2];
        if (!Parser.tokens.get(index).key.equals("L_PARENTHESES")) {
            return ret;
        }
        index++;
        int[] retV = Integers.expressionInt(index, 0);
        if (retV[0] == 0) {
            return ret;
        }
        index = retV[0];
        if (!Parser.tokens.get(index).key.equals("R_PARENTHESES")) {
            return ret;
        }
        ret[0] = index;
        ret[1] = retV[1];
        return ret;
    }

    public static double[] getOneDecimalExpression(int index) {
        double[] ret = new double[2];
        if (!Parser.tokens.get(index).key.equals("L_PARENTHESES")) {
            return ret;
        }
        index++;
        double[] retV = Decimals.expressionDecimal(index, 0);
        if (retV[0] == 0) {
            return ret;
        }
        index = (int) retV[0];
        if (!Parser.tokens.get(index).key.equals("R_PARENTHESES")) {
            return ret;
        }
        ret[0] = index;
        ret[1] = retV[1];
        return ret;
    }
}
