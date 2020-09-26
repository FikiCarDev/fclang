package com.devfoFikiCar.parser.Math;

import com.devfoFikiCar.parser.Parser;

public class Sqrt extends OneExpression{
    public static int[] mathSqrtInt(int index) {
        int[] ret = new int[2];
        if (!Parser.tokens.get(index).key.equals("SQRT")) {
            return ret;
        }
        index++;
        int[] retInt = getOneIntExpression(index);
        ret[0] = retInt[0];
        ret[1] = (int) Math.sqrt(retInt[1]);
        return ret;
    }

    public static double[] mathSqrtDecimal(int index) {
        double[] ret = new double[2];
        if (!Parser.tokens.get(index).key.equals("SQRT")) {
            return ret;
        }
        index++;
        double[] retDecimal = getOneDecimalExpression(index);
        ret[0] = retDecimal[0];
        ret[1] = Math.sqrt(retDecimal[1]);
        return ret;
    }
}
