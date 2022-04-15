fun main(args: Array<String>){
    val inputs = Matrix(mutableListOf(mutableListOf(0.4), mutableListOf(0.6)))
    val hadamard_test = Matrix(mutableListOf(mutableListOf(2.0), mutableListOf(18.0)))
    val matrixMath = MatrixMath()
    println(matrixMath.subtract(1.0, hadamard_test))
}