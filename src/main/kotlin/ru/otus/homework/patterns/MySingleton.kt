package ru.otus.homework.patterns
/*
Singleton. Один Станок производит детали.
каждый раз при вызове Machine вызывается один и тот же объект:
объекты по ссылке равны и счетчик деталей не обнуляется.
*/


fun main() {
    val machine1 = Machine.getInstance("Петя")
    val machine2 = Machine.getInstance("Ваня")
    val machine3 = Machine.getInstance("Вася")
    println(machine1.toString())
    println(machine2.toString())
    println(machine3.toString())
    println("machine1 === machine2 === machine3 : ${machine1 === machine2 && machine2 === machine3}")
}

class Machine private constructor() {
    companion object {
        private var counter = 0
        private var instance: Machine? = null
        fun getInstance(name: String): Machine {
            if (instance == null) {
                instance = Machine()
                println("Включение станка...")
                counter = 0
            }
            println("На станке работает $name")
            counter++
            println("всего деталей произведено: $counter")
            return instance!!
        }
    }
}