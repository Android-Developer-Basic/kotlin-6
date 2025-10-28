package ru.otus.homework.homework

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Delegate that allows to set non-empty string value
 */
class NonEmptyStringDelegate(var value: String=""): ReadWriteProperty<Any?, String> {

    override fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return value
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, newValue: String) {
        if (newValue.isNotBlank()) {
            this.value = newValue
        } else {
            System.err.println("Property ${property.name} cannot be empty")

        }
    }
}