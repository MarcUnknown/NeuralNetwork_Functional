import java.util.Random

class NeuralNetwork(input_nodes: Int, hidden_nodes: Int, output_nodes: Int, activationFunction: ActivationFunctions) {
    private val weights_input_hidden: Matrix
    private val weights_hidden_output: Matrix
    private val learning_rate = 0.1
    private val matrixMath: MatrixMath
    private val activationMath : ActivationMath
    private var activationFunction : (Double) -> Double = { 0.0 }


    init {
        require(input_nodes > 0 && hidden_nodes > 0 && output_nodes > 0) { IllegalArgumentException("Nodes have to greater than 0!") }
        matrixMath = MatrixMath()
        activationMath = ActivationMath()
        weights_input_hidden = randomizeWeights(hidden_nodes, input_nodes)
        weights_hidden_output = randomizeWeights(output_nodes, hidden_nodes)
        this.activationFunction = (activationMath.getActivationFunction(activationFunction) as ((Double) -> Double)?)!!
    }

    private fun randomizeWeights(rows : Int, columns : Int) : Matrix{
        return Matrix(List(rows) {
            List(columns) {
                Random().nextGaussian()
            }.toMutableList()
        }.toMutableList())
    }

    private fun applyActivationFunction(matrix: Matrix, f : (Double) -> Double) : Matrix{
        return Matrix(List(matrix.getRows()) { rowIndex ->
            List(matrix.getColumns()) { columnIndex ->
                f(matrix.getElements()[rowIndex][columnIndex])
            }.toMutableList()
        }.toMutableList())
    }

    fun predict(inputs: Matrix?): Matrix {
        require(inputs != null) { IllegalArgumentException("Inputs must not be zero!") }
        val hidden_in = matrixMath.dot(weights_input_hidden, inputs)
        val hidden_out = applyActivationFunction(hidden_in, this.activationFunction)
        val output_in = matrixMath.dot(weights_hidden_output, hidden_out)
        return applyActivationFunction(output_in, this.activationFunction)
    }

    /*
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
     */
}