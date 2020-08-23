package com.devfoFikiCar.parser.standard;

import com.devfoFikiCar.parser.Parser;

public class Bools {

    public static int[] bool(int index) {
        int[] ret = new int[2];
        if (smallBool(index)[0] != 0) {
            int[] ret_v = smallBool(index);
            ret[0] = ret_v[0];
            ret[1] = ret_v[1];
            index = ret[0];
            if (index < Parser.tokens.size() && (Parser.tokens.get(index).key == "OR" || Parser.tokens.get(index).key == "AND")) {
                switch (Parser.tokens.get(index).key) {
                    case "OR": {
                        index++;
                        ret_v = bool(index);
                        index = ret_v[0];
                        ret[0] = index;
                        boolean a = ret[1] == 1;
                        boolean b = ret_v[1] == 1;
                        ret[1] = (a || b) ? 1 : 0;
                        break;
                    }
                    case "AND": {
                        index++;
                        ret_v = bool(index);
                        index = ret_v[0];
                        ret[0] = index;
                        boolean a = ret[1] == 1;
                        boolean b = ret_v[1] == 1;
                        ret[1] = (a && b) ? 1 : 0;
                        break;
                    }
                }
                return ret;
            }
            return ret;
        }
        return ret;
    }

    public static int[] smallBool(int index) {
        int[] ret = new int[2];
        if (index < Parser.tokens.size() && Parser.tokens.get(index).key == "BOOL") {
            switch (Parser.tokens.get(index).value) {
                case "true": {
                    ret[1] = 1;
                    break;
                }
                case "false": {
                    ret[1] = 0;
                    break;
                }
            }
            ret[0] = ++index;
            return ret;
        } else if (fcompare(index)[0] != 0) {
            int[] ret_v = fcompare(index);
            ret[0] = ret_v[0];
            ret[1] = ret_v[1];
            return ret;
        } else if (Parser.tokens.get(index).key == "L_PARENTHESES") {
            int[] ret_v = bool(index + 1);
            ret[0] = ret_v[0];
            ret[1] = ret_v[1];
            index = ret[0];
            if (index == 0) {
                ret[0] = 0;
                return ret;
            } else {
                if (index < Parser.tokens.size() && Parser.tokens.get(index).key == "R_PARENTHESES") {
                    ret[0] = ++index;
                    return ret;
                } else {
                    ret[0] = 0;
                    return ret;
                }
            }
        } else return ret;
    }

