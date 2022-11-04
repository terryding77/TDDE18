package TDDE18.lab4.model;

import TDDE18.lab4.factory.NumberFactory;
import TDDE18.lab4.nubmer.Num;

public class Connection {
    private boolean isConstantVoltage;
    private Num voltage;

    public Connection() {
        isConstantVoltage = false;
        voltage = NumberFactory.getInstance().zero();
    }

    public Num getVoltage() {
        return voltage;
    }

    public void changeVoltage(Num deltaVoltage) {
        if (!isConstantVoltage) {
            voltage = voltage.add(deltaVoltage);
        }
    }

    public void setConstantVoltage(Num constantVoltage) {
        voltage = constantVoltage;
        isConstantVoltage = true;
    }
}
