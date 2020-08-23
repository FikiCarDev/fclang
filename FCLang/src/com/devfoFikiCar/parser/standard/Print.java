package com.devfoFikiCar.parser.standard;

import com.devfoFikiCar.parser.Parser;

public class Print {
    // print: 'PRINT' STRING | INT | DECIMAL | NAME
    public static int fprint(int index) {
        if (Parser.tokens.get(index + 1).key == "STRING" || Parser.tokens.get(index + 1).key == "INT"
                || Parser.tokens.get(index + 1).key == "DECIMAL" || Parser.tokens.get(index + 1).key == "BOOL") {
            switch (Parser.tokens.get(index + 1).key) {
                case "INT": {
                    int[] ret_v_int = Integers.expression_int(index + 1, 0);
                    System.out.println(ret_v_int[1]);
                    return --ret_v_int[0];
                }
                case "DECIMAL": {
                    double[] ret_v_double = Decimals.expression_decimal(index * 1.0 + 1.0, 0.0);
                    System.out.println(ret_v_double[1]);
                    return (int) --ret_v_double[0];
                }
                case "BOOL": {
                    int[] ret_v_bool = Bools.bool(index + 1);
                    System.out.println(ret_v_bool[1] == 1);
                    return ret_v_bool[0];
                }
                default: {
                    String toPrint = Parser.tokens.get(index + 1).value;
                    if (Parser.tokens.get(index + 1).key == "STRING")
                        toPrint = toPrint.subSequence(1, toPrint.length() - 1).toString();
                    System.out.println(toPrint);
                    break;
                }
            }
            return index + 1;
        } else if (Parser.tokens.get(index + 1).key == "NAME") {
            if (Parser.int_store.containsKey(Parser.tokens.get(index + 1).value) || Parser.string_store.containsKey(Parser.tokens.get(index + 1).value)
                    || Parser.decimal_store.containsKey(Parser.tokens.get(index + 1).value) || Parser.bool_store.containsKey(Parser.tokens.get(index + 1).value)) {
                int store = 0;
                if (Parser.bool_store.containsKey(Parser.tokens.get(index + 1).value)) store = 1;
                else if (Parser.int_store.containsKey(Parser.tokens.get(index + 1).value)) store = 2;
                else if (Parser.decimal_store.containsKey(Parser.tokens.get(index + 1).value)) store = 3;
                else store = 4;
                switch (store) {
                    case 1: {
                        System.out.println(Parser.bool_store.get(Parser.tokens.get(index + 1).value));
                        break;
                    }
                    case 2: {
                        System.out.println(Parser.int_store.get(Parser.tokens.get(index + 1).value));
                        break;
                    }
                    case 3: {
                        System.out.println(Parser.decimal_store.get(Parser.tokens.get(index + 1).value));
                        break;
                    }
                    case 4: {
                        String value = Parser.string_store.get(Parser.tokens.get(index + 1).value);
                        value = value.subSequence(1, value.length() - 1).toString();
                        System.out.println(value);
                        break;
                    }
                }
                return index + 1;
            } else return 0;
        } else if (Parser.tokens.get(index + 1).key == "L_PARENTHESES") {
            index++;
            int tempIndex = 0;
            for (int i = index; i < Parser.tokens.size(); i++)
                if (Parser.tokens.get(i).key != "L_PARENTHESES") {
                    tempIndex = i;
                    break;
                }
            switch (Parser.tokens.get(tempIndex).key) {
                case "INT": {
                    int[] ret_v_int = Integers.expression_int(index, 0);
                    System.out.println(ret_v_int[1]);
                    return --ret_v_int[0];
                }
                case "DECIMAL": {
                    double[] ret_v_double = Decimals.expression_decimal(index * 1.0, 0.0);
                    System.out.println(ret_v_double[1]);
                    return (int) --ret_v_double[0];
                }
            }
            return 0;
        } else return 0;
    }
}
