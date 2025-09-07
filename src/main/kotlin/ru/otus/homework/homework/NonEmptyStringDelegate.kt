package ru.otus.homework.homework

import kotlin.reflect.KProperty

/**
 * Delegate that allows to set non-empty string value
 */
class NonEmptyStringDelegate(private var initialValue: String = "") {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String = initialValue

    operator fun setValue(thisRef: Any?, property: KProperty<*>, newValue: String) {
        if (newValue.isNotBlank()) initialValue = newValue
    }
}