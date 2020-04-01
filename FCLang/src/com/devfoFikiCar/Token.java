package com.devfoFikiCar;

public class Token {
    public String key;
    public String value;

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
    * */

    public Token(String Key, String Value){
        key = Key;
        value = Value;
    }
}
