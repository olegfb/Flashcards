fun solution(strings: MutableList<String>, str: String): MutableList<String> {
    strings.replaceAll { a -> if (a == str) "Banana" else a }
    return strings
}