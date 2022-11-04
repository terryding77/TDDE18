package TDDE18.lab4.model;

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
        System.out.printf("%8.2f%8.2f", getVoltage(), getCurrent());
    }

    public void simulate(double timeStep) {
        double deltaVoltage = calcDeltaVoltage(timeStep, begin.getVoltage(), end.getVoltage());
        begin.changeVoltage(-deltaVoltage);
        end.changeVoltage(deltaVoltage);
    }


    protected double getVoltage() {
        return Math.abs(begin.getVoltage() - end.getVoltage());
    }


    abstract protected double getCurrent();

    protected abstract double calcDeltaVoltage(double timeStep, double beginVoltage, double endVoltage);
}
