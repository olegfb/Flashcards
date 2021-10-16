import kotlin.math.pow
import kotlin.math.roundToInt

const val CUBE = 1.0 / 3.0

fun main() {
    println(readLine()!!.toDouble().pow(CUBE).roundToInt())
}