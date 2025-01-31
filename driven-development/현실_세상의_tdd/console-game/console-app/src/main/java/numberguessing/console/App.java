package numberguessing.console;

import numberguessing.RandomGenerator;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        final var model = new AppModel(new RandomGenerator());
        final var sc = new Scanner(System.in);
        runLoop(model, sc);
        sc.close();
    }

    private static void runLoop(AppModel model, Scanner sc) {
        while (!model.isCompleted()) {
            System.out.println(model.flushOutput());
            model.processInput(sc.nextLine());
        }
    }
}
