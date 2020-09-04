package com.devfoFikiCar.parser.IO;

import com.devfoFikiCar.parser.Parser;

import java.util.Scanner;

public class GetInput {
    static final Scanner scanner = new Scanner(System.in);

    // Cannot write test for this since JUnit4 does not support input
    // To test use main method
    
    public static int getInputInt(int index) {
        if (index + 1 < Parser.tokens.size() && Parser.tokens.get(index).key == "GET_INT" && Parser.tokens.get(index + 1).key == "LESS_THAN") {
            int input = scanner.nextInt();
            Parser.intStore.put(Parser.tokens.get(index - 2).value, input);
            return ++index;
        }
        return 0;
    }

    public static int getInputDecimal(int index) {
        if (index + 1 < Parser.tokens.size() && Parser.tokens.get(index).key == "GET_DECIMAL" && Parser.tokens.get(index + 1).key == "LESS_THAN") {
            double input = scanner.nextDouble();
            Parser.decimalStore.put(Parser.tokens.get(index - 2).value, input);
            return ++index;
        }
        return 0;
    }

    public static int getInputString(int index) {
        if (index + 1 < Parser.tokens.size() && Parser.tokens.get(index).key == "GET_STRING" && Parser.tokens.get(index + 1).key == "LESS_THAN") {
            String input = scanner.nextLine();
            String ret = "\"" + input + "\"";
            Parser.stringStore.put(Parser.tokens.get(index - 2).value, ret);
            return ++index;
        }
        return 0;
    }

    public static int getInputBool(int index) {
        if (index + 1 < Parser.tokens.size() && Parser.tokens.get(index).key == "GET_BOOL" && Parser.tokens.get(index + 1).key == "LESS_THAN") {
            boolean input = scanner.nextBoolean();
            Parser.boolStore.put(Parser.tokens.get(index - 2).value, input);
            return ++index;
        }
        return 0;
    }
}
