package com.softserve.edu.task5;

import com.softserve.edu.task5.convert.DigitConverter;

import java.text.NumberFormat;
import java.text.ParseException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        NumberFormat numberFormat=NumberFormat.getNumberInstance();
        try {
            Integer inputNumber=numberFormat.parse(args[0]).intValue();
            DigitConverter digitConverter=new DigitConverter(inputNumber);
            System.out.println(digitConverter.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
