package ru.otus.homework.patterns

fun main() {
    println("Первый вызов:")
    val db1 = Database.instance
    println(db1.data)

    println("Второй вызов:")
    val db2 = Database.instance
    println(db2.data)

    println("db1 === db2: ${db1 === db2}")
}

class Database private constructor(val data: Map<String, String>) {
    companion object {
        val instance: Database by lazy {
            println("Инициализация ленивого Singleton для Database...")
            Database(mapOf(
                "1" to "One",
                "2" to "Two",
                "3" to "Three"
            ))
        }
    }
}
