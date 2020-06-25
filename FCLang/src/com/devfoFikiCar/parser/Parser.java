package com.devfoFikiCar.parser;

import com.devfoFikiCar.Token;
import com.devfoFikiCar.error.Error;

import java.util.ArrayList;
import java.util.HashMap;

public class Parser {
    private static ArrayList<Token> tokens = new ArrayList<>();
    private static ArrayList<Integer> skip = new ArrayList<>();

    private static HashMap<String, Integer> int_store = new HashMap<>();
    private static HashMap<String, String> string_store = new HashMap<>();
    private static HashMap<String, Double> decimal_store = new HashMap<>();
    private static HashMap<String, Boolean> bool_store = new HashMap<>();
    private static HashMap<Integer, Integer> skip_store = new HashMap<>();
    private static HashMap<String, Integer> goto_store = new HashMap<>();

    public static int parse(ArrayList<Token> old, int begin, int end) {
        tokens = old;
        int index_r = 0;
        safe_goto();
        for (int index = begin; index < end; index++) {
            if (skip_store.containsKey(index)) {
                index = skip_store.get(index);
                continue;
            }
            if (skip.contains(index)) continue;
            String key = tokens.get(index).key;
            switch (key) {
                case "PRINT": {
                    int result = fprint(index);
                    if (result == 0) Error.FatalError(1);
                    else index = result;
                    break;
                }
                case "INT_T": {
                    int result = int_d(index);
                    if (result == 0) Error.FatalError(2);
                    else index = result;
                    break;
                }
                case "STRING_T": {
                    int result = string_d(index);
                    if (result == 0) Error.FatalError(3);
                    else index = result;
                    break;
                }
                case "DECIMAL_T": {
                    int result = decimal_d(index);
                    if (result == 0) Error.FatalError(4);
                    else index = result;
                    break;
                }
                case "BOOL_T": {
                    int result = bool_d(index);
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
                    int result = ffor(index);
                    if (result == 0) Error.FatalError(9);
                    else index = result;
                    break;
                }
                case "GOTO": {
                    int result = fgoto(index);
                    if (result == -1) Error.FatalError(10);
                    else index = result;
                    break;
                }
            }
            index_r = index;
        }
        return index_r;
        // FOR DEBUGGING
        /*
        System.out.println("======================================");
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
        skip.forEach(System.out::println);
        */
    }

    private static void safe_goto() {
        for (int index = 0; index < tokens.size(); index++) {
            if (tokens.get(index).key == "L_GOTO") {
                goto_store.put(tokens.get(index).value, index);
            }
        }
    }

    private static int fgoto(int index) {
        if (tokens.get(index + 1).key == "L_GOTO") {
            index++;
        } else return -1;

        if (goto_store.containsKey(tokens.get(index).value)) {
            index = goto_store.get(tokens.get(index).value);
        } else return -1;

        return index;
    }

