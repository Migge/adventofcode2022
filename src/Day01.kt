private fun part1(input: List<String>): Int = input
    .splitOnEmpty()
    .maxOf { it.int().sum() }

private fun part2(input: List<String>): Int = input
    .splitOnEmpty()
    .map { it.int().sum() }
    .sortedDescending()
    .take(3)
    .sum()

fun main() {
    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
