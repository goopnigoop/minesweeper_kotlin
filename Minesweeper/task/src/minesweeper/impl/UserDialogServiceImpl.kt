package minesweeper.impl

import minesweeper.CellType
import minesweeper.UserDialogService

class UserDialogServiceImpl : UserDialogService {
    override fun getFromUserMinesQuantityForField(fieldSize: Int): Int {
        var isOk = true
        var mines: Int
        val fieldCells = fieldSize * fieldSize
        do {
            print("How many mines do you want on the field? > ")
            mines = readLine()!!.toInt()

            if (mines > fieldCells) {
                println("The quantity of mines cannot be more then field size: $fieldSize")
                isOk = false
            }
        } while (!isOk)
        return mines
    }

    override fun askUserForCoordinatesAndCellType(): Pair<Pair<Int, Int>, CellType> {
        print("Set/unset mines marks or claim a cell as free: > ")
        val (y, x, cellType) = readLine()!!.split(" ")
        return Pair(Pair(x.toInt().dec(), y.toInt().dec()), CellType.valueOf(cellType.uppercase()))
    }

}