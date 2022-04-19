import kotlin.math.exp
import kotlin.math.max

enum class ActivationFunctions{
    Sigmoid,
    ReLu,
    Tanh,
    Linear
}

class ActivationMath {
    private val sigmoid : (Double) -> Double = { 1 / (1 + exp(-it)) }
    private val reLu : (Double) -> Double = { max(0.0, it) }
    private val tanh : (Double) -> Double = { kotlin.math.tanh(it) }
    private val linear : (Double) -> Double = { it }

    private val dSigmoid : (Double) -> Double = { sigmoid(it) * (1 - sigmoid(it)) }
    private val dreLu : (Double) -> Double = { if (it < 0) 0.0 else 1.0 }
    private val dTanh : (Double) -> Double = { 1.0 - (tanh(it) * tanh(it)) }
    private val dLinear : (Double) -> Double = { 1.0 }

    fun getActivationFunction(activationFunction: ActivationFunctions) : (Double) -> Double {
        require(true) { IllegalArgumentException("Activation function needs to be set!") }
        return when (activationFunction){
            ActivationFunctions.Sigmoid -> sigmoid
            ActivationFunctions.ReLu -> reLu
            ActivationFunctions.Tanh -> tanh
            ActivationFunctions.Linear -> linear
        }
    }

    fun applyDerivativeActivationFunction(matrix: Matrix, activationFunction : (Double) -> Double) : Matrix{
        return Matrix(List(matrix.getRows()) { rowIndex->
            List(matrix.getColumns()) { columnIndex ->
                activationFunction(matrix.getElements()[rowIndex][columnIndex])
            }.toMutableList()
        }.toMutableList())
    }

}