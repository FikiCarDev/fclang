package com.devfoFikiCar;

public class Token {
    public String key;
    public String value;
    public int posInLine = -1;
    public int lineNumber = -1;

    /*
     *   Possible token keys:
     *   PRINT
     *   STRING
     *   INT
     *   DECIMAL
     *   BOOL
     *   BOOL_T          |
     *   STRING_T        |
     *   INT_T           - TYPES
     *   DECIMAL_T       |
     *   EQUALS          - =
     *   ADDITION
     *   SUBTRACTION
     *   MULTIPLICATION
     *   DIVISION
     *   L_PARENTHESES   (
     *   R_PARENTHESES   )
     *   IF
     *   L_BRACES        {
     *   R_BRACES        }
     *   EQUAL_TO        ==
     *   NOT_EQUAL       !=
     *   GREATER_EQUAL   >=
     *   LESS_EQUAL      <=
     *   LESS_THAN       <
     *   GREATER_THAN    >
     *   AND             &&
     *   OR              ||
     *   NOT             !
     *   ELSE            else
     *   FOR             for
     *   SPLIT           |
     *   GOTO
     *   L_GOTO          L-sth
     *   GET_INT         getInt
     *   GET_STRING
     *   GET_DECIMAL
     *   GET_BOOL
     *   {TYPE}_ARRAY    {Type}Array
     *   NEW             new
     *   DOT             .
     *   SIZE            size
     *   SORT            sort
     * */

    public Token(String Key, String Value) {
        key = Key;
        value = Value;
    }

    public Token(String Key, String Value, int LineNumber) {
        key = Key;
        value = Value;
        lineNumber = LineNumber;
    }

    @Override
    public String toString() {
        return "Token{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", posInLine=" + posInLine +
                ", lineNumber=" + lineNumber +
                '}';
    }
}
