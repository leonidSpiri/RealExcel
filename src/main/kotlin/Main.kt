import data.Cell
import data.ColumnList
import data.RowList
import utils.ParseRowColumn
import java.awt.*
import javax.swing.*
import kotlin.system.exitProcess


val rowList = RowList()
val tableList = mutableListOf<MutableList<JButton>>()
val columnHead = listOf("A", "B", "C", "D", "E", "F", "G")
val frame = JFrame("Real Excel")
val mainPanel = Panel(GridLayout(2, columnHead.size))
val panel = Panel(GridLayout(6, columnHead.size))
fun main() {
    createTable()
    setupToolsPanel()
    frame.add(mainPanel)
    frame.isVisible = true
    frame.setSize(500, 500)
    frame.jMenuBar = createMenuBar()
    frame.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
}

fun createMenuBar(): JMenuBar {
    val menuBar = JMenuBar()
    val fileMenu = JMenu("File")
    val openItem = JMenuItem("Open")
    val saveItem = JMenuItem("Save")
    val exitItem = JMenuItem("Exit")
    exitItem.addActionListener {
        exitProcess(0)
    }
    fileMenu.add(openItem)
    fileMenu.add(saveItem)
    fileMenu.add(exitItem)
    val aboutMenu = JMenu("About")
    val aboutItem = JMenuItem("About")
    aboutItem.addActionListener {
        JOptionPane.showMessageDialog(frame, "This is a simple Excel clone\n" +
                "Made by me, using Kotlin and Swing\n" +
                "https://github.com/leonidSpiri")
    }
    aboutMenu.add(aboutItem)
    menuBar.add(fileMenu)
    menuBar.add(aboutMenu)
    return menuBar
}

fun createTable() {
    for (i in 0..5) {
        val btnList = mutableListOf<JButton>()
        val column = ColumnList(i.toLong())
        columnHead.forEach { columnWord ->
            column.insertLast(x = columnWord, value = "$columnWord$i")
            val btn = JButton("$columnWord$i")
            btn.addActionListener {
                rowList.getColumnList(i.toLong())?.getCell(columnWord)
                    ?.let { cell -> showDialog(frame, cell.value, cell) }
                rowList.displayForward()
            }
            btnList.add(btn)
            panel.add(btn)
        }
        rowList.insertLast(column)
        tableList.add(i, btnList)
    }
    rowList.displayForward()
    mainPanel.add(panel)
}

fun setupToolsPanel() {
    val toolsPanel = Panel(GridLayout(4, 1))

    toolsPanel.add(JLabel("Поменять значения в ячейках (начало:конец:значение)"))
    val txtGetRange = JTextField(1)
    txtGetRange.addActionListener {
        val range = txtGetRange.text.uppercase().split(":")
        getRange(txtGetRange).forEach {
            it.value = range[2]
        }
        txtGetRange.minimumSize = Dimension(500, 500)
        rowList.displayForward()
        updateTable()
    }
    toolsPanel.add(txtGetRange)

    toolsPanel.add(JLabel("Посчитать сумму в ячейках (начало:конец)"))
    val txtFormula = JTextField(1)
    txtFormula.addActionListener {
        var summ = 0
        getRange(txtFormula).forEach {
            if (it.value.toIntOrNull() != null) {
                summ += it.value.toInt()
            }
        }
        txtFormula.minimumSize = Dimension(500, 500)
        txtFormula.text = summ.toString()
    }
    toolsPanel.add(txtFormula)


    mainPanel.add(toolsPanel)
}

fun updateTable() {
    for (i in 0..rowList.getSize()) {
        columnHead.forEach { columnWord ->
            val btn = tableList[i.toInt()][columnHead.indexOf(columnWord)]
            btn.text = rowList.getColumnList(i)?.getCell(columnWord)?.value
        }
    }
}

fun getRange(txt: JTextField): MutableList<Cell> {
    val range = txt.text.uppercase().split(":")
    val selectedRange = ParseRowColumn().parse(range[0], range[1])
    return rowList.getColumnRangeArray(
        yStart = selectedRange[0].first,
        yEnd = selectedRange[1].first,
        xStart = selectedRange[0].second,
        xEnd = selectedRange[1].second,
        columnHead = columnHead
    ).toMutableList()
}

fun showDialog(frame: JFrame, msg: String?, cell: Cell) {
    val dialog = Dialog(frame)
    dialog.setSize(100, 70)
    val textField = JTextField(1)
    textField.text = msg
    textField.addActionListener {
        dialog.dispose()
        cell.value = textField.text
        updateTable()
    }
    dialog.add(textField)
    dialog.isVisible = true
}