package topic2;

public class FiveSevenNine {
    public static void main(String[] args) {
        String[] names = {"five", "seven", "nine"};

        for (int n = 1; n < 20; ++n) {
            for (int i = 0; i < names.length; ++i) {
                if (n % (5 + i * 2) == 0) {
                    System.out.println(n + " / " + names[i] + " = 0");
                }
            }
        }
    }
}
