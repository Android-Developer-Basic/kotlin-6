package ru.otus.homework.patterns

fun main() {
    val lamp = Lamp()
    val lightOn = LightTurnOn(lamp)
    val lightOff = LightTurnOff(lamp)

    RemoteControll(lightOn).pressButton()
    RemoteControll(lightOff).pressButton()
}

interface Command {
    fun execute()
}

class LightTurnOn(private val lite: Lamp) : Command {
    override fun execute() {
        lite.turnOn()
    }
}

class LightTurnOff(private val lite: Lamp) : Command {
    override fun execute() {
        lite.turnOff()
    }
}

class RemoteControll(private val comand: Command) {

    fun pressButton() {
        comand.execute()
    }
}

class Lamp() {
    fun turnOn() {
        println("lightOn")
    }

    fun turnOff() {
        println("lightOff")
    }
}