package ru.otus.homework.hw

fun main() {
    val db1 = MySingleton
    println(db1)

    val db2 = MySingleton
    db2.id = 2
    println(db1)
    println(db2 === db1)
}


object MySingleton {
    var id = 0
    fun foo() {
        println("MySingleton.foo()")
    }
    override fun toString(): String {
        return "MySingleton id = $id"
    }
}