fun main() {
    var vMax = 0
    repeat(readLine()!!.toInt()){
        val v = readLine()!!.toInt()
        if ((v % 4) == 0 && v > vMax) vMax = v
    }
    println(vMax)
}