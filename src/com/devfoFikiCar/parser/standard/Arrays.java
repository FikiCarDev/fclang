package com.devfoFikiCar.parser.standard;

import com.devfoFikiCar.parser.Parser;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;

public class Arrays {
    /* TODO:
     *   declaration   {TYPE}Array name = new {TYPE}Array{number}        DONE
     *   size                                                            DONE
     *   get
     *   set           ArrayName.set(pos, value)
     *   sort
     * */
    /*
     * tokens:
     *  {Type}Array -> {TYPE}_ARRAY
     * */

    /**
     * declareIntArray declares int array, checks syntax and ensures capacity.
     * @param index position to start parsing from
     * @return index to continue parsing from or 0 if error occurred
     */
    public static int declareIntArray(int index) {
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

    /**
     * declareDecimalArray declares decimal array, checks syntax and ensures capacity.
     * @param index position to start parsing from
     * @return index to continue parsing from or 0 if error occurred
     */
    public static int declareDecimalArray(int index) {
        String name = "";
        if (Parser.tokens.get(index + 1).key != "NAME") return 0;
        name = Parser.tokens.get(index + 1).value;
        index++;
        if (Parser.tokens.get(index + 1).key != "EQUALS") return 0;
        index++;
        if (Parser.tokens.get(index + 1).key != "NEW") return 0;
        index++;
        if (Parser.tokens.get(index + 1).key != "DECIMAL_ARRAY") return 0;
        index++;
        if (Parser.tokens.get(index + 1).key != "L_BRACES") return 0;
        index++;
        int[] ret = Integers.expressionInt(index + 1, 0);
        if (ret[0] == 0) return 0;
        index = ret[0];
        if (Parser.tokens.get(index).key != "R_BRACES") return 0;
        Parser.decimalArrayStore.put(name, new Pair<>(new ArrayList<Double>(ret[1]), ret[1]));
        for (int i = 0; i < ret[1]; i++) Parser.decimalArrayStore.get(name).getKey().add(0.0);
        return index;
    }

    /**
     * declareStringArray declares string array, checks syntax and ensures capacity.
     * @param index position to start parsing from
     * @return index to continue parsing from or 0 if error occurred
     */
    public static int declareStringArray(int index) {
        String name = "";
        if (Parser.tokens.get(index + 1).key != "NAME") return 0;
        name = Parser.tokens.get(index + 1).value;
        index++;
        if (Parser.tokens.get(index + 1).key != "EQUALS") return 0;
        index++;
        if (Parser.tokens.get(index + 1).key != "NEW") return 0;
        index++;
        if (Parser.tokens.get(index + 1).key != "STRING_ARRAY") return 0;
        index++;
        if (Parser.tokens.get(index + 1).key != "L_BRACES") return 0;
        index++;
        int[] ret = Integers.expressionInt(index + 1, 0);
        if (ret[0] == 0) return 0;
        index = ret[0];
        if (Parser.tokens.get(index).key != "R_BRACES") return 0;
        Parser.stringArrayStore.put(name, new Pair<>(new ArrayList<String>(ret[1]), ret[1]));
        for (int i = 0; i < ret[1]; i++) Parser.stringArrayStore.get(name).getKey().add("");
        return index;
    }

    /**
     * declareBoolArray declares bool array, checks syntax and ensures capacity.
     * @param index position to start parsing from
     * @return index to continue parsing from or 0 if error occurred
     */
    public static int declareBoolArray(int index) {
        String name = "";
        if (Parser.tokens.get(index + 1).key != "NAME") return 0;
        name = Parser.tokens.get(index + 1).value;
        index++;
        if (Parser.tokens.get(index + 1).key != "EQUALS") return 0;
        index++;
        if (Parser.tokens.get(index + 1).key != "NEW") return 0;
        index++;
        if (Parser.tokens.get(index + 1).key != "BOOL_ARRAY") return 0;
        index++;
        if (Parser.tokens.get(index + 1).key != "L_BRACES") return 0;
        index++;
        int[] ret = Integers.expressionInt(index + 1, 0);
        if (ret[0] == 0) return 0;
        index = ret[0];
        if (Parser.tokens.get(index).key != "R_BRACES") return 0;
        Parser.boolArrayStore.put(name, new Pair<>(new ArrayList<Boolean>(ret[1]), ret[1]));
        for (int i = 0; i < ret[1]; i++) Parser.boolArrayStore.get(name).getKey().add(false);
        return index;
    }

    /**
     * arraySize parses {Array}.size() requests.
     * @param index position of array name
     * @return index to continue parsing from or 0 if error occurred
     */
    public static int[] arraySize(int index) {
        int[] ret = new int[2];
        int store = 0;
        String name = Parser.tokens.get(index).value;
        if (Parser.intArrayStore.containsKey(name)) store = 1;
        else if (Parser.decimalArrayStore.containsKey(name)) store = 2;
        else if (Parser.stringArrayStore.containsKey(name)) store = 3;
        else if (Parser.boolArrayStore.containsKey(name)) store = 4;
        else {
            ret[0] = 0;
            return ret;
        }
        if (Parser.tokens.get(index + 1).key != "DOT") {
            ret[0] = 0;
            return ret;
        }
        index++;
        if (Parser.tokens.get(index + 1).key != "SIZE") {
            ret[0] = 0;
            return ret;
        }
        index++;
        if (Parser.tokens.get(index + 1).key != "L_PARENTHESES" && Parser.tokens.get(index + 2).key != "R_PARENTHESES") {
            ret[0] = 0;
            return ret;
        }
        index++;
        ret[0] = index;
        switch (store) {
            case 1: {
                ret[1] = Parser.intArrayStore.get(name).getValue();
                return ret;
            }
            case 2: {
                ret[1] = Parser.decimalArrayStore.get(name).getValue();
                return ret;
            }
            case 3: {
                ret[1] = Parser.stringArrayStore.get(name).getValue();
                return ret;
            }
            case 4: {
                ret[1] = Parser.boolArrayStore.get(name).getValue();
                return ret;
            }
        }
        return ret;
    }

    /**
     * arraySort parses {Array}.sort() requests.
     * @param index position of array name
     * @return index to continue parsing from or 0 if error occurred
     */
    public static int arraySort(int index) {
        int store = 0;
        String name = Parser.tokens.get(index).value;
        if (Parser.intArrayStore.containsKey(name)) store = 1;
        else if (Parser.decimalArrayStore.containsKey(name)) store = 2;
        else if (Parser.stringArrayStore.containsKey(name)) store = 3;
        else if (Parser.boolArrayStore.containsKey(name)) store = 4;
        else return 0;
        if (Parser.tokens.get(index + 1).key != "DOT") return 0;
        index++;
        if (Parser.tokens.get(index + 1).key != "SORT") return 0;
        index++;
        if (Parser.tokens.get(index + 1).key != "L_PARENTHESES" && Parser.tokens.get(index + 2).key != "R_PARENTHESES")
            return 0;
        index += 2;
        switch (store) {
            case 1: {
                Collections.sort(Parser.intArrayStore.get(name).getKey());
                return index;
            }
            case 2: {
                Collections.sort(Parser.decimalArrayStore.get(name).getKey());
                return index;
            }
            case 3: {
                Collections.sort(Parser.stringArrayStore.get(name).getKey());
                return index;
            }
            case 4: {
                Collections.sort(Parser.boolArrayStore.get(name).getKey());
                return index;
            }
        }
        return index;
    }

    /**
     * setArrayValueInt validates expression and sets value to intArrayStore.
     * @param index position of array name
     * @return index to continue parsing from or 0 if error occured
     */
    public static int setArrayValueInt(int index) {
        String name = Parser.tokens.get(index).value;
        if(!Parser.intArrayStore.containsKey(name)) return 0;
        int size = Parser.intArrayStore.get(name).getValue();
        if (Parser.tokens.get(index + 1).key != "DOT") return 0;
        index++;
        if (Parser.tokens.get(index + 1).key != "SET") return 0;
        index++;
        if (Parser.tokens.get(index + 1).key != "L_PARENTHESES") return 0;
        index++;
        int[] ret = Integers.expressionInt(index + 1, 0);
        if (ret[0] == 0) return 0;
        index = ret[0];
        if (Parser.tokens.get(index).key != "COMMA") return 0;
        index++;
        int[] ret1 = Integers.expressionInt(index, 0);
        if (ret1[0] == 0) return 0;
        index = ret1[0];
        if (Parser.tokens.get(index).key != "R_PARENTHESES") return 0;
        size = Parser.intArrayStore.get(name).getValue();
        if (ret[1] >= size) return 0;
        Parser.intArrayStore.get(name).getKey().set(ret[1], ret1[1]);
        return index;
    }

    /**
     * setArrayValueDecimal validates expression and sets value to decimalArrayStore.
     * @param index position of array name
     * @return index to continue parsing from or 0 if error occurred
     */
    public static int setArrayValueDecimal(int index) {
        String name = Parser.tokens.get(index).value;
        if(!Parser.decimalArrayStore.containsKey(name)) return 0;
        int size = Parser.decimalArrayStore.get(name).getValue();
        if (Parser.tokens.get(index + 1).key != "DOT") return 0;
        index++;
        if (Parser.tokens.get(index + 1).key != "SET") return 0;
        index++;
        if (Parser.tokens.get(index + 1).key != "L_PARENTHESES") return 0;
        index++;
        int[] ret = Integers.expressionInt(index + 1, 0);
        if (ret[0] == 0) return 0;
        index = ret[0];
        if (Parser.tokens.get(index).key != "COMMA") return 0;
        index++;
        double[] ret1 = Decimals.expressionDecimal(index, 0);
        if (ret1[0] == 0) return 0;
        index = (int) ret1[0];
        if (Parser.tokens.get(index).key != "R_PARENTHESES") return 0;
        size = Parser.decimalArrayStore.get(name).getValue();
        if (ret[1] >= size) return 0;
        Parser.decimalArrayStore.get(name).getKey().set(ret[1], ret1[1]);
        return index;
    }

    /**
     * setArrayValueString validates expression and sets value to stringArrayStore.
     * @param index position of array name
     * @return index to continue parsing from or 0 if error occurred
     */
    public static int setArrayValueString(int index) {
        String name = Parser.tokens.get(index).value;
        if(!Parser.stringArrayStore.containsKey(name)) return 0;
        int size = Parser.stringArrayStore.get(name).getValue();
        if (Parser.tokens.get(index + 1).key != "DOT") return 0;
        index++;
        if (Parser.tokens.get(index + 1).key != "SET") return 0;
        index++;
        if (Parser.tokens.get(index + 1).key != "L_PARENTHESES") return 0;
        index++;
        int[] ret = Integers.expressionInt(index + 1, 0);
        if (ret[0] == 0) return 0;
        index = ret[0];
        if (Parser.tokens.get(index).key != "COMMA") return 0;
        index++;
        Pair<Integer, String> retPair = Strings.isString(index++);
        if(retPair.getKey() == 0) return 0;
        index = retPair.getKey();
        if (Parser.tokens.get(index + 1).key != "R_PARENTHESES") return 0;
        size = Parser.stringArrayStore.get(name).getValue();
        if (ret[1] >= size) return 0;
        Parser.stringArrayStore.get(name).getKey().set(ret[1], retPair.getValue());
        return index;
    }

    /**
     * setArrayValueBool validates expression and sets value to boolArrayStore.
     * @param index position of array name
     * @return index to continue parsing from or 0 if error occurred
     */
    public static int setArrayValueBool(int index) {
        String name = Parser.tokens.get(index).value;
        if(!Parser.boolArrayStore.containsKey(name)) return 0;
        int size = Parser.boolArrayStore.get(name).getValue();
        if (Parser.tokens.get(index + 1).key != "DOT") return 0;
        index++;
        if (Parser.tokens.get(index + 1).key != "SET") return 0;
        index++;
        if (Parser.tokens.get(index + 1).key != "L_PARENTHESES") return 0;
        index++;
        int[] ret = Integers.expressionInt(index + 1, 0);
        if (ret[0] == 0) return 0;
        index = ret[0];
        if (Parser.tokens.get(index).key != "COMMA") return 0;
        index++;
        int[] ret1 = Bools.bool(index);
        if (ret1[0] == 0) return 0;
        index = ret1[0];
        if (Parser.tokens.get(index).key != "R_PARENTHESES") return 0;
        size = Parser.boolArrayStore.get(name).getValue();
        if (ret[1] >= size) return 0;
        Parser.boolArrayStore.get(name).getKey().set(ret[1], ret1[1] == 1);
        return index;
    }
}
