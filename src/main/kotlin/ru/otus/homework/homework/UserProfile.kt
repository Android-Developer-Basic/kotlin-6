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
            val profile = create(fullName, email)
            val log = mutableListOf<String>()

            return object : UserProfile.Logging {

                override var fullName: String
                    get() = profile.fullName
                    set(value) {
                        val old = profile.fullName
                        profile.fullName = value

                        if (old != profile.fullName) {
                            log += "Changing `fullName` from '$old' to '${profile.fullName}'"
                        }
                    }

                override var email: String
                    get() = profile.email
                    set(value) {
                        val old = profile.email
                        profile.email = value

                        if (old != profile.email) {
                            log += "Changing `email` from '$old' to '${profile.email}'"
                        }
                    }

                override fun getLog(): List<String> = log
            }
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
private class ProfileImplementation(
    fullName: String,
    email: String
): UserProfile {

    override var email: String by Delegates.vetoable(email) { _, _, newValue ->
        emailRegex.matches(newValue)
    }

    override var fullName: String by Delegates.vetoable(fullName) { _, _, newValue ->
        newValue.isNotBlank()
    }
}