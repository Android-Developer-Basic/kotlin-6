package ru.otus.homework

//Singleton
object SingletonAsObject {
    val fieldOne: Int = 0
}

class SingletonAsClass private constructor(val fieldOne: Int) {
    companion object {
        fun shared(fieldOne: Int): SingletonAsClass {
            return SingletonAsClass(fieldOne)
        }
    }
}

//Decorator
interface StringBuilder {
    fun buildString(intValue: Int): String
}

class SimpleStringBuilder: StringBuilder {
    override fun buildString(intValue: Int): String {
        return "$intValue"
    }
}

class ResulStringBuilder(private val stringBuilder: StringBuilder): StringBuilder {
    override fun buildString(intValue: Int): String {
        return "Result: ${stringBuilder.buildString(intValue)}"
    }
}

//Command
interface Command {
    fun execute()
}

class CommandInvoker {
    fun executeCommand(command: Command) {
        command.execute()
    }
}

class SimplePrintCommand(val value: Int): Command {
    override fun execute() {
        println("$value")
    }
}

class DecorativePrintCommand(val value: Int): Command {
    override fun execute() {
        println("Value printed: $value")
    }
}

class CommandTest {
    fun testCommand() {
        val simplePrintCommand = SimplePrintCommand(0)
        val decorativePrintCommand = DecorativePrintCommand(1)
        val commandInvoker = CommandInvoker()

        commandInvoker.executeCommand(simplePrintCommand)
        commandInvoker.executeCommand(decorativePrintCommand)
    }
}

//Builder
sealed interface ObjectBuilder {
    fun build(value: Int): PrivateConstructorObject
}

class PrivateConstructorObject private constructor(val value: String) {
    companion object : ObjectBuilder {
        override fun build(value: Int): PrivateConstructorObject {
            return PrivateConstructorObject(value.toString())
        }
    }
}
