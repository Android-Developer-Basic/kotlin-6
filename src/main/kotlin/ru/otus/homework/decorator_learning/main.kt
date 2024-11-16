package ru.otus.homework.decorator_learning

fun main(){
    var myPizza: BasicPizza = Peperoni()

    println(myPizza.createPizza())

    myPizza =  OnionDecorator(myPizza) //декорировал пиццу луком

    println(myPizza.createPizza())


    myPizza = PepperDecorator(myPizza)    //декорировал пиццу перцем

    println(myPizza.createPizza())
}

