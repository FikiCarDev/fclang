package com.devfoFikiCar.parser.standard;

import com.devfoFikiCar.parser.Parser;

public class ForLoop {
    public static int ffor(int index) {
        int index_name = 0;
        int v_start = 0;
        int v_end = 0;
        int index_c = 0;
        int index_sg = 0;
        int v_step = 0;
        int for_begin = 0;
        int for_end = 0;

        String name = "";

        if (Parser.tokens.get(index + 1).key == "L_PARENTHESES") {
            index++;
        } else return 0;

        if (Parser.tokens.get(index + 1).key == "NAME") {
            index++;
            index_name = index;
        } else return 0;

        if (Parser.tokens.get(index + 1).key == "SPLIT") {
            index++;
        } else return 0;

        int[] ret_1 = Integers.expression_int(++index, 0);

        if (ret_1[0] != 0) {
            index = ret_1[0];
            v_start = ret_1[1];
        } else return 0;

        if (Parser.tokens.get(index).key != "SPLIT") return 0;

        int[] ret_2 = Integers.expression_int(++index, 0);

        if (ret_2[0] != 0) {
            index = ret_2[0];
            v_end = ret_2[1];
        } else return 0;

        if (Parser.tokens.get(index).key != "SPLIT") return 0;

        if (Parser.tokens.get(index + 1).key == "EQUAL_TO" || Parser.tokens.get(index + 1).key == "NOT_EQUAL" || Parser.tokens.get(index + 1).key == "GREATER_EQUAL"
                || Parser.tokens.get(index + 1).key == "LESS_EQUAL" || Parser.tokens.get(index + 1).key == "LESS_THAN" || Parser.tokens.get(index + 1).key == "GREATER_THAN") {
            index++;
            index_c = index;
        } else return 0;

        if (Parser.tokens.get(index + 1).key == "SPLIT") {
            index++;
        } else return 0;

        if (Parser.tokens.get(index + 1).key == "ADDITION" || Parser.tokens.get(index + 1).key == "SUBTRACTION" || Parser.tokens.get(index + 1).key == "MULTIPLICATION" || Parser.tokens.get(index + 1).key == "DIVISION") {
            index++;
            index_sg = index;
        } else return 0;

        if (Parser.tokens.get(index + 1).key == "SPLIT") {
            index++;
        } else return 0;

        int[] ret_3 = Integers.expression_int(++index, 0);

        if (ret_3[0] != 0) {
            index = ret_3[0];
            v_step = ret_3[1];
        } else return 0;

        if (Parser.tokens.get(index).key != "R_PARENTHESES") return 0;

        if (Parser.tokens.get(index + 1).key == "L_BRACES") {
            index++;
            for_begin = index;
        } else return 0;

        int r_pos = HelperFunctions.search_r_b(index);

        if (r_pos != 0) {
            index++;
            for_end = r_pos;
        } else return 0;

        name = Parser.tokens.get(index_name).value;
        Parser.int_store.put(name, v_start);

        switch (Parser.tokens.get(index_c).key) {
            case "EQUAL_TO": {
                switch (Parser.tokens.get(index_sg).key) {
                    case "ADDITION": {
                        for (int i = v_start; i == v_end; i += v_step) {
                            int index_t = Parser.parse(Parser.tokens, for_begin, for_end);
                            if (index_t > for_end || index_t < for_begin) {
                                Parser.int_store.remove(name);
                                return index_t;
                            }
                        }
                        Parser.int_store.remove(name);
                        break;
                    }
                    case "SUBTRACTION": {
                        for (int i = v_start; i == v_end; i -= v_step) {
                            int index_t = Parser.parse(Parser.tokens, for_begin, for_end);
                            if (index_t > for_end || index_t < for_begin) {
                                Parser.int_store.remove(name);
                                return index_t;
                            }
                        }
                        Parser.int_store.remove(name);
                        break;
                    }
                    case "MULTIPLICATION": {
                        for (int i = v_start; i == v_end; i *= v_step) {
                            Parser.int_store.put(name, i);
                            int index_t = Parser.parse(Parser.tokens, for_begin, for_end);
                            if (index_t > for_end || index_t < for_begin) {
                                Parser.int_store.remove(name);
                                return index_t;
                            }
                        }
                        Parser.int_store.remove(name);
                        break;
                    }
                    case "DIVISION": {
                        for (int i = v_start; i == v_end; i /= v_step) {
                            Parser.int_store.put(name, i);
                            int index_t = Parser.parse(Parser.tokens, for_begin, for_end);
                            if (index_t > for_end || index_t < for_begin) {
                                Parser.int_store.remove(name);
                                return index_t;
                            }
                        }
                        Parser.int_store.remove(name);
                        break;
                    }
                }
            }
            case "NOT_EQUAL": {
                switch (Parser.tokens.get(index_sg).key) {
                    case "ADDITION": {
                        for (int i = v_start; i != v_end; i += v_step) {
                            Parser.int_store.put(name, i);
                            int index_t = Parser.parse(Parser.tokens, for_begin, for_end);
                            if (index_t > for_end || index_t < for_begin) {
                                Parser.int_store.remove(name);
                                return index_t;
                            }
                        }
                        Parser.int_store.remove(name);
                        break;
                    }
                    case "SUBTRACTION": {
                        for (int i = v_start; i != v_end; i -= v_step) {
                            Parser.int_store.put(name, i);
                            int index_t = Parser.parse(Parser.tokens, for_begin, for_end);
                            if (index_t > for_end || index_t < for_begin) {
                                Parser.int_store.remove(name);
                                return index_t;
                            }
                        }
                        Parser.int_store.remove(name);
                        break;
                    }
                    case "MULTIPLICATION": {
                        for (int i = v_start; i != v_end; i *= v_step) {
                            Parser.int_store.put(name, i);
                            int index_t = Parser.parse(Parser.tokens, for_begin, for_end);
                            if (index_t > for_end || index_t < for_begin) {
                                Parser.int_store.remove(name);
                                return index_t;
                            }
                        }
                        Parser.int_store.remove(name);
                        break;
                    }
                    case "DIVISION": {
                        for (int i = v_start; i != v_end; i /= v_step) {
                            Parser.int_store.put(name, i);
                            int index_t = Parser.parse(Parser.tokens, for_begin, for_end);
                            if (index_t > for_end || index_t < for_begin) {
                                Parser.int_store.remove(name);
                                return index_t;
                            }
                        }
                        Parser.int_store.remove(name);
                        break;
                    }
                }
            }
            case "GREATER_EQUAL": {
                switch (Parser.tokens.get(index_sg).key) {
                    case "ADDITION": {
                        for (int i = v_start; i >= v_end; i += v_step) {
                            Parser.int_store.put(name, i);
                            int index_t = Parser.parse(Parser.tokens, for_begin, for_end);
                            if (index_t > for_end || index_t < for_begin) {
                                Parser.int_store.remove(name);
                                return index_t;
                            }
                        }
                        Parser.int_store.remove(name);
                        break;
                    }
                    case "SUBTRACTION": {
                        for (int i = v_start; i >= v_end; i -= v_step) {
                            Parser.int_store.put(name, i);
                            int index_t = Parser.parse(Parser.tokens, for_begin, for_end);
                            if (index_t > for_end || index_t < for_begin) {
                                Parser.int_store.remove(name);
                                return index_t;
                            }
                        }
                        Parser.int_store.remove(name);
                        break;
                    }
                    case "MULTIPLICATION": {
                        for (int i = v_start; i >= v_end; i *= v_step) {
                            Parser.int_store.put(name, i);
                            int index_t = Parser.parse(Parser.tokens, for_begin, for_end);
                            if (index_t > for_end || index_t < for_begin) {
                                Parser.int_store.remove(name);
                                return index_t;
                            }
                        }
                        Parser.int_store.remove(name);
                        break;
                    }
                    case "DIVISION": {
                        for (int i = v_start; i >= v_end; i /= v_step) {
                            Parser.int_store.put(name, i);
                            int index_t = Parser.parse(Parser.tokens, for_begin, for_end);
                            if (index_t > for_end || index_t < for_begin) {
                                Parser.int_store.remove(name);
                                return index_t;
                            }
                        }
                        Parser.int_store.remove(name);
                        break;
                    }
                }
            }
            case "LESS_EQUAL": {
                switch (Parser.tokens.get(index_sg).key) {
                    case "ADDITION": {
                        for (int i = v_start; i <= v_end; i += v_step) {
                            Parser.int_store.put(name, i);
                            int index_t = Parser.parse(Parser.tokens, for_begin, for_end);
                            if (index_t > for_end || index_t < for_begin) {
                                Parser.int_store.remove(name);
                                return index_t;
                            }
                        }
                        Parser.int_store.remove(name);
                        break;
                    }
                    case "SUBTRACTION": {
                        for (int i = v_start; i <= v_end; i -= v_step) {
                            Parser.int_store.put(name, i);
                            int index_t = Parser.parse(Parser.tokens, for_begin, for_end);
                            if (index_t > for_end || index_t < for_begin) {
                                Parser.int_store.remove(name);
                                return index_t;
                            }
                        }
                        Parser.int_store.remove(name);
                        break;
                    }
                    case "MULTIPLICATION": {
                        for (int i = v_start; i <= v_end; i *= v_step) {
                            Parser.int_store.put(name, i);
                            int index_t = Parser.parse(Parser.tokens, for_begin, for_end);
                            if (index_t > for_end || index_t < for_begin) {
                                Parser.int_store.remove(name);
                                return index_t;
                            }
                        }
                        Parser.int_store.remove(name);
                        break;
                    }
                    case "DIVISION": {
                        for (int i = v_start; i <= v_end; i /= v_step) {
                            Parser.int_store.put(name, i);
                            int index_t = Parser.parse(Parser.tokens, for_begin, for_end);
                            if (index_t > for_end || index_t < for_begin) {
                                Parser.int_store.remove(name);
                                return index_t;
                            }
                        }
                        Parser.int_store.remove(name);
                        break;
                    }
                }
            }
            case "LESS_THAN": {
                switch (Parser.tokens.get(index_sg).key) {
                    case "ADDITION": {
                        for (int i = v_start; i < v_end; i += v_step) {
                            Parser.int_store.put(name, i);
                            int index_t = Parser.parse(Parser.tokens, for_begin, for_end);
                            if (index_t > for_end || index_t < for_begin) {
                                Parser.int_store.remove(name);
                                return index_t;
                            }
                        }
                        Parser.int_store.remove(name);
                        break;
                    }
                    case "SUBTRACTION": {
                        for (int i = v_start; i < v_end; i -= v_step) {
                            Parser.int_store.put(name, i);
                            int index_t = Parser.parse(Parser.tokens, for_begin, for_end);
                            if (index_t > for_end || index_t < for_begin) {
                                Parser.int_store.remove(name);
                                return index_t;
                            }
                        }
                        Parser.int_store.remove(name);
                        break;
                    }
                    case "MULTIPLICATION": {
                        for (int i = v_start; i < v_end; i *= v_step) {
                            Parser.int_store.put(name, i);
                            int index_t = Parser.parse(Parser.tokens, for_begin, for_end);
                            if (index_t > for_end || index_t < for_begin) {
                                Parser.int_store.remove(name);
                                return index_t;
                            }
                        }
                        Parser.int_store.remove(name);
                        break;
                    }
                    case "DIVISION": {
                        for (int i = v_start; i < v_end; i /= v_step) {
                            Parser.int_store.put(name, i);
                            int index_t = Parser.parse(Parser.tokens, for_begin, for_end);
                            if (index_t > for_end || index_t < for_begin) {
                                Parser.int_store.remove(name);
                                return index_t;
                            }
                        }
                        Parser.int_store.remove(name);
                        break;
                    }
                }
            }
            case "GREATER_THAN": {
                switch (Parser.tokens.get(index_sg).key) {
                    case "ADDITION": {
                        for (int i = v_start; i > v_end; i += v_step) {
                            Parser.int_store.put(name, i);
                            int index_t = Parser.parse(Parser.tokens, for_begin, for_end);
                            if (index_t > for_end || index_t < for_begin) {
                                Parser.int_store.remove(name);
                                return index_t;
                            }
                        }
                        Parser.int_store.remove(name);
                        break;
                    }
                    case "SUBTRACTION": {
                        for (int i = v_start; i > v_end; i -= v_step) {
                            Parser.int_store.put(name, i);
                            int index_t = Parser.parse(Parser.tokens, for_begin, for_end);
                            if (index_t > for_end || index_t < for_begin) {
                                Parser.int_store.remove(name);
                                return index_t;
                            }
                        }
                        Parser.int_store.remove(name);
                        break;
                    }
                    case "MULTIPLICATION": {
                        for (int i = v_start; i > v_end; i *= v_step) {
                            Parser.int_store.put(name, i);
                            int index_t = Parser.parse(Parser.tokens, for_begin, for_end);
                            if (index_t > for_end || index_t < for_begin) {
                                Parser.int_store.remove(name);
                                return index_t;
                            }
                        }
                        Parser.int_store.remove(name);
                        break;
                    }
                    case "DIVISION": {
                        for (int i = v_start; i > v_end; i /= v_step) {
                            Parser.int_store.put(name, i);
                            int index_t = Parser.parse(Parser.tokens, for_begin, for_end);
                            if (index_t > for_end || index_t < for_begin) {
                                Parser.int_store.remove(name);
                                return index_t;
                            }
                        }
                        Parser.int_store.remove(name);
                        break;
                    }
                }
            }
        }

        return for_end;
    }
}
