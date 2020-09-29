package com.devfoFikiCar.parser.math;

import com.devfoFikiCar.parser.Parser;

public class Pow extends TwoExpressions {
    public static int[] mathPowInt(int index) {
        int[] ret = new int[2];
        if (!Parser.tokens.get(index).key.equals("POW")) {
            return ret;
        }
        index++;
        int[] retInt = getTwoIntExpressions(index);
        ret[0] = retInt[0];
        ret[1] = (int) Math.pow(retInt[1], retInt[2]);
        return ret;
    }

    public static double[] mathPowDecimal(int index) {
        double[] ret = new double[2];
        if (!Parser.tokens.get(index).key.equals("POW")) {
            return ret;
        }
        index++;
        double[] retDecimal = getTwoDecimalExpressions(index);
        ret[0] = retDecimal[0];
        ret[1] = Math.pow(retDecimal[1], retDecimal[2]);
        return ret;
    }
}
