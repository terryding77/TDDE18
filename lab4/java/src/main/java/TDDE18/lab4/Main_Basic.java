package TDDE18.lab4;

import TDDE18.lab4.model.*;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("DuplicatedCode")
public class Main_Basic {
    public static void runExample(int iterations, int prints, double timeStep, double batteryVoltage) {
        Connection p = new Connection();
        Connection n = new Connection();
        List<Component> net = new ArrayList<>();
        net.add(new Battery("Bat", batteryVoltage, p, n));
        net.add(new Resistor("R1", 6.0, p, n));
        net.add(new Resistor("R2", 8.0, p, n));
        simulate(net, iterations, prints, timeStep);
        deallocate_components(net);
    }

    public static void runTest1(int iterations, int prints, double timeStep, double batteryVoltage) {
        Connection p = new Connection();
        Connection n = new Connection();
        Connection p124 = new Connection();
        Connection p23 = new Connection();

        List<Component> net = new ArrayList<>();
        net.add(new Battery("Bat", batteryVoltage, p, n));
        net.add(new Resistor("R1", 6.0, p, p124));
        net.add(new Resistor("R2", 4.0, p124, p23));
        net.add(new Resistor("R3", 8.0, p23, n));
        net.add(new Resistor("R4", 12.0, p124, n));
        simulate(net, iterations, prints, timeStep);
        deallocate_components(net);
    }


    public static void runTest2(int iterations, int prints, double timeStep, double batteryVoltage) {
        Connection p = new Connection();
        Connection n = new Connection();
        Connection l = new Connection();
        Connection r = new Connection();

        List<Component> net = new ArrayList<>();
        net.add(new Battery("Bat", batteryVoltage, p, n));
        net.add(new Resistor("R1", 150.0, p, l));
        net.add(new Resistor("R2", 50.0, p, r));
        net.add(new Resistor("R3", 100.0, l, r));
        net.add(new Resistor("R4", 300.0, l, n));
        net.add(new Resistor("R5", 250.0, r, n));
        simulate(net, iterations, prints, timeStep);
        deallocate_components(net);
    }

    public static void runTest3(int iterations, int prints, double timeStep, double batteryVoltage) {
        Connection p = new Connection();
        Connection n = new Connection();
        Connection l = new Connection();
        Connection r = new Connection();

        List<Component> net = new ArrayList<>();
        net.add(new Battery("Bat", batteryVoltage, p, n));
        net.add(new Resistor("R1", 150.0, p, l));
        net.add(new Resistor("R2", 50.0, p, r));
        net.add(new Capacitor("C3", 1.0, l, r));
        net.add(new Resistor("R4", 300.0, l, n));
        net.add(new Capacitor("C5", 0.75, r, n));
        simulate(net, iterations, prints, timeStep);
        deallocate_components(net);
    }

    private static void simulate(List<Component> net, int iterations, int prints, double timeStep) {
        int nextPrintRound = iterations / prints - 1;
        net.forEach(Component::printName);
        System.out.println();
        net.forEach(Component::printTitle);
        System.out.println();
        for (int i = 0; i < iterations; i++) {
            if (i == nextPrintRound) {
                net.forEach(Component::printValue);
                System.out.println();
                nextPrintRound += iterations / prints;
            }
            net.forEach(component -> component.simulate(timeStep));
        }
    }

    private static void deallocate_components(List<Component> net) {
        // for (Component component : net) {
        // java不需要析构做啥, jvm自己完成
        // }
    }

    public static void main(String[] args) {
//        runExample(10000, 10, 0.1, 24.0);

        int iterations = 200000;
        int prints = 10;
        double timeStep = 0.01;
        double batteryVoltage = 24;
        runTest1(iterations, prints, timeStep, batteryVoltage);
        runTest2(iterations, prints, timeStep, batteryVoltage);
        runTest3(iterations, prints, timeStep, batteryVoltage);
    }
}