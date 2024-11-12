package ru.otus.homework.patterns

import ru.otus.homework.Cat
import ru.otus.homework.Dog
import ru.otus.homework.CatVetImpl
import ru.otus.homework.DogVetImpl
import ru.otus.homework.VetClinic

interface Command {
    fun execute()
}

class TreatCatCommand(private val vetClinic: VetClinic, private val cat: Cat) : Command {
    override fun execute() {
        vetClinic.treat(cat)
    }
}

class TreatDogCommand(private val vetClinic: VetClinic, private val dog: Dog) : Command {
    override fun execute() {
        vetClinic.treat(dog)
    }
}

class CommandInvoker {
    private val commands = mutableListOf<Command>()

    fun addCommand(command: Command) {
        commands.add(command)
    }

    fun executeCommands() {
        commands.forEach { it.execute() }
        commands.clear()
    }
}

fun main() {
    val vetClinic = VetClinic(CatVetImpl("Ivan"), DogVetImpl("Petr"))
    val cat = Cat()
    val dog = Dog()

    val treatCatCommand = TreatCatCommand(vetClinic, cat)
    val treatDogCommand = TreatDogCommand(vetClinic, dog)

    val invoker = CommandInvoker()
    invoker.addCommand(treatCatCommand)
    invoker.addCommand(treatDogCommand)

    invoker.executeCommands()
}
