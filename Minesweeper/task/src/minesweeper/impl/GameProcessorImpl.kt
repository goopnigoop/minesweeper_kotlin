package minesweeper.impl

import minesweeper.CellType
import minesweeper.GameProcessor
import minesweeper.strategies.CellStrategy
import java.util.*

class GameProcessorImpl(private val strategyMap: EnumMap<CellType, CellStrategy>) : GameProcessor {
    override fun processGame(coordinates: Pair<Int, Int>, type: CellType) {
        val cellStrategy = strategyMap[type] ?: throw IllegalArgumentException("Type is unknown")
        cellStrategy.mark(coordinates)
    }
}