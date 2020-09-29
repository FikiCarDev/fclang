package com.devfoFikiCar.parser.standard;

import com.devfoFikiCar.parser.Parser;
import com.devfoFikiCar.parser.math.*;
import javafx.util.Pair;

public class Integers {

    /**
     * expressionInt validates and calculates int expressions.
     *
     * @param index begin position for parsing
     * @param value beginning value for operations to start on
     * @return index to continue parsing from and value of expression
     * @grammar expression: term (('-' | '+') term)?
     */
    public static int[] expressionInt(int index, int value) {
        int[] ret = {index, value};
        if (termInt(index, value)[0] != 0) {
            int[] retV = termInt(index, value);
            index = retV[0];
            value = retV[1];
            if (index < Parser.tokens.size() && (Parser.tokens.get(index).key == "ADDITION" || Parser.tokens.get(index).key == "SUBTRACTION")) {
                retV = expressionInt(index + 1, value);
                switch (Parser.tokens.get(index).key) {
                    case "SUBTRACTION": {
                        value -= retV[1];
                        break;
                    }
                    case "ADDITION": {
                        value += retV[1];
                        break;
                    }
                }
                index = retV[0];
            }
            ret[0] = index;
            ret[1] = value;
            return ret;
        } else {
            ret[0] = 0;
            return ret;
        }
    }

    /**
     * termInt divides and multiplies ints.
     *
     * @param index begin position for parsing
     * @param value beginning value for operations to start on
     * @return index to continue parsing from and value of expression
     * @grammar term: factor (('/' | '*') factor)?
     */
    private static int[] termInt(int index, int value) {
        int[] ret = {index, value};
        if (factorInt(index, value)[0] != 0) {
            int[] retV = factorInt(index, value);
            index = retV[0];
            value = retV[1];
            if (index < Parser.tokens.size() && (Parser.tokens.get(index).key == "MULTIPLICATION" || Parser.tokens.get(index).key == "DIVISION")) {
                retV = expressionInt(index + 1, value);
                switch (Parser.tokens.get(index).key) {
                    case "DIVISION": {
                        value /= retV[1];
                        break;
                    }
                    case "MULTIPLICATION": {
                        value *= retV[1];
                        break;
                    }
                }
                index = retV[0];
            }
            ret[0] = index;
            ret[1] = value;
            return ret;
        } else {
            ret[0] = 0;
            return ret;
        }
    }

    /**
     * factorInt checks for int and base of int expressions.
     *
     * @param index begin position for parsing
     * @param value beginning value for operations to start on
     * @return index to continue parsing from and value of expression
     * @grammar factor: NUMBER | '(' expression ')'
     */
    private static int[] factorInt(int index, int value) {
        int[] ret = {index, value};
        if (Parser.tokens.get(index).key == "INT" || Parser.tokens.get(index).key == "NAME") {
            if (Parser.intStore.containsKey(Parser.tokens.get(index).value)) {
                ret[1] = Parser.intStore.get(Parser.tokens.get(index).value);
            } else if (Arrays.arraySize(index)[0] != 0) {
                ret = Arrays.arraySize(index);
            } else if (index + 5 < Parser.tokens.size() && (int) Arrays.getArrayValue(index, 1).getKey() != 0) {
                Pair<Integer, Integer> retPair = Arrays.getArrayValue(index, 1);
                ret[0] = retPair.getKey();
                ret[1] = retPair.getValue();
            } else {
                try {
                    ret[1] = Integer.parseInt(Parser.tokens.get(index).value);
                } catch (Exception e) {
                }
            }
            ret[0] = index + 1;
            return ret;
        } else if (Parser.tokens.get(index).key.equals("MAX")) {
            ret = Max.mathMaxInt(index);
            return ret;
        } else if (Parser.tokens.get(index).key.equals("MIN")) {
            ret = Min.mathMinInt(index);
            return ret;
        } else if (Parser.tokens.get(index).key.equals("SQRT")) {
            ret = Sqrt.mathSqrtInt(index);
            return ret;
        } else if (Parser.tokens.get(index).key.equals("POW")) {
            ret = Pow.mathPowInt(index);
            return ret;
        } else if (Parser.tokens.get(index).key.equals("ABS")) {
            ret = Abs.mathAbsInt(index);
            return ret;
        } else if (Parser.tokens.get(index).key == "L_PARENTHESES") {
            int[] retV = expressionInt(index + 1, ret[1]);
            index = retV[0];
            value = retV[1];
            if (index == 0) {
                ret[0] = 0;
                return ret;
            } else {
                if (index < Parser.tokens.size() && Parser.tokens.get(index).key == "R_PARENTHESES") {
                    ret[0] = index + 1;
                    ret[1] = value;
                    return ret;
                } else {
                    ret[0] = 0;
                    return ret;
                }
            }
        } else if (Parser.tokens.get(index).key == "EQUAL_TO" || Parser.tokens.get(index).key == "NOT_EQUAL"
                || Parser.tokens.get(index).key == "GREATER_EQUAL" || Parser.tokens.get(index).key == "LESS_EQUAL"
                || Parser.tokens.get(index).key == "LESS_THAN" || Parser.tokens.get(index).key == "GREATER_THAN") {
            ret[0] = index;
            ret[1] = value;
            return ret;
        } else {
            ret[0] = 0;
            return ret;
        }
    }
}
