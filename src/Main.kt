fun main(args: Array<String>){
    val inputs = Matrix(mutableListOf(mutableListOf(0.4), mutableListOf(0.6)))
    val hadamard_test = Matrix(mutableListOf(mutableListOf(2.0), mutableListOf(2.0)))
    println(inputs)
    val matrixMath = MatrixMath()
    println(matrixMath.hadamardDot(inputs, hadamard_test))
}