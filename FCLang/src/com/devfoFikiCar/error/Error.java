package com.devfoFikiCar.error;

public class Error {
    public static void FatalError(int code) {
        switch (code) {
            case 1:
                System.out.println("After print method you need to use a string, int, decimal or valid variable name.");
                System.exit(0);
            case 2:
                System.out.println("Wrong int declaration.");
                System.exit(0);
            case 3:
                System.out.println("Wrong string declaration.");
                System.exit(0);
            case 4:
                System.out.println("Wrong decimal declaration.");
                System.exit(0);
            case 5:
                System.out.println("Wrong expression.");
                System.exit(0);
            case 6:
                System.out.println("Wrong bool declaration.");
                System.exit(0);
            case 7:
                System.out.println("Wrong if statement.");
                System.exit(0);
            case 8:
                System.out.println("Wrong logical expression.");
                System.exit(0);
        }
    }
}
