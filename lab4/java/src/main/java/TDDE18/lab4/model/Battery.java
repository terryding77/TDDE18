package TDDE18.lab4.model;


public class Battery extends Component {
    double batteryVoltage;

    public Battery(String name, double voltage, Connection begin, Connection end) {
        super(name, begin, end);
        this.batteryVoltage = voltage;
        begin.setConstantVoltage(batteryVoltage);
        end.setConstantVoltage(0);
    }

    @Override
    protected double getCurrent() {
        return 0;
    }

    @Override
    protected double calcDeltaVoltage(double timeStep, double beginVoltage, double endVoltage) {
        return 0;
    }
}
