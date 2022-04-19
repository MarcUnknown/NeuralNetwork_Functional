fun main(){
    val inputs = Matrix(mutableListOf(mutableListOf(1.0), mutableListOf(0.0)))
    val targets = Matrix(mutableListOf(mutableListOf(0.123456789)))
    val matrixMath = MatrixMath()
    val neuralNetwork : NeuralNetwork = NeuralNetwork(2, mutableListOf(4),1, ActivationFunctions.Sigmoid)
    println(neuralNetwork.predict(inputs))
    //println(neuralNetwork.predict(inputs))
    //repeat(100) {
    //    neuralNetwork.train(inputs, targets)
    //}
    //println(neuralNetwork.predict(inputs))
}
