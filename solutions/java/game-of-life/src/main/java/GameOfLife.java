class GameOfLife {

    record Universe(int[][] matrix) {
        int rows() {
            return matrix.length;
        }

        int cols() {
            return rows() > 0 ? matrix[0].length : 0;
        }

        int value(int row, int col) {
            return row >= 0 && row < rows() && col >= 0 && col < cols() ? matrix[row][col] : 0;
        }

        int neighbors(int i, int j) {
            var result = 0;
            for (int row = i - 1; row <= i + 1; row++) {
                for (int col = j - 1; col <= j + 1; col++) {
                    if (row == i && col == j) continue;
                    result += value(row, col);
                }
            }
            return result;
        }
    }

    public int[][] tick(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return matrix;
        }
        var universe = new Universe(matrix);
        var result = new int[universe.rows()][universe.cols()];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                result[i][j] = switch (universe.neighbors(i, j)) {
                    case 2 -> universe.value(i, j);
                    case 3 -> 1;
                    default -> 0;
                };
            }
        }
        return result;
    }
}
