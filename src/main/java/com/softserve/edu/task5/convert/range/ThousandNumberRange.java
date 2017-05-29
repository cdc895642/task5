package com.softserve.edu.task5.convert.range;

import com.softserve.edu.task5.convert.Type;

import java.math.BigInteger;
import java.util.ResourceBundle;

/**
 * give capability to process integer from 1000 to 999 999
 * Created by cdc89 on 24.05.2017.
 */
public class ThousandNumberRange extends AbstractNumberRange {

    private static ThousandNumberRange instance = null;

    /**
     * singleton
     * @return object of this class
     */
    public static ThousandNumberRange getInstance() {
        if (instance == null) {
            instance = new ThousandNumberRange();
        }
        return instance;
    }

    private ThousandNumberRange() {
        maxNumIndex = 6;
        minNumIndex = 4;
        divider = BigInteger.valueOf(1000);
        numberType = Type.FEMALE;
        minValue = BigInteger.valueOf(1000);
        maxValue = BigInteger.valueOf(999999);
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
                return wordsStorage.getString("OneThousand");
            case 2:
            case 3:
            case 4:
                return wordsStorage.getString("SeveralThousands");
            default:
                return wordsStorage.getString("ManyThousands");
        }
    }
}
