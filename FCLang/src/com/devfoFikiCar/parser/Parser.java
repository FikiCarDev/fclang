package com.devfoFikiCar.parser;

import com.devfoFikiCar.Token;
import com.devfoFikiCar.error.Error;

import java.util.ArrayList;
import java.util.HashMap;

public class Parser {
    private static ArrayList<Token> tokens = new ArrayList<>();
    private static HashMap<String, Integer> int_store = new HashMap<>();
    private static HashMap<String, String> string_store = new HashMap<>();
    private static HashMap<String, Double> decimal_store = new HashMap<>();

    public static void parse(ArrayList<Token> old){
        tokens = old;
        for(int index = 0; index < tokens.size(); index++){
            String key = tokens.get(index).key;
            switch (key){
                case "PRINT": {
                    int result = fprint(index);
                    if (result == 0) Error.FatalError(1);
                    else index = result;
                    break;
                }
                case "INT_T":{
                    int result = int_d(index);
                    if(result == 0) Error.FatalError(2);
                    else index = result;
                    break;
                }
                case "STRING_T":{
                    int result = string_d(index);
                    if(result == 0) Error.FatalError(3);
                    else index = result;
                    break;
                }
                case "DECIMAL_T":{
                    int result = decimal_d(index);
                    if(result == 0) Error.FatalError(4);
                    else index = result;
                    break;
                }
            }
        }
        // FOR DEBUGGING
        System.out.println("======================================");
        System.out.println("Value store check:");
        System.out.println("======================================");
        int_store.entrySet().forEach(entry->{
            System.out.println(entry.getKey() + " " + entry.getValue());
        });
        /*string_store.entrySet().forEach(entry->{
            System.out.println(entry.getKey() + " " + entry.getValue());
        });
        decimal_store.entrySet().forEach(entry->{
            System.out.println(entry.getKey() + " " + entry.getValue());
        });*/
    }

    // print: 'PRINT' STRING | INT | DECIMAL | NAME
    private static int fprint(int index){
        int[] ret_v = expression(index + 1, 0);
        if(ret_v[0] != 0){
            System.out.println(ret_v[1]);
            return --ret_v[0];
        }else if(tokens.get(index + 1).key == "STRING" || tokens.get(index + 1).key == "INT" || tokens.get(index + 1).key == "DECIMAL"){
            String toPrint = tokens.get(index + 1).value;
            if(tokens.get(index + 1).key == "STRING")
                toPrint = toPrint.subSequence(1, toPrint.length() - 1).toString();
            System.out.println(toPrint);
            return index + 1;
        } else if(tokens.get(index + 1).key == "NAME"){
            if(int_store.containsKey(tokens.get(index + 1).value) || string_store.containsKey(tokens.get(index + 1).value) || decimal_store.containsKey(tokens.get(index + 1).value)){
                int store = 0;
                if(int_store.containsKey(tokens.get(index + 1).value)) store = 1;
                else if(string_store.containsKey(tokens.get(index + 1).value)) store = 2;
                else store = 3;
                switch (store){
                    case 1:{
                        System.out.println(int_store.get(tokens.get(index + 1).value));
                        break;
                    }
                    case 2:{
                        String value = string_store.get(tokens.get(index + 1).value);
                        value = value.subSequence(1, value.length() - 1).toString();
                        System.out.println(value);
                        break;
                    }
                    case 3:{
                        System.out.println(decimal_store.get(tokens.get(index + 1).value));
                        break;
                    }
                }
                return index + 1;
            } else return 0;
        } else return 0;
    }

    // int declaration
    private static int int_d(int index){
        if(tokens.get(index + 1).key == "NAME"){
            if(tokens.get(index + 2).key == "EQUALS") {
                int[] ret_v = expression(index + 3, 0);
                if(ret_v[0] != 0){
                    int_store.put(tokens.get(index + 1).value, ret_v[1]);
                    index = ret_v[0];
                    return --index;
                } else {
                    index = declare_v(index + 3, tokens.get(index).key, tokens.get(index + 1).value);
                    return index;
                }
            } else return 0;
        } return 0;
    }

    // string declaration
    private static int string_d(int index){
        if(tokens.get(index + 1).key == "NAME"){
            if(tokens.get(index + 2).key == "EQUALS") {
                index = declare_v(index + 3, tokens.get(index).key, tokens.get(index + 1).value);
                return index;
            } else return 0;
        } else return 0;
    }

    // decimal declaration
    private static int decimal_d(int index){
        if(tokens.get(index + 1).key == "NAME"){
            if(tokens.get(index + 2).key == "EQUALS") {
                index = declare_v(index + 3, tokens.get(index).key, tokens.get(index + 1).value);
                return index;
            } else return 0;
        } else return 0;
    }

    // declares
    private static int declare_v(int index, String type, String name){
        String value_t = tokens.get(index).key + "_T";
        if(type.equals(value_t)){
            switch (type){
                case "INT_T":{
                    int_store.put(name, Integer.valueOf(tokens.get(index).value));
                    break;
                }
                case "STRING_T":{
                    string_store.put(name, String.valueOf(tokens.get(index).value));
                    break;
                }
                case "DECIMAL_T":{
                    decimal_store.put(name, Double.valueOf(tokens.get(index).value));
                    break;
                }
            }
            return index;
        } return 0;
    }

    /*
    *   return int[] pos 0 = index pos 2 = value
    *
    * */

    // expression: term (('-' | '+') term)?
    private static int[] expression(int index, int value){
        int[] ret = {index, value};
        if(term(index, value)[0] != 0){
            int[] ret_v = term(index, value);
            index  = ret_v[0];
            value = ret_v[1];
            if(index < tokens.size() && (tokens.get(index).key == "ADDITION" || tokens.get(index).key == "SUBTRACTION")){
                ret_v = expression(index + 1, value);
                switch (tokens.get(index).key){
                    case "SUBTRACTION":{
                        value -= ret_v[1];
                        break;
                    }
                    case "ADDITION":{
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
    private static int[] term(int index, int value){
        int[] ret = {index, value};
        if(factor(index, value)[0] != 0){
            int[] ret_v = factor(index, value);
            index = ret_v[0];
            value = ret_v[1];
            if(index < tokens.size() && (tokens.get(index).key == "MULTIPLICATION" || tokens.get(index).key == "DIVISION")){
                ret_v = expression(index + 1, value);
                switch (tokens.get(index).key){
                    case "DIVISION":{
                        value /= ret_v[1];
                        break;
                    }
                    case "MULTIPLICATION":{
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
    private static int[] factor(int index, int value){
        int[] ret = {index, value};
        if(tokens.get(index).key == "INT"){
            ret[0] = index + 1;
            ret[1] = Integer.parseInt(tokens.get(index).value);
            return ret;
        } else if(tokens.get(index).key == "L_PARENTHESES"){
            int[] ret_v = expression(index + 1, ret[1]);
            index = ret_v[0];
            value = ret_v[1];
            if(index == 0){
                ret[0] = 0;
                return ret;
            } else {
                if(index < tokens.size() && tokens.get(index).key == "R_PARENTHESES"){
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
