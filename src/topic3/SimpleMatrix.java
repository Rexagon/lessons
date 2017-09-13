package topic3;

public class SimpleMatrix {
    public static void main(String[] args) {
        int[][] matrix = new int[5][5];
        for (int y = 0; y < 5; ++y) {
            for (int x = 0; x < 5; ++x) {
                matrix[x][y] = (y + 1) * 10 + x + 1;
            }
        }

        printMatrix(matrix);
        System.out.println();

        for (int i = 0; i < 5; ++i) {
            matrix[i][i] = 0;
        }

        printMatrix(matrix);
    }

    private static void printMatrix(int[][] matrix) {
        for (int y = 0; y < 5; ++y) {
            for (int x = 0; x < 5; ++x) {
                System.out.print(String.format("%02d ", matrix[x][y]));
            }

            System.out.println();
        }
    }
}
