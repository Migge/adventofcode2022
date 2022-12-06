private fun part1(input: List<String>): Int = markerIndex(input.first(), 4)

private fun part2(input: List<String>): Int = markerIndex(input.first(), 14)

private fun markerIndex(input: String, length: Int) = input
    .toList()
    .windowed(length)
    .map { it.distinct() }
    .first { it.size == length }
    .joinToString("")
    .let { input.indexOf(it) + length }

fun main() {
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 19)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
