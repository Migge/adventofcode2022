fun main() {

    fun part1(input: List<String>): Int = input
        .multiline()
        .maxOfOrNull { it.split('\n').sumOf(String::toInt) }!!

    fun part2(input: List<String>): Int = input
        .multiline()
        .map { it.split('\n').sumOf(String::toInt) }
        .sortedDescending()
        .take(3)
        .sum()

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
