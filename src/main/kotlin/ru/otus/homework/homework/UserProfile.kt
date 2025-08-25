@file:Suppress("RemoveRedundantQualifierName")

package ru.otus.homework.homework

import kotlin.reflect.KProperty

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
            TODO("Implement `createWithLogging` function")
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
) : UserProfile {
    val vetoable = VetoableEmail()
    val nonEmptyStringDelegate = NonEmptyStringDelegate()
    override var fullName: String by nonEmptyStringDelegate
    override var email: String by vetoable

    init {
        this.fullName = fullName
        this.email = email
    }

}

class VetoableEmail {
    var email: String = ""
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        if (value.matches(emailRegex))
            email = value
        else
            System.err.println("veto placed on invalid email $value")
    }

    operator fun getValue(hisRef: Any?, property: KProperty<*>): String {
        return this.email
    }
}