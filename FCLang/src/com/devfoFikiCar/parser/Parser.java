package com.devfoFikiCar.parser;

import com.devfoFikiCar.Token;
import com.devfoFikiCar.error.Error;

import java.util.ArrayList;
import java.util.HashMap;

public class Parser {
    public static ArrayList<Token> tokens = new ArrayList<>();
    public static HashMap<String, Integer> int_store = new HashMap<>();
    public static HashMap<String, String> string_store = new HashMap<>();
    public static HashMap<String, Double> decimal_store = new HashMap<>();

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
                default:{
                    int result = expression(index);
                    if(result == 0) Error.FatalError(5);
                    else index = result;
                    break;
                }
            }
        }
        // FOR DEBUGGING
        /*int_store.entrySet().forEach(entry->{
            System.out.println(entry.getKey() + " " + entry.getValue());
        });
        string_store.entrySet().forEach(entry->{
            System.out.println(entry.getKey() + " " + entry.getValue());
        });
        decimal_store.entrySet().forEach(entry->{
            System.out.println(entry.getKey() + " " + entry.getValue());
        });*/
    }

    // print: 'PRINT' STRING | INT | DECIMAL | NAME
    public static int fprint(int index){
        if(tokens.get(index + 1).key == "STRING" || tokens.get(index + 1).key == "INT" || tokens.get(index + 1).key == "DECIMAL"){
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
    public static int int_d(int index){
        if(tokens.get(index + 1).key == "NAME"){
            if(tokens.get(index + 2).key == "EQUALS") {
                index = declare_v(index + 3, tokens.get(index).key, tokens.get(index + 1).value);
                return index;
            } else return 0;
        } else return 0;
    }

    // string declaration
    public static int string_d(int index){
        if(tokens.get(index + 1).key == "NAME"){
            if(tokens.get(index + 2).key == "EQUALS") {
                index = declare_v(index + 3, tokens.get(index).key, tokens.get(index + 1).value);
                return index;
            } else return 0;
        } else return 0;
    }

    // decimal declaration
    public static int decimal_d(int index){
        if(tokens.get(index + 1).key == "NAME"){
            if(tokens.get(index + 2).key == "EQUALS") {
                index = declare_v(index + 3, tokens.get(index).key, tokens.get(index + 1).value);
                return index;
            } else return 0;
        } else return 0;
    }

    // declares
    public static int declare_v(int index, String type, String name){
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

    /*   TEST CASE:
     *
     *   2 + 2 + 1
     *   1 2 3 4 5
     *
     *   2 + ( 2 * 2 )
     *   1 2 3 4 5 6 7
     * */

    // expression: term (('-' | '+') term)?
    public static int expression(int index){
        if(term(index) != 0){
            index  = term(index);
            if(index < tokens.size() && (tokens.get(index).key == "ADDITION" || tokens.get(index).key == "SUBTRACTION")){
                index = expression(index + 1);
            }
            return index;
        } return 0;
    }

    // term: factor (('/' | '*') factor)?
    public static int term(int index){
        if(factor(index) != 0){
            index = factor(index);
            if(index < tokens.size() && (tokens.get(index).key == "MULTIPLICATION" || tokens.get(index).key == "DIVISION")){
                index = term(index + 1);
            }
            return index;
        } else return 0;
    }

    // factor: NUMBER | '(' expression ')'
    public static int factor(int index){
        if(tokens.get(index).key == "INT"){
            return index + 1;
        } else if(tokens.get(index).key == "L_PARENTHESES"){
            index = expression(index + 1);
            if(index == 0){
                return 0;
            } else {
                if(index < tokens.size() && tokens.get(index).key == "R_PARENTHESES"){
                    return index + 1;
                } else return 0;
            }
        } else return 0;
    }
}
