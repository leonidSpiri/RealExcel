package data

class ColumnList(private var y: Long) {
    private var first: Cell? = null
    private var last: Cell? = null

    private fun isEmpty() = first == null

    fun insertLast(value: String = "", x: String) {
        val newLink = Cell(x = x, y = y, value = value)
        if (isEmpty())
            first = newLink
        else {
            last?.next = newLink
            newLink.previous = last
        }
        last = newLink
    }

    fun displayForward(): String {
        var current = first
        var string = ""
        while (current != null) {
            string += (current.value + "|")
            current = current.next
        }
        string += "\n"
        return string
    }

    fun getCell(x: String): Cell? {
        var current = first
        while (current?.x != x) {
            current = current?.next
            if (current == null)
                return null
        }
        return current
    }
}