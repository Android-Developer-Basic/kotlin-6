package ru.otus.homework.patterns

/**
 * https://en.wikipedia.org/wiki/Builder_pattern
 */
fun main() {
    val director = MountainBikeBuildDirector(Bicycle.GtBuilder)
    director.construct()
    val bicycle = director.getResult()
    println(bicycle)
}


class Bicycle private constructor(private val make: String, private val model: String,
                                  private val colour: String, private val height: Int) {
    override fun toString(): String {
        return "Bicycle(make=$make, model=$model, colour=$colour height=$height)"
    }

    interface Builder {
        fun getResult(): Bicycle
        fun setColour(c: String)
        fun setHeight(h: Int)
    }
    companion object GtBuilder : Builder {
        private var colour = ""
        private var height = 0

        override fun getResult(): Bicycle {
            if (height == 29) return Bicycle("GT", "Avalanche", colour, height)
            throw Error("Height must be 29")
        }

        override fun setColour(c: String) {
            colour = c
        }
        override fun setHeight(h: Int) {
            height = h
        }
    }
}

class MountainBikeBuildDirector(private val builder: Bicycle.Builder) {
    fun construct() {
        builder.setColour("Red")
        builder.setHeight(29)
    }
    fun getResult(): Bicycle =  builder.getResult()
}
