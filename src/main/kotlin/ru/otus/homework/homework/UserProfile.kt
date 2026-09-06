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
        fun createWithLogging(fullName: String, email: String): UserProfile.Logging =
            LoggingProfileImplementation(create(fullName, email))
    }
}

/**
 * Проверка емейла на корректность
 */
private val emailRegex = Regex("^[A-Za-z](.*)([@])(.+)(\\.)(.+)")

/**
 * Реализация простого [UserProfile].
 */
private class ProfileImplementation(fullName: String, email: String) : UserProfile {

    override var fullName: String by NonEmptyStringDelegate(fullName)

    override var email: String by Delegates.vetoable(email) { _, _, newValue ->
        emailRegex.matches(newValue)
    }
}

/**
 * Журнал с возможностью записи. Приватный интерфейс: наружу, через [WithLogging],
 * журнал доступен только на чтение.
 */
private interface LogWriter : WithLogging {
    fun record(property: String, oldValue: String, newValue: String)
}

/**
 * Журнал изменений. Отдельный класс, чтобы профиль мог делегировать ему [LogWriter].
 */
private class LogRecorder : LogWriter {
    private val entries = mutableListOf<String>()

    override fun getLog(): List<String> = entries.toList()

    override fun record(property: String, oldValue: String, newValue: String) {
        entries += "Changing `$property` from '$oldValue' to '$newValue'"
    }
}

/**
 * Профиль с логированием: оборачивает готовый [UserProfile] и пишет в журнал
 * каждое реально произошедшее изменение свойства.
 */
private class LoggingProfileImplementation(
    private val profile: UserProfile,
    private val logRecorder: LogRecorder = LogRecorder(),
) : UserProfile.Logging, LogWriter by logRecorder {

    override var fullName: String
        get() = profile.fullName
        set(value) {
            val oldValue = profile.fullName
            profile.fullName = value
            logIfChanged("fullName", oldValue, profile.fullName)
        }

    override var email: String
        get() = profile.email
        set(value) {
            val oldValue = profile.email
            profile.email = value
            logIfChanged("email", oldValue, profile.email)
        }

    /**
     * Пишет в журнал, только если значение действительно изменилось:
     * делегаты [UserProfile] молча отклоняют некорректные значения.
     */
    private fun logIfChanged(property: String, oldValue: String, newValue: String) {
        if (newValue != oldValue) {
            record(property, oldValue, newValue)
        }
    }
}
