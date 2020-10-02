package com.devfoFikiCar.parser.standard;

import com.devfoFikiCar.parser.Parser;
import com.devfoFikiCar.parser.math.*;
import javafx.util.Pair;

public class Decimals {

    /**
     * expressionDecimals validates and calculates decimal expressions.
     *
     * @param index begin position for parsing
     * @param value beginning value for operations to start on
     * @return index to continue parsing from and value of expression
     * @grammar expression: term (('-' | '+') term)?
     */
    public static double[] expressionDecimal(double index, double value) {
        double[] ret = {index, value};
        if (termDecimal(index, value)[0] != 0) {
            double[] retV = termDecimal(index, value);
            index = retV[0];
            value = retV[1];
            if (index < Parser.tokens.size() && (Parser.tokens.get((int) index).key == "ADDITION" || Parser.tokens.get((int) index).key == "SUBTRACTION")) {
                retV = expressionDecimal(index + 1, value);
                switch (Parser.tokens.get((int) index).key) {
                    case "SUBTRACTION": {
                        value -= retV[1];
                        break;
                    }
                    case "ADDITION": {
                        value += retV[1];
                        break;
                    }
                }
                index = retV[0];
            }
            ret[0] = index;
            ret[1] = value;
            return ret;
        } else {
            ret[0] = 0;
            return ret;
        }
    }

    /**
     * termDecimal divides and multiplies decimals.
     *
     * @param index begin position for parsing
     * @param value beginning value for operations to start on
     * @return index to continue parsing from and value of expression
     * @grammar term: factor (('/' | '*') factor)?
     */
    private static double[] termDecimal(double index, double value) {
        double[] ret = {index, value};
        if (factorDecimal(index, value)[0] != 0) {
            double[] retV = factorDecimal(index, value);
            index = retV[0];
            value = retV[1];
            if (index < Parser.tokens.size() && (Parser.tokens.get((int) index).key == "MULTIPLICATION" || Parser.tokens.get((int) index).key == "DIVISION")) {
                retV = expressionDecimal(index + 1, value);
                switch (Parser.tokens.get((int) index).key) {
                    case "DIVISION": {
                        value /= retV[1];
                        break;
                    }
                    case "MULTIPLICATION": {
                        value *= retV[1];
                        break;
                    }
                }
                index = retV[0];
            }
            ret[0] = index;
            ret[1] = value;
            return ret;
        } else {
            ret[0] = 0;
            return ret;
        }
    }

    /**
     * factorDecimal checks for int and base of decimal expressions.
     *
     * @param index begin position for parsing
     * @param value beginning value for operations to start on
     * @return index to continue parsing from and value of expression
     * @grammar factor: NUMBER | '(' expression ')'
     */
    private static double[] factorDecimal(double index, double value) {
        double[] ret = {index, value};
        if (Parser.tokens.get((int) index).key == "DECIMAL" || Parser.tokens.get((int) index).key == "NAME") {
            if (Parser.decimalStore.containsKey(Parser.tokens.get((int) index).value)) {
                ret[1] = Parser.decimalStore.get(Parser.tokens.get((int) index).value);
            } else if (index + 5 < Parser.tokens.size() && (int) Arrays.getArrayValue((int) index, 2).getKey() != 0) {
                Pair<Integer, Double> retPair = Arrays.getArrayValue((int) index, 2);
                index = retPair.getKey();
                ret[1] = retPair.getValue();
            } else if (index + 7 < Parser.tokens.size() && (int) Matrixes.getMatrixValue((int) index, 2).getKey() != 0) {
                Pair<Integer, Double> retPair = Matrixes.getMatrixValue((int) index, 2);
                index = retPair.getKey();
                ret[1] = retPair.getValue();
            } else {
                try {
                    ret[1] = Double.parseDouble(Parser.tokens.get((int) index).value);
                } catch (Exception e) {
                }
            }
            ret[0] = index + 1;
            return ret;
        } else if (Parser.tokens.get((int) index).key.equals("MAX")) {
            ret = Max.mathMaxDecimal((int) index);
            return ret;
        } else if (Parser.tokens.get((int) index).key.equals("MIN")) {
            ret = Min.mathMinDecimal((int) index);
            return ret;
        } else if (Parser.tokens.get((int) index).key.equals("POW")) {
            ret = Pow.mathPowDecimal((int) index);
            return ret;
        } else if (Parser.tokens.get((int) index).key.equals("SQRT")) {
            ret = Sqrt.mathSqrtDecimal((int) index);
            return ret;
        } else if (Parser.tokens.get((int) index).key.equals("ABS")) {
            ret = Abs.mathAbsDecimal((int) index);
            return ret;
        } else if (Parser.tokens.get((int) index).key == "L_PARENTHESES") {
            double[] retV = expressionDecimal(index + 1, ret[1]);
            index = retV[0];
            value = retV[1];
            if (index == 0) {
                ret[0] = 0;
                return ret;
            } else {
                if (index < Parser.tokens.size() && Parser.tokens.get((int) index).key == "R_PARENTHESES") {
                    ret[0] = index + 1;
                    ret[1] = value;
                    return ret;
                } else {
                    ret[0] = 0;
                    return ret;
                }
            }
        } else {
            ret[0] = 0;
            return ret;
        }
    }
}
