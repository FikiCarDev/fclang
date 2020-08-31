package com.devfoFikiCar.parser;

import com.devfoFikiCar.Token;
import com.devfoFikiCar.error.Error;
import com.devfoFikiCar.parser.standard.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Parser {
    public static ArrayList<Token> tokens = new ArrayList<>();
    public static ArrayList<Integer> skip = new ArrayList<>();

    public static HashMap<String, Integer> intStore = new HashMap<>();
    public static HashMap<String, String> stringStore = new HashMap<>();
    public static HashMap<String, Double> decimalStore = new HashMap<>();
    public static HashMap<String, Boolean> boolStore = new HashMap<>();
    public static HashMap<Integer, Integer> skipStore = new HashMap<>();
    public static HashMap<String, Integer> gotoStore = new HashMap<>();

    public static int parse(ArrayList<Token> old, int begin, int end) {
        tokens = old;
        int indexR = 0;
        Goto.safeGoto();
        for (int index = begin; index < end; index++) {
            if (skipStore.containsKey(index)) {
                index = skipStore.get(index);
                continue;
            }
            if (skip.contains(index)) continue;
            String key = tokens.get(index).key;
            switch (key) {
                case "PRINT": {
                    int result = Print.printFunction(index);
                    if (result == 0) Error.FatalError(1);
                    else index = result;
                    break;
                }
                case "INT_T": {
                    int result = Declaration.declareInt(index);
                    if (result == 0) Error.FatalError(2);
                    else index = result;
                    break;
                }
                case "STRING_T": {
                    int result = Declaration.declareString(index);
                    if (result == 0) Error.FatalError(3);
                    else index = result;
                    break;
                }
                case "DECIMAL_T": {
                    int result = Declaration.declareDecimal(index);
                    if (result == 0) Error.FatalError(4);
                    else index = result;
                    break;
                }
                case "BOOL_T": {
                    int result = Declaration.declareBool(index);
                    if (result == 0) Error.FatalError(6);
                    else index = result;
                    break;
                }
                case "IF": {
                    int[] ret_v = IfStatement.ifStatement(index);
                    if (ret_v[0] == 0) Error.FatalError(7);
                    else {
                        if (ret_v[2] == 0) {
                            skip.add(ret_v[1]);
                            if (ret_v[5] != 0) {
                                skipStore.put(ret_v[1], ret_v[5]);
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
                    int result = ForLoop.forLoop(index);
                    if (result == 0) Error.FatalError(9);
                    else index = result;
                    break;
                }
                case "GOTO": {
                    int result = Goto.gotoFunction(index);
                    if (result == -1) Error.FatalError(10);
                    else index = result;
                    break;
                }
            }
            indexR = index;
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
        return indexR;

    }
}
