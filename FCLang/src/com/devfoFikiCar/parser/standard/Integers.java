package com.devfoFikiCar.parser.standard;

import com.devfoFikiCar.parser.Parser;

public class Integers {
    // expression: term (('-' | '+') term)?
    public static int[] expression_int(int index, int value) {
        int[] ret = {index, value};
        if (term_int(index, value)[0] != 0) {
            int[] ret_v = term_int(index, value);
            index = ret_v[0];
            value = ret_v[1];
            if (index < Parser.tokens.size() && (Parser.tokens.get(index).key == "ADDITION" || Parser.tokens.get(index).key == "SUBTRACTION")) {
                ret_v = expression_int(index + 1, value);
                switch (Parser.tokens.get(index).key) {
                    case "SUBTRACTION": {
                        value -= ret_v[1];
                        break;
                    }
                    case "ADDITION": {
                        value += ret_v[1];
                        break;
                    }
                }
                index = ret_v[0];
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
    private static int[] term_int(int index, int value) {
        int[] ret = {index, value};
        if (factor_int(index, value)[0] != 0) {
            int[] ret_v = factor_int(index, value);
            index = ret_v[0];
            value = ret_v[1];
            if (index < Parser.tokens.size() && (Parser.tokens.get(index).key == "MULTIPLICATION" || Parser.tokens.get(index).key == "DIVISION")) {
                ret_v = expression_int(index + 1, value);
                switch (Parser.tokens.get(index).key) {
                    case "DIVISION": {
                        value /= ret_v[1];
                        break;
                    }
                    case "MULTIPLICATION": {
                        value *= ret_v[1];
                        break;
                    }
                }
                index = ret_v[0];
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
    private static int[] factor_int(int index, int value) {
        int[] ret = {index, value};
        if (Parser.tokens.get(index).key == "INT" || Parser.tokens.get(index).key == "NAME") {
            if (Parser.int_store.containsKey(Parser.tokens.get(index).value)) {
                ret[1] = Parser.int_store.get(Parser.tokens.get(index).value);
            } else {
                try {
                    ret[1] = Integer.parseInt(Parser.tokens.get(index).value);
                } catch (Exception e) {
                }
            }
            ret[0] = index + 1;
            return ret;
        } else if (Parser.tokens.get(index).key == "L_PARENTHESES") {
            int[] ret_v = expression_int(index + 1, ret[1]);
            index = ret_v[0];
            value = ret_v[1];
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
