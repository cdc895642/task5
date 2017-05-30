package com.softserve.edu.task5.convert;

import com.softserve.edu.task5.convert.convert.DigitConverter;
import com.softserve.edu.task5.convert.convert.range.MillionNumberRange;
import com.softserve.edu.task5.convert.convert.range.ThousandNumberRange;

/**
 * entry point
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
        try {
            DigitConverter digitConverter = new DigitConverter(input);
            ThousandNumberRange thousandNumberRange = ThousandNumberRange.getInstance();
            MillionNumberRange millionNumberRange = MillionNumberRange.getInstance();
            digitConverter.addRange(thousandNumberRange);
            digitConverter.addRange(millionNumberRange);
            digitConverter.convertNumber();
            System.out.println(digitConverter);
        } catch (IllegalArgumentException e) {
            System.out.println("Please check input argument or added NumberRange :");
            e.printStackTrace();
        }
    }
}
