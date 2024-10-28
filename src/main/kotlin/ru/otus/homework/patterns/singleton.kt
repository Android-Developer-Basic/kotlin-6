package ru.otus.homework.patterns

fun main() {
   val app1 = App.getInstance()
   val app2 = App.getInstance()
   app1.value = 1
   app2.value = 2
   println("app1.value == app2.value == 2: ${app1.value == app2.value && app2.value == 2}")
}

class App private constructor(){
    var value: Int = 0
    companion object{
        private val instance_: App by lazy { App() }
        fun getInstance() = instance_
    }
}

