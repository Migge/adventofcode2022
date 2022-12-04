private fun part1(input: List<String>): Int = input
    .map { regex.intList(it) }
    .map { it.chunked(2) { (a, b) -> a..b } }
    .count { (x, y) -> x.all { it in y } || y.all { it in x } }

private fun part2(input: List<String>): Int = input
    .map { regex.intList(it) }
    .map { it.chunked(2) { (a, b) -> a..b } }
    .count { (x, y) -> x.any { it in y } || y.any { it in x } }

private val regex = """(\d+)-(\d+),(\d+)-(\d+)""".toRegex()

fun main() {
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
