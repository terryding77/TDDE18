package TDDE18.lab4.model;


public class Resistor extends Component {

    double resistance;

    public Resistor(String name, double resistance, Connection begin, Connection end) {
        super(name, begin, end);
        this.resistance = resistance;
    }

    @Override
    protected double getCurrent() {
        return getVoltage() / resistance;
    }

    @Override
    protected double calcDeltaVoltage(double timeStep, double beginVoltage, double endVoltage) {
        return (beginVoltage - endVoltage) / resistance * timeStep;
    }
}
