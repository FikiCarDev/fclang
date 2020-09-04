package com.devfoFikiCar.parser.standard;

public class Names {

    /**
     * redeclareNames detects the correct datatype and reassigns value
     * @param index begin position for parsing
     * @return index to continue parsing from
     */
    public static int redeclareNames(int index){
        --index;
        int intReturn = Declaration.declareInt(index);
        int decimalReturn = Declaration.declareDecimal(index);
        int boolReturn = Declaration.declareBool(index);
        int stringReturn = Declaration.declareString(index);
        if(intReturn != 0) return intReturn;
        if(decimalReturn != 0) return decimalReturn;
        if(boolReturn != 0) return boolReturn;
        if(stringReturn != 0) return stringReturn;
        return 0;
    }
}
