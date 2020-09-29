package com.devfoFikiCar.parser.standard;

import com.devfoFikiCar.parser.Parser;
import javafx.util.Pair;

import java.util.ArrayList;

public class Matrixes {
    /*
    *
    * public static int declareIntArray(int index) {
        String name = "";
        if (Parser.tokens.get(index + 1).key != "NAME") return 0;
        name = Parser.tokens.get(index + 1).value;
        index++;
        if (Parser.tokens.get(index + 1).key != "EQUALS") return 0;
        index++;
        if (Parser.tokens.get(index + 1).key != "NEW") return 0;
        index++;
        if (Parser.tokens.get(index + 1).key != "INT_ARRAY") return 0;
        index++;
        if (Parser.tokens.get(index + 1).key != "L_BRACES") return 0;
        index++;
        int[] ret = Integers.expressionInt(index + 1, 0);
        if (ret[0] == 0) return 0;
        index = ret[0];
        if (Parser.tokens.get(index).key != "R_BRACES") return 0;
        Parser.intArrayStore.put(name, new Pair<>(new ArrayList<Integer>(ret[1]), ret[1]));
        for (int i = 0; i < ret[1]; i++) Parser.intArrayStore.get(name).getKey().add(0);
        return index;
    }
    * */

    public static int[] getTwoIntExpressions(int index) {
        int[] ret = new int[3];
        if (!Parser.tokens.get(index).key.equals("L_BRACES")) {
            return ret;
        }
        index++;
        int[] retV = Integers.expressionInt(index, 0);
        if (retV[0] == 0) {
            return ret;
        }
        index = retV[0];
        if (!Parser.tokens.get(index).key.equals("COMMA")) {
            return ret;
        }
        index++;
        int[] retV2 = Integers.expressionInt(index, 0);
        if (retV2[0] == 0) {
            return ret;
        }
        index = retV2[0];
        if (!Parser.tokens.get(index).key.equals("R_BRACES")) {
            return ret;
        }
        ret[0] = index;
        ret[1] = retV[1];
        ret[2] = retV2[1];
        return ret;
    }

    public static int declareIntMatrix(int index) {
        String name = "";
        if (!Parser.tokens.get(index + 1).key.equals("NAME")) return 0;
        name = Parser.tokens.get(index + 1).value;
        index++;
        if (!Parser.tokens.get(index + 1).key.equals("EQUALS")) return 0;
        index++;
        if (!Parser.tokens.get(index + 1).key.equals("NEW")) return 0;
        index++;
        if (!Parser.tokens.get(index + 1).key.equals("INT_MATRIX")) return 0;
        index++;
        int[] ret = getTwoIntExpressions(++index);
        if (ret[0] == 0) return 0;
        index = ret[0];
        Parser.intMatrixStore.put(name, new Pair<>(new ArrayList<ArrayList<Integer>>(), new Pair<>(ret[1], ret[2])));
        for (int i = 0; i < ret[1]; i++) {
            ArrayList<Integer> tmp = new ArrayList<>();
            for (int j = 0; j < ret[2]; j++) {
                tmp.add(0);
            }
            Parser.intMatrixStore.get(name).getKey().add(new ArrayList<>(tmp));
            tmp.clear();
        }
        return index;
    }

    public static int declareDecimalMatrix(int index) {
        String name = "";
        if (!Parser.tokens.get(index + 1).key.equals("NAME")) return 0;
        name = Parser.tokens.get(index + 1).value;
        index++;
        if (!Parser.tokens.get(index + 1).key.equals("EQUALS")) return 0;
        index++;
        if (!Parser.tokens.get(index + 1).key.equals("NEW")) return 0;
        index++;
        if (!Parser.tokens.get(index + 1).key.equals("DECIMAL_MATRIX")) return 0;
        index++;
        int[] ret = getTwoIntExpressions(++index);
        if (ret[0] == 0) return 0;
        index = ret[0];
        Parser.decimalMatrixStore.put(name, new Pair<>(new ArrayList<ArrayList<Double>>(), new Pair<>(ret[1], ret[2])));
        for (int i = 0; i < ret[1]; i++) {
            ArrayList<Double> tmp = new ArrayList<>();
            for (int j = 0; j < ret[2]; j++) {
                tmp.add(0.0);
            }
            Parser.decimalMatrixStore.get(name).getKey().add(new ArrayList<>(tmp));
            tmp.clear();
        }
        return index;
    }

    public static int declareBoolMatrix(int index) {
        String name = "";
        if (!Parser.tokens.get(index + 1).key.equals("NAME")) return 0;
        name = Parser.tokens.get(index + 1).value;
        index++;
        if (!Parser.tokens.get(index + 1).key.equals("EQUALS")) return 0;
        index++;
        if (!Parser.tokens.get(index + 1).key.equals("NEW")) return 0;
        index++;
        if (!Parser.tokens.get(index + 1).key.equals("BOOL_MATRIX")) return 0;
        index++;
        int[] ret = getTwoIntExpressions(++index);
        if (ret[0] == 0) return 0;
        index = ret[0];
        Parser.boolMatrixStore.put(name, new Pair<>(new ArrayList<ArrayList<Boolean>>(), new Pair<>(ret[1], ret[2])));
        for (int i = 0; i < ret[1]; i++) {
            ArrayList<Boolean> tmp = new ArrayList<>();
            for (int j = 0; j < ret[2]; j++) {
                tmp.add(false);
            }
            Parser.boolMatrixStore.get(name).getKey().add(new ArrayList<>(tmp));
            tmp.clear();
        }
        return index;
    }

    public static int declareStringMatrix(int index) {
        String name = "";
        if (!Parser.tokens.get(index + 1).key.equals("NAME")) return 0;
        name = Parser.tokens.get(index + 1).value;
        index++;
        if (!Parser.tokens.get(index + 1).key.equals("EQUALS")) return 0;
        index++;
        if (!Parser.tokens.get(index + 1).key.equals("NEW")) return 0;
        index++;
        if (!Parser.tokens.get(index + 1).key.equals("STRING_MATRIX")) return 0;
        index++;
        int[] ret = getTwoIntExpressions(++index);
        if (ret[0] == 0) return 0;
        index = ret[0];
        Parser.stringMatrixStore.put(name, new Pair<>(new ArrayList<ArrayList<String>>(), new Pair<>(ret[1], ret[2])));
        for (int i = 0; i < ret[1]; i++) {
            ArrayList<String> tmp = new ArrayList<>();
            for (int j = 0; j < ret[2]; j++) {
                tmp.add("");
            }
            Parser.stringMatrixStore.get(name).getKey().add(new ArrayList<>(tmp));
            tmp.clear();
        }
        return index;
    }
}
