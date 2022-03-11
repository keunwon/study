package numberguessing.console;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

    }

    private static void runLoop(AppModel model, Scanner sc) {
        while (!model.isCompleted()) {
            System.out.println(model.flushOutput());
            model.processInput(sc.nextLine());
        }
    }
}
