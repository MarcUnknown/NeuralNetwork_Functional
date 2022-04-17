import kotlin.math.exp
import kotlin.math.max

enum class ActivationFunctions{
    sigmoid,
    reLu,
    tanh,
    linear
}

class ActivationMath {
    val sigmoid : (Double) -> Double = { 1 / (1 + exp(-it)) }
    val reLu : (Double) -> Double = { max(0.0,it) }
    val tanh : (Double) -> Double = { kotlin.math.tanh(it) }
    val linear : (Double) -> Double = { it }

    val dSigmoid : (Double) -> Double = { sigmoid(it) * (1 - sigmoid(it)) }
    val dreLu : (Double) -> Double = { if (it < 0) 0.0 else 1.0 }
    val dTanh : (Double) -> Double = { 1.0 - (tanh(it) * tanh(it)) }
    val dLinear : (Double) -> Double = { 1.0 }

    fun getActivationFunction(activationFunction: ActivationFunctions) : ((Double) -> Double?)? {
        require(true) { IllegalArgumentException("Activation function needs to be set!") }
        return when (activationFunction){
            ActivationFunctions.sigmoid -> sigmoid
            ActivationFunctions.reLu -> reLu
            ActivationFunctions.tanh -> tanh
            ActivationFunctions.linear -> linear
        }
    }
}