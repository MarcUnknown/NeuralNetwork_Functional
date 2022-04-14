/*
class NeuralNetwork(input_nodes: Int, hidden_nodes: Int, output_nodes: Int) {
    private var weights_input_hidden: Matrix
    private var weights_hidden_output: Matrix
    var learning_rate = 0.1
    private val matrixMath: MatrixMath
    private fun fillMatrixWithWeights(matrix: Matrix) {
        val random = Random()
        //Arrays.stream(matrix.getElements()).forEach { row -> Arrays.fill(row, random.nextGaussian()) }
    }

    fun predict(inputs: Matrix?): Matrix {
        val hidden_in = matrixMath.dot(weights_input_hidden, inputs!!)
        val hidden_out = applySigmoid(hidden_in)
        val output_in = matrixMath.dot(weights_hidden_output, hidden_out)
        return applySigmoid(output_in)
    }

    fun train(inputs: Matrix?, targets: Matrix?) {
        val hidden_in = matrixMath.dot(weights_input_hidden, inputs!!)
        val hidden_out = applySigmoid(hidden_in)
        val output_in = matrixMath.dot(weights_hidden_output, hidden_out)
        val output_out = applySigmoid(output_in)
        val output_errors = matrixMath.subtract(targets!!, output_out)
        val hidden_errors = matrixMath.dot(matrixMath.transpose(weights_hidden_output), output_errors)
        val delta_weights_hidden_output = matrixMath.mult(
            matrixMath.dot(
                matrixMath.hadamard_dot(
                    output_errors,
                    matrixMath.hadamard_dot(output_out, matrixMath.subtract(1.0, output_out))
                ),
                matrixMath.transpose(hidden_out)
            ), learning_rate
        )
        val delta_weights_input_hidden = matrixMath.mult(
            matrixMath.dot(
                matrixMath.hadamard_dot(
                    hidden_errors,
                    matrixMath.hadamard_dot(hidden_out, matrixMath.subtract(1.0, hidden_out))
                ),
                matrixMath.transpose(inputs)
            ), learning_rate
        )
        weights_input_hidden = matrixMath.add(weights_input_hidden, delta_weights_input_hidden)
        weights_hidden_output = matrixMath.add(weights_hidden_output, delta_weights_hidden_output)
    }

    private fun applySigmoid(matrix: Matrix): Matrix {
        val result = Matrix(matrix.getRows(), matrix.getColumns())
        //Arrays.stream(matrix.getElements()).flatMapToDouble { row -> Arrays.stream(sigmoidForRow(row)) }.toArray()
        //result.setElements((Object[]) Arrays.stream(matrix.getElements()).peek(row -> Arrays.stream(row).toArray())
        return result
    }

    private fun sigmoidForRow(row: DoubleArray): DoubleArray {
        return Arrays.stream(row).map { x: Double -> sigmoid(x) }.toArray()
    }

    private fun sigmoid(x: Double): Double {
        return 1 / (1 + Math.exp(-x))
    }

    init {
        weights_input_hidden = Matrix(hidden_nodes, input_nodes)
        weights_hidden_output = Matrix(output_nodes, hidden_nodes)
        fillMatrixWithWeights(weights_input_hidden)
        fillMatrixWithWeights(weights_hidden_output)
        matrixMath = MatrixMath()
    }
}
 */