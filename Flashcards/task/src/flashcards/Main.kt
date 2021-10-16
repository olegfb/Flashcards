package flashcards

import java.io.File
import java.io.IOException


class FlashCard{
    private val arrFC = mutableMapOf<String, String>()
    private val arrErr = mutableMapOf<String, Int>()
    private val messageLog = mutableListOf<String>()
    var fileOutputName = ""


    fun fcAdd(){
        val vTerm = addTerm()
        if (vTerm == "") return
        val vDefinition = addDefinition()
        if (vDefinition == "") return
        arrFC[vTerm] = vDefinition
        arrErr[vTerm] = 0
        printWithLog("The pair (\"$vTerm\":\"$vDefinition\") has been added.")
    }

    private fun addTerm(): String{
        printWithLog("Card:")
        val inStr = readLineWithLog()
        if (!arrFC.contains(inStr)) return inStr
        printWithLog("The card \"$inStr\" already exists.")
        return ""
    }

    private fun addDefinition(): String{
        printWithLog("The definition of the card:")
        val inStr = readLineWithLog()
        if (!arrFC.containsValue(inStr)) return inStr
        printWithLog("The definition \"$inStr\" already exists.")
        return ""
    }

    private fun findValue(inStr:String): String{
        for (a in arrFC){
            if (a.value == inStr) return a.key
        }
        return ""
    }

    fun fcRemove(){
        printWithLog("Which card?")
        val inStr = readLineWithLog()
        if (arrFC.containsKey(inStr)) {
            arrFC.remove(inStr)
            printWithLog("The card has been removed.")
            arrErr.remove(inStr)
        } else {
            printWithLog("Can't remove \"$inStr\": there is no such card.")
        }
    }

    fun fcAsk(){
        var inStr: String
        printWithLog("How many times to ask?")
        val cntAsk = readLineWithLog().toInt()
        for (i in 1..cntAsk) {
            val vTermRand = arrFC.keys.random()
            printWithLog("Print the definition of \"$vTermRand\":")
            inStr = readLineWithLog()
            if (inStr == arrFC[vTermRand]) printWithLog("Correct!")
            else {
                if (arrFC.containsValue(inStr)) printWithLog(
                    "Wrong. The right answer is \"${arrFC[vTermRand]}\", but your definition is correct for \"${
                        findValue(inStr)}\"."
                )
                else printWithLog("Wrong. The right answer is \"${arrFC[vTermRand]}\".")
                arrErr[vTermRand] = (arrErr[vTermRand] ?: 0) + 1
            }
        }

    }
    fun fcExport(pFileName: String = ""){
        var vFileName = pFileName
        if (vFileName == "") {
            printWithLog("File name:")
            vFileName = readLineWithLog()
        }
        val fExport = File(vFileName).bufferedWriter()
        arrFC.forEach { (t, u) -> fExport.write("$t ### $u ### ${arrErr[t]}\n") }
        fExport.close()
        printWithLog("${arrFC.size} cards have been saved.")
    }

    fun fcImport(pFileName: String = ""){
        var vFileName = pFileName
        if (vFileName == ""){
            printWithLog("File name:")
            vFileName = readLineWithLog()
        }
        var cntLines = 0
        try {
            val fImport = File(vFileName)
            for (line in fImport.readLines()){
                val (vTerm, vDefinition, vErrors) = line.split(" ### ")
                arrFC[vTerm] = vDefinition
                arrErr[vTerm] = vErrors.toInt()
                cntLines++
            }
            printWithLog("$cntLines cards have been loaded.")
        } catch (e: IOException) {
            printWithLog("File not found.")
        }
    }

    fun printWithLog(str: String) {
        messageLog.add(str)
        println(str)
    }

    fun readLineWithLog(): String {
        val str = readLine()!!
        messageLog.add(str)
        return str
    }

    fun saveLog(){
        printWithLog("File name:")
        val vFileName = readLineWithLog()
        val fExport = File(vFileName).bufferedWriter()
        messageLog.forEach { t -> fExport.write("$t\n") }
        fExport.close()
        printWithLog("The log has been saved.")
    }

    fun resetStat(){
        arrErr.replaceAll { _, _ ->  0 }
        printWithLog("Card statistics have been reset.")
    }

    fun showHardCard(){
        if (arrErr.isEmpty()) {
            printWithLog("There are no cards with errors.")
            return
        }
        val vMax = arrErr.maxOf { (_, u) -> u }
        if (vMax == 0) {
            printWithLog("There are no cards with errors.")
            return
        }
        val arrMax = arrErr.filter { it.value == vMax}.keys
        if (arrMax.size == 1) {
            printWithLog("The hardest card is \"${arrMax.first()}\". You have $vMax errors answering it.")
        } else {
            printWithLog("The hardest cards are \"${arrMax.joinToString("\", \"")}\". You have $vMax errors answering them.")
        }

    }

    fun procStartArgs(args: Array<String>) {
        if (args.size !in setOf(2, 4)) return
        for (i in args.indices step 2) {
            when (args[i]) {
                "-import" -> fcImport(args[i+1])
                "-export" -> fileOutputName = args[i+1]
            }
        }
    }
}

fun main(args: Array<String>) {
    val flashCards = FlashCard()
    var inStr: String
    if (args.isNotEmpty()) flashCards.procStartArgs(args)
    do {
        flashCards.printWithLog("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):")
        inStr = flashCards.readLineWithLog()
        when(inStr){
            "add" -> flashCards.fcAdd()
            "remove" -> flashCards.fcRemove()
            "import" -> flashCards.fcImport()
            "export" -> flashCards.fcExport()
            "ask" -> flashCards.fcAsk()
            "log" -> flashCards.saveLog()
            "hardest card" -> flashCards.showHardCard()
            "reset stats" -> flashCards.resetStat()
            "exit" -> {
                if (flashCards.fileOutputName != "") {
                    flashCards.fcExport(flashCards.fileOutputName)
                }
                flashCards.printWithLog("Bye bye!")
                break
            }
            else -> continue
        }
        flashCards.printWithLog("")
    } while (true)
}
