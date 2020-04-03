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
    *   EQUAL_TO        ==
    *   NOT_EQUAL       !=
    *   GREATER_EQUAL   >=
    *   LESS_EQUAL      <=
    *   LESS_THAN       <
    *   GREATER_THAN    >
    *   AND             &&
    *   OR              ||
    * */

    public Token(String Key, String Value){
        key = Key;
        value = Value;
    }
}
