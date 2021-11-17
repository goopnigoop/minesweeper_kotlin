package minesweeper.impl

import minesweeper.CellType
import minesweeper.Field
import minesweeper.FieldHandler
import kotlin.random.Random

class FieldHandlerImpl(private val field: Field) : FieldHandler {

    override fun getField() = field
    override fun getFieldSize() = field.size
    override fun isFieldBlocked() = field.isBlocked
    override fun getFieldCells(): List<MutableList<CellType>> = field.cells
    override fun setMinesQuantity(minesQuantityForField: Int) {
        field.minesQuantity = minesQuantityForField
    }

    override fun areMinesNotSet() = field.minesCoordinates.isEmpty()
    override fun getMinesCoordinates() = field.minesCoordinates
    override fun isMined() = field.cells.flatten().any { it == CellType.MINED }
    override fun isCellWithMine(coordinates: Pair<Int, Int>) = field.minesCoordinates.contains(coordinates)

    override fun getListOfCellsAround(cell: Pair<Int, Int>, fieldSize: Int): List<Pair<Int, Int>> {
        val x = cell.first
        val y = cell.second
        return listOf(
            Pair(x.dec(), y),
            Pair(x.inc(), y),
            Pair(x, y.inc()),
            Pair(x, y.dec()),
            Pair(x.dec(), y.dec()),
            Pair(x.dec(), y.inc()),
            Pair(x.inc(), y.inc()),
            Pair(x.inc(), y.dec()),
        ).filter { it.first in 0 until fieldSize && it.second in 0 until fieldSize }
    }

    override fun ifQuantityUnexploredCellsEqualsMinesQuantity(): Boolean {
        return field.cells.flatten().count { it == CellType.NOT_DEFINED } == field.minesCoordinates.size
    }

    override fun setMinedAndBlockField(x: Int, y: Int) {
        field.cells[x][y] = CellType.MINED
        field.isBlocked = true
    }

    override fun generateMinesCoordinates(coordinatesToBeExcluded: Pair<Int, Int>) {
        field.minesCoordinates = generateSequence {
            with(Random) {
                Pair(nextInt(field.size), nextInt(field.size))
            }
        }.filter { it != coordinatesToBeExcluded }
            .distinct()
            .take(field.minesQuantity)
            .toMutableList()
    }

    override fun allMarkedCellsAreMines(): Boolean {
        val allMinesAreMarked = {
            field.minesCoordinates.all { field.cells[it.first][it.second] == CellType.MINE }
        }

        val allUnexploredMarkedAsMines = {
            field.cells.flatten().count { it == CellType.MINE } == field.minesCoordinates.size
        }

        return allUnexploredMarkedAsMines() && allMinesAreMarked()
    }

    override fun printField() {
        println(
            """| │123456789│
               |—│—————————│""".trimMargin()
        )
        field.cells.forEachIndexed { index, mutableList ->
            println(mutableList.joinToString("", "${index.inc()}|", "|"))
        }
        println("—│—————————│")
    }

    override fun blockField() {
        field.isBlocked = true
    }
}