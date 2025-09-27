@file:Suppress("RemoveRedundantQualifierName")

package ru.otus.homework.homework
import kotlin.properties.Delegates.vetoable
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Профиль пользователя
 */
interface UserProfile {
    var fullName: String
    var email: String

    interface Logging : UserProfile, WithLogging

    companion object {
        fun create(fullName: String, email: String): UserProfile {
            require(fullName.isNotBlank()) { "Full name should not be empty" }
            require(email.isNotBlank() && emailRegex.matches(email)) { "Invalid email" }
            return ProfileImplementation(fullName, email)
        }

        fun createWithLogging(fullName: String, email: String): UserProfile.Logging {
            return LoggingProfileImplementation(fullName, email)
        }
    }
}

private val emailRegex = Regex("^[A-Za-z](.*)([@])(.+)(\\.)(.+)")

private class ProfileImplementation(
    fullName: String,
    email: String
) : UserProfile {
    override var fullName: String by MyNonEmptyStringDelegate(fullName)

    var fullNameForTest: String by NonEmptyStringDelegate(fullName)

    override var email: String by vetoable(email) { _, _, newValue ->
        newValue.isNotBlank() && emailRegex.matches(newValue)
    }
    init {
        this.fullNameForTest = fullName
    }
}

/**
 * Delegate that allows to set non-empty string value
 */
class MyNonEmptyStringDelegate(private var value: String) : ReadWriteProperty<Any?, String> {

    override fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return value
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        if (value.isNotBlank()) {
            this.value = value

            if (thisRef is ProfileImplementation) {
                thisRef.fullNameForTest = value
            }
        }
    }
}

/**
 * ✅ ЗАДАНИЕ 3.3: Реализация профиля с логированием
 */
private class LoggingProfileImplementation(
    fullName: String,
    email: String
) : UserProfile.Logging {
    private val log = mutableListOf<String>()
    private val baseProfile = ProfileImplementation(fullName, email)

    override var fullName: String
        get() = baseProfile.fullName
        set(value) {
            val oldValue = baseProfile.fullName
            if (value != oldValue) {
                log.add("Changing `fullName` from '$oldValue' to '$value'")
                baseProfile.fullName = value
            }
        }

    override var email: String
        get() = baseProfile.email
        set(value) {
            val oldValue = baseProfile.email
            if (value != oldValue) {
                log.add("Changing `email` from '$oldValue' to '$value'")
                baseProfile.email = value
            }
        }

    override fun getLog(): List<String> = log.toList()
}