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

/**
 * Convenience function for converting string list to int list.
 */
fun List<String>.int(): List<Int> = map { it.toInt() }

/**
 * Convenience functions for capturing regex groups.
 */
fun Regex.strList(str: String): List<String> = matchEntire(str)!!.destructured.toList()
fun Regex.intList(str: String): List<Int> = matchEntire(str)!!.destructured.toList().int()
