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
            return LoggingProfileImplementation(create(fullName, email))
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
private class ProfileImplementation(fullName: String, email: String): UserProfile {
    override var fullName: String by NonEmptyStringDelegate()

    override var email: String by Delegates.vetoable(email) { _, _, newValue ->
        newValue.isNotBlank() && emailRegex.matches(newValue)
    }

    init {
        this.fullName = fullName
    }
}

private class LoggingProfileImplementation(
    private val userProfile: UserProfile,
) : UserProfile.Logging, UserProfile by userProfile {
    private val log = mutableListOf<String>()

    override var fullName: String
        get() = userProfile.fullName
        set(value) {
            val oldValue = userProfile.fullName
            userProfile.fullName = value
            if (userProfile.fullName != oldValue) {
                log += "Изменение `fullName` с '$oldValue' на '${userProfile.fullName}'"
            }
        }

    override var email: String
        get() = userProfile.email
        set(value) {
            val oldValue = userProfile.email
            userProfile.email = value
            if (userProfile.email != oldValue) {
                log += "Изменение `email` с '$oldValue' на '${userProfile.email}'"
            }
        }

    override fun getLog(): List<String> = log.toList()
}
