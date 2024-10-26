package ru.otus.homework.patterns

fun main() {
    val ford = CarBuilder()
        .manufacturer("Ford Motors")
        .model("Fusion")
        .yearOfRelease(2005)
        .color("Machine Silver")
        .betweenServiceInterval(20000)
        .build()

    println(ford)

    val toyota = CarBuilder()
        .manufacturer("Toyota")
        .model("Camry")
        .yearOfRelease(2021)
        .color("Red")
        .betweenServiceInterval(15000)
        .build()

    println(toyota)

    val vaz = CarBuilder()
        .manufacturer("Ваз")
        .model("2108")
        .yearOfRelease(2003)
        .color("Green")
        .betweenServiceInterval(15000)
        .build()

    println(vaz)
}

// Авто
class Car(
    val manufacturer: String,
    val model: String,
    val yearOfRelease: Int,
    val color: String,
    val betweenServiceInterval: Int
) {
    override fun toString(): String {
        return "Car(manufacturer='$manufacturer', model='$model', yearOfRelease=$yearOfRelease, color='$color', betweenServiceInterval=$betweenServiceInterval)"
    }
}

// Авто билдер
class CarBuilder {
    private var manufacturer: String = ""
    private var model: String = ""
    private var yearOfRelease: Int = 0
    private var color: String = ""
    private var betweenServiceInterval: Int = 0

    fun manufacturer(manufacturer: String) = apply { this.manufacturer = manufacturer }
    fun model(model: String) = apply { this.model = model }
    fun yearOfRelease(yearOfRelease: Int) = apply { this.yearOfRelease = yearOfRelease }
    fun color(color: String) = apply { this.color = color }
    fun betweenServiceInterval(betweenServiceInterval: Int) = apply { this.betweenServiceInterval = betweenServiceInterval }

    fun build(): Car = Car(manufacturer, model, yearOfRelease, color, betweenServiceInterval)
}
