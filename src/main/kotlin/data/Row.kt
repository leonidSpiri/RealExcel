package data

data class Row(
    var next: Row? = null,
    var previous: Row? = null,
    val y: Long,
    var columnList: ColumnList
)
