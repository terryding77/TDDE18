package TDDE18.lab4.processor;

import TDDE18.lab4.Main_Circuit_File_Cmd_Args;
import TDDE18.lab4.factory.CircuitFactory;
import TDDE18.lab4.factory.NumberFactory;
import TDDE18.lab4.model.Circuit;
import TDDE18.lab4.nubmer.Num;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandLineArgsProcessor {
    int iterations;
    int prints;
    Num timeStep;
    Num batteryVoltage;
    List<String> circuitFiles;

    public CommandLineArgsProcessor(String[] args) {
        if (args.length < 5) {
            System.err.println("参数数量不足, 提供迭代次数、打印次数、时间间隔、电池电量、电路文件列表");
            System.exit(-1);
        }
        iterations = Integer.parseInt(args[0]);
        prints = Integer.parseInt(args[1]);
        timeStep = NumberFactory.getInstance().create(args[2]);
        batteryVoltage = NumberFactory.getInstance().create(args[3]);
        circuitFiles = new ArrayList<>(Arrays.asList(args).subList(4, args.length));
    }

    private static String getFilePath(String circuitFile) {
        URL resource = Main_Circuit_File_Cmd_Args.class.getClassLoader().getResource(circuitFile);
        if (resource == null) {
            return null;
        }
        return resource.getPath();
    }

    public void execute() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        for (String circuitFile : circuitFiles) {
            executeOneFile(circuitFile);
        }
    }

    private void executeOneFile(String circuitFile) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        CircuitFactory cf = new CircuitFactory();
        Circuit circuit = cf.createCircuitByFileType2(getFilePath(circuitFile), batteryVoltage);
        circuit.simulate(iterations, prints, timeStep);
        circuit.deallocate();
    }
}
