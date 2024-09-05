package ru.otus.homework.patterns

/*
Простой декоратор: помещает текст, введенный пользователем, в рамку
 */

fun main() {
    val text = TextImpl()
    text.showText()
    val decoratedText = TextDecorator(text)
    decoratedText.showText()
}


interface Text {
    val text: String
    fun showText()
}

class TextImpl: Text {
    private val scanner = java.util.Scanner(System.`in`)
    override val text = scanner.nextLine() ?: "There's nothing here"
    override fun showText() {
        println(text)
    }
}

class TextDecorator(private val textToDecorate: Text): Text {

    override val text: String = textToDecorate.text

    override fun showText() {
        val length = textToDecorate.text.count()
        println("#${"=".repeat(length)}#")
        println("|${textToDecorate.text}|")
        println("#${"=".repeat(length)}#")
    }
}