class MatrixMath {
    fun dot(matrix_1: Matrix, matrix_2: Matrix): Matrix {
        require(!(matrix_1.getColumns() !== matrix_2.rows)) { "Rows does not match columns!" }
        val result = Matrix(matrix_1.rows, matrix_2.getColumns())
        for (i in 0 until matrix_1.rows) {
            for (j in 0 until matrix_2.getColumns()) {
                for (k in 0 until matrix_1.getColumns()) {
                    result.getElements().get(i)[j] += matrix_1.getElements().get(i).get(k) * matrix_2.getElements()
                        .get(k).get(j)
                }
            }
        }
        return result
    }

    fun hadamard_dot(matrix_1: Matrix, matrix_2: Matrix): Matrix {
        require(!(matrix_1.rows != matrix_2.rows || matrix_1.getColumns() !== matrix_2.getColumns())) { "Matrices must have same dimensions!" }
        val result = Matrix(matrix_1.rows, matrix_1.getColumns())
        for (i in 0 until matrix_1.rows) {
            for (j in 0 until matrix_1.getColumns()) {
                result.getElements().get(i)[j] =
                    matrix_1.getElements().get(i).get(j) * matrix_2.getElements().get(i).get(j)
            }
        }
        return result
    }

    fun transpose(matrix: Matrix): Matrix {
        val transposed_Matrix = Matrix(matrix.getColumns(), matrix.rows)
        for (i in 0 until matrix.rows) {
            for (j in 0 until matrix.getColumns()) {
                transposed_Matrix.getElements().get(j)[i] = matrix.getElements().get(i).get(j)
            }
        }
        return transposed_Matrix
    }

    fun subtract(matrix_1: Matrix, matrix_2: Matrix): Matrix {
        require(!(matrix_1.rows != matrix_2.rows || matrix_1.getColumns() !== matrix_2.getColumns())) { "Matrices must have same dimensions!" }
        val result = Matrix(matrix_1.rows, matrix_1.getColumns())
        for (i in 0 until matrix_1.rows) {
            for (j in 0 until matrix_1.getColumns()) {
                result.getElements().get(i)[j] =
                    matrix_1.getElements().get(i).get(j) - matrix_2.getElements().get(i).get(j)
            }
        }
        return result
    }

    fun subtract(constant: Double, matrix: Matrix): Matrix {
        val result = Matrix(matrix.rows, matrix.getColumns())
        for (i in 0 until matrix.rows) {
            for (j in 0 until matrix.getColumns()) {
                result.getElements().get(i)[j] = 1 - matrix.getElements().get(i).get(j)
            }
        }
        return result
    }

    fun add(matrix_1: Matrix, matrix_2: Matrix): Matrix {
        require(!(matrix_1.rows != matrix_2.rows || matrix_1.getColumns() !== matrix_2.getColumns())) { "Matrices must have same dimensions!" }
        val result = Matrix(matrix_1.rows, matrix_1.getColumns())
        for (i in 0 until matrix_1.rows) {
            for (j in 0 until matrix_1.getColumns()) {
                result.getElements().get(i)[j] =
                    matrix_1.getElements().get(i).get(j) + matrix_2.getElements().get(i).get(j)
            }
        }
        return result
    }

    fun mult(matrix: Matrix, scalar: Double): Matrix {
        val result = Matrix(matrix.rows, matrix.getColumns())
        for (i in 0 until matrix.rows) {
            for (j in 0 until matrix.getColumns()) {
                result.getElements().get(i)[j] = matrix.getElements().get(i).get(j) * scalar
            }
        }
        return result
    }
}