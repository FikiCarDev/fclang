package com.devfoFikiCar.parser.standard;

import com.devfoFikiCar.parser.Parser;

public class Integers {
    // expression: term (('-' | '+') term)?
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

    // term: factor (('/' | '*') factor)?
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

    // factor: NUMBER | '(' expression ')'
    private static int[] factorInt(int index, int value) {
        int[] ret = {index, value};
        if (Parser.tokens.get(index).key == "INT" || Parser.tokens.get(index).key == "NAME") {
            if (Parser.intStore.containsKey(Parser.tokens.get(index).value)) {
                ret[1] = Parser.intStore.get(Parser.tokens.get(index).value);
            } else {
                try {
                    ret[1] = Integer.parseInt(Parser.tokens.get(index).value);
                } catch (Exception e) {
                }
            }
            ret[0] = index + 1;
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
