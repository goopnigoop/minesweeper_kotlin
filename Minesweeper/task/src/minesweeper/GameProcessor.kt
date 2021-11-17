package minesweeper

interface GameProcessor {
    fun processGame(coordinates: Pair<Int, Int>, type: CellType)
}