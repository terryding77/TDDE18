package TDDE18.lab4.model;

public class Connection {
    private boolean isConstantVoltage;
    private double voltage;

    public Connection() {
        isConstantVoltage = false;
        voltage = 0;
    }

    public double getVoltage() {
        return voltage;
    }

    public void changeVoltage(double deltaVoltage) {
        if (!isConstantVoltage) {
            voltage += deltaVoltage;
        }
    }

    public void setConstantVoltage(double constantVoltage) {
        voltage = constantVoltage;
        isConstantVoltage = true;
    }
}
