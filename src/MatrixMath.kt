class MatrixMath {
    fun dot(matrix_1: Matrix, matrix_2: Matrix): Matrix {
        require(matrix_1.getColumns() == matrix_2.getRows()) { "Rows does not match columns!" }
        val elements = mutableListOf(mutableListOf<Double>())
        for (i in 0 until matrix_1.getRows()) {
            for (j in 0 until matrix_2.getColumns()) {
                for (k in 0 until matrix_1.getColumns()) {
                    elements[i][j] += matrix_1.getElements()[i][k] * matrix_2.getElements()[k][j]
                }
            }
        }

        /*
        return Matrix(List(matrix_1.getRows()) { rowMatrix1_Index ->
            List(matrix_2.getColumns()) { columnMatrix2_Index ->
                matrix_1.getElements().mapIndexed( {columnMatrix1_Index, elements -> matrix_1.getElements()[rowMatrix1_Index][columnMatrix1_Index] * matrix_2.getElements()[columnMatrix1_Index][columnMatrix2_Index] })
            }.toMutableList()
        }.toMutableList()
        )
        // matrix_1.getElements()[].map { columnMatrix1_Index -> matrix_1.getElements()[rowMatrix1_Index][columnMatrix1_Index] * matrix_2.getElements()[columnMatrix1_Index][columnMatrix2_Index] }
        */
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
            List(matrix.getRows()) { columnIndex -> matrix.getElements()[columnIndex][rowIndex] }
                .toMutableList()
        }.toMutableList())
    }

    // fun subtract(matrix_1: Matrix, matrix_2: Matrix): Matrix {
    //     require(!(matrix_1.getRows() != matrix_2.getRows() || matrix_1.getColumns() !== matrix_2.getColumns())) { "Matrices must have same dimensions!" }
    //     val result = Matrix(matrix_1.getRows(), matrix_1.getColumns())
    //     for (i in 0 until matrix_1.getRows()) {
    //         for (j in 0 until matrix_1.getColumns()) {
    //             result.getElements().get(i)[j] =
    //                 matrix_1.getElements().get(i).get(j) - matrix_2.getElements().get(i).get(j)
    //         }
    //     }
    //     return result
    // }
//
    // fun subtract(constant: Double, matrix: Matrix): Matrix {
    //     val result = Matrix(matrix.getRows(), matrix.getColumns())
    //     for (i in 0 until matrix.getRows()) {
    //         for (j in 0 until matrix.getColumns()) {
    //             result.getElements().get(i)[j] = 1 - matrix.getElements().get(i).get(j)
    //         }
    //     }
    //     return result
    // }
//
    // fun add(matrix_1: Matrix, matrix_2: Matrix): Matrix {
    //     require(!(matrix_1.getRows() != matrix_2.getRows() || matrix_1.getColumns() !== matrix_2.getColumns())) { "Matrices must have same dimensions!" }
    //     val result = Matrix(matrix_1.getRows(), matrix_1.getColumns())
    //     for (i in 0 until matrix_1.getRows()) {
    //         for (j in 0 until matrix_1.getColumns()) {
    //             result.getElements().get(i)[j] =
    //                 matrix_1.getElements().get(i).get(j) + matrix_2.getElements().get(i).get(j)
    //         }
    //     }
    //     return result
    // }

    fun mult(matrix: Matrix, scalar: Double): Matrix {
        return Matrix(matrix.getElements().map { rowWithElements ->
            rowWithElements.map { columnElement -> columnElement * scalar }
                .toMutableList()}
            .toMutableList())
    }
}