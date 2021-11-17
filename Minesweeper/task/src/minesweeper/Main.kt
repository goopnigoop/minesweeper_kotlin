package minesweeper

import minesweeper.impl.FieldHandlerImpl
import minesweeper.impl.GameProcessorImpl
import minesweeper.impl.UserDialogServiceImpl
import minesweeper.strategies.CellStrategy
import minesweeper.strategies.impl.FreeCellStrategy
import minesweeper.strategies.impl.MineCellStrategy
import java.util.*

fun main() {
    val game = initializeGame()
    game.play()
}

private fun initializeGame(): MinesweeperGame {
    val fieldHandler = FieldHandlerImpl(Field(9))

    val strategyMap: EnumMap<CellType, CellStrategy> = EnumMap<CellType, CellStrategy>(CellType::class.java)
        .apply {
            this[CellType.FREE] = FreeCellStrategy(fieldHandler)
            this[CellType.MINE] = MineCellStrategy(fieldHandler)
        }
    return MinesweeperGame(UserDialogServiceImpl(), GameProcessorImpl(strategyMap), fieldHandler)
}





