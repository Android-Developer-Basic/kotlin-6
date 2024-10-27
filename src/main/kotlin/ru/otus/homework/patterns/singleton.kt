package ru.otus.homework.patterns

import kotlin.concurrent.thread

fun main() {
    checkSingleton()
}

class NewNoSynchronized private constructor() {
    companion object {
        private var instsnce: NewNoSynchronized? = null

        fun getInstance(): NewNoSynchronized {

            if (instsnce == null) {
                instsnce = NewNoSynchronized()
            }
            return requireNotNull(instsnce)
        }
    }
}

class NewSynchronized private constructor() {
    companion object {
        private var instsnce: NewSynchronized? = null

        fun getInstance(): NewSynchronized {

            if (instsnce == null) {
                synchronized(NewSynchronized::class.java) {

                    if (instsnce == null) {
                        instsnce = NewSynchronized()
                    }
                }
            }
            return requireNotNull(instsnce)
        }
    }
}

fun checkSingleton() {
    println("Test NewNoSynchronized")
    repeat(9999) {
        thread {
            val inst = NewNoSynchronized.getInstance()
            val newInst = NewNoSynchronized.getInstance()
            if (inst != newInst) println("There are different objects $inst and $newInst")
        }
    }
    println("Finish NewNoSynchronized")

    println("Test NewSynchronized")
    thread {
        val inst = NewSynchronized.getInstance()
        val newInst = NewSynchronized.getInstance()
        if (inst != newInst) println("There are different objects $inst and $newInst")
    }
    println("Finish NewSynchronized")
}
