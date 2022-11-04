package TDDE18.lab4.model;

import TDDE18.lab4.nubmer.Num;

import java.util.ArrayList;
import java.util.List;

public class Circuit {
    List<Component> net;

    public Circuit() {
        net = new ArrayList<>();
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
    public void simulate(int iterations, int prints, Num timeStep) {
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
