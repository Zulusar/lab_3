import kotlin.String


fun main() {
    println(
        "Приветствую в игре! Вводите координаты от 0 до 2 по очереди через пробел " +
                "Если хотите вернуться на несколько ходов, введите 3 и число ходов, на сколько хотите откатиться " +
                "Приятной игры! Желаю удачи!"
    )
    val string1 = "         "
    val cells: Array<Array<Char>> = arrayOf(emptyArray(), emptyArray(), emptyArray())
    val field = Board(cells, string1)
    val condition = State(field)
    var coordinates: Point
    val allGames = Game(condition)
    var enterData: List<Int>?
    var iter = 0

    do {
        println("Введите координаты или команду на откат:")
        enterData = readln().split(" ").map(String::toInt)
        coordinates = Point(enterData[0], enterData[1])
        if (coordinates.x != 3) {
            field.getOrNull(coordinates)
            allGames.step(coordinates)
            if (field.getOrNull(coordinates) != null || !allGames.step(coordinates)) {
                do {
                    println("Место занято. Введите координаты или команду на откат:")
                    enterData = readln().split(" ").map(String::toInt)
                    coordinates = Point(enterData[0], enterData[1])
                    field.getOrNull(coordinates)
                    allGames.step(coordinates)
                } while (field.getOrNull(coordinates) != null || !allGames.step(coordinates))
            }
            allGames.states.add(condition.copyState(condition.copyMassive()))
            field.setAndCopy(coordinates, field[coordinates])
            field.printField()
            field.check()
            condition.checkWin(field)
            condition.win()
            allGames.states.size + 1
            iter++
        }
        if (condition.win()) {
            println(condition.gameResult)
            return
        }
        if (coordinates.x == 3) {
            if (iter<=coordinates.y) {
                do {
                    println("Пока рано возвращаться на столько ходов")
                    enterData = readln().split(" ").map(String::toInt)
                    coordinates = Point(enterData[0], enterData[1])
                    allGames.takeBack(coordinates.y)
                } while (iter<=coordinates.y)
            }
            iter -= coordinates.y
            field.cells = allGames.states[iter].board.cells
            field.printField()
            field.iter = iter+1
        }

    } while (field.isFill == null)
    println(field.isFill)
}

