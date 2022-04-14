class Matrix(rows: Int, columns: Int) {
    private val rows: Int
    private val columns: Int
    private var elements: MutableList<MutableList<Double>>

    init {
        require(!(rows < 1 || columns < 1)) { "Rows and columns have to be greater than 0!" }
        this.rows = rows
        this.columns = columns
        elements = mutableListOf()
    }

    fun setElements(elements : MutableList<MutableList<Double>>) {
        this.elements = elements
    }

    fun getElements() : MutableList<MutableList<Double>>{
        return elements
    }

    fun getColumns() : Int{
        return columns
    }

    fun getRows() : Int{
        return rows
    }

    override fun toString(): String {
        val result = StringBuilder()
        for (i in 0 until rows) {
            for (j in 0 until columns) {
                result.append(elements[i][j]).append("\t")
            }
            result.append("\n")
        }
        return result.toString()
    }
}