package TDDE18.lab4;


import TDDE18.lab4.factory.NumberFactory;
import TDDE18.lab4.nubmer.DoubleNumber;
import TDDE18.lab4.processor.CommandLineArgsProcessor;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@SuppressWarnings("DuplicatedCode")
public class Main_Circuit_File_Cmd_Args {
    public static void main(String[] args) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        NumberFactory.setup(DoubleNumber.class);
        CommandLineArgsProcessor commandLineArgsProcessor = new CommandLineArgsProcessor(args);
        commandLineArgsProcessor.execute();
    }
}