package ru.otus.homework.mypatterns.tank

import ru.otus.homework.mypatterns.*

class TankControlPanel(var tank: Tank) {
    private val _fireCommand = FireTankCommand(tank)
    private val _moveForwardCommand = MoveForwardTankCommand(tank)
    private val _moveBackCommand = MoveBackTankCommand(tank)
    private val _turnRightCommand = TurnRightTankCommand(tank)
    private val _turnLeftCommand = TurnLeftTankCommand(tank)

    fun fireButtonClick() {
        executeCommand(_fireCommand)
    }

    fun rearmButtonClick() {
        executeCommand(RearmTankCommand(tank, 10))
    }

    fun refuelButtonClick() {
        executeCommand(RefuelTankCommand(tank, 100))
    }

    fun joystickUp(){
        executeCommand(_moveForwardCommand)
    }

    fun joystickDown(){
        executeCommand(_moveBackCommand)
    }

    fun joystickRight(){
        executeCommand(_turnRightCommand)
    }

    fun joystickLeft(){
        executeCommand(_turnLeftCommand)
    }

    private fun executeCommand(command: Command) {
        if (command.canExecute()) {
            command.execute()
        }else{
            println("Can't execute command ${command.javaClass.name}")
        }
    }
}