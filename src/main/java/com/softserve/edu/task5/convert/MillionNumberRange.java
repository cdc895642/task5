package com.softserve.edu.task5.convert;

/**
 * give capability to process integer from 1000 000 to 999 999 999
 * Created by cdc89 on 24.05.2017.
 */
public class MillionNumberRange extends AbstractNumberRange {
    private static MillionNumberRange instance = null;

    /**
     * singleton
     * @return
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
        divider = 1000000;
        numberType = Type.MALE;
        minValue = 1000000;
        maxValue = 999999999;
    }

    @Override
    public String getNumberDegree(Integer processedNumber) {
        int lastIndex = processedNumber.toString().length() - 1;
        int lastDigit = new Integer(processedNumber.toString().charAt(lastIndex) + "");
        if (processedNumber > 10 && processedNumber < 20) {
            lastDigit = processedNumber;
        }
        switch (lastDigit) {
            case 1:
                return "миллион";
            case 2:
            case 3:
            case 4:
                return "миллиона";
            default:
                return "миллионов";
        }
    }
}
