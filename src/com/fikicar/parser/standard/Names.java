package com.fikicar.parser.standard;

public class Names {

    /**
     * redeclareNames detects the correct datatype and reassigns value
     *
     * @param index begin position for parsing
     * @return index to continue parsing from
     */
    public static int redeclareNames(int index) {
        --index;
        int intReturn = Declaration.declareInt(index);
        if (intReturn != 0) return intReturn;

        int decimalReturn = Declaration.declareDecimal(index);
        if (decimalReturn != 0) return decimalReturn;

        int boolReturn = Declaration.declareBool(index);
        if (boolReturn != 0) return boolReturn;

        int stringReturn = Declaration.declareString(index);
        if (stringReturn != 0) return stringReturn;

        ++index;
        int arraySortReturn = Arrays.arraySort(index);
        if (arraySortReturn != 0) return arraySortReturn;

        int setIntArray = Arrays.setArrayValueInt(index);
        if (setIntArray != 0) return setIntArray;

        int setDecimalArray = Arrays.setArrayValueDecimal(index);
        if (setDecimalArray != 0) return setDecimalArray;

        int setStringArray = Arrays.setArrayValueString(index);
        if (setStringArray != 0) return setStringArray;

        int setBoolArray = Arrays.setArrayValueBool(index);
        if (setBoolArray != 0) return setBoolArray;

        int setIntMatrix = Matrixes.setMatrixValueInt(index);
        if (setIntMatrix != 0) return setIntMatrix;

        int setDecimalMatrix = Matrixes.setMatrixValueDecimal(index);
        if (setDecimalMatrix != 0) return setDecimalMatrix;

        int setStringMatrix = Matrixes.setMatrixValueString(index);
        if (setStringMatrix != 0) return setStringMatrix;

        int setBoolMatrix = Matrixes.setMatrixValueBool(index);
        return setBoolMatrix;
    }
}
