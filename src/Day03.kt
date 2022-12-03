private fun part1(input: List<String>): Int = input
    .map { it.toList() }
    .map { it.chunked(it.size / 2) }
    .map { (a, b) -> (a.toSet() intersect b.toSet()).first() }
    .sumOf { alphabet.indexOf(it) + 1 }

private fun part2(input: List<String>): Int = input
    .chunked(3)
    .map { it.map { str -> str.toSet() } }
    .map { (it[0] intersect it[1] intersect it[2]).first() }
    .sumOf { alphabet.indexOf(it) + 1 }

private val alphabet = (('a'..'z').toList() + ('A'..'Z').toList())

fun main() {
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
