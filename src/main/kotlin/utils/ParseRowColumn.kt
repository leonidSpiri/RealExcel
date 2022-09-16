package utils

class ParseRowColumn {
    fun parse(start: String, end: String): List<Pair<Long, String>> {
        var yStart = ""
        var xStart = ""
        var yEnd = ""
        var xEnd = ""

        start.forEachIndexed { _, c ->
            if (c.isDigit())
                yStart += c.toString()
            else
                xStart += c
        }

        end.forEachIndexed { _, c ->
            if (c.isDigit())
                yEnd += c.toString()
            else
                xEnd += c
        }
        return listOf(Pair(yStart.toLong(), xStart), Pair(yEnd.toLong(), xEnd))
    }
}