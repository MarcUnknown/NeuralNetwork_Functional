class Matrix(rows: Int, columns: Int) {
    val rows: Int
    private val columns: Int
    private val elements: Array<DoubleArray>

    fun setElements(rows: Array<Any>) {
        for (i in rows.indices) {
            elements[i] = rows[i] as DoubleArray
        }
    }

    fun setRow(rowIndex: Int, row: DoubleArray) {
        for (columnIndex in row.indices) elements[rowIndex][columnIndex] = row[columnIndex]
    }

    fun getRow(index: Int): DoubleArray {
        return elements[index]
    }

    fun setWeight(row: Int, column: Int, weight: Double) {
        elements[row][column] = weight
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

    init {
        require(!(rows < 1 || columns < 1)) { "Rows and columns have to be greater than 0!" }
        this.rows = rows
        this.columns = columns
        elements = Array(rows) { DoubleArray(columns) }
    }
}