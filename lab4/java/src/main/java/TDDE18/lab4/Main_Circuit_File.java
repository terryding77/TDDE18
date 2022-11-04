package TDDE18.lab4;


import TDDE18.lab4.model.Circuit;

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

    public static void runFileType1(String circuitFile, int iterations, int prints, double timeStep, double batteryVoltage) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Circuit circuit = new Circuit(getFilePath(circuitFile), batteryVoltage);
        circuit.simulate(iterations, prints, timeStep);
        circuit.deallocate();
    }

    public static void runFileType2(String circuitFile, int iterations, int prints, double timeStep, double batteryVoltage) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Circuit circuit = new Circuit(batteryVoltage, getFilePath(circuitFile));
        circuit.simulate(iterations, prints, timeStep);
        circuit.deallocate();
    }

    public static void main(String[] args) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        int iterations = 200000;
        int prints = 10;
        double timeStep = 0.01;
        double batteryVoltage = 24;

        // 这种格式读取更稳定
         runFileType1("filetype1/test1.circuit", iterations, prints, timeStep, batteryVoltage);
         runFileType1("filetype1/test2.circuit", iterations, prints, timeStep, batteryVoltage);
         runFileType1("filetype1/test3.circuit", iterations, prints, timeStep, batteryVoltage);

        // 这种格式人类可读性好
        runFileType2("filetype2/test1.circuit", iterations, prints, timeStep, batteryVoltage);
        runFileType2("filetype2/test2.circuit", iterations, prints, timeStep, batteryVoltage);
        runFileType2("filetype2/test3.circuit", iterations, prints, timeStep, batteryVoltage);
    }
}