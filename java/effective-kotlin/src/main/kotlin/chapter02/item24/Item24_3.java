package chapter02.item24;

public class Item24_3 {

    public static void main(String[] args) {
        Integer[] numbers = {1, 4, 2, 1};
        Object[] objects = numbers;
        objects[2] = "B";
    }
}
