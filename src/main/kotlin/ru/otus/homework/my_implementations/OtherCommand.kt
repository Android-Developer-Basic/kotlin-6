package ru.otus.homework.my_implementations

interface OtherCommand {
    fun execute()
}

class AddCommand(private val value: Double) : OtherCommand{
    override fun execute() {
        println("adding $value")
    }
}

class SubCommand(private val value: Double) : OtherCommand{
    override fun execute() {
        println("subtracting $value")
    }
}

class Processor{
    private val commands = mutableListOf<OtherCommand>()

    fun addCommand(command: OtherCommand){
        commands.add(command)
    }

    fun executeCommands(){
        commands.forEach { it.execute() }
        commands.clear()
    }
}