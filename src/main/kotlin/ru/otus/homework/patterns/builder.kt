package ru.otus.homework.patterns

fun main(){
    val dog = DogBuilder()
        .setName("Снежок")
        .setAge(2)
        .setHeight(50)
        .setColorFur("белый")
        .setColorEye("голубой")
        .setLopEared(false)
        .build()
}

data class Dog(
    val name: String,
    val age: Int,
    val height: Int,
    val colorFur: String,
    val colorEye: String,
    val isLopEared: Boolean,
)

class DogBuilder {
    var name: String = ""
    var age: Int = 0
    var height: Int = 0
    var colorFur: String = ""
    var colorEye: String = ""
    var isLopEared: Boolean = false

    fun setName(name: String) = apply {
        this.name = name
    }

    fun setAge(age: Int) = apply {
        this.age = age
    }

    fun setHeight(height: Int) = apply {
        this.height = height
    }

    fun setColorFur(colorFur: String) = apply {
        this.colorFur = colorFur
    }

    fun setColorEye(colorEye: String) = apply {
        this.colorEye = colorEye
    }

    fun setLopEared(isLopEared: Boolean) = apply {
        this.isLopEared = isLopEared
    }

    fun build() {
        val dog = Dog(name, age, height, colorFur, colorEye, isLopEared)
        if (dog.name == "" || dog.age == 0 || dog.height == 0 || dog.colorFur == "" || dog.colorEye == "") {
            throw Exception("Произошла ошибка")
        }
    }
}
