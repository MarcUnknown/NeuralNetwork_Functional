fun main(args: Array<String>){

    val inputs = Matrix(mutableListOf(mutableListOf(1.0, 2.0), mutableListOf(3.0, 4.0)))
    val dot_test = Matrix(mutableListOf(mutableListOf(3.0), mutableListOf(4.0)))
    val matrixMath = MatrixMath()
    println(matrixMath.dot(inputs, dot_test))
}
