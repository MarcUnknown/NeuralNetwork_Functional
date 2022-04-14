fun main(args: Array<String>){
    val neuralNetwork = NeuralNetwork(2,4,1)
    val inputs = Matrix(2,2)
    inputs.setElements(mutableListOf(mutableListOf(0.4), mutableListOf(0.6)))
    println(inputs)
}