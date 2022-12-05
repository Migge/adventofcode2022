private fun part1(input: List<String>): String {
    val (stacksStr, instrs) = input.splitOnEmpty()
    val stacks = initStacks(stacksStr)

    instrs.forEach {
        val (nr, from, to) = regex.intList(it)
        repeat (nr) {
            stacks[to-1].addLast(stacks[from-1].removeLast())
        }
    }
    return stacks.toList().map { it.last() }.joinToString("")
}

private fun part2(input: List<String>): String {
    val (stacksStr, instrs) = input.splitOnEmpty()
    val stacks = initStacks(stacksStr)

    instrs.forEach {
        val (nr, from, to) = regex.intList(it)
        val stack = ArrayDeque<Char>()
        repeat (nr) {
            stack.addFirst(stacks[from-1].removeLast())
        }
        stacks[to-1].addAll(stack)
    }
    return stacks.toList().map { it.last() }.joinToString("")
}

private fun initStacks(stacksStr: List<String>): Array<ArrayDeque<Char>> {
    val size = stacksStr.last().takeLast(2).trim().toInt()
    val stacks = Array(size) { ArrayDeque<Char>() }
    stacksStr.dropLast(1).forEach {
        for (i in 0 until size) {
            if (it.length > i*4+1 && it[i*4+1] != ' ') {
                stacks[i].addFirst(it[i*4+1])
            }
        }
    }
    return stacks
}

private val regex = """move (\d+) from (\d+) to (\d+)""".toRegex()

fun main() {
    val testInput = readInput("Day05_test")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
