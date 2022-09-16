import androidx.compose.ui.window.application
import data.Cell
import data.ColumnList
import data.RowList

fun main() = application {
    /* Window(
         onCloseRequest = ::exitApplication,
         title = "Real Excel for Desktop",
         state = rememberWindowState(width = 1000.dp, height = 900.dp)
     ) {
         val count = remember { mutableStateOf(0) }
         MaterialTheme {
             Column(Modifier.fillMaxSize(), Arrangement.spacedBy(5.dp)) {
                 Button(modifier = Modifier.align(Alignment.CenterHorizontally),
                     onClick = {
                         count.value++
                     }) {
                     Text(if (count.value == 0) "Hello World" else "Clicked ${count.value}!")
                 }
                 Button(modifier = Modifier.align(Alignment.CenterHorizontally),
                     onClick = {
                         count.value = 0
                     }) {
                     Text("Reset")
                 }
             }
         }
     }*/

    val rowList = RowList()
    for (i in 0..5) {

        val column = ColumnList(i.toLong())
        listOf("A", "B", "C", "D", "E", "F", "G").forEach {
            column.insertLast(x = it, value = "$it$i")
        }
        rowList.insertLast(column)
    }
    var tempList = mutableListOf<Cell>()
    rowList.getColumnList(3)?.getCell("A")?.let { tempList.add(it) }
    rowList.getColumnList(3)?.getCell("B")?.let { tempList.add(it) }
    rowList.getColumnList(2)?.getCell("A")?.let { tempList.add(it) }
    rowList.getColumnList(2)?.getCell("B")?.let { tempList.add(it) }

    tempList = rowList.getColumnRangeArray(
        yStart = 2,
        yEnd = 3,
        xStart = "A",
        xEnd = "C",
        columnHead = listOf("A", "B", "C", "D", "E", "F", "G")
    ).toMutableList()
    tempList.forEach {
        it.value = "1"
    }
    rowList.displayForward()
}