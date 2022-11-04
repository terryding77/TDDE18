package TDDE18.lab4.model;

import TDDE18.lab4.factory.ComponentFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Circuit {
    List<Component> net;

    public Circuit() {
        net = new ArrayList<>();
    }

    public Circuit(String circuitFile, double batteryVoltage) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        BufferedReader in = new BufferedReader(new FileReader(circuitFile)); //②第一层，FileReader，第二层BufferedReader。
        String str;
        int connectionNum = 0, componentNum = 0;
        if ((str = in.readLine()) != null) {
            String[] split = str.split(",");
            if (2 == split.length) {
                connectionNum = Integer.parseInt(split[0]);
                componentNum = Integer.parseInt(split[1]);
            }
        }
        Connection[] connections = new Connection[connectionNum];
        for (int i = 0; i < connectionNum; i++) {
            connections[i] = new Connection();
        }
        net = new ArrayList<>();
        for (int i = 0; i < componentNum; i++) {
            if ((str = in.readLine()) != null) {
                net.add(ComponentFactory.createComponentByFileType1(str, batteryVoltage, connections));
            }
        }
    }

    public Circuit(double batteryVoltage, String circuitFile) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        this();
        BufferedReader in = new BufferedReader(new FileReader(circuitFile)); //②第一层，FileReader，第二层BufferedReader。
        String line;
        Map<String, Connection> connectionMap = new HashMap<>();
        while ((line = in.readLine()) != null) {
            if ("".equals(line.strip())) {
                continue;
            }
            Component component = ComponentFactory.createComponentByFileType2(line, batteryVoltage, connectionMap);
            add(component);
        }
    }

    public void add(Component component) {
        net.add(component);
    }

    public void deallocate() {
        // for (Component component : net) {
        // java不需要析构做啥, jvm自己完成
        // }
    }

    @SuppressWarnings("DuplicatedCode")
    public void simulate(int iterations, int prints, double timeStep) {
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

}
