package data

class RowList {
    private var first: Row? = null
    private var last: Row? = null
    private var y: Long = 0

    private fun isEmpty() = first == null

    fun insertLast(columnList: ColumnList) {
        val newLink = Row(columnList = columnList, y = y++)
        if (isEmpty())
            first = newLink
        else {
            last?.next = newLink
            newLink.previous = last
        }
        last = newLink
    }

    fun getColumnList(y: Long): ColumnList? {
        var current = first
        while (current?.y != y) {
            if (current?.next == null)
                return null
            else
                current = current.next
        }
        return current.columnList
    }

    fun getColumnRangeArray(
        yStart: Long,
        xStart: String,
        yEnd: Long,
        xEnd: String,
        columnHead: List<String>
    ): Array<Cell> {
        val tempArray = mutableListOf<Cell>()
        for (i in yStart..yEnd) {
            val columnList = getColumnList(i)
            if (columnList != null) {
                val xStartIndex = columnHead.indexOf(xStart)
                val xEndIndex = columnHead.indexOf(xEnd)
                for (j in xStartIndex..xEndIndex) {
                    val cell = columnList.getCell(columnHead[j])
                    if (cell != null)
                        tempArray.add(cell)
                }
            }
        }
        return tempArray.toTypedArray()
    }

    fun displayForward() {
        var current = first
        while (current != null) {
            println(current.columnList.displayForward())
            current = current.next
        }
        println("--------------------")
    }

    fun getSize() = last?.y ?: 0
}