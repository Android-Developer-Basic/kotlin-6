package ru.otus.homework.anvol

import java.lang.Thread.sleep
import java.time.LocalDateTime

data class LogInfo(
    val what: String,
    val time: LocalDateTime = LocalDateTime.now(),
    val level: LogLevel
)

enum class LogLevel { DEBUG, INFO, ERROR }

object Logger {
    private val logs: MutableList<LogInfo> by lazy { ArrayList() }
    private var output: Boolean = true

    fun log(what: String, level: LogLevel = LogLevel.INFO) {
        logs.add(LogInfo(what = what, level = level))
        if (output) output(logs.last())
    }

    fun enableOutput() {
        output = true
    }

    fun disableOutput() {
        output = false
    }

    private fun output(log: LogInfo) {
        with (log) { println("${time}\t$level\t${what}") }
    }

    fun showLogs(logLevel: LogLevel? = null) {
        if (logs.isEmpty()) println("Log is empty!")
        else logs.filter { it.level == logLevel || logLevel == null }
                 .forEach { output(it) }
    }

    fun clearLog() {
        logs.clear()
    }
}

fun main() {
    Logger.log("Log started", LogLevel.DEBUG)
    sleep(500)
    Logger.enableOutput()
    Logger.log("Calculating sum:")
    Logger.log("2+2 = ${2 + 2}")
    sleep(500)
    Logger.disableOutput()
    Logger.log("This will only be logged to memory", LogLevel.ERROR)
    sleep(500)
    Logger.log("Log finished", LogLevel.DEBUG)

    println("Showing ALL logs from memory:")
    Logger.showLogs()

    println("Showing INFO logs from memory:")
    Logger.showLogs(LogLevel.INFO)

    println("Clearing logs:")
    Logger.clearLog()
    Logger.showLogs()
}