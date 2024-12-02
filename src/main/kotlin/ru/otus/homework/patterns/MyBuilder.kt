package ru.otus.homework.patterns

/*
Builder. Создаем авто по двум моделям с разным набором комплектующих.
 */

fun main() {
    // содаем объект factory
    val factory1 = Factory1()

    // создаем билдер для модели1
    val builder: CarBuilder = Model1()
    val carModel1: Car = factory1.create(builder)
    println("Модель1:" + "\n" + carModel1.get())
    println()

    // создаем билдер для модели2
    val builder2: CarBuilder = Model2()
    val carModel2: Car = factory1.create(builder2)
    println("Модель2:" + "\n" + carModel2.get())
}

class Car {
    var wheels: Wheels? = null
    var camera1: Camera1? = null
    var light: Light? = null
    var color: Color? = null
    fun get(): String {
        val sb = StringBuilder()
        if (wheels != null) sb.append("шасси \n")
        if (camera1 != null) sb.append("камера \n")
        if (light != null) sb.append(("Свет: " + light!!.lightsView) + " \n")
        if (color != null) sb.append("Цвет: " + color!!.colorName)
        return sb.toString()
    }
}

abstract class CarBuilder {
    lateinit var car: Car
    fun buildCar() {
        car = Car()
    }

    abstract fun setWheels()
    abstract fun setCamera1()
    abstract fun setLight()
    abstract fun setColor()
}

class Factory1 {
    fun create(carBuilder: CarBuilder): Car {
        carBuilder.buildCar()
        carBuilder.setWheels()
        carBuilder.setCamera1()
        carBuilder.setLight()
        carBuilder.setColor()
        return carBuilder.car
    }
}

class Model1 : CarBuilder() {
    override fun setWheels() {
        this.car.wheels = Wheels()
    }

    override fun setCamera1() {

    }

    override fun setLight() {
        this.car.light = Light()
        this.car.light!!.lightsView = "фары"
    }

    override fun setColor() {
        this.car.color = Color()
        this.car.color!!.colorName = "чёрный"
    }

}

class Model2 : CarBuilder() {
    override fun setWheels() {
        this.car.wheels = Wheels()
    }

    override fun setCamera1() {
        this.car.camera1 = Camera1()
    }

    override fun setLight() {

    }

    override fun setColor() {
        this.car.color = Color()
        this.car.color!!.colorName = "белый"
    }
}

class Wheels
class Camera1
class Light {
    var lightsView: String = ""
}

class Color {
    var colorName: String = ""
}