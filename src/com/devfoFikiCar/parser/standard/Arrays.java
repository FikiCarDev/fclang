package com.devfoFikiCar.parser.standard;

import com.devfoFikiCar.parser.Parser;

import java.util.ArrayList;

public class Arrays {
    /* TODO:
    *   declaration   {TYPE}Array name = new {TYPE}Array{number}
    *   size
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
        Parser.intArrayStore.put(name, new ArrayList<Integer>(ret[1]));
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
        Parser.decimalArrayStore.put(name, new ArrayList<Double>(ret[1]));
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
        Parser.stringArrayStore.put(name, new ArrayList<String>(ret[1]));
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
        Parser.boolArrayStore.put(name, new ArrayList<Boolean>(ret[1]));
        return index;
    }
}
