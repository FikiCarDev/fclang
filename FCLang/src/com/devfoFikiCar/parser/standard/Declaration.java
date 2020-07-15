package com.devfoFikiCar.parser.standard;

import com.devfoFikiCar.parser.Parser;

public class Declaration {
    // int declaration
    public static int int_d(int index) {
        if (Parser.tokens.get(index + 1).key == "NAME") {
            if (Parser.tokens.get(index + 2).key == "EQUALS") {
                int[] ret_v = Integers.expression_int(index + 3, 0);
                if (ret_v[0] != 0) {
                    Parser.int_store.put(Parser.tokens.get(index + 1).value, ret_v[1]);
                    index = ret_v[0];
                    return --index;
                } else {
                    index = declare_v(index + 3, Parser.tokens.get(index).key, Parser.tokens.get(index + 1).value);
                    return index;
                }
            } else return 0;
        }
        return 0;
    }

    // string declaration
    public static int string_d(int index) {
        if (Parser.tokens.get(index + 1).key == "NAME") {
            if (Parser.tokens.get(index + 2).key == "EQUALS") {
                index = declare_v(index + 3, Parser.tokens.get(index).key, Parser.tokens.get(index + 1).value);
                return index;
            } else return 0;
        } else return 0;
    }

    // bool declaration
    public static int bool_d(int index) {
        if (Parser.tokens.get(index + 1).key == "NAME") {
            if (Parser.tokens.get(index + 2).key == "EQUALS") {
                index = declare_v(index + 3, Parser.tokens.get(index).key, Parser.tokens.get(index + 1).value);
                return index;
            } else return 0;
        } else return 0;
    }

    // decimal declaration
    public static int decimal_d(int index) {
        if (Parser.tokens.get(index + 1).key == "NAME") {
            if (Parser.tokens.get(index + 2).key == "EQUALS") {
                double[] ret_v = Decimals.expression_decimal(index * 1.0 + 3.0, 0);
                if (ret_v[0] != 0) {
                    Parser.decimal_store.put(Parser.tokens.get(index + 1).value, ret_v[1]);
                    index = (int) ret_v[0];
                    return --index;
                } else {
                    index = declare_v(index + 3, Parser.tokens.get(index).key, Parser.tokens.get(index + 1).value);
                    return index;
                }
            } else return 0;
        } else return 0;
    }

    // declares
    public static int declare_v(int index, String type, String name) {
        String value_t = Parser.tokens.get(index).key + "_T";
        if (type.equals(value_t)) {
            switch (type) {
                case "INT_T": {
                    Parser.int_store.put(name, Integer.valueOf(Parser.tokens.get(index).value));
                    break;
                }
                case "STRING_T": {
                    Parser.string_store.put(name, String.valueOf(Parser.tokens.get(index).value));
                    break;
                }
                case "DECIMAL_T": {
                    Parser.decimal_store.put(name, Double.valueOf(Parser.tokens.get(index).value));
                    break;
                }
                case "BOOL_T": {
                    Parser.bool_store.put(name, Boolean.valueOf(Parser.tokens.get(index).value));
                }
            }
            return index;
        }
        return 0;
    }

}
