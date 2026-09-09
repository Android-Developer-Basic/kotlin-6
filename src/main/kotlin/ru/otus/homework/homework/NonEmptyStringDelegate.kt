package ru.otus.homework.homework

import kotlin.reflect.KProperty

/**
 * Delegate that allows to set non-empty string value
 * сделал логирование в NonEmptyStringDelegate через опциональные параметры, чтобы не делать обертку, иначе тесты с отклонением (assertEquals("Valid Name", user.fullName)) не пройдут, так как будет IllegalArgumentException
 * Если список логов передан — делегат пишет в лог при успешном изменении. Если нет — работает как обычный делегат.
 */
class NonEmptyStringDelegate(
    initialValue: String = "",
    private val logs: MutableList<String>? = null,
    private val propertyName: String? = null
) {
    private var value: String = initialValue

    operator fun getValue(thisRef: Any?, property: KProperty<*>): String = value

    operator fun setValue(thisRef: Any?, property: KProperty<*>, newValue: String) {
        if (newValue.isNotBlank()) {
            val oldValue = value
            value = newValue
            if (logs != null && propertyName != null) {
                logs.add("Changing `$propertyName` from '$oldValue' to '$newValue'")
            }
        }
    }
}