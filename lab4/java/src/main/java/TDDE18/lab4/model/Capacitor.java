package TDDE18.lab4.model;


public class Capacitor extends Component {
    double capacitance;
    double savedVoltage;

    public Capacitor(String name, double capacitance, Connection begin, Connection end) {
        super(name, begin, end);
        this.capacitance = capacitance;
        this.savedVoltage = 0;
    }

    @Override
    protected double getCurrent() {
        return 0;
    }

    @Override
    protected double calcDeltaVoltage(double timeStep, double beginVoltage, double endVoltage) {
        if (Math.abs(beginVoltage - endVoltage) < savedVoltage) {
            // 不考虑电容放电
            return 0;
        }
        double deltaVoltage = (Math.abs(beginVoltage - endVoltage) - savedVoltage) * capacitance * timeStep;
        savedVoltage += deltaVoltage;
        return Math.signum(beginVoltage - endVoltage) * deltaVoltage;
    }
}
