package ru.otus.homework.patterns

fun main() {
    val light = Light()
    val fan = Fan()

    val lightOn = LightOnCommand(light)
    val lightOff = LightOffCommand(light)
    val fanStart = FanStartCommand(fan)
    val fanStop = FanStopCommand(fan)

    val remoteControl = RemoteControl()

    remoteControl.addCommand(lightOn)
    remoteControl.addCommand(fanStart)
    remoteControl.addCommand(fanStop)
    remoteControl.addCommand(lightOff)
    remoteControl.executeCommands()
}

interface Command {
    fun execute()
}

class Light {
    fun turnOn() {
        println("The light is ON")
    }

    fun turnOff() {
        println("The light is OFF")
    }
}

class Fan {
    fun start() {
        println("The fan is running")
    }

    fun stop() {
        println("The fan is stopped")
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

class FanStartCommand(private val fan: Fan) : Command {
    override fun execute() {
        fan.start()
    }
}

class FanStopCommand(private val fan: Fan) : Command {
    override fun execute() {
        fan.stop()
    }
}

class RemoteControl {
    private val commands = mutableListOf<Command>()

    fun addCommand(command: Command) {
        commands.add(command)
    }

    fun executeCommands() {
        for (command in commands) {
            command.execute()
        }
        commands.clear()
    }
}
