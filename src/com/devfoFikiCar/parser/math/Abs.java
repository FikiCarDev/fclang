package com.devfoFikiCar.parser.math;

import com.devfoFikiCar.parser.Parser;

public class Abs extends OneExpression {
    public static int[] mathAbsInt(int index) {
        int[] ret = new int[2];
        if (!Parser.tokens.get(index).key.equals("ABS")) {
            return ret;
        }
        index++;
        int[] retInt = getOneIntExpression(index);
        ret[0] = retInt[0];
        ret[1] = Math.abs(retInt[1]);
        return ret;
    }

    public static double[] mathAbsDecimal(int index) {
        double[] ret = new double[2];
        if (!Parser.tokens.get(index).key.equals("ABS")) {
            return ret;
        }
        index++;
        double[] retDecimal = getOneDecimalExpression(index);
        ret[0] = retDecimal[0];
        ret[1] = Math.abs(retDecimal[1]);
        return ret;
    }
}
