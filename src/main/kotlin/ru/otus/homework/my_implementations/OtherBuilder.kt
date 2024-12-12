package ru.otus.homework.my_implementations

class Home private constructor(builder:Home.Builder) {
    var address:String? = null
    var builtYear:Int = 0
    init {
        this.address = builder.address
        this.builtYear = builder.builtYear
    }
    companion object Builder {
        var address:String? = null
            private set

        var builtYear:Int = 0
            private set

        fun setAddress(address:String):Builder {
            this.address = address
            return this
        }
        fun setYear(builtYear:Int):Builder {
            this.builtYear = builtYear
            return this
        }
        fun build():Home {
            val car = Home(this)
            return car
        }
    }
}