package data

data class Cell(
    var next: Cell? = null,
    var previous: Cell? = null,
    val x: String,
    var y: Long,
    var value: String = "",
    val type: String = "text",
    val textColor: String = "#000000",
    val backgroundColor: String = "#FFFFFF",
    val fontSize: Int = 12,
    val fontStyle: String = "normal",
    val border: String = "none",
    val borderLeft: String = "none",
    val borderTop: String = "none",
    val borderRight: String = "none",
    val borderBottom: String = "none",
    val borderRadius: String = "none",
    val margin: Int = 0,
    val marginLeft: Int = 0,
    val marginTop: Int = 0,
    val marginRight: Int = 0,
    val marginBottom: Int = 0,
)