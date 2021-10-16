fun main() {
    val n = readLine()!!.toInt()
    var r = 0
    repeat(n) {
        if (readLine()!!.toInt() > 0) r++
    }
    println(r)
}