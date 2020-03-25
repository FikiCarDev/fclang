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
    *   STRING_T        |
    *   INT_T           - TYPES
    *   DECIMAL_T       |
    *   EQUALS          - =
    * */

    public Token(String Key, String Value){
        key = Key;
        value = Value;
    }
}
