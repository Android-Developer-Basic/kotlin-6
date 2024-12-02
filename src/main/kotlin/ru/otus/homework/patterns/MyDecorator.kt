package ru.otus.homework.patterns
/*
Decorator. Добавление роботу отдельных модулей друг за другом.
*/

interface Robot {
    fun moduls(): String
}

//создание простого робота
class SimpleRobot : Robot {
    override fun moduls(): String {
        return ""
    }
}

// Завод
abstract class Factory(private val robot: Robot) : Robot {
    override fun moduls(): String {
        return robot.moduls()
    }
}

// Модуль
class Camera(robot: Robot) : Factory(robot) {
    override fun moduls() = super.moduls() + " камера"
}

// Модуль
class Chassis(robot: Robot) : Factory(robot) {
    override fun moduls() = super.moduls() + " шасси"
}

fun main() {
    // Создание простого робота
    var robot: Robot = SimpleRobot()
    // Добавление модулей
    robot = Camera(robot)
    robot = Chassis(robot)
    println("модель: ")
    println(robot.moduls())

    // Модель 2 без шасси
    var robot2: Robot = SimpleRobot()
    robot2 = Camera(robot2)
    println("модель 2: ")
    println(robot2.moduls())
}

