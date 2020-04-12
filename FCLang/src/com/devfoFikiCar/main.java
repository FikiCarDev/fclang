package com.devfoFikiCar;

import com.devfoFikiCar.lexer.Lexer;
import com.devfoFikiCar.parser.Parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class main {
    public static void main(String[] args) {
        String data = readFile();
        System.out.println(data);

        ArrayList<Token> tokens = Lexer.lexer(data);
        // FOR DEBUGGING
        /*int i = 0;
        for (Token t : tokens) {
            System.out.println(i + ". Token(" + t.key + ", " + t.value + ")");
            i++;
        }*/

        System.out.println("======================================");
        System.out.println("Beginning of FCLang execution: ");
        System.out.println("======================================");
        Parser.parse(tokens);
        System.out.println("======================================");
        System.out.println("Successful execution.");
        System.out.println("======================================");
    }

    public static String readFile() {
        String data = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader("./scripts/demo.fclang"));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(" ");
                line = br.readLine();
            }

            data = sb.toString();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
