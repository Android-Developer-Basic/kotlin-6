package ru.otus.homework.homework

interface Command {
    fun execute()
}

class Light {
    fun turnOn() {
        println("Свет включен")
    }

    fun turnOff() {
        println("Свет выключен")
    }

    fun changeColor() {
        println("Цвет был изменен")
    }

}

class LightOnCommand(private val light: Light) : Command {

    override fun execute() {
        light.turnOn()
    }

}

class LightOffCommand(private val light: Light) : Command {
    override fun execute() {
        light.turnOff()
    }

}

class LightChangeOffCommand(private val light: Light) : Command {
    override fun execute() {
        light.changeColor()
    }

}

class RemoteControl {

    lateinit var command: Command
    fun pressButton(){
        command.execute()
    }
}

fun main() {
    val light = Light()

    val lightOnCommand = LightOnCommand(light)
    val lightOffCommand = LightOffCommand(light)
    val lightChangeOffCommand = LightChangeOffCommand(light)

    val remoteControl = RemoteControl()

    remoteControl.command = lightOnCommand
    remoteControl.pressButton()

    remoteControl.command = lightOffCommand
    remoteControl.pressButton()

    remoteControl.command = lightChangeOffCommand
    remoteControl.pressButton()

}