fun main() {
    val studentsMarks = mutableMapOf<String, Int>()
    var vKey: String
    var vVal: Int
    do {
        vKey = readLine()!!
        if (vKey == "stop") break
        vVal = readLine()!!.toInt()
        if (vKey in studentsMarks.keys) continue
        studentsMarks[vKey] = vVal
    } while (true)
    println(studentsMarks)
}