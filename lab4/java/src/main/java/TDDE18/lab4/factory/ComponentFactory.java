package TDDE18.lab4.factory;

import TDDE18.lab4.model.*;
import TDDE18.lab4.nubmer.Num;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("DuplicatedCode")
public class ComponentFactory {
    public static final Map<String, Class<? extends Component>> componentMap = new HashMap<>();

    static {
        register("Battery", Battery.class);
        register("Capacitor", Capacitor.class);
        register("Resistor", Resistor.class);
    }

    public static void register(String className, Class<? extends Component> componentClass) {
        componentMap.put(className, componentClass);
    }

    public static Component createComponentByFileType1(String line, Num batteryVoltage, Connection[] connections) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Component component = null;
        String[] split = line.split(",");
        if (5 == split.length) {
            String name = split[0];
            String type = split[1];
            Num value = NumberFactory.getInstance().create(split[2]);
            int beginConnectionIndex = Integer.parseInt(split[3]);
            int endConnectionIndex = Integer.parseInt(split[4]);
            if ("Battery".equals(type)) {
                // Bat,Battery,24,0,1
                value = batteryVoltage;
            }
            Constructor<? extends Component> constructor = componentMap.get(type).getConstructor(
                    String.class, Num.class, Connection.class, Connection.class);
            component = constructor.newInstance(name, value, connections[beginConnectionIndex], connections[endConnectionIndex]);
        }
        return component;
    }

    public static Component createComponentByFileType2(String line, Num batteryVoltage, Map<String, Connection> connectionMap) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Component component = null;
        String[] split = line.split(",");
        if (5 == split.length) {
            String name = split[0];
            String type = split[1];
            Num value =  NumberFactory.getInstance().create(split[2]);
            String beginName = split[3];
            String endName = split[4];
            if ("Battery".equals(type)) {
                // Bat,Battery,24,0,1
                value = batteryVoltage;
            }
            Constructor<? extends Component> constructor = componentMap.get(type).getConstructor(
                    String.class, Num.class, Connection.class, Connection.class);
            component = constructor.newInstance(name, value,
                    connectionMap.computeIfAbsent(beginName, key -> new Connection()),
                    connectionMap.computeIfAbsent(endName, key -> new Connection())
            );

        }
        return component;
    }
}
