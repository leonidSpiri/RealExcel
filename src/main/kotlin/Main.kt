import androidx.compose.material.Button
import data.ColumnList
import data.RowList
import java.awt.Dialog
import java.awt.Frame
import java.awt.GridLayout
import java.awt.Panel
import java.awt.Window
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.WindowConstants
import javax.swing.text.View


fun main() {
    val frame = JFrame("window")
    val btns = mutableListOf<MutableList<JButton>>()
    val columnHead = listOf("A", "B", "C", "D", "E", "F", "G")
    val panel = Panel(GridLayout(6, columnHead.size))
    val rowList = RowList()

    for (i in 0..5) {
        val btnList = mutableListOf<JButton>()
        val column = ColumnList(i.toLong())
        columnHead.forEach {columnWord ->
            column.insertLast(x = columnWord, value = "$columnWord$i")
            val btn = JButton("$columnWord$i")
            btn.addActionListener {
                btn.text = "X"
                btn.isEnabled = false
                rowList.getColumnList(i.toLong())?.getCell(columnWord)?.value = "X"
                rowList.displayForward()
                showDialog(frame, "You clicked $columnWord$i")
            }

            btnList.add(btn)
            panel.add(btn)
        }
        rowList.insertLast(column)
        btns.add(i, btnList)
    }
    rowList.displayForward()
    frame.add(panel)
    frame.isVisible = true
    frame.setSize(500, 500);
    frame.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
}

fun showDialog(frame: JFrame, msg: String?) {
    val dialog = Dialog(frame)
    dialog.setSize(300, 300)
    val btn = JButton(msg)
    btn.addActionListener {
        dialog.dispose()
    }
    dialog.add(btn)
    dialog.isVisible = true
}
/*fun main() {
    val rowList = RowList()
    val columnHead = listOf("A", "B", "C", "D", "E", "F", "G")
    for (i in 0..5) {

        val column = ColumnList(i.toLong())
        columnHead.forEach {
            column.insertLast(x = it, value = "$it$i")
        }
        rowList.insertLast(column)
    }

    //rowList.getColumnList(3)?.getCell("A")?.let { tempList.add(it) }
    val selectedRange = ParseRowColumn().parse("A1", "G15")
    val tempList = rowList.getColumnRangeArray(
        yStart = selectedRange[0].first,
        yEnd = selectedRange[1].first,
        xStart = selectedRange[0].second,
        xEnd = selectedRange[1].second,
        columnHead = columnHead
    ).toMutableList()

    tempList.forEach {
        it.value = "new"
    }
    rowList.displayForward()
}*/