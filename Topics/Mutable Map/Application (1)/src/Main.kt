fun addUser(userMap: Map<String, String>, login: String, password: String): MutableMap<String, String> {
    val mmap = userMap.toMutableMap()
    if (login in userMap.keys) {
        println("User with this login is already registered!")
    } else {
        mmap[login] = password
    }
    return mmap
}