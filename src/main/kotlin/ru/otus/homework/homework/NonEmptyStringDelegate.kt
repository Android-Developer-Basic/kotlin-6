package ru.otus.homework.homework

import kotlin.reflect.KProperty

/**
 * Delegate that allows to set non-empty string value
 */
class NonEmptyStringDelegate() {
    private var value: String = ""

    // если модифицровать конструктор NonEmptyStringDelegate, было бы проще..
    fun init(initValue: String) {
        value = initValue
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return value
    }
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        if (value.isNotBlank()) {
            this.value = value
        } else {
            println("empty fullname")
        }
    }
}