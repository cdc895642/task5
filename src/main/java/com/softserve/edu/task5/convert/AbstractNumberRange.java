package com.softserve.edu.task5.convert;

/**
 * base implementation of functionality for NumberRange
 * Created by cdc89 on 24.05.2017.
 */
public abstract class AbstractNumberRange implements NumberRange {
    protected int maxNumIndex=0;
    protected int minNumIndex=0;
    protected int divider=1;
    protected String numberDegree=null;
    protected  Type numberType=null;
    protected int minValue=0;
    protected int maxValue=0;

    @Override
    public int getMaxNumIndex() {
        return maxNumIndex;
    }

    @Override
    public int getMinNumIndex() {
        return minNumIndex;
    }

    @Override
    public int getDivider() {
        return divider;
    }

    @Override
    public String getNumberDegree(Integer processedNumber) {
        return numberDegree;
    }

    @Override
    public Type getNumberType() {
        return numberType;
    }

    @Override
    public int getMinValue() {
        return minValue;
    }

    @Override
    public int getMaxValue() {
        return maxValue;
    }
}
