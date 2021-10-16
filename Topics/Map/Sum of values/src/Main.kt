fun summator(map: Map<Int, Int>): Int {
    var rez = 0
    for ((k, v) in map) if (k % 2 == 0) rez += v
    return rez
}