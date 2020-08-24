package com.devfoFikiCar.parser.standard;

import com.devfoFikiCar.parser.Parser;

public class IfStatement {
    // if -> 1: index
    // if <- 1: index 2: index of } 3: 0 for skip } 1 for skip to pos } + 1, execute else, begin else, end else
    public static int[] ifStatement(int index) {
        int[] ret = new int[6];
        if (Parser.tokens.get(index + 1).key == "L_PARENTHESES") {
            index += 2;
            int[] ret_v = Parser.expression_bool(index);
            //int[] ret_v = Bools.bool(index);
            index = ret_v[0];
            if (index != 0) {
                if (index < Parser.tokens.size() && Parser.tokens.get(index).key == "R_PARENTHESES") {
                    if (index + 1 < Parser.tokens.size() && Parser.tokens.get(index + 1).key == "L_BRACES") {
                        index++;
                        int r_pos = HelperFunctions.searchRightBracket(index);
                        int[] ret_1 = elseStatement(r_pos);
                        if (r_pos != 0) {
                            if (ret_v[1] == 1) {
                                ret[0] = index;
                                ret[1] = r_pos;
                                ret[2] = 0;
                                if (ret_1[0] != 0) {
                                    ret[3] = 0;
                                    ret[4] = ret_1[0];
                                    ret[5] = ret_1[1];
                                } else {
                                    ret[3] = 0;
                                    ret[4] = 0;
                                    ret[5] = 0;
                                }
                            } else {
                                ret[0] = index;
                                ret[1] = r_pos;
                                ret[2] = 1;
                                if (ret_1[0] != 0) {
                                    ret[3] = 1;
                                    ret[4] = ret_1[0];
                                    ret[5] = ret_1[1];
                                } else {
                                    ret[3] = 0;
                                    ret[4] = 0;
                                    ret[5] = 0;
                                }
                            }
                        } else {
                            ret[0] = 0;
                            return ret;
                        }
                    } else {
                        ret[0] = 0;
                        return ret;
                    }
                } else {
                    ret[0] = 0;
                    return ret;
                }
            } else {
                ret[0] = 0;
                return ret;
            }
        } else {
            ret[0] = 0;
        }
        return ret;
    }

    public static int[] elseStatement(int index) {
        int[] ret = {0, 0};
        if (index + 1 < Parser.tokens.size() && Parser.tokens.get(index + 1).key == "ELSE") {
            index++;
            if (index + 1 < Parser.tokens.size() && Parser.tokens.get(index + 1).key == "L_BRACES") {
                index++;
                int r_pos = HelperFunctions.searchRightBracket(index);
                if (r_pos != 0) {
                    ret[0] = index;
                    ret[1] = r_pos;
                } else {
                    return ret;
                }
            } else {
                return ret;
            }
        }
        return ret;
    }
}
