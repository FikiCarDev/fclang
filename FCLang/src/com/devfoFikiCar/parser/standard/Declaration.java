package com.devfoFikiCar.parser.standard;

import com.devfoFikiCar.parser.IO.GetInput;
import com.devfoFikiCar.parser.Parser;

public class Declaration {
    // int declaration
    public static int declareInt(int index) {
        if (Parser.tokens.get(index + 1).key == "NAME") {
            if (Parser.tokens.get(index + 2).key == "EQUALS") {
                int[] retV = Integers.expressionInt(index + 3, 0);
                if (retV[0] != 0) {
                    Parser.intStore.put(Parser.tokens.get(index + 1).value, retV[1]);
                    index = retV[0];
                    return --index;
                } else if(Parser.tokens.get(index + 3).key == "GET_INT"){
                    int res = GetInput.getInputInt(index + 3);
                    return res;
                } else {
                    index = declareValue(index + 3, Parser.tokens.get(index).key, Parser.tokens.get(index + 1).value);
                    return index;
                }
            } else return 0;
        }
        return 0;
    }

    // string declaration
    public static int declareString(int index) {
        if (Parser.tokens.get(index + 1).key == "NAME") {
            if (Parser.tokens.get(index + 2).key == "EQUALS") {
                if (Parser.tokens.get(index + 3).key == "GET_STRING") {
                    int res = GetInput.getInputString(index + 3);
                    return res;
                }
                index = declareValue(index + 3, Parser.tokens.get(index).key, Parser.tokens.get(index + 1).value);
                return index;
            } else return 0;
        } return 0;
    }

    // bool declaration
    public static int declareBool(int index) {
        if (Parser.tokens.get(index + 1).key == "NAME") {
            if (Parser.tokens.get(index + 2).key == "EQUALS") {
                int[] retV = Bools.bool(index + 3);
                if (retV[0] != 0) {
                    Parser.boolStore.put(Parser.tokens.get(index + 1).value, retV[1] == 1);
                    index = retV[0];
                    return --index;
                } else if(Parser.tokens.get(index + 3).key == "GET_BOOL"){
                    int res = GetInput.getInputBool(index + 3);
                    return res;
                }else {
                    index = declareValue(index + 3, Parser.tokens.get(index).key, Parser.tokens.get(index + 1).value);
                    return index;
                }
            } else return 0;
        } else return 0;
    }

    // decimal declaration
    public static int declareDecimal(int index) {
        if (Parser.tokens.get(index + 1).key == "NAME") {
            if (Parser.tokens.get(index + 2).key == "EQUALS") {
                double[] retV = Decimals.expressionDecimal(index * 1.0 + 3.0, 0);
                if (retV[0] != 0) {
                    Parser.decimalStore.put(Parser.tokens.get(index + 1).value, retV[1]);
                    index = (int) retV[0];
                    return --index;
                } else if(Parser.tokens.get(index + 3).key == "GET_DECIMAL"){
                    int res = GetInput.getInputDecimal(index + 3);
                    return res;
                }else {
                    index = declareValue(index + 3, Parser.tokens.get(index).key, Parser.tokens.get(index + 1).value);
                    return index;
                }
            } else return 0;
        } else return 0;
    }

    // declares
    public static int declareValue(int index, String type, String name) {
        String valueType = Parser.tokens.get(index).key + "_T";
        if (type.equals(valueType)) {
            switch (type) {
                case "INT_T": {
                    Parser.intStore.put(name, Integer.valueOf(Parser.tokens.get(index).value));
                    break;
                }
                case "STRING_T": {
                    Parser.stringStore.put(name, String.valueOf(Parser.tokens.get(index).value));
                    break;
                }
                case "DECIMAL_T": {
                    Parser.decimalStore.put(name, Double.valueOf(Parser.tokens.get(index).value));
                    break;
                }
                case "BOOL_T": {
                    Parser.boolStore.put(name, Boolean.valueOf(Parser.tokens.get(index).value));
                }
            }
            return index;
        }
        return 0;
    }

}
