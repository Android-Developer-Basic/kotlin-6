package ru.otus.homework

// Интерфейс команды
interface Command {
    fun execute()
    fun undo()
}
// Реципиент (Receiver)
class Light{
    fun turnOn() {
        println("Свет включен")
    }

    fun turnOff() {
        println("Свет выключен")
    }
}

// Конкретные команды
class TurnOnLightCommand(private val light: Light):Command{
    override fun execute() = light.turnOn()
    override fun undo() = light.turnOff()
}

class TurnOffLightCommand(private val light: Light):Command{
    override fun execute() = light.turnOff()
    override fun undo() = light.turnOn()
}

// Инициатор (Invoker)

class RemoteControl {
    private val commands: ArrayDeque<Command> = ArrayDeque()

    fun getCurrentStack(){
        val str = commands.joinToString(" + ") { "$it" }
        println(str)
    }
    fun addToStackCommand(command: Command){
        commands.add(command)
    }
    fun removeFromStackCommand(){
        commands.removeLast()
    }
    fun execute(){
        commands.forEach { it.execute() }
    }
}
