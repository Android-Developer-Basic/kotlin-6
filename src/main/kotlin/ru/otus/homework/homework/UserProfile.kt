@file:Suppress("RemoveRedundantQualifierName")

package ru.otus.homework.homework

import kotlin.properties.Delegates.vetoable

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
        fun create(fullName: String, email: String ): UserProfile {
            require(fullName.isNotBlank()) { "Full name should not be empty" }
            require(email.isNotBlank() && emailRegex.matches(email)) { "Invalid email" }
            return ProfileImplementation(fullName, email)
        }

        /**
         * Creates user profile with logging
         */
        fun createWithLogging(fullName: String, email: String): UserProfile.Logging {
            return LoggingProfileImplementation(fullName, email)
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
private class ProfileImplementation( fullName: String, email: String): UserProfile {
    override var fullName: String by NonEmptyStringDelegate(fullName)
    override var email: String by vetoable (email) { _, _, newValue ->
        newValue.isNotBlank() && emailRegex.matches(newValue)
    }
}

private class LoggingProfileImplementation(
    fullName: String,
    email: String
) : UserProfile.Logging {
    private val log = mutableListOf<String>()
    private val profile = ProfileImplementation(fullName, email)
    private val oldValue = fullName

    override var fullName: String
        get() = profile.fullName
        set(value) {

            if (value != oldValue) {
                log.add("Changing `fullName` from '$oldValue' to '$value'")
                profile.fullName = value
            }
        }

    override var email: String
        get() = profile.email
        set(value) {
            val oldValue = profile.email
            if (value != oldValue) {
                log.add("Changing `email` from '$oldValue' to '$value'")
                profile.email = value
            }
        }
    init{
        profile.fullName = fullName
    }

    override fun getLog(): List<String> = log.toList()
}
