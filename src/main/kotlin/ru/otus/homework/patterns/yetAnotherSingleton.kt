package ru.otus.homework.patterns


class  MySingleton private constructor(private val number: Int){
    companion object {
        fun getInstance(): MySingleton = myInstance

        private val myInstance by lazy {
            counter += 1
            MySingleton(counter)
        }

        private var counter = 0
    }

    fun getNumber() = number
}


fun main() {
    val singleton = MySingleton.getInstance()
    println(singleton.getNumber())

    val singleton2 = MySingleton.getInstance()
    println(singleton2.getNumber())
}

