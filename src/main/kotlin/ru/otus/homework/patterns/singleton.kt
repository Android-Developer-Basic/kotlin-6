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
}


object Database  {

    val data: Map<String?, String?> by lazy {
        print("Initializing database")
        mapOf(
            "1" to " One",
            "2" to "Two",
            "3" to "Three")
    }

    fun getInstance(): Database {
        return this
    }

}
