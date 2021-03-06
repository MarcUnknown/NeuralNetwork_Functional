import java.util.*

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

    companion object {
        fun of(elements : Array<Array<Double>>) : Matrix {
            require(!(elements.isEmpty() || elements[0].isEmpty())) { "Rows and columns have to be greater than 0!" }
            return Matrix(elements.map { row ->
                row.map { element ->
                    element
                }.toMutableList()
            }.toMutableList())
        }
    }

    override fun toString(): String {
        return elements.joinToString(separator = "\n") { elementsInRow -> elementsInRow.joinToString(separator = "\t") }
    }
}