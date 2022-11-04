package TDDE18.lab4.model;


import TDDE18.lab4.factory.NumberFactory;
import TDDE18.lab4.nubmer.Num;

public abstract class Component {
    String name;
    Connection begin, end;

    Component(String name, Connection begin, Connection end) {
        this.name = name;
        this.begin = begin;
        this.end = end;
    }

    public void printName() {
        System.out.printf("%16s", name);
    }

    public void printTitle() {
        System.out.printf("%8s%8s", "Volt", "Curr");
    }

    public void printValue() {
        System.out.printf("%8.2f%8.2f", getVoltage().doubleValue(2), getCurrent().doubleValue(2));
    }

    public void simulate(Num timeStep) {
        Num deltaVoltage = calcDeltaVoltage(timeStep, begin.getVoltage(), end.getVoltage());
        begin.changeVoltage(deltaVoltage.negate());
        end.changeVoltage(deltaVoltage);
    }


    protected Num getVoltage() {
        return begin.getVoltage().subtract(end.getVoltage()).abs();
    }


    abstract protected Num getCurrent();

    protected abstract Num calcDeltaVoltage(Num timeStep, Num beginVoltage, Num endVoltage);
}
