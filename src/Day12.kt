private fun part1(input: List<String>): Int {
    val start = input.find('S')
    val end = input.find('E')
    val grid: Array<IntArray> = createGrid(input)
    input.bfs(grid, end, start, 0)

    return grid.get(end)
}

private fun part2(input: List<String>): Int {
    val start = input.find('S')
    val end = input.find('E')
    var grid: Array<IntArray> = createGrid(input)
    input.bfs(grid, end, start, 0)

    input.find()

    return grid.get(end)
}

private fun createGrid(input: List<String>): Array<IntArray> =
    Array(input.size) { IntArray(input[0].length) { Int.MAX_VALUE } }

private fun List<String>.bfs(grid: Array<IntArray>, end: Point, p: Point, steps: Int) {
    if (steps >= grid.get(p)) return
    grid.set(p, steps)
//    println("point: $p, steps: $steps, end: $end")
    if (p == end) return
    dirs(p)
        //.also { if (steps == 24) println("point: $p, steps: $steps, dirs: $it") }
        .forEach { bfs(grid, end, it, steps + 1) }
}

private fun Array<IntArray>.get(point: Point): Int = this[point.y][point.x]
private fun Array<IntArray>.set(point: Point, steps: Int) {
    this[point.y][point.x] = steps
}

private fun List<String>.find(char: Char): Point {
    forEachIndexed { y, line ->
        val x = line.indexOf(char)
        if (x > -1) return Point(x, y)
    }
    throw RuntimeException("couldn't find start point")
}

private fun List<String>.get(point: Point): Char =
    this[point.y][point.x].let { if (it == 'S') 'a' else if (it == 'E') 'z' else it }

private fun List<String>.dirs(p: Point): List<Point> {
    val list: MutableList<Point> = mutableListOf()
    val w = this[0].length
    val h = this.size
    val elev = get(p) + 1

//    println("elevation: $elev")
    if (p.x > 0 && get(p.xM()) <= elev ) list.add(p.xM())
    if (p.x < w-1 && get(p.xP()) <= elev ) list.add(p.xP())
    if (p.y > 0 && get(p.yM()) <= elev ) list.add(p.yM())
    if (p.y < h-1 && get(p.yP()) <= elev ) list.add(p.yP())
    return list
}

private enum class Direction { UP, DOWN, RIGHT, LEFT }

data class Point(val x: Int, val y: Int) {
    fun xM() = Point(x-1, y)
    fun xP() = Point(x+1, y)
    fun yM() = Point(x, y-1)
    fun yP() = Point(x, y+1)
}

fun main() {
    val testInput = readInput("Day12_test")
    println("Test input:")
    check(part1(testInput) == 31)
    println(part1(testInput))
//    check(part2(testInput) == 19)
    println(part2(testInput))

    println("\nReal input:")
    val input = readInput("Day12")
    println(part1(input))
//    println(part2(input))
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
