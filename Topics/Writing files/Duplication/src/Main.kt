// Write your code here. Do not import any libraries
    val text = readLine()!!
    val myFileName = "MyFile.txt"
    val myFile = File(myFileName)
    myFile.writeText("$text$text")