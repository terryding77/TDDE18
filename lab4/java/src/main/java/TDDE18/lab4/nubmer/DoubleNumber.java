package TDDE18.lab4.nubmer;


public class DoubleNumber implements Num {
    private final double value;

    public DoubleNumber(String num) {
        value = Double.parseDouble(num);
    }

    public DoubleNumber(int num) {
        value = 1.0 * num;
    }

    public DoubleNumber(double num) {
        value = num;
    }

    @Override
    public Num add(Num x) {
        return new DoubleNumber(value + ((DoubleNumber) x).value);
    }

    @Override
    public Num subtract(Num x) {
        return new DoubleNumber(value - ((DoubleNumber) x).value);
    }

    @Override
    public Num multiply(Num x) {
        return new DoubleNumber(value * ((DoubleNumber) x).value);
    }

    @Override
    public Num divide(Num x) {
        return new DoubleNumber(value / ((DoubleNumber) x).value);
    }

    @Override
    public Num abs() {
        return new DoubleNumber(Math.abs(value));
    }

    @Override
    public Num negate() {
        return new DoubleNumber(-value);
    }

    @Override
    public int sign() {
        return (int) Math.signum(value);
    }

    @Override
    public double doubleValue(int scale) {
        return value;
    }
}
