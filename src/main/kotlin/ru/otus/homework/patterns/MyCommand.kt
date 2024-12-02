package ru.otus.homework.patterns
/*
Command. Подача команд роботу on или off.
*/

class Robot1 {
    fun on() {
        println("robot1 On")
    }

    fun off() {
        println("robot1 off")
    }
}

interface Command {
    fun execute()
}

class ControlPanel {
    private lateinit var button: Command


    fun setCommand(command: Command) {
        button = command
    }

    fun onClickButton() {
        button.execute()
    }
}

class OnCommand(private val robot1: Robot1) : Command {

    override fun execute() {
        robot1.on()
    }
}

class OffCommand(private val robot1: Robot1) : Command {

    override fun execute() {
        robot1.off()
    }
}

fun main() {
    //Объект панели с кнопками
    val panel = ControlPanel()
    //создаем получателя - робота
    val robot1 = Robot1()
    //Объект команды включения робота
    val onCommand = OnCommand(robot1)
    //Объект команды выключения робота
    val offCommand = OffCommand(robot1)
    //нажимаем кнопку on
    panel.setCommand(onCommand)
    panel.onClickButton()
    //нажимаем кнопку off
    panel.setCommand((offCommand))
    panel.onClickButton()
}