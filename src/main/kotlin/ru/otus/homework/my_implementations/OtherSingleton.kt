package ru.otus.homework.my_implementations

class OtherSingleton private constructor() {
    init {
        // may be some initialization
        println("init of OtherSingleton $this")
    }

    companion object {
        val instance: OtherSingleton by lazy { OtherSingleton() }
    }
}