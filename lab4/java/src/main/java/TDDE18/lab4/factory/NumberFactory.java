package TDDE18.lab4.factory;

import TDDE18.lab4.nubmer.DoubleNumber;
import TDDE18.lab4.nubmer.HighAccuracyNumber;
import TDDE18.lab4.nubmer.Num;
import TDDE18.lab4.nubmer.RationalNumber;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class NumberFactory {
    private static NumberFactory instance;

    private final Num zero;

    public static NumberFactory getInstance() {
        return instance;
    }

    private final Class<? extends Num> numberClass;

    private NumberFactory(Class<? extends Num> numberClass) {
        this.numberClass = numberClass;
        this.zero = create(0);
    }

    public static void setup(Class<? extends Num> numberClass) {
        instance = new NumberFactory(numberClass);
    }

    public Num zero() {
        return zero;
    }

    public Num create(String num) {
        return createBy(num, String.class);
    }

    public Num create(int num) {
        return createBy(num, int.class);
    }

    public Num create(double num) {
        return createBy(num, double.class);
    }

    private Num createBy(Object object, Class<?> clazz) {
        try {
            Constructor<? extends Num> constructor = numberClass.getConstructor(clazz);
            return constructor.newInstance(object);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
