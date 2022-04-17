fun main(args: Array<String>){

    val inputs = Matrix(mutableListOf(mutableListOf(0.4, 0.6)))
    val matrixMath = MatrixMath()
    val neuralNetwork : NeuralNetwork = NeuralNetwork(1,4,1, ActivationFunctions.sigmoid)
    print(neuralNetwork.predict(inputs))
}
