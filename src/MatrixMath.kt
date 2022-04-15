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

        return Matrix(elements)

        //return Matrix(List(matrix_1.getRows()) { matrix_1_rowIndex ->
        //    List(matrix_2.getColumns()) { matrix_2_columnIndex ->
        //        List(matrix_1.getColumns()) { matrix_1_columnIndex ->
        //            matrix_1.getElements()[matrix_1_rowIndex][matrix_1_columnIndex] + matrix_2.getElements()[matrix_1_columnIndex][matrix_2_columnIndex]
        //        }.toMutableList()
        //    }.toMutableList()
        //}.toMutableList())

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