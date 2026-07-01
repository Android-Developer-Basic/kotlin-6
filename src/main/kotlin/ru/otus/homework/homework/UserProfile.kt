@file:Suppress("RemoveRedundantQualifierName")

package ru.otus.homework.homework

import kotlin.properties.Delegates
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
            require(fullName.isNotBlank()) { "Full name should not be empty" }
            require(email.isNotBlank() && emailRegex.matches(email)) { "Invalid email" }

            return ProfileWithLoggingImplementation(fullName, email)
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
private class ProfileImplementation(initFullName: String, initEmail: String): UserProfile{
    override var fullName: String by NonEmptyStringDelegate(initFullName)
    override var email: String by Delegates.vetoable<String>(initEmail){
            property: KProperty<*>, oldValue, newValue -> emailRegex.matches(newValue)
    }
}

private class ProfileWithLoggingImplementation(initFullName: String, initEmail: String): UserProfile.Logging{

    private val log: MutableList<String> = mutableListOf()

    override var fullName: String by
        LoggingStringDecorator(
            NonEmptyStringDecorator(
                StringProperty(initFullName)
            ),
            log
        )

    override var email: String by LoggingStringDecorator(
        EmailStringDecorator(
            StringProperty(initEmail)
        ),
        log
    )

    override fun getLog(): List<String> = log
}