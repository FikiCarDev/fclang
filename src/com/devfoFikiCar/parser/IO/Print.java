package com.devfoFikiCar.parser.IO;

import com.devfoFikiCar.parser.Parser;
import com.devfoFikiCar.parser.standard.Arrays;
import com.devfoFikiCar.parser.standard.Bools;
import com.devfoFikiCar.parser.standard.Decimals;
import com.devfoFikiCar.parser.standard.Integers;

import java.lang.reflect.Array;

public class Print {
    /**
     * Print function.
     * @param index begin position for parsing
     * @return index to continue parsing from
     * @grammar print: 'PRINT' STRING | INT | DECIMAL | NAME (basePrint)?
     */
    public static int printFunction(int index) {
        if (Parser.tokens.size() > index + 1 && (Parser.tokens.get(index + 1).key == "STRING" || Parser.tokens.get(index + 1).key == "INT"
                || Parser.tokens.get(index + 1).key == "DECIMAL" || Parser.tokens.get(index + 1).key == "BOOL")) {
            switch (Parser.tokens.get(index + 1).key) {
                case "INT": {
                    int[] retInt = Integers.expressionInt(index + 1, 0);
                    System.out.println(retInt[1]);
                    return basePrint(--retInt[0]);
                }
                case "DECIMAL": {
                    double[] retDouble = Decimals.expressionDecimal(index * 1.0 + 1.0, 0.0);
                    System.out.println(retDouble[1]);
                    return basePrint((int) --retDouble[0]);
                }
                case "BOOL": {
                    int[] retBool = Bools.bool(index + 1);
                    System.out.println(retBool[1] == 1);
                    return basePrint(retBool[0]);
                }
                default: {
                    String toPrint = Parser.tokens.get(index + 1).value;
                    if (Parser.tokens.get(index + 1).key == "STRING")
                        toPrint = toPrint.subSequence(1, toPrint.length() - 1).toString();
                    System.out.println(toPrint);
                    break;
                }
            }
            return basePrint(index + 2); //1
        } else if (Parser.tokens.size() > index + 1 && Parser.tokens.get(index + 1).key == "NAME") {
            if (Parser.intStore.containsKey(Parser.tokens.get(index + 1).value) || Parser.stringStore.containsKey(Parser.tokens.get(index + 1).value)
                    || Parser.decimalStore.containsKey(Parser.tokens.get(index + 1).value) || Parser.boolStore.containsKey(Parser.tokens.get(index + 1).value)) {
                int store = 0;
                if (Parser.boolStore.containsKey(Parser.tokens.get(index + 1).value)) store = 1;
                else if (Parser.intStore.containsKey(Parser.tokens.get(index + 1).value)) store = 2;
                else if (Parser.decimalStore.containsKey(Parser.tokens.get(index + 1).value)) store = 3;
                else store = 4;
                switch (store) {
                    case 1: {
                        System.out.println(Parser.boolStore.get(Parser.tokens.get(index + 1).value));
                        break;
                    }
                    case 2: {
                        System.out.println(Parser.intStore.get(Parser.tokens.get(index + 1).value));
                        break;
                    }
                    case 3: {
                        System.out.println(Parser.decimalStore.get(Parser.tokens.get(index + 1).value));
                        break;
                    }
                    case 4: {
                        String value = Parser.stringStore.get(Parser.tokens.get(index + 1).value);
                        value = value.subSequence(1, value.length() - 1).toString();
                        System.out.println(value);
                        break;
                    }
                }
                return basePrint(index + 1); //2
            } else if(Arrays.arraySize(index + 1)[0] != 0){
                int[] ret = Arrays.arraySize(index + 1);
                System.out.println(ret[1]);
                return ret[0];
            } else return 0;
        } else if (Parser.tokens.size() > index + 1 && Parser.tokens.get(index + 1).key == "L_PARENTHESES") {
            index++;
            int tempIndex = 0;
            for (int i = index; i < Parser.tokens.size(); i++)
                if (Parser.tokens.get(i).key != "L_PARENTHESES") {
                    tempIndex = i;
                    break;
                }
            switch (Parser.tokens.get(tempIndex).key) {
                case "INT": {
                    int[] retInt = Integers.expressionInt(index, 0);
                    System.out.println(retInt[1]);
                    return basePrint(--retInt[0]);
                }
                case "DECIMAL": {
                    double[] retDouble = Decimals.expressionDecimal(index * 1.0, 0.0);
                    System.out.println(retDouble[1]);
                    return basePrint((int) --retDouble[0]);
                }
            }
            return 0;
        } else return 0;
    }

    /**
     * basePrint checks for extra one-line print.
     * @param index begin position for parsing
     * @return index to continue parsing from
     */
    public static int basePrint(int index){
        if(index + 1 < Parser.tokens.size() && Parser.tokens.get(index).key.equals("SPLIT")){
            int res = printFunction(index);
            return (res == 0) ? --index : res;
        } return --index;
    }
}
