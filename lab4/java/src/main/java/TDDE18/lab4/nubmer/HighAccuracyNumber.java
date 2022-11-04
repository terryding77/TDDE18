package TDDE18.lab4.nubmer;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;


public class HighAccuracyNumber implements Num {
    private final BigDecimal value;

    public HighAccuracyNumber(double x) {
        this(BigDecimal.valueOf(x));
    }

    public HighAccuracyNumber(int x) {
        this(BigDecimal.valueOf(x));
    }

    public HighAccuracyNumber(String num) {
        value = new BigDecimal(num);
    }

    private HighAccuracyNumber(BigDecimal val) {
        value = BigDecimal.valueOf(val.doubleValue());
    }

    @Override
    public Num add(Num x) {
        return new HighAccuracyNumber(value.add(((HighAccuracyNumber) x).value));
    }

    @Override
    public Num subtract(Num x) {
        return new HighAccuracyNumber(value.subtract(((HighAccuracyNumber) x).value));
    }

    @Override
    public Num multiply(Num x) {
        return new HighAccuracyNumber(value.multiply(((HighAccuracyNumber) x).value));
    }

    @Override
    public Num divide(Num x) {
        return new HighAccuracyNumber(value.divide(((HighAccuracyNumber) x).value, MathContext.DECIMAL64));
    }

    @Override
    public Num abs() {
        return new HighAccuracyNumber(value.abs());
    }

    @Override
    public Num negate() {
        return new HighAccuracyNumber(value.negate());
    }

    @Override
    public int sign() {
        return value.signum();
    }

    @Override
    public double doubleValue(int scale) {
        return value.setScale(scale, RoundingMode.HALF_DOWN).doubleValue();
    }
}
