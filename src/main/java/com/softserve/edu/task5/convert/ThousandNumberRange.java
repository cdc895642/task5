package com.softserve.edu.task5.convert;

/**
 * give capability to process integer from 1000 to 999 999
 * Created by cdc89 on 24.05.2017.
 */
public class ThousandNumberRange extends AbstractNumberRange {

    private static ThousandNumberRange instance = null;

    /**
     * singleton
     * @return
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
        divider = 1000;
        numberType = Type.FEMALE;
        minValue = 1000;
        maxValue = 999999;
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
                return "тысяча";
            case 2:
            case 3:
            case 4:
                return "тысячи";
            default:
                return "тысяч";
        }
    }
}
