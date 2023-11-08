class Board(var cells: Array<Array<Char>> = arrayOf(emptyArray(), emptyArray(), emptyArray())) {
    var iter = 0

    companion object {
        var i = 0
        var mass = emptyArray<Char>()
        var size = 3
        fun stringToArray(string: String): Array<Char> {
            while (i < size) {
                mass += string.substring(0..size)[i]
                i++
            }
            return mass
        }
    }

    constructor(cells: Array<Array<Char>>, string: String) : this(cells) {
        for (i in cells.indices) {
            cells[i] += stringToArray(string)
        }
    }

    fun printField() {
        for (row in cells) println(row.contentToString())
    }


    fun getOrNull(point: Point): Unit? {
        return if (point.x > size || point.y > size)
            println("Таких координат тут нет!")
        else null
    }


    operator fun get(point: Point): Char {
        if (iter % 2 == 0) cells[point.x][point.y] = 'X'
        else cells[point.x][point.y] = '0'
        return cells[point.x][point.y]
    }

    fun setAndCopy(point: Point, c: Char) {
        cells[point.x][point.y] = c
        iter++
    }

    var isFill: String? = null

    fun check() {
        var a = 9
        for (row in cells) {
            for (cell in row) {
                if (cell != ' ') a--
            }
        }
        if (a != 0)
            isFill = null
        else {
            isFill = "Гамовер! Добро пожаловать отсюда!"
            return
        }
    }
}





