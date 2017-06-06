package com.softserve.edu.task5;

import com.softserve.edu.task5.convert.DigitConverter;
import com.softserve.edu.task5.convert.range.MillionNumberRange;
import com.softserve.edu.task5.convert.range.ThousandNumberRange;

import java.io.PrintStream;

/**
 * entry point
 */
public class App {
    private static String RULES = "input integer value as an argument of the "
            + "program";
    private static PrintStream out = System.out;

    public static void main(String[] args) {
        if (args.length == 0) {
            out.print(RULES);
            return;
        }
        processNumber(args[0]);
    }

    public static void processNumber(String input) {
        try {
            DigitConverter digitConverter = new DigitConverter(input);
            ThousandNumberRange thousandNumberRange = ThousandNumberRange
                    .getInstance();
            MillionNumberRange millionNumberRange = MillionNumberRange
                    .getInstance();
            digitConverter.addRange(thousandNumberRange);
            digitConverter.addRange(millionNumberRange);
            digitConverter.convertNumber();
            out.println(digitConverter);
        } catch (IllegalArgumentException e) {
            out.println("Please check input argument or added NumberRange :");
            e.printStackTrace();
        }
    }

    public static void setOut(PrintStream out) {
        App.out = out;
    }
}
