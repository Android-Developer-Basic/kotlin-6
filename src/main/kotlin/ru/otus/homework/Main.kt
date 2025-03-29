package ru.otus.homework


fun main(){
    // Singleton
    println("-----Singleton Pattern-----")
    println(Singleton.property)
    Singleton.property = "New value"
    println(Singleton.property)

    Singleton.method()

    // Decorator
    println("-----Decorator Pattern-----")
    var beverage: Beverage = Coffee()
    println("${beverage.name()} стоит ${beverage.cost()} руб.")
    beverage = MilkDecorator(beverage)
    println("${beverage.name()} стоит ${beverage.cost()} руб.")
    beverage = SugarDecorator(beverage)
    println("${beverage.name()} стоит ${beverage.cost()} руб.")

    // Command
    println("-----Command Pattern-----")

    val light = Light()
    val turnOnLightCommand = TurnOnLightCommand(light)
    val turnOffLightCommand = TurnOffLightCommand(light)

    val remoteControl = RemoteControl()

    remoteControl.addToStackCommand(turnOffLightCommand)
    remoteControl.addToStackCommand(turnOnLightCommand)
    remoteControl.addToStackCommand(turnOnLightCommand)
    remoteControl.addToStackCommand(turnOnLightCommand)
    remoteControl.removeFromStackCommand()
    remoteControl.getCurrentStack()
    remoteControl.execute()

    // Builder
    println("-----Builder Pattern-----")
    val builder = ConcretePizzaBuilder()
    val director = PizzaDirector(builder)

    director.makeMargheritaPizza()
    val margheritaPizza = builder.build()
    println(margheritaPizza)

    director.makePepperoniPizza()
    val peperoniPizza = builder.build()
    println(peperoniPizza)

    builder.setName("Тестовая").setSize(12).addCheese().addMushrooms()
    val customPizza = builder.build()
    println(customPizza)
}
