package ru.otus.homework.patterns

interface Command {
    fun execute()
}

class Receiver {
    fun doAction1 (param: String) {println("doAction1, params: $param")}
    fun doAction2 (param: String) {println("doAction2, params: $param")}
    fun doAction3 (param: String) {println("doAction3, params: $param")}
}

class Action1(private val receiver: Receiver, private val param: String): Command{
    override fun execute() {
        receiver.doAction1(this.param)
    }
}

class Action2(private val receiver: Receiver, private val param: String): Command{
    override fun execute() {
        receiver.doAction2(this.param)
    }
}

class Action3(private val receiver: Receiver, private val param: String): Command{
    override fun execute() {
        receiver.doAction3(this.param)
    }
}

object CommandProcessor{
    fun invoke(commands: List<Command> ) {
        for (command in commands) command.execute()
    }
}

fun main(){
    val receiver = Receiver()
    val commands = listOf(
        Action1(receiver, "action 1"),
        Action2(receiver, "action 2"),
        Action3(receiver, "action 3")
    )
    CommandProcessor.invoke(commands)
}