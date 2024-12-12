package ru.otus.homework.my_implementations

class OtherDecorator (private val innerObject: Any) {

    override fun toString(): String {
        return "Decorated toString of innerObject(${innerObject.toString()})"
    }
}