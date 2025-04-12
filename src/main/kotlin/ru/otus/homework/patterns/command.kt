package ru.otus.homework.patterns

fun main() {
    val myTV: TV = TV()
    val tvOn: Command = TurnOnTVCommand(myTV)
    val tvOff: Command = TurnOffTVCommand(myTV)
    val remoteController: RemoteController = RemoteController(tvOn, tvOff)
    remoteController.clickButton()
    remoteController.clickCancelButton()
}

class TV{
    fun turnOn() {
        println("Телевизор включен")
    }

    fun turnOff() {
        println("Телевизор выключен")
    }
}

interface Command {
    fun execute()
}

class TurnOnTVCommand(private val tv: TV) : Command {
    override fun execute() {
        tv.turnOn()
    }
}

class TurnOffTVCommand(private val tv: TV) : Command {
    override fun execute() {
        tv.turnOff()
    }
}

class RemoteController(
    private val clickButtonCommand: Command,
    private val clickCancelButtonCommand: Command
) {
    fun clickButton() {
        clickButtonCommand.execute()
    }

    fun clickCancelButton() {
        clickCancelButtonCommand.execute()
    }
}