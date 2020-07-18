package com.devfoFikiCar.parser;

import com.devfoFikiCar.Token;
import com.devfoFikiCar.error.Error;
import com.devfoFikiCar.parser.standard.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Parser {
    public static ArrayList<Token> tokens = new ArrayList<>();
    public static ArrayList<Integer> skip = new ArrayList<>();

    public static HashMap<String, Integer> int_store = new HashMap<>();
    public static HashMap<String, String> string_store = new HashMap<>();
    public static HashMap<String, Double> decimal_store = new HashMap<>();
    public static HashMap<String, Boolean> bool_store = new HashMap<>();
    public static HashMap<Integer, Integer> skip_store = new HashMap<>();
    public static HashMap<String, Integer> goto_store = new HashMap<>();

    public static int parse(ArrayList<Token> old, int begin, int end) {
        tokens = old;
        int index_r = 0;
        Goto.safe_goto();
        for (int index = begin; index < end; index++) {
            if (skip_store.containsKey(index)) {
                index = skip_store.get(index);
                continue;
            }
            if (skip.contains(index)) continue;
            String key = tokens.get(index).key;
            switch (key) {
                case "PRINT": {
                    int result = Print.fprint(index);
                    if (result == 0) Error.FatalError(1);
                    else index = result;
                    break;
                }
                case "INT_T": {
                    int result = Declaration.int_d(index);
                    if (result == 0) Error.FatalError(2);
                    else index = result;
                    break;
                }
                case "STRING_T": {
                    int result = Declaration.string_d(index);
                    if (result == 0) Error.FatalError(3);
                    else index = result;
                    break;
                }
                case "DECIMAL_T": {
                    int result = Declaration.decimal_d(index);
                    if (result == 0) Error.FatalError(4);
                    else index = result;
                    break;
                }
                case "BOOL_T": {
                    int result = Declaration.bool_d(index);
                    if (result == 0) Error.FatalError(6);
                    else index = result;
                    break;
                }
                case "IF": {
                    int[] ret_v = fif(index);
                    if (ret_v[0] == 0) Error.FatalError(7);
                    else {
                        if (ret_v[2] == 0) {
                            skip.add(ret_v[1]);
                            if (ret_v[5] != 0) {
                                skip_store.put(ret_v[1], ret_v[5]);
                            }
                            index = ret_v[0];
                        } else {
                            if (ret_v[4] != 0) {
                                index = ret_v[4];
                                skip.add(ret_v[5]);
                            } else index = ret_v[1];
                        }
                    }
                    break;
                }
                case "FOR": {
                    int result = ForLoop.ffor(index);
                    if (result == 0) Error.FatalError(9);
                    else index = result;
                    break;
                }
                case "GOTO": {
                    int result = Goto.fgoto(index);
                    if (result == -1) Error.FatalError(10);
                    else index = result;
                    break;
                }
            }
            index_r = index;

        }

        // FOR DEBUGGING

        /*System.out.println("======================================");
        System.out.println("Value store check:");
        System.out.println("======================================");
        int_store.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + " " + entry.getValue());
        });
        string_store.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + " " + entry.getValue());
        });
        decimal_store.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + " " + entry.getValue());
        });
        bool_store.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + " " + entry.getValue());
        });
        skip.forEach(System.out::println);*/
        return index_r;

    }

    // if -> 1: index
    // if <- 1: index 2: index of } 3: 0 for skip } 1 for skip to pos } + 1, execute else, begin else, end else
    private static int[] fif(int index) {
        int[] ret = new int[6];
        if (tokens.get(index + 1).key == "L_PARENTHESES") {
            index += 2;
            int[] ret_v = expression_bool(index);
            index = ret_v[0];
            if (index != 0) {
                if (index < tokens.size() && tokens.get(index).key == "R_PARENTHESES") {
                    if (index + 1 < tokens.size() && tokens.get(index + 1).key == "L_BRACES") {
                        index++;
                        int r_pos = HelperFunctions.search_r_b(index);
                        int[] ret_1 = felse(r_pos);
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

    private static int[] felse(int index) {
        int[] ret = {0, 0};
        if (index + 1 < tokens.size() && tokens.get(index + 1).key == "ELSE") {
            index++;
            if (index + 1 < tokens.size() && tokens.get(index + 1).key == "L_BRACES") {
                index++;
                int r_pos = HelperFunctions.search_r_b(index);
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

    // ret { index, value }
    private static int[] expression_bool(int index) {
        int[] ret = {0, 0};
        if (tokens.get(index).key == "NOT") {
            index++;
            if (base_bool(index)[0] != 0) {
                int[] ret_v = base_bool(index);
                ret[0] = ret_v[0];
                if (ret_v[1] == 1) ret[1] = 0;
                else ret[1] = 1;
                return ret;
            } else {
                ret[0] = 0;
                return ret;
            }
        } else if (base_bool(index)[0] != 0) {
            int[] ret_1 = base_bool(index);
            index = ret_1[0];
            if (index < tokens.size() && (tokens.get(index).key.equals("OR") || tokens.get(index).key.equals("AND"))) {
                index++;
                int l_index = index - 1;
                if (expression_bool(index)[0] != 0) {
                    int[] ret_2 = expression_bool(index);
                    ret[0] = ret_2[0];
                    switch (tokens.get(l_index).key) {
                        case "OR": {
                            boolean a = false;
                            boolean b = false;
                            if (ret_1[0] == 1) a = true;
                            if (ret_2[0] == 1) b = true;
                            if (a || b) ret[1] = 1;
                            else ret[1] = 0;
                            break;
                        }
                        case "AND": {
                            boolean a = false;
                            boolean b = false;
                            if (ret_1[0] == 1) a = true;
                            if (ret_2[0] == 1) b = true;
                            if (a && b) ret[1] = 1;
                            else ret[1] = 0;
                            break;
                        }
                    }
                    return ret;
                } else {
                    ret[0] = 0;
                    return ret;
                }
            } else {
                ret[0] = index;
                ret[1] = ret_1[1];
                return ret;
            }
        }
        return ret;
    }

    // ret { index, value }
    private static int[] base_bool(int index) {
        int[] ret = {0, 0};
        if (tokens.get(index).key == "NAME" && bool_store.containsKey(tokens.get(index).value)) {
            if (bool_store.get(tokens.get(index).value)) ret[1] = 1;
            else ret[1] = 0;
            ret[0] = ++index;
            return ret;
        } else if (tokens.get(index).key == "BOOL" && (tokens.get(index).value.equals("true") || tokens.get(index).value.equals("false"))) {
            if (tokens.get(index).value.equals("true")) ret[1] = 1;
            else ret[1] = 0;
            ret[0] = ++index;
            return ret;
        } else if (comp_int(index)[0] != 0) {
            int[] ret_v = comp_int(index);
            ret[0] = ret_v[0];
            ret[1] = ret_v[1];
            return ret;
        } else if (tokens.get(index).key == "L_PARENTHESES") {
            index++;
            if (expression_bool(index)[0] != 0) {
                int[] ret_v = expression_bool(index);
                index = ret_v[0];
                if (tokens.get(index).key == "R_PARENTHESES") {
                    ret[0] = ++index;
                    return ret;
                } else {
                    ret[0] = 0;
                    return ret;
                }
            } else {
                ret[0] = 0;
                return ret;
            }
        }
        return ret;
    }

    // ret { index, value }
    private static int[] comp_int(int index) {
        int[] ret = {0, 0};
        int[] ret_1 = Integers.expression_int(index, 0);
        if (ret_1[0] != 0) {
            index = ret_1[0];
            int c_index = index;
            if (tokens.get(index).key == "EQUAL_TO" || tokens.get(index).key == "NOT_EQUAL" || tokens.get(index).key == "GREATER_EQUAL"
                    || tokens.get(index).key == "LESS_EQUAL" || tokens.get(index).key == "LESS_THAN" || tokens.get(index).key == "GREATER_THAN") {
                int[] ret_2 = Integers.expression_int(++index, 0);
                if (ret_2[0] != 0) {
                    index = ret_2[0];
                    switch (tokens.get(c_index).key) {
                        case "EQUAL_TO": {
                            if (ret_1[1] == ret_2[1]) ret[1] = 1;
                            else ret[1] = 0;
                            break;
                        }
                        case "NOT_EQUAL": {
                            if (ret_1[1] != ret_2[1]) ret[1] = 1;
                            else ret[1] = 0;
                            break;
                        }
                        case "GREATER_EQUAL": {
                            if (ret_1[1] >= ret_2[1]) ret[1] = 1;
                            else ret[1] = 0;
                            break;
                        }
                        case "LESS_EQUAL": {
                            if (ret_1[1] <= ret_2[1]) ret[1] = 1;
                            else ret[1] = 0;
                            break;
                        }
                        case "LESS_THAN": {
                            if (ret_1[1] < ret_2[1]) ret[1] = 1;
                            else ret[1] = 0;
                            break;
                        }
                        case "GREATER_THAN": {
                            if (ret_1[1] > ret_2[1]) ret[1] = 1;
                            else ret[1] = 0;
                            break;
                        }
                    }
                    ret[0] = index;
                    return ret;
                } else {
                    ret[0] = 0;
                    return ret;
                }
            } else {
                ret[0] = 0;
                return ret;
            }
        }
        return ret;
    }
}
