fun main() {
    val n = readLine()!!.toInt()
    var vOk = 0
    var vLarge = 0
    var vSmall = 0
    repeat(n){
        when (readLine()!!.toInt()) {
            -1 -> vSmall++
            0  -> vOk++
            1  -> vLarge++
        }
    }
    println("$vOk $vLarge $vSmall")
}