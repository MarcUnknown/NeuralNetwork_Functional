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


    //y_hat = np.array([0.000, 0.166, 0.333])
    //y_true = np.array([0.000, 0.254, 0.998])

    val y_hat = Matrix.of(arrayOf(
        arrayOf(0.7),
        arrayOf(0.1),
        arrayOf(0.2))
    )
    val y_true = Matrix.of(arrayOf(
        arrayOf(1.0),
        arrayOf(0.0),
        arrayOf(0.0))
    )


    val lossFunctions = LossFunctionMath()
    var func = lossFunctions.getLossFunction(LossFunctions.RMSE)
    println(func(y_true, y_hat))
    func = lossFunctions.getLossFunction(LossFunctions.MAE)
    println(func(y_true, y_hat))
    func = lossFunctions.getLossFunction(LossFunctions.Categorical)
    println(func(y_true, y_hat))
}