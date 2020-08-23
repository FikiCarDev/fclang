package com.devfoFikiCar.lexer;

import com.devfoFikiCar.Token;

import java.util.ArrayList;

public class Lexer {
    public static ArrayList<Token> lexer(String fileContents) {
        ArrayList<Token> tokens = new ArrayList<>();
        char[] data = fileContents.toCharArray();
        String temp = "";
        boolean inString = false;

        for (int i = 0; i < data.length; i++) {
            if (data[i] == '"') {
                inString = !inString;
            }
            if ((data[i] != ' ') || (data[i] == ' ' && inString)) {
                temp += data[i];
            } else {
                boolean skip = false;
                switch (temp) {
                    case "print": {
                        Token token = new Token("PRINT", temp);
                        tokens.add(token);
                        skip = true;
                        break;
                    }
                    case "int": {
                        Token token = new Token("INT_T", temp);
                        tokens.add(token);
                        skip = true;
                        break;
                    }
                    case "decimal": {
                        Token token = new Token("DECIMAL_T", temp);
                        tokens.add(token);
                        skip = true;
                        break;
                    }
                    case "string": {
                        Token token = new Token("STRING_T", temp);
                        tokens.add(token);
                        skip = true;
                        break;
                    }
                    case "=": {
                        Token token = new Token("EQUALS", temp);
                        tokens.add(token);
                        skip = true;
                        break;
                    }
                    case "+": {
                        Token token = new Token("ADDITION", temp);
                        tokens.add(token);
                        skip = true;
                        break;
                    }
                    case "-": {
                        Token token = new Token("SUBTRACTION", temp);
                        tokens.add(token);
                        skip = true;
                        break;
                    }
                    case "*": {
                        Token token = new Token("MULTIPLICATION", temp);
                        tokens.add(token);
                        skip = true;
                        break;
                    }
                    case "/": {
                        Token token = new Token("DIVISION", temp);
                        tokens.add(token);
                        skip = true;
                        break;
                    }
                    case "(": {
                        Token token = new Token("L_PARENTHESES", temp);
                        tokens.add(token);
                        skip = true;
                        break;
                    }
                    case ")": {
                        Token token = new Token("R_PARENTHESES", temp);
                        tokens.add(token);
                        skip = true;
                        break;
                    }
                    case "bool": {
                        Token token = new Token("BOOL_T", temp);
                        tokens.add(token);
                        skip = true;
                        break;
                    }
                    case "if": {
                        Token token = new Token("IF", temp);
                        tokens.add(token);
                        skip = true;
                        break;
                    }
                    case "{": {
                        Token token = new Token("L_BRACES", temp);
                        tokens.add(token);
                        skip = true;
                        break;
                    }
                    case "}": {
                        Token token = new Token("R_BRACES", temp);
                        tokens.add(token);
                        skip = true;
                        break;
                    }
                    case "&&": {
                        Token token = new Token("AND", temp);
                        tokens.add(token);
                        skip = true;
                        break;
                    }
                    case "||": {
                        Token token = new Token("OR", temp);
                        tokens.add(token);
                        skip = true;
                        break;
                    }
                    case "==": {
                        Token token = new Token("EQUAL_TO", temp);
                        tokens.add(token);
                        skip = true;
                        break;
                    }
                    case "!=": {
                        Token token = new Token("NOT_EQUAL", temp);
                        tokens.add(token);
                        skip = true;
                        break;
                    }
                    case ">=": {
                        Token token = new Token("GREATER_EQUAL", temp);
                        tokens.add(token);
                        skip = true;
                        break;
                    }
                    case "<=": {
                        Token token = new Token("LESS_EQUAL", temp);
                        tokens.add(token);
                        skip = true;
                        break;
                    }
                    case ">": {
                        Token token = new Token("GREATER_THAN", temp);
                        tokens.add(token);
                        skip = true;
                        break;
                    }
                    case "<": {
                        Token token = new Token("LESS_THAN", temp);
                        tokens.add(token);
                        skip = true;
                        break;
                    }
                    case "!": {
                        Token token = new Token("NOT", temp);
                        tokens.add(token);
                        skip = true;
                        break;
                    }
                    case "else": {
                        Token token = new Token("ELSE", temp);
                        tokens.add(token);
                        skip = true;
                        break;
                    }
                    case "for": {
                        Token token = new Token("FOR", temp);
                        tokens.add(token);
                        skip = true;
                        break;
                    }
                    case "|": {
                        Token token = new Token("SPLIT", temp);
                        tokens.add(token);
                        skip = true;
                        break;
                    }
                    case "goto": {
                        Token token = new Token("GOTO", temp);
                        tokens.add(token);
                        skip = true;
                        break;
                    }
                }
                if (temp.matches("(\".*\")") && !skip) {
                    Token token = new Token("STRING", temp);
                    tokens.add(token);
                } else if (temp.equals("true") || temp.equals("false")) {
                    Token token = new Token("BOOL", temp);
                    tokens.add(token);
                } else if (temp.matches("\\d+") && !skip) {
                    Token token = new Token("INT", temp);
                    tokens.add(token);
                } else if (temp.matches("\\d+(\\.\\d{1,2})?") && !skip) {
                    Token token = new Token("DECIMAL", temp);
                    tokens.add(token);
                } else if (temp.matches("^L-.*") && !skip) {
                    Token token = new Token("L_GOTO", temp);
                    tokens.add(token);
                } else if (temp.matches(".*") && !skip && temp != "") {
                    Token token = new Token("NAME", temp);
                    tokens.add(token);
                }
                temp = "";
            }
        }

        return tokens;
    }
}
