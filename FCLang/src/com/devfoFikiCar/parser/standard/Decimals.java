package com.devfoFikiCar.parser.standard;

import com.devfoFikiCar.parser.Parser;

public class Decimals {
    // expression for decimal
    public static double[] expression_decimal(double index, double value) {
        double[] ret = {index, value};
        if (term_decimal(index, value)[0] != 0) {
            double[] ret_v = term_decimal(index, value);
            index = ret_v[0];
            value = ret_v[1];
            if (index < Parser.tokens.size() && (Parser.tokens.get((int) index).key == "ADDITION" || Parser.tokens.get((int) index).key == "SUBTRACTION")) {
                ret_v = expression_decimal(index + 1, value);
                switch (Parser.tokens.get((int) index).key) {
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

    // term for decimal
    private static double[] term_decimal(double index, double value) {
        double[] ret = {index, value};
        if (factor_decimal(index, value)[0] != 0) {
            double[] ret_v = factor_decimal(index, value);
            index = ret_v[0];
            value = ret_v[1];
            if (index < Parser.tokens.size() && (Parser.tokens.get((int) index).key == "MULTIPLICATION" || Parser.tokens.get((int) index).key == "DIVISION")) {
                ret_v = expression_decimal(index + 1, value);
                switch (Parser.tokens.get((int) index).key) {
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

    // factor for decimal
    private static double[] factor_decimal(double index, double value) {
        double[] ret = {index, value};
        if (Parser.tokens.get((int) index).key == "DECIMAL" || Parser.tokens.get((int) index).key == "NAME") {
            if (Parser.decimal_store.containsKey(Parser.tokens.get((int) index).value)) {
                ret[1] = Parser.decimal_store.get(Parser.tokens.get((int) index).value);
            } else {
                try {
                    ret[1] = Double.parseDouble(Parser.tokens.get((int) index).value);
                } catch (Exception e) {
                }
            }
            ret[0] = index + 1;
            return ret;
        } else if (Parser.tokens.get((int) index).key == "L_PARENTHESES") {
            double[] ret_v = expression_decimal(index + 1, ret[1]);
            index = ret_v[0];
            value = ret_v[1];
            if (index == 0) {
                ret[0] = 0;
                return ret;
            } else {
                if (index < Parser.tokens.size() && Parser.tokens.get((int) index).key == "R_PARENTHESES") {
                    ret[0] = index + 1;
                    ret[1] = value;
                    return ret;
                } else {
                    ret[0] = 0;
                    return ret;
                }
            }
        } else {
            ret[0] = 0;
            return ret;
        }
    }
}
