package minesweeper

interface FieldHandler {
    fun getListOfCellsAround(cell: Pair<Int, Int>, fieldSize: Int): List<Pair<Int, Int>>
    fun getField(): Field
    fun ifQuantityUnexploredCellsEqualsMinesQuantity(): Boolean
    fun setMinedAndBlockField(x: Int, y: Int)
    fun generateMinesCoordinates(coordinatesToBeExcluded: Pair<Int, Int>)
    fun allMarkedCellsAreMines(): Boolean
    fun isCellWithMine(coordinates: Pair<Int, Int>): Boolean
    fun isMined(): Boolean
    fun getFieldCells(): List<MutableList<CellType>>
    fun printField()
    fun blockField()
    fun areMinesNotSet():Boolean
    fun getMinesCoordinates(): List<Pair<Int,Int>>
    fun getFieldSize(): Int
    fun isFieldBlocked(): Boolean
    fun setMinesQuantity(minesQuantityForField: Int)
}