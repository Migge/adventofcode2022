import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt")
    .readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 *  Separates by empty line. Each new string contains one or more \n-separated rows.
 */
fun List<String>.splitOnEmpty(): List<List<String>> {
    val res = mutableListOf<List<String>>()
    var group = mutableListOf<String>()

    forEach {
        if (it.trim().isEmpty()) {
            res += group
            group = mutableListOf()
        } else {
            group += it
        }
    }
    res += group
    return res
}

private val regexInt = """\d+""".toRegex()

/**
 * Converts string list to int list.
 */
fun List<String>.int(): List<Int> = map { it.toInt() }

/**
 * Finds all ints in each string.
 */
fun List<String>.ints(): List<List<Int>> = map { it.ints() }

/**
 * Returning all found ints in the string
 */
fun String.ints(): List<Int> = regexInt.findAll(this).map { it.value.toInt() }.toList()

/**
 * Convenience functions for capturing regex groups.
 */
fun Regex.strs(str: String): List<String> = matchEntire(str)!!.destructured.toList()
fun Regex.ints(str: String): List<Int> = strs(str).int()

fun Iterable<Char>.join(): String = this.joinToString("")
