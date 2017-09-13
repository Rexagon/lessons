package topic2;

import java.util.Scanner;

public class CalcMonth {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int year;
        while ((year = input.nextInt()) != 0) {
            System.out.println("Lengths of months:");

            try {
                for (int i = 1; i <= 12; ++i) {
                    System.out.println(String.format("\t%02d.%d: ", i, year) + countDays(i, year));
                }
            }
            catch (RuntimeException e) {
                System.out.println("Error:" + e.getMessage());
            }
        }
    }

    private static int countDays(int month, int year) {
        switch (month) {
            case 1:
                return 31;

            case 2:
                return isLeapYear(year) ? 29 : 28;

            case 3:
                return 31;

            case 4:
                return 30;

            case 5:
                return 31;

            case 6:
                return 30;

            case 7:
                return 31;

            case 8:
                return 31;

            case 9:
                return 30;

            case 10:
                return 31;

            case 11:
                return 30;

            case 12:
                return 31;

            default:
                throw new RuntimeException("wrong month number");
        }
    }

    private static boolean isLeapYear(int year) {
        return (year % 4 == 0) && (year % 100 != 0 || year % 400 == 0);
    }
}
