package topic3;

public class MatrixTranspose {
    public static void main(String[] args) {
        int[][] matrix = new int[5][5];
        for (int y = 0; y < 5; ++y) {
            for (int x = 0; x < 5; ++x) {
                matrix[x][y] = (y + 1) * 10 + x + 1;
            }
        }

        printMatrix(matrix);

        for (int y = 0; y < 5; ++y) {
            for (int x = y + 1; x < 5; ++x) {
                int temp = matrix[y][x];
                matrix[y][x] = matrix[x][y];
                matrix[x][y] = temp;
            }
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
        System.out.println();
    }
}
