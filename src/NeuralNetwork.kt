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
        return weights.fold(inputs) { output, weightMatrix ->
            applyActivationFunction(matrixMath.dot(weightMatrix, output), activationFunction)
        }
    }

    private fun predictWithOutputs(inputs: Matrix) : MutableList<Matrix> {
        return weights.foldIndexed(mutableListOf(inputs)) { index, output, weightMatrix ->
            (output + applyActivationFunction(matrixMath.dot(weightMatrix, output[index]), activationFunction)).toMutableList()
        }
    }

    private fun calculateErrors(prediction : Matrix, targets: Matrix) : MutableList<Matrix>{
        return weights.foldRightIndexed(mutableListOf(matrixMath.subtract(targets, prediction))) { index, weightMatrix, output ->
            (output + matrixMath.dot(matrixMath.transpose(weightMatrix), output[weights.size - (index+1)])).toMutableList()
        }
    }

    private fun calculateDeltaWeights(errors : MutableList<Matrix>, outputs : MutableList<Matrix>) : MutableList<Matrix>{
        return List(weights.size) { index ->
            matrixMath.mult(
                matrixMath.dot(
                    matrixMath.hadamardDot(
                        errors[index], activationMath.applyDerivativeActivationFunction(
                            outputs[outputs.size-1-index], activationFunction
                        )
                    ), matrixMath.transpose(outputs[outputs.size-2-index])
                ), learningRate)
        }.toMutableList()
    }

    private fun addDeltasToWeightMatrices(deltaWeights : MutableList<Matrix>) : MutableList<Matrix>{
        return List(deltaWeights.size) { index: Int ->
            matrixMath.add(weights[index], deltaWeights[deltaWeights.size-1-index])
        }.toMutableList()
    }

    fun train(inputs: Matrix, targets: Matrix) {
        require(true) { IllegalArgumentException("Inputs and targets must be set!") }
        weights = addDeltasToWeightMatrices(calculateDeltaWeights(calculateErrors(predict(inputs), targets), predictWithOutputs(inputs)))
    }
}