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
//(без логирования).
private class ProfileImplementation(
    fullNameInit: String,
    emailInit: String
) : UserProfile {

// можно так, чтобы не выбрасывалось исключение
//    override var fullName: String by Delegates.vetoable(fullNameInit) { _, _, newValue ->
//        newValue.isNotBlank()
//    }
//    либо так (выбросит исключение)
    override var fullName: String by NonEmptyStringDelegate(fullNameInit)

    override var email: String by Delegates.vetoable(emailInit) { _, _, newValue ->
        newValue.isNotBlank() && emailRegex.matches(newValue)
    }
}

private class LoggingProfileImplementation(
    fullNameInit: String,
    emailInit: String
) : UserProfile.Logging {

    private val logs = mutableListOf<String>()

    // С логами — отклоняет пустые строки и пишет в лог при успешном изменении
    override var fullName: String by NonEmptyStringDelegate(fullNameInit, logs, "fullName")

    override var email: String by Delegates.vetoable(emailInit) { _, old, new ->
        if (new.isNotBlank() && emailRegex.matches(new)) {
            logs.add("Changing `email` from '$old' to '$new'")
            true
        } else false
    }

    override fun getLog(): List<String> = logs.toList()
}