    private static int ffor(int index) {
        int index_name = 0;
        int v_start = 0;
        int v_end = 0;
        int index_c = 0;
        int index_sg = 0;
        int v_step = 0;
        int for_begin = 0;
        int for_end = 0;

        String name = "";

        if (tokens.get(index + 1).key == "L_PARENTHESES") {
            index++;
        } else return 0;

        if (tokens.get(index + 1).key == "NAME") {
            index++;
            index_name = index;
        } else return 0;

        if (tokens.get(index + 1).key == "SPLIT") {
            index++;
        } else return 0;

        int[] ret_1 = expression_int(++index, 0);

        if (ret_1[0] != 0) {
            index = ret_1[0];
            v_start = ret_1[1];
        } else return 0;

        if (tokens.get(index).key != "SPLIT") return 0;

        int[] ret_2 = expression_int(++index, 0);

        if (ret_2[0] != 0) {
            index = ret_2[0];
            v_end = ret_2[1];
        } else return 0;

        if (tokens.get(index).key != "SPLIT") return 0;

        if (tokens.get(index + 1).key == "EQUAL_TO" || tokens.get(index + 1).key == "NOT_EQUAL" || tokens.get(index + 1).key == "GREATER_EQUAL"
                || tokens.get(index + 1).key == "LESS_EQUAL" || tokens.get(index + 1).key == "LESS_THAN" || tokens.get(index + 1).key == "GREATER_THAN") {
            index++;
            index_c = index;
        } else return 0;

        if (tokens.get(index + 1).key == "SPLIT") {
            index++;
        } else return 0;

        if (tokens.get(index + 1).key == "ADDITION" || tokens.get(index + 1).key == "SUBTRACTION" || tokens.get(index + 1).key == "MULTIPLICATION" || tokens.get(index + 1).key == "DIVISION") {
            index++;
            index_sg = index;
        } else return 0;

        if (tokens.get(index + 1).key == "SPLIT") {
            index++;
        } else return 0;

        int[] ret_3 = expression_int(++index, 0);

        if (ret_3[0] != 0) {
            index = ret_3[0];
            v_step = ret_3[1];
        } else return 0;

        if (tokens.get(index).key != "R_PARENTHESES") return 0;

        if (tokens.get(index + 1).key == "L_BRACES") {
            index++;
            for_begin = index;
        } else return 0;

        int r_pos = search_r_b(index);

        if (r_pos != 0) {
            index++;
            for_end = r_pos;
        } else return 0;

        name = tokens.get(index_name).value;
        int_store.put(name, v_start);

        switch (tokens.get(index_c).key) {
            case "EQUAL_TO": {
                switch (tokens.get(index_sg).key) {
                    case "ADDITION": {
                        for (int i = v_start; i == v_end; i += v_step) {
                            int index_t = parse(tokens, for_begin, for_end);
                            if (index_t > for_end || index_t < for_begin) {
                                int_store.remove(name);
                                return index_t;
                            }
                        }
                        int_store.remove(name);
                        break;
                    }
                    case "SUBTRACTION": {
                        for (int i = v_start; i == v_end; i -= v_step) {
                            int index_t = parse(tokens, for_begin, for_end);
                            if (index_t > for_end || index_t < for_begin) {
                                int_store.remove(name);
                                return index_t;
                            }
                        }
                        int_store.remove(name);
                        break;
                    }
                    case "MULTIPLICATION": {
                        for (int i = v_start; i == v_end; i *= v_step) {
                            int_store.put(name, i);
                            int index_t = parse(tokens, for_begin, for_end);
                            if (index_t > for_end || index_t < for_begin) {
                                int_store.remove(name);
                                return index_t;
                            }
                        }
                        int_store.remove(name);
                        break;
                    }
                    case "DIVISION": {
                        for (int i = v_start; i == v_end; i /= v_step) {
                            int_store.put(name, i);
                            int index_t = parse(tokens, for_begin, for_end);
                            if (index_t > for_end || index_t < for_begin) {
                                int_store.remove(name);
                                return index_t;
                            }
                        }
                        int_store.remove(name);
                        break;
                    }
                }
            }
            case "NOT_EQUAL": {
                switch (tokens.get(index_sg).key) {
                    case "ADDITION": {
                        for (int i = v_start; i != v_end; i += v_step) {
                            int_store.put(name, i);
                            int index_t = parse(tokens, for_begin, for_end);
                            if (index_t > for_end || index_t < for_begin) {
                                int_store.remove(name);
                                return index_t;
                            }
                        }
                        int_store.remove(name);
                        break;
                    }
                    case "SUBTRACTION": {
                        for (int i = v_start; i != v_end; i -= v_step) {
                            int_store.put(name, i);
                            int index_t = parse(tokens, for_begin, for_end);
                            if (index_t > for_end || index_t < for_begin) {
                                int_store.remove(name);
                                return index_t;
                            }
                        }
                        int_store.remove(name);
                        break;
                    }
                    case "MULTIPLICATION": {
                        for (int i = v_start; i != v_end; i *= v_step) {
                            int_store.put(name, i);
                            int index_t = parse(tokens, for_begin, for_end);
                            if (index_t > for_end || index_t < for_begin) {
                                int_store.remove(name);
                                return index_t;
                            }
                        }
                        int_store.remove(name);
                        break;
                    }
                    case "DIVISION": {
                        for (int i = v_start; i != v_end; i /= v_step) {
                            int_store.put(name, i);
                            int index_t = parse(tokens, for_begin, for_end);
                            if (index_t > for_end || index_t < for_begin) {
                                int_store.remove(name);
                                return index_t;
                            }
                        }
                        int_store.remove(name);
                        break;
                    }
                }
            }
            case "GREATER_EQUAL": {
                switch (tokens.get(index_sg).key) {
                    case "ADDITION": {
                        for (int i = v_start; i >= v_end; i += v_step) {
                            int_store.put(name, i);
                            int index_t = parse(tokens, for_begin, for_end);
                            if (index_t > for_end || index_t < for_begin) {
                                int_store.remove(name);
                                return index_t;
                            }
                        }
                        int_store.remove(name);
                        break;
                    }
                    case "SUBTRACTION": {
                        for (int i = v_start; i >= v_end; i -= v_step) {
                            int_store.put(name, i);
                            int index_t = parse(tokens, for_begin, for_end);
                            if (index_t > for_end || index_t < for_begin) {
                                int_store.remove(name);
                                return index_t;
                            }
                        }
                        int_store.remove(name);
                        break;
                    }
                    case "MULTIPLICATION": {
                        for (int i = v_start; i >= v_end; i *= v_step) {
                            int_store.put(name, i);
                            int index_t = parse(tokens, for_begin, for_end);
                            if (index_t > for_end || index_t < for_begin) {
                                int_store.remove(name);
                                return index_t;
                            }
                        }
                        int_store.remove(name);
                        break;
                    }
                    case "DIVISION": {
                        for (int i = v_start; i >= v_end; i /= v_step) {
                            int_store.put(name, i);
                            int index_t = parse(tokens, for_begin, for_end);
                            if (index_t > for_end || index_t < for_begin) {
                                int_store.remove(name);
                                return index_t;
                            }
                        }
                        int_store.remove(name);
                        break;
                    }
                }
            }
            case "LESS_EQUAL": {
                switch (tokens.get(index_sg).key) {
                    case "ADDITION": {
                        for (int i = v_start; i <= v_end; i += v_step) {
                            int_store.put(name, i);
                            int index_t = parse(tokens, for_begin, for_end);
                            if (index_t > for_end || index_t < for_begin) {
                                int_store.remove(name);
                                return index_t;
                            }
                        }
                        int_store.remove(name);
                        break;
                    }
                    case "SUBTRACTION": {
                        for (int i = v_start; i <= v_end; i -= v_step) {
                            int_store.put(name, i);
                            int index_t = parse(tokens, for_begin, for_end);
                            if (index_t > for_end || index_t < for_begin) {
                                int_store.remove(name);
                                return index_t;
                            }
                        }
                        int_store.remove(name);
                        break;
                    }
                    case "MULTIPLICATION": {
                        for (int i = v_start; i <= v_end; i *= v_step) {
                            int_store.put(name, i);
                            int index_t = parse(tokens, for_begin, for_end);
                            if (index_t > for_end || index_t < for_begin) {
                                int_store.remove(name);
                                return index_t;
                            }
                        }
                        int_store.remove(name);
                        break;
                    }
                    case "DIVISION": {
                        for (int i = v_start; i <= v_end; i /= v_step) {
                            int_store.put(name, i);
                            int index_t = parse(tokens, for_begin, for_end);
                            if (index_t > for_end || index_t < for_begin) {
                                int_store.remove(name);
                                return index_t;
                            }
                        }
                        int_store.remove(name);
                        break;
                    }
                }
            }
            case "LESS_THAN": {
                switch (tokens.get(index_sg).key) {
                    case "ADDITION": {
                        for (int i = v_start; i < v_end; i += v_step) {
                            int_store.put(name, i);
                            int index_t = parse(tokens, for_begin, for_end);
                            if (index_t > for_end || index_t < for_begin) {
                                int_store.remove(name);
                                return index_t;
                            }
                        }
                        int_store.remove(name);
                        break;
                    }
                    case "SUBTRACTION": {
                        for (int i = v_start; i < v_end; i -= v_step) {
                            int_store.put(name, i);
                            int index_t = parse(tokens, for_begin, for_end);
                            if (index_t > for_end || index_t < for_begin) {
                                int_store.remove(name);
                                return index_t;
                            }
                        }
                        int_store.remove(name);
                        break;
                    }
                    case "MULTIPLICATION": {
                        for (int i = v_start; i < v_end; i *= v_step) {
                            int_store.put(name, i);
                            int index_t = parse(tokens, for_begin, for_end);
                            if (index_t > for_end || index_t < for_begin) {
                                int_store.remove(name);
                                return index_t;
                            }
                        }
                        int_store.remove(name);
                        break;
                    }
                    case "DIVISION": {
                        for (int i = v_start; i < v_end; i /= v_step) {
                            int_store.put(name, i);
                            int index_t = parse(tokens, for_begin, for_end);
                            if (index_t > for_end || index_t < for_begin) {
                                int_store.remove(name);
                                return index_t;
                            }
                        }
                        int_store.remove(name);
                        break;
                    }
                }
            }
            case "GREATER_THAN": {
                switch (tokens.get(index_sg).key) {
                    case "ADDITION": {
                        for (int i = v_start; i > v_end; i += v_step) {
                            int_store.put(name, i);
                            int index_t = parse(tokens, for_begin, for_end);
                            if (index_t > for_end || index_t < for_begin) {
                                int_store.remove(name);
                                return index_t;
                            }
                        }
                        int_store.remove(name);
                        break;
                    }
                    case "SUBTRACTION": {
                        for (int i = v_start; i > v_end; i -= v_step) {
                            int_store.put(name, i);
                            int index_t = parse(tokens, for_begin, for_end);
                            if (index_t > for_end || index_t < for_begin) {
                                int_store.remove(name);
                                return index_t;
                            }
                        }
                        int_store.remove(name);
                        break;
                    }
                    case "MULTIPLICATION": {
                        for (int i = v_start; i > v_end; i *= v_step) {
                            int_store.put(name, i);
                            int index_t = parse(tokens, for_begin, for_end);
                            if (index_t > for_end || index_t < for_begin) {
                                int_store.remove(name);
                                return index_t;
                            }
                        }
                        int_store.remove(name);
                        break;
                    }
                    case "DIVISION": {
                        for (int i = v_start; i > v_end; i /= v_step) {
                            int_store.put(name, i);
                            int index_t = parse(tokens, for_begin, for_end);
                            if (index_t > for_end || index_t < for_begin) {
                                int_store.remove(name);
                                return index_t;
                            }
                        }
                        int_store.remove(name);
                        break;
                    }
                }
            }
        }

        return for_end;
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
                        int r_pos = search_r_b(index);
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
                int r_pos = search_r_b(index);
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

    // searches for } <- return index from }
    private static int search_r_b(int index) {
        int r_pos = 0;
        int n_skip = 0;
        for (int i = ++index; i < tokens.size(); i++) {
            if (tokens.get(i).key == "L_BRACES") n_skip++;
            else if (tokens.get(i).key == "R_BRACES") {
                if (n_skip == 0) {
                    return i;
                } else {
                    n_skip--;
                }
            }
        }
        return r_pos;
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
        int[] ret_1 = expression_int(index, 0);
        if (ret_1[0] != 0) {
            index = ret_1[0];
            int c_index = index;
            if (tokens.get(index).key == "EQUAL_TO" || tokens.get(index).key == "NOT_EQUAL" || tokens.get(index).key == "GREATER_EQUAL"
                    || tokens.get(index).key == "LESS_EQUAL" || tokens.get(index).key == "LESS_THAN" || tokens.get(index).key == "GREATER_THAN") {
                int[] ret_2 = expression_int(++index, 0);
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

    // print: 'PRINT' STRING | INT | DECIMAL | NAME
    private static int fprint(int index) {
        if (tokens.get(index + 1).key == "STRING" || tokens.get(index + 1).key == "INT" || tokens.get(index + 1).key == "DECIMAL" || tokens.get(index + 1).key == "BOOL") {
            switch (tokens.get(index + 1).key) {
                case "INT": {
                    int[] ret_v_int = expression_int(index + 1, 0);
                    System.out.println(ret_v_int[1]);
                    return --ret_v_int[0];
                }
                case "DECIMAL": {
                    double[] ret_v_double = expression_decimal(index * 1.0 + 1.0, 0.0);
                    System.out.println(ret_v_double[1]);
                    return (int) --ret_v_double[0];
                }
                case "BOOL": {
                    System.out.println(tokens.get(index + 1).value);
                    break;
                }
                default: {
                    String toPrint = tokens.get(index + 1).value;
                    if (tokens.get(index + 1).key == "STRING")
                        toPrint = toPrint.subSequence(1, toPrint.length() - 1).toString();
                    System.out.println(toPrint);
                    break;
                }
            }
            return index + 1;
        } else if (tokens.get(index + 1).key == "NAME") {
            if (int_store.containsKey(tokens.get(index + 1).value) || string_store.containsKey(tokens.get(index + 1).value) || decimal_store.containsKey(tokens.get(index + 1).value) || bool_store.containsKey(tokens.get(index + 1).value)) {
                int store = 0;
                if (bool_store.containsKey(tokens.get(index + 1).value)) store = 1;
                else if (int_store.containsKey(tokens.get(index + 1).value)) store = 2;
                else if (decimal_store.containsKey(tokens.get(index + 1).value)) store = 3;
                else store = 4;
                switch (store) {
                    case 1: {
                        System.out.println(bool_store.get(tokens.get(index + 1).value));
                        break;
                    }
                    case 2: {
                        System.out.println(int_store.get(tokens.get(index + 1).value));
                        break;
                    }
                    case 3: {
                        System.out.println(decimal_store.get(tokens.get(index + 1).value));
                        break;
                    }
                    case 4: {
                        String value = string_store.get(tokens.get(index + 1).value);
                        value = value.subSequence(1, value.length() - 1).toString();
                        System.out.println(value);
                        break;
                    }
                }
                return index + 1;
            } else return 0;
        } else return 0;
    }

    // int declaration
    private static int int_d(int index) {
        if (tokens.get(index + 1).key == "NAME") {
            if (tokens.get(index + 2).key == "EQUALS") {
                int[] ret_v = expression_int(index + 3, 0);
                if (ret_v[0] != 0) {
                    int_store.put(tokens.get(index + 1).value, ret_v[1]);
                    index = ret_v[0];
                    return --index;
                } else {
                    index = declare_v(index + 3, tokens.get(index).key, tokens.get(index + 1).value);
                    return index;
                }
            } else return 0;
        }
        return 0;
    }

    // string declaration
    private static int string_d(int index) {
        if (tokens.get(index + 1).key == "NAME") {
            if (tokens.get(index + 2).key == "EQUALS") {
                index = declare_v(index + 3, tokens.get(index).key, tokens.get(index + 1).value);
                return index;
            } else return 0;
        } else return 0;
    }

    // bool declaration
    private static int bool_d(int index) {
        if (tokens.get(index + 1).key == "NAME") {
            if (tokens.get(index + 2).key == "EQUALS") {
                index = declare_v(index + 3, tokens.get(index).key, tokens.get(index + 1).value);
                return index;
            } else return 0;
        } else return 0;
    }

    // decimal declaration
    private static int decimal_d(int index) {
        if (tokens.get(index + 1).key == "NAME") {
            if (tokens.get(index + 2).key == "EQUALS") {
                double[] ret_v = expression_decimal(index * 1.0 + 3.0, 0);
                if (ret_v[0] != 0) {
                    decimal_store.put(tokens.get(index + 1).value, ret_v[1]);
                    index = (int) ret_v[0];
                    return --index;
                } else {
                    index = declare_v(index + 3, tokens.get(index).key, tokens.get(index + 1).value);
                    return index;
                }
            } else return 0;
        } else return 0;
    }

    // declares
    private static int declare_v(int index, String type, String name) {
        String value_t = tokens.get(index).key + "_T";
        if (type.equals(value_t)) {
            switch (type) {
                case "INT_T": {
                    int_store.put(name, Integer.valueOf(tokens.get(index).value));
                    break;
                }
                case "STRING_T": {
                    string_store.put(name, String.valueOf(tokens.get(index).value));
                    break;
                }
                case "DECIMAL_T": {
                    decimal_store.put(name, Double.valueOf(tokens.get(index).value));
                    break;
                }
                case "BOOL_T": {
                    bool_store.put(name, Boolean.valueOf(tokens.get(index).value));
                }
            }
            return index;
        }
        return 0;
    }

    // expression: term (('-' | '+') term)?
    private static int[] expression_int(int index, int value) {
        int[] ret = {index, value};
        if (term_int(index, value)[0] != 0) {
            int[] ret_v = term_int(index, value);
            index = ret_v[0];
            value = ret_v[1];
            if (index < tokens.size() && (tokens.get(index).key == "ADDITION" || tokens.get(index).key == "SUBTRACTION")) {
                ret_v = expression_int(index + 1, value);
                switch (tokens.get(index).key) {
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
            if (index < tokens.size() && (tokens.get(index).key == "MULTIPLICATION" || tokens.get(index).key == "DIVISION")) {
                ret_v = expression_int(index + 1, value);
                switch (tokens.get(index).key) {
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
        if (tokens.get(index).key == "INT" || tokens.get(index).key == "NAME") {
            if (int_store.containsKey(tokens.get(index).value)) {
                ret[1] = int_store.get(tokens.get(index).value);
            } else {
                try {
                    ret[1] = Integer.parseInt(tokens.get(index).value);
                } catch (Exception e) {
                }
            }
            ret[0] = index + 1;
            return ret;
        } else if (tokens.get(index).key == "L_PARENTHESES") {
            int[] ret_v = expression_int(index + 1, ret[1]);
            index = ret_v[0];
            value = ret_v[1];
            if (index == 0) {
                ret[0] = 0;
                return ret;
            } else {
                if (index < tokens.size() && tokens.get(index).key == "R_PARENTHESES") {
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

    // expression for decimal
    private static double[] expression_decimal(double index, double value) {
        double[] ret = {index, value};
        if (term_decimal(index, value)[0] != 0) {
            double[] ret_v = term_decimal(index, value);
            index = ret_v[0];
            value = ret_v[1];
            if (index < tokens.size() && (tokens.get((int) index).key == "ADDITION" || tokens.get((int) index).key == "SUBTRACTION")) {
                ret_v = expression_decimal(index + 1, value);
                switch (tokens.get((int) index).key) {
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
            if (index < tokens.size() && (tokens.get((int) index).key == "MULTIPLICATION" || tokens.get((int) index).key == "DIVISION")) {
                ret_v = expression_decimal(index + 1, value);
                switch (tokens.get((int) index).key) {
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
        if (tokens.get((int) index).key == "DECIMAL" || tokens.get((int) index).key == "NAME") {
            if (decimal_store.containsKey(tokens.get((int) index).value)) {
                ret[1] = decimal_store.get(tokens.get((int) index).value);
            } else {
                try {
                    ret[1] = Double.parseDouble(tokens.get((int) index).value);
                } catch (Exception e) {
                }
            }
            ret[0] = index + 1;
            return ret;
        } else if (tokens.get((int) index).key == "L_PARENTHESES") {
            double[] ret_v = expression_decimal(index + 1, ret[1]);
            index = ret_v[0];
            value = ret_v[1];
            if (index == 0) {
                ret[0] = 0;
                return ret;
            } else {
                if (index < tokens.size() && tokens.get((int) index).key == "R_PARENTHESES") {
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
