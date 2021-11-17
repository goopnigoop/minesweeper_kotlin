package minesweeper

interface UserDialogService {
    fun getFromUserMinesQuantityForField(fieldSize: Int): Int
    fun askUserForCoordinatesAndCellType(): Pair<Pair<Int, Int>, CellType>
}