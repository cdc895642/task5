package com.softserve.edu.task5;

import com.softserve.edu.task5.convert.DigitConverter;
import com.softserve.edu.task5.convert.MillionNumberRange;
import com.softserve.edu.task5.convert.ThousandNumberRange;

import java.text.NumberFormat;
import java.text.ParseException;

/**
 * Hello world!
 */
public class App {
    private static String RULES="input integer value as an argument of the program";

    public static void main(String[] args) {
        if (args.length==0){
            System.out.print(RULES);
        }
        processNumber(args[0]);
    }

    public static void processNumber(String input) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        try {
            Integer inputNumber = numberFormat.parse(input).intValue();
            DigitConverter digitConverter = new DigitConverter(inputNumber);
            ThousandNumberRange thousandNumberRange = ThousandNumberRange.getInstance();
            MillionNumberRange millionNumberRange = MillionNumberRange.getInstance();
            digitConverter.addRange(thousandNumberRange);
            digitConverter.addRange(millionNumberRange);
            digitConverter.convertNumber();
            System.out.println(digitConverter);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
