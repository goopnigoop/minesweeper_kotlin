package minesweeper

class MinesweeperGame(
    private val dialogService: UserDialogService,
    private val gameProcessor: GameProcessor,
    private val fieldHandler: FieldHandler,
) {

    fun play() {
        setMinesQuantity()
        fieldHandler.printField()
        do {
            val (coordinates, type) = dialogService.askUserForCoordinatesAndCellType()
            gameProcessor.processGame(coordinates, type)
            fieldHandler.printField()
        } while (!fieldHandler.isFieldBlocked())

        println(
            if (!fieldHandler.isMined()) {
                "Congratulations! You found all the mines!"
            } else {
                "You stepped on a mine and failed!"
            }
        )
    }

    private fun setMinesQuantity() {
        fieldHandler.setMinesQuantity(dialogService.getFromUserMinesQuantityForField(fieldHandler.getFieldSize()))
    }
}