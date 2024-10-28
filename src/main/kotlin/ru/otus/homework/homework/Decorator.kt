package ru.otus.homework.homework

interface Shape {
    fun draw()
}

class Circle : Shape {
    override fun draw() {
        println("Я круг!")
    }

}

class Rectangle : Shape {
    override fun draw() {
        println("Я квадрат!")
    }

}

class Triangle : Shape {
    override fun draw() {
        println("Я треугольник!")
    }

}

abstract class ShapeDecorator(private val decoratorShape: Shape) : Shape {
    override fun draw() {
        decoratorShape.draw()
    }

}

class RedShapeDecorator(private val decoratorShape: Shape) : ShapeDecorator(decoratorShape) {
    override fun draw() {
        super.draw()
        setRedBorder(decoratorShape)
    }

    private fun setRedBorder(decoratorShape: Shape) {
        println("Сообщение от RedShapeDecorator. Фигура ${decoratorShape.javaClass.simpleName} Цвет границы: красный")
    }
}

fun main() {
    val circle = Circle()
    val redCircle = RedShapeDecorator(Circle())
    val redRectangle = RedShapeDecorator(Rectangle())
    val redTriangle = RedShapeDecorator(Triangle())

    println("Обычный круг")
    circle.draw()

    println("Круг с красной границей")
    redCircle.draw()

    println("Квадрат с красной границей")
    redRectangle.draw()

    println("Треугольник с красной границей")
    redTriangle.draw()
}