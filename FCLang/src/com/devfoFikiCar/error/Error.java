package com.devfoFikiCar.error;

import com.devfoFikiCar.main;
import com.devfoFikiCar.parser.Parser;

public class Error {
    public static void FatalError(int code, int index) {
        switch (code) {
            case 1: {
                int line = Parser.tokens.get(index).lineNumber;
                int lineSize = main.code.get(line).length();
                int middle = lineSize / 2;
                System.out.println(main.code.get(line));
                for (int i = 0; i < middle; i++) {
                    System.out.print("-");
                }
                System.out.print("^");
                for (int i = middle + 1; i < lineSize; i++) {
                    System.out.print("-");
                }
                System.out.println();
                System.out.println("After print method you need to use a string, int, decimal or valid variable name.");
                System.exit(0);
            }
            case 2: {
                int line = Parser.tokens.get(index).lineNumber;
                int lineSize = main.code.get(line).length();
                int middle = lineSize / 2;
                System.out.println(main.code.get(line));
                for (int i = 0; i < middle; i++) {
                    System.out.print("-");
                }
                System.out.print("^");
                for (int i = middle + 1; i < lineSize; i++) {
                    System.out.print("-");
                }
                System.out.println();
                System.out.println("Wrong int declaration.");
                System.exit(0);
            }
            case 3: {
                int line = Parser.tokens.get(index).lineNumber;
                int lineSize = main.code.get(line).length();
                int middle = lineSize / 2;
                System.out.println(main.code.get(line));
                for(int i = 0; i < middle; i++){
                    System.out.print("-");
                }
                System.out.print("^");
                for(int i = middle + 1; i < lineSize; i++){
                    System.out.print("-");
                }
                System.out.println();
                System.out.println("Wrong string declaration.");
                System.exit(0);
            }
            case 4: {
                int line = Parser.tokens.get(index).lineNumber;
                int lineSize = main.code.get(line).length();
                int middle = lineSize / 2;
                System.out.println(main.code.get(line));
                for(int i = 0; i < middle; i++){
                    System.out.print("-");
                }
                System.out.print("^");
                for(int i = middle + 1; i < lineSize; i++){
                    System.out.print("-");
                }
                System.out.println();
                System.out.println("Wrong decimal declaration.");
                System.exit(0);
            }
            case 5: {
                int line = Parser.tokens.get(index).lineNumber;
                int lineSize = main.code.get(line).length();
                int middle = lineSize / 2;
                System.out.println(main.code.get(line));
                for(int i = 0; i < middle; i++){
                    System.out.print("-");
                }
                System.out.print("^");
                for(int i = middle + 1; i < lineSize; i++){
                    System.out.print("-");
                }
                System.out.println();
                System.out.println("Wrong expression.");
                System.exit(0);
            }
            case 6: {
                int line = Parser.tokens.get(index).lineNumber;
                int lineSize = main.code.get(line).length();
                int middle = lineSize / 2;
                System.out.println(main.code.get(line));
                for(int i = 0; i < middle; i++){
                    System.out.print("-");
                }
                System.out.print("^");
                for(int i = middle + 1; i < lineSize; i++){
                    System.out.print("-");
                }
                System.out.println();
                System.out.println("Wrong bool expression.");
                System.exit(0);
            }
            case 7: {
                int line = Parser.tokens.get(index).lineNumber;
                int lineSize = main.code.get(line).length();
                int middle = lineSize / 2;
                System.out.println(main.code.get(line));
                for(int i = 0; i < middle; i++){
                    System.out.print("-");
                }
                System.out.print("^");
                for(int i = middle + 1; i < lineSize; i++){
                    System.out.print("-");
                }
                System.out.println();
                System.out.println("Wrong if statement.");
                System.exit(0);
            }
            case 8: {
                int line = Parser.tokens.get(index).lineNumber;
                int lineSize = main.code.get(line).length();
                int middle = lineSize / 2;
                System.out.println(main.code.get(line));
                for(int i = 0; i < middle; i++){
                    System.out.print("-");
                }
                System.out.print("^");
                for(int i = middle + 1; i < lineSize; i++){
                    System.out.print("-");
                }
                System.out.println();
                System.out.println("Wrong logical expression.");
                System.exit(0);
            }
            case 9: {
                int line = Parser.tokens.get(index).lineNumber;
                int lineSize = main.code.get(line).length();
                int middle = lineSize / 2;
                System.out.println(main.code.get(line));
                for(int i = 0; i < middle; i++){
                    System.out.print("-");
                }
                System.out.print("^");
                for(int i = middle + 1; i < lineSize; i++){
                    System.out.print("-");
                }
                System.out.println();
                System.out.println("Wrong for loop.");
                System.exit(0);
            }
            case 10: {
                int line = Parser.tokens.get(index).lineNumber;
                int lineSize = main.code.get(line).length();
                int middle = lineSize / 2;
                System.out.println(main.code.get(line));
                for(int i = 0; i < middle; i++){
                    System.out.print("-");
                }
                System.out.print("^");
                for(int i = middle + 1; i < lineSize; i++){
                    System.out.print("-");
                }
                System.out.println();
                System.out.println("Wrong goto statement");
                System.exit(0);
            }
        }
    }
}
