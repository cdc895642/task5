package com.softserve.edu.task5.convert;

import com.softserve.edu.task5.convert.convert.DigitConverter;
import com.softserve.edu.task5.convert.convert.range.MillionNumberRange;
import com.softserve.edu.task5.convert.convert.range.ThousandNumberRange;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for DigitConverter.
 */
public class DigitConverterTest {
    private static ThousandNumberRange thousandNumberRange;
    private static MillionNumberRange millionNumberRange;

    @BeforeClass
    public static void initRanges() {
        thousandNumberRange = ThousandNumberRange.getInstance();
        millionNumberRange = MillionNumberRange.getInstance();
    }

    @Test(expected = IllegalArgumentException.class)
    public void ConvertNumber_WrongInputParameter_ExceptionThrown() {
        //Arrange
        String input = "a123";
        DigitConverter digitConverter = new DigitConverter(input);
    }

    @Test
    public void ConvertNumber_LessThenThousand123NoRangeAdd_ReturnResult() {
        //Arrange
        String input = "123";
        DigitConverter digitConverter = new DigitConverter(input);

        //Act
        digitConverter.convertNumber();
        String result = digitConverter.toString();
        String expected = "сто двадцать три";

        //Assert
        assertEquals(result, expected);
    }

    @Test
    public void ConvertNumber_LessThenThousand701NoRangeAdd_ReturnResult() {
        //Arrange
        String input = "701";
        DigitConverter digitConverter = new DigitConverter(input);

        //Act
        digitConverter.convertNumber();
        String result = digitConverter.toString();
        String expected = "семьсот один";

        //Assert
        assertEquals(result, expected);
    }

    @Test
    public void ConvertNumber_LessThenThousand19NoRangeAdd_ReturnResult() {
        //Arrange
        String input = "19";
        DigitConverter digitConverter = new DigitConverter(input);

        //Act
        digitConverter.convertNumber();
        String result = digitConverter.toString();
        String expected = "девятнадцать";

        //Assert
        assertEquals(result, expected);
    }

    @Test
    public void ConvertNumber_LessThenMillionRangeAdd_ReturnResult() {
        //Arrange
        String input = "698375";
        DigitConverter digitConverter = new DigitConverter(input);
        digitConverter.addRange(thousandNumberRange);

        //Act
        digitConverter.convertNumber();
        String result = digitConverter.toString();
        String expected = "шестьсот девяносто восемь тысяч триста семьдесят пять";

        //Assert
        assertEquals(result, expected);
    }

    @Test
    public void ConvertNumber_LessThenMillionRangeAddFemale_ReturnResult() {
        //Arrange
        String input = "321702";
        DigitConverter digitConverter = new DigitConverter(input);
        digitConverter.addRange(thousandNumberRange);

        //Act
        digitConverter.convertNumber();
        String result = digitConverter.toString();
        String expected = "триста двадцать одна тысяча семьсот два";

        //Assert
        assertEquals(result, expected);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ConvertNumber_LessThenMillionNoRangeAdd_ExceptionThrown() {
        //Arrange
        String input = "321702";
        DigitConverter digitConverter = new DigitConverter(input);

        //Act
        digitConverter.convertNumber();
    }

    @Test
    public void ConvertNumber_LessThenBillionRangeAdd_ReturnResult() {
        //Arrange
        String input = "456256128";
        DigitConverter digitConverter = new DigitConverter(input);
        digitConverter.addRange(thousandNumberRange);
        digitConverter.addRange(millionNumberRange);

        //Act
        digitConverter.convertNumber();
        String result = digitConverter.toString();
        String expected = "четыреста пятьдесят шесть миллионов двести пятьдесят шесть тысяч сто " +
                "двадцать восемь";

        //Assert
        assertEquals(result, expected);
    }

    @Test
    public void ConvertNumber_LessThenBillionRangeAddMale_ReturnResult() {//Arrange
        //Arrange
        String input = "1321001";
        DigitConverter digitConverter = new DigitConverter(input);
        digitConverter.addRange(thousandNumberRange);
        digitConverter.addRange(millionNumberRange);

        //Act
        digitConverter.convertNumber();
        String result = digitConverter.toString();
        String expected = "один миллион триста двадцать одна тысяча один";

        //Assert
        assertEquals(result, expected);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ConvertNumber_LessThenBillionNoThousandRangeAdd_ExceptionThrown() {
        //Arrange
        String input = "456256128";
        DigitConverter digitConverter = new DigitConverter(input);
        digitConverter.addRange(millionNumberRange);

        //Act
        digitConverter.convertNumber();
    }

    @Test(expected = IllegalArgumentException.class)
    public void ConvertNumber_LessThenBillionNoMillionRangeAdd_ExceptionThrown() {
        //Arrange
        String input = "1321001";
        DigitConverter digitConverter = new DigitConverter(input);
        digitConverter.addRange(thousandNumberRange);

        //Act
        digitConverter.convertNumber();
    }

    @Test
    public void ConvertNumber_LessThenBillionRangeAddMinusInput_ReturnResult() {
        //Arrange
        String input = "-11354019";
        DigitConverter digitConverter = new DigitConverter(input);
        digitConverter.addRange(thousandNumberRange);
        digitConverter.addRange(millionNumberRange);

        //Act
        digitConverter.convertNumber();
        String result = digitConverter.toString();
        String expected = "минус одинадцать миллионов триста пятьдесят четыре тысячи девятнадцать";

        //Assert
        assertEquals(result, expected);
    }
}
