package ru.otus.homework.patterns

/*
Простой счётчик
 */

fun main() {
    val myVar = Variable
    val increase = IncreaseCommand(myVar)
    val decrease = DecreaseCommand(myVar)
    val counter = Counter()

    counter.setCommand(increase)
    counter.count()
    println(myVar.value)

    counter.setCommand(increase)
    counter.count()
    println(myVar.value)
}


interface Command {
    fun execute()
    fun undo()
}

class IncreaseCommand(private val variable: Variable) : Command {

    override fun execute() {
        variable.increase()
    }
    override fun undo() {
        variable.decrease()
    }
}

class DecreaseCommand(private val variable: Variable) : Command {

    override fun execute() {
        variable.decrease()
    }
    override fun undo() {
        variable.increase()
    }
}

object Variable {

    var value = 0

    fun increase() {
        value++
    }
    fun decrease() {
        value--
    }
}

class Counter {

    private var command: Command? = null

    fun setCommand(command: Command) {
        this.command = command
    }
    fun count() {
        command?.execute()
    }
}
