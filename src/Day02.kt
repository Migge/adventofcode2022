private fun part1(input: List<String>): Int = input
    .map { it.toGame() }
    .fold(0) { acc, game -> acc + game.score() }

private fun part2(input: List<String>): Int = input
    .map { it.toForcedGame() }
    .fold(0) { acc, game -> acc + game.score() }

fun main() {
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}

private fun String.toGame() = Game(
    when (substringBefore(" ")) {
        "A" -> Move.ROCK
        "B" -> Move.PAPER
        "C" -> Move.SCISSOR
        else -> throw RuntimeException("invalid move")
    },
    when (substringAfter(" ")) {
        "X" -> Move.ROCK
        "Y" -> Move.PAPER
        "Z" -> Move.SCISSOR
        else -> throw RuntimeException("invalid move")
    }
)

private fun String.toForcedGame() = toGame().newGame(substringAfter(" "))

private enum class Move { ROCK, PAPER, SCISSOR }
private enum class Result { WIN, DRAW, LOSS }

private data class Game(val opp: Move, val me: Move) {
    fun result() = when {
        opp == me -> Result.DRAW
        opp == Move.ROCK && me == Move.SCISSOR -> Result.LOSS
        opp == Move.PAPER && me == Move.ROCK -> Result.LOSS
        opp == Move.SCISSOR && me == Move.PAPER -> Result.LOSS
        else -> Result.WIN
    }
    private fun gameScore() = when (result()) {
        Result.WIN -> 6
        Result.DRAW -> 3
        Result.LOSS -> 0
    }
    private fun moveScore() = when (me) {
        Move.ROCK -> 1
        Move.PAPER -> 2
        Move.SCISSOR -> 3
    }
    fun score() = gameScore() + moveScore()

    fun forceWin() = when (opp) {
        Move.ROCK -> Game(Move.ROCK, Move.PAPER)
        Move.PAPER -> Game(Move.PAPER, Move.SCISSOR)
        Move.SCISSOR -> Game(Move.SCISSOR, Move.ROCK)
    }

    fun forceLoss() = when (opp) {
        Move.ROCK -> Game(Move.ROCK, Move.SCISSOR)
        Move.PAPER -> Game(Move.PAPER, Move.ROCK)
        Move.SCISSOR -> Game(Move.SCISSOR, Move.PAPER)
    }

    fun forceDraw() = Game(opp, opp)

    fun newGame(str: String) = when (str) {
        "X" -> forceLoss()
        "Y" -> forceDraw()
        "Z" -> forceWin()
        else -> throw RuntimeException("invalide code")
    }
}
