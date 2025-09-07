@file:Suppress("RemoveRedundantQualifierName")

package ru.otus.homework.homework

import kotlin.properties.Delegates

/**
 * Профиль пользователя
 */
interface UserProfile {
    /**
     * Полное имя
     * Не должно принимать пустые строки
     */
    var fullName: String

    /**
     * Email.
     * Не должен принимать пустые и некорректные строки
     */
    var email: String

    /**
     * Профиль с логированием
     */
    interface Logging : UserProfile, WithLogging

    companion object {
        /**
         * Создает профиль пользователя
         */
        fun create(fullName: String, email: String): UserProfile {
            require(fullName.isNotBlank()) { "Full name should not be empty" }
            require(email.isNotBlank() && emailRegex.matches(email)) { "Invalid email" }
            return ProfileImplementation(fullName, email)
        }

        /**
         * Creates user profile with logging
         */
        fun createWithLogging(fullName: String, email: String): UserProfile.Logging {
            return ProfileWithLoggingImplementation(ProfileImplementation(fullName, email))
        }
    }
}

/**
 * Проверка емейла на корректность
 */
private val emailRegex = Regex("^[A-Za-z](.*)([@])(.+)(\\.)(.+)")

/**
 * Реализация простого [UserProfile].
 */
private class ProfileImplementation(initialFullName: String, initialEmail: String) : UserProfile {
    override var email: String by Delegates.vetoable(initialEmail) { _, _, newValue ->
        return@vetoable newValue.isNotBlank() && emailRegex.matches(newValue)
    }
    override var fullName: String by NonEmptyStringDelegate(initialFullName)
}

private class ProfileWithLoggingImplementation(private val pi: ProfileImplementation) :
    UserProfile.Logging {
    private val log: MutableList<String> = mutableListOf()

    override var fullName: String by Delegates.vetoable(pi.fullName) { property, oldValue, newValue ->
        pi.fullName = newValue
        if (pi.fullName != newValue) return@vetoable false
        log.add("Changing `${property.name}` from '$oldValue' to '${newValue}'")
    }

    override var email: String by Delegates.vetoable(pi.email) { property, oldValue, newValue ->
        pi.email = newValue
        if (pi.email != newValue) return@vetoable false
        log.add("Changing `${property.name}` from '$oldValue' to '${newValue}'")
    }

    override fun getLog(): List<String> = log
}