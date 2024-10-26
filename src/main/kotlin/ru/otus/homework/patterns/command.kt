package ru.otus.homework.patterns

fun main() {
    val editor = TextEditor()
    val commandManager = TextEditorCommandManager()

    val addHello = AddTextCommand(editor, "Привет ")
    val addWorld = AddTextCommand(editor, "Мир!")
    val deleteWorld = DeleteTextCommand(editor, 4) // Удаляет "Мир!"

    commandManager.executeCommand(addHello)   // Вывод: Текст после добавления: Привет
    commandManager.executeCommand(addWorld)    // Вывод: Текст после добавления: Привет Мир!
    commandManager.executeCommand(deleteWorld)  // Вывод: Текст после удаления: Привет

    commandManager.undoLastCommand()           // Вывод: Отмена удаления: Восстановление последних 4 символов.
    commandManager.undoLastCommand()           // Вывод: Текст после удаления: Привет
    commandManager.undoLastCommand()           // Вывод: Текст после добавления: Привет
}

interface Command {
    fun execute()
    fun undo()
}

class TextEditor {
    private val text = StringBuilder()

    fun addText(newText: String) {
        text.append(newText)
    }

    fun deleteText(length: Int) {
        if (length > text.length) {
            println("Невозможно удалить больше, чем длина текущего текста.")
            return
        }
        text.delete(text.length - length, text.length)
    }

    fun getText(): String {
        return text.toString()
    }
}

class AddTextCommand(private val editor: TextEditor, private val textToAdd: String) : Command {
    override fun execute() {
        editor.addText(textToAdd)
        println("Текст после добавления: ${editor.getText()}")
    }

    override fun undo() {
        editor.deleteText(textToAdd.length)
        println("Текст после отмены: ${editor.getText()}")
    }
}

class DeleteTextCommand(private val editor: TextEditor, private val length: Int) : Command {
    private var deletedText: String = ""

    override fun execute() {
        // Сохраняем текст, который будет удалён, для последующего восстановления
        deletedText = editor.getText().takeLast(length)
        editor.deleteText(length)
        println("Текст после удаления: ${editor.getText()}")
    }

    override fun undo() {
        // Восстанавливаем удалённый текст при отмене команды
        editor.addText(deletedText)
        println("Отмена удаления: Восстановлен текст \"$deletedText\".")
        println("Текст после отмены: ${editor.getText()}")
    }
}


class TextEditorCommandManager {
    private val commandHistory = ArrayDeque<Command>()

    fun executeCommand(command: Command) {
        command.execute()
        commandHistory.addFirst(command) // Добавляем команду на вершину стека
    }

    fun undoLastCommand() {
        if (commandHistory.isNotEmpty()) {
            val lastCommand = commandHistory.removeFirst() // Извлекаем последнюю команду из стека
            lastCommand.undo()
        } else {
            println("Нет команд для отмены.")
        }
    }
}




