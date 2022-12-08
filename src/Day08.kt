private fun part1(input: List<String>): Int {
    val res = input.size

    val board = input
        .map { it.map { ch -> ch.toString().toInt().toCell() } }
        //.mapIndexed { y, rows -> rows.map { x -> Cell(x, y) } }
        .let { Board(it) }

//    println(board)
    return board.content
        .map { it.count { cell -> cell.visible } }
        .sum()
}

private fun part2(input: List<String>): Int {
    val res = input.size

    return res
}

private data class Board(val content: List<List<Cell>>) {

    init {
        content.first().forEach { it.visible = true }
        content.last().forEach { it.visible = true }
        content.map { it.first() }.forEach { it.visible = true }
        content.map { it.last() }.forEach { it.visible = true }

        val c = content
        val w = c[0].size
        val h = c.size

        println("width: $w, height: $h")
        for (y in 1..h-2) {
            for (x in 1..w-2) {
                val cur = c[y][x]
                val prev = c[y][x-1]
                if (cur.height > prev.height) cur.visible = true
                else if (cur.height < prev.height) break
            }
            for (x in w-2 downTo 1) {
                val cur = c[y][x]
                val prev = c[y][x+1]
                if (cur.height > prev.height) cur.visible = true
                else if (cur.height < prev.height) break
            }
        }
        for (x in 1..w-2) {
            for (y in 1..h-2) {
                val cur = c[y][x]
                val prev = c[y-1][x]
                if (cur.height > prev.height) cur.visible = true
                else if (cur.height < prev.height) break
            }
            for (y in h-2 downTo 1) {
                val cur = c[y][x]
                val prev = c[y+1][x]
                if (cur.height > prev.height) cur.visible = true
                else if (cur.height < prev.height) break
            }
        }
    }

//    private operator fun get(cell: Cell) =
//        content.getOrNull(cell.x)?.getOrNull(cell.y)
}

private fun Int.toCell() = Cell(this)
private class Cell(val height: Int) {
//private class Cell(val x: Int, val y: Int, val height: Int) {
    var visible = false

    override fun toString(): String {
        return if (visible) "[$height,t]" else "[$height,f]"
    }
}

fun main() {
    val testInput = readInput("Day08_test")
    println("Test input:")
    check(part1(testInput) == 21)
    println(part1(testInput))
//    check(part2(testInput) == 19)
    println(part2(testInput))

    println("\nReal input:")
    val input = readInput("Day08")
    println(part1(input))   // 677 too low
    println(part2(input))
}

private val regex = """(\d+),(\d+) -> (\d+),(\d+)""".toRegex()
// regex.matchEntire(it)!!.destructured.toList().int()

/*
val (startX, startY, endX, endY) = inputLineRegex
.matchEntire(s)
?.destructured
?: throw IllegalArgumentException("Incorrect input line $s")
*/

//enum class Direction { UP, DOWN, FORWARD }
//
//data class Step(val direction: Direction, val units: Int)
//
//fun String.toStep() = Step(
//    Direction.valueOf(substringBefore(" ").uppercase()),
//    substringAfter(" ").toInt()
//)
