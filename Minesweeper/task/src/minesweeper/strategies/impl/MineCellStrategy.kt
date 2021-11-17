package minesweeper.strategies.impl

import minesweeper.CellType
import minesweeper.CellType.MINE
import minesweeper.CellType.NOT_DEFINED
import minesweeper.FieldHandler
import minesweeper.strategies.CellStrategy

class MineCellStrategy(private val fieldHandler: FieldHandler) : CellStrategy {
    companion object {
        val canNotBeMarked = { fieldCells: List<MutableList<CellType>>, x: Int, y: Int ->
            !setOf(MINE, NOT_DEFINED).contains(fieldCells[x][y])
        }
    }

    override fun mark(coordinates: Pair<Int, Int>) {
        val x = coordinates.first
        val y = coordinates.second

        val fieldCells = fieldHandler.getFieldCells()
        if (canNotBeMarked(fieldCells, x, y)) {
            return
        }

        fieldCells[x][y] =
            if (fieldCells[x][y] == MINE) {
                NOT_DEFINED
            } else {
                if (fieldHandler.allMarkedCellsAreMines()) {
                    fieldHandler.blockField()
                }
                MINE
            }
    }

    override fun getFieldHandler() = fieldHandler
}