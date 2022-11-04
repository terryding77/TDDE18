package TDDE18.lab4.factory;

import TDDE18.lab4.model.Circuit;
import TDDE18.lab4.model.Component;
import TDDE18.lab4.model.Connection;
import TDDE18.lab4.nubmer.Num;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("DuplicatedCode")
public class CircuitFactory {

    public Circuit createCircuitByFileType1(String circuitFile, Num batteryVoltage) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Circuit circuit = new Circuit();
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
        for (int i = 0; i < componentNum; i++) {
            if ((str = in.readLine()) != null) {
                circuit.add(ComponentFactory.createComponentByFileType1(str, batteryVoltage, connections));
            }
        }
        return circuit;
    }

    public Circuit createCircuitByFileType2(String circuitFile, Num batteryVoltage) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Circuit circuit = new Circuit();
        BufferedReader in = new BufferedReader(new FileReader(circuitFile)); //②第一层，FileReader，第二层BufferedReader。
        String line;
        Map<String, Connection> connectionMap = new HashMap<>();
        while ((line = in.readLine()) != null) {
            if ("".equals(line.strip())) {
                continue;
            }
            Component component = ComponentFactory.createComponentByFileType2(line, batteryVoltage, connectionMap);
            circuit.add(component);
        }
        return circuit;
    }
}
