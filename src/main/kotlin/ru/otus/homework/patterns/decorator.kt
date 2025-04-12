package ru.otus.homework.patterns

fun main() {

    val cat: Animal = Cat()
    val fatCat: Animal = FatAnimalDecorator(Cat())

    print("Обычная кошка - ")
    cat.eat()

    print("Толстая кошка - ")
    fatCat.eat()
}


interface Animal {
    fun eat()
}

class Cat : Animal {
    override fun eat(){
        println("Кошка ест")
    }
}

abstract class AnimalDecorator(private val decoratedAnimal: Animal) : Animal {
    override fun eat() {
        decoratedAnimal.eat()
    }
}

class FatAnimalDecorator(decoratedAnimal: Animal) : AnimalDecorator(decoratedAnimal) {
    override fun eat(){
        super.eat()
        println("(Это животное очень много ест)")
    }
}