package MatrixProcessing;

import java.util.Scanner;

public class MatrixProcessing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Додавання матриць\n2. Множення матриці на константу\n3. Множення матриц " +
                    "\n4.Транспонування матриці\n5. Обчислення визначника\n6. Обернена матриця\n0. Вихід");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addMatrices();
                    break;
                case 2:
                    multiplyByConstant();
                    break;
                case 3:
                    multiplyMatrices();
                    break;
                case 4:
                    transposeMatrix();
                    break;
                case 5:
                    calculateDeterminant();
                    break;
                case 6:
                    inverseMatrix();
                    break;
                case 0:
                    System.out.println("Ви вийщли с программи. До побачення!");
                    System.exit(0);
                default:
                    System.out.println("Невірний вибір. Будь ласка, введіть правильну опцію.");
            }
        }
    }

    private static void inverseMatrix() {
        int[][] matrix = readMatrix();

        if (isSquare(matrix)) {
            int determinant = calculateDeterminant(matrix);
            if (determinant != 0) {
                double[][] inverse = calculateInverse(matrix);
                System.out.println("Результат:");
                printMatrix(inverse);
            } else {
                System.out.println("Ця матриця не має оберненої.");
            }
        } else {
            System.out.println("ПОМИЛКА: Обернена матриця може бути обчислена лише для квадратної матриці.");
        }
    }

    private static double[][] calculateInverse(int[][] matrix) {
        int n = matrix.length;
        int[][] augmentedMatrix = new int[n][2 * n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                augmentedMatrix[i][j] = matrix[i][j];
            }
            augmentedMatrix[i][i + n] = 1;
        }

        augmentedMatrix = rowReduce(augmentedMatrix);

        double[][] inverse = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                inverse[i][j] = (double) augmentedMatrix[i][j + n];
            }
        }

        return inverse;
    }

    private static int[][] rowReduce(int[][] matrix) {
        int rowCount = matrix.length;
        int colCount = matrix[0].length;

        for (int i = 0; i < rowCount; i++) {
            int pivotColumn = -1;
            for (int j = 0; j < colCount; j++) {
                if (matrix[i][j] != 0) {
                    pivotColumn = j;
                    break;
                }
            }
            if (pivotColumn != -1) {
                int pivotValue = matrix[i][pivotColumn];
                for (int j = 0; j < colCount; j++) {
                    matrix[i][j] /= pivotValue;
                }

                for (int k = 0; k < rowCount; k++) {
                    if (k != i) {
                        int factor = matrix[k][pivotColumn];
                        for (int j = 0; j < colCount; j++) {
                            matrix[k][j] -= factor * matrix[i][j];
                        }
                    }
                }
            }
        }

        return matrix;
    }

    private static void calculateDeterminant() {
        int[][] matrix = readMatrix();

        if (isSquare(matrix)) {
            int determinant = calculateDeterminant(matrix);
            System.out.println("The result is: " + determinant);
        } else {
            System.out.println("ERROR: Determinant can only be calculated for a square matrix.");
        }
    }

    private static int calculateDeterminant(int[][] matrix) {
        int n = matrix.length;

        if (n == 1) {
            return matrix[0][0];
        }

        if (n == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }

        int determinant = 0;
        for (int i = 0; i < n; i++) {
            determinant += Math.pow(-1, i) * matrix[0][i] * calculateDeterminant(getSubmatrix(matrix, 0, i));
        }

        return determinant;
    }

    private static int[][] getSubmatrix(int[][] matrix, int rowToRemove, int colToRemove) {
        int rows = matrix.length - 1;
        int cols = matrix[0].length - 1;

        int[][] submatrix = new int[rows][cols];

        for (int i = 0, newRow = 0; i < matrix.length; i++) {
            if (i == rowToRemove) {
                continue;
            }

            for (int j = 0, newCol = 0; j < matrix[i].length; j++) {
                if (j == colToRemove) {
                    continue;
                }

                submatrix[newRow][newCol] = matrix[i][j];
                newCol++;
            }

            newRow++;
        }

        return submatrix;
    }

    private static boolean isSquare(int[][] matrix) {
        return matrix.length == matrix[0].length;
    }

    private static int[][] readMatrix() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введить розмір матриці: ");
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();

        int[][] matrix = new int[rows][cols];

        System.out.println("Введить матрицю:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        return matrix;
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    private static void printMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            for (double value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    private static void addMatrices() {
        int[][] matrixA = readMatrix();
        int[][] matrixB = readMatrix();

        if (canAdd(matrixA, matrixB)) {
            int[][] result = addMatrices(matrixA, matrixB);
            System.out.println("Результат:");
            printMatrix(result);
        } else {
            System.out.println("ПОМИЛКА: Матриці можна додавати лише якщо вони мають однакові розміри.");
        }
    }

    private static int[][] addMatrices(int[][] matrixA, int[][] matrixB) {
        int rows = matrixA.length;
        int cols = matrixA[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = matrixA[i][j] + matrixB[i][j];
            }
        }
        return result;
    }
    private static boolean canAdd(int[][] matrixA, int[][] matrixB) {
        return matrixA.length == matrixB.length && matrixA[0].length == matrixB[0].length;
    }
    private static void multiplyByConstant() {
        int[][] matrix = readMatrix();
        System.out.print("Введить константу: ");
        int constant = new Scanner(System.in).nextInt();

        int[][] result = multiplyByConstant(matrix, constant);
        System.out.println("Результат:");
        printMatrix(result);
    }
    private static int[][] multiplyByConstant(int[][] matrix, int constant) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = constant * matrix[i][j];
            }
        }

        return result;
    }
    private static void multiplyMatrices() {
        int[][] matrixA = readMatrix();
        int[][] matrixB = readMatrix();

        if (canMultiply(matrixA, matrixB)) {
            int[][] result = multiplyMatrices(matrixA, matrixB);
            System.out.println("The result is:");
            printMatrix(result);
        } else {
            System.out.println("ПОМИЛКА: Матриці не можна помножити. Кількість стовпців у першій матриці повинна бути рівною кількості рядків у другій матриці.");
        }
    }
    private static int[][] multiplyMatrices(int[][] matrixA, int[][] matrixB) {
        int rowsA = matrixA.length;
        int colsA = matrixA[0].length;
        int colsB = matrixB[0].length;
        int[][] result = new int[rowsA][colsB];

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    result[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }

        return result;
    }
    private static boolean canMultiply(int[][] matrixA, int[][] matrixB) {
        return matrixA[0].length == matrixB.length;
    }
    private static void transposeMatrix() {
        int[][] matrix = readMatrix();
        int[][] result = transposeMatrix(matrix);
        System.out.println("The result is:");
        printMatrix(result);
    }
    private static int[][] transposeMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][i] = matrix[i][j];
            }
        }

        return result;
    }
}
