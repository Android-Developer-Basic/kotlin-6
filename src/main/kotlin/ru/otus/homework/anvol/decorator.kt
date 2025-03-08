package ru.otus.homework.anvol

interface UIComponent {
    fun getContent(): String
    fun draw()
}

class Button(private val title: String): UIComponent {
    override fun draw() {
        println("Button <${getContent()}>")
    }

    override fun getContent(): String {
        return this.title
    }
}

abstract class ButtonDecorator(private val button: UIComponent) : UIComponent {
    override fun draw() {
        button.draw()
    }

    override fun getContent(): String {
        return button.getContent()
    }
}

class ButtonWithImage(button: UIComponent, private val image: String) : ButtonDecorator(button) {
    override fun draw() {
         println("Button <${getContent()}>")
    }

    override fun getContent(): String {
        return "${this.image} ${super.getContent()}"
    }
}

class ButtonWithTooltip(button: UIComponent, private val tooltip: String): ButtonDecorator(button) {
    override fun draw() {
        println("Button <${getContent()}>")
    }

    override fun getContent(): String {
        return "${super.getContent()} [${this.tooltip}]"
    }
}

fun main() {
    val simpleButton : UIComponent = Button("A simple button")
    simpleButton.draw()

    val buttonWithImage : UIComponent = ButtonWithImage(simpleButton, "<image>")
    buttonWithImage.draw()

    val buttonWithImageAndTooltip: UIComponent = ButtonWithTooltip(buttonWithImage, "a tooltip")
    buttonWithImageAndTooltip.draw()

    val buttonWithTooltip: UIComponent = ButtonWithTooltip(simpleButton, "another tooltip")
    buttonWithTooltip.draw()
}