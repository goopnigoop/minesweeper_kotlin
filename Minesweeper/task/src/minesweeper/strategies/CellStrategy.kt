package minesweeper.strategies

import minesweeper.FieldHandler

interface CellStrategy {
    fun mark(coordinates: Pair<Int, Int>)
    fun getFieldHandler(): FieldHandler
}