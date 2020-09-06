package com.devfoFikiCar.lexer;

import com.devfoFikiCar.Token;
import com.devfoFikiCar.main;

import java.util.ArrayList;

public class Lexer {
    public static int lineNumber = -1;
    private static int curSum = 0;
    private static int oldSum = 0;

    public static ArrayList<Token> lexer() {
        ArrayList<Token> tokens = new ArrayList<>();
        for (int line = 0; line < main.code.size(); line++) {
            lineNumber++;
            String currentLine = main.code.get(line) + " ";
            String temp = "";
            boolean skip = false;
            oldSum = tokens.size();
            for (int i = 0; i < currentLine.length(); i++) {
                curSum = tokens.size();
                skip = false;
                switch (currentLine.charAt(i)) {
                    case ' ': {
                        tokens.add(lexTemp(temp));
                        temp = "";
                        skip = true;
                        break;
                    }
                    case '=': {
                        if (currentLine.charAt(i + 1) == '=') {
                            i++;
                            tokens.add(lexTemp(temp));
                            tokens.add(new Token("EQUAL_TO", "==",  lineNumber));
                            temp = "";
                            skip = true;
                        } else {
                            tokens.add(lexTemp(temp));
                            tokens.add(new Token("EQUALS", "=",  lineNumber));
                            temp = "";
                            skip = true;
                        }
                        break;
                    }
                    case '+': {
                        tokens.add(lexTemp(temp));
                        tokens.add(new Token("ADDITION", "+",  lineNumber));
                        temp = "";
                        skip = true;
                        break;
                    }
                    case '-': {
                        if (currentLine.charAt(i - 1) != 'L') {
                            tokens.add(lexTemp(temp));
                            tokens.add(new Token("SUBTRACTION", "-",  lineNumber));
                            temp = "";
                            skip = true;
                        }
                        break;
                    }
                    case '*': {
                        tokens.add(lexTemp(temp));
                        tokens.add(new Token("MULTIPLICATION", "*",  lineNumber));
                        temp = "";
                        skip = true;
                        break;
                    }
                    case '/': {
                        tokens.add(lexTemp(temp));
                        tokens.add(new Token("DIVISION", "/",  lineNumber));
                        temp = "";
                        skip = true;
                        break;
                    }
                    case '.': {
                        if(!temp.matches("\\d+")) {
                            tokens.add(lexTemp(temp));
                            tokens.add(new Token("DOT", ".", lineNumber));
                            temp = "";
                            skip = true;
                        }
                        break;
                    }
                    case '(': {
                        tokens.add(lexTemp(temp));
                        tokens.add(new Token("L_PARENTHESES", "(",  lineNumber));
                        temp = "";
                        skip = true;
                        break;
                    }
                    case ')': {
                        tokens.add(lexTemp(temp));
                        tokens.add(new Token("R_PARENTHESES", ")",  lineNumber));
                        temp = "";
                        skip = true;
                        break;
                    }
                    case '{': {
                        tokens.add(lexTemp(temp));
                        tokens.add(new Token("L_BRACES", "{",  lineNumber));
                        temp = "";
                        skip = true;
                        break;
                    }
                    case '}': {
                        tokens.add(lexTemp(temp));
                        tokens.add(new Token("R_BRACES", "}",  lineNumber));
                        temp = "";
                        skip = true;
                        break;
                    }
                    case '&': {
                        if (currentLine.charAt(i + 1) == '&') {
                            i++;
                            tokens.add(lexTemp(temp));
                            tokens.add(new Token("AND", "&&",  lineNumber));
                            temp = "";
                            skip = true;
                        }
                        break;
                    }
                    case '>': {
                        if (currentLine.charAt(i + 1) == '=') {
                            i++;
                            tokens.add(lexTemp(temp));
                            tokens.add(new Token("GREATER_EQUAL", ">=",  lineNumber));
                            temp = "";
                            skip = true;
                        } else {
                            tokens.add(lexTemp(temp));
                            tokens.add(new Token("GREATER_THAN", ">",  lineNumber));
                            temp = "";
                            skip = true;
                        }
                        break;
                    }
                    case '<': {
                        if (currentLine.charAt(i + 1) == '=') {
                            i++;
                            tokens.add(lexTemp(temp));
                            tokens.add(new Token("LESS_EQUAL", "<=",  lineNumber));
                            temp = "";
                            skip = true;
                        } else {
                            tokens.add(lexTemp(temp));
                            tokens.add(new Token("LESS_THAN", "<",  lineNumber));
                            temp = "";
                            skip = true;
                        }
                        break;
                    }
                    case '!': {
                        if (currentLine.charAt(i + 1) == '=') {
                            i++;
                            tokens.add(lexTemp(temp));
                            tokens.add(new Token("NOT_EQUAL", "!=",  lineNumber));
                            temp = "";
                            skip = true;
                        } else {
                            tokens.add(lexTemp(temp));
                            tokens.add(new Token("NOT", "!",  lineNumber));
                            temp = "";
                            skip = true;
                        }
                        break;
                    }
                    case '|': {
                        if (currentLine.charAt(i + 1) == '|') {
                            i++;
                            tokens.add(lexTemp(temp));
                            tokens.add(new Token("OR", "||",  lineNumber));
                            temp = "";
                            skip = true;
                        } else {
                            tokens.add(lexTemp(temp));
                            tokens.add(new Token("SPLIT", "|",  lineNumber));
                            temp = "";
                            skip = true;
                        }
                        break;
                    }
                    case '"': {
                        tokens.add(lexTemp(temp));
                        temp = "\"";
                        for (int j = i + 1; j < currentLine.length(); j++) {
                            if (currentLine.charAt(j) == '"') {
                                tokens.add(new Token("STRING", temp + "\"",  lineNumber));
                                i = j;
                            } else temp += currentLine.charAt(j);
                        }
                        temp = "";
                        skip = true;
                        break;
                    }
                }
                if (!skip)
                    temp += currentLine.charAt(i);
            }
        }
        lineNumber = -1;
        tokens = checkForEmptyTokens(tokens);
        return tokens;
    }

