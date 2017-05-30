package com.softserve.edu.task5.convert.convert;

import com.softserve.edu.task5.convert.convert.range.NumberRange;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * convert integer into the string representation. Base implementation can
 * process numbers from 0 to 999
 */
public class DigitConverter {
    private Locale locale;
    private ResourceBundle wordsStorage;
    private String[] firstTen;
    private String[] secondTen;
    private String[] tens;
    private String[] hundreds;
    private StringBuilder outputNumber;
    private BigInteger inputNumber;
    private BigInteger minValue = BigInteger.ZERO;
    private BigInteger maxValue = BigInteger.valueOf(999);
    private List<NumberRange> rangeList;
    private String inputString;

    private DigitConverter() {
        rangeList = new ArrayList<>();
        outputNumber = new StringBuilder();
    }

    public DigitConverter(String inputString, Locale locale) {
        this();
        this.locale = locale;
        wordsStorage = ResourceBundle.getBundle("WordsStorage", locale);
        firstTen = wordsStorage.getString("FirstTen").split(",");
        secondTen = wordsStorage.getString("SecondTen").split(",");
        tens = wordsStorage.getString("Tens").split(",");
        hundreds = wordsStorage.getString("Hundreds").split(",");
        this.inputString = inputString;
        inputString = removeMinus(inputString);
        inputNumber = new BigInteger(inputString);
    }

    public DigitConverter(String inputString) {
        this(inputString, new Locale("ru", "RU"));
    }

    private String removeMinus(String inputString) {
        inputString = inputString.startsWith("-") ? inputString.replaceFirst("-", "") : inputString;
        return inputString;
    }

    public void addRange(NumberRange numberRange) {
        rangeList.add(numberRange);
    }

    private void checkInputValue(BigInteger inputNumber) {
        if (inputNumber.compareTo(minValue) >= 0 && inputNumber.compareTo(maxValue) <= 0) {
            return;
        }
        for (NumberRange numberRange : rangeList) {
            if (inputNumber.compareTo(numberRange.getMinValue()) >= 0 && inputNumber.compareTo
                    (numberRange.getMaxValue()) <=
                    0) {
                return;
            }
        }
        throw new IllegalArgumentException(String.format("input number %s is " +
                "out of range", inputNumber));
    }

    /**
     * convert input number into the string representation
     */
    public void convertNumber() {
        checkInputValue(inputNumber);
        if (inputNumber.compareTo(BigInteger.ZERO) == 0) {
            outputNumber.append("ноль");
            return;
        }
        BigInteger remainder = inputNumber;
        int arraySize = remainder.toString().length();
        BigInteger processingNumber;
        BigInteger divider = BigInteger.ONE;

        for (int i = 0; i < inputNumber.toString().length(); ) {
            Type degreeType = Type.NONE;
            for (NumberRange numberRange : rangeList) {
                if (arraySize >= numberRange.getMinNumIndex() && arraySize <=
                        numberRange.getMaxNumIndex()) {
                    divider = numberRange.getDivider();
                    degreeType = numberRange.getNumberType();
                }
            }

            processingNumber = remainder.divide(divider);
            checkInputValue(processingNumber);
            remainder = remainder.subtract(processingNumber.multiply(divider));

            convertBeforeThousand(processingNumber.intValue(), degreeType);
            addNumberDegree(processingNumber, divider);

            arraySize = remainder.toString().length();
            i += processingNumber.toString().length();
            divider = BigInteger.ONE;
        }
    }

    private void addNumberDegree(BigInteger processingNumber, BigInteger divider) {
        String degree = getNumberDegree(processingNumber, divider);
        outputNumber.append(degree + " ");
    }

    private void convertBeforeThousand(Integer input, Type type) {
        char[] inputArray = input.toString().toCharArray();
        if (inputArray.length == 3) {
            outputNumber.append(hundreds[new Integer(inputArray[0] + "")] + "" +
                    " ");
        }
        if (inputArray.length == 2 && input >= 20) {
            outputNumber.append(tens[new Integer(inputArray[0] + "")] + " ");
        } else if (input >= 10 && input < 20) {
            outputNumber.append(secondTen[Integer.parseInt(new String
                    (inputArray)) - 10] + " ");
            return;
        }
        if (inputArray.length == 1) {
            String unit = getUnits(new Integer(inputArray[0] + ""), type);
            outputNumber.append(unit + " ");
            return;
        }
        convertBeforeThousand(Integer.parseInt(new String(inputArray, 1,
                inputArray.length - 1)), type);
    }

    private String getNumberDegree(BigInteger processingNumber, BigInteger divider) {
        for (NumberRange numberRange : rangeList) {
            if (divider.compareTo(numberRange.getDivider()) == 0) {
                return numberRange.getNumberDegree(processingNumber, wordsStorage);
            }
        }
        return "";
    }

    /**
     * can be overridden if string representation of number depends on gender and the number of
     * units
     *
     * @param digit number of units
     * @param type  gender of words
     * @return string representation of units (1-9)
     */
    protected String getUnits(int digit, Type type) {
        switch (digit) {
            case 1:
                if (type == Type.FEMALE) {
                    return wordsStorage.getString("FemaleOne");//одна
                }
                break;
            case 2:
                if (type == Type.FEMALE) {
                    return wordsStorage.getString("FemaleTwo");//две
                }
                break;
        }
        return firstTen[digit];
    }

    @Override
    public String toString() {
        String result = isInputHaveMinus() ?
                wordsStorage.getString("Minus") +" " + outputNumber.toString() :
                outputNumber.toString();
        return result.trim();
    }

    private boolean isInputHaveMinus() {
        return inputString.startsWith("-");
    }
}
