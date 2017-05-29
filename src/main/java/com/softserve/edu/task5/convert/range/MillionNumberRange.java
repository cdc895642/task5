package com.softserve.edu.task5.convert.range;

import com.softserve.edu.task5.convert.Type;

import java.math.BigInteger;
import java.util.ResourceBundle;

/**
 * give capability to process integer from 1000 000 to 999 999 999
 * Created by cdc89 on 24.05.2017.
 */
public class MillionNumberRange extends AbstractNumberRange {
    private static MillionNumberRange instance = null;

    /**
     * singleton
     * @return object of this class
     */
    public static MillionNumberRange getInstance() {
        if (instance == null) {
            instance = new MillionNumberRange();
        }
        return instance;
    }

    private MillionNumberRange() {
        maxNumIndex = 9;
        minNumIndex = 7;
        divider = BigInteger.valueOf(1000000);
        numberType = Type.MALE;
        minValue = BigInteger.valueOf(1000000);
        maxValue = BigInteger.valueOf(999999999);
    }

    @Override
    public String getNumberDegree(BigInteger processingNumber, ResourceBundle wordsStorage) {
        int lastIndex = processingNumber.toString().length() - 1;
        int lastDigit = new Integer(processingNumber.toString().charAt(lastIndex) + "");
        if (processingNumber.intValue() > 10 && processingNumber.intValue() < 20) {
            lastDigit = processingNumber.intValue();
        }
        switch (lastDigit) {
            case 1:
                return wordsStorage.getString("OneMillion");
            case 2:
            case 3:
            case 4:
                return wordsStorage.getString("SeveralMillions");
            default:
                return wordsStorage.getString("ManyMillions");
        }
    }
}
