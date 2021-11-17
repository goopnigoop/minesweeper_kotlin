package minesweeper

import minesweeper.CellType.NOT_DEFINED

class Field(val size: Int) {
    val cells: List<MutableList<CellType>>
    var isBlocked: Boolean = false
    var minesQuantity: Int = 0
    var minesCoordinates: MutableList<Pair<Int, Int>> = mutableListOf()

    init {
        cells = generateEmptyField()
    }

    private fun generateEmptyField() = List(size) { MutableList(size) { NOT_DEFINED } }


}