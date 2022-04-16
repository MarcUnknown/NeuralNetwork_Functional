import java.util.stream.Collector
import kotlin.streams.asSequence
import kotlin.streams.toList

class MatrixMath {
    fun dot(matrix_1: Matrix, matrix_2: Matrix): Matrix {
        require(matrix_1.getColumns() == matrix_2.getRows()) { "Rows does not match columns!" }
        return Matrix(List(matrix_1.getRows()) { matrix_1_rowIndex ->
            List(matrix_2.getColumns()) { matrix_2_columnIndex ->
                List(matrix_1.getColumns()) { matrix_1_columnIndex ->
                    matrix_1.getElements()[matrix_1_rowIndex][matrix_1_columnIndex] * matrix_2.getElements()[matrix_1_columnIndex][matrix_2_columnIndex]
                }.toMutableList().fold(0.0) {acc, d ->
                    acc + d}
            }.toMutableList()
        }.toMutableList())
    }

    fun hadamardDot(matrix_1: Matrix, matrix_2: Matrix): Matrix {
        require(!(matrix_1.getRows() != matrix_2.getRows() || matrix_1.getColumns() != matrix_2.getColumns())) { "Matrices must have the same dimensions!" }

        return Matrix(List(matrix_1.getRows()) { rowIndex ->
            List(matrix_1.getColumns()) { columnIndex -> matrix_1.getElements()[rowIndex][columnIndex] * matrix_2.getElements()[rowIndex][columnIndex]}
                .toMutableList()
        }.toMutableList())
    }

    fun transpose(matrix: Matrix): Matrix {
        return Matrix(List(matrix.getColumns()) { rowIndex ->
            List(matrix.getRows()) { columnIndex ->
                matrix.getElements()[columnIndex][rowIndex] }
                .toMutableList()
        }.toMutableList())
    }

    fun subtract(matrix_1: Matrix, matrix_2: Matrix): Matrix {
        require(!(matrix_1.getRows() != matrix_2.getRows() || matrix_1.getColumns() != matrix_2.getColumns())) { "Matrices must have same dimensions!" }

        return Matrix(List(matrix_1.getRows()) { rowIndex ->
            List(matrix_1.getColumns()) { columnIndex ->
                matrix_1.getElements()[rowIndex][columnIndex] - matrix_2.getElements()[rowIndex][columnIndex]
            }.toMutableList()
        }.toMutableList())
    }

    fun subtract(constant: Double, matrix: Matrix): Matrix {
        return Matrix(List(matrix.getRows()) { rowIndex ->
            List(matrix.getColumns()) { columnIndex ->
                constant - matrix.getElements()[rowIndex][columnIndex]
            }.toMutableList()
        }.toMutableList())
    }

    fun add(matrix_1: Matrix, matrix_2: Matrix): Matrix {
        require(!(matrix_1.getRows() != matrix_2.getRows() || matrix_1.getColumns() != matrix_2.getColumns())) { "Matrices must have same dimensions!" }

        return Matrix(List(matrix_1.getRows()) { rowIndex ->
            List(matrix_1.getColumns()) { columnIndex ->
                matrix_1.getElements()[rowIndex][columnIndex] + matrix_2.getElements()[rowIndex][columnIndex]
            }.toMutableList()
        }.toMutableList())
    }

    fun mult(matrix: Matrix, scalar: Double): Matrix {
        return Matrix(matrix.getElements().map { rowWithElements ->
            rowWithElements.map { columnElement -> columnElement * scalar }
                .toMutableList()}
            .toMutableList())
    }
}