package com.fikicar;

public class Token {

    public String key;
    public String value;
    public int posInLine = -1;
    public int lineNumber = -1;


    public Token(String Key, String Value) {
        key = Key;
        value = Value;
    }

    public Token(String key, String value, int lineNumber) {
        this.key = key;
        this.value = value;
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
