fun main(){
    val inputsNodes = 2
    val outputNodes = 1
    val hiddenLayers = mutableListOf(4,8)
    val activationFunctions = mutableListOf(
        ActivationFunctions.Sigmoid,
        ActivationFunctions.ReLu,
        ActivationFunctions.Tanh
    )
    val lossFunction = LossFunctions.MAE
    val neuralNetwork = NeuralNetwork(inputsNodes, hiddenLayers, outputNodes,
        activationFunctions,
        lossFunction)

    val inputs = mutableListOf(
        Matrix.of(arrayOf(
            arrayOf(0.0),
            arrayOf(0.0))
        ),

        Matrix.of(arrayOf(
            arrayOf(0.0),
            arrayOf(1.0))
        ),

        Matrix.of(arrayOf(
            arrayOf(1.0),
            arrayOf(0.0))
        ),

        Matrix.of(arrayOf(
            arrayOf(1.0),
            arrayOf(1.0))
        )
    )

    val targets = mutableListOf(
        Matrix.of(arrayOf(
            arrayOf(0.0))
        ),

        Matrix.of(arrayOf(
            arrayOf(1.0))
        ),

        Matrix.of(arrayOf(
            arrayOf(1.0))
        ),

        Matrix.of(arrayOf(
            arrayOf(0.0))
        )
    )

    print("Prediction before Training: ")
    inputs.forEach { print("${neuralNetwork.predict(it)} ") }

    var trainingTime = System.currentTimeMillis()
    for (j in 0 until 10){
        for (i in 0 until 200) {
            neuralNetwork.train(inputs[i % inputs.size], targets[i % inputs.size])
        }
        println()
        println("After Training with ${(j+1) * 200} examples: ")
        inputs.forEach { print("${neuralNetwork.predict(it)} ") }
    }
    trainingTime = System.currentTimeMillis() - trainingTime
    println()
    println("Time for training needed: ${trainingTime}ms")
}