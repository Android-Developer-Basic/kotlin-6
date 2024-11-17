package ru.otus.homework.command_pattern
//invoker
class RemoteControl {
    private val commands: MutableMap<String,SomeCommand> = mutableMapOf()


    private var lastCommand: SomeCommand? = null

    fun setCommand(slot: String, commands: SomeCommand) {

    }

    fun pressButton(slot:String){
        commands[slot].let { command ->
            command?.execute()
            lastCommand = command
        }
    }

    fun pressUndo(){
        lastCommand?.undo()
    }

}