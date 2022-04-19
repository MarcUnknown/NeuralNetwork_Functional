import java.util.Random

class NeuralNetwork(input_nodes: Int, hidden_layers: MutableList<Int>, output_nodes: Int, activationFunction: ActivationFunctions) {
    private var weights: MutableList<Matrix> = mutableListOf()
    private val nodesLayers : MutableList<Int>
    private var activationFunction : (Double) -> Double
    private val learningRate = 0.1
    private val activationMath : ActivationMath
    private val matrixMath: MatrixMath

    init {
        require(input_nodes > 0 && output_nodes > 0) { IllegalArgumentException("Nodes have to be greater than 0!") }
        matrixMath = MatrixMath()
        activationMath = ActivationMath()
        nodesLayers = (mutableListOf(input_nodes) + hidden_layers + mutableListOf(output_nodes)).toMutableList()
        weights = initWeights()
        this.activationFunction = activationMath.getActivationFunction(activationFunction)
    }

    private fun initWeights() : MutableList<Matrix> {
        return List(nodesLayers.size-1) { index ->
            randomizeWeights(nodesLayers[index+1], nodesLayers[index])
        }.toMutableList()
    }

    private fun randomizeWeights(rows : Int, columns : Int) : Matrix {
        return Matrix(List(rows) {
            List(columns) {
                Random().nextGaussian()
            }.toMutableList()
        }.toMutableList())
    }

    private fun applyActivationFunction(matrix: Matrix, activationFunction : (Double) -> Double) : Matrix{
        return Matrix(List(matrix.getRows()) { rowIndex ->
            List(matrix.getColumns()) { columnIndex ->
                activationFunction(matrix.getElements()[rowIndex][columnIndex])
            }.toMutableList()
        }.toMutableList())
    }

    fun predict(inputs: Matrix): Matrix {
        require(true) { IllegalArgumentException("Inputs must not be zero!") }
        return weights.fold(inputs) { output , weightMatrix ->
            applyActivationFunction(matrixMath.dot(weightMatrix, output), activationFunction)
        }
    }

    /*
    fun train(inputs: Matrix, targets: Matrix) {
        require(true) { IllegalArgumentException("Inputs and targets must be set!") }
        val hidden_in = matrixMath.dot(weights_input_hidden, inputs)
        val hidden_out = applyActivationFunction(hidden_in, activationFunction)
        val output_in = matrixMath.dot(weights_hidden_output, hidden_out)
        val output_out = applyActivationFunction(output_in, activationFunction)
        val output_errors = matrixMath.subtract(targets, output_out)
        val hidden_errors = matrixMath.dot(matrixMath.transpose(weights_hidden_output), output_errors)
        val delta_weights_hidden_output = matrixMath.mult(
            matrixMath.dot(
                matrixMath.hadamardDot(
                    output_errors,
                    activationMath.applyDerivativeActivationFunction(output_out, activationFunction)
                ),
                matrixMath.transpose(hidden_out)
            ), learningRate
        )
        val delta_weights_input_hidden = matrixMath.mult(
            matrixMath.dot(
                matrixMath.hadamardDot(
                    hidden_errors,
                    activationMath.applyDerivativeActivationFunction(hidden_out, activationFunction)
                ),
                matrixMath.transpose(inputs)
            ), learningRate
        )
        weights_input_hidden = matrixMath.add(weights_input_hidden, delta_weights_input_hidden)
        weights_hidden_output = matrixMath.add(weights_hidden_output, delta_weights_hidden_output)
    }
     */
}