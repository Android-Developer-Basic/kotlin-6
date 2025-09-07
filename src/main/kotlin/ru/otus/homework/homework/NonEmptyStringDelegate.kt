package ru.otus.homework.homework

import kotlin.reflect.KProperty

/**
 * Delegate that allows to set non-empty string value
 */
class NonEmptyStringDelegate(initialValue: String ="") {
    private var value = initialValue

    operator fun getValue(thisRef: Any?, property: KProperty<*>): String = value

    operator fun setValue(thisRef: Any?, property: KProperty<*>, newValue: String) {
        if (newValue.isBlank()) {
            return
        }
        value = newValue
    }
}