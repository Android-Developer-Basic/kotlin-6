package ru.otus.homework.patterns

/*
Оооочень простой билдер
 */

fun main() {
    val user = UserBuilder()
        .name("Jhon")
        .surname("Wick")
        .age(45)
        .gender("male")
        .build()

    println(user)
}


class User (
    var name: String = "",
    var surname: String = "",
    var age: Int = 0,
    var gender: String = ""
)

class UserBuilder {
    private val user = User()

    fun name(name: String) = apply { user.name = name }
    fun surname(surname: String) = apply { user.surname = surname }
    fun age(age: Int) = apply { user.age = age }
    fun gender(gender: String) = apply { user.gender = gender }

    fun build(): User = user
}