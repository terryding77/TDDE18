package TDDE18.lab4.nubmer;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;


public class RationalNumber implements Num {
    private BigInteger numerator;   // the numerator
    private BigInteger denominator;   // the denominator


    public RationalNumber(double x) {
        this(BigDecimal.valueOf(x));
    }

    public RationalNumber(int num) {
        this(BigInteger.valueOf(num), BigInteger.ONE);
    }

    public RationalNumber(String num) {
        setBy(new BigDecimal(num));
    }

    private RationalNumber(BigDecimal num) {
        setBy(num);
    }

    private void setBy(BigDecimal num) {
        numerator = num.unscaledValue();
        int scale = num.scale();
        if (scale > 0) {
            denominator = BigInteger.TEN.pow(scale);
        } else {
            denominator = BigInteger.ONE;
            numerator = numerator.multiply(BigInteger.TEN.pow(-scale));
        }
    }

    private RationalNumber(BigInteger numerator, BigInteger denominator) {
        if (numerator.signum() == 0) {
            denominator = BigInteger.ONE;
        } else {
            BigInteger gcd = numerator.gcd(denominator);
            numerator = numerator.divide(gcd);
            denominator = denominator.divide(gcd);
        }
        if (denominator.bitLength() > 64) {
            // Warning 这里进行了所有计算里唯一的有理数剪枝，不然随着迭代 有理数分子分母太大，会算不过来的~
            this.setBy(decimalDivide(numerator, denominator));
        } else {
            this.numerator = numerator;
            this.denominator = denominator;
        }
    }

    @Override
    public Num add(Num number) {
        RationalNumber x = (RationalNumber) number;
        BigInteger numerator = this.numerator.multiply(x.denominator).add(this.denominator.multiply(x.numerator));
        BigInteger denominator = this.denominator.multiply(x.denominator);
        return new RationalNumber(numerator, denominator);
    }

    @Override
    public Num subtract(Num number) {
        RationalNumber x = (RationalNumber) number;
        BigInteger numerator = this.numerator.multiply(x.denominator).subtract(this.denominator.multiply(x.numerator));
        BigInteger denominator = this.denominator.multiply(x.denominator);
        return new RationalNumber(numerator, denominator);
    }

    @Override
    public Num multiply(Num number) {
        RationalNumber x = (RationalNumber) number;
        return new RationalNumber(numerator.multiply(x.numerator), denominator.multiply(x.denominator));
    }

    @Override
    public Num divide(Num number) {
        RationalNumber x = (RationalNumber) number;
        return new RationalNumber(numerator.multiply(x.denominator), denominator.multiply(x.numerator));
    }

    @Override
    public Num abs() {
        return new RationalNumber(numerator.abs(), denominator.abs());
    }

    @Override
    public Num negate() {
        return new RationalNumber(numerator.negate(), denominator);
    }

    @Override
    public int sign() {
        return numerator.signum() * denominator.signum();
    }

    @Override
    public double doubleValue(int scale) {
        // 获取指定精度的double值
        return decimalDivide(numerator, denominator).setScale(2, RoundingMode.HALF_DOWN).doubleValue();
    }

    private static BigDecimal decimalDivide(BigInteger numerator, BigInteger denominator) {
        BigDecimal num = new BigDecimal(numerator);
        BigDecimal den = new BigDecimal(denominator);
        return num.divide(den, MathContext.DECIMAL32);
    }
}
