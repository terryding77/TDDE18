package TDDE18.lab4.model;


import TDDE18.lab4.factory.NumberFactory;
import TDDE18.lab4.nubmer.Num;

public class Resistor extends Component {

    Num resistance;

    public Resistor(String name, double resistance, Connection begin, Connection end) {
        this(name, NumberFactory.getInstance().create(resistance), begin, end);
    }

    public Resistor(String name, Num resistance, Connection begin, Connection end) {
        super(name, begin, end);
        this.resistance = resistance;
    }

    @Override
    protected Num getCurrent() {
        return getVoltage().divide(resistance);
    }

    @Override
    protected Num calcDeltaVoltage(Num timeStep, Num beginVoltage, Num endVoltage) {
        // FIXME
        return beginVoltage.subtract(endVoltage).multiply(timeStep).divide(resistance);
    }
}
