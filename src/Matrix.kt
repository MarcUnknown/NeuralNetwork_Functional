class Matrix(private val elements: MutableList<MutableList<Double>>) {

    init {
        require(!(elements.size < 1 || elements[0].size < 1)) { "Rows and columns have to be greater than 0!" }
    }

    fun getElements() : MutableList<MutableList<Double>>{
        return elements
    }

    fun getColumns() : Int{
        return elements[0].size
    }

    fun getRows() : Int{
        return elements.size
    }

    override fun toString(): String {
        return elements.joinToString(separator = "\n") { elementsInRow -> elementsInRow.joinToString(separator = "\t") }
    }
}