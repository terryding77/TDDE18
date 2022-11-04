package TDDE18.lab4;

import TDDE18.lab4.factory.NumberFactory;
import TDDE18.lab4.model.*;
import TDDE18.lab4.nubmer.DoubleNumber;
import TDDE18.lab4.nubmer.Num;


@SuppressWarnings("DuplicatedCode")
public class Main_Circuit {
    public static void runTest1(int iterations, int prints, Num timeStep, Num batteryVoltage) {
        Connection p = new Connection();
        Connection n = new Connection();
        Connection p124 = new Connection();
        Connection p23 = new Connection();

        Circuit circuit = new Circuit();
        circuit.add(new Battery("Bat", batteryVoltage, p, n));
        circuit.add(new Resistor("R1", 6.0, p, p124));
        circuit.add(new Resistor("R2", 4.0, p124, p23));
        circuit.add(new Resistor("R3", 8.0, p23, n));
        circuit.add(new Resistor("R4", 12.0, p124, n));
        circuit.simulate(iterations, prints, timeStep);
        circuit.deallocate();
    }


    public static void runTest2(int iterations, int prints, Num timeStep, Num batteryVoltage) {
        Connection p = new Connection();
        Connection n = new Connection();
        Connection l = new Connection();
        Connection r = new Connection();

        Circuit circuit = new Circuit();
        circuit.add(new Battery("Bat", batteryVoltage, p, n));
        circuit.add(new Resistor("R1", 150.0, p, l));
        circuit.add(new Resistor("R2", 50.0, p, r));
        circuit.add(new Resistor("R3", 100.0, l, r));
        circuit.add(new Resistor("R4", 300.0, l, n));
        circuit.add(new Resistor("R5", 250.0, r, n));
        circuit.simulate(iterations, prints, timeStep);
        circuit.deallocate();
    }

    public static void runTest3(int iterations, int prints, Num timeStep, Num batteryVoltage) {
        Connection p = new Connection();
        Connection n = new Connection();
        Connection l = new Connection();
        Connection r = new Connection();

        Circuit circuit = new Circuit();
        circuit.add(new Battery("Bat", batteryVoltage, p, n));
        circuit.add(new Resistor("R1", 150.0, p, l));
        circuit.add(new Resistor("R2", 50.0, p, r));
        circuit.add(new Capacitor("C3", 1.0, l, r));
        circuit.add(new Resistor("R4", 300.0, l, n));
        circuit.add(new Capacitor("C5", 0.75, r, n));
        circuit.simulate(iterations, prints, timeStep);
        circuit.deallocate();
    }

    public static void main(String[] args) {
        NumberFactory.setup(DoubleNumber.class);
        int iterations = 200000;
        int prints = 10;
        Num timeStep = NumberFactory.getInstance().create(0.01);
        Num batteryVoltage = NumberFactory.getInstance().create(24);
        runTest1(iterations, prints, timeStep, batteryVoltage);
        runTest2(iterations, prints, timeStep, batteryVoltage);
        runTest3(iterations, prints, timeStep, batteryVoltage);
    }
}