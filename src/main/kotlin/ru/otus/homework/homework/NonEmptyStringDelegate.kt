package ru.otus.homework.homework

import kotlin.reflect.KProperty

/**
 * Delegate that allows to set non-empty string value
 */
class NonEmptyStringDelegate( ) {

    private var strValue = ""

    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return strValue
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, fullName: String) {
        if( fullName.isNotBlank() ) {
            strValue = fullName
        }
    }
}