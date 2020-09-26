package com.devfoFikiCar.parser.math;

import com.devfoFikiCar.parser.Parser;

public class Min extends TwoExpressions{
    public static int[] mathMinInt(int index) {
        int[] ret = new int[2];
        if (!Parser.tokens.get(index).key.equals("MIN")) {
            return ret;
        }
        index++;
        int[] retInt = getTwoIntExpressions(index);
        ret[0] = retInt[0];
        ret[1] = Math.min(retInt[1], retInt[2]);
        return ret;
    }

    public static double[] mathMinDecimal(int index) {
        double[] ret = new double[2];
        if (!Parser.tokens.get(index).key.equals("MIN")) {
            return ret;
        }
        index++;
        double[] retDecimal = getTwoDecimalExpressions(index);
        ret[0] = retDecimal[0];
        ret[1] = Math.min(retDecimal[1], retDecimal[2]);
        return ret;
    }
}
