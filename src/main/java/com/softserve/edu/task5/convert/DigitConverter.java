package com.softserve.edu.task5.convert;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * convert integer into the string representation. Base implementation can process numbers from 0 to 999
 */
public class DigitConverter {
    private String[] firstTen = {"", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"};
    private String[] secondTen = {"десять", "одинадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать", "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать"};
    private String[] tens = {"", "", "двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят", "девяносто"};
    private String[] hundreds = {"", "сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот"};
    private StringBuilder outputNumber;
    private Integer inputNumber;
    private int minValue = 0;
    private int maxValue = 999;
    private List<NumberRange> rangeList;

    /**
     * return minimal input value
     *
     * @return minimal input value
     */
    public int getMinValue() {
        return minValue;
    }

    /**
     * return maximum input value
     *
     * @return maximum input value
     */
    public int getMaxValue() {
        return maxValue;
    }

    public void addRange(NumberRange numberRange){
        rangeList.add(numberRange);
        if (numberRange.getMinValue()<minValue){
            minValue=numberRange.getMinValue();
        }
        if (numberRange.getMaxNumIndex()>maxValue){
            maxValue=numberRange.getMaxNumIndex();
        }
    }

    public DigitConverter(Integer inputNumber) {
        this();
        this.inputNumber = inputNumber;
        //convert();
    }

    private DigitConverter() {
        rangeList = new ArrayList<>();
        outputNumber = new StringBuilder();
    }

    public DigitConverter(String inputString) throws ParseException {
        this();
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        inputNumber = numberFormat.parse(inputString).intValue();
        //convert();
    }

    private void checkInputValue(int inputNumber) {
        if (inputNumber >= minValue && inputNumber <= maxValue) {
            return;
        }
        for (NumberRange numberRange:rangeList){
            if (inputNumber >= numberRange.getMinValue() && inputNumber <= numberRange.getMaxValue()){
                return;
            }
        }
        throw new IllegalArgumentException(String.format("input number %s is out of range", inputNumber));
    }

    /**
     * convert input number into the string representation
     */
    public void convertNumber() {
        checkInputValue(inputNumber);
        if (inputNumber == 0) {
            outputNumber.append("ноль");
            return;
        }
        Integer remainder = inputNumber;
        int arraySize = remainder.toString().length();
        Integer processedNumber = null;
        int divider = 1;

        for (int i = 0; i < inputNumber.toString().length(); ) {
            Type degreeType = Type.MALE;
            for (NumberRange numberRange : rangeList) {
                if (arraySize >= numberRange.getMinNumIndex() && arraySize <= numberRange.getMaxNumIndex()) {
                    divider = numberRange.getDivider();
                    degreeType = numberRange.getNumberType();
                }
            }

            processedNumber = remainder / divider;
            checkInputValue(processedNumber);
            remainder = remainder - processedNumber * divider;

            convertBeforeThousand(processedNumber, degreeType);
            addNumberDegree(processedNumber, divider);

            arraySize = remainder.toString().length();
            i += processedNumber.toString().length();
            divider = 1;
        }
    }

    /**
     * replaced by convertNumber()
     */
    @Deprecated
    private void convert() {
        checkInputValue(inputNumber);
        if (inputNumber == 0) {
            outputNumber.append("ноль");
            return;
        }
        Integer remainder = inputNumber;
        int arraySize = remainder.toString().length();
        Integer processedNumber = null;
        int divider = 1;

        for (int i = 0; i < inputNumber.toString().length(); ) {
            Type degreeType = Type.MALE;
            if (arraySize > 6 && arraySize < 10) {
                divider = 1000000;
            }
            if (arraySize > 3 && arraySize < 7) {
                degreeType = Type.FEMALE;
                divider = 1000;
            }

            processedNumber = remainder / divider;
            remainder = remainder - processedNumber * divider;

            convertBeforeThousand(processedNumber, degreeType);
            addDegree(processedNumber, divider);

            arraySize = remainder.toString().length();
            i += processedNumber.toString().length();
            divider = 1;
        }

    }

    /**
     * replaced by addNumberDegree()
     * @param processedNumber
     * @param divider
     */
    @Deprecated
    private void addDegree(Integer processedNumber, int divider) {
        String degree = getDegree(processedNumber, divider);
        outputNumber.append(degree + " ");
    }

    private void addNumberDegree(Integer processedNumber, int divider) {
        String degree = getNumberDegree(processedNumber, divider);
        outputNumber.append(degree + " ");
    }

    private void convertBeforeThousand(Integer input, Type type) {
        char[] inputArray = input.toString().toCharArray();
        if (inputArray.length == 3) {
            outputNumber.append(hundreds[new Integer(inputArray[0] + "")] + " ");
        }
        if (inputArray.length == 2 && input >= 20) {
            outputNumber.append(tens[new Integer(inputArray[0] + "")] + " ");
        } else if (input >= 10 && input < 20) {
            outputNumber.append(secondTen[Integer.parseInt(new String(inputArray)) - 10] + " ");
            return;
        }
        if (inputArray.length == 1) {
            String unit = getUnits(new Integer(inputArray[0] + ""), type);
            outputNumber.append(unit + " ");
            return;
        }
        convertBeforeThousand(Integer.parseInt(new String(inputArray, 1, inputArray.length - 1)), type);
    }

    private String getNumberDegree(Integer processedNumber, int divider) {
        for (NumberRange numberRange:rangeList){
            if (divider==numberRange.getDivider()){
                return numberRange.getNumberDegree(processedNumber);
            }
        }
        return "";
    }

    /**
     * replaced by getNumberDegree()
     * @param processedNumber
     * @param divider
     * @return
     */
    @Deprecated
    private String getDegree(Integer processedNumber, int divider) {
        int lastIndex = processedNumber.toString().length() - 1;
        int lastDigit = new Integer(processedNumber.toString().charAt(lastIndex) + "");
        if (processedNumber > 10 && processedNumber < 20) {
            lastDigit = processedNumber;
        }
        if (divider == 1000000) {
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
        if (divider == 1000) {
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
        return "";
    }

    private String getUnits(int digit, Type type) {
        switch (digit) {
            case 1:
                if (type == Type.FEMALE) {
                    return "одна";
                }
                break;
            case 2:
                if (type == Type.FEMALE) {
                    return "две";
                }
                break;
        }
        return firstTen[digit];
    }

    @Override
    public String toString() {
        return outputNumber.toString();
    }
}