    // expresion_int | expresion_decimal ( < | > | <= | >= | == | != ) expresion_int | expresion_decimal
    // index value(1 ok, 0 no)
    public static int[] fcompare(int index) {
        int[] ret = new int[2];
        int signPos = 0;
        for (int i = index + 1; i < Parser.tokens.size(); i++) {
            if (Parser.tokens.get(i).key == "EQUAL_TO" || Parser.tokens.get(i).key == "NOT_EQUAL"
                    || Parser.tokens.get(i).key == "GREATER_EQUAL" || Parser.tokens.get(i).key == "LESS_EQUAL"
                    || Parser.tokens.get(i).key == "LESS_THAN" || Parser.tokens.get(i).key == "GREATER_THAN") {
                signPos = i;
                break;
            }
        }
        if (signPos == 0) return ret;
        switch (Parser.tokens.get(signPos).key) {
            case "EQUAL_TO": {
                int[] ret_int1 = Integers.expression_int(index, 0);
                double[] ret_double1 = Decimals.expression_decimal(index * 1.0, 0.0);
                if (ret_double1[0] != 0) {
                    double[] ret_double2 = Decimals.expression_decimal(signPos * 1.0 + 1.0, 0.0);
                    if (ret_double1[1] == ret_double2[1]) {
                        ret[0] = (int) ret_double2[0];
                        ret[1] = 1;
                    } else {
                        ret[0] = (int) ret_double2[0];
                        ret[1] = 0;
                    }
                } else if (ret_int1[0] != 0) {
                    int[] ret_int2 = Integers.expression_int(signPos + 1, 0);
                    if (ret_int1[1] == ret_int2[1]) {
                        ret[0] = ret_int2[0];
                        ret[1] = 1;
                    } else {
                        ret[0] = ret_int2[0];
                        ret[1] = 0;
                    }
                } else {
                    ret[0] = 0;
                    ret[1] = 0;
                    return ret;
                }
                break;
            }
            case "NOT_EQUAL": {
                int[] ret_int1 = Integers.expression_int(index, 0);
                double[] ret_double1 = Decimals.expression_decimal(index * 1.0, 0.0);
                if (ret_double1[0] != 0) {
                    double[] ret_double2 = Decimals.expression_decimal(signPos * 1.0 + 1.0, 0.0);
                    if (ret_double1[1] != ret_double2[1]) {
                        ret[0] = (int) ret_double2[0];
                        ret[1] = 1;
                    } else {
                        ret[0] = (int) ret_double2[0];
                        ret[1] = 0;
                    }
                } else if (ret_int1[0] != 0) {
                    int[] ret_int2 = Integers.expression_int(signPos + 1, 0);
                    if (ret_int1[1] != ret_int2[1]) {
                        ret[0] = ret_int2[0];
                        ret[1] = 1;
                    } else {
                        ret[0] = ret_int2[0];
                        ret[1] = 0;
                    }
                } else {
                    ret[0] = 0;
                    ret[1] = 0;
                    return ret;
                }
                break;
            }
            case "GREATER_EQUAL": {
                int[] ret_int1 = Integers.expression_int(index, 0);
                double[] ret_double1 = Decimals.expression_decimal(index * 1.0, 0.0);
                if (ret_double1[0] != 0) {
                    double[] ret_double2 = Decimals.expression_decimal(signPos * 1.0 + 1.0, 0.0);
                    if (ret_double1[1] >= ret_double2[1]) {
                        ret[0] = (int) ret_double2[0];
                        ret[1] = 1;
                    } else {
                        ret[0] = (int) ret_double2[0];
                        ret[1] = 0;
                    }
                } else if (ret_int1[0] != 0) {
                    int[] ret_int2 = Integers.expression_int(signPos + 1, 0);
                    if (ret_int1[1] >= ret_int2[1]) {
                        ret[0] = ret_int2[0];
                        ret[1] = 1;
                    } else {
                        ret[0] = ret_int2[0];
                        ret[1] = 0;
                    }
                } else {
                    ret[0] = 0;
                    ret[1] = 0;
                    return ret;
                }
                break;
            }
            case "LESS_EQUAL": {
                int[] ret_int1 = Integers.expression_int(index, 0);
                double[] ret_double1 = Decimals.expression_decimal(index * 1.0, 0.0);
                if (ret_double1[0] != 0) {
                    double[] ret_double2 = Decimals.expression_decimal(signPos * 1.0 + 1.0, 0.0);
                    if (ret_double1[1] <= ret_double2[1]) {
                        ret[0] = (int) ret_double2[0];
                        ret[1] = 1;
                    } else {
                        ret[0] = (int) ret_double2[0];
                        ret[1] = 0;
                    }
                } else if (ret_int1[0] != 0) {
                    int[] ret_int2 = Integers.expression_int(signPos + 1, 0);
                    if (ret_int1[1] <= ret_int2[1]) {
                        ret[0] = ret_int2[0];
                        ret[1] = 1;
                    } else {
                        ret[0] = ret_int2[0];
                        ret[1] = 0;
                    }
                } else {
                    ret[0] = 0;
                    ret[1] = 0;
                    return ret;
                }
                break;
            }
            case "LESS_THAN": {
                int[] ret_int1 = Integers.expression_int(index, 0);
                double[] ret_double1 = Decimals.expression_decimal(index * 1.0, 0.0);
                if (ret_double1[0] != 0) {
                    double[] ret_double2 = Decimals.expression_decimal(signPos * 1.0 + 1.0, 0.0);
                    if (ret_double1[1] < ret_double2[1]) {
                        ret[0] = (int) ret_double2[0];
                        ret[1] = 1;
                    } else {
                        ret[0] = (int) ret_double2[0];
                        ret[1] = 0;
                    }
                } else if (ret_int1[0] != 0) {
                    int[] ret_int2 = Integers.expression_int(signPos + 1, 0);
                    if (ret_int1[1] < ret_int2[1]) {
                        ret[0] = ret_int2[0];
                        ret[1] = 1;
                    } else {
                        ret[0] = ret_int2[0];
                        ret[1] = 0;
                    }
                } else {
                    ret[0] = 0;
                    ret[1] = 0;
                    return ret;
                }
                break;
            }
            case "GREATER_THAN": {
                int[] ret_int1 = Integers.expression_int(index, 0);
                double[] ret_double1 = Decimals.expression_decimal(index * 1.0, 0.0);
                if (ret_double1[0] != 0) {
                    double[] ret_double2 = Decimals.expression_decimal(signPos * 1.0 + 1.0, 0.0);
                    if (ret_double1[1] > ret_double2[1]) {
                        ret[0] = (int) ret_double2[0];
                        ret[1] = 1;
                    } else {
                        ret[0] = (int) ret_double2[0];
                        ret[1] = 0;
                    }
                } else if (ret_int1[0] != 0) {
                    int[] ret_int2 = Integers.expression_int(signPos + 1, 0);
                    if (ret_int1[1] > ret_int2[1]) {
                        ret[0] = ret_int2[0];
                        ret[1] = 1;
                    } else {
                        ret[0] = ret_int2[0];
                        ret[1] = 0;
                    }
                } else {
                    ret[0] = 0;
                    ret[1] = 0;
                    return ret;
                }
                break;
            }
        }
        return ret;
    }
}