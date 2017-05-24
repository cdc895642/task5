package com.softserve.edu.task5.convert;

import java.text.NumberFormat;
import java.text.ParseException;

/**
 * convert integer into the string representation
 */
public class DigitConverter {
    private String[] firstTen = {"", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"};
    private String[] secondTen = {"десять", "одинадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать", "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать"};
    private String[] tens = {"", "", "двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят", "девяносто"};
    private String[] hundreds = {"", "сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот"};
    private StringBuilder outputNumber;
    private Integer inputNumber;
    private int minValue=0;
    private int maxValue=999999999;

    public int getMinValue() {
        return minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public DigitConverter(Integer inputNumber) {
        this.inputNumber = inputNumber;
        convert();
    }

    public DigitConverter(String inputString) throws ParseException {
        NumberFormat numberFormat=NumberFormat.getNumberInstance();
        inputNumber=numberFormat.parse(inputString).intValue();
        convert();
    }

    private void checkInputValue(){
    if (inputNumber<minValue || inputNumber>maxValue){
        throw new IllegalArgumentException(String.format("input number %s is out of range",inputNumber));
    }
    }

    private void convert() {
        checkInputValue();
        outputNumber = new StringBuilder();
        if (inputNumber==0){
            outputNumber.append("ноль");
            return;
        }
        Integer remainder = inputNumber;
        int arraySize = remainder.toString().length();
        Integer processedNumber = null;
        int divider = 1;

        for (int i = 0; i < inputNumber.toString().length(); ) {
            Type degreeType=Type.MALE;
            if (arraySize > 6 && arraySize < 10) {
                divider = 1000000;
            }
            if (arraySize > 3 && arraySize < 7) {
                degreeType=Type.FEMALE;
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

    private void addDegree(Integer processedNumber, int divider) {
        String degree=getDegree(processedNumber, divider);
        outputNumber.append(degree+" ");
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

    private String getDegree(Integer processedNumber, int divider) {
        int lastIndex=processedNumber.toString().length()-1;
        int lastDigit=new Integer(processedNumber.toString().charAt(lastIndex)+"");
        if (processedNumber>10 && processedNumber<20){
            lastDigit=processedNumber;
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

    private enum Type {
        MALE, FEMALE
    }

    @Override
    public String toString() {
        return outputNumber.toString();
    }
}
