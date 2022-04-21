import java.lang.StringBuilder

fun main(){
    val inputsNodes = 2
    val outputNodes = 1
    val hiddenLayers = mutableListOf(4)
    val activationFunctions = mutableListOf(
        ActivationFunctions.Sigmoid,
        ActivationFunctions.Sigmoid,
    )
    val neuralNetwork : NeuralNetwork = NeuralNetwork(inputsNodes, hiddenLayers, outputNodes, activationFunctions)

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

    val buffer : StringBuilder = StringBuilder()
    var trainingTime = System.currentTimeMillis()
    repeat(10000) {
        for (i in 0 .. 4) {
            neuralNetwork.train(inputs[i%4], targets[i%4], buffer)
        }
    }
    trainingTime = System.currentTimeMillis() - trainingTime
    println(buffer)
    println("Time for training needed: ${trainingTime}ms")

    var correct = 0.0
    for (i in 0 until 4){
        val prediction = if (neuralNetwork.predict(inputs[i % 4]).getElements()[0][0] > 0.5) 1.0 else 0.0
        if (targets[i%4].getElements()[0][0] == prediction)
            correct++
    }
    println("Accuracy: ${(correct / 4) * 100}%")
}