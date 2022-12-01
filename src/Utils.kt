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
fun List<String>.multiline(): List<String> {
    var str = ""
    val multiline = mutableListOf<String>()

    this.forEach {
        if (it.trim().isEmpty()) {
            multiline.add(str)
            str = ""
        } else {
            if (str.isNotEmpty()) str += "\n"
            str += "$it"
        }
    }
    if (str.isNotEmpty()) multiline.add(str)
    return multiline
}
