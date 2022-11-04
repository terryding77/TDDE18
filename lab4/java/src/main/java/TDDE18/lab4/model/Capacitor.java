package TDDE18.lab4.model;


import TDDE18.lab4.factory.NumberFactory;
import TDDE18.lab4.nubmer.Num;

public class Capacitor extends Component {
    Num capacitance;
    Num savedVoltage;


    public Capacitor(String name, double capacitance, Connection begin, Connection end) {
        this(name, NumberFactory.getInstance().create(capacitance), begin, end);
    }

    public Capacitor(String name, Num capacitance, Connection begin, Connection end) {
        super(name, begin, end);
        this.capacitance = capacitance;
        this.savedVoltage = NumberFactory.getInstance().zero();
    }

    @Override
    protected Num getCurrent() {
        return NumberFactory.getInstance().zero();
    }

    @Override
    protected Num calcDeltaVoltage(Num timeStep, Num beginVoltage, Num endVoltage) {
        Num delta = beginVoltage.subtract(endVoltage).abs().subtract(savedVoltage);
        if (delta.sign() < 0) {
//        if (Math.abs(beginVoltage - endVoltage) < savedVoltage) {
            // 不考虑电容放电
            return NumberFactory.getInstance().zero();
        }
        Num deltaVoltage = delta.multiply(capacitance).multiply(timeStep);
        savedVoltage = savedVoltage.add(deltaVoltage);
        return deltaVoltage.multiply(NumberFactory.getInstance().create(beginVoltage.subtract(endVoltage).sign()));
    }
}