    public static ArrayList<Token> checkForEmptyTokens(ArrayList<Token> tokens) {
        for (int i = 0; i < tokens.size(); i++) {
            if (tokens.get(i).key.equals("")) {
                tokens.remove(i);
                i--;
            }
        }
        return tokens;
    }

    public static Token lexTemp(String temp) {
        Token token = new Token("", "");
        if (temp.equals("print")) {
            return new Token("PRINT", "print",  lineNumber);
        } else if (temp.equals("int")) {
            return new Token("INT_T", "int",  lineNumber);
        } else if (temp.equals("decimal")) {
            return new Token("DECIMAL_T", "decimal",  lineNumber);
        } else if (temp.equals("string")) {
            return new Token("STRING_T", "string",  lineNumber);
        } else if (temp.equals("bool")) {
            return new Token("BOOL_T", "bool",  lineNumber);
        } else if (temp.equals("if")) {
            return new Token("IF", "if",  lineNumber);
        } else if (temp.equals("size")) {
            return new Token("SIZE", "SIZE",  lineNumber);
        } else if (temp.equals("else")) {
            return new Token("ELSE", "else",  lineNumber);
        } else if (temp.equals("for")) {
            return new Token("FOR", "for",  lineNumber);
        } else if (temp.equals("sort")) {
            return new Token("SORT", "sort",  lineNumber);
        } else if (temp.equals("goto")) {
            return new Token("GOTO", "goto",  lineNumber);
        } else if (temp.equals("getInt")) {
            return new Token("GET_INT", "getInt",  lineNumber);
        } else if (temp.equals("getDecimal")) {
            return new Token("GET_DECIMAL", "getDecimal",  lineNumber);
        } else if (temp.equals("IntArray")) {
            return new Token("INT_ARRAY", "IntArray", lineNumber);
        } else if (temp.equals("DecimalArray")) {
            return new Token("DECIMAL_ARRAY", "DecimalArray", lineNumber);
        } else if (temp.equals("StringArray")) {
            return new Token("STRING_ARRAY", "StringArray", lineNumber);
        } else if (temp.equals("BoolArray")) {
            return new Token("BOOL_ARRAY", "BoolArray", lineNumber);
        }  else if (temp.equals("new")) {
            return new Token("NEW", "new", lineNumber);
        } else if (temp.equals("getString")) {
            return new Token("GET_STRING", "getString",  lineNumber);
        } else if (temp.equals("getBool")) {
            return new Token("GET_BOOL", "getBool",  lineNumber);
        } else if (temp.matches("(\".*\")")) {
            return new Token("STRING", temp,  lineNumber);
        } else if (temp.equals("true") || temp.equals("false")) {
            return new Token("BOOL", temp,  lineNumber);
        } else if (temp.matches("\\d+")) {
            return new Token("INT", temp,  lineNumber);
        } else if (temp.matches("\\d+(\\.\\d{1,2})?")) {
            return new Token("DECIMAL", temp,  lineNumber);
        } else if (temp.matches("^L-.*")) {
            return new Token("L_GOTO", temp,  lineNumber);
        } else if (temp.matches(".*") && temp != "") {
            Token token1 = new Token("NAME", temp,  lineNumber);
            if(Math.abs(curSum - oldSum) == 0) token1.posInLine = 0;
            else token1.posInLine = 1;
            return token1;
        }
        return token;
    }
}
//1D_ARRAY       1DArray