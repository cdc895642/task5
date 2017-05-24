package com.softserve.edu.task5.convert;

/**
 * Created by cdc89 on 24.05.2017.
 * add ability to convert integer of some range (thousands, millions etc) to string
 */
public interface NumberRange {
    /**
     * get max count of digit in number that this class can handle
     * @return max count of digit in number
     */
    public int getMaxNumIndex();

    /**
     * get min count of digit in number that this class can handle
     * @return min count of digit in number
     */
    public int getMinNumIndex();

    /**
     * get divider that fit to the numbers that can be processed (1000 for thousands, 1000000 for millions etc)
     * @return divider that fit to the numbers that can be processed
     */
    public int getDivider();

    /**
     * get String representation of divider fit to processedNumber
     * @param processedNumber
     * @return String representation of divider
     */
    public String getNumberDegree(Integer processedNumber);

    /**
     * get kind of number string representation
     * @return kind of number string representation
     */
    public Type getNumberType();

    /**
     * get min value that can be processed
     * @return min value that can be processed
     */
    public int getMinValue();

    /**
     * get max value that can be processed
     * @return get max value that can be processed
     */
    public int getMaxValue();
}
