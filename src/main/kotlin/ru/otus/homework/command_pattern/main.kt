package ru.otus.homework.command_pattern


fun main(){
    val light = Light()

    // Создаем команды
    val lightOn = LightOnCommand(light)
    val lightOff = LightOffCommand(light)


    val remote = RemoteControl()


    remote.setCommand("ON", lightOn)
    remote.setCommand("OFF", lightOff)


    remote.pressButton("ON")
    remote.pressButton("OFF")


    remote.pressUndo()
}