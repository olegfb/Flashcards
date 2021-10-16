fun bill(priceList: Map<String, Int>, shoppingList: MutableList<String>): Int {
    var rez = 0
    for (shl in shoppingList) {
        rez += priceList[shl] ?: 0
    }
    return rez
}