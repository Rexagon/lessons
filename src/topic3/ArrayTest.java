package topic3;

import java.util.Random;

public class ArrayTest {
    public static void main(String[] args) {
        Random random = new Random();

        int[] array = new int[random.nextInt(11) + 5];
        for (int i = 0; i < array.length; ++i) {
            array[i] = random.nextInt(21) - 10;
        }

        printArray(array);

        for (int i = 0; i < array.length / 2; ++i) {
            int temp = array[array.length - 1 - i];
            array[array.length - 1 - i] = array[i];
            array[i] = temp;
        }

        printArray(array);
    }

    private static void printArray(int[] array) {
        for (int i = 0; i < array.length; ++i) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
