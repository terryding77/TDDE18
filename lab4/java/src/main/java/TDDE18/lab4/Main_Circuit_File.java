package TDDE18.lab4;


import TDDE18.lab4.factory.CircuitFactory;
import TDDE18.lab4.factory.NumberFactory;
import TDDE18.lab4.model.Circuit;
import TDDE18.lab4.nubmer.DoubleNumber;
import TDDE18.lab4.nubmer.Num;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;

@SuppressWarnings("DuplicatedCode")
public class Main_Circuit_File {
    public static String getFilePath(String circuitFile) {
        URL resource = Main_Circuit_File.class.getClassLoader().getResource(circuitFile);
        if (resource == null) {
            return null;
        }
        return resource.getPath();
    }

    public static void runFileType1(CircuitFactory cf, String circuitFile, int iterations, int prints, Num timeStep, Num batteryVoltage) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Circuit circuit = cf.createCircuitByFileType1(getFilePath(circuitFile), batteryVoltage);
        circuit.simulate(iterations, prints, timeStep);
        circuit.deallocate();
    }

    public static void runFileType2(CircuitFactory cf, String circuitFile, int iterations, int prints, Num timeStep, Num batteryVoltage) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Circuit circuit = cf.createCircuitByFileType2(getFilePath(circuitFile), batteryVoltage);
        circuit.simulate(iterations, prints, timeStep);
        circuit.deallocate();
    }

    public static void main(String[] args) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        NumberFactory.setup(DoubleNumber.class);
        int iterations = 200000;
        int prints = 10;
        Num timeStep = NumberFactory.getInstance().create(0.01);
        Num batteryVoltage = NumberFactory.getInstance().create(24);

        CircuitFactory cf = new CircuitFactory();
        // 这种格式读取更稳定
        runFileType1(cf, "filetype1/test1.circuit", iterations, prints, timeStep, batteryVoltage);
        runFileType1(cf, "filetype1/test2.circuit", iterations, prints, timeStep, batteryVoltage);
        runFileType1(cf, "filetype1/test3.circuit", iterations, prints, timeStep, batteryVoltage);

        // 这种格式人类可读性好
        runFileType2(cf, "filetype2/test1.circuit", iterations, prints, timeStep, batteryVoltage);
        runFileType2(cf, "filetype2/test2.circuit", iterations, prints, timeStep, batteryVoltage);
        runFileType2(cf, "filetype2/test3.circuit", iterations, prints, timeStep, batteryVoltage);
    }
}