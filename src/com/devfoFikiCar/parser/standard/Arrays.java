package com.devfoFikiCar.parser.standard;

import com.devfoFikiCar.parser.Parser;
import javafx.util.Pair;

import java.util.ArrayList;

public class Arrays {
    /* TODO:
    *   declaration   {TYPE}Array name = new {TYPE}Array{number}        DONE
    *   size                                                            DONE
    *   get
    *   set
    *   sort
    * */
    /*
    * tokens:
    *  {Type}Array -> {TYPE}_ARRAY
    * */

    /**
     * declareIntArray declares int array, checks syntax and ensures capacity.
     * @param index position to start parsing from
     * @return index to continue parsing from or 0 if error occurred
     */
    public static int declareIntArray(int index){
        String name = "";
        if(Parser.tokens.get(index + 1).key != "NAME") return 0;
        name = Parser.tokens.get(index + 1).value;
        index++;
        if(Parser.tokens.get(index + 1).key != "EQUALS") return 0;
        index++;
        if(Parser.tokens.get(index + 1).key != "NEW") return 0;
        index++;
        if(Parser.tokens.get(index + 1).key != "INT_ARRAY") return 0;
        index++;
        if(Parser.tokens.get(index + 1).key != "L_BRACES") return 0;
        index++;
        int[] ret = Integers.expressionInt(index + 1, 0);
        if(ret[0] == 0) return 0;
        index = ret[0];
        if(Parser.tokens.get(index).key != "R_BRACES") return 0;
        Parser.intArrayStore.put(name, new Pair<>(new ArrayList<Integer>(ret[1]), ret[1]));
        return index;
    }

    /**
     * declareDecimalArray declares decimal array, checks syntax and ensures capacity.
     * @param index position to start parsing from
     * @return index to continue parsing from or 0 if error occurred
     */
    public static int declareDecimalArray(int index){
        String name = "";
        if(Parser.tokens.get(index + 1).key != "NAME") return 0;
        name = Parser.tokens.get(index + 1).value;
        index++;
        if(Parser.tokens.get(index + 1).key != "EQUALS") return 0;
        index++;
        if(Parser.tokens.get(index + 1).key != "NEW") return 0;
        index++;
        if(Parser.tokens.get(index + 1).key != "DECIMAL_ARRAY") return 0;
        index++;
        if(Parser.tokens.get(index + 1).key != "L_BRACES") return 0;
        index++;
        int[] ret = Integers.expressionInt(index + 1, 0);
        if(ret[0] == 0) return 0;
        index = ret[0];
        if(Parser.tokens.get(index).key != "R_BRACES") return 0;
        Parser.decimalArrayStore.put(name, new Pair<>(new ArrayList<Double>(ret[1]), ret[1]));
        return index;
    }

    /**
     * declareStringArray declares string array, checks syntax and ensures capacity.
     * @param index position to start parsing from
     * @return index to continue parsing from or 0 if error occurred
     */
    public static int declareStringArray(int index){
        String name = "";
        if(Parser.tokens.get(index + 1).key != "NAME") return 0;
        name = Parser.tokens.get(index + 1).value;
        index++;
        if(Parser.tokens.get(index + 1).key != "EQUALS") return 0;
        index++;
        if(Parser.tokens.get(index + 1).key != "NEW") return 0;
        index++;
        if(Parser.tokens.get(index + 1).key != "STRING_ARRAY") return 0;
        index++;
        if(Parser.tokens.get(index + 1).key != "L_BRACES") return 0;
        index++;
        int[] ret = Integers.expressionInt(index + 1, 0);
        if(ret[0] == 0) return 0;
        index = ret[0];
        if(Parser.tokens.get(index).key != "R_BRACES") return 0;
        Parser.stringArrayStore.put(name, new Pair<>(new ArrayList<String>(ret[1]), ret[1]));
        return index;
    }

    /**
     * declareBoolArray declares bool array, checks syntax and ensures capacity.
     * @param index position to start parsing from
     * @return index to continue parsing from or 0 if error occurred
     */
    public static int declareBoolArray(int index){
        String name = "";
        if(Parser.tokens.get(index + 1).key != "NAME") return 0;
        name = Parser.tokens.get(index + 1).value;
        index++;
        if(Parser.tokens.get(index + 1).key != "EQUALS") return 0;
        index++;
        if(Parser.tokens.get(index + 1).key != "NEW") return 0;
        index++;
        if(Parser.tokens.get(index + 1).key != "BOOL_ARRAY") return 0;
        index++;
        if(Parser.tokens.get(index + 1).key != "L_BRACES") return 0;
        index++;
        int[] ret = Integers.expressionInt(index + 1, 0);
        if(ret[0] == 0) return 0;
        index = ret[0];
        if(Parser.tokens.get(index).key != "R_BRACES") return 0;
        Parser.boolArrayStore.put(name, new Pair<>(new ArrayList<Boolean>(ret[1]), ret[1]));
        return index;
    }

    public static int[] arraySize(int index){
        int[] ret = new int[2];
        int store = 0;
        String name = Parser.tokens.get(index).value;
        if(Parser.intArrayStore.containsKey(name)) store = 1;
        else if(Parser.decimalArrayStore.containsKey(name)) store = 2;
        else if(Parser.stringArrayStore.containsKey(name)) store = 3;
        else if(Parser.boolArrayStore.containsKey(name)) store = 4;
        else {
            ret[0] = 0;
            return ret;
        }
        if(Parser.tokens.get(index + 1).key != "DOT"){
            ret[0] = 0;
            return ret;
        }
        index++;
        if(Parser.tokens.get(index + 1).key != "SIZE") {
            ret[0] = 0;
            return ret;
        }
        index++;
        if(Parser.tokens.get(index + 1).key != "L_PARENTHESES" && Parser.tokens.get(index + 1).key != "R_PARENTHESES"){
            ret[0] = 0;
            return ret;
        }
        index++;
        ret[0] = index;
        switch (store){
            case 1:{
                ret[1] = Parser.intArrayStore.get(name).getValue();
                return ret;
            }
            case 2:{
                ret[1] = Parser.decimalArrayStore.get(name).getValue();
                return ret;
            }
            case 3:{
                ret[1] = Parser.stringArrayStore.get(name).getValue();
                return ret;
            }
            case 4:{
                ret[1] = Parser.boolArrayStore.get(name).getValue();
                return ret;
            }
        }
        return ret;
    }
}
