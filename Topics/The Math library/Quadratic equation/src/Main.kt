import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt

const val TWO = 2.0
const val FOUR = 4.0

fun main() {
    val a = readLine()!!.toDouble()
    val b = readLine()!!.toDouble()
    val c = readLine()!!.toDouble()
    val x1 = (-b - sqrt(b * b - FOUR * a * c)) / (TWO * a)
    val x2 = (-b + sqrt(b * b - FOUR * a * c)) / (TWO * a)
    println("${min(x1, x2)} ${max(x1, x2)}")
}