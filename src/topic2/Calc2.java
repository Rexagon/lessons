package topic2;

import java.util.Scanner;

public class Calc2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();

        double result = 1;
        double temp = 1;

        for (int i = 1; i <= n; ++i) {
            temp *= 1.0 / i;
            result *= (temp + 5 * Math.pow(i, 3));
        }

        System.out.println(result);
    }
}
