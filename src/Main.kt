fun main(){
    val inputs = Matrix(mutableListOf(mutableListOf(1.0), mutableListOf(0.0)))
    val targets = Matrix(mutableListOf(mutableListOf(0.07464)))
    val matrixMath = MatrixMath()
    val neuralNetwork : NeuralNetwork = NeuralNetwork(2,4,1, ActivationFunctions.reLu)
    println(neuralNetwork.predict(inputs))
    repeat(10000) {
        neuralNetwork.train(inputs, targets)
    }
    println(neuralNetwork.predict(inputs))
}
