package ru.otus.homework.homework

fun main() {
    printInlineTask()
    printCoffeeTask()
    printUserProfileTask()
    printLoggingUserProfileTask()
}

private fun printInlineTask() {
    println("Задание 1")
    println("Список: 1, 2, 3, 4")
    println("Результат:")
    skipThreeAndPrint(listOf(1, 2, 3, 4))
    println()
}

private fun printCoffeeTask() {
    println("Задание 2")

    val simpleCoffee = SimpleCoffee()
    val coffeeWithMilk = MilkDecorator(SimpleCoffee())
    val coffeeWithMilkAndSugar = SugarDecorator(coffeeWithMilk)
    val coffeeWithAllIngredients = VanillaDecorator(coffeeWithMilkAndSugar)

    printCoffee("Кофе", simpleCoffee)
    printCoffee("С молоком", coffeeWithMilk)
    printCoffee("С молоком и сахаром", coffeeWithMilkAndSugar)
    printCoffee("Все добавки", coffeeWithAllIngredients)
    println()
}

private fun printCoffee(title: String, coffee: Coffee) {
    println("$title: ${coffee.description()}, цена ${coffee.cost()} коп.")
}

private fun printUserProfileTask() {
    println("Задание 3")

    val user = UserProfile.create("Andrey", "andrey@andreymail.com")
    println("Профиль: fullName='${user.fullName}', email='${user.email}'")

    user.fullName = "Andrey 2"
    user.email = "andrey2@andreymail.com"
    println("Результат: fullName='${user.fullName}', email='${user.email}'")

    user.fullName = "   "
    user.email = "прпрпрп"
    println("Результат (не изменились): fullName='${user.fullName}', email='${user.email}'")
    println()
}

private fun printLoggingUserProfileTask() {
    println("Профиль с логированием")
    val user = UserProfile.createWithLogging("Andrey", "andrey@andreymail.com")
    user.fullName = "Andrey 3"
    user.email = "andrey3@andreymail.com"
    user.fullName = ""
    user.email = "ывывывы"

    println("Результат: fullName='${user.fullName}', email='${user.email}'")
    println("Журнал изменений:")
    user.getLog().forEach { println(it) }
}