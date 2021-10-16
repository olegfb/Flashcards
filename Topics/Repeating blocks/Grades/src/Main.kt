import kotlin.text.toInt as toInt

fun main() {

    val n = readLine()!!.toInt()
    var arMap = mutableMapOf<Int, Int>(2 to 0, 3 to 0, 4 to 0, 5 to 0)
    repeat(n){
        val x = readLine()!!.toInt()
        arMap[x] = (arMap[x] ?: 0) + 1
    }
    println(arMap.values.joinToString(" "))
}