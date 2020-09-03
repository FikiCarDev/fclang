package com.devfoFikiCar.lexer;

import com.devfoFikiCar.Token;
import com.devfoFikiCar.main;

import java.util.ArrayList;

public class Lexer {

    public static ArrayList<Token> lexer(){
        ArrayList<Token> tokens = new ArrayList<>();
        for(int line = 0; line < main.code.size(); line++){
            String currentLine = main.code.get(line) + " ";
            String temp = "";
            boolean skip = false;
            for(int i = 0; i < currentLine.length(); i++){
                skip = false;
                switch (currentLine.charAt(i)){
                    case ' ': {
                        tokens.add(lexTemp(temp));
                        temp = "";
                        skip = true;
                        break;
                    }
                    case '=': {
                        if(currentLine.charAt(i + 1) == '='){
                            i++;
                            tokens.add(lexTemp(temp));
                            tokens.add(new Token("EQUAL_TO", "=="));
                            temp = "";
                            skip = true;
                        } else {
                            tokens.add(lexTemp(temp));
                            tokens.add(new Token("EQUALS", "="));
                            temp = "";
                            skip = true;
                        }
                        break;
                    }
                    case '+': {
                        tokens.add(lexTemp(temp));
                        tokens.add(new Token("ADDITION", "+"));
                        temp = "";
                        skip = true;
                        break;
                    }
                    case '-': {
                        if(currentLine.charAt(i - 1) != 'L') {
                            tokens.add(lexTemp(temp));
                            tokens.add(new Token("SUBTRACTION", "-"));
                            temp = "";
                            skip = true;
                        }
                        break;
                    }
                    case '*': {
                        tokens.add(lexTemp(temp));
                        tokens.add(new Token("MULTIPLICATION", "*"));
                        temp = "";
                        skip = true;
                        break;
                    }
                    case '/': {
                        tokens.add(lexTemp(temp));
                        tokens.add(new Token("DIVISION", "/"));
                        temp = "";
                        skip = true;
                        break;
                    }
                    case '(': {
                        tokens.add(lexTemp(temp));
                        tokens.add(new Token("L_PARENTHESES", "("));
                        temp = "";
                        skip = true;
                        break;
                    }
                    case ')': {
                        tokens.add(lexTemp(temp));
                        tokens.add(new Token("R_PARENTHESES", ")"));
                        temp = "";
                        skip = true;
                        break;
                    }
                    case '{': {
                        tokens.add(lexTemp(temp));
                        tokens.add(new Token("L_BRACES", "{"));
                        temp = "";
                        skip = true;
                        break;
                    }
                    case '}': {
                        tokens.add(lexTemp(temp));
                        tokens.add(new Token("R_BRACES", "}"));
                        temp = "";
                        skip = true;
                        break;
                    }
                    case '&': {
                        if(currentLine.charAt(i + 1) == '&'){
                            i++;
                            tokens.add(lexTemp(temp));
                            tokens.add(new Token("AND", "&&"));
                            temp = "";
                            skip = true;
                        }
                        break;
                    }
                    case '>': {
                        if(currentLine.charAt(i + 1) == '='){
                            i++;
                            tokens.add(lexTemp(temp));
                            tokens.add(new Token("GREATER_EQUAL", ">="));
                            temp = "";
                            skip = true;
                        } else {
                            tokens.add(lexTemp(temp));
                            tokens.add(new Token("GREATER_THAN", ">"));
                            temp = "";
                            skip = true;
                        }
                        break;
                    }
                    case '<': {
                        if(currentLine.charAt(i + 1) == '='){
                            i++;
                            tokens.add(lexTemp(temp));
                            tokens.add(new Token("LESS_EQUAL", "<="));
                            temp = "";
                            skip = true;
                        } else {
                            tokens.add(lexTemp(temp));
                            tokens.add(new Token("LESS_THAN", "<"));
                            temp = "";
                            skip = true;
                        }
                        break;
                    }
                    case '!': {
                        if(currentLine.charAt(i + 1) == '='){
                            i++;
                            tokens.add(lexTemp(temp));
                            tokens.add(new Token("NOT_EQUAL", "!="));
                            temp = "";
                            skip = true;
                        } else {
                            tokens.add(lexTemp(temp));
                            tokens.add(new Token("NOT", "!"));
                            temp = "";
                            skip = true;
                        }
                        break;
                    }
                    case '|': {
                        if(currentLine.charAt(i + 1) == '|'){
                            i++;
                            tokens.add(lexTemp(temp));
                            tokens.add(new Token("OR", "||"));
                            temp = "";
                            skip = true;
                        } else {
                            tokens.add(lexTemp(temp));
                            tokens.add(new Token("SPLIT", "|"));
                            temp = "";
                            skip = true;
                        }
                        break;
                    }
                    case '"':{
                        tokens.add(lexTemp(temp));
                        temp = "\"";
                        for(int j = i + 1; j < currentLine.length(); j++){
                            if(currentLine.charAt(j) == '"'){
                                tokens.add(new Token("STRING", temp + "\""));
                                i = j;
                            } else temp += currentLine.charAt(j);
                        }
                        temp = "";
                        skip = true;
                        break;
                    }
                }
                if(!skip)
                    temp += currentLine.charAt(i);
            }
        }
        tokens = checkForEmptyTokens(tokens);
        return tokens;
    }

    public static ArrayList<Token> checkForEmptyTokens(ArrayList<Token> tokens){
        for(int i = 0; i < tokens.size(); i++){
            if(tokens.get(i).key.equals("")){
                tokens.remove(i);
                i--;
            }
        }
        return tokens;
    }

    public static Token lexTemp(String temp){
        Token token = new Token("", "");
        if(temp.equals("print")){
            return new Token("PRINT", "print");
        } else if(temp.equals("int")){
            return new Token("INT_T", "int");
        } else if(temp.equals("decimal")){
            return new Token("DECIMAL_T", "decimal");
        }else if(temp.equals("string")){
            return new Token("STRING_T", "string");
        }else if(temp.equals("bool")){
            return new Token("BOOL_T", "bool");
        }else if(temp.equals("if")){
            return new Token("IF", "if");
        }else if(temp.equals("else")){
            return new Token("ELSE", "else");
        }else if(temp.equals("for")){
            return new Token("FOR", "for");
        }else if(temp.equals("goto")){
            return new Token("GOTO", "goto");
        }else if(temp.equals("getInt")){
            return new Token("GET_INT", "getInt");
        }else if(temp.equals("getDecimal")){
            return new Token("GET_DECIMAL", "getDecimal");
        }else if(temp.equals("getString")){
            return new Token("GET_STRING", "getString");
        }else if(temp.equals("getBool")){
            return new Token("GET_BOOL", "getBool");
        } else if (temp.matches("(\".*\")")) {
            return new Token("STRING", temp);
        } else if (temp.equals("true") || temp.equals("false")) {
            return new Token("BOOL", temp);
        } else if (temp.matches("\\d+")) {
            return new Token("INT", temp);
        } else if (temp.matches("\\d+(\\.\\d{1,2})?")) {
            return new Token("DECIMAL", temp);
        } else if (temp.matches("^L-.*")) {
            return new Token("L_GOTO", temp);
        } else if (temp.matches(".*") &&temp != "") {
            return new Token("NAME", temp);
        }
        return token;
    }
}
