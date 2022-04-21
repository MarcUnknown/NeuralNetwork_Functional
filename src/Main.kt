fun main(){
    val matrixMath = MatrixMath()
    val neuralNetwork : NeuralNetwork = NeuralNetwork(2, mutableListOf(2,4,8,16,32),1, ActivationFunctions.Sigmoid)

    val inputs = mutableListOf(Matrix(mutableListOf(mutableListOf(0.0), mutableListOf(0.0))),
        Matrix(mutableListOf(mutableListOf(0.0), mutableListOf(1.0))), Matrix(mutableListOf(mutableListOf(1.0), mutableListOf(0.0))),
        Matrix(mutableListOf(mutableListOf(1.0), mutableListOf(1.0))))

    val targets = mutableListOf(Matrix(mutableListOf(mutableListOf(0.0))),
        Matrix(mutableListOf(mutableListOf(1.0))), Matrix(mutableListOf(mutableListOf(1.0))),
        Matrix(mutableListOf(mutableListOf(0.0))))

    val trainingTime = System.currentTimeMillis()
    repeat(1000) {
        for (i in 0 .. 50) {
            neuralNetwork.train(inputs[i%4], targets[i%4])
        }
    }
    println("Time for training needed: ${System.currentTimeMillis() - trainingTime}ms")

    var correct = 0.0
    for (i in 0 until 4){
        val prediction = if (neuralNetwork.predict(inputs[i % 4]).getElements()[0][0] > 0.5) 1.0 else 0.0
        if (targets[i%4].getElements()[0][0] == prediction)
            correct++
    }
    println("Accuracy: ${(correct / 4) * 100}%")
}