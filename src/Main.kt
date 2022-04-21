fun main(){
    val inputs = Matrix(mutableListOf(mutableListOf(0.4), mutableListOf(0.6)))
    val targets = Matrix(mutableListOf(mutableListOf(0.12345678912345678)))
    val matrixMath = MatrixMath()
    val neuralNetwork : NeuralNetwork = NeuralNetwork(2, mutableListOf(8, 32),1, ActivationFunctions.ReLu)
    println("Target: ${targets.getElements()[0][0]}")
    println("Value before training: ${neuralNetwork.predict(inputs)}")
    repeat(100) {
        neuralNetwork.train(inputs, targets)
    }
    println("Value after training: ${neuralNetwork.predict(inputs)}")
}
