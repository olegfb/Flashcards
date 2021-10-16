fun helpingTheRobot(purchases: Map<String, Int>, addition: Map<String, Int>): MutableMap<String, Int> {
    val rez = purchases.toMutableMap()
    for ((kAd, vAd) in addition) {
        rez[kAd] = (rez[kAd] ?: 0) + vAd
    }
    return rez
}