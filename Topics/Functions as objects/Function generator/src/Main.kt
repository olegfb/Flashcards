fun identity(arg: Int) = arg
fun half(arg: Int) = arg / 2
fun zero(arg: Int) = 0

fun generate(functionName: String): (Int) -> Int {
    return when (functionName) {
        "identity" -> ::identity
        "half" -> ::half
        else -> ::zero
    }
}
