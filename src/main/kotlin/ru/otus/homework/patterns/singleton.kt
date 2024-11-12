package ru.otus.homework.patterns

fun main() {
    println("Hello, singleton!")
    println("First call:")
    val db1 = Database.getInstance()
    println(db1.data)
    println("Second call:")
    val db2 = Database.getInstance()
    println(db2.data)

    println("db1 === db2: ${db1 === db2}")

    println("Первый вызов ленивого Singleton:")
    val lazyDb1 = LazyDatabase.instance
    println(lazyDb1.data)
    println("Второй вызов ленивого Singleton:")
    val lazyDb2 = LazyDatabase.instance
    println(lazyDb2.data)
    println("lazyDb1 === lazyDb2: ${lazyDb1 === lazyDb2}")
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

object LazyDatabase {
    val instance: Database by lazy {
        println("Инициализация ленивого Singleton...")
        Database(mapOf("1" to "One", "2" to "Two", "3" to "Three"))
    }
}