package com.softserve.edu.task5.convert.convert.range;

import com.softserve.edu.task5.convert.convert.Type;

import java.math.BigInteger;
import java.util.ResourceBundle;

/**
 * base implementation of functionality for NumberRange
 * Created by cdc89 on 24.05.2017.
 */
public abstract class AbstractNumberRange implements NumberRange {
    protected int maxNumIndex=0;
    protected int minNumIndex=0;
    protected BigInteger divider=BigInteger.ONE;
    protected String numberDegree=null;
    protected Type numberType=null;
    protected BigInteger minValue=BigInteger.ZERO;
    protected BigInteger maxValue=BigInteger.ZERO;

    @Override
    public int getMaxNumIndex() {
        return maxNumIndex;
    }

    @Override
    public int getMinNumIndex() {
        return minNumIndex;
    }

    @Override
    public BigInteger getDivider() {
        return divider;
    }

    @Override
    public String getNumberDegree(BigInteger processedNumber, ResourceBundle wordsStorage) {
        return numberDegree;
    }

    @Override
    public Type getNumberType() {
        return numberType;
    }

    @Override
    public BigInteger getMinValue() {
        return minValue;
    }

    @Override
    public BigInteger getMaxValue() {
        return maxValue;
    }
}
