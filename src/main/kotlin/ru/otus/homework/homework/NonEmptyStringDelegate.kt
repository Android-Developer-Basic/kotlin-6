package ru.otus.homework.homework

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Delegate that allows to set non-empty string value
 */
class NonEmptyStringDelegate(private var value: String) : ReadWriteProperty<Any?, String> {
    constructor() : this("")

    override fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return value
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        if (value.isNotBlank()) {
            this.value = value
        }
    }
}