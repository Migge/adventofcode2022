private fun part1(input: List<String>): Int {
    val root = buildDisk(input.drop(1))
    return root.flatMap()
        .map { it.size }
        .filter { it <= 100000 }
        .sorted()
        .sum()
}

private fun part2(input: List<String>): Int {
    val root = buildDisk(input.drop(1))
    val remaining = 30000000 - (70000000 - root.size)
    return root.flatMap()
        .map { it.size }
        .filter { it >= remaining }
        .min()
}

private fun buildDisk(instrs: List<String>): Dir {
    val root = Dir("/", null)
    var dir = root
    for (instr in instrs) {
        val t = instr.split(" ")
        when {
            instr == "$ cd .." -> dir = dir.parent!!
            t[1] == "cd" -> dir = dir.children.first { it.name == t[2] }
            t[0] == "dir" -> dir.children += Dir(t[1], dir)
            t[0].toIntOrNull() != null -> dir.addFile(File(t[1], t[0].toInt()))
        }
    }
    return root
}

data class File(val name: String, val size: Int)

class Dir(val name: String, val parent: Dir?) {
    val children = mutableListOf<Dir>()
    val size get() = _size
    private val files = mutableListOf<File>()
    private var _size = 0

    private fun sizeChanged(size: Int) {
        _size += size
        parent?.sizeChanged(size)
    }

    fun addFile(file: File) {
        files += file
        sizeChanged(file.size)
    }
}

private fun Dir.flatMap(): List<Dir> =
    listOf(this) + children.fold(emptyList()) { list, e -> list + e.flatMap() }

fun main() {
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 95437)
    check(part2(testInput) == 24933642)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
