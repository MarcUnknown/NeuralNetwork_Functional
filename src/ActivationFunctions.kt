import kotlin.math.exp
import kotlin.math.max

enum class ActivationFunctions{
    sigmoid,
    reLu,
    tanh,
    linear
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
            ActivationFunctions.sigmoid -> sigmoid
            ActivationFunctions.reLu -> reLu
            ActivationFunctions.tanh -> tanh
            ActivationFunctions.linear -> linear
        }
    }
}