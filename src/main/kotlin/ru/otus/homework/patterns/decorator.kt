package ru.otus.homework.patterns

fun main(){
    println("Making Espresso ...")
    val espresso: Coffee = Espresso().also { it.make() }
    println("\nMaking Cuppuccino ...")
    val cappuccino: Coffee = Cuppuccino(espresso).also { it.make() }
    println("\nMaking Cuppuccino with Sugar ...")
    val cappuccinoWithSugar = Sugar(cappuccino).also { it.make() }
}

interface Coffee{
    fun make()
}

abstract class Decorator(private val product: Coffee) : Coffee {
    override fun make(){
        product.make()
    }
}

class Espresso() : Coffee{
    override fun make(){
        println("Make espresso")
    }
}

class Cuppuccino(private val product: Coffee) : Decorator(product){
    override fun make(){
        super.make()
        println("Add milk")
    }
}

class Sugar(private val product: Coffee) : Decorator(product){
    override fun make(){
        super.make()
        println("Add Sugar")
    }
}



