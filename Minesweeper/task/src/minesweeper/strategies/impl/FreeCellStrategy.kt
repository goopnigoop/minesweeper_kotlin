package minesweeper.strategies.impl

import minesweeper.CellType
import minesweeper.CellType.*
import minesweeper.FieldHandler
import minesweeper.strategies.CellStrategy
import java.util.*

class FreeCellStrategy(private val fieldHandler: FieldHandler) : CellStrategy {

    override fun mark(coordinates: Pair<Int, Int>) {
        val x = coordinates.first
        val y = coordinates.second

        if (fieldHandler.isCellWithMine(coordinates)) {
            fieldHandler.setMinedAndBlockField(x, y)
            return
        }

        if (fieldHandler.areMinesNotSet()) {
            fieldHandler.generateMinesCoordinates(coordinates)
        }

        val fieldCells = fieldHandler.getFieldCells()
        val minesCoordinates = fieldHandler.getMinesCoordinates()
        val fieldSize = fieldHandler.getFieldSize()

        val queue = LinkedList<Pair<Int, Int>>()
        queue.add(coordinates)
        while (queue.isNotEmpty()) {
            val cell = queue.pop()
            val currentX = cell.first
            val currentY = cell.second

            val listOfCellsAround = fieldHandler.getListOfCellsAround(cell, fieldSize)
            val countOfMinesAround = listOfCellsAround.count(minesCoordinates::contains)

            fieldCells[currentX][currentY] =
                if (countOfMinesAround == 0) {
                    addAllCellsAroundToQueue(queue, cell)
                    FREE
                } else {
                    CellType.getBySign(countOfMinesAround.digitToChar())
                }
        }

        if (fieldHandler.ifQuantityUnexploredCellsEqualsMinesQuantity()) {
            fieldHandler.blockField()
        }
    }

    override fun getFieldHandler() = fieldHandler

    private fun addAllCellsAroundToQueue(queue: LinkedList<Pair<Int, Int>>, coordinates: Pair<Int, Int>) {
        val fieldCells = fieldHandler.getFieldCells()
        this.fieldHandler.getListOfCellsAround(coordinates, fieldHandler.getFieldSize())
            .filter { fieldCells[it.first][it.second] == NOT_DEFINED || fieldCells[it.first][it.second] == MINE }
            .forEach(queue::add)
    }
}