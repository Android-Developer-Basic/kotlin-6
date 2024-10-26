package ru.otus.homework.patterns

import java.util.TreeSet

fun main() {
    println("Hello, singleton!")
    println("First call:")
    val db1 = Database.getInstance()
    println(db1.data)
    println("Second call:")
    val db2 = Database.getInstance()
    println(db2.data)

    println("db1 === db2: ${db1 === db2}")


    println()
    println(MemoryMonitor.getInstance().getStackMemoryInfo(1024))  // Предполагаем 1 МБ на поток -Xss1m.
    println()

    val initialCapacity = 5_000_000

    println("-=Array=-")
    MemoryMonitor.getInstance().collectGarbage()
    println(MemoryMonitor.getInstance().getHeapMemoryInfo())
    println()
    val array = IntArray(initialCapacity)
    for(i: Int in 0.. initialCapacity - 1) {
        array[i] = (i+1)*10
    }
    println(MemoryMonitor.getInstance().getMemoryUsageDifferenceInfo())
    println()


    println("-=Set=-")
    MemoryMonitor.getInstance().collectGarbage()
    println(MemoryMonitor.getInstance().getHeapMemoryInfo())
    println()
    val set = HashSet<Int>(initialCapacity)
    for(i: Int in 1..initialCapacity) {
        set.add(i*10)
    }
    println(MemoryMonitor.getInstance().getMemoryUsageDifferenceInfo())
    println()


    println("-=HashMap=-")
    MemoryMonitor.getInstance().collectGarbage()
    println(MemoryMonitor.getInstance().getHeapMemoryInfo())
    println()
    val hashMap = HashMap<Int, Int>(initialCapacity)
    for(i: Int in 1..initialCapacity) {
        hashMap[i] = i*10
    }
    println(MemoryMonitor.getInstance().getMemoryUsageDifferenceInfo())
    println()

    println("-=TreeMap=-")
    MemoryMonitor.getInstance().collectGarbage()
    println(MemoryMonitor.getInstance().getHeapMemoryInfo())
    println()

    val treeMap = HashMap<Int, Int>(initialCapacity)
    for(i: Int in 1..initialCapacity) {
        treeMap[i] = i*10
    }
    println(MemoryMonitor.getInstance().getMemoryUsageDifferenceInfo())
    println()

}

class Database private constructor(val data: Map<String, String>) {
    companion object {
        private var instance: Database? = null

        fun getInstance(): Database {
            if (instance == null) {
                println("Initializing database...")
                instance = Database(mapOf(
                    "1" to "One",
                    "2" to "Two",
                    "3" to "Three"
                ))
            }
            return instance!!
        }
    }
}

class MemoryMonitor private constructor() {

    companion object {
        private var instance: MemoryMonitor? = null

        fun getInstance(): MemoryMonitor {
            if (instance == null) {
                instance = MemoryMonitor()
            }
            return instance!!
        }
    }

    private val runtime: Runtime by lazy { Runtime.getRuntime() }

    // Информация о памяти кучи
    fun getHeapMemoryInfo(): String {
        val maxMemory = runtime.maxMemory() / 1024 / 1024  // MB
        val allocatedMemory = runtime.totalMemory() / 1024 / 1024  // MB
        val freeMemory = runtime.freeMemory() / 1024 / 1024  // MB
        return """
            Информация о памяти кучи:
            Максимальная память: $maxMemory МБ
            Выделенная память: $allocatedMemory МБ
            Свободная память: $freeMemory МБ
            Используемая память: ${allocatedMemory - freeMemory} МБ
        """.trimIndent()
    }

    // Информация о памяти стека с отложенной загрузкой, предполагая размер стека на поток
    fun getStackMemoryInfo(assumedStackSizeKB: Int = 1024): String {
        val activeThreads by lazy { Thread.activeCount() }
        val estimatedStackUsageKB by lazy { activeThreads * assumedStackSizeKB }
        return """
            Информация о памяти стека:
            Предполагаемый размер стека (на поток): ${assumedStackSizeKB} КБ
            Активные потоки: $activeThreads
            Оценка общего использования памяти стека: $estimatedStackUsageKB КБ
        """.trimIndent()
    }
    // Собрать мусор
    fun collectGarbage() {
        runtime.gc()
    }
    // Вывести разницу в использовании памяти
    fun getMemoryUsageDifferenceInfo(): String {
        val beforeGC = runtime.totalMemory() - runtime.freeMemory()  // Использованная память до GC
        collectGarbage()
        val afterGC = runtime.totalMemory() - runtime.freeMemory()   // Использованная память после GC
        val difference = (beforeGC - afterGC) / 1024 / 1024          // Разница в MB
        return """
        Использование памяти до сборки мусора: ${beforeGC / 1024 / 1024} МБ
        Использование памяти после сборки мусора: ${afterGC / 1024 / 1024} МБ
        Освобожденная память после сборки мусора: $difference МБ
        """.trimIndent()
    }
}

