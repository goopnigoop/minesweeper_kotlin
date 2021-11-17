package minesweeper

enum class CellType(val sign: Char) {
    FREE('/'),
    MINE('*'),
    MINED('X'),
    NOT_DEFINED('.'),
    ONE('1'),
    TWO('2'),
    THREE('3'),
    FOUR('4'),
    FIVE('5'),
    SIX('6'),
    SEVEN('7'),
    EIGHT('8');

    companion object {
        fun getBySign(sign: Char): CellType {
            return values().first { it.sign == sign }
        }
    }

    override fun toString(): String {
        return sign.toString()
    }
}