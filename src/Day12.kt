private fun part1(input: List<String>): Int {
    val start = input.find('S')
    val end = input.find('E')
    val grid: Array<IntArray> = createGrid(input)
    return input.bfs(grid, end, start, 0)
}

private fun part2(input: List<String>): Int {
    val start = input.find('S')
    val end = input.find('E')

    return input
        .findAll('a')
        .let { it + start }
        .minOf { input.bfs(createGrid(input), end, it, 0) }
}

private fun createGrid(input: List<String>): Array<IntArray> =
    Array(input.size) { IntArray(input[0].length) { Int.MAX_VALUE } }

private fun List<String>.bfs(grid: Array<IntArray>, end: Point, p: Point, steps: Int): Int {
    if (steps >= grid.get(p)) return grid.get(end)
    grid.set(p, steps)
    if (p == end) return grid.get(end)
    dirs(p).forEach { bfs(grid, end, it, steps + 1) }
    return grid.get(end)
}

private fun Array<IntArray>.get(point: Point): Int = this[point.y][point.x]
private fun Array<IntArray>.set(point: Point, steps: Int) {
    this[point.y][point.x] = steps
}

private fun List<String>.find(char: Char): Point = findAll(char).first()
private fun List<String>.findAll(char: Char): List<Point> {
    val list: MutableList<Point> = mutableListOf()
    forEachIndexed { y, line ->
        line
            .mapIndexedNotNull { x, c -> x.takeIf { c == char } }
            .forEach { x -> list.add(Point(x, y)) }
    }
    return list
}

private fun List<String>.get(point: Point): Char =
    this[point.y][point.x].let { if (it == 'S') 'a' else if (it == 'E') 'z' else it }

private fun List<String>.dirs(p: Point): List<Point> {
    val list: MutableList<Point> = mutableListOf()
    val w = this[0].length
    val h = this.size
    val elev = get(p) + 1

    if (p.x > 0 && get(p.xM()) <= elev ) list.add(p.xM())
    if (p.x < w-1 && get(p.xP()) <= elev ) list.add(p.xP())
    if (p.y > 0 && get(p.yM()) <= elev ) list.add(p.yM())
    if (p.y < h-1 && get(p.yP()) <= elev ) list.add(p.yP())
    return list
}

data class Point(val x: Int, val y: Int) {
    fun xM() = Point(x-1, y)
    fun xP() = Point(x+1, y)
    fun yM() = Point(x, y-1)
    fun yP() = Point(x, y+1)
}

fun main() {
    val testInput = readInput("Day12_test")
    check(part1(testInput) == 31)
    check(part2(testInput) == 29)

    val input = readInput("Day12")
    println(part1(input))
    println(part2(input))
}
