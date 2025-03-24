package ru.otus.homework.patterns

fun main() {
    val pult : Pult  = Pult()
    val tv : TV  = TV()
    pult.SetCommand(TVOnCommand(tv))
    pult.PressButton()
    pult.PressUndo()
}

interface ICommand
{
    fun Execute()
    fun Undo()
}

// Receiver - Получатель
class TV
{
    fun On()
    {
        println("Телевизор включен!")
    }

    fun Off()
    {
        println("Телевизор выключен...")
    }
}

class TVOnCommand(tv_ : TV) : ICommand
{
    var tv : TV = tv_
    override fun Execute()
    {
        tv.On()
    }
    override fun Undo()
    {
        tv.Off()
    }
}

// Invoker - инициатор
class Pult
{
    var command : ICommand? = null

    fun SetCommand(com: ICommand) {
        command = com
    }
    fun PressButton() {
        command?.Execute()
    }
    fun PressUndo() {
        command?.Undo()
    }
}