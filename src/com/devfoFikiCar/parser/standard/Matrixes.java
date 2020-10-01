package com.devfoFikiCar.parser.standard;

import com.devfoFikiCar.parser.Parser;
import javafx.util.Pair;

import java.util.ArrayList;

public class Matrixes {

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

    public static int[] checkPosition(int index) {
        int[] ret = new int[3];
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
        if (!Parser.tokens.get(index).key.equals("COMMA")) {
            return ret;
        }
        index++;
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

    public static int setMatrixValueInt(int index) {
        String name = Parser.tokens.get(index).value;
        if (!Parser.intMatrixStore.containsKey(name)) return 0;
        if (!Parser.tokens.get(index + 1).key.equals("DOT")) return 0;
        index++;
        if (!Parser.tokens.get(index + 1).key.equals("SET")) return 0;
        index++;
        if (!Parser.tokens.get(index + 1).key.equals("L_PARENTHESES")) return 0;
        index++;
        int[] retV = checkPosition(++index);
        if (retV[0] == 0) return 0;
        index = retV[0];
        int[] retV2 = Integers.expressionInt(index, 0);
        if (retV2[0] == 0) return 0;
        index = retV2[0];
        int value = retV2[1];
        if (!Parser.tokens.get(index).key.equals("R_PARENTHESES")) return 0;
        Parser.intMatrixStore.get(name).getKey().get(retV[1]).set(retV[2], value);
        return index;
    }

    public static int setMatrixValueDecimal(int index) {
        String name = Parser.tokens.get(index).value;
        if (!Parser.decimalMatrixStore.containsKey(name)) return 0;
        if (!Parser.tokens.get(index + 1).key.equals("DOT")) return 0;
        index++;
        if (!Parser.tokens.get(index + 1).key.equals("SET")) return 0;
        index++;
        if (!Parser.tokens.get(index + 1).key.equals("L_PARENTHESES")) return 0;
        index++;
        int[] retV = checkPosition(++index);
        if (retV[0] == 0) return 0;
        index = retV[0];
        double[] retV2 = Decimals.expressionDecimal(index, 0);
        if (retV2[0] == 0) return 0;
        index = (int) retV2[0];
        double value = retV2[1];
        if (!Parser.tokens.get(index).key.equals("R_PARENTHESES")) return 0;
        Parser.decimalMatrixStore.get(name).getKey().get(retV[1]).set(retV[2], value);
        return index;
    }

    public static int setMatrixValueString(int index) {
        String name = Parser.tokens.get(index).value;
        if (!Parser.stringMatrixStore.containsKey(name)) return 0;
        if (!Parser.tokens.get(index + 1).key.equals("DOT")) return 0;
        index++;
        if (!Parser.tokens.get(index + 1).key.equals("SET")) return 0;
        index++;
        if (!Parser.tokens.get(index + 1).key.equals("L_PARENTHESES")) return 0;
        index++;
        int[] retV = checkPosition(++index);
        if (retV[0] == 0) return 0;
        index = retV[0];
        Pair<Integer, String> retPair = Strings.isString(index);
        if (retPair.getKey() == 0) return 0;
        index = retPair.getKey();
        String value = retPair.getValue();
        if (!Parser.tokens.get(++index).key.equals("R_PARENTHESES")) return 0;
        Parser.stringMatrixStore.get(name).getKey().get(retV[1]).set(retV[2], value);
        return index;
    }

    public static int setMatrixValueBool(int index) {
        String name = Parser.tokens.get(index).value;
        if (!Parser.boolMatrixStore.containsKey(name)) return 0;
        if (!Parser.tokens.get(index + 1).key.equals("DOT")) return 0;
        index++;
        if (!Parser.tokens.get(index + 1).key.equals("SET")) return 0;
        index++;
        if (!Parser.tokens.get(index + 1).key.equals("L_PARENTHESES")) return 0;
        index++;
        int[] retV = checkPosition(++index);
        if (retV[0] == 0) return 0;
        index = retV[0];
        int[] retV2 = Bools.bool(index);
        if (retV2[0] == 0) return 0;
        index = retV2[0];
        int value = retV2[1];
        if (!Parser.tokens.get(index).key.equals("R_PARENTHESES")) return 0;
        Parser.boolMatrixStore.get(name).getKey().get(retV[1]).set(retV[2], value == 1);
        return index;
    }
}
