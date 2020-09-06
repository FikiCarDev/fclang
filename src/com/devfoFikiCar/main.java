package com.devfoFikiCar;

import com.devfoFikiCar.lexer.Lexer;
import com.devfoFikiCar.parser.Parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class main {

    public static ArrayList<Token> tokens = new ArrayList<>();
    public static ArrayList<String> code = new ArrayList<>();

    public static String data;

    /**
     * @param args filePath required, -dev with -m for minimal debug and -f for full
     */
    public static void main(String[] args) {
        switch (args.length) {
            case 0: {
                System.out.println("Enter the file path");
                break;
            }
            case 1: {
                setData(args[0]);
                setTokens();
                runParser();
                break;
            }
            case 2: {
                System.out.println("Invalid option " + args[0]);
                break;
            }
            case 3: {
                if (args[0].equals("-dev")) {
                    switch (args[1]) {
                        case "-m": {
                            setData(new File(args[2]).getAbsolutePath());
                            setTokens();
                            devM();
                            runParser();
                            break;
                        }
                        case "-f": {
                            setData(new File(args[2]).getAbsolutePath());
                            setTokens();
                            devF();
                            runParser();
                            break;
                        }
                    }
                }
                break;
            }
        }
        //setData("./scripts/tests/goto/gotoTest2.fclang");
        /*ArrayList<Token> tokens = Lexer.lexerMain();
        for(Token t : tokens){
            System.out.println(t.key + " " + t.value);
        }*/
        //setTokens();
        //runParser();
    }

    public static void runParser() {
        System.out.println("======================================");
        System.out.println("Beginning of FCLang execution: ");
        System.out.println("======================================");
        Parser.parse(tokens, 0, tokens.size());
        System.out.println("======================================");
        System.out.println("Successful execution.");
        System.out.println("======================================");
        clearLists();
    }

    public static void setData(String filePath) {
        main.data = readFile(filePath);
    }

    public static void setTokens() {
        main.tokens = Lexer.lexer();
    }

    public static void devM() {
        System.out.println("Size of tokens after lexing: " + tokens.size());
    }

    public static void devF() {
        System.out.println("======================================");
        System.out.println("Beginning of FCLang dev debug: ");
        System.out.println("======================================");
        System.out.println("Size of tokens after lexing: " + tokens.size());
        for (Token t : tokens) {
            System.out.println(t.toString());
        }
        System.out.println("======================================");
        System.out.println("Ending of FCLang dev debug: ");
        System.out.println("======================================");
    }

    /**
     * readFile safes code in string and ArrayList.
     *
     * @param fileName path relative to jar file
     * @return code backup
     */
    public static String readFile(String fileName) {
        String data = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
            String line = br.readLine();
            while (line != null) {
                code.add(line);
                line = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * Always clear memory.
     */
    public static void clearLists() {
        tokens.clear();
        code.clear();
        Parser.tokens.clear();
        Parser.skip.clear();
        Parser.intStore.clear();
        Parser.decimalStore.clear();
        Parser.stringStore.clear();
        Parser.boolStore.clear();
        Parser.gotoStore.clear();
        Parser.skipStore.clear();
        Parser.intArrayStore.clear();
        Parser.decimalStore.clear();
        Parser.stringArrayStore.clear();
        Parser.boolArrayStore.clear();
    }
}
