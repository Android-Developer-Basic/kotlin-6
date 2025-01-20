package ru.otus.homework.patterns

fun main() {
    val childrenWardrobe = WardrobeBuilder()
        .setHeight(150)
        .setLength(100)
        .setNumberOfDoors(2)
        .setColor("Pink")
        .setRail(false)
        .setMirror(false)
        .build()

    val basicWardrobe = WardrobeBuilder()
        .setHeight(180)
        .setLength(180)
        .setNumberOfDoors(4)
        .setRail(true)
        .build()

    println("A wardrobe for kids: $childrenWardrobe")
    println("A wardrobe for parents: $basicWardrobe")
}
data class Wardrobe (
    val height: Int?,
    val length: Int?,
    val numberOfDoors: Int?,
    val color: String?,
    val rail: Boolean?,
    val mirror: Boolean?
    )

class WardrobeBuilder {
    private var height: Int? = null
    private var length: Int? = null
    private var numberOfDoors: Int? = null
    private var color: String? = null
    private var rail: Boolean? = null
    private var mirror: Boolean? = null

    fun setHeight(height: Int): WardrobeBuilder {
        this.height = height
        return this
    }
    fun setLength(length: Int): WardrobeBuilder {
        this.length = length
        return this
    }
    fun setNumberOfDoors(numberOfDoors: Int): WardrobeBuilder {
        this.numberOfDoors = numberOfDoors
        return this
    }
    fun setColor(color: String): WardrobeBuilder{
        this.color = color
        return this
    }
    fun setRail(rail: Boolean): WardrobeBuilder {
        this.rail = rail
        return this
    }
    fun setMirror(mirror: Boolean): WardrobeBuilder {
        this.mirror = mirror
        return this
    }
    fun build (): Wardrobe {
        return Wardrobe (height, length, numberOfDoors, color, rail, mirror)
    }
}
