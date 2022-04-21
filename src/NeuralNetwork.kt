import java.lang.StringBuilder
import java.util.*
import kotlin.math.sqrt

class NeuralNetwork(input_nodes: Int, hidden_layers: MutableList<Int>, output_nodes: Int, activationFunctions: MutableList<ActivationFunctions>) {
    private val matrixMath: MatrixMath
    private val activationMath : ActivationMath

    private val nodesLayers : MutableList<Int>
    private var weights: MutableList<Matrix> = mutableListOf()
    private val activationFunctions : MutableList<(Double) -> Double>

    private val learningRate = 0.1

    init {
        require(input_nodes > 0 && output_nodes > 0) { IllegalArgumentException("Nodes have to be greater than 0!") }
        matrixMath = MatrixMath()
        activationMath = ActivationMath()
        nodesLayers = (mutableListOf(input_nodes) + hidden_layers + mutableListOf(output_nodes)).toMutableList()
        weights = initWeights()
        this.activationFunctions = getActivationForLayers(activationFunctions)
    }

    private fun getActivationForLayers(activationFunctions : MutableList<ActivationFunctions>) : MutableList<(Double) -> Double>{
        return List(weights.size) { index: Int ->
            activationMath.getActivationFunction(activationFunctions[index])
        }.toMutableList()
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
        return weights.foldIndexed(inputs) { index, output, weightMatrix ->
            applyActivationFunction(matrixMath.dot(weightMatrix, output), activationFunctions[index])
        }
    }

    private fun predictWithOutputs(inputs: Matrix) : MutableList<Matrix> {
        return weights.foldIndexed(mutableListOf(inputs)) { index, output, weightMatrix ->
            (output + applyActivationFunction(matrixMath.dot(weightMatrix, output[index]), activationFunctions[index])).toMutableList()
        }
    }

    private fun calculateErrors(prediction : Matrix, targets: Matrix) : MutableList<Matrix>{
        return weights.foldRightIndexed(mutableListOf(matrixMath.subtract(targets, prediction))) {
                index, weightMatrix, output ->
            (output + matrixMath.dot(matrixMath.transpose(weightMatrix), output[weights.size - (index+1)])).toMutableList()
        }
    }

    private fun calculateDeltaWeights(errors : MutableList<Matrix>, outputs : MutableList<Matrix>) : MutableList<Matrix>{
        return List(weights.size) { index ->
            matrixMath.mult(
                matrixMath.dot(
                    matrixMath.hadamardDot(
                        errors[index], activationMath.applyDerivativeActivationFunction(
                            outputs[outputs.size-1-index], activationFunctions[index]
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

    private fun printLoss(predictions: Matrix, targets: Matrix, builder : StringBuilder){
        val differences = matrixMath.subtract(predictions, targets)
        val squared = matrixMath.hadamardDot(differences, differences)
        val mean = squared.getElements().flatMap { row -> row.map { element -> element } }.sum()
        builder.append("Loss value: " + sqrt(mean) + "\n")
    }

    fun train(inputs: Matrix, targets: Matrix, buffer : StringBuilder) {
        require(true) { IllegalArgumentException("Inputs and targets must be set!") }
        weights = addDeltasToWeightMatrices(calculateDeltaWeights(calculateErrors(predict(inputs), targets), predictWithOutputs(inputs)))
        printLoss(predict(inputs), targets, buffer)
    }
}