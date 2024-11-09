package ru.otus.homework

object DatabaseConnection {

    val instance: DatabaseConnection by lazy {
        println("Initializing DatabaseConnection...")
        DatabaseConnection
    }

    fun connect() {
        println("Connecting to database...")
    }

    fun disconnect() {
        println("Disconnecting from database...")
    }
}

fun main() {
    val connection1 = DatabaseConnection.instance
    connection1.connect()
    val connection2 = DatabaseConnection.instance
    connection2.disconnect()
}