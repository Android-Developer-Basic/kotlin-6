package ru.otus.homework.mypatterns

import ru.otus.homework.mypatterns.tank.Direction
import ru.otus.homework.mypatterns.tank.Tank

interface Command {
    fun execute()
    fun canExecute(): Boolean
}

abstract class BaseCommand : Command {
    override fun execute() {
    }

    override fun canExecute(): Boolean {
        return true
    }
}

abstract class ToggleCommand : BaseCommand() {
    private var _isEnabled: Boolean = true

    fun disable() {
        _isEnabled = false
    }

    fun enable() {
        _isEnabled = true
    }
}

class LambdaCommand(private val onExecute: () -> Unit, private val onCanExecute: () -> Boolean) : BaseCommand() {
    override fun execute() {
        onExecute()
    }

    override fun canExecute(): Boolean {
        return onCanExecute()
    }
}

abstract class TankBaseCommand(protected val tank: Tank) : BaseCommand()

class FireTankCommand(tank: Tank) : TankBaseCommand(tank) {
    override fun canExecute(): Boolean = tank.ammoAmount > 0
    override fun execute() {
        println("Fire!")
        tank.ammoAmount--;
    }
}

class MoveForwardTankCommand(tank: Tank) : TankBaseCommand(tank) {
    override fun canExecute(): Boolean = tank.fuel > 0
    override fun execute() {
        when(tank.direction) {
            Direction.TOP -> tank.location.y++
            Direction.RIGHT -> tank.location.x++
            Direction.BOTTOM -> tank.location.y--
            Direction.LEFT -> tank.location.x--
        }
        tank.fuel--
    }
}

class MoveBackTankCommand(tank: Tank) : TankBaseCommand(tank) {
    override fun canExecute(): Boolean = tank.fuel > 0
    override fun execute() {
        when(tank.direction) {
            Direction.TOP -> tank.location.y--
            Direction.RIGHT -> tank.location.x--
            Direction.BOTTOM -> tank.location.y++
            Direction.LEFT -> tank.location.x++
        }
        tank.fuel--
    }
}

class TurnLeftTankCommand(tank: Tank) : TankBaseCommand(tank) {
    override fun execute() {
        when(tank.direction) {
            Direction.TOP -> tank.direction = Direction.LEFT
            Direction.RIGHT -> tank.direction = Direction.TOP
            Direction.BOTTOM -> tank.direction = Direction.RIGHT
            Direction.LEFT -> tank.direction = Direction.BOTTOM
        }
    }
}

class TurnRightTankCommand(tank: Tank) : TankBaseCommand(tank) {
    override fun execute() {
        when(tank.direction) {
            Direction.TOP -> tank.direction = Direction.RIGHT
            Direction.RIGHT -> tank.direction = Direction.BOTTOM
            Direction.BOTTOM -> tank.direction = Direction.LEFT
            Direction.LEFT -> tank.direction = Direction.TOP
        }
    }
}

class RefuelTankCommand(tank: Tank, private val fuelAmount: Int) : TankBaseCommand(tank) {
    override fun execute() {
        tank.fuel += fuelAmount
    }
}

class RearmTankCommand(tank: Tank, private val ammoAmount: Int) : TankBaseCommand(tank) {
    override fun execute() {
        tank.ammoAmount += ammoAmount
    }
}