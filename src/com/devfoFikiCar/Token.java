package com.devfoFikiCar;

import java.awt.*;

public class Token {
    public String key;
    public String value;
    public Color colorLight;
    public Color colorDark;
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
     *   SET             set
     *   COMMA           ,
     *   GET             get
     *   ABS
     *   POW
     *   SQRT
     *   MIN
     *   MAX
     *   COMMENT
     *   ROW_SIZE
     *   COLUMN_SIZE
     * */

    /*
    *   COLORS:
    *   LIGHT:
    *   VariableNames           056 058 066
    *   keywords                166 038 164
    *   strings                 080 161 079
    *   bool int and decimals   152 104 001
    *   methods                 064 120 242
    *   signs                   001 132 188
    *   (){}[]                  056 058 066
    *   comments                204 223 050
    *   back                    250 250 250
    *
    *   DARK:
    *   VariableNames           121 171 255
    *   keywords                255 255 255
    *   strings                 255 198 000
    *   bool int and decimals   127 179 071
    *   methods                 190 214 255
    *   signs                   216 216 216
    *   comments                204 223 050
    *   back                    030 030 030
    * */

    public static final Color lightVariableNames = new Color(56, 58, 66);
    public static final Color lightKeywords = new Color(166,38,164);
    public static final Color lightStrings = new Color(80, 161,79);
    public static final Color lightBID = new Color(152, 104,1);
    public static final Color lightMethods = new Color(64, 120,242);
    public static final Color lightSigns = new Color(1, 132,188);
    public static final Color lightBP = new Color(56, 58,66);
    public static final Color lightComments = new Color(204, 223,50);
    public static final Color lightBackground = new Color(250, 250,250);

    public static final Color darkVariableNames = new Color(121, 171, 255);
    public static final Color darkKeywords = new Color(255,255,255);
    public static final Color darkStrings = new Color(255, 198,0);
    public static final Color darkBID = new Color(127, 179,71);
    public static final Color darkMethods = new Color(190, 214,255);
    public static final Color darkSigns = new Color(216, 216,216);
    public static final Color darkComments = new Color(204, 223,50);
    public static final Color darkBackground = new Color(30, 30,30);



    public Token(String Key, String Value) {
        key = Key;
        value = Value;
    }

    public Token(String Key, String Value, int LineNumber) {
        key = Key;
        value = Value;
        lineNumber = LineNumber;
    }

    public Token(String key, String value, Color colorLight, Color colorDark, int lineNumber) {
        this.key = key;
        this.value = value;
        this.colorLight = colorLight;
        this.colorDark = colorDark;
        this.lineNumber = lineNumber;
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
