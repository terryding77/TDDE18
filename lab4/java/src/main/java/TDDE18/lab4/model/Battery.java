package TDDE18.lab4.model;


import TDDE18.lab4.factory.NumberFactory;
import TDDE18.lab4.nubmer.Num;


public class Battery extends Component {
    Num batteryVoltage;

    public Battery(String name, double voltage, Connection begin, Connection end) {
        this(name, NumberFactory.getInstance().create(voltage), begin, end);
    }

    public Battery(String name, Num voltage, Connection begin, Connection end) {
        super(name, begin, end);
        this.batteryVoltage = voltage;
        begin.setConstantVoltage(batteryVoltage);
        end.setConstantVoltage(NumberFactory.getInstance().zero());
    }

    @Override
    protected Num getCurrent() {
        return NumberFactory.getInstance().zero();
    }

    @Override
    protected Num calcDeltaVoltage(Num timeStep, Num beginVoltage, Num endVoltage) {
        return NumberFactory.getInstance().zero();
    }
}
