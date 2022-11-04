package TDDE18.lab4.nubmer;


public interface Num {
    Num add(Num x);

    Num subtract(Num x);

    Num multiply(Num x);

    Num divide(Num x);

    Num abs();

    Num negate();

    int sign();

    double doubleValue(int scale);
}
