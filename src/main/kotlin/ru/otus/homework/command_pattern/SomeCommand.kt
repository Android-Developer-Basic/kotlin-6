package ru.otus.homework.command_pattern

//command
interface SomeCommand {
    fun execute()
    fun undo()
}